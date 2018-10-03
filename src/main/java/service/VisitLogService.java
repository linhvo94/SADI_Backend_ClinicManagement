package service;

import model.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class VisitLogService {

    @Autowired
    SessionFactory sessionFactory;

    public int createNewVisitLogWithNewPatient(VisitLog visitLog) {
        //prepare visitLog object for the datbase which only contains patient, date and time
        //the object will ignore other children properties
        VisitLog visitLogDB = new VisitLog();
        visitLogDB.setPatient(visitLog.getPatient());
        visitLogDB.setVisitDate(visitLog.getVisitDate());
        visitLogDB.setVisitTime(visitLog.getVisitTime());
        sessionFactory.getCurrentSession().save(visitLogDB);
        visitLogDB.getId();
        return visitLogDB.getId();
    }

    public int createNewVisitLogWithExistingPatient(VisitLog visitLog) {
        //prepare visitLog object for the datbase which only contains patient, date and time
        //the object will ignore other children properties
        VisitLog visitLogDB = new VisitLog();
        visitLogDB.setVisitDate(visitLog.getVisitDate());
        visitLogDB.setVisitTime(visitLog.getVisitTime());
        Patient patient = findPatientByID(visitLog.getPatient().getId());
        visitLogDB.setPatient(patient);
        sessionFactory.getCurrentSession().save(visitLogDB);
        return visitLogDB.getId();
    }

    private Patient findPatientByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where id = :id");
        query.setInteger("id", id);
        return (Patient) query.uniqueResult();
    }


    public List<VisitLog> getAllVisitLogs() {
        Query query = sessionFactory.getCurrentSession().createQuery("from VisitLog");
        return query.list();
    }

    public List<VisitLog> findVisitLogByDate(String visitDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from VisitLog where cast(visitDate as date) = cast(:visitDate as date)");
        query.setString("visitDate", visitDate);
        return query.list();
    }

    public VisitLog findVisitLogByID(int visitID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from VisitLog as vl where vl.id = :visitID");
        query.setInteger("visitID", visitID);
        return (VisitLog) query.uniqueResult();
    }

    public List<VisitLog> findVisitLogByPatient(int patientID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from VisitLog as vl where vl.patient.id = :patientID");
        query.setInteger("patientID", patientID);
        return query.list();
    }

    public void deleteVisitLog(int visitID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from VisitLog as vl where vl.id = :visitID");
        query.setInteger("visitID", visitID);
        VisitLog visitLog = (VisitLog) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(visitLog);
    }

    //method to update date and time of a visit
    public void updateVisitLogDateTime(VisitLog visitLog, int visitID) {
        //get visitLog in the database
        VisitLog visitLogDB = findVisitLogByID(visitID);
        //update date and time properties of VisitLog with the data in the request
        visitLogDB.setVisitDate(visitLog.getVisitDate());
        visitLogDB.setVisitTime(visitLog.getVisitTime());
        sessionFactory.getCurrentSession().update(visitLogDB);
    }

    public void updateVisitLogPatientInfoByNewPatient(VisitLog visitLog, int visitID) {
        //get visitLog in the database
        VisitLog visitLogDB = findVisitLogByID(visitID);

        //If the patient hasn't been set by mistake, this will update the patient of the visitLog in Database
        // by creating new patient and assign it to the visitLog
        if(visitLogDB.getPatient() == null) {
            visitLogDB.setPatient(visitLog.getPatient());
            sessionFactory.getCurrentSession().update(visitLogDB);
        }
    }

    public void updateVisitLogPatientInfoByCurrentPatient(VisitLog visitLog, int visitID) {
        //get visitLog in the database
        VisitLog visitLogDB = findVisitLogByID(visitID);
        //If the patient hasn't been set by mistake, this will update the patient of the visitLog in Database
        // by assign a current patient to the visitLog
        if(visitLogDB.getPatient() == null) {
            Patient patient = findPatientByID(visitLog.getPatient().getId());
            visitLogDB.setPatient(patient);
            sessionFactory.getCurrentSession().update(visitLogDB);
        }

    }

    public void addProblemsForAVisit(List<Problem> problems, int visitID) {
        VisitLog visitLogDB = findVisitLogByID(visitID);
        if (visitLogDB != null ) {
            for (Problem problem : problems) {
                problem.setVisitLog(visitLogDB);
            }
            visitLogDB.setProblems(problems);
            sessionFactory.getCurrentSession().update(visitLogDB);
        }
    }

    public void addDiagnosedDiseasesForAVisit(List<DiagnosedDisease> diagnosedDiseases, int visitID) {
        VisitLog visitLogDB = findVisitLogByID(visitID);
        if (visitLogDB != null) {
            for (DiagnosedDisease diagnosedDisease: diagnosedDiseases) {
                //query to get new Disease object by id
                Disease disease = findDiseaseByID(diagnosedDisease.getDisease().getId());
                diagnosedDisease.setDisease(disease); // set found Disease object to DiagnosedDisease
                diagnosedDisease.setVisitLog(visitLogDB);
            }
            visitLogDB.setDiagnosedDiseases(diagnosedDiseases);
            sessionFactory.getCurrentSession().update(visitLogDB);
        }
    }


    public void addPrescriptionsForAVisit(List<Prescription> prescriptions, int visitID) {
        VisitLog visitLogDB = findVisitLogByID(visitID);
        if (visitLogDB != null) {
            for(Prescription prescription: prescriptions) {
                //set PrescribedDrug's reference to Prescription
                for (PrescribedDrug prescribedDrug: prescription.getPrescribedDrugs()) {
                    //get the id of Drug in the request before query
                    int drugIDInRequest = prescribedDrug.getDrug().getId();
                    prescribedDrug.setDrug(findDrugByID(drugIDInRequest)); // set found Drug object to PrescribedDrug
                    prescribedDrug.setPrescription(prescription);
                }
                prescription.setVisitLog(visitLogDB);
            }
            visitLogDB.setPrescriptions(prescriptions);
            sessionFactory.getCurrentSession().update(visitLogDB);
        }
    }

    public void addLabTestsForAVisit(List<LabTest> labTests, int visitID) {
        VisitLog visitLogDB = findVisitLogByID(visitID);
        if (visitLogDB != null) {
            for(LabTest labTest: labTests) {
                for (LabMedical labMedical: labTest.getLabMedicals()) {
                    //get the id of MedicalService in the request before query
                    int medicalServiceIDInRequest = labMedical.getMedicalService().getId();
                    labMedical.setMedicalService(findMedicalServiceByID(medicalServiceIDInRequest));
                    labMedical.setLabTest(labTest);
                }
                labTest.setVisitLog(visitLogDB);
            }
            visitLogDB.setLabTests(labTests);
            sessionFactory.getCurrentSession().update(visitLogDB);
        }
    }


    private MedicalService findMedicalServiceByID(int medicalServiceID) {
        //query to get LabTest object by id
        Query query = sessionFactory.getCurrentSession().createQuery("from MedicalService where id = :id");
        query.setInteger("id", medicalServiceID);
        return (MedicalService) query.setMaxResults(1).uniqueResult();
    }

    private Disease findDiseaseByID(int diseaseID) {
        //query to get Disease object by id
        Query query = sessionFactory.getCurrentSession().createQuery("from Disease where id = :id");
        query.setInteger("id", diseaseID);
        return (Disease) query.setMaxResults(1).uniqueResult();
    }

    private Drug findDrugByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Drug where id = :id");
        query.setInteger("id", id);
        return (Drug) query.uniqueResult();
    }
}
