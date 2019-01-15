package services

import com.datastax.driver.core.Session

trait StudentService {

  def add(query: String, session: Session): String

  def update(query: String, session: Session): String

  def delete(query: String, session: Session): String

  def displayResult(selectQuery: String, session: Session): List[entities.Student]
}
