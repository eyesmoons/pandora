package com.pandora.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.domain.system.role.command.AddRoleCommand;
import com.pandora.dao.system.entity.SysRoleEntity;
import com.pandora.service.system.ISysRoleMenuService;
import com.pandora.service.system.ISysRoleService;

/**
 * 角色模型工厂
 */
public class RoleModelFactory {

    public static RoleModel loadFromDb(Long roleId, ISysRoleService roleService, ISysRoleMenuService roleMenuService) {

        SysRoleEntity byId = roleService.getById(roleId);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, roleId, "角色");
        }

        return new RoleModel(byId);
    }

    public static RoleModel loadFromAddCommand(AddRoleCommand addCommand, RoleModel model) {
        if (addCommand != null && model != null) {
            BeanUtil.copyProperties(addCommand, model);
        }
        return model;
    }


}
