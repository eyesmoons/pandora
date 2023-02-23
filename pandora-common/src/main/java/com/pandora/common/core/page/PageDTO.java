package com.pandora.common.core.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import lombok.Data;

/**
 * 分页模型类
 * @author valarchie
 */
@Data
public class PageDTO {
    /**
     * 总记录数
     */
    private Long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    public PageDTO(List<?> list) {
        this.rows = list;
        this.total = (long) list.size();
    }

    @SuppressWarnings("rawtypes")
    public PageDTO(Page page) {
        this.rows = page.getRecords();
        this.total = page.getTotal();
    }

    public PageDTO(List<?> list, Long count) {
        this.rows = list;
        this.total = count;
    }

}
