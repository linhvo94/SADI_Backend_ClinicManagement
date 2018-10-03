package store;


import model.DiagnosedDisease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.DiagnosedDiseaseService;

import java.util.List;

@Transactional
@Repository
public class DiagnosedDiseaseStore {

    @Autowired
    DiagnosedDiseaseService diagnosedDiseaseService;

    public List<DiagnosedDisease> getAllDiagnosedDisease() {
        return diagnosedDiseaseService.getAllDiagnosedDisease();
    }

    public DiagnosedDisease findDiagnosedDiseaseByID(int diagnosedDiseaseID) {
        return diagnosedDiseaseService.findDiagnosedDiseaseByID(diagnosedDiseaseID);
    }

    public List<DiagnosedDisease> getAllDiagnosedDiseasePerVisit(int visitID) {
        return diagnosedDiseaseService.getAllDiagnosedDiseasePerVisit(visitID);
    }

    public void updateDiagnosedDisease(DiagnosedDisease diagnosedDisease, int diagnosedDiseaseID) {
        diagnosedDiseaseService.updateDiagnosedDisease(diagnosedDisease, diagnosedDiseaseID);
    }

    public void deleteDiagnosedDisease(int diagnosedDiseaseID) {
        diagnosedDiseaseService.deleteDiagnosedDisease(diagnosedDiseaseID);
    }
}
