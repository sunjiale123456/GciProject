package org.example.pojo;

public class ResultData {
    /**
     * 状态码 0 表示成功，1表示处理中，-1表示失败
     */

    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;

    public ResultData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    /**
     * 成功，传入数据
     *
     * @return
     */
    public static ResultData buildSuccess() {
        return new ResultData(0, null, null);
    }

    /**
     * 成功，传入数据
     *
     * @param data
     * @return
     */
    public static ResultData buildSuccess(Object data) {
        return new ResultData(0, data, null);
    }

    /**
     * 失败，传入描述信息
     *
     * @param msg
     * @return
     */
    public static ResultData buildError(String msg) {
        return new ResultData(-1, null, msg);
    }
}
