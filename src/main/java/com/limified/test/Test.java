package com.limified.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin implements Listener {
    eventmanager manager = new eventmanager();

    public Test() throws IOException {
    }


    public void onEnable() {
        System.out.println("omg starting");
        Bukkit.setMotd("Logger works!!!");

        File playerDir = new File("plugins/playerlogs");
        if (!playerDir.exists()) {
            playerDir.mkdir();
        }

        Bukkit.getPluginManager().registerEvents(manager, this);
    }

    public static void writetologs(String text, String filelocation, String playername) {
        try {
            FileWriter logs = new FileWriter("plugins/playerlogs/" + playername+ "/" + filelocation + "/logs.txt", true);
            FileWriter filelogs = new FileWriter("plugins/playerlogs/" + playername + "/" + "/logs.txt", true);
                logs.write(playername+" has filled a bucket\n");
                filelogs.write(playername+" has filled a bucket\n");
                logs.close();
                filelogs.close();

        } catch (FileNotFoundException var4) {
            System.out.println("no file :(");
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }
    public void onDisable() {

        System.out.println("noooo unstarted");
    }
}
