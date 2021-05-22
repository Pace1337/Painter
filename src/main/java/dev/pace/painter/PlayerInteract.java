package dev.pace.painter;

import jdk.nashorn.internal.ir.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    private Painter plugin;
    public PlayerInteract(Painter plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPaint(PlayerInteractEvent event) {
        if (!event.hasItem())
            return;
        if (!event.getItem().getType().isBlock())
            return;
        if (!plugin.hasPainters())
            return;
        if (!plugin.getPainters().contains(event.getPlayer()))
            return;
        if (event.getPlayer().rayTraceBlocks(50) == null)
            return;

        event.setCancelled(true);

        Block block = (Block) event.getPlayer().rayTraceBlocks(50).getHitBlock();
        ((org.bukkit.block.Block) block).setType(event.getItem().getType());
    }
}