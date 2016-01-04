package com.solland.paidao.entity.dto.param;

import com.solland.paidao.common.BaseModel;

import java.io.Serializable;

/**
 * Created by sunshibo on 2016/1/4.
 */
public class LoginParam extends BaseModel implements Serializable{


    private static final long serialVersionUID = 8146980880302786091L;

    /**
     * 登录账号
     */
    private String account ;

    /**
     * 密码
     */
    private String password ;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
