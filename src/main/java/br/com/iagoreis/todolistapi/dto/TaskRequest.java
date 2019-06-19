package br.com.iagoreis.todolistapi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import br.com.iagoreis.todolistapi.config.TaskStatus;

public class TaskRequest implements Serializable {

	private static final long serialVersionUID = 1653264968758357416L;
	
	@NotNull
	private String description;
	
	@NotNull
	private TaskStatus status;

	public TaskRequest(String description, TaskStatus status) {
		this.description = description;
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TaskRequest [description=" + description + ", status=" + status + "]";
	}

}
