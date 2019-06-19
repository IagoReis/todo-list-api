package br.com.iagoreis.todolistapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Ignore;
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
	
	@Ignore
	@Test
	public void insertTask() {
		
		final var taskRequest = new TaskRequest("Teste Insert", TaskStatus.PENDING);
		
		final Task taskSaved = taskService.insert(taskRequest);
		
		assertNotNull(taskSaved);
		assertEquals(taskRequest.getDescription(), taskSaved.getDescription());
		assertEquals(taskRequest.getStatus(), taskSaved.getStatus());
	}
	
	@Ignore
	@Test
	public void selectTask() throws Exception {
		
		final var taskRequest = new TaskRequest("Teste Select", TaskStatus.PENDING);
		
		final Task taskSaved = taskService.insert(taskRequest);
		
		final Task task = taskService.getById(taskSaved.getId());
		
		assertNotNull(task);
		assertEquals(task.getId(), taskSaved.getId());
		assertEquals(task.getDescription(), taskSaved.getDescription());
		assertEquals(task.getStatus(), taskSaved.getStatus());
	}
	
	@Ignore
	@Test
	public void selectTasks() {
		final Collection<Task> tasks = taskService.getAll();
		
		assertNotNull(tasks);
		assertFalse(tasks.isEmpty());
	}
	
	@Ignore
	@Test
	public void updateTask() throws Exception {
		
		final var taskRequest = new TaskRequest("Teste Before Update", TaskStatus.COMPLETED);
		
		final Task taskSaved = taskService.insert(taskRequest);
		
		taskRequest.setDescription("Teste After Update");
		taskRequest.setStatus(TaskStatus.PENDING);
		
		final Task taskUpdated = taskService.update(taskSaved.getId(), taskRequest);
		
		assertNotNull(taskUpdated);
		assertEquals(taskUpdated.getId(), taskSaved.getId());
		assertNotEquals(taskUpdated.getDescription(), taskSaved.getDescription());
		assertNotEquals(taskUpdated.getStatus(), taskSaved.getStatus());
		
	}
	
	@Ignore
	@Test
	public void deleteTask() throws Exception {
		
		final var taskRequest = new TaskRequest("Teste Delete", TaskStatus.COMPLETED);
		
		final Task taskSaved = taskService.insert(taskRequest);
		
		final Boolean taskDeleted = taskService.delete(taskSaved.getId());
		
		assertTrue(taskDeleted);
	}

}
