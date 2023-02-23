package com.pandora.admin.controller.monitor;

import com.pandora.common.core.base.BaseController;
import com.pandora.common.core.dto.ResponseDTO;
import com.pandora.common.core.page.PageDTO;
import com.pandora.domain.system.monitor.MonitorApplicationService;
import com.pandora.domain.system.monitor.dto.RedisCacheInfoDTO;
import com.pandora.infrastructure.annotations.AccessLog;
import com.pandora.infrastructure.cache.redis.RedisCacheService;
import com.pandora.domain.system.monitor.dto.OnlineUser;
import com.pandora.domain.system.monitor.dto.ServerInfo;
import com.pandora.dao.system.enums.dictionary.BusinessTypeEnum;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 缓存监控
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    @Autowired
    private MonitorApplicationService monitorApplicationService;

    @Autowired
    private RedisCacheService redisCacheService;

    @PreAuthorize("@permission.has('monitor:cache:list')")
    @GetMapping("/cacheInfo")
    public ResponseDTO<RedisCacheInfoDTO> getRedisCacheInfo() {
        RedisCacheInfoDTO redisCacheInfo = monitorApplicationService.getRedisCacheInfo();
        return ResponseDTO.ok(redisCacheInfo);
    }


    @PreAuthorize("@permission.has('monitor:server:list')")
    @GetMapping("/serverInfo")
    public ResponseDTO<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = monitorApplicationService.getServerInfo();
        return ResponseDTO.ok(serverInfo);
    }

    /**
     * 获取在线用户列表
     * @param ipaddr
     * @param userName
     * @return
     */
    @PreAuthorize("@permission.has('monitor:online:list')")
    @GetMapping("/onlineUser/list")
    public ResponseDTO<PageDTO> list(String ipaddr, String userName) {
        List<OnlineUser> onlineUserList = monitorApplicationService.getOnlineUserList(userName, ipaddr);
        return ResponseDTO.ok(new PageDTO(onlineUserList));
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@permission.has('monitor:online:forceLogout')")
    @AccessLog(title = "在线用户", businessType = BusinessTypeEnum.FORCE_LOGOUT)
    @DeleteMapping("/onlineUser/{tokenId}")
    public ResponseDTO<Object> forceLogout(@PathVariable String tokenId) {
        redisCacheService.loginUserCache.delete(tokenId);
        return ResponseDTO.ok();
    }


}
