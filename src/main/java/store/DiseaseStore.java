package store;

import model.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.DiseaseService;

import java.util.List;

@Transactional
@Repository
public class DiseaseStore {

    @Autowired
    DiseaseService diseaseService;

    public void createNewDisease(Disease disease) {
        diseaseService.createNewDisease(disease);
    }

    public List<Disease> getAllDiseases() {
        return diseaseService.getAllDiseases();
    }

    public Disease findDiseaseByID(int id) {
        return diseaseService.findDiseaseByID(id);
    }

    public List<Disease> findDiseaseByICD(String icd) {
        return diseaseService.findDiseaseByICD(icd);
    }

    public List<Disease> findDiseaseByName(String diseaseName) {
        return diseaseService.findDiseaseByName(diseaseName);
    }

    public void deleteDisease(int id) {
        diseaseService.deleteDisease(id);
    }

    public void updateDisease(Disease disease, int id) {
        diseaseService.updateDisease(disease, id);
    }
}
