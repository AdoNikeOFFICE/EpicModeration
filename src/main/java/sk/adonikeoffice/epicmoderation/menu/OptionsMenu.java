package sk.adonikeoffice.epicmoderation.menu;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.mineacademy.fo.ChatUtil;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.RandomUtil;
import org.mineacademy.fo.menu.Menu;
import org.mineacademy.fo.menu.button.Button;
import org.mineacademy.fo.menu.model.ItemCreator;
import org.mineacademy.fo.remain.CompColor;
import org.mineacademy.fo.remain.CompMaterial;
import org.mineacademy.fo.remain.CompParticle;
import org.mineacademy.fo.remain.CompSound;
import sk.adonikeoffice.epicmoderation.data.PlayerData;

public class OptionsMenu extends Menu {

	@Getter
	private final Player pickedPlayer;

	// UP - OPTIONS
	private final Button teleport;
	private final Button message;
	private final Button freeze;
	/*private Button kick;
	private Button spectate;
	private Button inventory;
	private Button enderInventory;*/

	// DOWN
	private final Button helmet;
	private final Button chestplate;
	private final Button leggings;
	private final Button boots;
	private final Button mainHand;
	private final Button offHand;

	public OptionsMenu(final Menu parent, final Player pickedPlayer /* PICKED PLAYER FROM PARENT MENU */) {
		super(parent);

		this.pickedPlayer = pickedPlayer;

		setTitle("Profile of " + pickedPlayer.getName());
		setSize(9 * 6);
		setSlotNumbersVisible();

		// UP
		teleport = Button.makeSimple(makeWool("Teleport to the player"), (player) -> {
			if (pickedPlayer.isOnline()) {
				final Location location = pickedPlayer.getLocation();
				location.add(0, 3, 0);
				location.setYaw(0);
				location.setPitch(90);

				CompParticle.TOTEM.spawn(location, 5);
				CompSound.ANVIL_LAND.play(location);

				player.setAllowFlight(true);
				player.setFlying(true);

				player.teleport(location);
			}
		});

		message = Button.makeStringPrompt(makeWool("Message the player"), "What message do you want to send to the '" + pickedPlayer.getName() + "'?", message -> {
			Common.tell(pickedPlayer, Messenger.getQuestionPrefix() + message);
		});

		freeze = Button.makeSimple(makeWool("Freeze the player"), player -> {
			final PlayerData data = PlayerData.findPlayer(pickedPlayer);

			final boolean has = data.isFreezed();

			data.setFreezed(!has);
			data.setFreezedLocation(pickedPlayer.getLocation());

			restartMenu(has ? "&cDisabled" : "&aEnabled");
		});

		// DOWN
		final PlayerInventory inventory = pickedPlayer.getInventory();

		final ItemStack helmetItem = inventory.getHelmet();

		helmet = Button.makeSimple(isNotNull(helmetItem, "&fHelmet &8-"), (player) -> {
			if (helmetItem != null) {
				inventory.setHelmet(new ItemStack(Material.AIR));

				newInstance().displayTo(getViewer());
			}
		});

		final ItemStack chestplateItem = inventory.getChestplate();

		chestplate = Button.makeSimple(isNotNull(chestplateItem, "&fChestplate &8-"), (player) -> {
			if (chestplateItem != null) {
				inventory.setChestplate(new ItemStack(Material.AIR));

				newInstance().displayTo(getViewer());
			}
		});

		final ItemStack leggingsItem = inventory.getLeggings();

		leggings = Button.makeSimple(isNotNull(leggingsItem, "&fLeggings &8-"), (player) -> {
			if (leggingsItem != null) {
				inventory.setLeggings(new ItemStack(Material.AIR));

				newInstance().displayTo(getViewer());
			}
		});

		final ItemStack bootsItem = inventory.getBoots();

		boots = Button.makeSimple(isNotNull(bootsItem, "&fBoots &8-"), (player) -> {
			if (bootsItem != null) {
				inventory.setBoots(new ItemStack(Material.AIR));

				newInstance().displayTo(getViewer());
			}
		});

		final ItemStack mainHandItem = inventory.getItemInMainHand();

		mainHand = Button.makeSimple(isAir(mainHandItem, "&fMain Hand &8-"), (player) -> {
			inventory.setItemInMainHand(new ItemStack(Material.AIR));

			newInstance().displayTo(getViewer());
		});

		final ItemStack offHandItem = inventory.getItemInOffHand();

		offHand = Button.makeSimple(isAir(offHandItem, "&fOff Hand &8-"), (player) -> {
			inventory.setItemInOffHand(new ItemStack(Material.AIR));

			newInstance().displayTo(getViewer());
		});
	}

	private static ItemCreator makeWool(final String text) {
		final CompColor randomColor = CompColor.fromChatColor(RandomUtil.nextChatColor());

		return ItemCreator.ofWool(randomColor)
				.name(randomColor.getChatColor() + text);
	}

	private static ItemCreator isAir(final ItemStack item, final String text) {
		if (item.getType() != CompMaterial.AIR.getMaterial())
			return ItemCreator.of(item)
					.name("&f" + ChatUtil.capitalize(item.getType().name()))
					.lore("", "&7&oClick to remove!");

		return ItemCreator.of(CompMaterial.ORANGE_STAINED_GLASS_PANE)
				.name(text + " &6This slot is empty.")
				.hideTags(true);
	}

	private static ItemCreator isNotNull(final ItemStack item, final String text) {
		if (item != null)
			return ItemCreator.of(item)
					.name("&f" + ChatUtil.capitalize(item.getType().name()))
					.lore("", "&7&oClick to remove!");

		return ItemCreator.of(CompMaterial.RED_STAINED_GLASS_PANE)
				.name(text + " &4This slot is empty.")
				.hideTags(true);
	}

	@Override
	public ItemStack getItemAt(final int slot) {
		// UP
		if (slot == 10)
			return teleport.getItem();
		if (slot == 11)
			return message.getItem();
		if (slot == 12)
			return freeze.getItem();
		/*if (slot == 13)
			return kick.getItem();
		if (slot == 14)
			return spectate.getItem();
		if (slot == 15)
			return inventory.getItem();
		if (slot == 16)
			return enderInventory.getItem();*/

		// DOWN
		if (slot == 37)
			return mainHand.getItem();
		if (slot == 38)
			return offHand.getItem();

		if (slot == 40)
			return helmet.getItem();
		if (slot == 41)
			return chestplate.getItem();
		if (slot == 42)
			return leggings.getItem();
		if (slot == 43)
			return boots.getItem();

		return null;
	}

	@Override
	public Menu newInstance() {
		return new OptionsMenu(this.getParent(), this.getPickedPlayer());
	}

}
