package dev.pace.painter;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PaintCommand implements CommandExecutor {

    private Painter plugin;
    public PaintCommand(Painter plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
        // Console
        sender.sendMessage("Only players are allowed to run this command.");
        return true;
        //TODO Add player permission so it cannot be abused.
    }
    Player player = (Player) sender;
    if (plugin.hasPainters()) {
        if (plugin.getPainters().contains(player)) {
            // Remove the painting player :thumb:.
            plugin.removePainter(player);
            player.sendMessage(ChatColor.RED + "You can no longer paint.");
            return true;
        }
    }
    plugin.addPainter(player);
    player.sendMessage(ChatColor.GREEN + "You are now able to paint.");
    return true;
        }
    }