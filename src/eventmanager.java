package com.limified.test;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class eventmanager implements Listener {
    public boolean moves = true;
    public boolean items = true;
    public boolean blocks = true;
    public boolean entities = true;
    Zips zipper = new Zips();

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
}
