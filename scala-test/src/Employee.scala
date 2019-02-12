

class Employee(var name : String, var salary : Int){
  var age= 21;
}

object Main{
  
  def main(args: Array[String]) : Unit = 
  {
    var emily= new Employee("Emily", 100)
    println(emily.name);
    emily.salary=200;
    println(emily.salary)
    emily.age= 22
  }
}
