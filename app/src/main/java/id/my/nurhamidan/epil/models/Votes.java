package id.my.nurhamidan.epil.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Votes implements Serializable {
    @SerializedName("id_pengguna")
    @Expose
    private Integer userId;

    @SerializedName("id_kandidat")
    @Expose
    private Integer candidateId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }
}
