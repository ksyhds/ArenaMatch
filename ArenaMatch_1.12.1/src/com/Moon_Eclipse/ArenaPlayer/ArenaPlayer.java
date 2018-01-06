package com.Moon_Eclipse.ArenaPlayer;

import org.bukkit.entity.*;

public class ArenaPlayer
{
	public final Player MinecraftPlayer;
	public float BowForce = 0;
	
	public ArenaPlayer(Player p)
	{
		MinecraftPlayer = p;
	}
	public void setBowForce(float bf)
	{
		this.BowForce = bf;
	}
	public float getPlayerBowForce()
	{
		return BowForce;
	}
	public ArenaPlayer getArenaPlayer(String name)
	{
		return this;
	}
}
