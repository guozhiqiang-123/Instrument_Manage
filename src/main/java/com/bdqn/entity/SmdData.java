package com.bdqn.entity;

import lombok.Data;

@Data
public class SmdData {
    private Integer id;//主键ID
    private String model;//产品型号
    private Integer num;//扫描数量
    //private Integer line;//拉号
    private String currentTime;//记录时间
}
