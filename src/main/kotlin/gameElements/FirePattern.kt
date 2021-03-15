package gameElements

import timer

class FirePattern(val fn: () -> List<Bullet>) {
    constructor(n: Int): this( { List(n) { Bullet() } } )
    operator fun invoke() = fn()

    fun setDelay(delay: Int, startDelay: Int = 0) =
            FirePattern { if ((timer - startDelay) % delay == 0) fn() else emptyList() }
    inline fun applyIndexed(crossinline f: (Int, Bullet) -> Unit) =
            FirePattern { fn().onEachIndexed(f) }
    inline fun apply(crossinline f: (Bullet) -> Unit) =
            FirePattern { fn().onEach(f) }
    inline fun applyEvery(crossinline f: (List<Bullet>) -> List<Bullet>) =
            FirePattern { f(fn()) }
}