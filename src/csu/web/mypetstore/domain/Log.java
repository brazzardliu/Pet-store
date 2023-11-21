package csu.web.mypetstore.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Log implements Serializable {
    private String username;
    private String info;
    private String data;
    private String value;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
