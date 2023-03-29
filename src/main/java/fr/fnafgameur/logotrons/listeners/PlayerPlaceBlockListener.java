package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import fr.fnafgameur.logotrons.managers.BlockManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerPlaceBlockListener implements Listener {

    private final Main main;
    private final BlockManager blockManager;

    public PlayerPlaceBlockListener(Main main, BlockManager blockManager) {
        this.main = main;
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        if (!main.getConfig().getBoolean("settings.PlaceBlock")) {
            return;
        }
        Block block = event.getBlock();
        Player player = event.getPlayer();
        Location loc = block.getLocation();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        long x = Math.round(loc.getX());
        long y = Math.round(loc.getY());
        long z = Math.round(loc.getZ());
        String world = loc.getWorld().getName();
        main.logToFile("<" + date + " | " + time + "> " + "[" + player.getName() + "] -> " + "placed " + block.getType().name().toLowerCase().replace("_", " ") + " -> loc= " + x + " " + y + " " + z + " " + world, "BlockBreak");
        blockManager.addBlockPlaced(block, loc);
    }
}
