class Arctg {
    fun calc(x: Double, n: Int): Double {
        if (x < -1 || x > 1) {
            throw IllegalArgumentException("x should be in the range [-1;1], but x = $x")
        }
        if (n < 0) {
            throw IllegalArgumentException("n should be positive, but n = $n")
        }

        var arctan = 0.0
        var sign = 1.0
        for (i in 1..n * 2 step 2) {
            val term = Math.pow(x, i.toDouble()) / i
            arctan += sign * term
            sign = -sign
        }
        return arctan
    }
}