package com.akshar.expensetrackerapi.Service;

import com.akshar.expensetrackerapi.Entity.User;
import com.akshar.expensetrackerapi.Entity.UserModel;
import com.akshar.expensetrackerapi.Exceptions.ItemAlreadyExistsException;
import com.akshar.expensetrackerapi.Exceptions.ResourceNotFoundException;
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

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id :" +id));

    }

    @Override
    public User updateUser(UserModel userModel, Long id) {
        User user = readUser(id);
        user.setName(userModel.getName() != null ? userModel.getName() : user.getName());
        user.setEmail(userModel.getEmail() != null ? userModel.getEmail() : user.getEmail());
        user.setPassword(userModel.getPassword() != null ? userModel.getPassword() : user.getPassword());
        user.setAge(userModel.getAge() != null ? userModel.getAge() : user.getAge());
        return userRepository.save(user);

    }

    @Override
    public void deleteUser(Long id) {
        User user = readUser(id);
        userRepository.delete(user);
    }
}
