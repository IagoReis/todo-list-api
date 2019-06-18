package br.com.iagoreis.todolistapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
	
}
