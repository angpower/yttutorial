package net.angpower.Listeners;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.plugin.Plugin;

import net.angpower.Main.MainClass;
import net.angpower.Main.MainClass.Global;

public class BlockChangerListener implements Listener {
	
	public static Plugin plugin;
	
	public BlockChangerListener(MainClass instance) 	{
		plugin = instance;
	}	
	
	@EventHandler
	public void onBlockDamage(BlockDamageEvent event) 	{
		//Player player = event.getPlayer();	
		if (Global.BCenable == true) {
			//player.sendMessage(ChatColor.BLUE + " I did Damage! ");		
			event.getBlock().setTypeId(35);	
		}else {
			return;
		}
	}
	
}
