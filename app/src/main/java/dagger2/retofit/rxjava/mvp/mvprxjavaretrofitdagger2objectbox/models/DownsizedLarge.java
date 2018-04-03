package dagger2.retofit.rxjava.mvp.mvprxjavaretrofitdagger2objectbox.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DownsizedLarge {

@SerializedName("url")
@Expose
private String url;
@SerializedName("width")
@Expose
private String width;
@SerializedName("height")
@Expose
private String height;
@SerializedName("size")
@Expose
private String size;

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getWidth() {
return width;
}

public void setWidth(String width) {
this.width = width;
}

public String getHeight() {
return height;
}

public void setHeight(String height) {
this.height = height;
}

public String getSize() {
return size;
}

public void setSize(String size) {
this.size = size;
}

}