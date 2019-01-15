package services.impl

import com.datastax.driver.core.{ResultSet, Session}
import database.CassandraConnector
import entities.Student
import services.StudentService

import scala.collection.JavaConversions

class StudentServiceImpl extends StudentService {

  def add(query: String, session: Session): String = {
    println("\n\nInserting one Record")
    CassandraConnector.executeQuery(session, query)
    "record inserted"
  }

  def update(query: String, session: Session): String = {
    println("\n\nUpdating Inserted Record")
    CassandraConnector.executeQuery(session, query)
    "record updated"
  }

  def delete(query: String, session: Session): String = {
    println("\n\nDeleting Inserted Record")
    CassandraConnector.executeQuery(session, query)
    "record deleted"
  }

  def displayResult(selectQuery: String, session: Session): List[entities.Student] = {
    val resultSet: ResultSet = CassandraConnector.executeQuery(session, selectQuery)
    val iterator = JavaConversions.asScalaIterator(resultSet.iterator)
    val records = iterator map { row =>
      Student(row.getInt("id"), row.getString("name"), row.getInt("age"))
    }
    records.toList
  }
}
