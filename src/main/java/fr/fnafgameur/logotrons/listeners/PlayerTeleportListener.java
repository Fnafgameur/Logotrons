package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerTeleportListener implements Listener {

    private final Main main;

    public PlayerTeleportListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (!main.getConfig().getBoolean("settings.PlayerTeleport")) {
            return;
        }
        Player player = event.getPlayer();
        Location formerLoc = event.getFrom();
        int fx = formerLoc.getBlockX();
        int fy = formerLoc.getBlockY();
        int fz = formerLoc.getBlockZ();
        World fworld = formerLoc.getWorld();
        Location newLoc = event.getTo();
        int nx = newLoc.getBlockX();
        int ny = newLoc.getBlockY();
        int nz = newLoc.getBlockZ();
        World nworld = newLoc.getWorld();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        // LOG TO FILE
        main.logToFile("<" + date + " | " + time + "> " + player.getName() + " teleported from " + fx + " " + fy + " " + fz + " " + fworld.getName() + " to " + nx + " " + ny + " " + nz + " " + nworld.getName(), "PlayerTeleport");
    }
}
