package cn.itsource.util;

public class AjaxResult {
    private  Boolean success;
    private String message;
    private Integer errorCode;
    private Object resultObj;
    public AjaxResult() {
    }

    public AjaxResult(Boolean success, String message, Integer errorCode, Object resultObj) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.resultObj = resultObj;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public AjaxResult setResultObj(Object resultObj) {
        this.resultObj = resultObj;
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
