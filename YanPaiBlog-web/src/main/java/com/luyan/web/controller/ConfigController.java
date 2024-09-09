package com.luyan.web.controller;

import com.luyan.entity.domain.Config;
import com.luyan.entity.utils.R;
import com.luyan.service.ConfigService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("config")
public class ConfigController {
    @Resource
    private ConfigService configService;

    @GetMapping("type")
    public R<List<Config>> getConfig(Integer type) {
        log.info("getConfig - {}", type);
        return R.ok(configService.getConfigByType(type));
    }
}
