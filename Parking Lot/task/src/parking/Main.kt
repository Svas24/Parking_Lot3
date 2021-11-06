package parking

fun main() {
    data class Spot(val number: String, val color: String)

    var lot = emptyArray<Spot?>()

    while (true) {
        val input = readLine()!!.split(" ")
        if (input[0] == "exit") return
        if (input[0] == "create") {
            lot = Array(input[1].toInt()) { null }
            println("Created a parking lot with ${lot.size} spots.")
            continue
        }
        if (lot.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
            continue
        }
        when (input[0].lowercase()) {
            "status" -> {
                if (lot.all { it == null })
                    println("Parking lot is empty.")
                else lot.forEachIndexed { idx, spot ->
                    if (spot != null) print("${idx + 1} ${spot.number} ${spot?.color.capitalize()}\n")
                }
            }
            "park" -> {
                if (lot.contains(null)) {
                    val id = lot.indexOfFirst { it == null }
                    lot[id] = Spot(input[1], input[2].lowercase())
                    println("${input[2]} car parked in spot ${id + 1}.")
                } else println("Sorry, the parking lot is full.")
            }
            "leave" -> {
                val id = input[1].toInt() - 1
                if (lot[id] != null) {
                    lot[id] = null
                    println("Spot ${id + 1} is free.")
                } else println("There is no car in spot ${id + 1}.")
            }
            "reg_by_color" -> {
                if (lot.any { it?.color == input[1].lowercase() })
                    println(lot.filter { it?.color == input[1].lowercase() }.map { it?.number }.joinToString())
                else println("No cars with color ${input[1].capitalize()} were found.")
            }
            "spot_by_color" -> {
                val ids = mutableListOf<Int>()
                lot.forEachIndexed {id, spot -> if (spot?.color ==  input[1].lowercase()) ids.add(id + 1)}
                if (ids.isNotEmpty()) println(ids.joinToString())
                else println("No cars with color ${input[1].capitalize()} were found.")
            }
            "spot_by_reg" -> {
                val id = lot.indexOfFirst { it?.number == input[1] }
                if (id != -1) println(id + 1)
                else println("No cars with registration number ${input[1]} were found.")
            }
        }
    }
}
