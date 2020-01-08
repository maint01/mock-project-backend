package com.itsol.train.mock.dao;

import com.itsol.train.mock.dto.UserDto;

import java.util.List;

public interface UserDAO {
    List<UserDto> findAllUserNotActive1();

    List<UserDto> findAllUserNotActive2();
}
