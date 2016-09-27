package chap02

import com.cra.figaro.language._
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination

object HelloWorldFigaroBed {
	val sideOfBed = Flip(0.5)
	val sunnyToday = Flip(0.2)
	
	val greetingToday =
		If(sideOfBed,
			If(sunnyToday,
				Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
				Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again")
			),
			Select(1.0 -> "Oh no, not again")
		)
	/*greetingToday.setCondition((s:String) => {
			println("debugging: " + s)
			true
		})*/

	val sunnyTomorrow = If(sunnyToday, Flip(0.8), Flip(0.05))
	val greetingTomorrow =
		If(sideOfBed,
			If(sunnyTomorrow,
				Select(0.5 -> "Hello, world!", 0.5 -> "Howdy, universe!"),
				Select(0.1 -> "Hello, world!", 0.9 -> "Oh no, not again")
			),
			Select(1.0 -> "Oh no, not again")
		)

	def main(args: Array[String]) {
		println("The probability for:\n" +
			"Hello, world! greeting tomorrow is " + VariableElimination.probability(greetingTomorrow, "Hello, world!") + "\n" +
			"Howdy, universe! greeting tomorrow is " + VariableElimination.probability(greetingTomorrow, "Howdy, universe!") + "\n" +
			"Oh no, not again greeting tomorrow is " + VariableElimination.probability(greetingTomorrow, "Oh no, not again"))

		greetingToday.observe("Hello, world!")
		val helloWorld1 = VariableElimination.probability(greetingTomorrow, "Hello, world!")
		println("If today's greeting is \"Hello, world!\", " +
				"tomorrow's greeting will be \"Hello, world!\" " +
				"with probability " + helloWorld1 + ".")

		greetingToday.unobserve()
		sideOfBed.observe(false)
		val ohNo = VariableElimination.probability(greetingTomorrow, "Oh no, not again")
		println("If we got out of the wrong side of the bed today, " +
				"tomorrow's greeting will be \"Oh no, not again\" " +
				"with probability " + ohNo + ".")
	}
}