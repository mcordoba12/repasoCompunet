package co.icesi.taskManager.controllers.api;

import org.springframework.http.ResponseEntity;

import co.icesi.taskManager.dtos.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface TaskController {

    public ResponseEntity<?> findAllTask();

    public ResponseEntity<?> addTask(TaskDto dto);

    public ResponseEntity<?> updateTask(TaskDto dto, long id);

    public ResponseEntity<?> deleteTask(long id);

    public ResponseEntity<?> findById(long id);

}
