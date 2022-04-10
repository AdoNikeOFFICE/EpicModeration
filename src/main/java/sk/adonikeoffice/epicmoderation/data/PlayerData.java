package sk.adonikeoffice.epicmoderation.data;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.TimeUtil;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.ConfigItems;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.Collection;
import java.util.Objects;

@Getter
public class PlayerData extends YamlConfig {

	private static final ConfigItems<PlayerData> loadedPlayers = ConfigItems.fromFolder("players", PlayerData.class);

	private final String name;

	// PARAMETERS
	private String uuid;
	private String ip;
	private String firstJoin;
	private String lastJoin;

	private boolean online;
	private boolean freezed;

	private Location freezedLocation;

	private PlayerData(final String name) {
		this.name = name;

		this.loadConfiguration(NO_DEFAULT, "players/" + name + ".yml");
	}

	@Override
	protected void onLoadFinish() {
		uuid = getString("UUID");
		ip = getString("IP");
		firstJoin = getString("FIRST_JOIN");
		lastJoin = getString("LAST_JOIN", "Not yet.");
		online = getBoolean("ONLINE", false);
		freezed = getBoolean("FREEZED", false);
		freezedLocation = getLocation("FREEZED_LOCATION");
	}

	@Override
	protected SerializedMap serialize() {
		return SerializedMap.ofArray(
				"NAME", this.name,
				"UUID", this.uuid,
				"IP", this.ip,
				"FIRST_JOIN", this.firstJoin,
				"LAST_JOIN", this.lastJoin,
				"ONLINE", this.online,
				"FREEZED", this.freezed,
				"FREEZED_LOCATION", this.freezedLocation
		);
	}

	public static PlayerData deserialize(final SerializedMap map) {
		final String playerName = map.getString("NAME");

		return new PlayerData(playerName);
	}

	public static void createPlayer(final Player player) {
		final PlayerData playerData = loadedPlayers.loadOrCreateItem(player.getName(), () -> new PlayerData(player.getName()));

		playerData.setUuid(player.getUniqueId().toString());
		playerData.setIp(Objects.requireNonNull(player.getAddress()).toString());
		playerData.setFirstJoin(TimeUtil.getFormattedDate());
		playerData.setOnline(true);
		playerData.setFreezed(false);

		Common.runLater(playerData::save);
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;

		save();
	}

	public void setIp(final String ip) {
		this.ip = ip;

		save();
	}

	public void setFirstJoin(final String firstJoin) {
		this.firstJoin = firstJoin;

		save();
	}

	public void setLastJoin(final String lastJoin) {
		this.lastJoin = lastJoin;

		save();
	}

	public void setOnline(final boolean online) {
		this.online = online;

		save();
	}

	public void setFreezed(final boolean freezed) {
		this.freezed = freezed;

		save();
	}

	public void setFreezedLocation(final Location freezedLocation) {
		this.freezedLocation = freezedLocation;

		save();
	}

	public static void disableImportantThings() {
		for (final PlayerData player : getPlayers()) {
			player.setOnline(false);
			player.setFreezed(false);
		}
	}

	public static void removePlayer(final PlayerData player) {
		loadedPlayers.removeItem(player);
	}

	public static void loadPlayers() {
		loadedPlayers.loadItems();

		final int size = getPlayers().size();

		if (size >= 1)
			Common.log("Loaded " + Common.plural(size, "player"));
	}

	public static PlayerData findPlayer(final Player player) {
		return loadedPlayers.findItem(player.getName());
	}

	public static boolean isLoaded(final Player player) {
		return loadedPlayers.isItemLoaded(player.getName());
	}

	public static Collection<PlayerData> getPlayers() {
		return loadedPlayers.getItems();
	}

}
