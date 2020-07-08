package tujia;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author: lixinghao
 * @date: 2020/7/6 3:08 下午
 * @Description:
 */
@Data
@Accessors(chain = true)
public class CardAuthRequestParam {
    private String version = "20140808";
    private String busiTypeId = "mskz";
    private String merchantCode = "mskzJK002";
    private String sign_type = "MD5";
    private String charset = "UTF-8";
    /**
     * 签名字段,必填
     */
    private String sign = "094E8DC8CAD1F8CFD4B654D37EF7882E";
    /**
     * 银行卡卡号，可以是信用卡，也可以是借记卡
     */
    private String cardNo;
    /**
     * 非必填，银行卡对应的预留手机号
     */
    private String mobile;
    /**
     * 执卡人姓名 (持卡人，手机号，证件号不能同时为空)，非必填
     */
    private String cardHolder;
    /**
     * 证件类型 IDENTITYCARD:身份证 PASSPORT:护照 OFFICERCERT:军官证
     */
    private String identityType;
    /**
     * 证件号码。证件号码不为空时，证件类型必传. (证件类型 证件号要么都传 要么都不传)
     */
    private String identityCode;
    /**
     * 有效日期，信用卡号有效日期 yyMM 。
     */
    private Date validate;
    /**
     * cvv值。非必传
     */
    private String cvv;
}
