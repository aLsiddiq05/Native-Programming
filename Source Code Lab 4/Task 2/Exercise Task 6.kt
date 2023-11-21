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

enum class Daypart {
    MORNING, AFTERNOON, EVENING, NIGHT
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
        add(Event("Exercise for 30 minutes", "Stay active and healthy", daypart = Daypart.NIGHT, durationInMinutes = 30))
    }

    println("Last event of the day: ${events.last().title}")
}
