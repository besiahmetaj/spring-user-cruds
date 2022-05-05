package com.sda.springbootresthibernate.service;

import com.sda.springbootresthibernate.entity.User;
import com.sda.springbootresthibernate.errors.NotFoundException;
import com.sda.springbootresthibernate.models.constants.ErrorMessages;
import com.sda.springbootresthibernate.models.pojo.UserRequestParams;
import com.sda.springbootresthibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveTheUser(final UserRequestParams userRequestParams) {
        User user = new User();
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        return userRepository.save(user);

    }

    public User updateTheUser(final Long id, final UserRequestParams userRequestParams) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)
        );

        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        return user;

    }

    public User getTheUser(final Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)
        );
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteTheUser(final Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)
        );
        userRepository.delete(user);
    }

    public List<User> filterUsers(final String firstName, final String lastName, final String email) {
        return userRepository.findByAttributes(firstName, lastName, email);
    }


}
