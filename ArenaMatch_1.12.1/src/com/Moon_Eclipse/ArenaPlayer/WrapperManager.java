package com.Moon_Eclipse.ArenaPlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import com.Moon_Eclipse.ArenaMatch.ArenaMatch;

public class WrapperManager implements Listener
{
	public Map<Player, ArenaPlayer> ArenaPlayerMap;
	public static Plugin plugin;
	
	public WrapperManager()
	{
		Bukkit.getPluginManager().registerEvents(this, ArenaMatch.getInstance());
		ArenaPlayerMap = new HashMap<>();
	}
	
	public ArenaPlayer getArtenaPlayer(Player p)
	{
		return ArenaPlayerMap.get(p);
	}
	public Map<Player, ArenaPlayer> getWrappers()
	{
		return ArenaPlayerMap;
	}
	public void wrapCollection(Collection<? extends Player> players)
	{
		clear();
		players.stream().forEach(p -> ArenaPlayerMap.put(p, new ArenaPlayer(p)));
	}
	public void WrapAllOnline()
	{
		wrapCollection(Bukkit.getOnlinePlayers());
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		ArenaPlayerMap.put(event.getPlayer(), new ArenaPlayer(event.getPlayer()));
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		ArenaPlayerMap.remove(event.getPlayer());
	}
	public void clear()
	{
		ArenaPlayerMap.clear();
	}
}
