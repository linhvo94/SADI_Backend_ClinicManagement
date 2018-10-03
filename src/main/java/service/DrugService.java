package service;

import model.Drug;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DrugService {

    @Autowired
    SessionFactory sessionFactory;

    public void createNewDrug(Drug drug) {
        sessionFactory.getCurrentSession().save(drug);
    }

    public List<Drug> getAllDrugs() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug");
        return query.list();
    }

    public Drug findDrugByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id = :id");
        query.setInteger("id", id);
        return (Drug) query.uniqueResult();
    }

    public List<Drug> findDrugByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug as d where lower(d.drugName) like lower(:name)");
        query.setString("name", "%"+name+"%");
        return query.list();
    }

    public void deleteDrug(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id = :id");
        query.setInteger("id", id);
        Drug drug = (Drug) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(drug);
    }

    public void updateDrug(Drug drug, int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id = :id");
        query.setInteger("id", id);

        Drug drugDB = (Drug) query.uniqueResult();
        drugDB.setDrugName(drug.getDrugName());

        sessionFactory.getCurrentSession().update(drugDB);
    }
}
