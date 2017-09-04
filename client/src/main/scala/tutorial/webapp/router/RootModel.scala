package tutorial.webapp.router

import diode.Action

//Diode
case class Increase(amount: Int) extends Action

case class Decrease(amount: Int) extends Action

case object Reset extends Action

case class RootModel(counter: Counter)
case class Counter(i: Int)