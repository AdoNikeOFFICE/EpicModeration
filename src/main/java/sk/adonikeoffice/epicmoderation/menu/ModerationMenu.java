/*package sk.adonikeoffice.epicmoderation.menu;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.SkullCreator;
import org.mineacademy.fo.remain.Remain;

public class ModerationMenu extends MenuPagged<OfflinePlayer> {

	public ModerationMenu() {
		super(9 * 2, Bukkit.getOfflinePlayers());

		setTitle("EpicModeration || Online " + Remain.getOnlinePlayers().size());
	}

	@Override
	protected ItemStack convertToItemStack(final OfflinePlayer player) {
		return ItemCreator.of(SkullCreator.itemFromUuid(player.getUniqueId()))
				.name((player.isOnline() ? "&a|| &7" : "&c|| &7") + player.getName())
				.lore("", "")
				.makeMenuTool();
	}

	@Override
	protected void onPageClick(final Player player, final OfflinePlayer item, final ClickType click) {

	}

	@Override
	protected String[] getInfo() {
		return new String[]{
				"In This Menu You Can See",
				"All Players That Are",
				"Online Or Offline And",
				"You Can Moderate Them"
		};
	}

}
*/