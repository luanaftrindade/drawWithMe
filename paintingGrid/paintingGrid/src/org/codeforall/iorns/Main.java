package org.codeforall.iorns;


import org.codeforall.iorns.utils.KeyboardHandler;

public class Main {

    public static void main(String[] args) {

        Grid grid = new Grid(50, 50);
        grid.draw();

        KeyboardHandler keyboardHandler = new KeyboardHandler(grid);
        keyboardHandler.init();

    }
}
