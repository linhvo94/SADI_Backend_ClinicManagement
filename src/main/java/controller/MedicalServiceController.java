package controller;

import model.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import store.MedicalServiceStore;

import java.util.List;


@RestController
@RequestMapping(path = "/")
public class MedicalServiceController {

    @Autowired
    MedicalServiceStore medicalServiceStore;

    @RequestMapping(path="medicalservices", method = RequestMethod.GET)
    public List<MedicalService> getAllMedicalServices() {
        return medicalServiceStore.getAllMedicalService();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "medicalservices", method = RequestMethod.POST)
    public int addMedicalService(@RequestBody MedicalService medicalService){
        return medicalServiceStore.addMedicalService(medicalService);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "medicalservices/{medicalServiceID}", method = RequestMethod.DELETE)
    public void deleteMedicalService(@PathVariable int medicalServiceID){
        medicalServiceStore.deleteMedicalService(medicalServiceID);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(path = "medicalservices", method = RequestMethod.PUT)
    public void updateMedicalService(@RequestBody MedicalService medicalService){
        medicalServiceStore.updateMedicalService(medicalService);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR','ROLE_NURSE')")
    @RequestMapping(path = "medicalservices/findbyid/{medicalServiceID}", method = RequestMethod.GET)
    public MedicalService findMedicalServiceByID(@PathVariable int medicalServiceID){
        return medicalServiceStore.findMedicalServiceByID(medicalServiceID);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_DOCTOR','ROLE_NURSE', 'ROLE_PATIENT')")
    @RequestMapping(path = "medicalservices/findbyname/{name}", method = RequestMethod.GET)
    public List<MedicalService> findMedicalServiceByName(@PathVariable String name) {
        return medicalServiceStore.findMedicalServiceByName(name);
    }

}
