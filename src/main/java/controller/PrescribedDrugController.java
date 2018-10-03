package controller;


import model.PrescribedDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.PrescribedDrugService;
import store.PrescribedDrugStore;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PrescribedDrugController {

    @Autowired
    private PrescribedDrugStore prescribedDrugStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="prescribedDrugs", method = RequestMethod.GET)
    public List<PrescribedDrug> getAllDiagnosedDisease() {
        return prescribedDrugStore.getAllPrescribedDrugs();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "prescribedDrugs/findbyid/{id}", method = RequestMethod.GET)
    public PrescribedDrug findPrescribedDrugByID(@PathVariable int id) {
        return prescribedDrugStore.findPrescribedDrugByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "prescribedDrugs/findbyprescriptionID/{prescriptionID}")
    public List<PrescribedDrug> getAllPrescribedDrugPerPrescription(@PathVariable int prescriptionID) {
        return prescribedDrugStore.getAllPrescribedDrugPerPrescription(prescriptionID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path="prescribedDrugs/{id}", method = RequestMethod.PUT)
    public void updateDiagnosedDisease(@RequestBody PrescribedDrug prescribedDrug, @PathVariable int id) {
        prescribedDrugStore.updatePrescribedDrug(prescribedDrug, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path="prescribedDrugs/{id}", method = RequestMethod.DELETE)
    public void deleteDiagnosedDisease(@PathVariable int id){
        prescribedDrugStore.deletePrescribedDrug(id);
    }

}
