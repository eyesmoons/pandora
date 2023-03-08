package com.pandora.core.web.domain.permission.checker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.pandora.core.web.domain.login.LoginUser;
import com.pandora.core.web.domain.permission.DataCondition;
import com.pandora.service.system.ISysDeptService;
import org.junit.jupiter.api.Test;

class SingleDeptDataPermissionCheckerTest {

    private ISysDeptService deptService = mock(ISysDeptService.class);

    @Test
    void testCheckWhenParameterNull() {
        SingleDeptDataPermissionChecker checker = new SingleDeptDataPermissionChecker(deptService);

        boolean check1 = checker.check(null, null);
        boolean check2 = checker.check(new LoginUser(), null);
        boolean check3 = checker.check(null, new DataCondition());
        boolean check4 = checker.check(new LoginUser(), new DataCondition());

        assertFalse(check1);
        assertFalse(check2);
        assertFalse(check3);
        assertFalse(check4);
    }

    @Test
    void testCheckWhenSameDeptId() {
        SingleDeptDataPermissionChecker checker = new SingleDeptDataPermissionChecker(deptService);
        LoginUser loginUser = new LoginUser();
        loginUser.setDeptId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetDeptId(1L);

        boolean check = checker.check(loginUser, dataCondition);

        assertTrue(check);
    }


    @Test
    void testCheckWhenDifferentDeptId() {
        SingleDeptDataPermissionChecker checker = new SingleDeptDataPermissionChecker(deptService);
        LoginUser loginUser = new LoginUser();
        loginUser.setDeptId(1L);
        DataCondition dataCondition = new DataCondition();
        dataCondition.setTargetUserId(2L);

        boolean check = checker.check(loginUser, dataCondition);

        assertFalse(check);
    }





}
