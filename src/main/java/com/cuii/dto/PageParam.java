package com.cuii.dto;

import lombok.Data;

@Data
public class PageParam {

    private Integer current = 1;

    private Integer pageSize = 10;

}
