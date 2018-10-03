package service;

import model.PrescribedDrug;
import model.VisitLog;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ReportService {

    @Autowired
    SessionFactory sessionFactory;


    //count the number of patients who visit per day
    //if a patient visits 2 times, it still counts 1
    public List<Object[]> countNumberOfPatientVisitsByDate() {
        List results = sessionFactory.getCurrentSession().createCriteria(VisitLog.class)
                .setProjection( Projections.projectionList()
                        .add( Projections.countDistinct("patient") )
                        .add( Projections.groupProperty("visitDate" ) )
                )
                .addOrder(Order.asc("visitDate"))
                .list();
        return results;
    }

    //count the drug based on the quantity given to the patient
    public List<Object[]> countDrugPrescribed(){
        List results = sessionFactory.getCurrentSession().createCriteria(PrescribedDrug.class, "prescribedDrug")
                .setProjection( Projections.projectionList()
                        .add( Projections.sum("quantity"))
                        .add( Projections.groupProperty("drug" ) )
                )
                .list();
        return results;
    }

}
