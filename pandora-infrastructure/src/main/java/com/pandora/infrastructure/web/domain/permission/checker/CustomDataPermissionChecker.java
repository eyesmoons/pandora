package com.pandora.infrastructure.web.domain.permission.checker;

import cn.hutool.core.collection.CollUtil;
import com.pandora.infrastructure.web.domain.login.LoginUser;
import com.pandora.infrastructure.web.domain.permission.DataCondition;
import com.pandora.infrastructure.web.domain.permission.DataPermissionChecker;
import com.pandora.service.system.ISysDeptService;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomDataPermissionChecker extends DataPermissionChecker {

    private ISysDeptService deptService;


    @Override
    public boolean check(LoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getRoleInfo() == null) {
            return false;
        }

        Set<Long> deptIdSet = loginUser.getRoleInfo().getDeptIdSet();
        Long targetDeptId = condition.getTargetDeptId();

        return condition.getTargetDeptId() != null && CollUtil.safeContains(deptIdSet, targetDeptId);
    }
}
