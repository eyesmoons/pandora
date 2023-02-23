package com.pandora.admin.controller.system;


import com.pandora.common.core.base.BaseController;
import com.pandora.common.core.dto.ResponseDTO;
import com.pandora.common.core.page.PageDTO;
import com.pandora.common.exception.error.ErrorCode;
import com.pandora.common.utils.poi.CustomExcelUtil;
import com.pandora.domain.common.command.BulkOperationCommand;
import com.pandora.domain.system.logininfo.dto.LoginInfoDTO;
import com.pandora.domain.system.logininfo.LoginInfoApplicationService;
import com.pandora.domain.system.logininfo.query.LoginInfoQuery;
import com.pandora.infrastructure.annotations.AccessLog;
import com.pandora.dao.system.enums.dictionary.BusinessTypeEnum;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统访问记录
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/loginInfo")
@Validated
public class SysLoginInfoController extends BaseController {

    @Autowired
    private LoginInfoApplicationService loginInfoApplicationService;

    @PreAuthorize("@permission.has('monitor:logininfor:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(LoginInfoQuery query) {
        PageDTO pageDTO = loginInfoApplicationService.getLoginInfoList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:logininfor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, LoginInfoQuery query) {
        PageDTO pageDTO = loginInfoApplicationService.getLoginInfoList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), LoginInfoDTO.class, response);
    }

    @PreAuthorize("@permission.has('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{infoIds}")
    public ResponseDTO<?> remove(@PathVariable @NotNull @NotEmpty List<Long> infoIds) {
        loginInfoApplicationService.deleteLoginInfo(new BulkOperationCommand<>(infoIds));
        return ResponseDTO.ok();
    }

    @PreAuthorize("@permission.has('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.CLEAN)
    @DeleteMapping("/clean")
    public ResponseDTO<?> clean() {
        return ResponseDTO.fail(ErrorCode.Business.UNSUPPORTED_OPERATION);
    }
}
