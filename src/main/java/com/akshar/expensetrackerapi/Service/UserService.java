package com.akshar.expensetrackerapi.Service;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;

public interface UserService {

    User createUser(UserModel userModel);
}
