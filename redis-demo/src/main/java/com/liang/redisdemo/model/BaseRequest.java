package com.liang.redisdemo.model;

import lombok.Data;

/**
 * @author kfz
 * @create 2023-01-16 15:58
 */
@Data
public class BaseRequest {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
