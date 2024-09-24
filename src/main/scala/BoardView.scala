package laivanupotus

class BoardView(size: Int) {
    val shotsFiredTable: Array[Array[Option[Boolean]]] = Array.fill(size, size)(None)

    def registerShot(hit: Boolean, x: Int, y: Int): Unit = {
        shotsFiredTable(y)(x) = Option(hit)
    }
    def alreadyHit(x: Int, y: Int): Boolean = {
        shotsFiredTable(y)(x) match {
            case Some(_) => true
            case _ => false
        }
    }

    override def toString(): String = {
        var stringCombiner = ""
        for (x <- this.shotsFiredTable) {
            for (y <- x) {
                y match {
                case Some(true)  => stringCombiner += "X "
                case Some(false) => stringCombiner += "O "
                case _             => stringCombiner += "~ "
                }
            }
            stringCombiner += "\n"
        }
        stringCombiner    
    }
}
