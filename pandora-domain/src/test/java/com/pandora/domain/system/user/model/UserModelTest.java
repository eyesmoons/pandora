package com.pandora.domain.system.user.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pandora.common.exception.ApiException;
import com.pandora.common.exception.error.ErrorCode.Business;
import com.pandora.domain.system.user.command.UpdateUserPasswordCommand;
import com.pandora.infrastructure.web.domain.login.LoginUser;
import com.pandora.infrastructure.security.AuthenticationUtils;
import com.pandora.service.system.ISysUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserModelTest {

    private final ISysUserService userService = mock(ISysUserService.class);

    private static final long USER_ID = 1L;
    private static final long ADMIN_USER_ID = 1L;

    @Test
    void testCheckUsernameIsUnique() {
        UserModel userWithSameName = new UserModel();
        userWithSameName.setUserId(USER_ID);
        userWithSameName.setUsername("user 1");
        UserModel userWithNewName = new UserModel();
        userWithNewName.setUserId(USER_ID);
        userWithNewName.setUsername("user 2");

        when(userService.isUserNameDuplicated(eq("user 1"))).thenReturn(true);
        when(userService.isUserNameDuplicated(eq("user 2"))).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class,
            () -> userWithSameName.checkUsernameIsUnique(userService));
        Assertions.assertEquals(Business.USER_NAME_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(() -> userWithNewName.checkUsernameIsUnique(userService));
    }

    @Test
    void testCheckPhoneNumberIsUnique() {
        UserModel userWithSameNumber = new UserModel();
        userWithSameNumber.setUserId(USER_ID);
        userWithSameNumber.setPhoneNumber("111111");
        UserModel userWithNewNumber = new UserModel();
        userWithNewNumber.setUserId(USER_ID);
        userWithNewNumber.setPhoneNumber("222222");

        when(userService.isPhoneDuplicated(eq("111111"), eq(USER_ID))).thenReturn(true);
        when(userService.isPhoneDuplicated(eq("222222"), eq(USER_ID))).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class,
            () -> userWithSameNumber.checkPhoneNumberIsUnique(userService));
        Assertions.assertEquals(Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(() -> userWithNewNumber.checkPhoneNumberIsUnique(userService));
    }

    @Test
    void testCheckEmailIsUnique() {
        UserModel userWithSameEmail = new UserModel();
        userWithSameEmail.setUserId(USER_ID);
        userWithSameEmail.setEmail("1@1.com");
        UserModel userWithNewNumber = new UserModel();
        userWithNewNumber.setUserId(USER_ID);
        userWithNewNumber.setEmail("2@2.com");

        when(userService.isEmailDuplicated(eq("1@1.com"), eq(USER_ID))).thenReturn(true);
        when(userService.isEmailDuplicated(eq("2@2.com"), eq(USER_ID))).thenReturn(false);

        ApiException exception = assertThrows(ApiException.class,
            () -> userWithSameEmail.checkEmailIsUnique(userService));
        Assertions.assertEquals(Business.USER_EMAIL_IS_NOT_UNIQUE, exception.getErrorCode());
        Assertions.assertDoesNotThrow(() -> userWithNewNumber.checkPhoneNumberIsUnique(userService));
    }

    @Test
    void testCheckCanBeDeleteWhenDeleteItself() {
        UserModel userModel = new UserModel();
        userModel.setUserId(USER_ID);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(USER_ID);

        ApiException exception = assertThrows(ApiException.class, () ->userModel.checkCanBeDelete(loginUser));

        Assertions.assertEquals(Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE, exception.getErrorCode());
    }

    @Test
    void testCheckCanBeDeleteWhenDeleteAdmin() {
        UserModel userModel = new UserModel();
        long adminId = 1L;
        userModel.setUserId(adminId);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(2L);

        ApiException exception = assertThrows(ApiException.class, () ->userModel.checkCanBeDelete(loginUser));

        Assertions.assertEquals(Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE, exception.getErrorCode());
    }


    @Test
    void testCheckCanBeDeleteWhenSuccessful() {
        UserModel userModel = new UserModel();
        userModel.setUserId(2L);
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(ADMIN_USER_ID);

        Assertions.assertDoesNotThrow(()-> userModel.checkCanBeDelete(loginUser));
    }

    @Test
    void testModifyPasswordWhenSuccessful() {
        UserModel userModel = new UserModel();
        userModel.setPassword("$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");
        UpdateUserPasswordCommand passwordCommand = new UpdateUserPasswordCommand();
        passwordCommand.setOldPassword("admin123");
        String newPassword = "admin456";
        passwordCommand.setNewPassword(newPassword);

        userModel.modifyPassword(passwordCommand);

        Assertions.assertTrue(AuthenticationUtils.matchesPassword(newPassword, userModel.getPassword()));
    }

    @Test
    void testModifyPasswordWhenPasswordWrong() {
        UserModel userModel = new UserModel();
        userModel.setPassword("$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");
        UpdateUserPasswordCommand passwordCommand = new UpdateUserPasswordCommand();
        passwordCommand.setOldPassword("admin999");
        String newPassword = "admin456";
        passwordCommand.setNewPassword(newPassword);

        ApiException exception = assertThrows(ApiException.class, () -> userModel.modifyPassword(passwordCommand));
        Assertions.assertEquals(Business.USER_PASSWORD_IS_NOT_CORRECT, exception.getErrorCode());
    }

    @Test
    void testModifyPasswordWhenOldNewPasswordSame() {
        UserModel userModel = new UserModel();
        userModel.setPassword("$2a$10$rb1wRoEIkLbIknREEN1LH.FGs4g0oOS5t6l5LQ793nRaFO.SPHDHy");
        UpdateUserPasswordCommand passwordCommand = new UpdateUserPasswordCommand();
        passwordCommand.setOldPassword("admin123");
        String newPassword = "admin123";
        passwordCommand.setNewPassword(newPassword);

        ApiException exception = assertThrows(ApiException.class, () -> userModel.modifyPassword(passwordCommand));
        Assertions.assertEquals(Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD, exception.getErrorCode());
    }

    @Test
    void testResetPassword() {
        UserModel userModel = new UserModel();
        userModel.resetPassword("admin456");

        Assertions.assertTrue(AuthenticationUtils.matchesPassword("admin456", userModel.getPassword()));
    }
}
