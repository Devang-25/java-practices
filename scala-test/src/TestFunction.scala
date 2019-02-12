

class TestFunction {
  def m(x: Int) = x + 1
  val f = (x: Int) => x + 1
  val f1 = (x: Int) => x + 1
  val simpleStr = () => "Hi There!"
  val evenOrOdd = (x: Int) => {
    if (x % 2 == 0) {
      "even"
    } else {
      "odd"
    }
  }

  def sumsal(elist: List[Employee]) = {
    var sum = 0
    elist.foreach(e => sum += e.salary)
    sum
  }
  
  def sumSalarySmart(elist: List[Employee], selection: Employee=> Boolean)= {
    var sum = 0
    elist.foreach(e => {
      if(selection(e)){
        sum += e.salary
      }
    })
    sum
  }
}

object Main1 {
  def main(args: Array[String]): Unit = {
    val t = new TestFunction();
    println(t.m(10))
    println(t.f(10))
    println(t.simpleStr())
    println(t.evenOrOdd(11))

    val elist = List(new Employee("emily", 1000), new Employee("emily", 2000));
    println("total salary " + t.sumsal(elist))
  }
}