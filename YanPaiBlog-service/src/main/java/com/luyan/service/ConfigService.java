package com.luyan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luyan.entity.domain.Config;

import java.util.List;

public interface ConfigService extends IService<Config> {
    List<Config> getConfigByType(Integer type);
}
