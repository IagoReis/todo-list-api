package br.com.iagoreis.todolistapi.controller;

import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;
import br.com.iagoreis.todolistapi.service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	
	
	@PostMapping("/v1/task")
	public Task insert(@RequestBody @Valid TaskRequest taskRequest) {
		final Task task = taskService.insert(taskRequest);
		
		return task;
	}
	
	@GetMapping("/v1/task")
	public Collection<Task> getAll() {
		final Collection<Task> tasks = taskService.getAll();
		
		return tasks;
	}
	
	@GetMapping("/v1/task/{id}")
	public Task getById(@PathVariable @Valid UUID id) throws Exception {
		final Task task = taskService.getById(id);
		
		return task;
	}
	
	@PutMapping("/v1/task/{id}")
	public Task deleteById(@PathVariable @Valid UUID id, @RequestBody @Valid TaskRequest taskRequest) throws Exception {
		final Task task = taskService.update(id, taskRequest);
		
		return task;
	}
	
	@DeleteMapping("/v1/task/{id}")
	public Boolean deleteById(@PathVariable @Valid UUID id) throws Exception {
		Boolean isDeleted = taskService.deleteById(id);
		
		return isDeleted;
	}
	
}
