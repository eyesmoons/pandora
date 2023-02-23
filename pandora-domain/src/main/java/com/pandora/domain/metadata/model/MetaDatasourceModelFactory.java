package com.pandora.domain.metadata.model;

import cn.hutool.core.bean.BeanUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.dao.metadata.entity.MetaDatasourceEntity;
import com.pandora.domain.metadata.command.AddMetaDatasourceCommand;
import com.pandora.service.metadata.IMetadataSourceService;

public class MetaDatasourceModelFactory {

    public static MetaDatasourceModel loadFromDb(Long datasourceId, IMetadataSourceService metadataSourceService) {
        MetaDatasourceEntity byId = metadataSourceService.getById(datasourceId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, datasourceId, "数据源");
        }
        return new MetaDatasourceModel(byId);
    }

    public static MetaDatasourceModel loadFromAddCommand(AddMetaDatasourceCommand addCommand, MetaDatasourceModel model) {
        if (addCommand != null && model != null) {
            BeanUtil.copyProperties(addCommand, model);
        }
        return model;
    }
}
