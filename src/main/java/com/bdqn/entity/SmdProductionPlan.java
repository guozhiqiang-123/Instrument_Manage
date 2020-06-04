package com.bdqn.entity;

import lombok.Data;

/**
 * Created by john on 2019/7/22.
 */
@Data
public class SmdProductionPlan {
    private int id;//主键
    private String produceName;//产品型号
    private String machine;//机器编号
    private String planNum;//计划产量
    private int multiple;//PCB单板倍数
}
