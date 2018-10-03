package store;

import model.PrescribedDrug;
import model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.PrescriptionService;

import java.util.List;

@Transactional
@Repository
public class PrescriptionStore {

    @Autowired
    PrescriptionService prescriptionService;

    public List<Prescription> getAllPrescriptions(){
        return prescriptionService.getAllPrescriptions();
    }

    public Prescription findPrescriptionByID(int prescriptionID) {
        return prescriptionService.findPrescriptionByID(prescriptionID);
    }

    public List<PrescribedDrug> getAllPrescribedDrugPerPrescription(int prescriptionID) {
        return prescriptionService.getAllPrescribedDrugPerPrescription(prescriptionID);
    }

    public List<Prescription> getAllPrescriptionsPerVisit(int visitID){
        return prescriptionService.getAllPrescriptionsPerVisit(visitID);
    }

    public void updatePrescription(Prescription prescriptionInRequest, int prescriptionID) {
        prescriptionService.updatePrescription(prescriptionInRequest, prescriptionID);
    }

    public void deletePrescription(int id) {
        prescriptionService.deletePrescription(id);
    }
}
