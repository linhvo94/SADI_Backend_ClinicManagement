package controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.ReportService;
import store.ReportStore;


import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ReportController {

    @Autowired
    private ReportStore reportStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "/reportNumberOfVisits", method = RequestMethod.GET)
    public List<Object[]> countNumberOfPatientVisitsByDate() {
        return reportStore.countNumberOfPatientVisitsByDate();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "/reportNumberOfDrugPrescribed", method = RequestMethod.GET)
    public List<Object[]> countDrugPrescribed() {
        return reportStore.countDrugPrescribed();
    }
}
