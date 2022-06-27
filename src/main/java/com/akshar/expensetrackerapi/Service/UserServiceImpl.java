package com.akshar.expensetrackerapi.Service;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;
import com.akshar.expensetrackerapi.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel userModel) {
        User user = new User();
        System.out.println(user);
        BeanUtils.copyProperties(userModel, user);
        System.out.println(user);
        System.out.println(userModel);
        return userRepository.save(user);
    }
}
