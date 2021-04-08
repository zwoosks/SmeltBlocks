package me.zwoosks.smeltblocks;

import org.bukkit.event.Listener;

public class EventManager implements Listener {
	
	@SuppressWarnings("unused")
	private Main plugin;

	public EventManager(Main plugin) {
		this.plugin = plugin;
	}

//	@EventHandler
//	public void onEntityExplode(EntityExplodeEvent e) {
//		Bukkit.broadcastMessage("working");
//	}
	
}