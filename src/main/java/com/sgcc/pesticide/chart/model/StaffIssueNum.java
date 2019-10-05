package com.sgcc.pesticide.chart.model;

/**
 * @author jerrywang
 * @create 2017/7/30.
 */
public class StaffIssueNum {

    private String username;

    private long num;

    private String roleId;

    private boolean bbb;

    public boolean isBbb() {
        return bbb;
    }

    public void setBbb(boolean bbb) {
        this.bbb = bbb;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}
