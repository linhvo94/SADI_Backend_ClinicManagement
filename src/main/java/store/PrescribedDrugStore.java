package store;


import model.PrescribedDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.PrescribedDrugService;

import java.util.List;

@Transactional
@Repository
public class PrescribedDrugStore {

    @Autowired
    PrescribedDrugService prescribedDrugService;

    public List<PrescribedDrug> getAllPrescribedDrugs() {
        return prescribedDrugService.getAllPrescribedDrugs();
    }

    public PrescribedDrug findPrescribedDrugByID(int id) {
        return prescribedDrugService.findPrescribedDrugByID(id);
    }

    public List<PrescribedDrug> getAllPrescribedDrugPerPrescription(int prescriptionID) {
        return prescribedDrugService.getAllPrescribedDrugPerPrescription(prescriptionID);
    }

    public void deletePrescribedDrug(int id) {
        prescribedDrugService.deletePrescribedDrug(id);
    }

    public void updatePrescribedDrug(PrescribedDrug prescribedDrugInRequest, int id) {
        prescribedDrugService.updatePrescribedDrug(prescribedDrugInRequest, id);
    }
}
