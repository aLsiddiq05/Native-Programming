/*
 * Muhammad Amier
 * S63650
 */

// Define the Event class
class Event(
    val title: String,
    val description: String? = "",
    val daypart: Daypart,
    val durationInMinutes: Int
)

// Enum class for dayparts
enum class Daypart {
    MORNING, AFTERNOON, EVENING
}

// Extension property for duration classification
val Event.durationOfEvent: String
    get() = if (durationInMinutes < 60) {
        "short"
    } else {
        "long"
    }

fun main() {
    // Assuming you have the events variable from the previous task's solution
    val events = mutableListOf<Event>().apply {
        add(Event("Wake up", "Time to get up", Daypart.MORNING, 0))
        add(Event("Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15))
        add(Event("Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30))
        add(Event("Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60))
        add(Event("Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10))
        add(Event("Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45))
        add(Event("Exercise for 30 minutes", "Stay active and healthy", daypart = Daypart.AFTERNOON, durationInMinutes = 30))
    }

    // Use the extension property to retrieve the duration classification
    val durationOfEvent = events[0].durationOfEvent

    // Display the duration classification
    println("Duration of first event of the day: ${events[0].durationOfEvent}")
}

