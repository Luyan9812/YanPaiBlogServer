package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Tag;
import com.luyan.mapper.TagMapper;
import com.luyan.service.TagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {
    @Resource
    private TagMapper tagMapper;

    @Override
    public List<Tag> getTagsByCategory(int categoryId) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getCategoryId, categoryId);
        return tagMapper.selectList(wrapper);
    }
}
