package br.com.iagoreis.todolistapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.iagoreis.todolistapi.config.TaskStatus;
import br.com.iagoreis.todolistapi.dto.TaskRequest;
import br.com.iagoreis.todolistapi.model.Task;
import br.com.iagoreis.todolistapi.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoListApiApplicationTests {
	
	@Autowired
	TaskService taskService;
	
	@Test
	public void insertTask() {
		
		final var taskRequest = new TaskRequest("abc", TaskStatus.COMPLETED);
		
		final Task taskSaved = taskService.insert(taskRequest);
		
		assertNotNull(taskSaved);
		assertEquals(taskRequest.getDescription(), taskSaved.getDescription());
		assertEquals(taskRequest.getStatus(), taskSaved.getStatus());
	}

}
