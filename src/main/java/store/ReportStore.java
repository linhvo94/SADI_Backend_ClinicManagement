package store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ReportService;

import java.util.List;

@Transactional
@Repository
public class ReportStore {

    @Autowired
    ReportService reportService;

    public List<Object[]> countNumberOfPatientVisitsByDate() {
        return reportService.countNumberOfPatientVisitsByDate();
    }

    public List<Object[]> countDrugPrescribed() {
        return reportService.countDrugPrescribed();
    }
}
