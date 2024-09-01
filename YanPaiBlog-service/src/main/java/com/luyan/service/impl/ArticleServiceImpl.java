package com.luyan.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Article;
import com.luyan.entity.domain.ArticleDetail;
import com.luyan.entity.domain.ArticleTag;
import com.luyan.entity.dto.SaveArticleDto;
import com.luyan.entity.exception.ServiceException;
import com.luyan.mapper.ArticleMapper;
import com.luyan.service.ArticleDetailService;
import com.luyan.service.ArticleService;
import com.luyan.service.ArticleTagService;
import com.luyan.utils.BaseContext;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@Slf4j
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {
    @Value("${global.upload_covers}")
    private String uploadCovers;
    @Value("${global.upload_headers}")
    private String uploadHeaders;
    @Value("${global.base_upload_path}")
    private String baseUploadPath;

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleTagService articleTagService;
    @Resource
    private ArticleDetailService articleDetailService;

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
    public void delete(String path) {
        String key = "upload/";
        String fileName = path.substring(path.indexOf(key) + key.length());
        File file = new File(baseUploadPath, fileName);
        FileUtil.del(file);
    }

    @Override
    public int saveArticle(SaveArticleDto saveArticleDto) {
        saveArticleDto.setStatus(1);
        saveArticleDto.setUserId(BaseContext.getCurrentId());
        articleMapper.insert(saveArticleDto);

        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(saveArticleDto.getId());
        articleDetail.setContent(saveArticleDto.getContent());
        articleDetailService.save(articleDetail);

        saveArticleDto.getTags().forEach((tagId) -> {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tagId);
            articleTag.setArticleId(saveArticleDto.getId());
            articleTagService.save(articleTag);
        });
        return saveArticleDto.getId();
    }
}
