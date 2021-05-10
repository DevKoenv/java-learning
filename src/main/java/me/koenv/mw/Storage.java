package me.koenv.mw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Storage {

    private static final File AppDataPath = new File(System.getenv("APPDATA") + "\\mw");
    private static final File credFile = new File(AppDataPath + "\\credentials.json");

    public void run() {
        if(!AppDataPath.exists()) {
            AppDataPath.mkdir();
            run();
        } else {
            File credFile = new File(AppDataPath + "\\credentials.json");
            try {
                if(!credFile.exists()){
                    credFile.createNewFile();
                }
                PrintWriter out = new PrintWriter(new FileWriter(credFile, true));
                out.close();
            } catch (IOException ignored){ }







        }
    }
}
