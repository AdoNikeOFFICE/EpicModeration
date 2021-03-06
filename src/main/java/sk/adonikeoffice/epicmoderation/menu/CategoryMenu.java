/*package sk.adonikeoffice.epicmoderation.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.MenuPagged;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.menu.model.SkullCreator;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.remain.Remain;
import sk.adonikeoffice.epicmoderation.data.PlayerData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CategoryMenu extends MenuPagged<PlayerData> {

	public CategoryMenu(final Menu parent, final boolean online) {
		super(9 * 2, parent, PlayerData.getPlayers().stream().filter(data -> online == data.isOnline()).collect(Collectors.toList()));

		setTitle("EpicModeration || " + (online ? "Online " + Remain.getOnlinePlayers().size() : "Offline " + PlayerData.getPlayers().stream().filter(data -> !data.isOnline()).count()));
	}

	@Override
	protected ItemStack convertToItemStack(final PlayerData data) {
		if (data.getName().equals(getViewer().getName()))
			return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE, "&cThis Is You &4(" + data.getName() + ")")
					.lore(
							getLore(data)
					)
					.makeMenuTool();

		return ItemCreator.of(SkullCreator.itemFromUuid(UUID.fromString(data.getUuid())))
				.name((data.isOnline() ? "&a||" : "&c||") + " &7" + data.getName())
				.lore(
						getLore(data)
				)
				.makeMenuTool();
	}

	private static List<String> getLore(final PlayerData data) {
		return Arrays.asList(
				"",
				"&2&l* &fUUID &a" + data.getUuid(),
				"&2&l* &fIP &a" + data.getIp(),
				"&2&l* &fFIRST_JOIN &a" + data.getFirstJoin(),
				"&2&l* &fLAST_JOIN &a" + data.getLastJoin(),
				""
		);
	}

	@Override
	protected void onPageClick(final Player player, final PlayerData item, final ClickType click) {
		if (item.getName().equals(player.getName()))
			return;

		new OptionsMenu(this, player).displayTo(player);
	}

	@Override
	public ItemStack getItemAt(final int slot) {
		return super.getItemAt(slot);
	}

	@Override
	public Menu newInstance() {
		return this;
	}

}*/
