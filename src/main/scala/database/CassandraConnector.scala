package database
import com.datastax.driver.core.{Cluster, ResultSet, Session}
trait CassandraConnector {
def getCasssandraBuilder: Cluster = {
    Cluster.builder()
      .addContactPoint("localhost")
      .withPort(9042)
      .build()
  }
  def getSession(keySpaceName: String, cluster: Cluster): Session = {
    cluster.connect(keySpaceName)
  }
  def executeQuery(session: Session, query: String): ResultSet = {
    session.execute(query)
  }
}
object CassandraConnector extends CassandraConnector

