package controller;

import model.LabMedical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.LabMedicalStore;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class LabMedicalController {

    @Autowired
    LabMedicalStore labMedicalStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="labmedicals", method = RequestMethod.GET)
    public List<LabMedical> getAllLabMedical() {
        return labMedicalStore.getAllLabMedical();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "labmedicals", method = RequestMethod.POST)
    public int addLabMedical(@RequestBody LabMedical labMedical){
        return labMedicalStore.addLabMedical(labMedical);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "labmedicals/{labMedicalID}", method = RequestMethod.DELETE)
    public void deleteLabMedical(@PathVariable int labMedicalID){
        labMedicalStore.deleteLabMedical(labMedicalID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "labmedicals/{labMedicalID}", method = RequestMethod.PUT)
    public void updateLabMedical(@RequestBody  LabMedical labMedical, @PathVariable int labMedicalID){
        labMedicalStore.updateLabMedical(labMedical, labMedicalID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "labmedicals/findbyid/{labMedicalID}", method = RequestMethod.GET)
    public LabMedical findLabMedicalByID(@PathVariable int labMedicalID) {
        return labMedicalStore.findLabMedicalByID(labMedicalID);
    }

}
