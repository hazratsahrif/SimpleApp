package com.example.simpleapp.ui.home.model;

public class ProfileModel {
    Integer ivUser;
    String tvUser;

    public Integer getIvUser() {
        return ivUser;
    }

    public void setIvUser(Integer ivUser) {
        this.ivUser = ivUser;
    }

    public String getTvUser() {
        return tvUser;
    }

    public void setTvUser(String tvUser) {
        this.tvUser = tvUser;
    }

    public ProfileModel(Integer ivUser, String tvUser) {
        this.ivUser = ivUser;
        this.tvUser = tvUser;
    }
}
