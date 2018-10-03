package controller;


import model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.DrugStore;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class DrugController {

    @Autowired
    DrugStore drugStore;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "drugs", method = RequestMethod.POST)
    public void createNewDrug(@RequestBody Drug drug) {
        drugStore.createNewDrug(drug);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "drugs", method = RequestMethod.GET)
    public List<Drug> getAllDrugs() {
        return drugStore.getAllDrugs();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "drugs/findbyid/{id}", method = RequestMethod.GET)
    public Drug findDrugByID(@PathVariable int id) {
        return drugStore.findDrugByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "drugs/findbyname/{name}", method = RequestMethod.GET)
    public List<Drug> findDrugByName(@PathVariable String name) {
        return drugStore.findDrugByName(name);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "drugs/{id}", method = RequestMethod.DELETE)
    public void deleteDrug(@PathVariable int id) {
        drugStore.deleteDrug(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "drugs/{id}", method = RequestMethod.PUT)
    public void updateDrug(@RequestBody Drug drug, @PathVariable int id) {
        drugStore.updateDrug(drug, id);
    }
}
