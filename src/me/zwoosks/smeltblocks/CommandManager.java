package me.zwoosks.smeltblocks;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CommandManager implements CommandExecutor {
	
	private Main plugin;
	
	public CommandManager(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("smelt").setExecutor(this);
		plugin.getCommand("craftblocks").setExecutor(this);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("smelt")) {
				if(player.hasPermission("zwoosks.smelt")) {
					Iterator it = Utils.smelts.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry pair = (Map.Entry)it.next();
						smeltItems(player.getInventory(), (Material) pair.getKey(), (Material) pair.getValue());
					}
					player.sendMessage(Utils.chat(plugin.getConfig().getString("messages.smeltMessage")));
				} else {
					player.sendMessage(Utils.chat(plugin.getConfig().getString("messages.lackPerms").replace("%perm%", "zwoosks.smelt")));
				}
			} else if(cmd.getName().equalsIgnoreCase("craftblocks")) {
				if(player.hasPermission("zwoosks.craftblocks")) {
					craftItems(player.getInventory(), Material.DIAMOND, Material.DIAMOND_BLOCK, 9);
					craftItems(player.getInventory(), Material.COAL, Material.COAL_BLOCK, 9);
					craftItems(player.getInventory(), Material.IRON_INGOT, Material.IRON_BLOCK, 9);
					craftItems(player.getInventory(), Material.GOLD_INGOT, Material.GOLD_BLOCK, 9);
					craftItems(player.getInventory(), Material.EMERALD, Material.EMERALD_BLOCK, 9);
					craftItems(player.getInventory(), Material.REDSTONE, Material.REDSTONE_BLOCK, 9);
					player.sendMessage(Utils.chat(plugin.getConfig().getString("messages.craftblocksMessage")));
				} else {
					player.sendMessage(Utils.chat(plugin.getConfig().getString("messages.lackPerms").replace("%perm%", "zwoosks.craftblocks")));
				}
			}
		} else {
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("messages.notPlayer")));
		}
		return true;
	}
	
	private void smeltItems(PlayerInventory inv, Material oldMaterial, Material newMaterial) {
		ItemStack[] items = inv.getContents();
		for (int i = 0 ; i < items.length; ++i) {
			ItemStack item = items[i];
			if (i < 27 && i > -1) {
		        if(item != null) {
		        	if(item.getType() == oldMaterial) {
			        	ItemStack newItemStack = new ItemStack(newMaterial, item.getAmount());
			        	inv.setItem(i, newItemStack);
			        }
		        }
		    }
		}
	}
	
	private void craftItems(PlayerInventory inv, Material oldMaterial, Material newMaterial, int neededOldToNew) {
		int oldMaterialCount = 0;
		ItemStack[] items = inv.getContents();
		for (int i = 0 ; i < items.length; ++i) {
			ItemStack item = items[i];
			if (i < 27 && i > -1) {
				if(item != null) {
					if(item.getType() == oldMaterial) {
						oldMaterialCount += item.getAmount();
						inv.setItem(i, null);
					}
				}
			}
		}
		Bukkit.broadcastMessage("count mat: " + oldMaterialCount);
		if(oldMaterialCount >= neededOldToNew) {
			int newAmount = oldMaterialCount/neededOldToNew;
			int residual = oldMaterialCount % neededOldToNew;
			ItemStack newItems = new ItemStack(newMaterial, newAmount);
			ItemStack residualItems = new ItemStack(oldMaterial, residual);
			inv.addItem(newItems);
			inv.addItem(residualItems);
		}
	}
	
}