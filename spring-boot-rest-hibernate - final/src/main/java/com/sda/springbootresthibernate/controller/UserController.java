package com.sda.springbootresthibernate.controller;

import com.sda.springbootresthibernate.entity.User;
import com.sda.springbootresthibernate.errors.NotFoundException;
import com.sda.springbootresthibernate.models.pojo.UserRequestParams;
import com.sda.springbootresthibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
//@ResponseBody ne rast te kthimit te vete objektit dhe jo te ResponseEntity
//@RestController => @Controller + @ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    //@RequestMapping(method =RequestMethod.GET,path = "/users") same as @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        final List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/page")
    public String returnPage(){
        return "view";
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") final Long id) throws NotFoundException {
        final User user = userService.getTheUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/user")
    public ResponseEntity<User> postUser(@RequestBody final UserRequestParams userRequestParams) {
        final User user = userService.saveTheUser(userRequestParams);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<User> putUser(@PathVariable("userId") final Long id, @RequestBody final UserRequestParams userRequestParams) throws NotFoundException {
        User user = userService.updateTheUser(id, userRequestParams);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") final Long id) throws NotFoundException {
        userService.deleteTheUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/users")
    public ResponseEntity<List<User>> getUsers(@RequestParam final String firstName,
                                               @RequestParam final String lastName,
                                               @RequestParam final String email) {
        List<User> users = userService.filterUsers(firstName, lastName, email);
        return ResponseEntity.ok(users);
    }
}
