package com.pandora.core.web.domain.permission.checker;

import com.pandora.core.web.domain.login.LoginUser;
import com.pandora.core.web.domain.permission.DataCondition;
import com.pandora.core.web.domain.permission.DataPermissionChecker;
import com.pandora.service.system.ISysDeptService;
import lombok.Data;

/**
 * 数据权限测试接口
 * @author valarchie
 */
@Data
public class AllDataPermissionChecker extends DataPermissionChecker {

    private ISysDeptService deptService;


    @Override
    public boolean check(LoginUser loginUser, DataCondition condition) {
        return true;
    }
}
