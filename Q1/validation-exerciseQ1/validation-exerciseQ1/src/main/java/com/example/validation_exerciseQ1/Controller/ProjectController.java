package com.example.validation_exerciseQ1.Controller;

import com.example.validation_exerciseQ1.Api.ApiResponse;
import com.example.validation_exerciseQ1.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@RequestBody @Valid Project project , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }//add project (create)


    @GetMapping("/display")
    public ResponseEntity<?> displayProjects(){
        return ResponseEntity.status(200).body(projects);
    }//display all project (Read)


    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateProject(@PathVariable int index ,@RequestBody @Valid Project project , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(index >= projects.size() || index < 0){
            return ResponseEntity.status(400).body("project not found -this msg by updateProject-");
        }

        projects.set(index,project);
        return ResponseEntity.status(200).body(new ApiResponse("Project update successfully"));
    }//update project (update)


    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteProject(@PathVariable int index){
        if(index < 0 || index >= projects.size()){
            return ResponseEntity.status(400).body("project not found -this msg by deleteProject-");
        }
        projects.remove(index);
        return ResponseEntity.status(200).body("Project deleted successfully");
    }//delete project (delete)


    @PutMapping("change/{index}/{status}")
    public ResponseEntity<?> changeProjectStatus(@PathVariable int index , @PathVariable String status){

        if(index < 0 || index >= projects.size()){
            return ResponseEntity.status(400).body("Project not found -this msg by changeProjectStatus- ");
        }

        if(!status.equalsIgnoreCase("not started") &&
           !status.equalsIgnoreCase("in progress") &&
           !status.equalsIgnoreCase("completed")){
            return ResponseEntity.status(400).body("status should be : not started / in progress or completed only");
        }//checking the status

        if(projects.get(index).getStatus().equalsIgnoreCase("completed")){
            return ResponseEntity.status(400).body("Status can not changed after completion");
        }//if current status is completed then it can not be changed

        if(projects.get(index).getStatus().equalsIgnoreCase("not started") &&
           status.equalsIgnoreCase("completed")){
            return ResponseEntity.status(400).body("Status can not change directly to completed");
        }//if current status is not started then it can not be change to completed directly

        if(projects.get(index).getStatus().equalsIgnoreCase("in progress") &&
                status.equalsIgnoreCase("not started")){
            return ResponseEntity.status(400).body("in progress status can not change to not started");
        }//if current status is in progress it can not be changed to not started

        projects.get(index).setStatus(status);
        return ResponseEntity.status(200).body("Project status changed successfully");

    }//change project status (extra endpoint)


    @GetMapping("/search/{title}")
    public ResponseEntity<?> searchByTitle(@PathVariable String title){
        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(project);
            }
        }
        return ResponseEntity.status(400).body("Project not found -this msg by searchByTitle ");
    }//search for project by title (extra endpoint)

    @GetMapping("/company/{companyName}")
    public ResponseEntity<?> displayAllProjectByCompanyName(@PathVariable String companyName){
        ArrayList<Project> result = new ArrayList<>();

        for(Project project : projects){
          if(project.getCompanyName().equalsIgnoreCase(companyName)) {
              result.add(project);
          }
        }
        return ResponseEntity.status(200).body(result);
    }//display all project with specific company name (extra endpoint)










}
