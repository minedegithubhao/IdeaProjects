package com.smartdrm.entity.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author cxdpc
 * @date 2024/1/26 15:51
 */
@Data
public class SysDictData {

    private int dictCode;

    private int dictSort;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private String cssClass;

    private String listClass;

    private String isDefault;

    private String status;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private String remark;
}
