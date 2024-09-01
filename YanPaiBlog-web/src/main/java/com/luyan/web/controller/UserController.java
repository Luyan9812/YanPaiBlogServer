package com.luyan.web.controller;

import com.luyan.entity.domain.User;
import com.luyan.entity.dto.UserInfoDto;
import com.luyan.entity.utils.R;
import com.luyan.entity.utils.ResultCodeEnum;
import com.luyan.service.UserInfoService;
import com.luyan.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    @PostMapping("register")
    public R<Object> register(@RequestBody @Validated User user) {
        log.info("register: {}", user);
        userService.register(user.getUsername(), user.getPassword());
        return R.ok(null);
    }

    @PostMapping("login")
    public R<Object> login(@RequestBody @Validated User user) {
        log.info("login: {}", user);
        String token = userService.login(user.getUsername(), user.getPassword());
        if (token == null) {
            return R.error(ResultCodeEnum.UNKNOWN_ERROR);
        }
        return R.okPairs(R.Pair.of("token", token));
    }

    @GetMapping("indexUserInfo")
    public R<Map<String, Object>> getIndexUserInfo() {
        return R.ok(userInfoService.getIndexUserInfo());
    }

    @GetMapping("userInfo")
    public R<UserInfoDto> getUserInfo() {
        return R.ok(userInfoService.getUserInfo());
    }

    @GetMapping("achievement")
    public R<Object> getAchievement() {
        return R.ok(userService.getAchievement());
    }
}
