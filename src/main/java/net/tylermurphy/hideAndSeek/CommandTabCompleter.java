package net.tylermurphy.hideAndSeek;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandTabCompleter{

	public static List<String> handleTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) {
			return new ArrayList<String>(CommandHandler.COMMAND_REGISTER.keySet());
		} else if(args.length > 1) {
			if(!CommandHandler.COMMAND_REGISTER.containsKey(args[0].toLowerCase())) {
				return null;
			} else {
				String[] usage = CommandHandler.COMMAND_REGISTER.get(args[0].toLowerCase()).getUsage().split(" ");
				if(args.length - 2 < usage.length) {
					String parameter = usage[args.length-2];
					if(parameter.equals("<player>")) {
						return null;//playerList.values().stream().map(p -> p.getName()).collect(Collectors.toList());
					} else {
						List<String> temp = new ArrayList<String>();
						temp.add(parameter.replace("<", "").replace(">", ""));
						return temp;
					}
				} else {
					return null;
				}
			}
		}
		return null;
	}

}