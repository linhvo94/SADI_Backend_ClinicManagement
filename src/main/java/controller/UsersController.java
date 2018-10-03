package controller;

import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;


@RestController
@RequestMapping(path = "/")
public class UsersController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "users/findbyid/{id}", method = RequestMethod.GET)
    public Users findUserByID(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path="users", method = RequestMethod.GET)
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
