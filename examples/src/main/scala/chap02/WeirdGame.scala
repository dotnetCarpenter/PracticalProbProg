package chap02

import com.cra.figaro.language.{Flip, Chain, Select}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.discrete.{FromRange}

object WeirdGame {
	val spinner = Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)

	def die = Chain(spinner, (n:Int) => FromRange(1, n + 1))

	def predict12SidedDie() = {
		VariableElimination.probability(spinner, 12)
	}

	def predict7() = {
		VariableElimination.probability(die, 7)
	}

	def predict12SidedDieGiven7() = {
		var counter = 0
		var total = Array():Array[Double]
		die.addCondition((n:Int) => {
			counter += 1
			total = total :+ n.asInstanceOf[Double]
			println(counter + ": " + n)
			if(counter == 20) {
				println(
					total
						.map(_ / 100 * counter)
						//.fold(0)(_ + _).asInstanceOf(Double)
				)
			}
			true
		})
		die.observe(7)
		val probability = VariableElimination.probability(die, 7)
		die.unobserve()
		probability
	}

	def main(args: Array[String]) {
		println( "Probability that you rolled a 12-sided die is " + predict12SidedDie() )

		println( "Probability that you rolled a 7 is " + predict7() )

		//die.observe(7)
		println( "Probability that you rolled a 12-sided die given that you rolled a 7 is " +
			predict12SidedDieGiven7() )
	}
}