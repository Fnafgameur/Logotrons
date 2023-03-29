package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import fr.fnafgameur.logotrons.managers.BlockManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerBreakBlockListener implements Listener {

    private final Main main;
    private final BlockManager blockManager;

    public PlayerBreakBlockListener(Main main, BlockManager blockManager) {
        this.main = main;
        this.blockManager = blockManager;
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        if (!main.getConfig().getBoolean("settings.BreakBlock")) {
            return;
        }
        Block block = event.getBlock();
        // check if the broken block is registered in the config file
        if (!main.getConfig().getConfigurationSection("blocks").getKeys(false).contains(block.getType().name())) {
            return;
        }

        if (blockManager.getBlockPlaced().containsKey(block.getLocation())) {
            blockManager.removeBlockPlaced(block);
            return;
        }

        Player player = event.getPlayer();
        Location loc = block.getLocation();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        long x = Math.round(loc.getX());
        long y = Math.round(loc.getY());
        long z = Math.round(loc.getZ());
        String world = loc.getWorld().getName();
        main.logToFile("<" + date + " | " + time + "> " + "[" + player.getName() + "] -> " + "broke " + block.getType().name().toLowerCase().replace("_", " ") + " -> loc= " + x + " " + y + " " + z + " " + world, "BlockBreak");
    }
}
