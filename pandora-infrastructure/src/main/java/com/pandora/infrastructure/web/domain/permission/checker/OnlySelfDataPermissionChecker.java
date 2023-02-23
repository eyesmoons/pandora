package com.pandora.infrastructure.web.domain.permission.checker;

import com.pandora.infrastructure.web.domain.login.LoginUser;
import com.pandora.infrastructure.web.domain.permission.DataCondition;
import com.pandora.infrastructure.web.domain.permission.DataPermissionChecker;
import com.pandora.service.system.ISysDeptService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlySelfDataPermissionChecker extends DataPermissionChecker {

    private ISysDeptService deptService;

    @Override
    public boolean check(LoginUser loginUser, DataCondition condition) {
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getUserId() == null || condition.getTargetUserId() == null) {
            return false;
        }

        Long currentUserId = loginUser.getUserId();
        Long targetUserId = condition.getTargetUserId();
        return Objects.equals(currentUserId, targetUserId);
    }

}
