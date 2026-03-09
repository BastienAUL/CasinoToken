package fr.baulney.casinoToken.commands;

import fr.baulney.casinoToken.CasinoToken;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class CommandBuyToken implements CommandExecutor, Listener {

    CasinoToken plugin = JavaPlugin.getPlugin(CasinoToken.class);
    String OWNER = plugin.getOwner();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Commande uniquement pour les joueurs.");
            return true;
        }

        String ownerName = plugin.getOwner();
        if (ownerName == null || ownerName.equalsIgnoreCase("None")) {
            player.sendMessage("§cLe casino n'a pas de propriétaire défini. Le propriétaire doit être défini avec /owner !");
            return true;
        }

        Inventory inventory = Bukkit.createInventory(null, 27, "CasinoTokens");

        inventory.setItem(10, addGlow(createDye(Material.LIME_DYE, "§a1€ Jeton", "§7Jeton à utiliser au Casino")));
        inventory.setItem(11, addGlow(createDye(Material.GREEN_DYE, "§210€ Jeton", "§7Jeton à utiliser au Casino")));
        inventory.setItem(12, addGlow(createDye(Material.LIGHT_BLUE_DYE, "§b50€ Jeton", "§7Jeton à utiliser au Casino")));
        inventory.setItem(13, addGlow(createDye(Material.BLUE_DYE, "§1100€ Jeton", "§7Jeton à utiliser au Casino")));
        inventory.setItem(14, addGlow(createDye(Material.YELLOW_DYE, "§e500€ Jeton", "§7Jeton à utiliser au Casino")));
        inventory.setItem(15, addGlow(createDye(Material.RED_DYE, "§41000€ Jeton", "§7Jeton à utiliser au Casino")));
        inventory.setItem(16, addGlow(createDye(Material.WHITE_DYE, "§f5000€ Jeton", "§7Jeton à utiliser au Casino")));

        player.openInventory(inventory);

        return true;
    }

    private ItemStack createDye(Material material, String name, String lore) {

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Collections.singletonList(lore));
            item.setItemMeta(meta);
        }

        return item;
    }

    private ItemStack addGlow(ItemStack item) {

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }

        return item;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        HumanEntity clicker = event.getWhoClicked();

        if (!(clicker instanceof Player player)) return;

        if (!event.getView().getTitle().equals("CasinoTokens")) return;

        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();

        if (item == null || item.getType() == Material.AIR) return;

        Economy econ = CasinoToken.getEconomy();

        double price = 0;

        switch (item.getType()) {
            case LIME_DYE -> price = 1;
            case GREEN_DYE -> price = 10;
            case LIGHT_BLUE_DYE -> price = 50;
            case BLUE_DYE -> price = 100;
            case YELLOW_DYE -> price = 500;
            case RED_DYE -> price = 1000;
            case WHITE_DYE -> price = 5000;
        }

        if (price == 0) return;

        if (!econ.has(player, price)) {

            player.sendMessage("§cVous n'avez pas assez d'argent !");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
            return;
        }

        econ.withdrawPlayer(player, price);

        String ownerName = plugin.getOwner();

        if (ownerName != null && !ownerName.equalsIgnoreCase("None")) {
            econ.depositPlayer(Bukkit.getOfflinePlayer(ownerName), price);
        }

        player.getInventory().addItem(item.clone());

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        player.sendMessage("§aVous avez acheté un jeton pour §e" + price + "€");
    }
}