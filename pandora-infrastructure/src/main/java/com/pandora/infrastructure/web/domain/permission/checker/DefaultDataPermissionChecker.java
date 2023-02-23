package com.pandora.infrastructure.web.domain.permission.checker;

import com.pandora.infrastructure.web.domain.login.LoginUser;
import com.pandora.infrastructure.web.domain.permission.DataCondition;
import com.pandora.infrastructure.web.domain.permission.DataPermissionChecker;
import com.pandora.service.system.ISysDeptService;
import lombok.Data;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
public class DefaultDataPermissionChecker extends DataPermissionChecker {

    private ISysDeptService deptService;

    @Override
    public boolean check(LoginUser loginUser, DataCondition condition) {
        return false;
    }

}
