package com.Moon_Eclipse.ArenaPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.Moon_Eclipse.ArenaMatch.ArenaMatch;

public class ArenaPlayer
{
	public final Player MinecraftPlayer;
	public float BowForce = 0;
	public boolean CanDrop = false;
	public boolean Shoot = false;
	
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
	
	public boolean getCanDrop()
	{
		return this.CanDrop;
	}
	public void setCanDrop(Boolean set)
	{
		this.CanDrop = set;
	}
	public void sendMessageToArenaPlayer(String Message)
	{
		Player p = this.MinecraftPlayer;
		p.sendMessage(Message);
	}
	// 아래는 화살에 의한 공격을 플레이어가 했는지 안했는지를 알아보기위해 제작한 호출용 메소드
	public boolean isShoot()
	{
		return Shoot;
	}
	
	public void setShoot(boolean set)
	{
		this.Shoot = set;
	}
	
	public void RunPlayerItemDropTimer()
	{
		setCanDrop(true);
		
		BukkitTask task = new BukkitRunnable()
		{
            @Override
            public void run() 
            {
            	setCanDrop(false);
            }
        }.runTaskLater(ArenaMatch.getInstance(), 1200);
	}
}
