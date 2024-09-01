package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.Tag;

import java.util.List;

public interface TagService extends IService<Tag> {
    // 根据分类查找标签
    List<Tag> getTagsByCategory(int categoryId);
}
