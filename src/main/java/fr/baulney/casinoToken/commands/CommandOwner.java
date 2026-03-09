package fr.baulney.casinoToken.commands;

import fr.baulney.casinoToken.CasinoToken;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandOwner implements CommandExecutor {

    private final CasinoToken plugin;

    public CommandOwner(CasinoToken plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String owner = plugin.getOwner();

        if (owner == null || owner.equalsIgnoreCase("None")) {
            sender.sendMessage("§cAucun propriétaire n'est défini pour le casino.");
        } else {
            sender.sendMessage("§aLe propriétaire actuel du casino est : §e" + owner);
        }

        return true;
    }
}