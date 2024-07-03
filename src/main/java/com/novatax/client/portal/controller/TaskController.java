package com.novatax.client.portal.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novatax.client.portal.dto.JobDTO;
import com.novatax.client.portal.dto.TaskDTO;
import com.novatax.client.portal.entities.Job;
import com.novatax.client.portal.entities.Tasks;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.TasksRepository;
import com.novatax.client.portal.repository.UserRepository;
import com.novatax.client.portal.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
    private TaskService taskService;

    @GetMapping
    public List<Tasks> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTaskById(@PathVariable Integer id) {
        Optional<Tasks> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<TaskDTO>> getTasksByJobId(@PathVariable Integer jobId) {
        List<TaskDTO> tasks = taskService.getTasksByJobId(jobId);
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tasks);
        }
    }
    
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<TaskDTO>> getTasksByClientId(@PathVariable int clientId){
    	  List<TaskDTO> tasks = taskService.getTasksByClientId(clientId);
          if (tasks.isEmpty()) {
              return ResponseEntity.notFound().build();
          } else {
              return ResponseEntity.ok(tasks);
          }
    }

    @PostMapping
    public Tasks createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Integer id, @RequestBody TaskDTO taskDTO) throws Exception {
    		Tasks task = taskService.updateTask(id, taskDTO);
            return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }

//	@Autowired
//	TasksRepository taskRepository;
//	
//	@Autowired
//	UserRepository userRepository;
//
//	 @GetMapping("/listall")
//	    public List<Tasks> index(){
//	        return taskRepository.findAll();
//	    }
//
//	    @GetMapping("/find/{id}")
//	    public Tasks show(@PathVariable String id){
//	        int taskId = Integer.parseInt(id);
//	        return taskRepository.findById(taskId)
//                    .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
//	    }
//	    
//	    @GetMapping("/mytask")
//	    public List<Tasks> getTaskDetails(@RequestParam String email) {
//	    	List<Users> users = userRepository.findByEmail(email).stream().collect(Collectors.toList());
//	    	if(users != null && !users.isEmpty()) {
//	    		List<Tasks> task = taskRepository.findByAssigneeId(users.get(0).getId());
//	    		if(task != null) {
//	    			return task;
//	    		}
//	    	}
//	    	return null;
//	    }
//
//
//	    @PostMapping("/create")
//	    public Tasks create(@RequestBody Map<String, String> body){
//	        String task_description = body.get("task_description");
//	        Integer assigned_to = Integer.parseInt(body.get("assigned_to"));
//	        Users assignee = userRepository.findById(assigned_to)
//	        		.orElseThrow(() -> new RuntimeException("Assignee not found with id: "+ assigned_to));
//	        Integer assigned_by = Integer.parseInt(body.get("assigned_by"));
//	        Users assigner = userRepository.findById(assigned_to)
//	        		.orElseThrow(() -> new RuntimeException("Assigner not found with id: "+ assigned_by));
//	        LocalDate due_date = LocalDate.parse(body.get("due_date"));
//	        Date sql_due_date= java.sql.Date.valueOf(due_date);
//	        String priority = body.get("priority");
//	        String status = body.get("status");
//	        String notes = body.get("notes");
//	        LocalDate localDate = LocalDate.now();
//			java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
//	        return taskRepository.save(new Tasks(task_description, assignee, assigner, sql_due_date, priority,status, notes,sqlDate, sqlDate ));
//	    }
//
//	    @PutMapping("/update/{id}")
//	    public Tasks update(@PathVariable String id, @RequestBody Map<String, String> body){
//	    	  int taskId = Integer.parseInt(id);
//	    	  Tasks task = taskRepository.findById(taskId)
//                      .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
//
//	    	  task.setTask_description(body.get("task_description"));
//	    	  Integer assigned_to = Integer.parseInt(body.get("assigned_to"));
//	    	  Users assignee = userRepository.findById(assigned_to)
//		        		.orElseThrow(() -> new RuntimeException("Assignee not found with id: "+ assigned_to));
//		        Integer assigned_by = Integer.parseInt(body.get("assigned_by"));
//		        Users assigner = userRepository.findById(assigned_to)
//		        		.orElseThrow(() -> new RuntimeException("Assigner not found with id: "+ assigned_by));
//		      task.setAssignee(assignee);
//		      task.setAssigner(assigner);
//		      LocalDate due_date = LocalDate.parse(body.get("due_date"));
//		      Date sql_due_date= java.sql.Date.valueOf(due_date);
//	    	  task.setDue_date(sql_due_date);
//	    	  task.setPriority(body.get("priority"));
//	    	  task.setStatus(body.get("status"));
//	    	  task.setNotes(body.get("notes"));
//	    	  task.setUpdated_at(sql_due_date);
//	    	  return taskRepository.save(task);
//	    }
//
//	    @DeleteMapping("/delete/{id}")
//	    public boolean delete(@PathVariable String id){
//	        int taskId = Integer.parseInt(id);
//	        taskRepository.deleteById(taskId);
//	        return true;
//	    }
}
