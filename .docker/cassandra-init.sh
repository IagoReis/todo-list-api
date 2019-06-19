CQL="
CREATE KEYSPACE IF NOT EXISTS todolist WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes = true;
CREATE TABLE IF NOT EXISTS todolist.task (id uuid, description text, status text, PRIMARY KEY(id));"

until echo $CQL | cqlsh; do
  echo "cqlsh: Cassandra is unavailable to initialize - will retry later"
  sleep 2
done &

exec /docker-entrypoint.sh "$@"