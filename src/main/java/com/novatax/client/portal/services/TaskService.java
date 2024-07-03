package com.novatax.client.portal.services;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.novatax.client.portal.dto.TaskDTO;
import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Tasks;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.JobRepository;
import com.novatax.client.portal.repository.TasksRepository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class TaskService {

    @Autowired
    private TasksRepository taskRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private JobRepository jobRepository;

    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Tasks> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByJobId(Integer jobId) {
        List<Tasks> tasks = taskRepository.findByJob_Id(jobId);
        return tasks.stream().map(this::convertToTaskDTO).collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public List<TaskDTO> getTasksByClientId(int clientId) {
        List<Tasks> tasks = taskRepository.findByClient_Id(clientId);
        return tasks.stream().map(this::convertToTaskDTO).collect(Collectors.toList());
    }
    
    private TaskDTO convertToTaskDTO(Tasks task) {
        // Ensure the lazy-loaded job is initialized
        Hibernate.initialize(task.getJob());

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setName(task.getName());
        taskDTO.setType(task.getType());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setClientId(task.getClient().getId());
        taskDTO.setPriority(task.getPriority());
        taskDTO.setNotes(task.getNotes());
        taskDTO.setDue_Date(task.getDue_date());
        taskDTO.setJobId(task.getJob().getId()); // Ensure jobId is set here

        return taskDTO;
    }
//    @Transactional
//    public Tasks createTask(Tasks task) {
//        return taskRepository.save(task);
//    }
    
    public Tasks createTask(TaskDTO taskDTO) {
  
        Clients client = clientRepository.findById(taskDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Tasks task = new Tasks();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setType(taskDTO.getType());
        task.setPriority(taskDTO.getPriority());
        task.setDue_date(taskDTO.getDue_Date());
        task.setStatus(taskDTO.getStatus());
        task.setJob(jobRepository.findById(taskDTO.getJobId()).orElse(null));
        task.setClient(client);
        task.setNotes(taskDTO.getNotes());

        return taskRepository.save(task);
    }
    
    public Tasks updateTask(Integer id, TaskDTO taskDTO) throws ParseException {
        Optional<Tasks> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            throw new IllegalArgumentException("Task not found");
        }
        return saveOrUpdateTask(optionalTask.get(), taskDTO);
    }
    
    public Tasks saveOrUpdateTask(Tasks task, TaskDTO taskDTO){
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setType(taskDTO.getType());
        task.setStatus(taskDTO.getStatus());
        task.setDue_date(taskDTO.getDue_Date());
        task.setUpdated_at(new Date());
        task.setNotes(taskDTO.getNotes());
        task.setPriority(taskDTO.getPriority());
        Clients client = clientRepository.findById(taskDTO.getClientId())
        		.orElseThrow(() -> new RuntimeException("Client not found"));

        task.setClient(client);

        return taskRepository.save(task);
    }
    
    
    @Transactional
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    // Other business methods...
}