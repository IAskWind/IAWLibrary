package com.iaskwind.iawlibrary.update;

/**
 * Created by winston on 16/11/29.
 * 自动更新的时候用到的实体
 */
public class IAW_UpdateInfoModel {
    /**
     * update : true
     * versionName : 1.0.4
     * path : http://www.baidu.com
     * updateLog : 测试内容
     * versionCode : 104
     * size : 9658252323
     * patch_md5 :
     * signature :
     */

    private boolean update;
    private String versionName;
    private String path;
    private String updateLog;
    private int versionCode;
    private long size;
    private String patch_md5;
    private String signature;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getPatch_md5() {
        return patch_md5;
    }

    public void setPatch_md5(String patch_md5) {
        this.patch_md5 = patch_md5;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
