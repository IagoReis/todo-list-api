package br.com.iagoreis.todolistapi.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.utils.UUIDs;

import br.com.iagoreis.todolistapi.dao.TaskDao;
import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;
import br.com.iagoreis.todolistapi.service.TaskService;

@Component
public class TaskServiceImpl implements TaskService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public Task insert(final TaskRequest taskRequest) {
		
		logger.info("Iniciando insercao de Task");
		
		logger.info("Task recebida para insercao {}", taskRequest);
		
		final var task = new Task();
		
		task.setId(UUIDs.timeBased());
		task.setStatus(taskRequest.getStatus());
		task.setDescription(taskRequest.getDescription());
		
		final Task taskSaved = taskDao.save(task);
		
		logger.info("Task inserida com sucesso: {}", taskSaved);
		
		return taskSaved;
	}

	@Override
	public Task update(final UUID id, final TaskRequest taskRequest) throws Exception {
		
		logger.info("Iniciando alteracao de Task");
		
		logger.info("Task recebida para alteracao {}", taskRequest);
		
		final Optional<Task> taskOptional = taskDao.findById(id);
		
		if (taskOptional.isEmpty()) {
			logger.error("Task id {} nao encontrada", id);
			throw new Exception("Task não encontrada");
		}
		
		final Task task = taskOptional.get();
		
		task.setDescription(taskRequest.getDescription());
		task.setStatus(taskRequest.getStatus());
		
		final Task taskUpdated = taskDao.save(task);
		
		logger.info("Task alterada com sucesso: {}", taskUpdated);
		
		return taskUpdated;
	}

	@Override
	public Task getById(final UUID id) throws Exception {
		
		logger.info("Iniciando busca de Task por id");
		
		final Optional<Task> taskOptional = taskDao.findById(id);
		
		if (taskOptional.isEmpty()) {
			logger.error("Task id {} nao encontrada", id);
			throw new Exception("Task não encontrada");
		}
		
		logger.info("Task retornada com sucesso: {}", taskOptional.get());
		
		return taskOptional.get();
	}
	
	@Override
	public Boolean delete(final UUID id) throws Exception {
		
		logger.info("Iniciando exclusao de Task");
		
		logger.info("Id da Task recebida para exclusao {}", id);
		
		final Optional<Task> taskOptional = taskDao.findById(id);
		
		if (taskOptional.isEmpty()) {
			logger.error("Task id {} nao encontrada", id);
			throw new Exception("Task não encontrada");
		}
		
		taskDao.delete(taskOptional.get());
		
		logger.info("Task excluida com sucesso: {}", id);
		
		return Boolean.TRUE;
	}

	@Override
	public Collection<Task> getAll() {
		logger.info("Iniciando busca de todas as Tasks");
		
		final Collection<Task> tasks = taskDao.findAll();
		
		logger.info("Tasks retornadas com sucesso: {}", tasks);
		
		return tasks;
	}
	
}
