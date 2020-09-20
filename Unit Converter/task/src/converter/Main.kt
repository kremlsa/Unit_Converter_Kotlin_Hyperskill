package converter

import java.util.*

var hub: Double = 0.0
val scanner = Scanner(System.`in`)
var units: String = ""
var outUnits: String = ""
var isRun = true

enum class Conv(val rate: Double, val short: String, val singular: String, val plural: String) {
    METER(1.0, "m", "meter", "meters"),
    KMETER(1000.0, "km", "kilometer", "kilometers"),
    CMETER(0.01, "cm", "centimeter", "centimeters"),
    MMETER(0.001, "mm", "millimeter", "millimeters"),
    MILE(1609.35, "mi", "mile", "miles"),
    YARD(0.9144, "yd", "yard", "yards"),
    FOOT(0.3048, "ft", "foot", "feet"),
    INCH(0.0254, "in", "inch", "inches"),
    GRAM(1.0, "g", "gram", "grams"),
    KGRAM(1000.0, "kg", "kilogram", "kilograms"),
    MGRAM(0.001, "mg", "milligram", "milligrams"),
    POUND(453.592, "lb", "pound", "pounds"),
    OUNCE(28.3495, "oz", "ounce", "ounces");

    companion object {
        fun find (value: Double, name: String): String {
            when (name) {
                "m", "meter", "meters" -> return if (value == 1.0) "meter" else "meters"
                "km", "kilometer", "kilometers" -> return if (value == 1.0) "kilometer" else "kilometers"
                "centimeters", "cm", "centimeter" -> return if (value == 1.0) "centimeter" else "centimeters"
                "mm", "millimeter", "millimeters" -> return if (value == 1.0) "millimeter" else "millimeters"
                "mi", "mile", "miles" -> return if (value == 1.0) "mile" else "miles"
                "yd", "yard", "yards" -> return if (value == 1.0) "yard" else "yards"
                "ft", "foot", "feet" -> return if (value == 1.0) "foot" else "feet"
                "in", "inch", "inches" -> return if (value == 1.0) "inch" else "inches"
                "g", "gram", "grams" -> return if (value == 1.0) "gram" else "grams"
                "kg", "kilogram", "kilograms" -> return if (value == 1.0) "kilogram" else "kilograms"
                "mg", "milligram", "milligrams" -> return if (value == 1.0) "milligram" else "milligrams"
                "lb", "pound", "pounds" -> return if (value == 1.0) "pound" else "pounds"
                "oz", "ounce", "ounces" -> return if (value == 1.0) "ounce" else "ounces"
                "degree Celsius", "degrees Celsius", "celsius", "dc", "c" -> return if (value == 1.0) "degree Celsius" else "degrees Celsius"
                "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f" -> return if (value == 1.0) "degree Fahrenheit" else "degrees Fahrenheit"
                "kelvin", "kelvins", "k" -> return if (value == 1.0) "kelvin" else "kelvins"
            }
            return "???"
        }

        fun convert(value: Double, name: String, dir: String): Double {
            when (name) {
                "m", "meter", "meters" -> return if (dir == "to") value * METER.rate else return value / METER.rate
                "km", "kilometer", "kilometers" -> return if (dir == "to") value * KMETER.rate else return value / KMETER.rate
                "centimeters", "cm", "centimeter" -> return if (dir == "to") value * CMETER.rate else return value / CMETER.rate
                "mm", "millimeter", "millimeters" -> return if (dir == "to") value * MMETER.rate else return value / MMETER.rate
                "mi", "mile", "miles" -> return if (dir == "to") value * MILE.rate else return value / MILE.rate
                "yd", "yard", "yards" -> return if (dir == "to") value * YARD.rate else return value / YARD.rate
                "ft", "foot", "feet" -> return if (dir == "to") value * FOOT.rate else return value / FOOT.rate
                "in", "inch", "inches" -> return if (dir == "to") value * INCH.rate else return value / INCH.rate
                "g", "gram", "grams" -> return if (dir == "to") value * GRAM.rate else return value / GRAM.rate
                "kg", "kilogram", "kilograms" -> if (dir == "to") return value * KGRAM.rate else return value / KGRAM.rate
                "mg", "milligram", "milligrams" -> if (dir == "to") return value * MGRAM.rate else return value / MGRAM.rate
                "lb", "pound", "pounds" -> if (dir == "to") return value * POUND.rate else return value / POUND.rate
                "oz", "ounce", "ounces" -> if (dir == "to") return value * OUNCE.rate else return value / OUNCE.rate
            }
            return 0.0
        }

        fun isLength(unit: String): Boolean {
            when (unit) {
                "m", "meter", "meters" -> return true
                "km", "kilometer", "kilometers" -> return true
                "centimeters", "cm", "centimeter" -> return true
                "mm", "millimeter", "millimeters" -> return true
                "mi", "mile", "miles" -> return true
                "yd", "yard", "yards" -> return true
                "ft", "foot", "feet" -> return true
                "in", "inch", "inches" -> return true
            }
            return false
        }

        fun isWeight(unit: String): Boolean {
            when (unit) {
                "g", "gram", "grams" -> return true
                "kg", "kilogram", "kilograms" -> return true
                "mg", "milligram", "milligrams" -> return true
                "lb", "pound", "pounds" -> return true
                "oz", "ounce", "ounces" -> return true
            }
            return false
        }

        fun isConvert(unitin: String, unitout: String): Boolean {
            if (isLength(unitin) && isLength(unitout)) return true
            if(isWeight(unitin) && isWeight(unitout)) return true
            return false
        }
    }

}

