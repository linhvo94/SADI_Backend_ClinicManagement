package service;


import model.DiagnosedDisease;
import model.Disease;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DiagnosedDiseaseService {

    @Autowired
    SessionFactory sessionFactory;

    public List<DiagnosedDisease> getAllDiagnosedDisease() {
        Query query = sessionFactory.getCurrentSession().createQuery("from DiagnosedDisease");
        return query.list();
    }

    public DiagnosedDisease findDiagnosedDiseaseByID(int diagnosedDiseaseID) {
        Query query = sessionFactory.getCurrentSession().createQuery(" from DiagnosedDisease where id = :id");
        query.setInteger("id", diagnosedDiseaseID);
        return (DiagnosedDisease) query.uniqueResult();
    }


    public List<DiagnosedDisease> getAllDiagnosedDiseasePerVisit(int visitID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DiagnosedDisease as d where d.visitLog.id = :visitID");
        query.setInteger("visitID", visitID);
        return query.list();
    }

    public void updateDiagnosedDisease(DiagnosedDisease diagnosedDisease, int diagnosedDiseaseID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DiagnosedDisease where id = :id");
        query.setInteger("id", diagnosedDiseaseID);

        DiagnosedDisease diagnosedDiseaseDB = (DiagnosedDisease) query.uniqueResult();

        //check if the user changed different Disease based on Disease's ID
        //if the ids are different, query to find, then set new Disease for the DiagnosedDisease
        if(diagnosedDiseaseDB.getDisease().getId() != diagnosedDisease.getDisease().getId()) {
            //query to get new Disease object by id
            Disease disease = findDiseaseByID(diagnosedDisease.getDisease().getId());
            //if the id provided matched in the database in order to update new Disease
            if(disease != null) {
                diagnosedDiseaseDB.setDisease(disease); // set found Disease object to DiagnosedDisease
            }
        } // if the Disease's id is the same, don't update

        sessionFactory.getCurrentSession().update(diagnosedDiseaseDB);
    }

    private Disease findDiseaseByID(int diseaseID) {
        //query to get Disease object by id
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where id = :id");
        query.setInteger("id", diseaseID);
        return (Disease) query.setMaxResults(1).uniqueResult();
    }

    public void deleteDiagnosedDisease(int diagnosedDiseaseID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from DiagnosedDisease where id = :id");
        query.setInteger("id", diagnosedDiseaseID);
        DiagnosedDisease diagnosedDisease = (DiagnosedDisease) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(diagnosedDisease);
    }

}
