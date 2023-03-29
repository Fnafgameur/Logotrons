package fr.fnafgameur.logotrons.commands;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LogotronsCommand implements CommandExecutor {

    private final Main main;

    public LogotronsCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            return false;
        }
        if (!player.hasPermission("logotrons.admin")) {
            player.sendMessage("§cYou don't have permission to use this command !");
            return false;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            player.sendMessage("§6§lLogotrons Help:");
            player.sendMessage("§b/logotrons addblock §7- §6Add the held block to the list of registered blocks");
            player.sendMessage("§b/logotrons settings §7- §6Allows you to change the event registered by the plugin");
            player.sendMessage("§b/logotrons removeblock §7- §6Remove the held block from the list of registered blocks");
            return false;
        }
        if (args[0].equalsIgnoreCase("addblock")) {
            // check if player hold a block
            if (!player.getInventory().getItemInMainHand().getType().isBlock()) {
                player.sendMessage("§cYou must hold a block in your hand !");
                return false;
            }
            if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                player.sendMessage("§cYou must hold a block in your hand !");
                return false;
            }
            // check if block is already registered
            if (main.getConfig().contains("blocks." + player.getInventory().getItemInMainHand().getType().name())) {
                player.sendMessage("§cThis block is already registered !");
                return false;
            }
            // add block to config
            main.getConfig().set("blocks." + player.getInventory().getItemInMainHand().getType(), player.getInventory().getItemInMainHand().getType().name().toLowerCase());
            main.saveConfig();
            player.sendMessage("§aThe block §6" + player.getInventory().getItemInMainHand().getType().name().toLowerCase().replace("_"," ") + "§a has been registered !");
            return false;
        }
        if (args[0].equalsIgnoreCase("removeblock")) {
            // check if block is already registered
            if (args[1].isBlank()) {
                player.sendMessage("§cYou must specify a block !");
                return false;
            }
            if (!main.getConfig().contains("blocks." + args[1])) {
                player.sendMessage("§cThis block is not registered !");
                return false;
            }
            // remove block from config
            main.getConfig().set("blocks." + args[1], null);
            main.saveConfig();
            player.sendMessage("§aThe block §6" + args[1].toLowerCase().replace("_", " ") + "§a has been unregistered !");
            return false;
        }
        if (args[0].equalsIgnoreCase("settings")) {
            if (args[1].isBlank()) {
                player.sendMessage("§ctype /logotrons settings help to list all settings commands");
                return false;
            }
            if (args[1].isBlank() || args[1].equalsIgnoreCase("help")) {
                player.sendMessage("§6§lLogotrons Settings Help:");
                player.sendMessage("§b/logotrons settings list §7- §6List of all settings");
                player.sendMessage("§b/logotrons settings <setting> <true/false> §7- §6Enable/disable a registered event");
                return false;
            }
            if (args[1].equalsIgnoreCase("list")) {
                player.sendMessage("§6§lLogotrons Settings List :");
                player.sendMessage("§bBreakBlock §7- §6Register information about the block broken");
                player.sendMessage("§bPlaceBlock §7- §6Register information about the block placed");
                player.sendMessage("§bPlayerDeath §7- §6Register information about the latest death of a player");
                player.sendMessage("§bPlayerRespawn §7- §6Register information about the latest respawn of a player");
                player.sendMessage("§bPlayerTeleport §7- §6Register information about the latest teleport of a player");
                player.sendMessage("§bPlayerJoin §7- §6Register information about the latest connection of a player");
                player.sendMessage("§bPlayerQuit §7- §6Register information about the latest disconnection of a player");
                player.sendMessage("§bPlayerCommand §7- §6Register information about the latest used command by a player");
                return false;
            }
            if (args[1].equalsIgnoreCase("enable") || args[1].equalsIgnoreCase("disable")) {
                if (args[2].isBlank()) {
                    player.sendMessage("§cYou must specify a setting !");
                    return false;
                }
                switch (args[2]) {
                    case "BreakBlock":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.BreakBlock", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6BreakBlock§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.BreakBlock", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6BreakBlock§a has been disabled !");
                            return true;
                        }
                    case "PlaceBlock":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlaceBlock", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlaceBlock§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlaceBlock", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlaceBlock§a has been disabled !");
                            return true;
                        }
                    case "PlayerTeleport":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlayerTeleport", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerTeleport§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlayerTeleport", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerTeleport§a has been disabled !");
                            return true;
                        }
                    case "PlayerJoin":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlayerJoin", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerJoin§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlayerJoin", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerJoin§a has been disabled !");
                            return true;
                        }
                    case "PlayerQuit":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlayerQuit", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerQuit§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlayerQuit", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerQuit§a has been disabled !");
                            return true;
                        }
                    case "PlayerDeath":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlayerDeath", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerDeath§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlayerDeath", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerDeath§a has been disabled !");
                            return true;
                        }
                    case "PlayerRespawn":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlayerRespawn", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerRespawn§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlayerRespawn", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerRespawn§a has been disabled !");
                            return true;
                        }
                    case "PlayerCommand":
                        if (args[1].equalsIgnoreCase("enable")) {
                            main.getConfig().set("settings.PlayerCommand", true);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerCommand§a has been enabled !");
                            return true;
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            main.getConfig().set("settings.PlayerCommand", false);
                            main.saveConfig();
                            player.sendMessage("§aThe event §6PlayerCommand§a has been disabled !");
                            return true;
                        }
                    case "all":
                        if (args[1].equalsIgnoreCase("enable")) {
                            for (String settings : main.getConfig().getConfigurationSection("settings").getKeys(false)) {
                                main.getConfig().set("settings." + settings, true);
                                main.saveConfig();
                            }
                            player.sendMessage("§aAll events have been enabled !");
                        }
                        if (args[1].equalsIgnoreCase("disable")) {
                            for (String settings : main.getConfig().getConfigurationSection("settings").getKeys(false)) {
                                main.getConfig().set("settings." + settings, false);
                                main.saveConfig();
                            }
                            player.sendMessage("§aAll events have been disabled !");
                        }
                }
            }
        }
        return false;
    }
}
