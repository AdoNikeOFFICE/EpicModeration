package sk.adonikeoffice.epicmoderation.util;

import org.bukkit.entity.Player;
import org.mineacademy.fo.menu.Menu;

public class MenuUtil {
	public static void reopenMenu(final Menu menu, final Player player) {
		player.closeInventory();

		menu.displayTo(player);
	}
}
