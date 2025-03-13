package com.Spring_Security_Back.user.interfaces;


import com.Spring_Security_Back.user.models.entity.User;
import com.Spring_Security_Back.user.models.response.UserResponse;

public interface UserMapEntityToDto {
    UserResponse entityToDto(User user);
}