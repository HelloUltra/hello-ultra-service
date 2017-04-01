package com.example.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joonghyun on 2017. 3. 19..
 */
public class ConversationInfo {

    private String function;    //호출 메소드명

    private Map<String, String> param = new HashMap<>();  //메소드 필요 파람

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
