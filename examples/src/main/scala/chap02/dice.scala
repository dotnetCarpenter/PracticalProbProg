package chap02

import com.cra.figaro.library.atomic.discrete.{FromRange}
import com.cra.figaro.language.{Flip, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination

object Dice {
	val die1 = FromRange(1, 7)
	val die2 = FromRange(1, 7)
	val total = Apply(die1, die2, (a:Int, b:Int) => a + b)

	def predictTotal(n:Int) = {
		VariableElimination.probability(total, n)
	}
	def predict6() = {
		total.addCondition((n:Int) => n > 8)
		val result = VariableElimination.probability(die1, 6)
		total.removeConditions()
		result
	}
	def predictMonopolyGoToJail() = {
		def doubles = {
			die1 === die2
		}
		val jail = doubles && doubles && doubles
		VariableElimination.probability(jail, true)
	}

	def main(args: Array[String]) {
		println( "Probability of rolling 11 is " + predictTotal(11) )

		println( "Probability of rolling a 6 with the first die when the total is above 8 is " +
			predict6() )

		println( "Probability of rolling three doubles in a row is " + predictMonopolyGoToJail() )
	}
}
