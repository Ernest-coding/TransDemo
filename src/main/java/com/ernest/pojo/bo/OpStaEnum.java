package com.ernest.pojo.bo;

/**
 * 各种操作的结果返回情况
 */
public enum OpStaEnum {
    /**
     * 返回情况
     * 200-299 各种成功
     * 300-399 各种其他失败
     * 400-499 各种失败
     */
    SUCCESS(200, "success"),
    MAN(201, "man"),
    USER(202, "user"),
    SIGN(203, "sign"),
    FAILED(400, "未知错误，请联系管理员"),
    HASEXIST(300, "账户已存在"),
    ILLEGAL(301, "用户违规被禁用，请联系管理员"),
    NOTEXIST(302, "账户不存在"),
    WRONGPWD(303, "密码错误"),
    WRONGPHONE(304, "手机号错误"),
    NOTKNOW(500, "操作数据库未知错误");

    private int index;
    private String status;

    OpStaEnum(int index, String status) {
        this.index = index;
        this.status = status;
    }

    /**
     * 通过状态值获取状态描述
     *
     * @param ind 状态值
     * @return
     */
    public static String getStatus(Integer ind) {
        for (OpStaEnum value : OpStaEnum.values()) {
            if (value.getIndex() == ind) {
                return value.getStatus();
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
