package com.luyan.web.controller;

import com.luyan.entity.domain.User;
import com.luyan.entity.domain.UserInfo;
import com.luyan.entity.dto.MsgDto;
import com.luyan.entity.dto.UserInfoDto;
import com.luyan.entity.utils.R;
import com.luyan.entity.utils.ResultCodeEnum;
import com.luyan.service.UserInfoService;
import com.luyan.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin({"http://47.96.95.64"})
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserInfoService userInfoService;

    @PostMapping("register")
    public R<Object> register(@RequestBody @Validated User user) {
        log.info("register - {}", user);
        userService.register(user.getUsername(), user.getPassword());
        return R.ok(null);
    }

    @PostMapping("login")
    public R<Object> login(@RequestBody @Validated User user) {
        log.info("login - {}", user);
        String token = userService.login(user.getUsername(), user.getPassword());
        if (token == null) {
            return R.error(ResultCodeEnum.UNKNOWN_ERROR);
        }
        return R.okPairs(R.Pair.of("token", token));
    }

    @GetMapping("indexUserInfo")
    public R<Object> getIndexUserInfo() {
        log.info("getIndexUserInfo");
        return R.ok(userInfoService.getIndexUserInfo());
    }

    @GetMapping("userInfo")
    public R<UserInfoDto> getUserInfo() {
        log.info("getUserInfo");
        return R.ok(userInfoService.getUserInfo());
    }

    @PutMapping("update")
    public R<Object> updateUserInfo(@RequestBody UserInfo userInfo) {
        log.info("updateUserInfo - {}", userInfo);
        userInfoService.updateUserInfo(userInfo);
        return R.ok(null);
    }

    @GetMapping("author")
    public R<UserInfoDto> getAuthorInfo(Integer authorId) {
        log.info("getAuthorInfo - {}", authorId);
        return R.ok(userService.getAuthorInfo(authorId));
    }

    @GetMapping("achievement")
    public R<Object> getAchievement() {
        log.info("getAchievement");
        return R.ok(userService.getAchievement());
    }

    @GetMapping("changeFollowState")
    public R<Object> changeFollowState(Integer authorId, Boolean followState) {
        log.info("changeFollowState - {}, {}", authorId, followState);
        userService.changeFollowState(authorId, followState);
        return R.ok(null);
    }

    @GetMapping("fansList")
    public R<List<UserInfoDto>> getFansList() {
        log.info("getFansList");
        return R.ok(userInfoService.getFansList());
    }

    @GetMapping("followList")
    public R<List<UserInfoDto>> getFollowList() {
        log.info("getFollowList");
        return R.ok(userInfoService.getFollowList());
    }

    @GetMapping("messages")
    public R<List<MsgDto>> getMessage(Integer type) {
        log.info("getMessage - {}", type);
        return R.ok(userService.getMessages(type));
    }
}
