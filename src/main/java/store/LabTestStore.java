package store;

import model.LabTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.LabTestService;

import java.util.List;

/**
 * Created by Linh Vo on 9/19/2018.
 */

@Transactional
@Repository
public class LabTestStore {

    @Autowired
    LabTestService labTestService;

    public int addLabTest(LabTest labTest){
        return labTestService.addLabTest(labTest);
    }

    public List<LabTest> getAllLabTestPerVisit(int visitID) {
        return labTestService.getAllLabTestPerVisit(visitID);
    }

    public void updateLabTest(LabTest labTest){
        labTestService.updateLabTest(labTest);
    }

    public void deleteLabTest(int  LabTestId){
        labTestService.deleteLabTest(LabTestId);

    }

    public List<LabTest> getAllLabTest(){
        return labTestService.getAllLabTest();
    }

    public LabTest getLabTest(int  LabTestId){
        return labTestService.getLabTest(LabTestId);
    }
}
