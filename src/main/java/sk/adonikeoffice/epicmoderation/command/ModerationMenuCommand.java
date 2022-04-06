package sk.adonikeoffice.epicmoderation.command;

import org.mineacademy.fo.command.SimpleCommand;
import sk.adonikeoffice.epicmoderation.menu.MainMenu;

public class ModerationMenuCommand extends SimpleCommand {

	public ModerationMenuCommand() {
		super("epicmoderationmenu|emm|mm");

		setPermission(null);
	}

	@Override
	protected void onCommand() {
		new MainMenu().displayTo(getPlayer());
	}

}
