package co.icesi.taskManager.services.impl;

import co.icesi.taskManager.model.Task;
import co.icesi.taskManager.repositories.TaskRepository;
import co.icesi.taskManager.services.interfaces.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    @Override
    public Task updateTask(Task task) {
        Long id = task.getId();

        Task task2 = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task2.setName(task.getName());
        task2.setNotes(task.getNotes());
        task2.setDescription(task.getDescription());
        task2.setPriority(task.getPriority());

        return task2;

    }

    @Override
    public void deleteTask(long taskId) {
        Task taskToDelete = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found"));
        System.out.println("Deleting task with ID: " + taskId);

        taskRepository.delete(taskToDelete);
    }

    @Override
    public void assignTask(long taskId, long userId) {

    }

    @Override
    public void unassignTask(long taskId, long userId) {

    }

    @Override
    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Product not found"));
    }


    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }
}
