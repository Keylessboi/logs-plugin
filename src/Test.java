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
    public boolean moves = true;
    public boolean items = true;
    public boolean blocks = true;
    public boolean entities = true;
    Zips zipper = new Zips();
    eventmanager manager = new eventmanager();

    public Test() throws IOException {
    }

    public void onEnable() {
        System.out.println("omg starting");
        Bukkit.setMotd("Logger works!!!");

        try {
            this.zipper.unzip("plugins/playerlogs");
        } catch (IOException var3) {
            File playerDir = new File("plugins/playerlogs");
            if (!playerDir.exists()) {
                playerDir.mkdir();
            }
        }

        Bukkit.getPluginManager().registerEvents(manager, this);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerMoveEvent e) {
        File playerDir = new File("plugins/playerlogs/" + e.getPlayer().getName());
        if (!playerDir.exists()) {
            playerDir.mkdir();
        }

        File entityinteractions;
        File entityfile;
        if (this.moves) {
            entityinteractions = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/moves");
            if (!entityinteractions.exists()) {
                entityinteractions.mkdir();
            }

            try {
                entityfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/moves/logs.txt");
                if (entityfile.createNewFile()) {
                    System.out.println("File created: " + entityfile.getName());
                } else {
                    System.out.println("Moves file already exists");
                }
            } catch (IOException var7) {
                System.out.println("An error occurred.");
                var7.printStackTrace();
            }
        }

        if (this.items) {
            entityinteractions = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/iteminteractions");
            if (!entityinteractions.exists()) {
                entityinteractions.mkdir();
            }

            try {
                entityfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/iteminteractions/logs.txt");
                if (entityfile.createNewFile()) {
                    System.out.println("File created: " + entityfile.getName());
                } else {
                    System.out.println("Items file already exists");
                }
            } catch (IOException var6) {
                System.out.println("An error occurred.");
                var6.printStackTrace();
            }
        }

        if (this.entities) {
            entityinteractions = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/entityinteractions");
            if (!entityinteractions.exists()) {
                entityinteractions.mkdir();
            }

            try {
                entityfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/entityinteractions/logs.txt");
                if (entityfile.createNewFile()) {
                    System.out.println("File created: " + entityfile.getName());
                } else {
                    System.out.println("Entity file already exists");
                }
            } catch (IOException var5) {
                System.out.println("An error occurred.");
                var5.printStackTrace();
            }
        }

        new File("plugins/playerlogs/" + e.getPlayer().getName() + "/logs.txt");
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        try {
            FileWriter logs = new FileWriter("plugins/playerlogs/" + e.getPlayer().getName() + "/logs.txt", true);
            FileWriter movelogs = new FileWriter("plugins/playerlogs/" + e.getPlayer().getName() + "/moves/logs.txt", true);
            logs.write(e.getPlayer().getName() + " moved from " + e.getFrom() + " to " + e.getTo() + "\n");
            movelogs.write(e.getPlayer().getName() + " moved from " + e.getFrom() + " to " + e.getTo() + "\n");
            logs.close();
            movelogs.close();
        } catch (FileNotFoundException var4) {
            System.out.println("no file :(");
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(e.getPlayer().getName());
    }

    @EventHandler
    public void PlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        try {
            FileWriter logs = new FileWriter("plugins/playerlogs/" + e.getPlayer().getName() + "/logs.txt", true);
            new FileWriter("plugins/playerlogs/" + e.getPlayer().getName() + ",moves/moves.txt");
            logs.write(e.getPlayer().getName() + " got out of bed: " + e.getBed() + "\n");
            logs.close();
        } catch (FileNotFoundException var4) {
            System.out.println("no file :(");
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        System.out.println(e.getPlayer().getName());
    }

    @EventHandler
    public void PlayerAnimationEvent(PlayerAnimationEvent e) {
        e.setCancelled(true);
    }

    public void onDisable() {
        try {
            String sourceFile = "plugins/playerlogs";
            FileOutputStream fos = new FileOutputStream("plugins/old_player_logs.zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            Zips.zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
        } catch (IOException var5) {
            System.out.println("Something went wrong while zipping");
        }

        System.out.println("noooo unstarted");
    }
}
