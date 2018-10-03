package controller;


import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import service.PatientService;
import store.PatientStore;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class PatientController {

    @Autowired
    private PatientStore patientStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "patients", method = RequestMethod.POST)
    public void createNewPatient(@RequestBody Patient patient) {
        patientStore.createNewPatient(patient);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="patients", method = RequestMethod.GET)
    public List<Patient> getAllPatients() {

        System.out.println("Patient");
        return patientStore.getAllPatients();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE','ROLE_PATIENT')")
    @RequestMapping(path = "patients/findbyid/{id}", method = RequestMethod.GET)
    public Patient findPatientByID(@PathVariable int id) {
        return patientStore.findPatientByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "patients/findbyname/{name}", method = RequestMethod.GET)
    public List<Patient> findPatientByName(@PathVariable String name){
        return patientStore.findPatientByName(name);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "patients/findbydob/{bd}", method = RequestMethod.GET)
    public List<Patient> findPatientByBirthDate(@PathVariable String bd) {
        return patientStore.findPatientByBirthDate(bd);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="patients/{id}", method = RequestMethod.DELETE)
    public void deletePatient(@PathVariable int id){
        patientStore.deletePatient(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="patients/{id}", method = RequestMethod.PUT)
    public void updatePatient(@RequestBody Patient patient, @PathVariable int id) {
        patientStore.updatePatient(patient, id);
    }

}
