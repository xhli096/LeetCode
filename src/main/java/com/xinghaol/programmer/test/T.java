package com.xinghaol.programmer.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinghao
 * @date: 2020/8/16 10:12 下午
 * @Description:
 */
public class T {
    public static void main(String[] args) {
        List<HouseLabelMarkQueue> list = new ArrayList<>();

        HouseLabelMarkQueue houseLabelMarkQueue2 = new HouseLabelMarkQueue();
        houseLabelMarkQueue2.setHouseGuid("ee29f514-d7cf-40e0-9cab-22fb778a31ef");
        houseLabelMarkQueue2.setHouseId(1170290L);
        houseLabelMarkQueue2.setHouseName("联调成功的第一个房屋");
        houseLabelMarkQueue2.setHotelName("门店联调测试20170901-02");
        houseLabelMarkQueue2.setChannel(1);
        houseLabelMarkQueue2.setCityName("北京");
        houseLabelMarkQueue2.setCompanyId(3L);
        houseLabelMarkQueue2.setCityId(48L);
        houseLabelMarkQueue2.setProvinceId(423L);
        houseLabelMarkQueue2.setAuditStatus(2);
        list.add(houseLabelMarkQueue2);

        HouseLabelMarkQueue houseLabelMarkQueue3 = new HouseLabelMarkQueue();
        houseLabelMarkQueue3.setHouseGuid("49629d22-57d8-4585-b991-a89893da3fc2");
        houseLabelMarkQueue3.setHouseId(1170187L);
        houseLabelMarkQueue3.setHouseName("夏日海岸豪华湖景大床房");
        houseLabelMarkQueue3.setHotelName("三亚夏日海岸度假公寓");
        houseLabelMarkQueue3.setChannel(1);
        houseLabelMarkQueue3.setCityName("三亚");
        houseLabelMarkQueue3.setCompanyId(3L);
        houseLabelMarkQueue3.setCityId(1L);
        houseLabelMarkQueue3.setProvinceId(415L);
        houseLabelMarkQueue3.setAuditStatus(2);
        list.add(houseLabelMarkQueue3);

        System.out.println(JSON.toJSONString(list));
    }
}
