package org.github.sguzman.scala.lang.hq9plus

/**
  * @author Salvador Guzman - sguzman
  * @group ScHQ9+
  * @version org.github.sguzman.scala.lang.hq9plus.impl
  *
  * @note HQ9+ intepreter
  *
  * @since 5/22/16 9:48 PM
  */
object Main {
  /**
    * Damn it all!
    */
  def damnItAll() = System.exit(1)

  /**
    * Experienced a fatal exception
    */
  def noooo(): Unit = {
    println("No.")
    throw new HQ9PlusException
  }

  /**
    * Faithfully represent counter
    */
  var counter = 0

  /**
    * Holds entire program in memory
    */
  var program: String = ""

  /**
    * Here are all the instructions!
    */

  /**
    * Prints hello world
    */
  def hello(): Unit = println("Hello, world!")

  /**
    * Print entire program
    */
  def q(): Unit = println(program.toString)

  /**
    * 99 bottles of beer on the wall
    *
    * @param n: Int - how many bottles?
    */
  def nine(n: Int = 99): Unit = {
    for(num <- n to 1 by -1) num match {
      case 1 =>
        println("1 bottle of beer on the wall")
        println("1 bottle of beer")
        println("Take one down and pass it around")
        println("No bottles of beer on the wall")
      case _: Int if num > 0 =>
        println(s"$num bottles of beer on the wall")
        println(s"$num bottles of beer")
        println("Take one down and pass it around")
        println(s"${num - 1} bottle${if (num == 2) "" else "s"} of beer on the wall")
      case _ => noooo()
    }
  }

  /**
    * Increment the counter
    */
  def incre(): Unit = counter += 1

  def parser(code: String): Unit = for (char <- program) char match {
    case 'H' | 'h' => hello()
    case 'Q' | 'q' => q()
    case '9' => nine()
    case '+' => incre()
    case _ => noooo()
  }

  /**
    * Handle input file
    *
    * @param args: Array[String]
    */
  def main(args: Array[String]) = {
    if (args.length < 1) {
      println("usage: scala Main <file>")
      damnItAll()
    }

    try {
      /**
        * Get entire source code in memory
        */
      val file = scala.io.Source.fromFile(args(0))
      try {
        program = file.mkString
      } finally {
        file.close()
      }

      /** Parse the program */
      parser(program)
    } catch {
      case _: HQ9PlusException => damnItAll()
    }
  }
}