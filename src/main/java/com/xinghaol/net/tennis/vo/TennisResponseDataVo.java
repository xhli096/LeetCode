package com.xinghaol.net.tennis.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/6/5 5:31 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class TennisResponseDataVo implements Serializable {
    private String userrole;
    private String cardType;
    private TennisUserInfo user;
}
