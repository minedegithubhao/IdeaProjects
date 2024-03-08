package com.smartdrm.entity.system;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author cxdpc
 * @date 2024/1/29 09:51
 */
@Data
public class SysDictType {

    private Integer dictId;

    private String dictName;

    private String dictType;

    private String status;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private String remark;
}
