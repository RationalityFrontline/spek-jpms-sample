package com.sample.calculator

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

object CalculatorFeature : Spek({
    Feature("it can do addition") {
        Scenario("addition") {
            var result = 0
            val x = 1
            val y = 2

            When("adding two integers together") {
                result = Calculator.add(x, y)
            }

            Then("the result should be correct") {
                assertEquals(x + y, result)
            }
        }
    }
})