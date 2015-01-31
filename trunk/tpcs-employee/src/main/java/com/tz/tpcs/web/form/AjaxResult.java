package com.tz.tpcs.web.form;

/**
 * Ajax 处理结果封装类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/29 12:49
 */
public class AjaxResult {

    private boolean success; //操作是否成功
    private Object obj; //结果对象

    /** 空参构造 */
    public AjaxResult() {
    }

    /** 有参构造 */
    public AjaxResult(boolean success, Object obj) {
        this.success = success;
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
