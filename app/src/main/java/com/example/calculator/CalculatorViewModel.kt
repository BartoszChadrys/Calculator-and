package com.example.calculator

import androidx.lifecycle.ViewModel

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
}