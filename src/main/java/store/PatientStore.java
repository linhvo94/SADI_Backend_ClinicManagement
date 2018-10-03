package store;


import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.PatientService;

import java.util.List;

@Transactional
@Repository
public class PatientStore {

    @Autowired
    PatientService patientService;


    public void createNewPatient(Patient patient) {
        patientService.createNewPatient(patient);
    }

    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    public Patient findPatientByID(int id) {
        return patientService.findPatientByID(id);
    }

    public List<Patient> findPatientByName(String name) {
        return patientService.findPatientByName(name);
    }

    public List<Patient> findPatientByBirthDate(String birthDate) {
        return patientService.findPatientByBirthDate(birthDate);
    }

    public void deletePatient(int id) {
        patientService.deletePatient(id);
    }

    public void updatePatient(Patient patient, int id) {
        patientService.updatePatient(patient, id);
    }
}
