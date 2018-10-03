package controller;


import model.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.DiseaseStore;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class DiseaseController {

    @Autowired
    DiseaseStore diseaseStore;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "diseases", method = RequestMethod.POST)
    public void createNewDisease(@RequestBody Disease disease) {
        diseaseStore.createNewDisease(disease);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "diseases", method = RequestMethod.GET)
    public List<Disease> getAllDiseases() {
        return diseaseStore.getAllDiseases();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "diseases/findbyid/{id}", method = RequestMethod.GET)
    public Disease findDiseaseByID(@PathVariable int id) {
        return diseaseStore.findDiseaseByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "diseases/findbyicd/{icd}", method = RequestMethod.GET)
    public List<Disease> findDiseaseByICD(@PathVariable String icd) {
        return diseaseStore.findDiseaseByICD(icd);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "diseases/findbyname/{diseaseName}", method = RequestMethod.GET)
    public List<Disease> findDiseaseByName(@PathVariable String diseaseName) {
        return diseaseStore.findDiseaseByName(diseaseName);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "diseases/{id}", method = RequestMethod.DELETE)
    public void deleteDisease(@PathVariable int id) {
        diseaseStore.deleteDisease(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "diseases/{id}", method = RequestMethod.PUT)
    public void updateDisease(@RequestBody Disease disease, @PathVariable int id) {
        diseaseStore.updateDisease(disease, id);
    }
}
