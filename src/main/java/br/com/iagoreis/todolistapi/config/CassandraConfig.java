package br.com.iagoreis.todolistapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {
	
	@Value("${spring.data.cassandra.keyspace}")
	private String keyspace;
	
	@Value("${spring.data.cassandra.port}")
	private Integer port;
	
	@Value("${spring.data.cassandra.host}")
	private String host;

	@Override
	protected String getKeyspaceName() {
		return keyspace;
	}

	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(host);
		cluster.setPort(port);
		cluster.setJmxReportingEnabled(false);
		return cluster;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

}
