package service;

import model.user.UserRoles;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserRolesService {
    @Autowired
    SessionFactory sessionFactory;

    public List<UserRoles> getAllUserRolesPerUser(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserRoles u where u.users.id = :id");
        query.setInteger("id", id);
        return query.list();
    }

    public List<UserRoles> getAllUserRolesPerUserName(String id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserRoles u where u.users.id = :id");
        query.setString("id", id);
        return query.list();
    }
}
