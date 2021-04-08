package me.zwoosks.smeltblocks;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class Utils {
	
	public static HashMap<Material, Material> smelts;
	static {
		smelts = new HashMap<Material, Material>();
		smelts.put(Material.IRON_ORE, Material.IRON_INGOT);
		smelts.put(Material.GOLD_ORE, Material.GOLD_NUGGET);
	}
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}