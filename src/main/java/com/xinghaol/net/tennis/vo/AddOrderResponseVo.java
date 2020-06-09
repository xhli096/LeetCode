package com.xinghaol.net.tennis.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/6/5 11:57 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class AddOrderResponseVo implements Serializable {
    private AddOrderDataVo datas;
    private String respCode;
    private String respMsg;
}
