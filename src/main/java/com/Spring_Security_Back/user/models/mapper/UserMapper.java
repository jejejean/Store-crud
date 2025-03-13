package com.Spring_Security_Back.user.models.mapper;

import com.Spring_Security_Back.user.interfaces.UserMapEntityToDto;
import com.Spring_Security_Back.user.models.entity.User;
import com.Spring_Security_Back.user.models.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements UserMapEntityToDto {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse entityToDto(User user) {
        return modelMapper.map(user, UserResponse.class);
    }
}
