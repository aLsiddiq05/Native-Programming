/**
 * Amier
 * S63650
 */
open class Phone(var isScreenLightOn: Boolean = false) {
    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    open fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

class FoldablePhone(isFolded: Boolean = false) : Phone() {
    var isFolded: Boolean = isFolded
        private set

    override fun switchOn() {
        if (!isFolded) {
            isScreenLightOn = true
        }
    }

    fun fold() {
        isFolded = true
    }

    fun unfold() {
        isFolded = false
    }
}

fun main() {
    val phone = FoldablePhone()

    // Check initial state
    phone.checkPhoneScreenLight() // should print "The phone screen's light is off."

    // Turn on the screen
    phone.switchOn()
    phone.checkPhoneScreenLight() // should print "The phone screen's light is on."

    phone.switchOff()
    
    // Fold the phone
    phone.fold()
    phone.switchOn() // should not turn on the screen
    phone.checkPhoneScreenLight() // should print "The phone screen's light is off."

    // Unfold the phone
    phone.unfold()
    phone.switchOn() // should turn on the screen
    phone.checkPhoneScreenLight() // should print "The phone screen's light is on."

    // Turn off the screen
    phone.switchOff()
    phone.checkPhoneScreenLight() // should print "The phone screen's light is off."
}
