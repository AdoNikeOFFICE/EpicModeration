package sk.adonikeoffice.epicmoderation.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.SkullCreator;
import org.mineacademy.fo.remain.Remain;
import sk.adonikeoffice.epicmoderation.data.PlayerData;

import java.util.UUID;
import java.util.stream.Collectors;

public class OnlineMenu extends MenuPagged<PlayerData> {

	public OnlineMenu(final Menu parent) {
		super(9 * 2, parent, PlayerData.getPlayers().stream().filter(PlayerData::isOnline).collect(Collectors.toList()));

		setTitle("EpicModeration || Online " + Remain.getOnlinePlayers().size());
	}

	@Override
	protected ItemStack convertToItemStack(final PlayerData player) {
		return ItemCreator.of(SkullCreator.itemFromUuid(UUID.fromString(player.getUuid())))
				.name("&7" + player.getName())
				.lore("", "")
				.makeMenuTool();
	}

	@Override
	protected void onPageClick(final Player player, final PlayerData item, final ClickType click) {

	}

}
