package com.xinghaol.net.tennis.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

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
