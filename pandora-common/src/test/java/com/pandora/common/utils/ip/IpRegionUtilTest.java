package com.pandora.common.utils.ip;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IpRegionUtilTest {

    @Test
    void testGetIpRegion() {
        IpRegion ipRegion = IpRegionUtil.getIpRegion("110.81.189.80");

        Assertions.assertEquals("中国", ipRegion.getCountry());
        Assertions.assertEquals("福建省", ipRegion.getProvince());
        Assertions.assertEquals("泉州市", ipRegion.getCity());
    }

    @Test
    public void testGetIpRegionWithIpv6() {
        IpRegion ipRegion = IpRegionUtil.getIpRegion("2001:0DB8:0000:0023:0008:0800:200C:417A");

        Assertions.assertNotNull(ipRegion);
        Assertions.assertNull(ipRegion.getCountry());
        Assertions.assertEquals("未知 未知", ipRegion.briefLocation());
    }

    @Test
    public void testGetIpRegionWithEmpty() {
        IpRegion ipRegion = IpRegionUtil.getIpRegion("");

        Assertions.assertNotNull(ipRegion);
        Assertions.assertNull(ipRegion.getCountry());
        Assertions.assertEquals("未知 未知", ipRegion.briefLocation());
    }

    @Test
    public void testGetIpRegionWithNull() {
        IpRegion ipRegion = IpRegionUtil.getIpRegion(null);

        Assertions.assertNotNull(ipRegion);
        Assertions.assertNull(ipRegion.getCountry());
        Assertions.assertEquals("未知 未知", ipRegion.briefLocation());
    }

    @Test
    public void testGetIpRegionWithWrongIpString() {
        IpRegion ipRegion = IpRegionUtil.getIpRegion("xsdfwefsfsd");

        Assertions.assertNotNull(ipRegion);
        Assertions.assertNull(ipRegion.getCountry());
        Assertions.assertEquals("未知 未知", ipRegion.briefLocation());
    }

    @Test
    void getBriefLocationByIp() {
        String briefLocationByIp = IpRegionUtil.getBriefLocationByIp("110.81.189.80");

        Assertions.assertEquals("福建省 泉州市", briefLocationByIp);
    }
}
