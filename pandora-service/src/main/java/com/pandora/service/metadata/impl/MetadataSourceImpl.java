package com.pandora.service.metadata.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pandora.dao.metadata.entity.MetaDatasourceEntity;
import com.pandora.dao.metadata.mapper.MetaDatasourceMapper;
import com.pandora.service.metadata.IMetadataSourceService;
import org.springframework.stereotype.Service;

@Service
public class MetadataSourceImpl extends ServiceImpl<MetaDatasourceMapper, MetaDatasourceEntity> implements IMetadataSourceService {
    @Override
    public boolean isDatasourceNameDuplicated(Long datasourceId, String datasourceName) {
        QueryWrapper<MetaDatasourceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne(datasourceId != null, "datasource_id", datasourceId)
                .eq("datasource_name", datasourceName);
        return this.baseMapper.exists(queryWrapper);
    }
}
