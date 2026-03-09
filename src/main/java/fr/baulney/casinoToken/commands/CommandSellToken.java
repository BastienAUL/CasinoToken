package fr.baulney.casinoToken.commands;

import fr.baulney.casinoToken.CasinoToken;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CommandSellToken implements CommandExecutor {

    private final CasinoToken plugin;

    public CommandSellToken(CasinoToken plugin) {
        this.plugin = plugin;
    }

    private final Map<Material, Double> tokenPrices = new HashMap<>() {{
        put(Material.LIME_DYE, 1.0);
        put(Material.GREEN_DYE, 10.0);
        put(Material.LIGHT_BLUE_DYE, 50.0);
        put(Material.BLUE_DYE, 100.0);
        put(Material.YELLOW_DYE, 500.0);
        put(Material.RED_DYE, 1000.0);
        put(Material.WHITE_DYE, 5000.0);
    }};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cCommande uniquement pour les joueurs.");
            return true;
        }

        Economy econ = CasinoToken.getEconomy();
        String ownerName = plugin.getOwner();

        if (ownerName == null || ownerName.equalsIgnoreCase("None")) {
            player.sendMessage("§cLe casino n'a pas de propriétaire défini !");
            return true;
        }

        double totalEarned = 0;

        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) continue;

            Double price = tokenPrices.get(item.getType());
            if (price != null) {
                int amount = item.getAmount();
                double totalPrice = price * amount;

                if (!econ.has(Bukkit.getOfflinePlayer(ownerName), totalPrice)) {
                    player.sendMessage("§cLe propriétaire n'a pas assez d'argent pour racheter tous vos jetons !");
                    continue;
                }

                econ.withdrawPlayer(Bukkit.getOfflinePlayer(ownerName), totalPrice);
                econ.depositPlayer(player, totalPrice);

                totalEarned += totalPrice;

                player.getInventory().remove(item);
            }
        }

        if (totalEarned > 0) {
            player.sendMessage("§aVous avez vendu vos jetons pour §e" + totalEarned + "€");
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        } else {
            player.sendMessage("§cVous n'avez aucun jeton à vendre !");
        }

        return true;
    }
}