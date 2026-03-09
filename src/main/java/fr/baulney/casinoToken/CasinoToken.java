package fr.baulney.casinoToken;

import fr.baulney.casinoToken.commands.CommandBuyToken;
import fr.baulney.casinoToken.commands.CommandOwner;
import fr.baulney.casinoToken.commands.CommandSetOwner;
import fr.baulney.casinoToken.commands.CommandSellToken;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class CasinoToken extends JavaPlugin {

    private static Economy econ = null;

    @Override
    public void onEnable() {

        getLogger().info("CasinoTokens activé !");
        saveDefaultConfig();

        if (!setupEconomy()) {
            getLogger().severe("Vault ou un plugin d'économie est manquant !");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        CommandBuyToken commandBuyToken = new CommandBuyToken();

        Objects.requireNonNull(getCommand("buytoken")).setExecutor(commandBuyToken);
        Bukkit.getPluginManager().registerEvents(commandBuyToken, this);
        Objects.requireNonNull(getCommand("selltoken")).setExecutor(new CommandSellToken(this));
        Objects.requireNonNull(getCommand("owner")).setExecutor(new CommandOwner(this));
        Objects.requireNonNull(getCommand("setowner")).setExecutor(new CommandSetOwner(this));

    }

    @Override
    public void onDisable() {
        getLogger().info("CasinoTokens désactivé !");
    }

    private boolean setupEconomy() {

        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp =
                getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            return false;
        }

        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public String getOwner() {
        return getConfig().getString("casino-owner");
    }

    public void setOwner(String owner) {
        getConfig().set("casino-owner", owner);
        saveConfig();
    }
}