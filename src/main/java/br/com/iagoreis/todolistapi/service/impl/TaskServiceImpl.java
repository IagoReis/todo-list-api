package br.com.iagoreis.todolistapi.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

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

	@Override
	public Task update(UUID id, TaskRequest taskRequest) throws Exception {
		
		final Optional<Task> taskOptional = taskDao.findById(id);
		
		if (taskOptional.isEmpty()) {
			throw new Exception("Task não encontrada");
		}
		
		final Task task = taskOptional.get();
		
		task.setDescription(taskRequest.getDescription());
		task.setStatus(taskRequest.getStatus());
		
		final Task taskUpdated = taskDao.save(task);
		
		return taskUpdated;
	}

	@Override
	public Task getById(UUID id) throws Exception {
		
		final Optional<Task> taskOptional = taskDao.findById(id);
		
		if (taskOptional.isEmpty()) {
			throw new Exception("Task não encontrada");
		}
		
		return taskOptional.get();
	}
	
	@Override
	public Boolean delete(UUID id) throws Exception {
		
		final Optional<Task> taskOptional = taskDao.findById(id);
		
		if (taskOptional.isEmpty()) {
			throw new Exception("Task não encontrada");
		}
		
		taskDao.delete(taskOptional.get());
		
		return Boolean.TRUE;
	}

	@Override
	public Collection<Task> getAll() {
		
		final Collection<Task> tasks = taskDao.findAll();
		
		return tasks;
	}
	
}
