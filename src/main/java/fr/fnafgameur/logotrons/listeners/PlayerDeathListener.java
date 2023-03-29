package fr.fnafgameur.logotrons.listeners;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerDeathListener implements Listener {

    private final Main main;

    public PlayerDeathListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        //check if the event is enabled in the config
        if (!main.getConfig().getBoolean("settings.PlayerDeath")) {
            return;
        }
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        Location location = victim.getLocation();
        World world = victim.getWorld();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        EntityDamageEvent cause = victim.getLastDamageCause(); // Check si ca donne bien la cause en RAPPORT avec la mort qu'il y eut
        //LOG TO FILE
        if (killer != null) {
            main.logToFile("<" + date + " | " + time + "> " + "[" + victim.getName() + "] " + "got killed by " + killer.getName() + " -> loc= " + x + " " + y + " " + z + " " + world.getName() + " of " + cause.getCause(), "PlayerDeath");
            return;
        }
        main.logToFile("<" + date + " | " + time + "> " + "[" + victim.getName() + "]" + " -> loc= " + x + " " + y + " " + z + " " + world.getName() + " died of " + cause.getCause(), "PlayerDeath");
    }
}
