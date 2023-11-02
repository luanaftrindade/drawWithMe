package org.codeforall.iorns.utils;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.codeforall.iorns.Grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.codeforall.iorns.Grid.PADDING;
import static org.codeforall.iorns.Grid.CELLSIZE;


public class KeyboardHandler implements org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler {


    // deixo default? private, public?
    Rectangle paintingCell;
    Grid grid;
    FileManager fileManager;
    MyColor setColor = MyColor.fromColor(Color.GRAY);


    public List<Rectangle> paintedCells = new ArrayList<>();

    public KeyboardHandler(Grid grid) {
        paintingCell = new Rectangle(PADDING, PADDING, CELLSIZE, CELLSIZE);
        paintingCell.setColor(setColor.color);
        paintingCell.fill();
        this.grid = grid;
        fileManager = new FileManager(this);
    }

    public void init() {
        initKeyboard();
    }

    public void initKeyboard() {

        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent right = new KeyboardEvent();
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        right.setKey(KeyboardEvent.KEY_RIGHT);
        keyboard.addEventListener(right);

        KeyboardEvent left = new KeyboardEvent();
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        left.setKey(KeyboardEvent.KEY_LEFT);
        keyboard.addEventListener(left);

        KeyboardEvent up = new KeyboardEvent();
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        up.setKey(KeyboardEvent.KEY_UP);
        keyboard.addEventListener(up);

        KeyboardEvent down = new KeyboardEvent();
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        down.setKey(KeyboardEvent.KEY_DOWN);
        keyboard.addEventListener(down);

        KeyboardEvent paintAndClear = new KeyboardEvent();
        paintAndClear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        paintAndClear.setKey(KeyboardEvent.KEY_P);
        keyboard.addEventListener(paintAndClear);

        KeyboardEvent save = new KeyboardEvent();
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        save.setKey(KeyboardEvent.KEY_S);
        keyboard.addEventListener(save);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        clear.setKey(KeyboardEvent.KEY_C);
        keyboard.addEventListener(clear);

        KeyboardEvent load = new KeyboardEvent();
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        load.setKey(KeyboardEvent.KEY_L);
        keyboard.addEventListener(load);

        KeyboardEvent changeColor = new KeyboardEvent();
        changeColor.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        changeColor.setKey(KeyboardEvent.KEY_K);
        keyboard.addEventListener(changeColor);
    }

    public void painting() {
        for (int i = 0; i < grid.getRectangles().size(); i++) {
            if (paintingCell.getX() == grid.getRectangles().get(i).getX() && paintingCell.getY() == grid.getRectangles().get(i).getY()) {
                if (grid.getRectangles().get(i).isFilled() == false) {
                    grid.getRectangles().get(i).setColor(setColor.color);
                    grid.getRectangles().get(i).fill();
                    paintedCells.add(grid.getRectangles().get(i));
                    System.out.println(paintedCells);
                    break;
                }
                grid.getRectangles().get(i).setColor(Color.BLACK);
                grid.getRectangles().get(i).draw();
                paintedCells.remove(grid.getRectangles().get(i));
                System.out.println(paintedCells);
            }
        }
    }


    public void clearAll() {
        for (int i = 0; i < grid.getRectangles().size(); i++) {
            grid.getRectangles().get(i).setColor(Color.BLACK);
            grid.getRectangles().get(i).draw();
        }
        for (int i = 0; i < paintedCells.size(); i++) {
            paintedCells.get(i).setColor(Color.BLACK);
            paintedCells.get(i).draw();
            paintedCells.remove(i);
            i--;
        }
    }

    public enum MyColor {

        GRAY(Color.GRAY),
        RED(Color.RED),
        BLUE(Color.BLUE),
        CYAN(Color.CYAN),
        PINK(Color.PINK),
        ORANGE(Color.ORANGE),
        GREEN(Color.GREEN),
        YELLOW(Color.YELLOW);


        private Color color;

        MyColor(Color color) {
            this.color = color;
        }

        public static MyColor fromColor(Color color) {
            for (MyColor myColor : values()) {
                if (myColor.color == color) {
                    return myColor;
                }
            }
            return null;
        }
    }


    public void setNewColor() {

        switch (setColor) {
            case GRAY:
                setColor = MyColor.RED;
                break;

            case RED:
                setColor = MyColor.BLUE;
                break;

            case BLUE:
                setColor = MyColor.CYAN;
                break;

            case CYAN:
                setColor = MyColor.PINK;
                break;

            case PINK:
                setColor = MyColor.ORANGE;
                break;

            case ORANGE:
                setColor = MyColor.GREEN;
                break;

            case GREEN:
                setColor = MyColor.YELLOW;
                break;

            case YELLOW:
                setColor = MyColor.GRAY;
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + setColor);
        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_RIGHT:
                if (paintingCell.getX() < grid.getWidth()) {
                    paintingCell.translate(CELLSIZE, 0);
                }
                break;

            case KeyboardEvent.KEY_LEFT:
                if (paintingCell.getX() > PADDING) {
                    paintingCell.translate(-CELLSIZE, 0);
                }
                break;

            case KeyboardEvent.KEY_UP:
                if (paintingCell.getY() > PADDING) {
                    paintingCell.translate(0, -CELLSIZE);
                }
                break;

            case KeyboardEvent.KEY_DOWN:
                if (paintingCell.getY() < grid.getHeight()) {
                    paintingCell.translate(0, CELLSIZE);
                }
                break;

            case KeyboardEvent.KEY_P:
                painting();
                break;

            case KeyboardEvent.KEY_S:
                fileManager.writeFile();
                break;

            case KeyboardEvent.KEY_L:
                convertToGrid(fileManager.readFile());
                break;

            case KeyboardEvent.KEY_C:
                clearAll();
                break;

            case KeyboardEvent.KEY_K:
                setNewColor();
                break;
        }

    }


    public String convertToGrid(String string) {

        String[] cells = string.split(",");
        for (String cell : cells) {
            String[] paintedPosition = cell.split("/");
            int valueX = Integer.parseInt(paintedPosition[0].trim());
            int valueY = Integer.parseInt(paintedPosition[1].trim());
            paintingCell = new Rectangle(valueX, valueY, CELLSIZE, CELLSIZE);
            paintingCell.setColor(setColor.color);
            paintedCells.add(paintingCell);
            paintingCell.fill();
        }
        return Arrays.toString(cells);
    }


    // EM FALTA 
    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    @Override
    public String toString() {
        String result = "";
        for (Rectangle rectangle : paintedCells) {
            result += rectangle.getX() + "/" + rectangle.getY() + ",";
        }
        return result;
    }

}
