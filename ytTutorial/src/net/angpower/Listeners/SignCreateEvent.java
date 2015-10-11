package net.angpower.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.Plugin;

import net.angpower.Main.MainClass;
import net.md_5.bungee.api.ChatColor;

public class SignCreateEvent implements Listener{
	
	public static Plugin plugin;
	
	public SignCreateEvent(MainClass instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onSignCreate(SignChangeEvent event) {
		Player player = event.getPlayer();
		
		if (event.getLine(0).equalsIgnoreCase("[cmd]") == true) {
			if (event.getLine(1).equals("") == false) {
				event.setLine(0, ChatColor.GOLD + "[cmd]");
				
			}else {
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + " You need to add a command on the second line.");
			}
		}
	}
}
