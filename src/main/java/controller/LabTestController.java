package controller;

import model.LabTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.LabMedicalStore;
import store.LabTestStore;

import java.util.List;

/**
 * Created by Linh Vo on 9/19/2018.
 */

@RestController
@RequestMapping(path="/")
public class LabTestController {

    @Autowired
    LabTestStore labTestStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="labtest", method = RequestMethod.GET)
    public List<LabTest> getLabTest() {
        return labTestStore.getAllLabTest();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "labtest", method = RequestMethod.POST)
    public int addLabTest(@RequestBody LabTest labTest){
        return labTestStore.addLabTest(labTest);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "labtest/{labTestID}", method = RequestMethod.DELETE)
    public void deleteLabTest(@PathVariable int labTestID){
        labTestStore.deleteLabTest(labTestID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "labtest", method = RequestMethod.PUT)
    public void updateLabTest(@RequestBody  LabTest labTest){
        labTestStore.updateLabTest(labTest);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "labtest/{Id}", method = RequestMethod.GET)
    public LabTest getLabTest(@PathVariable int LabTestId){
        return labTestStore.getLabTest(LabTestId);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "labtests/findbyvisitID/{visitID}", method = RequestMethod.GET)
    public List<LabTest> getAllLabTestPerVisit(@PathVariable("visitID") int visitID){
        return labTestStore.getAllLabTestPerVisit(visitID);
    }
}
