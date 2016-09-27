package chap02

import com.cra.figaro.language.{Flip}
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination

object EqualTest {
	val x = Flip(0.4)
	val y = Flip(0.4)

	def xz():Double = {
		val z = x
		val w = x === z
		VariableElimination.probability(w, true)
	}

	def zy():Double = {
		val z = y
		val w = x === z // 0.16 + 0.36 = 0.52
		VariableElimination.probability(w, true)
	}

	def main(args: Array[String]) {
		println(xz())
		println(zy())
	}
}