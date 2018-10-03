package store;

import model.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MedicalServiceService;

import java.util.List;

@Transactional
@Repository
public class MedicalServiceStore {

    @Autowired
    MedicalServiceService medicalServiceService;

    public int addMedicalService(MedicalService medicalService){
       return medicalServiceService.addMedicalService(medicalService);
    }

    public List<MedicalService> getAllMedicalService(){
        return medicalServiceService.getAllMedicalService();
    }

    public void updateMedicalService(MedicalService medicalService) {
        medicalServiceService.updateMedicalService(medicalService);
    }

    public void deleteMedicalService(int medicalServiceId){
       medicalServiceService.deleteMedicalService(medicalServiceId);
    }

    public MedicalService getMedicalService(int medicalServiceId) {
        return medicalServiceService.getMedicalService(medicalServiceId);
    }

    public MedicalService findMedicalServiceByID(int medicalServiceId) {
        return medicalServiceService.findMedicalServiceByID(medicalServiceId);
    }

    public List<MedicalService> findMedicalServiceByName(String name) {
        return medicalServiceService.findMedicalServiceByName(name);
    }
}
