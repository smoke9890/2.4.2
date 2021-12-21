package web.dao;



import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserDao {
    void saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    void removeUserById(Long id);

    User getUserByName(String name);

    List<Role> getAllRoles ();

    Role getIdByRole(Long id);
    void edit(User u);

}
