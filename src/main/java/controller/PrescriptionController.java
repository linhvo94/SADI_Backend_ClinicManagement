package controller;


import model.PrescribedDrug;
import model.Prescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.PrescriptionService;
import store.PrescriptionStore;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PrescriptionController {

    @Autowired
    private PrescriptionStore prescriptionStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "prescriptions", method = RequestMethod.GET)
    public List<Prescription> getAllPrescriptions() {
        return prescriptionStore.getAllPrescriptions();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "prescriptions/findbyid/{id}", method = RequestMethod.GET)
    public Prescription findPrescriptionByID(@PathVariable int id) {
        return prescriptionStore.findPrescriptionByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "prescriptions/findbyvisitID/{visitID}", method = RequestMethod.GET)
    public List<Prescription> getAllPrescriptionsPerVisit(@PathVariable("visitID") int visitID){
        return prescriptionStore.getAllPrescriptionsPerVisit(visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "prescribedDrugsInPrescription/{id}", method = RequestMethod.GET)
    public List<PrescribedDrug> getAllPrescribedDrugPerPrescription(@PathVariable int id) {
        return prescriptionStore.getAllPrescribedDrugPerPrescription(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "prescriptions/{id}", method = RequestMethod.PUT)
    public void updatePrescription(@RequestBody Prescription prescription, @PathVariable int id) {
        prescriptionStore.updatePrescription(prescription, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "prescriptions/{id}", method = RequestMethod.DELETE)
    public void deletePrescription(@PathVariable int id) {
        prescriptionStore.deletePrescription(id);
    }


}
