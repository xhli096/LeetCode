package com.xinghaol.net.tennis.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/6/5 5:33 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class TennisResponseVo implements Serializable {
    private TennisResponseDataVo datas;
    private String respCode;
    private String respMsg;
}
