package com.xinghaol.net.tennis.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/6/5 11:58 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class AddOrderDataVo implements Serializable {
    private String orderNo;
}
