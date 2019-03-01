package Application

import scala.annotation.tailrec
import scala.io.StdIn

class Hangman {

  @tailrec
  final def getUserInputUntilValid(validItems: List[String]): String = {
    val input = StdIn.readLine.trim.toUpperCase

    if (validItems.contains(input)) {
      input
    }
    else {
      println(s"Please choose one of the following:\n${validItems.mkString(",azz")}")
      getUserInputUntilValid(validItems)
    }
  }

  @tailrec
  final def playGame(answer: String, lives: Int = 11, lettersChosen: List[Char]): Either[Unit, Hangman]= {
    val userInput = getUserInputUntilValid(('A' to 'Z')
                         .map(_.toString).toList)
                         .trim
                         .head

    val answerSet = answer.toCharArray.toSet
    val newLetterChosen = (userInput :: lettersChosen).sorted.distinct

    if (answerSet.intersect((userInput :: lettersChosen).toSet) == answerSet) {
      Left(println("You won! ※\\(^o^)/※"))
    }

    else if (lettersChosen.contains(userInput)) {
      Left(println("Invalid choice. You already picked that letter.\nTry again."))
      playGame(answer, lives, newLetterChosen)
    }

    else if (answer.contains(userInput) && lives > 0) {
      println("Correct. Next answer?\n")
      println(s"Letters chosen: $newLetterChosen")
      printScreen(answer, newLetterChosen)
      playGame(answer, lives, newLetterChosen)
    }

    else if (lives < 1 ) Left(println("You lost (╯°□°）╯︵ ┻━┻)") )

    else {
      print(s"Incorrect. $lives lives remaining.\n")
      println(s"Letters chosen: $newLetterChosen")
      playGame(answer, lives - 1, newLetterChosen)
    }
  }

  def printScreen(answer: String, lettersChosen: List[Char]): Unit =
    println(
        answer.map{
          case correctLetter if lettersChosen.contains(correctLetter) => correctLetter
          case _ => "_"
        }.mkString(" ")
    )
}
