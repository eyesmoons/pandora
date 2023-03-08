package com.pandora.admin.controller.system;

import com.pandora.common.core.base.BaseController;
import com.pandora.common.core.dto.ResponseDTO;
import com.pandora.common.core.page.PageDTO;
import com.pandora.domain.system.config.dto.ConfigDTO;
import com.pandora.domain.system.config.ConfigApplicationService;
import com.pandora.domain.system.config.query.ConfigQuery;
import com.pandora.domain.system.config.command.ConfigUpdateCommand;
import com.pandora.core.annotations.AccessLog;
import com.pandora.core.cache.guava.GuavaCacheService;
import com.pandora.core.cache.map.MapCache;
import com.pandora.dao.system.enums.dictionary.BusinessTypeEnum;
import com.pandora.dao.system.result.DictionaryData;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 参数配置 信息操作处理
 * @author valarchie
 */
@RestController
@RequestMapping("/system/config")
@Validated
public class SysConfigController extends BaseController {

    @Autowired
    private ConfigApplicationService configApplicationService;

    @Autowired
    private GuavaCacheService guavaCacheService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@permission.has('system:config:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO> list(ConfigQuery query) {
        PageDTO page = configApplicationService.getConfigList(query);
        return ResponseDTO.ok(page);
    }

    /**
     * 根据字典类型查询字典数据信息
     * 换成用Enum
     */
    @GetMapping(value = "/dict/{dictType}")
    public ResponseDTO<List<DictionaryData>> dictType(@PathVariable String dictType) {
        List<DictionaryData> dictionaryData = MapCache.dictionaryCache().get(dictType);
        return ResponseDTO.ok(dictionaryData);
    }


    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:config:query')")
    @GetMapping(value = "/{configId}")
    public ResponseDTO<ConfigDTO> getInfo(@NotNull @Positive @PathVariable Long configId) {
        ConfigDTO config = configApplicationService.getConfigInfo(configId);
        return ResponseDTO.ok(config);
    }


    /**
     * 修改参数配置
     */
    @PreAuthorize("@permission.has('system:config:edit')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<?> edit(@RequestBody ConfigUpdateCommand config) {
        configApplicationService.updateConfig(config);
        return ResponseDTO.ok();
    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("@permission.has('system:config:remove')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.CLEAN)
    @DeleteMapping("/refreshCache")
    public ResponseDTO<?> refreshCache() {
        guavaCacheService.configCache.invalidateAll();
        return ResponseDTO.ok();
    }
}
