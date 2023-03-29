package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerQuitListener implements Listener {

    private final Main main;

    public PlayerQuitListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!main.getConfig().getBoolean("settings.PlayerQuit")) {
            return;
        }
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = player.getWorld();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //LOG TO FILE
        main.logToFile("<" + date + " | " + time + "> " + player.getName() + " left the server" + " -> loc= " + x + " " + y + " " + z + " " + world.getName(), "PlayerQuit");
    }
}
