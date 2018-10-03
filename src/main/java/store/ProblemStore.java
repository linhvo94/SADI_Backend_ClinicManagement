package store;


import model.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import service.ProblemService;

import java.util.List;

@Transactional
@Repository
public class ProblemStore {

    @Autowired
    ProblemService problemService;

    public List<Problem> getAllProblems() {
        return problemService.getAllProblems();
    }

    public Problem findProblemByID(int problemID) {
        return problemService.findProblemByID(problemID);
    }

    public List<Problem> getAllProblemsPerVisit(int visitID) {
        return problemService.getAllProblemsPerVisit(visitID);
    }

    public void updateProblem(Problem problem, int problemID) {
        problemService.updateProblem(problem, problemID);
    }

    public void deleteProblem(int problemID) {
        problemService.deleteProblem(problemID);
    }
}
