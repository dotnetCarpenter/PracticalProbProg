package chap02

import com.cra.figaro.library.atomic.discrete.{FromRange}
import com.cra.figaro.language.{Flip, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination

object Dice {
	val die1 = FromRange(1, 7)
	val die2 = FromRange(1, 7)
	val total = Apply(die1, die2, (a:Int, b:Int) => a + b)

	def predict(n:Int):Double = {
		VariableElimination.probability(total, n)
	}

	def main(args: Array[String]) {
		println( predict(11) )
	}
}
