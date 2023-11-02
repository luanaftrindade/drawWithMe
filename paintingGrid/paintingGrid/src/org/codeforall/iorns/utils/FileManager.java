package org.codeforall.iorns.utils;

import java.io.*;

public class FileManager {

    public static String FILEPATH = "paintingGrid/resources/savedGrid.txt";

    KeyboardHandler keyboardHandler;

    public FileManager(KeyboardHandler keyboardHandler){
        this.keyboardHandler = keyboardHandler;
    }


    public void writeFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(keyboardHandler.toString());
            //System.out.println(eventHandler.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }


    public String readFile() {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(FILEPATH);
        } catch (FileNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = null;
        try {
            string = bufferedReader.readLine();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return string;
    }

}



