package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class PlayerJoinListener implements Listener {

    private final Main main;

    public PlayerJoinListener(Main main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!main.getConfig().getBoolean("settings.PlayerJoin")) {
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
        UUID playerUUID = player.getUniqueId();
        String playerIP = player.getAddress().getHostName();
        //LOG TO FILE
        main.logToFile("<" + date + " | " + time + "> " + player.getName() + " joined the server" + " -> loc= " + x + " " + y + " " + z + " " + world.getName() + " IP= " + playerIP + " UUID= " + playerUUID, "PlayerJoin");
    }
}
