// 4.1 Traits
  // ->4.1.1 An Example of Traits
  import java.util.Date

  // V1
  case class Anonymous(id: String, createdAt: Date = new Date())
  case class User(
                   id: String,
                   email: String,
                   createdAt: Date = new Date()
                 )

  // V2
  trait Visitor {
    def id: String      // Unique id assigned to each user
    def createdAt: Date // Date this user first visited the site

    // How long has this visitor been around?
    def age: Long = new Date().getTime - createdAt.getTime
  }

  case class Anonymous(
                        id: String,
                        createdAt: Date = new Date()
                      ) extends Visitor

  case class User(
                   id: String,
                   email: String,
                   createdAt: Date = new Date()
                 ) extends Visitor

  def older(v1: Visitor, v2: Visitor): Boolean =
    v1.createdAt.before(v2.createdAt)

  older(Anonymous("1"), User("2", "test@example.com"))

  // -> 4.1.2 Traits Compared to Classes
  val anon = Anonymous("anon1")
  anon.createdAt
  anon.age
