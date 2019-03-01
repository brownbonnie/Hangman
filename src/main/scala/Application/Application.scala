package Application

object Application {

  def main(args: Array[String]): Unit = {

    val answer = "ANT"

    println(
      "┌───────────────────────────────┐\n" +
      "│      Welcome to Hangman!      │\n" +
      "│     Please pick a letter.     │\n" +
      "└───────────────────────────────┘\n"
    )

    val hm = new Hangman()
    hm.printScreen(answer, Nil)
    hm.playGame(answer, lettersChosen = Nil)

  }
}
