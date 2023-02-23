package com.pandora.domain.metadata.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pandora.dao.metadata.entity.MetaDatasourceEntity;
import com.pandora.dao.system.query.AbstractPageQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatasourceQuery extends AbstractPageQuery {
    private String type;
    private String datasourceName;
    private String ip;

    @Override
    public QueryWrapper toQueryWrapper() {
        QueryWrapper<MetaDatasourceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(ObjectUtils.isNotEmpty(type), "type", type);
        wrapper.like(ObjectUtils.isNotEmpty(datasourceName), "datasource_name", datasourceName);
        wrapper.like(ObjectUtils.isNotEmpty(ip), "ip", ip);
        return wrapper;
    }
}