fun convertTemp(unitin: String, unitout: String, value: Double): Double {
    return when (unitin) {
        "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f" -> farenheit(unitout,value)
        "kelvin", "kelvins" -> kelvin(unitout, value)
        "degree Celsius", "degrees Celsius", "celsius", "dc", "c" -> celsisus(unitout, value)
        else -> value
    }
}

fun celsisus(unit: String, value: Double): Double {
    return when (unit) {
        "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f" -> value * 9.0 / 5.0 + 32.0
        "kelvin", "kelvins", "k" -> value + 273.15
        else -> value
    }
}

fun farenheit(unit: String, value: Double): Double {
    return when (unit) {
        "degree Celsius", "degrees Celsius", "celsius", "dc", "c" -> (value - 32.0) * 5.0 / 9.0
        "kelvin", "kelvins", "k" -> (value + 459.67) * 5.0 / 9.0
        else -> value
    }
}

fun kelvin(unit: String, value: Double): Double {
    return when (unit) {
        "degree Celsius", "degrees Celsius", "celsius", "dc", "c" -> value - 273.15
        "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f" -> value * 9.0 / 5.0 - 459.67
        else -> value
    }
}

fun isTemp(unit: String): Boolean {
    when (unit) {
        "degree Celsius", "degrees Celsius", "celsius", "dc", "c" -> return true
        "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f" -> return true
        "kelvin", "kelvins", "k" -> return true
    }
    return false
}

fun find (value: Double, name: String): String {
    when (name) {
        "degree Celsius", "degrees Celsius", "celsius", "dc", "c" -> return if (value == 1.0) "degree Celsius" else "degrees Celsius"
        "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df", "f" -> return if (value == 1.0) "degree Fahrenheit" else "degrees Fahrenheit"
        "kelvin", "kelvins", "k" -> return if (value == 1.0) "kelvin" else "kelvins"
    }
    return "???"
}



fun main() {
    while (isRun) {
        print("Enter what you want to convert (or exit): ")
        parseInput()
        if (isRun) {
            if (hub < 0 && Conv.isLength(units)) {
                println("Length shouldn't be negative.")
            } else if (hub < 0 && Conv.isWeight(units)) {
                println("Weight shouldn't be negative.")
            } else if (Conv.isConvert(units, outUnits)) {
                val hubConvertTo = Conv.convert(hub, units, "to")
                val hubConvertFrom = Conv.convert(hubConvertTo, outUnits, "from")
                println("$hub ${units} is ${hubConvertFrom} ${Conv.find(hubConvertFrom, outUnits)}")
            } else if (isTemp(units) && isTemp(outUnits)) {
                val convT = convertTemp(units, outUnits, hub)
                println("$hub ${units} is ${convT} ${find(convT, outUnits)}")
            } else println ("Conversion from ${Conv.find(10.0, units)} to ${Conv.find(10.0, outUnits)} is impossible")
        }
    }
}

fun parseInput() {
    val action = scanner.next()
    if (action == "exit") {
        isRun = false
    } else {
        hub =action.toDouble()
        units = scanner.next().toLowerCase()
        if (units == "degree" || units == "degrees") {
            units = find(hub, scanner.next().toLowerCase())
        } else if(isTemp(units)) {
            units = find(hub, units)
        }
        else {
            units = Conv.find(hub, units)
        }
        val action = scanner.next()
        outUnits = scanner.next().toLowerCase()
        if (outUnits == "degree" || outUnits == "degrees") {
            outUnits = find(hub, scanner.next().toLowerCase())
        } else if(isTemp(outUnits)) {
            outUnits = find(hub, outUnits)
        } else {
            outUnits = Conv.find(hub, outUnits)
        }
    }
}