package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class UserDaoImpl implements UserDao {


   @PersistenceContext
    private EntityManager em;

    @Autowired
    public UserDaoImpl() {
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User").getResultList();

    }

    @Override
    public User getUserById(Long id) {
        return em.find(User.class, id);
    }

    @Override
    public void removeUserById(Long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserByName(String name) {
        return em.createQuery("FROM User WHERE name = :name", User.class).setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("from Role").getResultList();
    }

    @Override
    public Role getIdByRole(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public void edit(User u) {
        em.merge(u);
    }
}

