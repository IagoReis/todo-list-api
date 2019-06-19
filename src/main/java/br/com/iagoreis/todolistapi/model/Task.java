package br.com.iagoreis.todolistapi.model;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import br.com.iagoreis.todolistapi.config.TaskStatus;

@Table
public class Task implements Serializable {

	private static final long serialVersionUID = -3148155520391005699L;
	
	@Id
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordering = Ordering.DESCENDING)
	private UUID id;

	@Column
	private String description;

	@Column
	private TaskStatus status;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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
		return "Task [id=" + id + ", description=" + description + ", status=" + status + "]";
	}

}
