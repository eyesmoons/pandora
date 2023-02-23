package com.pandora.domain.metadata.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AddMetaDatasourceCommand {

    @NotBlank(message = "数据源类型不能为空")
    private String type;

    @NotBlank(message = "数据源名称不能为空")
    @Size(max = 80, message = "数据源名称长度不能超过80个字符")
    private String datasourceName;

    @NotBlank(message = "数据源描述不能为空")
    @Size(max = 200, message = "数据源描述长度不能超过200个字符")
    private String remark;

    @NotBlank(message = "ip或主机名称不能为空")
    private String ip;

    @NotNull(message = "ip或主机名称不能为空")
    private Integer port;

    @NotNull(message = "数据源状态不能为空")
    private Integer status;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "默认数据库不能为空")
    private String defaultDbName;

    private String connectionParams;
}
