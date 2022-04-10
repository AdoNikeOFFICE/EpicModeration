package sk.adonikeoffice.epicmoderation.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.mineacademy.fo.TimeUtil;
import sk.adonikeoffice.epicmoderation.data.PlayerData;

public class ModerationListener implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(final PlayerJoinEvent event) {
		final Player player = event.getPlayer();
		final PlayerData data = PlayerData.findPlayer(player);

		if (data == null && !PlayerData.isLoaded(player))
			PlayerData.createPlayer(player);
		else if (PlayerData.isLoaded(player) && data != null)
			data.setOnline(true);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onQuit(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		final PlayerData data = PlayerData.findPlayer(player);

		if (data != null && PlayerData.isLoaded(player)) {
			data.setLastJoin(TimeUtil.getFormattedDate());
			data.setOnline(false);
		}
	}

	@EventHandler
	public void onBlockBreak(final BlockBreakEvent event) {
		final Player player = event.getPlayer();
		final PlayerData data = PlayerData.findPlayer(player);

		if (data.isFreezed())
			event.setCancelled(true);
	}

}
