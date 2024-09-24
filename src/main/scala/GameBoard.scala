package laivanupotus

import scala.compiletime.ops.boolean

class GameBoard(size: Int) {

    private val board = Array.fill(size, size)(false)

    def getSize: Int = this.size
    def getBoard: Array[Array[Boolean]] = this.board

    def setShip(shipCoords: Array[(Int, Int)]): Boolean = {
        val isClear = shipCoords.forall((x, y) => !this.board(y)(x))
        if (isClear) shipCoords.foreach((x, y) => this.board(y)(x) = true)
        isClear
    }

    def getCoordinate(x: Int, y: Int): Boolean = board(y)(x)

    override def toString: String = {
        var stringCombiner = ""
        for (x <- this.board) {
            for (y <- x) {
                if (y) stringCombiner += "X "
                else stringCombiner += "_ "
            }
            stringCombiner += "\n"
        }
        stringCombiner
    }
}
