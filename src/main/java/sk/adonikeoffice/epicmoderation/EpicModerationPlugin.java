package sk.adonikeoffice.epicmoderation;

import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;
import org.mineacademy.fo.PlayerUtil;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.remain.Remain;
import org.mineacademy.fo.settings.Lang;
import org.mineacademy.fo.settings.YamlStaticConfig;
import sk.adonikeoffice.epicmoderation.command.ModerationMenuCommand;
import sk.adonikeoffice.epicmoderation.data.PlayerData;
import sk.adonikeoffice.epicmoderation.listener.ModerationListener;
import sk.adonikeoffice.epicmoderation.settings.Localization;
import sk.adonikeoffice.epicmoderation.settings.Settings;
import sk.adonikeoffice.epicmoderation.task.MovementTask;

import java.util.Arrays;
import java.util.List;

public final class EpicModerationPlugin extends SimplePlugin {

	@Override
	protected void onPluginStart() {
		Common.ADD_TELL_PREFIX = false;

		Messenger.ENABLED = true;
		Messenger.setSuccessPrefix(Lang.of("Prefixes.Success"));
		Messenger.setQuestionPrefix(Lang.of("Prefixes.Question"));
		Messenger.setErrorPrefix(Lang.of("Prefixes.Error"));
	}

	@Override
	protected void onReloadablesStart() {
		registerCommand(new ModerationMenuCommand());
		registerEvents(new ModerationListener());

		Common.runTimer(1, new MovementTask());

		PlayerData.loadPlayers();

		{
			PlayerData.setOfflineToAllPlayers();

			for (final Player player : Remain.getOnlinePlayers())
				PlayerUtil.kick(player, "EpicModeration: Safe");
		}
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
