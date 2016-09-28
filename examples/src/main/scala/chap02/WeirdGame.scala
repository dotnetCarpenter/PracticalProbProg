package chap02

import com.cra.figaro.language.{Flip, Chain, Select}
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.library.atomic.discrete.{FromRange}

object WeirdGame {
	val spinner = Select(0.2 -> 4, 0.2 -> 6, 0.2 -> 8, 0.2 -> 12, 0.2 -> 20)

	def roll = {
		Chain(spinner, (n:Int) => FromRange(1, n + 1))
	}

	def main(args: Array[String]) {

	}
}