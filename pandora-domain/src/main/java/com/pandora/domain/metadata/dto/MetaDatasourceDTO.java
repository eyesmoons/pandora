package com.pandora.domain.metadata.dto;

import com.pandora.dao.metadata.entity.MetaDatasourceEntity;
import com.pandora.dao.system.entity.SysUserEntity;
import com.pandora.dao.system.enums.dictionary.StatusEnum;
import com.pandora.dao.system.enums.interfaces.BasicEnumUtil;
import com.pandora.core.cache.CacheCenter;
import lombok.Data;

import java.util.Date;

@Data
public class MetaDatasourceDTO {
    private Long datasourceId;
    private String datasourceName;
    private String type;
    private String remark;
    private String ip;
    private Integer port;
    private String status;
    private String creatorName;
    private Date createTime;

    public MetaDatasourceDTO(MetaDatasourceEntity entity) {
        this.datasourceId = entity.getDatasourceId();
        this.datasourceName = entity.getDatasourceName();
        this.type = entity.getType();
        this.remark = entity.getRemark();
        this.ip = entity.getIp();
        this.port = entity.getPort();
        this.status = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());
        this.createTime = entity.getCreateTime();
        SysUserEntity creator = CacheCenter.userCache.getObjectById(entity.getCreatorId());
        if (creator != null) {
            this.creatorName = creator.getUsername();
        }
    }
}
