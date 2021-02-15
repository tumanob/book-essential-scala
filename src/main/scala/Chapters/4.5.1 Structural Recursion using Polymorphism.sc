//4.5 Working With Data
  //4.5.1 Structural Recursion using Polymorphism

  // V1
  sealed trait A {
    def foo: String
  }
  final case class B() extends A {
    def foo: String =
      "It's B!"
  }
  final case class C() extends A {
    def foo: String =
      "It's C!"
  }

  val anA: A = B()
  anA.foo
  val anA: A = C()
  anA.foo

  // V2
  sealed trait A {
    def foo: String =
      "It's A!"
  }
  final case class B() extends A {
    override def foo: String =
      "It's B!"
  }
  final case class C() extends A {
    override def foo: String =
      "It's C!"
  }
  val anA: A = B()
  anA.foo
