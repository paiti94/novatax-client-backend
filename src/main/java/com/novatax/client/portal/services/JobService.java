package com.novatax.client.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novatax.client.portal.dto.JobDTO;
import com.novatax.client.portal.dto.TaskDTO;
import com.novatax.client.portal.entities.Clients;
import com.novatax.client.portal.entities.Job;
import com.novatax.client.portal.entities.Tasks;
import com.novatax.client.portal.entities.Users;
import com.novatax.client.portal.repository.ClientRepository;
import com.novatax.client.portal.repository.JobRepository;
import com.novatax.client.portal.repository.TasksRepository;
import com.novatax.client.portal.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.ParseException;

@Service
@Transactional
public class JobService {

    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private TaskService taskService;
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public JobDTO getJobById(Integer id) {
        
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            JobDTO jobDTO = convertToJobDTO(job);

            return jobDTO;
        } else {
            // Handle the case where the job is not found
            throw new RuntimeException("Job not found with id: " + id);
        }
    }
    
    private JobDTO convertToJobDTO(Job job) {
        // Convert Job entity to JobDTO
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setName(job.getName());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setUrgency(job.getUrgency());
        jobDTO.setRepeat(job.getRepeat());
        jobDTO.setStart_Date(job.getStart_Date());
        jobDTO.setDue_Date(job.getDue_Date());
        jobDTO.setStatus(job.getStatus());
        jobDTO.setTags(job.getTags());
        jobDTO.setComments(job.getComments());
        jobDTO.setFiles(job.getFiles());
        jobDTO.setAssignedTo(job.getAssignedTo());
        jobDTO.setAssignedBy(job.getAssignedBy());
        jobDTO.setClient(job.getClient());

        // Convert tasks
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Tasks task : job.getTasks()) {
            TaskDTO taskDTO = convertToTaskDTO(task);
            taskDTOs.add(taskDTO);
        }
        jobDTO.setTasks(taskDTOs);

        return jobDTO;
    }

    private TaskDTO convertToTaskDTO(Tasks task) {
        // Convert Task entity to TaskDTO
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
    
    public List<Job> getJobByClientId(int clientId) {
        return jobRepository.findByClient_Id(clientId);
    }

    @Transactional
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Transactional
    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }
    
    public Job createJob(JobDTO jobDto) {
        Job job = new Job();
        List<Tasks> tasks = new ArrayList<Tasks>();
        job.setName(jobDto.getName());
        job.setDescription(jobDto.getDescription());
        job.setUrgency(jobDto.getUrgency());
        job.setRepeat(jobDto.getRepeat());
        job.setStart_Date(jobDto.getStart_Date());
        job.setDue_Date(jobDto.getDue_Date());
        job.setStatus(jobDto.getStatus());
        job.setTags(jobDto.getTags());
        job.setComments(jobDto.getComments());
        job.setFiles(jobDto.getFiles());
        job.setTasks(null);

        Users assignedTo = userRepository.findById(jobDto.getAssignedTo().getId())
                .orElseThrow(() -> new RuntimeException("AssignedTo user not found"));
        Users assignedBy = userRepository.findById(jobDto.getAssignedBy().getId())
                .orElseThrow(() -> new RuntimeException("AssignedBy user not found"));
        
        Clients client = clientRepository.findById(jobDto.getClient().getId())
        		.orElseThrow(() -> new RuntimeException("Client not found"));
        if(jobDto.getTasks()!=null) {
        	if(!jobDto.getTasks().isEmpty()) {
        		jobDto.getTasks().forEach(t -> {
            		Tasks task = taskService.createTask(t);
            		tasks.add(task);
            	});
        	}
        }
        job.setAssignedTo(assignedTo);
        job.setAssignedBy(assignedBy);
        job.setClient(client);
        job.setTasks(tasks);

        return jobRepository.save(job);
    }
    
    public Job updateJob(Integer id, JobDTO jobDTO) throws ParseException {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (!optionalJob.isPresent()) {
            throw new IllegalArgumentException("Job not found");
        }
        return saveOrUpdateJob(optionalJob.get(), jobDTO);
    }
    
    @Transactional
    private Job saveOrUpdateJob(Job job, JobDTO jobDTO){
        
        job.setName(jobDTO.getName());
        job.setDescription(jobDTO.getDescription());
        job.setUrgency(jobDTO.getUrgency());
        job.setRepeat(jobDTO.getRepeat());
        job.setStart_Date(jobDTO.getStart_Date());
        job.setDue_Date(jobDTO.getDue_Date());
        job.setStatus(jobDTO.getStatus());
        job.setTags(jobDTO.getTags());
        job.setComments(jobDTO.getComments());
        job.setFiles(jobDTO.getFiles());
        Users assignedTo = userRepository.findById(jobDTO.getAssignedTo().getId())
                .orElseThrow(() -> new RuntimeException("AssignedTo user not found"));
        Users assignedBy = userRepository.findById(jobDTO.getAssignedBy().getId())
                .orElseThrow(() -> new RuntimeException("AssignedBy user not found"));
        
        Clients client = clientRepository.findById(jobDTO.getClient().getId())
        		.orElseThrow(() -> new RuntimeException("Client not found"));
        
        job.setAssignedTo(assignedTo);
        job.setAssignedBy(assignedBy);
        job.setClient(client);
        
        // Process tasks
        List<Integer> taskDTOIds = jobDTO.getTasks().stream().map(TaskDTO::getId).collect(Collectors.toList());
        List<Tasks> currentTasks = job.getTasks() != null ? job.getTasks() : new ArrayList<>();

        // Update existing tasks and add new tasks
        for (TaskDTO taskDTO : jobDTO.getTasks()) {
            if (taskDTO.getId() != null) {
                // Update existing task
                Tasks existingTask = currentTasks.stream()
                        .filter(t -> t.getId().equals(taskDTO.getId()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Task not found: " + taskDTO.getId()));
                taskService.saveOrUpdateTask(existingTask, taskDTO);
            } else {
                // Create new task
                Tasks newTask = taskService.createTask(taskDTO);
                newTask.setJob(job);
                currentTasks.add(newTask);
            }
        }

        // Remove tasks that are no longer in the DTO
        List<Tasks> tasksToRemove = currentTasks.stream()
                .filter(t -> !taskDTOIds.contains(t.getId()))
                .collect(Collectors.toList());
        for (Tasks task : tasksToRemove) {
            currentTasks.remove(task);
            taskService.deleteTask(task.getId());
        }
        
        entityManager.flush();
        entityManager.clear();  
        
        job.setTasks(currentTasks);

        // Save the job again with updated tasks
        Job savedJob = jobRepository.save(job);

        return savedJob;
    }
    // Other business methods...
}