package com.luyan.entity.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private Integer saltPos;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
