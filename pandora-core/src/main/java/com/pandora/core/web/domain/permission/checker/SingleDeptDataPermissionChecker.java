package com.pandora.core.web.domain.permission.checker;

import com.pandora.core.web.domain.login.LoginUser;
import com.pandora.core.web.domain.permission.DataCondition;
import com.pandora.core.web.domain.permission.DataPermissionChecker;
import com.pandora.service.system.ISysDeptService;
import java.util.Objects;

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
public class SingleDeptDataPermissionChecker extends DataPermissionChecker {

    private ISysDeptService deptService;

    @Override
    public boolean check(LoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getDeptId() == null || condition.getTargetDeptId() == null) {
            return false;
        }

        Long currentDeptId = loginUser.getDeptId();
        Long targetDeptId = condition.getTargetDeptId();

        return Objects.equals(currentDeptId, targetDeptId);
    }


}
