package service;

import model.Drug;
import model.PrescribedDrug;
import model.Prescription;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PrescriptionService {

    @Autowired
    SessionFactory sessionFactory;


    public List<Prescription> getAllPrescriptions() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription");
        return query.list();
    }

    public Prescription findPrescriptionByID(int prescriptionID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription where id = :id");
        query.setInteger("id", prescriptionID);
        return (Prescription) query.uniqueResult();
    }

    public List<PrescribedDrug> getAllPrescribedDrugPerPrescription(int prescriptionID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from PrescribedDrug p where p.prescription.id = :prescriptionID");
        query.setInteger("prescriptionID", prescriptionID);
        return query.list();
    }

    public void updatePrescription(Prescription prescriptionInRequest, int prescriptionID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription where id = :id");
        query.setInteger("id", prescriptionID);
        //get the Prescription object in database, then update its primitive properties
        Prescription prescriptionDB = (Prescription) query.uniqueResult();


        //update the list of Drug given to patient
        //if the user provides different id of drug, update the new Drug for prescribedDrug
        //otherwise, just keep the same drug in the list
        for (PrescribedDrug prescribedDrugDB: prescriptionDB.getPrescribedDrugs()) {

            for (PrescribedDrug prescribedDrugInRequest: prescriptionInRequest.getPrescribedDrugs()) {
                //in order to update, the id of the prescribedDrug in database must be the same as in the request,
                //and the id of Drug must be different as the user wants to update new Drug
                if (prescribedDrugDB.getId() == prescribedDrugInRequest.getId() &&
                        prescribedDrugDB.getDrug().getId() != prescribedDrugInRequest.getDrug().getId()) {

                    prescribedDrugDB.setQuantity(prescribedDrugInRequest.getQuantity());
                    prescribedDrugDB.setDose(prescribedDrugInRequest.getDose());
                    prescribedDrugDB.setHowToUse(prescribedDrugInRequest.getHowToUse());
                    //get the new drug based on new id
                    Drug newPrescriptedDrug = findDrugByID(prescribedDrugInRequest.getDrug().getId());
                    //set new Drug for the prescribeDrug in the database
                    prescribedDrugDB.setDrug(newPrescriptedDrug);
                }
            }
        }

        sessionFactory.getCurrentSession().update(prescriptionDB);
    }

    public List<Prescription> getAllPrescriptionsPerVisit(int visitID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription as p where p.visitLog.id = :visitID");
        query.setInteger("visitID", visitID);
        return query.list();
    }

    public void deletePrescription(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Prescription where id = :id");
        query.setInteger("id", id);
        Prescription prescription = (Prescription) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(prescription);
    }

    private Drug findDrugByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id = :id");
        query.setInteger("id", id);
        return (Drug) query.uniqueResult();
    }
}
