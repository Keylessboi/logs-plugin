package com.limified.logs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class eventmanager implements Listener {

    public boolean moves = true;
    public boolean walks = false;
    public boolean items = true;
    public boolean blocks = true;
    public boolean entities = true;


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
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
            entityinteractions = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/items");
            if (!entityinteractions.exists()) {
                entityinteractions.mkdir();
            }

            try {
                entityfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/items/logs.txt");
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
            entityinteractions = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/entities");
            if (!entityinteractions.exists()) {
                entityinteractions.mkdir();
            }

            try {
                entityfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/entities/logs.txt");
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
        if (this.blocks) {
            File blockinteractions = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/blocks");
            if (!blockinteractions.exists()) {
                blockinteractions.mkdir();
            }

            try {
                File blockfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/blocks/logs.txt");
                if (blockfile.createNewFile()) {
                    System.out.println("File created: " + blockfile.getName());
                } else {
                    System.out.println("Entity file already exists");
                }
            } catch (IOException var5) {
                System.out.println("An error occurred.");
                var5.printStackTrace();
            }
        }

        File logsfile = new File("plugins/playerlogs/" + e.getPlayer().getName() + "/logs.txt");
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        try {
            if (walks) {
                FileWriter logs = new FileWriter("plugins/playerlogs/" + e.getPlayer().getName() + "/logs.txt", true);
                FileWriter movelogs = new FileWriter("plugins/playerlogs/" + e.getPlayer().getName() + "/moves/logs.txt", true);
                logs.write(e.getPlayer().getName() + " moved from " + e.getFrom() + " to " + e.getTo() + "\n");
                movelogs.write(e.getPlayer().getName() + " moved from " + e.getFrom() + " to " + e.getTo() + "\n");
                logs.close();
                movelogs.close();
            }
        } catch (FileNotFoundException var4) {
            System.out.println("no file :(");
            var4.printStackTrace();
        } catch (Exception var5) {
            var5.printStackTrace();
        }
    }

    @EventHandler
    public void PlayerBedLeaveEvent(PlayerBedLeaveEvent e) {
        try {
            if (moves) {
                Main.writetologs(e.getPlayer().getName()+" left bed "+e.getBed(),"moves",e.getPlayer().getName());
            }
        } catch (Exception Ex) {
            System.out.println("please report this to the developer of this plugin");
            Ex.printStackTrace();
        }
    }

    @EventHandler
    public void PlayerAnimationEvent(PlayerAnimationEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void PlayerBucketFillEvent(PlayerBucketFillEvent e) {
        try {
            if (items) {
                Main.writetologs(e.getPlayer().getName()+" filled a bucket: "+e.getBucket(), "items", e.getPlayer().getName());
            }
        } catch (Exception Ex) {
            System.out.println("report this issue to the developer of this plugin");
            Ex.printStackTrace();
        }
    }
    @EventHandler
    public void PlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {
        if (items) {
            Main.writetologs(e.getPlayer().getName()+ " has emptied their bucket: "+e.getBucket(),"items",e.getPlayer().getName());
        }
    }
    @EventHandler
    public void PlayerBedEnterEvent(PlayerBedEnterEvent e) {
        if (moves) {
            Main.writetologs(e.getPlayer().getName()+ " has left their bed: "+e.getBed()+" located at "+e.getBed().getLocation(),"moves",e.getPlayer().getName());
        }
    }

}
