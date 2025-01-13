package com.proyecto.adopcion.service;

import com.proyecto.adopcion.model.User;
import com.proyecto.adopcion.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, User userUpdate) {
        Optional<User> userCurrent = userRepository.findById(id);

        if (userCurrent.isPresent()) {

            User user = userCurrent.get();

            user.setName(userUpdate.getName());
            user.setEmail(userUpdate.getEmail());
            user.setPhone(userUpdate.getPhone());

            return userRepository.save(user);

        } else {
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }

    }

    @Transactional
    public User parcialUpdateUser(Long id, User userUpdate) {
        Optional<User> userCurrent = userRepository.findById(id);

        if (userCurrent.isPresent()) {

            User user = userCurrent.get();

            if (userUpdate.getName() != null) {
                user.setName(userUpdate.getName());
            }
            if (userUpdate.getEmail() != null) {
                user.setEmail(userUpdate.getEmail());
            }
            if (userUpdate.getPhone() != null) {
                user.setPhone(userUpdate.getPhone());
            }

            return userRepository.save(user);

        } else {
            throw new RuntimeException("Usuario no encontado con el id: " + id);
        }

    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
