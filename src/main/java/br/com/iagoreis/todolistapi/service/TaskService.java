package br.com.iagoreis.todolistapi.service;

import java.util.Collection;
import java.util.UUID;

import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;

public interface TaskService {
	
	Task insert(TaskRequest taskRequest);
	
	Task update(UUID id, TaskRequest taskRequest);
	
	Task getById(UUID id) throws Exception;
	
	Collection<Task> getAll();
}
