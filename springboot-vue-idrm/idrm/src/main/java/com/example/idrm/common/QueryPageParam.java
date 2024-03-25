package com.example.idrm.common;

import lombok.Data;

/**
 * @author cxdpc
 * @date 2024/3/25 10:45
 */
@Data
public class QueryPageParam<T> {

    // 默认为20，1
    private int pageSize = 20;

    private int pageNumber = 1;

    private T params;
}
