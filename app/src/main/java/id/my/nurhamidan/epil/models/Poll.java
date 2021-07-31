package id.my.nurhamidan.epil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Poll implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("nama")
    @Expose
    private String name;

    @SerializedName("id_pengguna")
    @Expose
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
