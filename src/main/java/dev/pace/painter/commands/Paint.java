package dev.pace.painter.commands;

import dev.pace.painter.Painter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Paint implements CommandExecutor {

    private Painter plugin;
    public Paint(Painter plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
        sender.sendMessage("Only players are allowed to run this command.");
        return true;
    }
    Player player = (Player) sender;
        if (!player.hasPermission("paint.use")||!player.isOp()) {
            player.sendMessage(ChatColor.RED + "You do not have permission to execute this command, contact server administrators if you believe this is an error.");
            return true;
        }
    if (plugin.hasPainters()) {
        if (plugin.getPainters().contains(player)) {
            // Remove the painting player.
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
