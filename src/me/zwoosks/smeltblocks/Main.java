package me.zwoosks.smeltblocks;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new CommandManager(this);
		getServer().getPluginManager().registerEvents(new EventManager(this), this);
		saveDefaultConfig();
	}
	
}