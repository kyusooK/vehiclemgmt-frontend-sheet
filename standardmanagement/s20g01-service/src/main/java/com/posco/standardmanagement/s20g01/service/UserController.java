package com.posco.standardmanagement.s20g01.service;

import com.posco.standardmanagement.s20g01.domain.user.CreateUserCommand;
import com.posco.standardmanagement.s20g01.domain.user.DeleteUserCommand;
import com.posco.standardmanagement.s20g01.domain.user.UpdateUserCommand;
import com.posco.standardmanagement.s20g01.domain.user.User;
import com.posco.standardmanagement.s20g01.domain.user.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> create(
        @Valid @RequestBody CreateUserCommand command
    ) {
        return ResponseEntity.ok(userService.create(command));
    }

    @PatchMapping(path = "/users/{id}")
    public ResponseEntity<User> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateUserCommand command
    ) {
        return ResponseEntity.ok(userService.update(id, command));
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //readModel rest api
}
