package com.pandora.domain.system.config.model;

import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.domain.system.config.command.ConfigUpdateCommand;
import com.pandora.dao.system.entity.SysConfigEntity;
import com.pandora.service.system.ISysConfigService;

/**
 * 配置模型工厂
 */
public class ConfigModelFactory {

    public static ConfigModel loadFromDb(Long configId, ISysConfigService configService) {
        SysConfigEntity byId = configService.getById(configId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, configId, "参数配置");
        }
        return new ConfigModel(byId);
    }

    public static ConfigModel loadFromUpdateCommand(ConfigUpdateCommand updateCommand, ISysConfigService configService) {
        ConfigModel configModel = loadFromDb(updateCommand.getConfigId(), configService);
        configModel.setConfigValue(updateCommand.getConfigValue());
        return configModel;
    }


}
