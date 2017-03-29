package com.example.dto;

/**
 * Created by cjh on 2017. 3. 21..
 */
public class Paging {

    private Integer offset = 0; //시작 num

    private Integer limit = 1;  //row 수

    //1 page 부터 시작.
    public Paging(Integer pageNum) {
        if(pageNum > 0) {
            this.offset = (pageNum - 1) * 3;
            this.limit = 3;
        }
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }
}
