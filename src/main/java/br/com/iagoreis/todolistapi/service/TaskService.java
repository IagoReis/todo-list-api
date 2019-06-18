package br.com.iagoreis.todolistapi.service;

import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;

public interface TaskService {
	
	Task insert(TaskRequest taskRequest);
	
}
