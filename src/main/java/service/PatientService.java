package service;

import model.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
@Service
public class PatientService {

    @Autowired
    SessionFactory sessionFactory;

    public void createNewPatient(Patient patient) {
        Patient patientDB = new Patient(); //prepare object before pushing the database
        patientDB.setName(patient.getName());
        patientDB.setGender(patient.getGender());
        patientDB.setDob(patient.getDob());
        patientDB.setAddress(patient.getAddress());
        sessionFactory.getCurrentSession().save(patientDB);
    }

    public List<Patient> getAllPatients(){
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient");
        return query.list();
    }

    public Patient findPatientByID(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where id = :id");
        query.setInteger("id", id);
        return (Patient) query.uniqueResult();
    }

    public List<Patient> findPatientByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient as p where lower(p.name) like lower(:name)");
        query.setString("name", "%"+name+"%");
        return query.list();
    }

    public List<Patient> findPatientByBirthDate(String birthDate) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where cast(dob as date) = cast(:birthDate as date)");
        query.setString("birthDate", birthDate);
        return query.list();
    }

    public void deletePatient(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where id = :id");
        query.setInteger("id", id);
        Patient patient = (Patient) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(patient);
    }

    public void updatePatient(Patient patient, int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Patient where id = :id");
        query.setInteger("id", id);

        Patient patientDB = (Patient) query.uniqueResult();
        patientDB.setName(patient.getName());
        patientDB.setGender(patient.getGender());
        patientDB.setDob(patient.getDob());
        patientDB.setAddress(patient.getAddress());

        sessionFactory.getCurrentSession().update(patientDB);
    }

}
