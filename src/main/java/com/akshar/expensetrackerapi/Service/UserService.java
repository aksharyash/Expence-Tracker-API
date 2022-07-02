package com.akshar.expensetrackerapi.Service;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;

public interface UserService {

    User createUser(UserModel userModel);

    User readUser(Long id);

    User updateUser(UserModel userModel, Long id);

    void deleteUser(Long id);
}
