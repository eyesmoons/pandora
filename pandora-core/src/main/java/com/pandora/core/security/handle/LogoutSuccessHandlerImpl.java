package com.pandora.core.security.handle;

import cn.hutool.json.JSONUtil;
import com.pandora.common.core.dto.ResponseDTO;
import com.pandora.common.utils.ServletHolderUtil;
import com.pandora.core.thread.AsyncTaskFactory;
import com.pandora.core.thread.ThreadPoolManager;
import com.pandora.core.web.domain.login.LoginUser;
import com.pandora.core.web.service.TokenService;
import com.pandora.dao.system.enums.LoginStatusEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 自定义退出处理类 返回成功
 * 在SecurityConfig类当中 定义了/logout 路径对应处理逻辑
 *
 * @author ruoyi
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser != null) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.deleteLoginUser(loginUser.getToken());
            // 记录用户退出日志
            ThreadPoolManager.execute(AsyncTaskFactory.loginInfoTask(
                userName, LoginStatusEnum.LOGOUT, LoginStatusEnum.LOGOUT.description()));
        }
        ServletHolderUtil.renderString(response, JSONUtil.toJsonStr(ResponseDTO.ok()));
    }
}
