package laivanupotus

import scala.io.StdIn._
import scala.collection.mutable.ArrayBuffer


object game extends App{

    def main(turnLimit: Int): Unit = {
        val size = 10
        val gameBoard = new GameBoard(size)
        val boardView = new BoardView(size)
        val ships = createShips(5, gameBoard)

        var turnsUsed = 0
        while(turnsUsed < turnLimit) {
            val (x, y) = getShotCoords()
            if (boardView.alreadyHit(x, y)) {
                println("You already fired here!")
            } else {
                val wasHit = checkHit(x, y, gameBoard)
                boardView.registerShot(wasHit, x, y)
                println(boardView)
                if (wasHit && isDestroyed(getHitShip(x, y, gameBoard, ships), boardView)) {
                    println("You sank my battleship")
                }
                turnsUsed += 1
            }
            if (ships.forall(x => isDestroyed(x, boardView))){
                println("Congratulations, you destroyed all ships with "
                        + (turnLimit - turnsUsed) + " turns remaining!")
                return
            }
        }
    }

    def getHitShip(x: Int, y: Int, board: GameBoard, ships: ArrayBuffer[Ship]): Ship = {
        ships.find(ship => ship.getCoordinateArray.contains((x, y))).get
    }

    def isDestroyed(ship: Ship, boardView: BoardView): Boolean = {
        val coords = ship.getCoordinateArray
        coords.forall((x, y) => boardView.alreadyHit(x, y))
    }

    def checkHit(x: Int, y: Int, board: GameBoard): Boolean = {
        val wasHit = board.getCoordinate(x, y)
        if (wasHit) println("Bull's Eye! You hit a ship")
        else println("Sorry, missed this time")
        wasHit
    }

    def getShotCoords(): (Int, Int) = {
        println("Shoot! Enter coordinates (first x, then y)")
        val x = readInt()
        val y = readInt()
        (x, y)
    }


    def createShips(amount: Int, gameBoard: GameBoard): ArrayBuffer[Ship] = {

        val ships = ArrayBuffer[Ship]()
        
        var i = amount
        while (i > 0) {

            val newShip = createShipFromInput(i, gameBoard)
            if (addShip(newShip, gameBoard)) {
                i -= 1
                ships += newShip
                println(gameBoard)
                println("Ship was added to the gameboard!")
            }
            else println("Invalid ship placement, try again")
        }
        ships
    }

    def createShipFromInput(length: Int, gameBoard: GameBoard): Ship = {
        println("Create a ship with length: " + length)
        println("Select orientation ([H]orizontal or [V]ertical")
        val orientation = readChar().toUpper
        println("Enter x-coordinate where the ship starts")
        val anchorX = readInt()
        println("Enter y-coordinate where the ship starts")
        val anchorY = readInt()

        new Ship(length, orientation, (anchorX, anchorY))
    }

    def addShip(ship: Ship, board: GameBoard): Boolean = {
        val x = ship.getX
        val y = ship.getY
        val length = ship.getLength
        if (ship.isHorizontal && x + length > board.getSize) return false
        else if (!ship.isHorizontal && y + length > board.getSize) return false

        val coords = ship.getCoordinateArray
        board.setShip(coords)
    }

    
    val turnLimit = 100
    main(turnLimit)
}
