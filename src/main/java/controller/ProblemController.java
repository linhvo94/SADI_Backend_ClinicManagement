package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.ProblemService;
import store.ProblemStore;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ProblemController {

    @Autowired
    private ProblemStore problemStore;

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="problems", method = RequestMethod.GET)
    public List<Problem> getAllProblems() {
        return problemStore.getAllProblems();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="problems/findbyid/{id}", method = RequestMethod.GET)
    public Problem findProblemByID(@PathVariable int id) {
        return problemStore.findProblemByID(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path = "problems/findbyvisitID/{visitID}", method = RequestMethod.GET)
    public List<Problem> getAllProblemsPerVisit(@PathVariable("visitID") int visitID){
        return problemStore.getAllProblemsPerVisit(visitID);
    }


    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="problems/{id}", method = RequestMethod.PUT)
    public void updateProblem(@RequestBody Problem problem, @PathVariable int id) {
        problemStore.updateProblem(problem, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN','ROLE_NURSE')")
    @RequestMapping(path="problems/{id}", method = RequestMethod.DELETE)
    public void deleteProblem(@PathVariable int id) {
        problemStore.deleteProblem(id);
    }

}
