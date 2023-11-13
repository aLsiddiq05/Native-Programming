/**
 * Amier
 * S63650
 */
fun main() {    
    val amanda = Person("Amanda", 33, "play tennis", null)
    val atiqah = Person("Atiqah", 28, "climb", amanda)
    
    amanda.showProfile()
    atiqah.showProfile()
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println("Name: $name")
        println("Age: $age")
        if (hobby != null) {
            println("Likes to $hobby.")
        } else {
            println("Doesn't have a specified hobby.")
        }
        if (referrer != null) {
            println("Has a referrer named ${referrer.name}, who likes to ${referrer.hobby ?: "do not specify a hobby"}.")
        } else {
            println("Doesn't have a referrer.")
        }
        println()
    }
}



