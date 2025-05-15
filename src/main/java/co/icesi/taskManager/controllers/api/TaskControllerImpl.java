package co.icesi.taskManager.controllers.api;

import co.icesi.taskManager.dtos.TaskDto;
import co.icesi.taskManager.mappers.TaskMapper;
import co.icesi.taskManager.model.Task;
import co.icesi.taskManager.services.impl.TaskServiceImp;
import co.icesi.taskManager.services.interfaces.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskControllerImpl {

    @Autowired
    private TaskService taskService;


    @Autowired
    private TaskMapper taskMapper;


    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {

        List<TaskDto> dtos = taskService.getAllTask().stream()
                .map(taskMapper::taskToTaskDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping()
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto dto) {
        Task task = taskMapper.taskDtoToTask(dto);
        TaskDto savedDto = taskMapper.taskToTaskDto(taskService.createTask(task));
        return ResponseEntity.created(URI.create("/tasks/" + savedDto.getId())).body(savedDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(taskMapper.taskToTaskDto(task));
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto dto) {
        Task task = taskMapper.taskDtoToTask(dto);
        Task updat = taskService.updateTask(task);
        return ResponseEntity.ok(taskMapper.taskToTaskDto(updat));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }






}
