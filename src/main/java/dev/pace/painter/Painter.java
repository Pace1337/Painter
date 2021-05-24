package dev.pace.painter;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Painter extends JavaPlugin {

    private List<Player> painters = new ArrayList<Player>();

    @Override
    public void onEnable() {
        this.getCommand("paint").setExecutor(new PaintCommand(this));
        this.getCommand("painter").setExecutor(new PaintCommand(this));
        this.getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        // Register all commands and register the PlayerInteract event.
    }

    @Override
    public void onDisable() {
    }

    public void addPainter(Player player) {
        painters.add(player);
    }
    public void removePainter(Player player) {
        painters.remove(player);
    }
    public List<Player> getPainters() {
        return painters;
    }

    public boolean hasPainters() {
        if (painters.isEmpty())
            return false;
        return true;
    }
}
