/**
 * Amier
 * S63650
 */
class Song(
    val title: String,
    val artist: String,
    val yearPublished: Int,
    val playCount: Int
) {
    val isPopular: Boolean
        get() = playCount >= 1000

    fun printSongDescription() {
        println("$title, performed by $artist, was released in $yearPublished.")
    }
}

fun main() {
    val song1 = Song("I Need To Know", "Sleeping With Sirens", 2017, 3000)
    val song2 = Song("Surti Tejo", "Jamrud", 2000, 800)

    song1.printSongDescription()
    song2.printSongDescription()

    println("Is song1 popular? ${if (song1.isPopular) "Yes" else "No"}")
    println("Is song2 popular? ${if (song2.isPopular) "Yes" else "No"}")
}



