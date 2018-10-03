package service;

import model.LabMedical;
import model.MedicalService;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class MedicalServiceService {

    @Autowired
    SessionFactory sessionFactory;

    public int addMedicalService(MedicalService medicalService){

        for (LabMedical labMedical: medicalService.getLabMedicals()){
            labMedical.setMedicalService(medicalService);
        }
        sessionFactory.getCurrentSession().save(medicalService);
        return medicalService.getId();
    }

    public List<MedicalService> getAllMedicalService(){
        return sessionFactory.getCurrentSession().createQuery("from MedicalService").list();
    }

    public void updateMedicalService(MedicalService medicalService){
        sessionFactory.getCurrentSession().update(medicalService);
    }

    public void deleteMedicalService(int medicalServiceID){
        MedicalService medicalService = getMedicalService(medicalServiceID);
        sessionFactory.getCurrentSession().delete(medicalService);
    }

    public MedicalService getMedicalService(int medicalServiceID){
        return (MedicalService) sessionFactory.getCurrentSession().get(MedicalService.class, medicalServiceID);
    }

    public MedicalService findMedicalServiceByID(int medicalServiceID){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService as m where m.id = :medicalServiceID");
        query.setInteger("medicalServiceID", medicalServiceID);
        return (MedicalService) query.uniqueResult();
    }

    public List<MedicalService> findMedicalServiceByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService as m where lower(m.name) like lower(:name)");
        query.setString("name", "%"+name+"%");
        return query.list();
    }
}
