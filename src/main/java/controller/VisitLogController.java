package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.VisitLogStore;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class VisitLogController {

    @Autowired
    private VisitLogStore visitLogStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visitsByNewPatient", method = RequestMethod.POST)
    public int createNewVisitLogWithNewPatient(@RequestBody VisitLog visitLog) {
         return visitLogStore.createNewVisitLogWithNewPatient(visitLog);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visitsByCurrentPatient", method = RequestMethod.POST)
    public int createNewVisitLogWithExistingPatient(@RequestBody VisitLog visitLog) {
        return visitLogStore.createNewVisitLogWithExistingPatient(visitLog);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits", method = RequestMethod.GET)
    public List<VisitLog> getAllVisitLogs() {
        return visitLogStore.getAllVisitLogs();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits/findbydate/{date}", method = RequestMethod.GET)
    public List<VisitLog> findVisitLogByDate(@PathVariable("date") String date) {
        return visitLogStore.findVisitLogByDate(date);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits/findbyvisitid/{visitID}", method = RequestMethod.GET)
    public VisitLog findVisitLogByID(@PathVariable("visitID") int visitID) {
        return visitLogStore.findVisitLogByID(visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR','ROLE_NURSE')")
    @RequestMapping(path = "visits/findbypatient/{patientID}", method = RequestMethod.GET)
    public List<VisitLog> findVisitLogByPatient(@PathVariable int patientID) {
        return visitLogStore.findVisitLogByPatient(patientID);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "visits/{visitID}", method = RequestMethod.DELETE)
    public void  deleteVisitLog(@PathVariable int visitID) {
        visitLogStore.deleteVisitLog(visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits/editDateTime/{visitID}", method = RequestMethod.PUT)
    public void updateVisitLogDateTime(@RequestBody VisitLog visitLog, @PathVariable int visitID) {
        visitLogStore.updateVisitLogDateTime(visitLog, visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits/updatePatientByNewPatient/{visitID}", method = RequestMethod.PUT)
    public void updateVisitLogPatientInfoByNewPatient(@RequestBody VisitLog visitLog, @PathVariable int visitID) {
        visitLogStore.updateVisitLogPatientInfoByNewPatient(visitLog, visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits/updatePatientByCurrentPatient/{visitID}", method = RequestMethod.PUT)
    public void updateVisitLogPatientInfoByCurrentPatient(@RequestBody VisitLog visitLog, @PathVariable int visitID) {
        visitLogStore.updateVisitLogPatientInfoByCurrentPatient(visitLog, visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "visits/addProblems/{visitID}", method = RequestMethod.PUT)
    public void addProblemsForAVisit(@RequestBody List<Problem> problems, @PathVariable int visitID) {
        visitLogStore.addProblemsForAVisit(problems, visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "visits/addDiagnosedDiseases/{visitID}", method = RequestMethod.PUT)
    public void addDiagnosedDiseasesForAVisit(@RequestBody List<DiagnosedDisease> diagnosedDiseases, @PathVariable int visitID) {
        visitLogStore.addDiagnosedDiseasesForAVisit(diagnosedDiseases, visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "visits/addPrescriptions/{visitID}", method = RequestMethod.PUT)
    public void addPrescriptionsForAVisit(@RequestBody List<Prescription> prescriptions, @PathVariable int visitID) {
        visitLogStore.addPrescriptionsForAVisit(prescriptions, visitID);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @RequestMapping(path = "visits/addLabTests/{visitID}", method = RequestMethod.PUT)
    public void addLabTestsForAVisit(@RequestBody List<LabTest> labTests, @PathVariable int visitID) {
        visitLogStore.addLabTestsForAVisit(labTests, visitID);
    }
}
