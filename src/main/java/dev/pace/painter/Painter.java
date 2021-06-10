package dev.pace.painter;

import dev.pace.painter.commands.Paint;
import dev.pace.painter.commands.PainterHelp;
import dev.pace.painter.events.PlayerInteract;
import dev.pace.painter.updatechecker.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Painter extends JavaPlugin {

    private final List<Player> painters = new ArrayList<Player>();

    @Override
    public void onEnable() {
        this.getCommand("paint").setExecutor(new Paint(this));
        this.getCommand("painter").setExecutor(new Paint(this));
        this.getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        getCommand("painthelp").setExecutor(new PainterHelp(this));
        getCommand("painterhelp").setExecutor(new PainterHelp(this));
        Logger logger = this.getLogger();
        int pluginId = 11632;
        Metrics metrics = new Metrics(this, 11632);

        new UpdateChecker(this, 92651).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("Painter is up to date!");
            } else {
                logger.info("New update available for Painter. https://www.spigotmc.org/resources/painter.92651/");
            }
        });
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
