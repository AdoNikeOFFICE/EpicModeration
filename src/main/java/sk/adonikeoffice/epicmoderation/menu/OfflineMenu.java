package sk.adonikeoffice.epicmoderation.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.SkullCreator;
import org.mineacademy.fo.remain.CompMaterial;
import sk.adonikeoffice.epicmoderation.data.PlayerData;

import java.util.UUID;
import java.util.stream.Collectors;

public class OfflineMenu extends MenuPagged<PlayerData> {

	private final Button refreshMenuButton;

	public OfflineMenu(final Menu parent) {
		super(9 * 2, parent, PlayerData.getPlayers().stream().filter(data -> !data.isOnline()).collect(Collectors.toList()));

		setTitle("EpicModeration || Offline " + PlayerData.getPlayers().stream().filter(data -> !data.isOnline()).count());

		refreshMenuButton = Button.makeSimple(ItemCreator.of(CompMaterial.CLOCK, "&aRefresh The Menu"), (target) -> {
			newInstance().displayTo(target);

			Common.runLater(() -> animateTitle("Menu was refreshed!"));
		});
	}

	@Override
	protected ItemStack convertToItemStack(final PlayerData player) {
		return ItemCreator.of(SkullCreator.itemFromUuid(UUID.fromString(player.getUuid())))
				.name((player.isOnline() ? "&a|| &7" : "&c|| &7") + player.getName())
				.lore("", "")
				.makeMenuTool();
	}

	@Override
	protected void onPageClick(final Player player, final PlayerData item, final ClickType click) {

	}

	@Override
	public ItemStack getItemAt(final int slot) {
		if (slot == 22)
			return refreshMenuButton.getItem();

		return super.getItemAt(slot);
	}

	@Override
	public Menu newInstance() {
		return this;
	}

}
