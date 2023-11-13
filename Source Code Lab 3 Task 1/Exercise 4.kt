/**
 * Amier
 * S63650
 */
fun main() {
    val celsiusToFahrenheitFormula: (Double) -> Double = { celsius -> (celsius * 9 / 5) + 32 }
    val kelvinToCelsiusFormula: (Double) -> Double = { kelvin -> kelvin - 273.15 }
    val fahrenheitToKelvinFormula: (Double) -> Double = { fahrenheit -> (5.0 / 9.0) * (fahrenheit - 32) + 273.15 }

    val celsius = 27.0
    val kelvin = 350.0
    val fahrenheit = 10.0

    printFinalTemperature(celsius, "Celsius", "Fahrenheit", celsiusToFahrenheitFormula)
    printFinalTemperature(kelvin, "Kelvin", "Celsius", kelvinToCelsiusFormula)
    printFinalTemperature(fahrenheit, "Fahrenheit", "Kelvin", fahrenheitToKelvinFormula)
}

fun printFinalTemperature(
    initialMeasurement: Double,
    initialUnit: String,
    finalUnit: String,
    conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}




