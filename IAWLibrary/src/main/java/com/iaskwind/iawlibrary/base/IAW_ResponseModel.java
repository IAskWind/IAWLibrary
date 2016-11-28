package com.iaskwind.iawlibrary.base;

/**
 * Created by winston on 16/11/25.
 */
public class IAW_ResponseModel<T> {

    private boolean success; //请求是否成功标志
    private String accessToken;//返回的token
    private String msg;//请求返回的消息
    private boolean tokenInvalid;
    private int page;
    private T data;  //服务端如果用map record 而不是当前的实体类的话，key必须是data 否则移动不能转换成实体


    public boolean isTokenInvalid() {
        return tokenInvalid;
    }

    public void setTokenInvalid(boolean tokenInvalid) {
        this.tokenInvalid = tokenInvalid;
    }


    public boolean isSuccess() {
        return success;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
