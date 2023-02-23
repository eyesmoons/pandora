package com.pandora.domain.system.menu.model;

import cn.hutool.core.bean.BeanUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.domain.system.menu.command.AddMenuCommand;
import com.pandora.dao.system.entity.SysMenuEntity;
import com.pandora.service.system.ISysMenuService;

/**
 * 菜单模型工厂
 * @author valarchie
 */
public class MenuModelFactory {

    public static MenuModel loadFromDb(Long menuId, ISysMenuService menuService) {
        SysMenuEntity byId = menuService.getById(menuId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.OBJECT_NOT_FOUND, menuId, "菜单");
        }
        return new MenuModel(byId);
    }

    public static MenuModel loadFromAddCommand(AddMenuCommand command, MenuModel model) {
        BeanUtil.copyProperties(command, model);
        return model;
    }

}
