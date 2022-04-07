package sk.adonikeoffice.epicmoderation.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.mineacademy.fo.ChatUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompMaterial;
import sk.adonikeoffice.epicmoderation.util.MenuUtil;

public class OptionsMenu extends Menu {

	private final Button helmet;
	/*private Button chestplate;
	private Button leggings;
	private Button boots;
	private Button mainHand;
	private Button offHand;*/

	public OptionsMenu(final Menu parent, final Player player) {
		super(parent);

		setTitle("Profile of " + player.getName());
		setSize(9 * 6);

		final PlayerInventory inventory = player.getInventory();

		helmet = Button.makeSimple(isNotNull(inventory.getHelmet()), (target) -> {
			if (inventory.getHelmet() != null) {
				inventory.setHelmet(CompMaterial.AIR.toItem());

				MenuUtil.reopenMenu(this, player);
			} else
				animateTitle("&cHelmet slot is empty.");
		});

		/*player.getInventory().getChestplate()
		player.getInventory().getLeggings()
		player.getInventory().getBoots()
		player.getInventory().getItemInMainHand()
		player.getInventory().getItemInOffHand()*/
	}

	private static ItemCreator isNotNull(final ItemStack item) {
		if (item != null)
			return ItemCreator.of(item)
					.name("&7Helmet &f" + ChatUtil.capitalize(item.getType().name()))
					.lore("", "&7&oClick to remove!");

		return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE)
				.name("&cHelmet slot is empty.")
				.hideTags(true);
	}

	@Override
	public ItemStack getItemAt(final int slot) {
		if (slot == 11)
			return helmet.getItem();

		return null;
	}

	@Override
	public Menu newInstance() {
		return this;
	}

}
