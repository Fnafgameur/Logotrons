package fr.fnafgameur.logotrons.commands;

import fr.fnafgameur.logotrons.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogotronsTabCompleter implements TabCompleter {

    private final Main main;

    public LogotronsTabCompleter(Main main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> list = new ArrayList<>();
        List<String> completions = new ArrayList<>();
        int arg = 0;

        if (args.length == 1) {
            list.add("addblock");
            list.add("removeblock");
            list.add("settings");
        }
        if (args.length == 2) {
            arg = 1;
            if (args[0].equalsIgnoreCase("remove")) {
                list = main.getConfig().getConfigurationSection("blocks").getKeys(false).stream().collect(Collectors.toList());
            }
            if (args[0].equalsIgnoreCase("settings")) {
                list.add("enable");
                list.add("disable");
                list.add("list");
            }
        }
        if (args.length == 3) {
            arg = 2;
            if (args[0].equalsIgnoreCase("settings") && args[1].equalsIgnoreCase("enable")) {
                // get all the disabled settings
                list = main.getConfig().getConfigurationSection("settings").getKeys(false).stream().filter(s -> !main.getConfig().getBoolean("settings." + s)).collect(Collectors.toList());
            }
            if (args[0].equalsIgnoreCase("settings") && args[1].equalsIgnoreCase("disable")) {
                // get all the enabled settings
                list = main.getConfig().getConfigurationSection("settings").getKeys(false).stream().filter(s -> main.getConfig().getBoolean("settings." + s)).collect(Collectors.toList());
            }
        }

        StringUtil.copyPartialMatches(args[arg], list, completions);
        Collections.sort(completions);

        return completions;
    }
}