package com.pandora.domain.system.menu.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.domain.system.menu.command.UpdateMenuCommand;
import com.pandora.dao.system.entity.SysMenuEntity;
import com.pandora.service.system.ISysMenuService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MenuModel extends SysMenuEntity {

    public MenuModel(SysMenuEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public void loadUpdateCommand(UpdateMenuCommand command) {
        if (command != null) {
            MenuModelFactory.loadFromAddCommand(command, this);
        }
    }


    public void checkMenuNameUnique(ISysMenuService menuService) {
        if (menuService.isMenuNameDuplicated(getMenuName(), getMenuId(), getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkExternalLink() {
        if (getIsExternal() && !HttpUtil.isHttp(getPath()) && !HttpUtil.isHttps(getPath())) {
            throw new ApiException(ErrorCode.Business.MENU_EXTERNAL_LINK_MUST_BE_HTTP);
        }
    }


    public void checkParentIdConflict() {
        if (getMenuId().equals(getParentId())) {
            throw new ApiException(ErrorCode.Business.MENU_PARENT_ID_NOT_ALLOW_SELF);
        }
    }


    public void checkHasChildMenus(ISysMenuService menuService) {
        if (menuService.hasChildrenMenu(getMenuId())) {
            throw new ApiException(ErrorCode.Business.MENU_EXIST_CHILD_MENU_NOT_ALLOW_DELETE);
        }
    }


    public void checkMenuAlreadyAssignToRole(ISysMenuService menuService) {
        if (menuService.isMenuAssignToRoles(getMenuId())) {
            throw new ApiException(ErrorCode.Business.MENU_ALREADY_ASSIGN_TO_ROLE_NOT_ALLOW_DELETE);
        }
    }


}
