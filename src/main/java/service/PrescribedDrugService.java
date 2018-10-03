package service;


import model.Drug;
import model.PrescribedDrug;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PrescribedDrugService {

    @Autowired
    SessionFactory sessionFactory;

    public List<PrescribedDrug> getAllPrescribedDrugs() {
        Query query = sessionFactory.getCurrentSession().createQuery("from PrescribedDrug");
        return query.list();
    }

    public PrescribedDrug findPrescribedDrugByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from PrescribedDrug where id = :id");
        query.setInteger("id", id);
        return (PrescribedDrug) query.uniqueResult();
    }

    public List<PrescribedDrug> getAllPrescribedDrugPerPrescription(int prescriptionID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from PrescribedDrug as p where p.prescription.id = :prescriptionID");
        query.setInteger("prescriptionID", prescriptionID);
        return query.list();
    }

    public void deletePrescribedDrug(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from PrescribedDrug where id = :id");
        query.setInteger("id", id);
        PrescribedDrug prescribedDrug = (PrescribedDrug) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(prescribedDrug);
    }

    public void updatePrescribedDrug(PrescribedDrug prescribedDrugInRequest, int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from PrescribedDrug where id = :id");
        query.setInteger("id", id);

        PrescribedDrug prescribedDrugDB = (PrescribedDrug) query.uniqueResult();

        if (prescribedDrugDB.getDrug().getId() != prescribedDrugInRequest.getDrug().getId()) {

            //get the new drug based on new id
            Drug newPrescriptedDrug = findDrugByID(prescribedDrugInRequest.getDrug().getId());
            //set new Drug for the prescribeDrug in the database
            prescribedDrugDB.setDrug(newPrescriptedDrug);
        }


        sessionFactory.getCurrentSession().update(prescribedDrugDB);
    }

    private Drug findDrugByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id = :id");
        query.setInteger("id", id);
        return (Drug) query.uniqueResult();
    }
}
