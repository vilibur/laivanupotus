package laivanupotus

class Ship(length: Int, orientation: Char, anchor: (Int, Int)){
    def getLength: Int = this.length
    def getAnchor: (Int, Int) = this.anchor
    def getX: Int = this.anchor._1
    def getY: Int = this.anchor._2
    def isHorizontal: Boolean = this.orientation == 'H'

    def getCoordinateArray: Array[(Int, Int)] = {
        this.orientation match {
            case 'H' => toSliding(this.getX).zip(toStatic(this.getY))
            case _   => toStatic(this.getX).zip(toSliding(this.getY))
        }
    }

    private def toSliding(a: Int): Array[Int] = (0 until this.length).map(i => i + a).toArray
    private def toStatic(a: Int): Array[Int] = (0 until this.length).map(i => a).toArray
}
