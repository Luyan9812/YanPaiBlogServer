package com.luyan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luyan.entity.domain.Config;
import com.luyan.mapper.ConfigMapper;
import com.luyan.service.ConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config>
        implements ConfigService {
    @Resource
    private ConfigMapper configMapper;

    @Override
    public List<Config> getConfigByType(Integer type) {
        LambdaQueryWrapper<Config> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Config::getType, type);
        wrapper.eq(Config::getStatus, 1);
        return configMapper.selectList(wrapper);
    }
}
