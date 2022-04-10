package sk.adonikeoffice.epicmoderation.menu;

import org.bukkit.inventory.ItemStack;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.button.ButtonMenu;
import org.mineacademy.fo.remain.CompMaterial;

public class MainMenu extends Menu {

	private final Button onlinePlayers;
	//private final Button offlinePlayers;

	public MainMenu() {
		setTitle("EpicModeration™");
		setSize(9 * 3);

		onlinePlayers = new ButtonMenu(new OnlineMenu(this), CompMaterial.PAPER, "&aShow Online Players");
		//	offlinePlayers = new ButtonMenu(new OfflineMenu(this), CompMaterial.PAPER, "&aShow Offline Players");
	}

	@Override
	public ItemStack getItemAt(final int slot) {
		if (slot == 22)
			return onlinePlayers.getItem();

		//if (slot == 23)
		//	return offlinePlayers.getItem();

		return null;
	}

	@Override
	protected String[] getInfo() {
		return new String[]{
				"In This Menu You Can",
				"Pick Up A Category You",
				"Want To See And Moderate"
		};
	}

}
