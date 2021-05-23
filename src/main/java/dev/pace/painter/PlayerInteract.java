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
    }
    private Block traceBlock(CommandSender sender, Player target, boolean fromTarget) throws CommandException {
        Player source = getSource(sender, target, fromTarget);
        BlockIterator it = new BlockIterator(source);
        int i = 0;
        while (it.hasNext() && i < MAX_TRACE_DISTANCE) {
            Block block = it.next();
            if (block.getType() != Material.AIR) {
                return block;
            }
            i++;
        }
        throw new CommandException("Not currently looking at a block that is close enough.");
    }
}