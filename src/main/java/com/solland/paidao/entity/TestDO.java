package com.solland.paidao.entity;

import java.io.Serializable;

/**
 * Created by admin on 2015/1/20.
 */
public class TestDO implements Serializable {
    private static final long serialVersionUID = 1L;
   private Integer id ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
