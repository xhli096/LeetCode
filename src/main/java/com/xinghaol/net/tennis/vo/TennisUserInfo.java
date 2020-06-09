package com.xinghaol.net.tennis.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: lixinghao
 * @date: 2020/6/5 5:28 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class TennisUserInfo implements Serializable {
    private Long id;
    private String loginname;
    private String password;
    private String usertypecode;
    private String openid;
    private String nickname;
    private String headicon;
    private String mobile;
}
