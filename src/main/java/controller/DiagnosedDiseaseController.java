package controller;

import org.springframework.security.access.prepost.PreAuthorize;
import store.DiagnosedDiseaseStore;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class DiagnosedDiseaseController {

    @Autowired
    DiagnosedDiseaseStore diagnosedDiseaseStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="diagnosedDiseases", method = RequestMethod.GET)
    public List<DiagnosedDisease> getAllDiagnosedDisease() {
        return diagnosedDiseaseStore.getAllDiagnosedDisease();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "diagnosedDiseases/findbyid/{id}", method = RequestMethod.GET)
    public DiagnosedDisease findDiagnosedDiseaseByID(@PathVariable int id) {
        return diagnosedDiseaseStore.findDiagnosedDiseaseByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "diagnosedDiseases/findbyvisitID/{visitID}", method = RequestMethod.GET)
    public List<DiagnosedDisease> getAllDiagnosedDiseasePerVisit(@PathVariable int visitID) {
        return diagnosedDiseaseStore.getAllDiagnosedDiseasePerVisit(visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path="diagnosedDiseases/{id}", method = RequestMethod.PUT)
    public void updateDiagnosedDisease(@RequestBody DiagnosedDisease diagnosedDisease, @PathVariable int id) {
        diagnosedDiseaseStore.updateDiagnosedDisease(diagnosedDisease, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path="diagnosedDiseases/{id}", method = RequestMethod.DELETE)
    public void deleteDiagnosedDisease(@PathVariable int id) {
        diagnosedDiseaseStore.deleteDiagnosedDisease(id);
    }

}
