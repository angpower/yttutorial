package net.angpower.Listeners;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import net.angpower.Main.MainClass;
import net.md_5.bungee.api.ChatColor;

public class SignClickEvent implements Listener {
	
	public static Plugin plugin;
	
	public SignClickEvent(MainClass instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onSignClick(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.SIGN_POST || event.getClickedBlock().getType() == Material.WALL_SIGN){
				if (event.getClickedBlock().getState() instanceof Sign) {
					Sign sign = (Sign) event.getClickedBlock().getState();
					
					if (sign.getLine(0).equals(ChatColor.GOLD + "[cmd]") == true) {
						String command = sign.getLine(1);						
						player.sendMessage(ChatColor.GREEN + " Command Executed ! ");
						player.chat("/" + command);
					}
				}
			}
		}
	}

}
