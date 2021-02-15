// 4.3.1 The Product Type Pattern
  //Product Type Pattern
  //If A has a b (with type B) and a c (with type C) write
  case class A(b: B, c: C)
  //or
  trait A {
    def b: B
    def c: C
  }

// 4.4 The Sum Type Pattern
  //Sum Type Pattern
  //If A is a B or C write
  sealed trait A
  final case class B() extends A
  final case class C() extends A

// 4.4.2 The Missing Patterns
  // We can say that A has a d of type D, where D is a B or C
  sealed trait D
  trait A {
      def d: D
    }

  final case class B() extends D
  final case class C() extends D

  //Alternatively we could implement this as A is a D or E, and D has a B and E has a C.
  // Again this translates directly into code
  sealed trait A
  final case class D(b: B) extends A
  final case class E(c: C) extends A
