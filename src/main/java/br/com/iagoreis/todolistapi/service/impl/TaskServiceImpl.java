package br.com.iagoreis.todolistapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.utils.UUIDs;

import br.com.iagoreis.todolistapi.dao.TaskDao;
import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;
import br.com.iagoreis.todolistapi.service.TaskService;

@Component
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public Task insert(TaskRequest taskRequest) {
		final var task = new Task();
		
		task.setId(UUIDs.timeBased());
		task.setStatus(taskRequest.getStatus());
		task.setDescription(taskRequest.getDescription());
		
		final Task taskSaved = taskDao.save(task);
		
		return taskSaved;
	}
	
}
