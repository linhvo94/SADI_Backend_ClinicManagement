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
public class LabMedicalService {

    @Autowired
    SessionFactory sessionFactory;

    public MedicalService getMedicalServiceByID(int medicalServiceID){
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService  m where m.id =: medicalServiceID");
        query.setInteger("medicalServiceID", medicalServiceID);
        return (MedicalService) query.uniqueResult();
    }
    public int addLabMedical(LabMedical labMedical){
        MedicalService medicalService = getMedicalServiceByID(labMedical.getMedicalService().getId());

        if (medicalService != null){
            labMedical.setMedicalService(medicalService);
        }

        sessionFactory.getCurrentSession().save(labMedical);
        return labMedical.getId();
    }

    public void updateLabMedical(LabMedical labMedical, int labMedicalID){
        Query query = sessionFactory.getCurrentSession().createQuery("from LabMedical where id = :id");
        query.setInteger("id", labMedicalID);

        LabMedical labMedicalDB = (LabMedical) query.uniqueResult();
        labMedicalDB.setResult(labMedical.getResult());

        sessionFactory.getCurrentSession().update(labMedicalDB);
    }

    public void deleteLabMedical(int labMedicalID){
        LabMedical labMedical = findLabMedicalByID(labMedicalID);
        sessionFactory.getCurrentSession().delete(labMedical);
    }

    public List<LabMedical> getAllLabMedical(){
        return sessionFactory.getCurrentSession().createQuery("from LabMedical").list();
    }

    public LabMedical findLabMedicalByID(int LabMedicalID){
        return (LabMedical) sessionFactory.getCurrentSession().get(LabMedical.class, LabMedicalID);
    }

}
