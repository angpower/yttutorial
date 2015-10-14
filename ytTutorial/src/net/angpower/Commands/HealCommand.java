package net.angpower.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import net.angpower.Main.MainClass;
import net.angpower.Main.MainClass.Global;

public class HealCommand implements Listener {
	
	public static Plugin plugin;
	
	public HealCommand(MainClass instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event){
			Player player = event.getPlayer();
			String message = event.getMessage();
			String args[] = message.split(" ");
			
			if (args[0].equalsIgnoreCase("/heal")) {
				if (player.hasPermission("UHC.HOST")) {
					if (args.length == 1) {
						player.sendMessage(ChatColor.AQUA + "[INFO] Use /heal <player> to heal that player.");
					} else if (args.length == 2) {
						
						if (args[1].equalsIgnoreCase("all") == false) {
							Player heal = MainClass.getPlayer(args[1]);
							
							if (heal != null) {
								heal.setHealth(20);;
								heal.sendMessage(ChatColor.AQUA + "[INFO] You have been healed.");
								player.sendMessage(ChatColor.AQUA + "[INFO] Succesfully healed " + heal.getName());
							} else {
								player.sendMessage(ChatColor.RED + "[ERROR] This player doesn't exist.");
							}
						} else {
							
							for (Player p : Bukkit.getOnlinePlayers()) {
								if (Global.Alive.contains(p.getName()) == true) {
									p.setHealth(20);;
									p.sendMessage(ChatColor.AQUA + "[INFO] You have been healed.");
								}
							}
						}
						
						
					} else {
						MainClass.IncorrectUse(player);
					}
				} else {
					MainClass.NoPerms(player);
				}
			}
		}

}
