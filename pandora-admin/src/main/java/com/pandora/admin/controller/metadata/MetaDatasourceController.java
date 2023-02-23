package com.pandora.admin.controller.metadata;

import com.pandora.common.core.dto.ResponseDTO;
import com.pandora.common.core.page.PageDTO;
import com.pandora.dao.system.enums.dictionary.BusinessTypeEnum;
import com.pandora.domain.metadata.MetaDatasourceService;
import com.pandora.domain.metadata.command.AddMetaDatasourceCommand;
import com.pandora.domain.metadata.command.UpdateMetaDatasourceCommand;
import com.pandora.domain.metadata.dto.MetaDatasourceDTO;
import com.pandora.domain.metadata.query.DatasourceQuery;
import com.pandora.domain.system.dept.dto.DeptDTO;
import com.pandora.infrastructure.annotations.AccessLog;
import com.pandora.infrastructure.security.AuthenticationUtils;
import com.pandora.infrastructure.web.domain.login.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/metadata/datasource")
public class MetaDatasourceController {

    @Autowired
    private MetaDatasourceService metaDatasourceService;

    @PreAuthorize("@permission.has('metadata:datasource:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(DatasourceQuery query) {
        PageDTO page = metaDatasourceService.getDatasourceList(query);
        return ResponseDTO.ok(page);
    }

    @PreAuthorize("@permission.has('metadata:datasource:add')")
    @AccessLog(title = "数据源管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<?> add(@RequestBody @Validated AddMetaDatasourceCommand addCommand) {
        metaDatasourceService.addDatasource(addCommand);
        return ResponseDTO.ok();
    }

    @PreAuthorize("@permission.has('metadata:datasource:edit')")
    @AccessLog(title = "数据源管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<?> edit(@Validated @RequestBody UpdateMetaDatasourceCommand updateCommand) {
        LoginUser loginUser = AuthenticationUtils.getLoginUser();
        metaDatasourceService.updateDatasource(updateCommand, loginUser);
        return ResponseDTO.ok();
    }

    @PreAuthorize("@permission.has('metadata:datasource:remove')")
    @AccessLog(title = "数据源管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{datasourceId}")
    public ResponseDTO<?> remove(@PathVariable @NotNull Long datasourceId) {
        metaDatasourceService.removeDatasource(datasourceId);
        return ResponseDTO.ok();
    }

    @PreAuthorize("@permission.has('metadata:datasource:query')")
    @GetMapping(value = "/{datasourceId}")
    public ResponseDTO<MetaDatasourceDTO> getInfo(@PathVariable Long datasourceId) {
        MetaDatasourceDTO dto = metaDatasourceService.getDatasourceInfo(datasourceId);
        return ResponseDTO.ok(dto);
    }
}
