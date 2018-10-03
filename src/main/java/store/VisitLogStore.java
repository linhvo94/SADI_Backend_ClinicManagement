package store;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.VisitLogService;

import java.util.List;

@Transactional
@Service
public class VisitLogStore {

    @Autowired
    VisitLogService visitLogService;

    public int createNewVisitLogWithNewPatient(VisitLog visitLog) {
        return visitLogService.createNewVisitLogWithNewPatient(visitLog);
    }

    public int createNewVisitLogWithExistingPatient(VisitLog visitLog) {
        return visitLogService.createNewVisitLogWithExistingPatient(visitLog);
    }


    public List<VisitLog> getAllVisitLogs() {
        return visitLogService.getAllVisitLogs();
    }

    public List<VisitLog> findVisitLogByDate(String visitDate) {
        return visitLogService.findVisitLogByDate(visitDate);
    }

    public VisitLog findVisitLogByID(int visitID) {
        return visitLogService.findVisitLogByID(visitID);
    }

    public List<VisitLog> findVisitLogByPatient(int patientID) {
        return visitLogService.findVisitLogByPatient(patientID);
    }

    public void deleteVisitLog(int visitID) {
        visitLogService.deleteVisitLog(visitID);
    }

    public void updateVisitLogDateTime(VisitLog visitLog, int visitID) {
        visitLogService.updateVisitLogDateTime(visitLog, visitID);
    }

    public void updateVisitLogPatientInfoByNewPatient(VisitLog visitLog, int visitID) {
        visitLogService.updateVisitLogPatientInfoByNewPatient(visitLog, visitID);
    }

    public void updateVisitLogPatientInfoByCurrentPatient(VisitLog visitLog, int visitID) {
        visitLogService.updateVisitLogPatientInfoByCurrentPatient(visitLog, visitID);
    }

    public void addProblemsForAVisit(List<Problem> problems, int visitID) {
        visitLogService.addProblemsForAVisit(problems, visitID);
    }

    public void addDiagnosedDiseasesForAVisit(List<DiagnosedDisease> diagnosedDiseases, int visitID) {
        visitLogService.addDiagnosedDiseasesForAVisit(diagnosedDiseases, visitID);
    }

    public void addPrescriptionsForAVisit(List<Prescription> prescriptions, int visitID) {
        visitLogService.addPrescriptionsForAVisit(prescriptions, visitID);
    }

    public void addLabTestsForAVisit(List<LabTest> labTests, int visitID) {
        visitLogService.addLabTestsForAVisit(labTests, visitID);
    }
}
