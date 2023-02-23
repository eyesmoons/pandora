package com.pandora.common.utils.ip;

import com.pandora.common.config.PandoraConfig;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OnlineIpRegionUtilTest {

    @BeforeEach
    public void enableOnlineAddressQuery(){
        PandoraConfig pandoraConfig = new PandoraConfig();
        pandoraConfig.setAddressEnabled(true);
    }

    @Test
    public void getIpRegionWithIpv6() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
            OnlineIpRegionUtil.getIpRegion("ABCD:EF01:2345:6789:ABCD:EF01:2345:6789")
        );
        Assert.assertNull(region);
    }

    @Test
    public void getIpRegionWithIpv4() {
        IpRegion ipRegion = OnlineIpRegionUtil.getIpRegion("120.42.247.130");

        Assert.assertEquals("福建省", ipRegion.getProvince());
        Assert.assertEquals("泉州市", ipRegion.getCity());
    }

    @Test
    public void getIpRegionWithEmpty() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
            OnlineIpRegionUtil.getIpRegion("")
        );

        Assert.assertNull(region);
    }


    @Test
    public void getIpRegionWithNull() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
            OnlineIpRegionUtil.getIpRegion(null)
        );

        Assert.assertNull(region);
    }

    @Test
    public void getIpRegionWithWrongIpString() {
        IpRegion region = Assertions.assertDoesNotThrow(() ->
            OnlineIpRegionUtil.getIpRegion("seffsdfsdf")
        );

        Assert.assertNull(region);
    }


}

