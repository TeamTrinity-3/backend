package com.Mofit.MofitBackend.global.login.details;

public interface OAuth2UserInfo {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
    String getPicture();
}
