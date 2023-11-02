package org.codeforall.iorns;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.codeforall.iorns.Cell;

import java.util.ArrayList;
import java.util.List;

public class Grid extends Rectangle {


    public static final int PADDING = 10;
    public static final int CELLSIZE = 10;
    private int cols;
    private int rows;
    private int width;
    private int height;
    Cell cell;
    private List<Rectangle> rectangles = new ArrayList<>();

    public List<Rectangle> getRectangles() {
        return rectangles;
    }

    @Override
    public int getX() {
        return PADDING;
    }

    public int getY() {
        return PADDING;
    }

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        createCells();
    }

    public int getWidth() {
        width = cols * CELLSIZE;
        return width;
    }

    public int getHeight() {
        height = rows * CELLSIZE;
        return height;
    }

    public void createCells() {
        int positionX = PADDING;
        int positionY = PADDING;


        while (positionX != getWidth() + CELLSIZE) {
            cell = new Cell(positionX, positionY);
            positionX += CELLSIZE;
            if (positionX == getWidth() + CELLSIZE && positionY != getHeight()) {
                positionX = PADDING;
                positionY += CELLSIZE;
            }
            rectangles.add(cell);
           // System.out.println(rectangles);
        }
    }
}


