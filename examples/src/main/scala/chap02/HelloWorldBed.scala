package chap02

import com.cra.figaro.language._
import com.cra.figaro.library.compound.If
import com.cra.figaro.algorithm.factored.VariableElimination

object HelloWorldFigaroBed {
	val wrongSideOfBed = Flip(0.5)//Apply(Flip(0.5), (b:Boolean) => if(b) "right"; else "wrong")
	val sunnyToday = Flip(0.2)
	//sunnyToday.setCondition((b:Boolean) => b)
	/*sunnyToday.setCondition(Chain(wrongSideOfBed, (s: String) =>
		if(s == "wrong") Flip(0.0)
		else Flip(0.2))
	)*/
	val greetingToday = If(wrongSideOfBed, "Oh no, not again",
		If(sunnyToday,
			Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
			Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again")
		)
	)
	/*val greetingToday = If(sunnyToday,
			Select(0.6 -> "Hello, world!", 0.4 -> "Howdy, universe!"),
			Select(0.2 -> "Hello, world!", 0.8 -> "Oh no, not again")
		)*/
	val sunnyTomorrow = If(sunnyToday, Flip(0.8), Flip(0.05))
	val greetingTomorrow = If(sunnyTomorrow,
		Select(0.5 -> "Hello, world!", 0.5 -> "Howdy, universe!"),
		Select(0.1 -> "Hello, world!", 0.9 -> "Oh no, not again"))

	def main(args: Array[String]) {
		println("We got out of the wrong side of the bed with probability " +
			VariableElimination.probability(wrongSideOfBed, true))

		val result = VariableElimination.probability(greetingTomorrow, "Hello, world!")
	    println("If today's greeting is \"Hello, world!\", " +
	            "tomorrow's greeting will be \"Hello, world!\" " +
	            "with probability " + result + ".")
	}
}