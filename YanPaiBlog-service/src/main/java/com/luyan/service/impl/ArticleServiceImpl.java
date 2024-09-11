package com.luyan.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.ArticleDetail;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.entity.domain.UserInfo;
import com.luyan.entity.dto.ArticleDto;
import com.luyan.entity.dto.SaveArticleDto;
import com.luyan.entity.exception.ServiceException;
import com.luyan.entity.utils.ResultCodeEnum;
import com.luyan.mapper.ArticleMapper;
import com.luyan.service.*;
import com.luyan.utils.BaseContext;
import com.luyan.utils.ImagePathUtil;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Value("${global.page-size}")
    private Integer pageSize;
    @Value("${global.upload-covers}")
    private String uploadCovers;
    @Value("${global.upload-headers}")
    private String uploadHeaders;
    @Value("${global.base-upload-path}")
    private String baseUploadPath;

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagService articleTagService;
    @Resource
    private ArticleDetailService articleDetailService;
    @Resource
    private UserFootService userFootService;
    @Resource
    private UserInfoService userInfoService;


    private Page<ArticleDto> pageArticleToDto(Page<Article> page) {
        Page<ArticleDto> result = new Page<>();
        BeanUtils.copyProperties(page, result, "records");
        result.setRecords(page.getRecords().stream().map((article) -> {
            ArticleDto dto = new ArticleDto();
            BeanUtils.copyProperties(article, dto);
            dto.setReadNum(userFootService.getReadNumByArticle(article.getId()));
            dto.setCollectionNum(userFootService.getCollectionNumByArticle(article.getId()));
            dto.setAuthorInfo(userInfoService.getUserInfoByUid(article.getUserId()));
            dto.setTags(articleTagService.getTagByArticle(article.getId()));
            return dto;
        }).collect(Collectors.toList()));
        return result;
    }

    @Override
    public long getPublishNum(int uid) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getUserId, uid);
        return articleMapper.selectCount(wrapper);
    }

    @Override
    @SneakyThrows(IOException.class)
    public String uploadFile(String type, MultipartFile file) {
        Set<String> allow = Set.of("png", "jpg", "jpeg");
        String originalName = file.getOriginalFilename();
        String extName = FileUtil.extName(originalName);
        if (!allow.contains(extName)) {
            throw new ServiceException("文件格式错误");
        }
        String targetName = String.format("%s.%s", IdUtil.simpleUUID(), extName);
        String uploadPath = type.equals("headers") ? uploadHeaders : uploadCovers;
        File target = new File(uploadPath, targetName);
        file.transferTo(target);
        return String.format("/upload/%s/%s", type, targetName);
    }

    @Override
    public void deleteFile(String path) {
        String key = "upload/";
        String fileName = path.substring(path.indexOf(key) + key.length());
        File file = new File(baseUploadPath, fileName);
        FileUtil.del(file);
    }

    @Override
    public void deleteFiles(List<String> paths) {
        paths.forEach((path) -> {
            if (!path.contains("upload")) return;
            deleteFile(path);
        });
    }

    @Override
    public int saveArticle(SaveArticleDto saveArticleDto) {
        saveArticleDto.setStatus(1);
        saveArticleDto.setUserId(BaseContext.getCurrentId());
        articleMapper.insert(saveArticleDto);

        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(saveArticleDto.getId());
        articleDetail.setContent(ImagePathUtil.removeDomain(saveArticleDto.getContent()));
        articleDetailService.save(articleDetail);

        saveArticleDto.getTags().forEach((tagId) -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tagId);
            articleTag.setArticleId(saveArticleDto.getId());
            articleTagService.save(articleTag);
        });
        return saveArticleDto.getId();
    }

    @Override
    public void updateArticle(SaveArticleDto saveArticleDto) {
        Article article = new Article();
        article.setId(saveArticleDto.getId());
        article.setTitle(saveArticleDto.getTitle());
        article.setPicture(saveArticleDto.getPicture());
        article.setSummary(saveArticleDto.getSummary());
        article.setCategoryId(saveArticleDto.getCategoryId());
        articleMapper.updateById(article);

        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(saveArticleDto.getId());
        articleDetail.setContent(ImagePathUtil.removeDomain(saveArticleDto.getContent()));
        articleDetailService.updateArticleDetail(articleDetail);

        articleTagService.deleteTagsByArticle(saveArticleDto.getId());
        saveArticleDto.getTags().forEach((tagId) -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tagId);
            articleTag.setArticleId(saveArticleDto.getId());
            articleTagService.save(articleTag);
        });
    }

    @Override
    public Page<ArticleDto> getArticlesByCategory(int categoryId, int currentPage) {
        if (currentPage <= 0) {
            throw new ServiceException(String.format("页码 {%d} 错误", currentPage));
        }
        Page<Article> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(categoryId > 0, Article::getCategoryId, categoryId);
        wrapper.eq(Article::getStatus, 1);
        wrapper.orderByDesc(Article::getHotScore, Article::getUpdateTime);
        articleMapper.selectPage(page, wrapper);
        return pageArticleToDto(page);
    }

    @Override
    public Page<ArticleDto> getPublishedArticles(int currentPage) {
        if (currentPage <= 0) {
            throw new ServiceException(String.format("页码 {%d} 错误", currentPage));
        }
        int uid = BaseContext.getCurrentId();
        Page<Article> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getUserId, uid);
        wrapper.eq(Article::getStatus, 1);
        wrapper.orderByDesc(Article::getUpdateTime);
        articleMapper.selectPage(page, wrapper);
        return pageArticleToDto(page);
    }

    @Override
    public Page<ArticleDto> getReadArticles(int currentPage) {
        if (currentPage <= 0) {
            throw new ServiceException(String.format("页码 {%d} 错误", currentPage));
        }
        int uid = BaseContext.getCurrentId();
        Page<Article> page = new Page<>(currentPage, pageSize);
        articleMapper.getReadArticles(page, uid);
        return pageArticleToDto(page);
    }

    @Override
    public Page<ArticleDto> getCollectionArticles(int currentPage) {
        if (currentPage <= 0) {
            throw new ServiceException(String.format("页码 {%d} 错误", currentPage));
        }
        int uid = BaseContext.getCurrentId();
        Page<Article> page = new Page<>(currentPage, pageSize);
        articleMapper.getCollectionArticles(page, uid);
        return pageArticleToDto(page);
    }

    @Override
    public ArticleDto getArticleById(int articleId) {
        Article article = articleMapper.selectById(articleId);
        if (article == null) {
            throw new ServiceException(ResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        ArticleDto result = new ArticleDto();
        Integer uid = BaseContext.getCurrentId();
        if (uid != null) {  // 登录情况下设置文章阅读状态
            userFootService.setArticleReadState(articleId, true);
            UserFootService.UserState state = userFootService.getUserStatInArticle(uid, articleId);
            result.setHasPraised(state.isHasPraised());
            result.setHasCollection(state.isHasCollection());
        }
        ArticleDetail detail = articleDetailService.getArticleDetail(articleId);
        UserInfo userInfo = userInfoService.getUserInfoByUid(article.getUserId());
        BeanUtils.copyProperties(article, result);
        result.setContent(detail.getContent());
        result.setAuthorInfo(userInfo);
        result.setTags(articleTagService.getTagByArticle(articleId));
        return result;
    }

    @Override
    public List<Object> getHotArticles() {
        Page<Article> page = new Page<>(1, 10);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus, 1);
        wrapper.orderByDesc(Article::getHotScore);
        wrapper.orderByDesc(Article::getUpdateTime);
        articleMapper.selectPage(page, wrapper);
        return page.getRecords().stream().map((article -> new HashMap<String, Object>() {
            {
                put("id", article.getId());
                put("title", article.getTitle());
            }
        })).collect(Collectors.toList());
    }

    @Override
    public void deleteArticle(int articleId) {
        int uid = BaseContext.getCurrentId();
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getUserId, uid);
        wrapper.eq(Article::getId, articleId);
        Article article = articleMapper.selectOne(wrapper);
        if (article == null) {
            throw new ServiceException(ResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        articleMapper.deleteById(article.getId());
        articleDetailService.deleteArticle(article.getId());
        articleTagService.deleteTagsByArticle(article.getId());
    }

    @Override
    public Page<ArticleDto> getArticlesByTag(Integer tagId, Integer currentPage) {
        Page<Article> page = new Page<>(currentPage, pageSize);
        articleMapper.getArticlesByTag(page, tagId);
        return pageArticleToDto(page);
    }

    @Override
    public Page<ArticleDto> search(String key, Integer currentPage) {
        Page<Article> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Article::getTitle, key);
        articleMapper.selectPage(page, wrapper);
        return pageArticleToDto(page);
    }
}
