// 4.2 This or That and Nothing Else: Sealed Traits

import java.util.Date

sealed trait Visitor {
  def id: String

  def createdAt: Date

  def age: Long = new Date().getTime() - createdAt.getTime()
}

final case class User(id: String,
                      email: String,
                      createdAt: Date = new Date()) extends Visitor

final case class Anonymous(id: String,
                           createdAt: Date = new Date()
                          ) extends Visitor

def missingCase(v: Visitor) =
  v match {
    case User(_, _, _) => "Got a user"
  }

val testUser = new User("testId", "test@email.com")
missingCase(testUser)
