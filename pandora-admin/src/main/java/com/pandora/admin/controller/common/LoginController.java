package com.pandora.admin.controller.common;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.pandora.admin.request.LoginDTO;
import com.pandora.admin.response.UserPermissionDTO;
import com.pandora.common.config.PandoraConfig;
import com.pandora.common.constant.Constants.Token;
import com.pandora.common.core.dto.ResponseDTO;
import com.pandora.common.exception.error.ErrorCode.Business;
import com.pandora.domain.system.menu.MenuApplicationService;
import com.pandora.domain.system.menu.dto.RouterDTO;
import com.pandora.domain.system.user.command.AddUserCommand;
import com.pandora.domain.system.user.dto.UserDTO;
import com.pandora.infrastructure.cache.map.MapCache;
import com.pandora.infrastructure.web.domain.login.CaptchaDTO;
import com.pandora.infrastructure.web.domain.login.LoginUser;
import com.pandora.infrastructure.web.service.LoginService;
import com.pandora.infrastructure.security.AuthenticationUtils;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页
 *
 * @author valarchie
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    private final MenuApplicationService menuApplicationService;
    /**
     * 系统基础配置
     */
    private final PandoraConfig pandoraConfig;

    public LoginController(LoginService loginService,
        MenuApplicationService menuApplicationService, PandoraConfig pandoraConfig) {
        this.loginService = loginService;
        this.menuApplicationService = menuApplicationService;
        this.pandoraConfig = pandoraConfig;
    }

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StrUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。",
            pandoraConfig.getName(), pandoraConfig.getVersion());
    }

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ResponseDTO<CaptchaDTO> getCaptchaImg() {
        CaptchaDTO captchaImg = loginService.getCaptchaImg();
        return ResponseDTO.ok(captchaImg);
    }

    /**
     * 登录方法
     *
     * @param loginDTO 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResponseDTO<Map> login(@RequestBody LoginDTO loginDTO) {
        // 生成令牌
        String token = loginService.login(loginDTO.getUsername(), loginDTO.getPassword(), loginDTO.getCode(),
            loginDTO.getUuid());

        return ResponseDTO.ok(MapUtil.of(Token.TOKEN_FIELD, token));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getLoginUserInfo")
    public ResponseDTO<?> getLoginUserInfo() {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();

        UserPermissionDTO permissionDTO = new UserPermissionDTO();
        permissionDTO.setUser(new UserDTO(loginUser.getEntity()));
        permissionDTO.setRoleKey(loginUser.getRoleInfo().getRoleKey());
        permissionDTO.setPermissions(loginUser.getRoleInfo().getMenuPermissions());
        permissionDTO.setDictTypes(MapCache.dictionaryCache());
        return ResponseDTO.ok(permissionDTO);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public ResponseDTO<List<RouterDTO>> getRouters() {
        Long userId = AuthenticationUtils.getUserId();
        List<RouterDTO> routerTree = menuApplicationService.getRouterTree(userId);
        return ResponseDTO.ok(routerTree);
    }


    @PostMapping("/register")
    public ResponseDTO<?> register(@RequestBody AddUserCommand command) {
        return ResponseDTO.fail(Business.UNSUPPORTED_OPERATION);
    }

}
