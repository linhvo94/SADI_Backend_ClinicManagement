package service;

import model.Patient;
import model.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private SessionFactory sessionFactory;

    public Users getUserByName(String username){
        Query query = sessionFactory.getCurrentSession().createQuery("from Users where username=:username");
        query.setString("username", username);
                return (Users) query.uniqueResult();
    }
    public Users getUserById(int id){
        Query query = sessionFactory.getCurrentSession().createQuery("from Users u where u.id=:id");
        query.setInteger("id",id);
        return (Users) query.uniqueResult();
    }

    public List<Users> getAllUsers(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Users ");
        return query.list();
    }
}
