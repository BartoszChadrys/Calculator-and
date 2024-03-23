package com.example.calculator

import androidx.lifecycle.ViewModel
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

class CalculatorViewModel: ViewModel() {
    var previousNumber = ""
    var currentNumber = "0"
    var currentOperation = ""
    var isAfterClear = false
    var tvResults = ""

    fun isShowingArithmetic(): Boolean {
        if (tvResults == "*" || tvResults == "/" ||
            tvResults == "-" || tvResults == "+") {
            return true
        }
        return false
    }

    fun checkIfNumberIsInt(number: Double): String {
        if (number == number.toInt().toDouble()) {
            return number.toInt().toString()
        }
        return number.toString()
    }

    fun equals() {
        if (previousNumber == "" || currentNumber == "" || isShowingArithmetic() || currentOperation == "") {
            return
        }
        var result = 0.0
        when (currentOperation) {
            "+" -> result = previousNumber.toDouble() + currentNumber.toDouble()
            "-" -> result = previousNumber.toDouble() - currentNumber.toDouble()
            "*" -> result = previousNumber.toDouble() * currentNumber.toDouble()
            "/" -> result = previousNumber.toDouble() / currentNumber.toDouble()
            "^" -> result = previousNumber.toDouble().pow(currentNumber.toDouble())
        }
        currentNumber = checkIfNumberIsInt(result)
        tvResults = currentNumber
        currentOperation = ""
        previousNumber = ""
    }

    fun updateNumber(number: String) {
        if (currentNumber == "0") {
            currentNumber = number
        } else {
            currentNumber += number
        }
        tvResults = currentNumber
    }

    fun updateOperation(operation: String) {
        if (isShowingArithmetic()) {
            currentOperation = operation
            tvResults = currentOperation
            return
        }
        equals()
        currentOperation = operation
        if (!isAfterClear) {
            previousNumber = currentNumber
        }
        isAfterClear = false
        currentNumber = "0"
        tvResults = currentOperation
    }

    fun allClear() {
        previousNumber = ""
        currentNumber = "0"
        currentOperation = ""
        tvResults = "0"
    }

    fun clear() {
        if (isAfterClear) {
            previousNumber = ""
        }
        currentNumber = "0"
        tvResults = "0"
        currentOperation = ""
        isAfterClear = true
    }

    fun dot() {
        if (!isShowingArithmetic()) {
            if (!currentNumber.contains('.')) {
                currentNumber += "."
                tvResults = currentNumber
            } else if (currentNumber.last() == '.') {
                currentNumber = currentNumber.toDouble().toInt().toString()
                tvResults = currentNumber
            }
        }
    }

    fun plusMinus() {
        if (!isShowingArithmetic()) {
            currentNumber = (-(currentNumber.toDouble())).toString()
            val result = currentNumber.toDouble()
            currentNumber = checkIfNumberIsInt(result)
            tvResults = currentNumber
        }
    }

    // Scientific operations

    fun sin() {
        if (!isShowingArithmetic()) {
            currentNumber = sin(currentNumber.toDouble()).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun cos() {
        if (!isShowingArithmetic()) {
            currentNumber = cos(currentNumber.toDouble()).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun tan() {
        if (!isShowingArithmetic()) {
            currentNumber = tan(currentNumber.toDouble()).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun log() {
        if (!isShowingArithmetic()) {
            currentNumber = log(currentNumber.toDouble(), 10.0).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun ln() {
        if (!isShowingArithmetic()) {
            currentNumber = ln(currentNumber.toDouble()).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun sqrt() {
        if (!isShowingArithmetic()) {
            currentNumber = sqrt(currentNumber.toDouble()).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun powerDouble() {
        if (!isShowingArithmetic()) {
            currentNumber = currentNumber.toDouble().pow(2.0).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }

    fun powerY() {
        if (!isShowingArithmetic()) {
            currentOperation = "^"
            if (!isAfterClear) {
                previousNumber = currentNumber
            }
            isAfterClear = false
            currentNumber = "0"
            tvResults = currentOperation
        }
    }

    fun percent() {
        if (!isShowingArithmetic()) {
            currentNumber = (currentNumber.toDouble() / 100).toString()
            currentNumber = checkIfNumberIsInt(currentNumber.toDouble())
            tvResults = currentNumber
        }
    }
}