package cn.itsource.util;

public class AjaxResult {
    private  Boolean success=true;
    private String message="操作成功";
    private Integer errorCode;
    private Object restObj;
    public AjaxResult() {
    }

    public AjaxResult(Boolean success, String message, Integer errorCode, Object restObj) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.restObj = restObj;
    }

    public Object getRestObj() {
        return restObj;
    }

    public AjaxResult setRestObj(Object restObj) {
        this.restObj = restObj;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public AjaxResult setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }
    //链式写法
    public AjaxResult setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public static AjaxResult getAjax(){
        return new AjaxResult();
    }
}
