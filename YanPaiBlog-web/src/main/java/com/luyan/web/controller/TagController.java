package com.luyan.web.controller;

import com.luyan.entity.domain.Tag;
import com.luyan.entity.utils.R;
import com.luyan.service.TagService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin({"http://47.96.95.64"})
@RestController
@RequestMapping("tag")
public class TagController {
    @Resource
    private TagService tagService;

    @GetMapping("getTagsByCategory")
    public R<List<Tag>> getTagsByCategory(Integer categoryId) {
        log.info("getTagsByCategory - {}", categoryId);
        return R.ok(tagService.getTagsByCategory(categoryId));
    }
}
