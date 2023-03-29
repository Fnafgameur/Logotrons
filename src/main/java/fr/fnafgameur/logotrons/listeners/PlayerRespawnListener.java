package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerRespawnListener implements Listener {

    private final Main main;

    public PlayerRespawnListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (!main.getConfig().getBoolean("settings.PlayerRespawn")) {
            return;
        }
        Player player = event.getPlayer();
        Location location = event.getRespawnLocation();
        World world = location.getWorld();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        //LOG TO FILE
        main.logToFile("<" + date + " | " + time + "> " + player.getName() + " respawned " + "-> loc= " + x + " " + y + " " + z + " " + world.getName(), "PlayerRespawn");
    }
}
