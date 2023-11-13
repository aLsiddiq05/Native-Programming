/**
 * Amier
 * S63650
 */
fun main() {
    val child = 5
    val adult = 28
    val senior = 87

    val isMonday = true

    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    return when {
        age >= 0 && age <= 12 -> 15 // Children's ticket price
        age >= 13 && age <= 60 -> if (isMonday) 25 else 30 // Standard ticket price with Monday discount
        age >= 61 && age <= 100 -> 20 // Senior ticket price
        else -> -1 // Invalid age
    }
}





