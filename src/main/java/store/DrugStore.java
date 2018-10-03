package store;

import model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.DrugService;

import java.util.List;

@Transactional
@Repository
public class DrugStore {

    @Autowired
    DrugService drugService;

    public void createNewDrug(Drug drug) {
        drugService.createNewDrug(drug);
    }

    public List<Drug> getAllDrugs() {
        return drugService.getAllDrugs();
    }

    public Drug findDrugByID(int id) {
        return drugService.findDrugByID(id);
    }

    public List<Drug> findDrugByName(String name) {
        return drugService.findDrugByName(name);
    }

    public void deleteDrug(int id) {
        drugService.deleteDrug(id);
    }

    public void updateDrug(Drug drug, int id) {
        drugService.updateDrug(drug, id);
    }
}
