package com.pandora.service.metadata;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pandora.dao.metadata.entity.MetaDatasourceEntity;

public interface IMetadataSourceService extends IService<MetaDatasourceEntity> {
    boolean isDatasourceNameDuplicated(Long datasourceId, String datasourceName);
}
