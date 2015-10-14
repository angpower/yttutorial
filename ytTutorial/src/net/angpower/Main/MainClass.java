package net.angpower.Main;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.angpower.Commands.MsgCommand;
import net.angpower.Listeners.BlockChangerListener;
import net.angpower.Listeners.SignClickEvent;
import net.angpower.Listeners.SignCreateEvent;

public class MainClass extends JavaPlugin {
	
	public PluginDescriptionFile pdffile = this.getDescription();
	
	public static class Global {
		
		public static boolean BCenable = false;
		
		public static ArrayList<String> Alive = new ArrayList<String>();
		public static ArrayList<Player> BlockChangerUsers = new ArrayList<Player>();
		
	}
	
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		
		//register Other listeners
		pm.registerEvents(new BlockChangerListener(this), this);
		
		//register events
		pm.registerEvents(new SignCreateEvent(this), this);
		pm.registerEvents(new SignClickEvent(this), this);
		
		//register commands
		pm.registerEvents(new MsgCommand (this), this);
		
		getLogger().info(pdffile.getFullName() + " has been enabled. ");
	}
	
	public void onDisable(){
		getLogger().info(pdffile.getFullName() + " has been disabled. ");
	}
	
	
	//Global procedures
		public static void IncorrectUse(Player player) {
			player.sendMessage(ChatColor.RED + "[ERROR] Incorrect usage!");
		}
		
		public static void NoPerms(Player player) {
			player.sendMessage(ChatColor.RED + "[ERROR] You don't have permissions to do that!");
		}
		
		public static Player getPlayer(String name) {
			
			Player player = null;
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.getName().equals(name) == true) {
					player = p;
				}
			}
			
			return player;
		}
		
		@Override
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) 
		{
			if (cmd.getName().equalsIgnoreCase("BlockChanger"))
				toggleBlockChanger(sender);
			
			return true;
		}
		
		private void toggleBlockChanger(CommandSender sender) 
		{
			if (!BCenable ((Player) sender ))
			{
				Global.BlockChangerUsers.add((Player) sender);
				((Player)sender).sendMessage(ChatColor.BLUE + " BlockChanger has been Enabled! ");
				Global.BCenable = true;
			}else {
				Global.BlockChangerUsers.remove((Player) sender);
				((Player) sender).sendMessage(ChatColor.RED + "BlockChanger has been Disabled! ");
				Global.BCenable = false;
			}
			
		}
		
		public boolean BCenable(Player player)
		{
			return Global.BlockChangerUsers.contains(player);
		}
}
