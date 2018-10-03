package service;

import model.Problem;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProblemService {

    @Autowired
    SessionFactory sessionFactory;

    public List<Problem> getAllProblems() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Problem");
        return query.list();
    }

    public Problem findProblemByID(int problemID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Problem where id = :id");
        query.setInteger("id", problemID);
        return (Problem) query.uniqueResult();
    }

    public List<Problem> getAllProblemsPerVisit(int visitID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Problem as p where p.visitLog.id = :visitID");
        query.setInteger("visitID", visitID);
        return query.list();
    }

    public void updateProblem(Problem problem, int problemID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Problem where id = :id");
        query.setInteger("id", problemID);

        Problem problemDB = (Problem) query.uniqueResult();
        problemDB.setProblem(problem.getProblem());

        sessionFactory.getCurrentSession().update(problemDB);
    }

    public void deleteProblem(int problemID) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Problem where id = :id");
        query.setInteger("id", problemID);
        Problem problem = (Problem) query.uniqueResult();
        sessionFactory.getCurrentSession().delete(problem);
    }

}
