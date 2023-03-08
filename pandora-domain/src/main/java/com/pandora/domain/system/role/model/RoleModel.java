package com.pandora.domain.system.role.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.common.exception.error.ErrorCode.Business;
import com.pandora.domain.system.role.command.UpdateRoleCommand;
import com.pandora.core.web.domain.login.LoginUser;
import com.pandora.dao.system.entity.SysRoleEntity;
import com.pandora.dao.system.entity.SysRoleMenuEntity;
import com.pandora.dao.system.enums.dictionary.StatusEnum;
import com.pandora.service.system.ISysRoleMenuService;
import com.pandora.service.system.ISysRoleService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleModel extends SysRoleEntity {

    public RoleModel(SysRoleEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity,this);
        }
    }

    public void loadFromUpdateCommand(UpdateRoleCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "roleId");
        }
    }

    private List<Long> menuIds;

    private List<Long> deptIds;

    public void checkRoleNameUnique(ISysRoleService roleService) {
        if (roleService.isRoleNameDuplicated(getRoleId(), getRoleName())) {
            throw new ApiException(ErrorCode.Business.ROLE_NAME_IS_NOT_UNIQUE, getRoleName());
        }
    }

    public void checkRoleCanBeDelete(ISysRoleService roleService) {
        if (roleService.isAssignedToUsers(getRoleId())) {
            throw new ApiException(Business.ROLE_ALREADY_ASSIGN_TO_USER, getRoleName());
        }
    }

    public void checkRoleKeyUnique(ISysRoleService roleService) {
        if (roleService.isRoleKeyDuplicated(getRoleId(), getRoleKey())) {
            throw new ApiException(ErrorCode.Business.ROLE_KEY_IS_NOT_UNIQUE, getRoleKey());
        }
    }

    public void checkRoleAvailable() {
        if (StatusEnum.DISABLE.getValue().equals(getStatus())) {
            throw new ApiException(Business.ROLE_IS_NOT_AVAILABLE, getRoleName());
        }
    }

    /**
     *   用户可能在请求参数中，传输比自身更大的权限，导致越权
     */
    public void checkAssignedPermissionsExceedLoginUser(LoginUser loginUser) {
    }

    public void generateDeptIdSet() {
        if (deptIds == null) {
            setDeptIdSet("");
            return;
        }

        if (deptIds.size() > new HashSet<>(deptIds).size()) {
            throw new ApiException(ErrorCode.Business.ROLE_DATA_SCOPE_DUPLICATED_DEPT);
        }

        String deptIdSet = StrUtil.join(",", deptIds);
        setDeptIdSet(deptIdSet);
    }



    public void insert(ISysRoleMenuService roleMenuService) {
        this.insert();
        saveMenus(roleMenuService);
    }

    public void updateById(ISysRoleMenuService roleMenuService) {
        this.updateById();
        // 清空之前的角色菜单关联
        cleanOldMenus(roleMenuService);
        saveMenus(roleMenuService);
    }

    public void deleteById(ISysRoleMenuService roleMenuService) {
        this.deleteById();
        // 清空之前的角色菜单关联
        cleanOldMenus(roleMenuService);
    }


    private void cleanOldMenus(ISysRoleMenuService roleMenuService) {
        roleMenuService.getBaseMapper().deleteByMap(Collections.singletonMap("role_id", getRoleId()));
    }

    private void saveMenus(ISysRoleMenuService roleMenuService) {
        List<SysRoleMenuEntity> list = new ArrayList<>();
        if (getMenuIds() != null) {
            for (Long menuId : getMenuIds()) {
                SysRoleMenuEntity rm = new SysRoleMenuEntity();
                rm.setRoleId(getRoleId());
                rm.setMenuId(menuId);
                list.add(rm);
            }
            roleMenuService.saveBatch(list);
        }
    }

}
