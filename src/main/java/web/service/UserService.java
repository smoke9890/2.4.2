package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void removeUserById(Long id);

    Role getIdByRole(Long id);

    List<Role> getAllRoles ();

     void edit(User u);
}
