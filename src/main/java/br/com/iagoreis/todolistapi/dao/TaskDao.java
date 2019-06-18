package br.com.iagoreis.todolistapi.dao;


import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import br.com.iagoreis.todolistapi.model.Task;

@Repository
public interface TaskDao extends CassandraRepository<Task, UUID> {

}
