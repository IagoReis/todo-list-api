package br.com.iagoreis.todolistapi.service;

import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;

import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;

public interface TaskService {
	
	Task insert(TaskRequest taskRequest);
	
	Task update(UUID id, TaskRequest taskRequest) throws Exception;
	
	Task get(UUID id) throws Exception;
	
	Collection<Task> getAll();

	Boolean delete(@Valid UUID id) throws Exception;
}
