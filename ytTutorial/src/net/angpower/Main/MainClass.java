package net.angpower.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.angpower.Listeners.SignClickEvent;
import net.angpower.Listeners.SignCreateEvent;

public class MainClass extends JavaPlugin {
	
	public PluginDescriptionFile pdffile = this.getDescription();
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new SignCreateEvent(this), this);
		pm.registerEvents(new SignClickEvent(this), this);
		
		getLogger().info(pdffile.getFullName() + " has been enabled. ");
	}
	
	public void onDisable(){
		getLogger().info(pdffile.getFullName() + " has been disabled. ");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]){
		
		if (cmd.getName().equalsIgnoreCase("message") == true){
			if(sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage(ChatColor.GOLD + "Hey Player");
			}else if (sender instanceof ConsoleCommandSender){
				getLogger().info("Hey Console");
			}
			return true;
		}else if (cmd.getName().equalsIgnoreCase("heal") == true || cmd.getName().equalsIgnoreCase("h") == true){
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("tutorial.heal") == true){
				if (args.length == 0) {   // args like not added a name ex: /heal 
					player.setHealth(20);
					player.sendMessage(ChatColor.GREEN + " you Healed Yourself! ");
				}else if (args.length == 1){  // args like you added a name ex: /heal name
				  if (player.getServer().getPlayer(args[0]) != null) {
					Player target = player.getServer().getPlayer(args[0]);
					target.setHealth(20);
					target.sendMessage(ChatColor.GREEN + " You have been Healed by! " + ChatColor.GOLD + player.getName());
					player.sendMessage(ChatColor.GREEN + " You have Healed player " + ChatColor.GOLD + target.getName());
					
				}else{
					player.sendMessage(ChatColor.RED + " This Player does not Exist! ");
				}
			}else {
				player.sendMessage(ChatColor.RED + " Incorrect usage! ");
			}
		}else {
			player.sendMessage(ChatColor.RED + " you don't have Permission! ");
		}
				
		} else if (sender instanceof ConsoleCommandSender){
			if (args.length == 1){
				  if (Bukkit.getServer().getPlayer(args[0]) != null) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					target.setHealth(20);
					target.sendMessage(ChatColor.GREEN + " You have been Healed by! " + ChatColor.GOLD + "Console");
					getLogger().info( " You have Healed player " + target.getName());
					
				}else{
					getLogger().warning( " This Player does not Exist! ");
				}
			}else {
				getLogger().warning( " Incorrect Usage! ");
			}
		}
			return true;
	}	
		
		return false;
	}
}
