package dev.pace.painter;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.util.BlockIterator;

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

        Block bl = traceBlock(event.getPlayer());
        if(bl == null)
            return;
        bl.setType(event.getPlayer().getItemInHand().getType());
        MaterialData d = event.getPlayer().getItemInHand().getData();
        bl.setData(d.getData());

        event.setCancelled(true);
    }
    private Block traceBlock(Player target) {
        BlockIterator it = new BlockIterator(target, 50);
        int i = 0;
        while (it.hasNext() && i < 50) {
            Block block = it.next();
            if (block.getType() != Material.AIR) {
                return block;
            }
            i++;
        }
        return null;
    }
}