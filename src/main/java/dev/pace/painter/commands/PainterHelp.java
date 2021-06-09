package dev.pace.painter.commands;

import dev.pace.painter.Painter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class PainterHelp implements CommandExecutor {

    private ArrayList<String> about;
    private final Painter plugin;

    public PainterHelp(final Painter instance) {
        this.about = new ArrayList<String>();
        this.plugin = instance;
    }

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may use this command.");
            return true;
        }
        final Player p = (Player) sender;
        if (!p.hasPermission("paint.use")) {
            this.about.add(p.getName());
            p.sendMessage(ChatColor.RED + "You are not allowed to execute this command. Contact a server administrator if you believe this is an error.");
            return true;
        }
        if (command.getName().equalsIgnoreCase("painthelp")) {
            p.sendMessage(this.cc("&dPainter Help - Current Version: 0.4 - by Pace#0001"));
            p.sendMessage(this.cc("&c--------------------------------------"));
            p.sendMessage(this.cc("&c/painter - &dPainter main command."));
            p.sendMessage(this.cc("&c/paint - &dPainter alternative command."));
            p.sendMessage(this.cc("&c/painthelp - &dShows all the information about the plugin."));
            return true;
        }
        return false;
    }

    public String cc(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}