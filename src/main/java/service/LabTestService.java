package service;

import model.LabMedical;
import model.LabTest;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Linh Vo on 9/19/2018.
 */

@Transactional
@Service
public class LabTestService {
    @Autowired
    SessionFactory sessionFactory;

    public int addLabTest(LabTest labTest) {
        LabTest labTestDB = new LabTest();
        labTestDB.setLabMedicals(labTest.getLabMedicals());
        labTestDB.setDate(labTest.getDate());
        labTestDB.setTime(labTest.getTime());

        for (LabMedical labMedical : labTestDB.getLabMedicals()) {
            labMedical.setLabTest(labTestDB);
        }

        sessionFactory.getCurrentSession().save(labTestDB);
        return labTestDB.getId();
    }

    public List<LabTest> getAllLabTestPerVisit(int visitID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from LabTest as l where l.visitLog.id = :visitID");
        query.setInteger("visitID", visitID);
        return query.list();
    }

    public void updateLabTest(LabTest labTest) {
        LabTest labTestDB = getLabTest(labTest.getId());
        labTestDB.setTime(labTest.getTime());
        labTestDB.setDate(labTest.getDate());
        labTestDB.setLabMedicals(labTest.getLabMedicals());
        sessionFactory.getCurrentSession().update(labTestDB);
    }

    public void deleteLabTest(int LabTestId) {
        LabTest labTest = getLabTest(LabTestId);
        sessionFactory.getCurrentSession().delete(labTest);

    }

    public List<LabTest> getAllLabTest() {
        return sessionFactory.getCurrentSession().createQuery("from LabTest").list();
    }

    public LabTest getLabTest(int LabTestId) {
        return (LabTest) sessionFactory.getCurrentSession().get(LabTest.class, LabTestId);

    }


}
