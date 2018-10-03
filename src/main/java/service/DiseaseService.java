package service;

import model.Disease;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DiseaseService {

    @Autowired
    SessionFactory sessionFactory;

    public void createNewDisease(Disease disease) {
        sessionFactory.getCurrentSession().save(disease);
    }

    public List<Disease> getAllDiseases() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease");
        return query.list();
    }

    public Disease findDiseaseByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where id = :id");
        query.setInteger("id", id);
        return (Disease) query.uniqueResult();
    }

    public List<Disease> findDiseaseByICD(String icd) {
        //query to get Disease object by icd
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where lower(icd) like lower(:icd)");
        query.setString("icd", "%"+icd+"%");
        return query.list();
    }

    public List<Disease> findDiseaseByName(String diseaseName) {
        //query to get Disease object by icd
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where lower(diseaseName) like lower(:diseaseName)");
        query.setString("diseaseName", "%"+diseaseName+"%");
        return query.list();
    }

    public void deleteDisease(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where id = :id");
        query.setInteger("id", id);
        Disease disease = (Disease) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(disease);
    }

    public void updateDisease(Disease disease, int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where id = :id");
        query.setInteger("id", id);

        Disease diseaseDB = (Disease) query.uniqueResult();

        diseaseDB.setIcd(disease.getIcd());
        diseaseDB.setDiseaseName(disease.getDiseaseName());
        diseaseDB.setTypeCode(disease.getTypeCode());
        diseaseDB.setTypeName(disease.getTypeName());

        sessionFactory.getCurrentSession().update(diseaseDB);
    }

}
