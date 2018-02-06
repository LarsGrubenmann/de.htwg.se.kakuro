package de.htwg.se.kakuro.model.fieldComponent.FieldImpl

import de.htwg.se.kakuro.model.fieldComponent.{ CellInterface, SumInterface }
import org.apache.logging.log4j.Logger
import org.apache.logging.log4j.LogManager

import scala.swing.Reactor

case class Sum(sumValue: Int, members: Set[Cell]) extends SumInterface with Reactor {
  val logger: Logger = LogManager.getLogger(this.getClass.getName)

  def this() = this(0, Set[Cell]())

  def current: Int = members.foldLeft(0)(_ + _.value)

  def isValid: Boolean = {
    sumValue >= current
  }

  def isSolved: Boolean = {
    sumValue == current
  }

  def getCandidates: Set[Int] = { (1 to 9).toSet.&~(members.map(_.value)).filter(_ <= sumValue - current) }

}
