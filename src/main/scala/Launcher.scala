import database.CassandraConnector
import services.StudentService
import services.impl.StudentServiceImpl

import scala.util.{Failure, Success, Try}

object Launcher extends App {
  val student: StudentService = new StudentServiceImpl
  val keyspace = "first_keyspace"
  val tableName = "first_table"
  val selectQuery = s"select * from $tableName"
  val insertQuery = s"insert into $keyspace.$tableName(id,age,name) values(1,24,'hasan')"
  val updateQuery = s"update $keyspace.$tableName set age=23 where id=2"
  val deleteQuery = s"delete from $keyspace.$tableName where id=2"
  Try {
    CassandraConnector.getCasssandraBuilder
  } match {
    case Success(cluster) =>
      val session = CassandraConnector.getSession(keyspace, cluster)
      //student.add(insertQuery,session)
      //student.delete(deleteQuery,session)
      // student.update(updateQuery,session)
      println(student.displayResult(selectQuery, session))

      session.close()
      cluster.close()
    case Failure(exception) => println("Unable to Connect to Cassandra" + exception)
  }
}