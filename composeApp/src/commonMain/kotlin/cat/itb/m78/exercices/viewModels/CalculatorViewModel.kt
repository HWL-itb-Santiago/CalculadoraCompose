package cat.itb.m78.exercices.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class TypeOfButton{Wait,Plus, Minus, Times, Divide}

data class Result(
    val finalResult: Float = 0f,
    val typeOfCalculation: TypeOfButton = TypeOfButton.Wait
)

data object FinalResult
{
    private var result = Result()
    fun update(newResult: Result)
    {
        result = newResult
    }
    fun get() = result
}


class CalculatorViewModel : ViewModel()
{
    private var _currentResult = MutableStateFlow(FinalResult.get().finalResult)
    var currentResult: StateFlow<Float> = _currentResult.asStateFlow()

    private var _type = MutableStateFlow(FinalResult.get().typeOfCalculation.ordinal)
    private var type : StateFlow<Int> = _type.asStateFlow()

    fun setType(newType: Int)
    {
        _type.value = newType
        typeOfCalculation()
    }
    fun validateInput(input: String) : Boolean
    {
        return (input.isNotEmpty() && !input.contains(' '))
    }

    private fun typeOfCalculation()
    {
        val newType = when (type.value)
        {
            1 -> TypeOfButton.Plus
            2 -> TypeOfButton.Minus
            3 -> TypeOfButton.Times
            4 -> TypeOfButton.Divide
            else -> TypeOfButton.Wait
        }
        FinalResult.update(FinalResult.get().copy(typeOfCalculation = newType))
    }
    fun calculate(input: Float)
    {
        when (type.value)
        {
            0 -> plus(0f)
            1 -> plus(input)
            2 -> minus(input)
            3 -> times(input)
            4 -> divide(input)
        }
    }
    fun plus(addNumber: Float)
    {
        _currentResult.value = addNumber + currentResult.value
        FinalResult.update(FinalResult.get().copy(finalResult = _currentResult.value))
    }

    fun minus(minusNumber: Float)
    {
        _currentResult.value = currentResult.value - minusNumber
        FinalResult.update(FinalResult.get().copy(finalResult = _currentResult.value))
    }

    fun times(timesNumber: Float)
    {
        _currentResult.value = timesNumber * currentResult.value
        FinalResult.update(FinalResult.get().copy(finalResult = _currentResult.value))
    }

    private fun divide(divideNumber: Float)
    {
        _currentResult.value = currentResult.value / divideNumber
        FinalResult.update(FinalResult.get().copy(finalResult = _currentResult.value))
    }
}