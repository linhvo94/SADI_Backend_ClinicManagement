package store;

import model.LabMedical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LabMedicalService;

import java.util.List;

@Transactional
@Repository
public class LabMedicalStore {

    @Autowired
    LabMedicalService labMedicalService;

    public int addLabMedical(LabMedical lab_medical){
        return labMedicalService.addLabMedical(lab_medical);
    }

    public void updateLabMedical(LabMedical labMedical, int labMedicalID){
        labMedicalService.updateLabMedical(labMedical, labMedicalID);
    }

    public void deleteLabMedical(int labMedicalID){
        labMedicalService.deleteLabMedical(labMedicalID);

    }
    public List<LabMedical> getAllLabMedical(){
        return labMedicalService.getAllLabMedical();
    }

    public LabMedical findLabMedicalByID(int labMedicalID){
        return labMedicalService.findLabMedicalByID(labMedicalID);
    }

}
