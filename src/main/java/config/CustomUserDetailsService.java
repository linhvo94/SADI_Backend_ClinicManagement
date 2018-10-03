package config;

import model.user.UserRoles;
import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import service.UserRolesService;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRolesService userRolesService;

    @Autowired
    UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        Users userDB = userService.getUserByName(s);
        if(userDB == null) {
            throw new UsernameNotFoundException("Not found user");
        }

        final List<UserRoles> userRoles = userRolesService.getAllUserRolesPerUser(userDB.getId());

        List<GrantedAuthority> list = new ArrayList<>();

        for (final UserRoles userRoleDB: userRoles) {
            GrantedAuthority grantedAuthority = new GrantedAuthority() {
            public String getAuthority() {
                return userRoleDB.getRoles().getName();
            }
        };
            list.add(grantedAuthority);
        }

        User user = new User(userDB.getUsername(), userDB.getPassword(),true,true,true, true, list );

        return user;

    }
}
