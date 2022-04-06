package sk.adonikeoffice.epicmoderation;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.Lang;
import org.mineacademy.fo.settings.YamlStaticConfig;
import sk.adonikeoffice.epicmoderation.command.ModerationMenuCommand;
import sk.adonikeoffice.epicmoderation.data.PlayerData;
import sk.adonikeoffice.epicmoderation.listener.ModerationListener;
import sk.adonikeoffice.epicmoderation.settings.Localization;
import sk.adonikeoffice.epicmoderation.settings.Settings;

import java.util.Arrays;
import java.util.List;

public final class EpicModerationPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		Common.ADD_TELL_PREFIX = false;

		Messenger.ENABLED = true;
		Messenger.setSuccessPrefix(Lang.of("Prefixes.Success"));
		Messenger.setErrorPrefix(Lang.of("Prefixes.Error"));

		registerCommand(new ModerationMenuCommand());
		registerEvents(new ModerationListener());

		PlayerData.loadPlayers();
	}

	@Override
	public List<Class<? extends YamlStaticConfig>> getSettings() {
		return Arrays.asList(Settings.class, Localization.class);
	}

	@Override
	public int getMetricsPluginId() {
		return 14867;
	}

}
