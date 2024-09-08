package com.luyan.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MsgDto {
    private Integer id;
    private String photo;
    private String msg;
    private Integer state;
    private LocalDateTime updateTime;
}
