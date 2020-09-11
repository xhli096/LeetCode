package com.xinghaol.programmer.test;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: lixinghao
 * @date: 2020/8/11 3:21 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class HouseLabelMarkQueue {
    private Integer id;
    private String houseGuid;
    private Long houseId;
    private String houseNumber;
    private String houseName;
    private Integer channel;
    private Long cityId;
    private Long provinceId;
    /**
     * 门店名称
     */
    private String hotelName;
    /**
     * 城市名称
     */
    private String cityName;
    private Integer auditStatus;
    private Long companyId;
    private Long auditUserId;
    private Date createTime;
    private Date lastUpdateTime;
    private Date startAuditTime;
}
