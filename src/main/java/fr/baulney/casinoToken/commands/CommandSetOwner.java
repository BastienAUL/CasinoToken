package fr.baulney.casinoToken.commands;

import fr.baulney.casinoToken.CasinoToken;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandSetOwner implements CommandExecutor {

    private final CasinoToken plugin;

    public CommandSetOwner(CasinoToken plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("casino.owner")) {
            sender.sendMessage("§cVous n'avez pas la permission.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage("§cUsage : /setowner <pseudo>");
            return true;
        }

        plugin.setOwner(args[0]);

        sender.sendMessage("§aLe propriétaire du casino est maintenant : §e" + args[0]);

        return true;
    }
}