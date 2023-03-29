package fr.fnafgameur.logotrons;

import fr.fnafgameur.logotrons.commands.LogotronsCommand;
import fr.fnafgameur.logotrons.commands.LogotronsTabCompleter;
import fr.fnafgameur.logotrons.listeners.*;
import fr.fnafgameur.logotrons.managers.BlockManager;
import fr.mrmicky.fastinv.FastInvManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class Main extends JavaPlugin {

    private final BlockManager blockManager = new BlockManager();

    //TODO: TESTER LE PLUGIN, LES CMDS POUR AJOUTER UN EVENT A REGISTER...
    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerListeners();
        registerCommands();
        registerEvents();
        FastInvManager.register(this);
        Bukkit.getLogger().info("Logotrons is enabled !");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Logotrons is disabled !");
    }

    public void registerCommands() {
        getCommand("logotrons").setExecutor(new LogotronsCommand(this));
        getCommand("logotrons").setTabCompleter(new LogotronsTabCompleter(this));
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerBreakBlockListener(this, blockManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerPlaceBlockListener(this, blockManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSendCommandListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerTeleportListener(this), this);
    }

    public void logToFile(String message, String event) {
        String saveName = "";

        try {
            File dataFolder = getDataFolder();
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            if(event.equalsIgnoreCase("BlockBreak")) {
                saveName = "BlocsLogs.txt";
            } else if(event.equalsIgnoreCase("PlayerDeath")) {
                saveName = "PlayerDeath.txt";
            } else if(event.equalsIgnoreCase("PlayerJoin")) {
                saveName = "PlayerJoin.txt";
            } else if (event.equalsIgnoreCase("PlayerQuit")) {
                saveName = "PlayerQuit.txt";
            } else if (event.equalsIgnoreCase("PlayerRespawn")) {
                saveName = "PlayerRespawn.txt";
            } else if (event.equalsIgnoreCase("PlayerSendCommand")) {
                saveName = "PlayerSendCommand.txt";
            } else if (event.equalsIgnoreCase("PlayerTeleport")) {
                saveName = "PlayerTeleport.txt";
            }
            File saveTo = new File(dataFolder, saveName);
            if (!saveTo.exists()) {
                try {
                    saveTo.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter fw = null;
            try {
                fw = new FileWriter(saveTo, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerListeners() {
        if (this.getConfig().get("settings.BreakBlock") == null) {
            this.getConfig().set("settings.BreakBlock", false);
        }
        if (this.getConfig().get("settings.PlaceBlock") == null) {
            this.getConfig().set("settings.PlaceBlock", false);
        }
        if (this.getConfig().get("settings.PlayerDeath") == null) {
            this.getConfig().set("settings.PlayerDeath", false);
        }
        if (this.getConfig().get("settings.PlayerJoin") == null) {
            this.getConfig().set("settings.PlayerJoin", false);
        }
        if (this.getConfig().get("settings.PlayerQuit") == null) {
            this.getConfig().set("settings.PlayerQuit", false);
        }
        if (this.getConfig().get("settings.PlayerRespawn") == null) {
            this.getConfig().set("settings.PlayerRespawn", false);
        }
        if (this.getConfig().get("settings.PlayerSendCommand") == null) {
            this.getConfig().set("settings.PlayerSendCommand", false);
        }
        if (this.getConfig().get("settings.PlayerTeleport") == null) {
            this.getConfig().set("settings.PlayerTeleport", false);
        }
        this.saveConfig();
    }
}
