package com.pandora.domain.system.logininfo.dto;

import com.pandora.common.annotation.ExcelColumn;
import com.pandora.common.annotation.ExcelSheet;
import com.pandora.dao.system.entity.SysLoginInfoEntity;
import com.pandora.dao.system.enums.LoginStatusEnum;
import com.pandora.dao.system.enums.interfaces.BasicEnumUtil;
import java.util.Date;
import lombok.Data;

@Data
@ExcelSheet(name = "登录日志")
public class LoginInfoDTO {

    public LoginInfoDTO(SysLoginInfoEntity entity) {
        if (entity != null) {
            infoId = entity.getInfoId() + "";
            username = entity.getUsername();
            ipAddress = entity.getIpAddress();
            loginLocation = entity.getLoginLocation();
            operationSystem = entity.getOperationSystem();
            browser = entity.getBrowser();
            status = entity.getStatus() + "";
            statusStr = BasicEnumUtil.getDescriptionByValue(LoginStatusEnum.class, entity.getStatus());
            msg = entity.getMsg();
            loginTime = entity.getLoginTime();
        }
    }


    @ExcelColumn(name = "ID")
    private String infoId;

    @ExcelColumn(name = "用户名")
    private String username;

    @ExcelColumn(name = "ip地址")
    private String ipAddress;

    @ExcelColumn(name = "登录地点")
    private String loginLocation;

    @ExcelColumn(name = "操作系统")
    private String operationSystem;

    @ExcelColumn(name = "浏览器")
    private String browser;

    private String status;

    @ExcelColumn(name = "状态")
    private String statusStr;

    @ExcelColumn(name = "描述")
    private String msg;

    @ExcelColumn(name = "登录时间")
    private Date loginTime;

}
