package com.pandora.domain.metadata.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.dao.metadata.entity.MetaDatasourceEntity;
import com.pandora.domain.metadata.command.UpdateMetaDatasourceCommand;
import com.pandora.service.metadata.IMetadataSourceService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MetaDatasourceModel extends MetaDatasourceEntity {

    public MetaDatasourceModel(MetaDatasourceEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity,this);
        }
    }

    public void checkDatasourceNameUnique(IMetadataSourceService metadataSourceService) {
        if (metadataSourceService.isDatasourceNameDuplicated(getDatasourceId(), getDatasourceName())) {
            throw new ApiException(ErrorCode.Business.DATASOURCE_NAME_IS_NOT_UNIQUE, getDatasourceName());
        }
    }

    public void loadUpdateCommand(UpdateMetaDatasourceCommand updateCommand) {
        MetaDatasourceModelFactory.loadFromAddCommand(updateCommand, this);
        setStatus(Convert.toInt(updateCommand.getStatus(), 1));
    }
}
