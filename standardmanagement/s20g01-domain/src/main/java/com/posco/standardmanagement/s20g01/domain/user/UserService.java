package com.posco.standardmanagement.s20g01.domain.user;

import com.posco.standardmanagement.s20g01.domain.user.CreateUserCommand;
import com.posco.standardmanagement.s20g01.domain.user.DeleteUserCommand;
import com.posco.standardmanagement.s20g01.domain.user.UpdateUserCommand;
import com.posco.standardmanagement.s20g01.domain.user.User;
import com.posco.standardmanagement.s20g01.domain.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(CreateUserCommand command) {
        User user = new User();
        user.setName(command.getName());
        user.setEmail(command.getEmail());
        return userRepository.save(user);
    }

    public User update(Long id, UpdateUserCommand command) {
        User existing = findById(id);
        if (command.getId() != null) {
            existing.setId(command.getId());
        }
        if (command.getName() != null) {
            existing.setName(command.getName());
        }
        if (command.getEmail() != null) {
            existing.setEmail(command.getEmail());
        }
        return userRepository.save(existing);
    }

    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User not found"
                )
            );
    }
    //// readModel mybatis
}
