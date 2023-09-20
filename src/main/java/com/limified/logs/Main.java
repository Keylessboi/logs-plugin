package com.limified.logs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    eventmanager manager = new eventmanager();
    public static boolean verbose = true;
    public Main() throws IOException {
    }
    


    public void onEnable() {
        System.out.println("Loggerified is starting!");
        if (verbose) {
            Bukkit.setMotd("Logger has successfully launched");
        }
        File playerDir = new File("plugins/playerlogs");
        if (!playerDir.exists()) {
            playerDir.mkdir();
        }
        System.out.println("Do /logger on/off to toggle on or off.");
        Bukkit.getPluginManager().registerEvents(manager, this);
    }
    public static boolean loggerstatus =true;
    public static void writetologs(String text, String filelocation, String playername) {
       if (loggerstatus) {
           try {
               FileWriter logs = new FileWriter("plugins/playerlogs/" + playername + "/" + filelocation + "/logs.txt", true);
               FileWriter filelogs = new FileWriter("plugins/playerlogs/" + playername + "/" + "/logs.txt", true);
               logs.write(playername + " has filled a bucket\n");
               filelogs.write(playername + " has filled a bucket\n");
               logs.close();
               filelogs.close();

           } catch (FileNotFoundException e) {
               System.out.println("no file :(");
               if (verbose) {
                   e.printStackTrace();
               } else {
                   System.out.println("Something went wrong! Reporting to developer");

               }
           } catch (Exception e) {
               if (verbose) {
                   e.printStackTrace();
               } else {
                   System.out.println("Something went wrong! You can drop the file error.txt to report to the developer");
               }
           }
       }
    }
    public void onDisable() {

        System.out.println("noooo unstarted");
    }
}
