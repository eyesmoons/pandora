package com.pandora.domain.system.post.query;

import cn.hutool.core.util.StrUtil;
import com.pandora.dao.system.entity.SysPostEntity;
import com.pandora.dao.system.query.AbstractPageQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostQuery extends AbstractPageQuery {

    private String postCode;
    private String postName;
    private Integer status;


    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<SysPostEntity> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq(status != null, "status", status)
            .eq(postCode != null, "post_code", postCode)
            .like(StrUtil.isNotEmpty(postName), "post_name", postName);

        addSortCondition(queryWrapper);

        return queryWrapper;
    }
}
