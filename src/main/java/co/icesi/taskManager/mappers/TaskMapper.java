package co.icesi.taskManager.mappers;

import co.icesi.taskManager.dtos.TaskDto;
import co.icesi.taskManager.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskDto taskToTaskDto(Task task);

    Task taskDtoToTask(TaskDto task);
}

