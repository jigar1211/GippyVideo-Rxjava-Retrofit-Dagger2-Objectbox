package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("msg")
@Expose
private String msg;
@SerializedName("response_id")
@Expose
private String responseId;

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public String getResponseId() {
return responseId;
}

public void setResponseId(String responseId) {
this.responseId = responseId;
}

}