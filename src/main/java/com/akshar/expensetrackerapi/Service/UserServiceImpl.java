package com.akshar.expensetrackerapi.Service;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;
import com.akshar.expensetrackerapi.Exceptions.ItemAlreadyExistsException;
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
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("User is already register with email :" +userModel.getEmail());
        }
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        return userRepository.save(user);
    }
}
