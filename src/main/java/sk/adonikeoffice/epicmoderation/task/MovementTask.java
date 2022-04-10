package sk.adonikeoffice.epicmoderation.task;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.remain.Remain;
import sk.adonikeoffice.epicmoderation.data.PlayerData;

public class MovementTask extends BukkitRunnable {

	@Override
	public void run() {
		for (final Player player : Remain.getOnlinePlayers()) {
			if (!player.isOnline())
				return;
			
			final PlayerData data = PlayerData.findPlayer(player);

			if (PlayerData.isLoaded(player) && data.isFreezed())
				player.teleport(data.getFreezedLocation());
		}
	}

}
