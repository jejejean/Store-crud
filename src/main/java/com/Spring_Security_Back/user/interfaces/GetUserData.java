package com.Spring_Security_Back.user.interfaces;


import com.Spring_Security_Back.user.models.entity.User;

public interface GetUserData {
    User getUserByUsername(String username);
}