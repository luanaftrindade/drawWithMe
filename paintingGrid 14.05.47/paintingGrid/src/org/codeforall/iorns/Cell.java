package org.codeforall.iorns;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell extends Rectangle {

    public static int xCell = Grid.CELLSIZE;
    public static int yCell = Grid.CELLSIZE;


    public Cell(int positionX, int positionY){
        super(positionX, positionY, xCell, yCell);
        super.draw();


    }
}
