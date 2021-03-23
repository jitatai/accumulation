package com.jt.projects.results;

import lombok.Data;

@Data
public class PageQueryDTO extends BaseTO {
    private int page = 1;
    private int pageSize = 20;
    private Long total;
}
