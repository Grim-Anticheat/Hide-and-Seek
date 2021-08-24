package net.tylermurphy.hideAndSeek;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.tylermurphy.hideAndSeek.commands.About;
import net.tylermurphy.hideAndSeek.commands.Help;
import net.tylermurphy.hideAndSeek.commands.SetBorder;
import net.tylermurphy.hideAndSeek.commands.SetSpawnLocation;
import net.tylermurphy.hideAndSeek.commands.Start;
import net.tylermurphy.hideAndSeek.commands.Stop;
import net.tylermurphy.hideAndSeek.util.ICommand;

import static net.tylermurphy.hideAndSeek.Store.*;

public class CommandHandler {

	public static Map<String,ICommand> COMMAND_REGISTER = new LinkedHashMap<String,ICommand>();
	
	private static void registerCommand(ICommand command) {
		if(!COMMAND_REGISTER.containsKey(command.getLabel())) {
			COMMAND_REGISTER.put(command.getLabel().toLowerCase(), command);
		}
	}
	
	public static void registerCommands() {
		registerCommand(new About());
		registerCommand(new Help());
		registerCommand(new Start());
		registerCommand(new Stop());
		registerCommand(new SetSpawnLocation());
		registerCommand(new SetBorder());
	}
	
	public static boolean handleCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player == false) {
			sender.sendMessage(errorPrefix + "This command can only be run as a player.");
		} else if(args.length < 1) {
			COMMAND_REGISTER.get("about").execute(sender, new String[0]);
		} else if(!COMMAND_REGISTER.containsKey(args[0].toLowerCase())) {
			COMMAND_REGISTER.get("about").execute(sender, Arrays.copyOfRange(args, 1, args.length));
		} else {
			try {
				COMMAND_REGISTER.get(args[0].toLowerCase()).execute(sender,Arrays.copyOfRange(args, 1, args.length));
			} catch (Exception e) {
				sender.sendMessage(errorPrefix + "An error has occured.");
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return CommandHandler.handleCommand(sender, command, label, args);
	}
	
}