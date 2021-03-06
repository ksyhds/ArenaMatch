package com.Moon_Eclipse.ArenaMatch;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageModifier;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.google.common.base.Equivalence.Wrapper;

import forblue.armor.plugins.EventListener;

//import forblue.armor.plugins.EventListener;

import com.Moon_Eclipse.MCgive.*;
import com.Moon_eclipse.EclipseLib.LibMain;
import com.Moon_Eclipse.ArenaPlayer.ArenaPlayer;
import com.Moon_Eclipse.ArenaPlayer.WrapperManager;
import com.Moon_Eclipse.MCMANYtitle.*;

public class ArenaMatch extends JavaPlugin implements Listener
{

	static FileConfiguration userscore;
	File UserScore;
	
	FileConfiguration submap;
	File SubMap;
	
	FileConfiguration title;
	File TitleFile;
	
	static FileConfiguration Profile;
	File ProfileFile;
	
	static FileConfiguration party;
	File PartyFile;
	
	static FileConfiguration guild;
	File GuildFile;
	
	static FileConfiguration tier;
	File TierFile;
	
	static FileConfiguration stats;
	File StatsFile;
	
	static FileConfiguration AttributeGUI;
	File AttributeGUIFile;
	
	static FileConfiguration Effect;
	File EffectFile;
	
	static Configuration c;
	
	public static final String VAULT = "Vault";
    static Economy vault = null;
    boolean vaultLoaded = false;
    
    WrapperManager ArenaPlayerManager;
    
    boolean Debug = false;
    
	HashMap<String,String> match = new HashMap<String,String>();
	HashMap<String,String> usemap = new HashMap<String,String>();
	HashMap<String,String> UseArena = new HashMap<String,String>();
	HashMap<String,String> UserArenaState = new HashMap<String,String>();
	HashMap<String,String> PlayerWhoClicked = new HashMap<String,String>();
	HashMap<String,String> PreviousPlayer = new HashMap<String,String>();
	HashMap<String,String> PreviousStat = new HashMap<String,String>();
	HashMap<String,String> UserChatColor = new HashMap<String,String>();
	HashMap<String,String> UserChatState = new HashMap<String,String>();
	HashMap<String,List<String>> UserCompititionLore = new HashMap<String,List<String>>();
	HashMap<String,List<String>> UserDefaultLore = new HashMap<String,List<String>>();
	HashMap<String,Integer> TimerValue = new HashMap<String,Integer>();
	HashMap<String,Integer> TimerValueMain = new HashMap<String,Integer>();
	HashMap<String,Integer> TimerIcon = new HashMap<String,Integer>();
	HashMap<String,Integer> TimerIconValue = new HashMap<String,Integer>();
	HashMap<String,Boolean> IsSetFriend = new HashMap<String,Boolean>();
	HashMap<String,Boolean> IsinMatch = new HashMap<String,Boolean>();
	HashMap<String,Boolean> IsinList = new HashMap<String,Boolean>();
	HashMap<String,Boolean> REjectList = new HashMap<String,Boolean>();
	HashMap<String,Boolean> isDead = new HashMap<String,Boolean>();
	HashMap<String,Scoreboard> StateBoard = new HashMap<String,Scoreboard>();
	HashMap<String,Inventory> PersonalGUI = new HashMap<String,Inventory>();
	HashMap<String, BukkitTask> GUIAnimationTask = new HashMap<String,BukkitTask>();
	ArrayList<String> usingmap = new ArrayList<String>();

	
	
	java.util.Random ran = new java.util.Random();
	int rv = 0;
	
	int FieldIconId,FieldIconMeta = 0, DefaultIconId = 0, DefaultIconMeta = 0, CompititionIconId = 0, CompititionIconMeta = 0;
	String FieldIconName = "", DefaultIconName = "", CompititionIconName = "";
	ArrayList<String> FieldIconLore = new ArrayList<String>();
	ArrayList<String> DefaultIconLore = new ArrayList<String>();
	ArrayList<String> CompititionIconLore = new ArrayList<String>();
	
	//스탯 포인트의 기본치를 저장할 공간을 선언
	int Initial_Stat_Point = 0;
	
	HashMap<Integer, List<String>> 기본 = new HashMap<Integer, List<String>>();
	HashMap<Integer, List<String>> 정글 = new HashMap<Integer, List<String>>();
	HashMap<Integer, List<String>> 지옥 = new HashMap<Integer, List<String>>();
	HashMap<Integer, List<String>> 중세 = new HashMap<Integer, List<String>>();
	HashMap<Integer, List<String>> 설산 = new HashMap<Integer, List<String>>();
	HashMap<Integer, List<String>> 묘지 = new HashMap<Integer, List<String>>();
	HashMap<Integer, List<String>> 천공섬 = new HashMap<Integer, List<String>>();
	
	private static ArenaMatch instance;
	
	PlayerProfile pp;
	
	public void onEnable()
	{
		
		instance = this;
		ArenaPlayerManager = new WrapperManager();
		this.saveDefaultConfig();
		this.saveDefaultSubMap();
		this.saveDefaultUserScore();
		this.saveDefaultProfileFile();
		this.saveDefaultTitle();
		this.saveDefaultPartyFile();
		this.saveDefaultGuildFile();
		this.saveDefaultTierFile();
		this.saveDefaultStatsFile();
		this.saveDefaultAttributeGUIFile();
		this.saveDefaultEffectFile();
		
		c = this.getConfig();
		this.GetGUIIcon();
		Bukkit.getPluginManager().registerEvents(this, this);
		
		UserScore = new File(getDataFolder(), "userscore.yml");
		userscore = YamlConfiguration.loadConfiguration(UserScore);
		
		SubMap = new File(getDataFolder(), "submap.yml");
		submap = YamlConfiguration.loadConfiguration(SubMap);
		
		ProfileFile = new File(getDataFolder(), "profile.yml");
		Profile = YamlConfiguration.loadConfiguration(ProfileFile);
		
		TitleFile = new File(getDataFolder(), "Title.yml");
		title = YamlConfiguration.loadConfiguration(TitleFile);
		
		PartyFile = new File(getDataFolder(), "party.yml");
		party = YamlConfiguration.loadConfiguration(PartyFile);
		
		GuildFile = new File(getDataFolder(), "guild.yml");
		guild = YamlConfiguration.loadConfiguration(GuildFile);
		
		TierFile = new File(getDataFolder(), "tier.yml");
		tier = YamlConfiguration.loadConfiguration(TierFile);
		
		StatsFile = new File(getDataFolder(), "stats.yml");
		stats = YamlConfiguration.loadConfiguration(StatsFile);
		
		AttributeGUIFile = new File(getDataFolder(), "AttributeGUI.yml");
		AttributeGUI = YamlConfiguration.loadConfiguration(AttributeGUIFile);
		
		EffectFile = new File(getDataFolder(), "Effect.yml");
		Effect = YamlConfiguration.loadConfiguration(EffectFile);
		
		getEconomy();
		InitializeArena();
		this.MainTimer();
		
		pp = new PlayerProfile();
		
		// 지급할 스탯 포인트의 저장소를 초기화
		Initial_Stat_Point = c.getInt("config.Initial_Stat_Point");
		
	}
	public void onDisable()
	{
		saveConfig();
		saveSubmap();
		saveUserScore();
	}
	public boolean onCommand(CommandSender sender, Command command, String Label, String[] args)
	{
		///Arena reload, /Arena save, Arena rank, Arena Move, Arena setspawn, Arena Setsubmap 아레나이름 서브맵이름 pos[1/2]
		if((sender instanceof Player))
		{
			if(command.getName().equalsIgnoreCase("arena"))
			{
				Player p = (Player) sender;
				
				if( args.length < 1 || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help"))
				{			
					p.sendMessage("/Arena rank");
					p.sendMessage("/Arena matchdown");
					if(p.isOp())
					{
						p.sendMessage("/Arena reload");
						p.sendMessage("/Arena save");
						p.sendMessage("/Arena setspawn");
						p.sendMessage("/Arena matchup 아레나이름");
						p.sendMessage("/Arena setsubmap 아레나이름 서브맵이름");
						p.sendMessage("/Arena initial 플레이어이름 - 플레이어 플긴값초기화");
						p.sendMessage("/Arena task 플레이어이름 - 플레이어 GUI 버킷 런에이블 초기화");
						p.sendMessage("/Arena debug_playervalue 플레이어이름 - 플레이어 플긴값 보기");
						p.sendMessage("/Arena delete  - 손에 든 아이템 속성 제거");
						p.sendMessage("/Arena alldelete - 서버에 접속중인 전인원의 인벤토리안에 있는 아이템 속성 제거");
						p.sendMessage("/Arena kill 이름 - 대상의 체력을 0으로 만들어 사망하게함.");
						p.sendMessage("/Arena setscore 이름 점수- 대상의 경쟁전 점수를 수정합니다.");
						p.sendMessage("/Arena color 플레이어이름 알파벳/숫자 - 해당 플레이어의 채팅 색상을 변경");
						p.sendMessage("/Arena glowoff - 자신의 glow 이펙트 제거");
						p.sendMessage("/Arena glowon - 자신에게 glow 이펙트 추가");
						p.sendMessage("/Arena buy 가격(랭크포인트) 구매할아이템이름  개수 - MCGIVE의 아이템을 랭크포인트로 구매");
						p.sendMessage("/Arena debug - 아레나 데미지 상황을 확인할 수 있도록 로그를 전부 브리핑 합니다.");
						
					}
				}
				if(args.length >= 1)
				{
					switch(args[0])
					{
						case "debug":
							if(p.isOp())
							{
								Debug = !Debug;
								p.sendMessage("디버그 상태를 \'" + Debug + "\' 로 변경합니다.");
							}
						break;
						case "mob":
						Location p_loc = p.getLocation();
						Skeleton sk = (Skeleton) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
						sk.setMaxHealth(2000D);
		                sk.setHealth(100D);
		                sk.setCustomName("Creeper");
		                sk.setCustomNameVisible(true);
		                sk.getEquipment().setItemInHand(main.get_Mcgive_Item("", "reignsword1", 1));
		                
						break;
						
						///Arena buy 가격(랭크포인트) 구매할아이템이름  개수
						//  cmd	  0       1             2       3
						case "buy":
							if(args.length >= 4)
							{
								String PlayerName =  p.getName();
								int balance = userscore.getInt("userscore." + PlayerName + ".Score");
								int Price = Integer.parseInt(args[1]);
								
								if(balance >= Price)
								{
									
									int remain = balance - Price;
									String ItemName = args[2];
									int amount = Integer.parseInt(args[3]);
									
									ItemStack Item = main.get_Mcgive_Item(p.getName(), ItemName, amount);
									
									p.getInventory().addItem(Item);
									
									userscore.set("userscore." + p.getName() + ".Score", remain);
									this.modifyScoreBoard(p);
								}
								else
								{
									p.sendMessage("§b[마인아레나] §e랭크 포인트가 부족합니다.");
								}
							}
							else
							{
								p.sendMessage("인수가 모자랍니다.");
							}
							
							
						break;
						
						case "glowoff":
							p.setGlowing(false);
						break;
						
						case "glowon":
							p.setGlowing(true);
						break;
						
						case "getprofile":
							
							double stat_array[] = new double[100];
							stat_array = pp.getstat(p, stat_array);
							for(int i = 0 ; i < 30 ; i ++)
							{
								p.sendMessage(i + "번째: " + stat_array[i]);
							}
							
						break;
						/*
						case "bowforce":
							
							String Playername = args[1];
							Player temp_p = Bukkit.getPlayer(Playername);
							ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(temp_p);
							float bowforce = ap.getPlayerBowForce();
							
							p.sendMessage("입력이름: " + Playername + ", BowForce: " + bowforce);
							
						break;
						*/
						case "sendmessage":
							p.sendMessage("§b[마인아레나] §e전투중에는 사용할 수 없는 명령어 입니다.");
						break;
					
						case "checkarena":
							p.sendMessage( "기본 ->" + "-2: " + 기본.get(-2).toString() + "-1: " + 기본.get(-1).toString() + ", 0~299: " + 기본.get(0).toString() + ", 300~599: " + 기본.get(1).toString() + ", 600~999: " + 기본.get(2).toString() + ", 1000~: " + 기본.get(3).toString());;
							p.sendMessage( "정글 ->" + "-2: " + 정글.get(-2).toString() + "-1: " + 정글.get(-1).toString() + ", 0~299: " + 정글.get(0).toString() + ", 300~599: " + 정글.get(1).toString() + ", 600~999: " + 정글.get(2).toString() + ", 1000~: " + 정글.get(3).toString());
							p.sendMessage( "지옥 ->" + "-2: " + 지옥.get(-2).toString() + "-1: " + 지옥.get(-1).toString() + ", 0~299: " + 지옥.get(0).toString() + ", 300~599: " + 지옥.get(1).toString() + ", 600~999: " + 지옥.get(2).toString() + ", 1000~: " + 지옥.get(3).toString());
							p.sendMessage( "중세 ->" + "-2: " + 중세.get(-2).toString() + "-1: " + 중세.get(-1).toString() + ", 0~299: " + 중세.get(0).toString() + ", 300~599: " + 중세.get(1).toString() + ", 600~999: " + 중세.get(2).toString() + ", 1000~: " + 중세.get(3).toString());
							p.sendMessage( "설산 ->" + "-2: " + 설산.get(-2).toString() + "-1: " + 설산.get(-1).toString() + ", 0~299: " + 설산.get(0).toString() + ", 300~599: " + 설산.get(1).toString() + ", 600~999: " + 설산.get(2).toString() + ", 1000~: " + 설산.get(3).toString());
							p.sendMessage( "묘지 ->" + "-2: " + 묘지.get(-2).toString() + "-1: " + 묘지.get(-1).toString() + ", 0~299: " + 묘지.get(0).toString() + ", 300~599: " + 묘지.get(1).toString() + ", 600~999: " + 묘지.get(2).toString() + ", 1000~: " + 묘지.get(3).toString());
							p.sendMessage( "천공섬 ->" + "-2: " + 천공섬.get(-2).toString() + "-1: " + 천공섬.get(-1).toString() + ", 0~99: " + 천공섬.get(0).toString() + ", 300~599: " + 천공섬.get(1).toString() + ", 600~999: " + 천공섬.get(2).toString() + ", 1000~: " + 천공섬.get(3).toString());
						break;
						case "color":
							userscore.set("userscore." + args[1] + ".ChatColor", args[2]);
							UserChatColor.put(args[1], args[2]);
						break;
						case "setscore":
							if(p.isOp())
							{
								int score = Integer.parseInt(args[2]);
								userscore.set("userscore." + args[1] + ".Score", score);
								p.sendMessage(args[1] + "님의 점수를" + score + "로 수정했습니다.");
							}
							
						break;
						case "initial":
							this.InitializeUserValues(args[1]);
							
						break;
						case "task":
							GUIAnimationTask.get(args[1]).cancel();
							
						break;
						case "spawn":
							if(!IsinMatch.get(p.getName()) || (IsinMatch.get(p.getName()) && UserArenaState.get(p.getName()).equals("Field")))
							{
								this.InitializeUserValues(p.getName());
								RemovePlayerInList(p.getName());
								p.setGlowing(false);
								Bukkit.dispatchCommand(p, "spawn");
							}
							else
							{
								p.sendMessage("§b[마인아레나] §e전투 도중에는 이동할 수 없습니다.");
							}
							
						break;
						case "debug_playervalue":
							String[] values = getPlayerValues(args[1]);
							for(String value : values)
							{
								p.sendMessage(value);
							}
						break;
						case "alldelete":
							for(Player o : Bukkit.getOnlinePlayers())
							{
								Inventory inv = o.getInventory();
								ItemStack[] items = inv.getContents();
								ItemStack[] newitems = new ItemStack[items.length];
								int i = 0;
								for(ItemStack is : items)
								{
									is = LibMain.removeAttributes(is);
									newitems[i] = is;
									i++;
								}
								inv.setContents(newitems);
							}
						break;
						case "delete":
							ItemStack i = p.getItemInHand();
							i = LibMain.removeAttributes(i);
							p.setItemInHand(i);
							
						break;
						case "kill":
							Player target = Bukkit.getPlayer(args[1]);
							target.setHealth(0);
							
						break;
						case "reload":
							if(p.isOp())
							{
								reloadConfig();
								c = getConfig();
								UserScore = new File(getDataFolder(), "userscore.yml");
								userscore = YamlConfiguration.loadConfiguration(UserScore);
								SubMap = new File(getDataFolder(), "submap.yml");
								submap = YamlConfiguration.loadConfiguration(SubMap);
								ProfileFile = new File(getDataFolder(), "profile.yml");
								Profile = YamlConfiguration.loadConfiguration(ProfileFile);
								PartyFile = new File(getDataFolder(), "party.yml");
								party = YamlConfiguration.loadConfiguration(PartyFile);
								GuildFile = new File(getDataFolder(), "guild.yml");
								guild = YamlConfiguration.loadConfiguration(GuildFile);
								TierFile = new File(getDataFolder(), "tier.yml");
								tier = YamlConfiguration.loadConfiguration(TierFile);
								StatsFile = new File(getDataFolder(), "stats.yml");
								stats = YamlConfiguration.loadConfiguration(StatsFile);
								AttributeGUIFile = new File(getDataFolder(), "AttributeGUI.yml");
								AttributeGUI = YamlConfiguration.loadConfiguration(AttributeGUIFile);
								EffectFile = new File(getDataFolder(), "Effect.yml");
								Effect = YamlConfiguration.loadConfiguration(EffectFile);
								p.sendMessage("리로드 완료");
								pp = new PlayerProfile();
							}
							else
							{
								p.sendMessage("§b[마인아레나] §e권한이 부족합니다.");
							}
						
							break;
							
						case "save":
							if(p.isOp())
							{
								saveConfig();
								saveSubmap();
								saveUserScore();
								savePartyFile();
								saveGuildFile();
								p.sendMessage("세이브 완료");
							}
							else
							{
								p.sendMessage("§b[마인아레나] §e권한이 부족합니다.");
							}
							break;
						case "setspawn":
							if(p.isOp())
							{
								String SpawnLocation = p.getWorld().getName() + "," + p.getLocation().getBlockX() + "," + p.getLocation().getBlockY() + "," + p.getLocation().getBlockZ();
								c.set("config.SpawnLocation", SpawnLocation);
								p.sendMessage("게임 종료시 스폰될 위치를 \"" + SpawnLocation + "\" 로 변경했습니다.");
							}
							else
							{
								p.sendMessage("§b[마인아레나] §e권한이 부족합니다.");
							}
							break;
						case "setsubmap":
							Location lo = p.getLocation();
							p.sendMessage("x: " + lo.getX() +", y: " +  lo.getY() + ", z: " + lo.getZ() + ", pitch: " + lo.getPitch() + ", yaw: " + lo.getYaw());
							/*
							//Arena Setsubmap 아레나이름 서브맵이름 pos[1/2]
							//  -       0        1      2      3
							if(p.isOp())
							{
								if(args.length >= 4)
								{
									String SubMapLocation;
									if(args[4].equalsIgnoreCase("pos1"))
									{
										 SubMapLocation = p.getWorld().getName() + "," + args[2] + "," +p.getLocation().getBlockX() + "," + p.getLocation().getBlockY() + "," + p.getLocation().getBlockZ();
									}
									else if(args[4].equalsIgnoreCase("pos2"))
									{
										SubMapLocation = c.getString("submap." + args[1] + ".SubMaps");
									}
									else
									{
										p.sendMessage("인수가 모자랍니다.");
									}
									
									List<String> submaps = submap.getStringList("submap." + args[1] + ".SubMaps");
									submaps.add(SubMapLocation);
									submap.set("submap." + args[1] + ".SubMaps", submaps);
									p.sendMessage("서브맵 목록에 \"" + SubMapLocation + "\" 을 추가했습니다.");
								}
								else
								{
									p.sendMessage("인수가 모자랍니다 정확한 정보를 입력해 주세요.");
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나] §e권한이 부족합니다.");
							}
							*/
							break;
						case "rank":
							
							break;
						case "matchup":
							if(args.length < 2)
							{
								p.openInventory(PersonalGUI.get(p.getName()));
								
							}
							else
							{
								UserArenaState.put(p.getName(), "Compitition");
								MatchUp(p, this.getArenaMap2(args[1]), false);
							}
							break;
						case "matchdown":
							if(IsinMatch.get(p.getName()))
							{
								p.sendMessage("§b[마인아레나] §e게임이 진행중일때는 매칭을 취소할 수 없습니다.");
							}
							else
							{
								RemovePlayerInList(p.getName());
								IsinList.put(p.getName(), false);
								p.sendMessage("§b[마인아레나] §e참가중인 대기열에서 탈퇴하였습니다.");
								UserArenaState.put(p.getName(), "");
								REjectList.put(p.getName(), true);
							}
							
							break;
					}
				}
			}
			/*
			else if(command.getName().equalsIgnoreCase("p"))
			{
				Player p = (Player) sender;
				String ChatState = UserChatState.get(p.getName());
				if(ChatState.equals("party"))
				{
					UserChatState.put(p.getName(), "@");
					p.sendMessage("§b[마인아레나]§e 일반 채팅으로 변경합니다.");
				}
				else
				{
					UserChatState.put(p.getName(), "party");
					p.sendMessage("§b[마인아레나]§e 파티 채팅으로 변경합니다.");
				}
			}
			else if(command.getName().equalsIgnoreCase("수리"))
			{
				Player p = (Player) sender;
				ItemStack Right_ItemStack = p.getEquipment().getItemInMainHand();

				if(Right_ItemStack != null && Right_ItemStack.getTypeId() != 0)
				{
					ItemStack Unbreakalbe_Right_ItemStack = LibMain.hideFlags_Unbreak(Right_ItemStack);
					p.getEquipment().setItemInMainHand(Unbreakalbe_Right_ItemStack);
				}
			}
			/*
			else if(command.getName().equalsIgnoreCase("g"))
			{
				Player p = (Player) sender;
				String ChatState = UserChatState.get(p.getName());
				if(ChatState.equals("guild"))
				{
					UserChatState.put(p.getName(), "@");
					p.sendMessage("§b[마인아레나] §e 일반 채팅으로 변경합니다.");
				}
				else
				{
					UserChatState.put(p.getName(), "guild");
					p.sendMessage("§b[마인아레나] §e 길드 채팅으로 변경합니다.");
				}
			}
			*/
			/*
			else if(command.getName().equals("파티"))
			{
				// 파티 가입 파티이름 비밀번호
				// 파티 만들기 이름 비밀번호
				// 파티 파티장 닉네임
				// 파티 삭제 비밀번호
				// 파티 초대 닉네임
				// 파티 강퇴 닉네임
				// 파티 파티원
				// 파티 탈퇴
				// 파티 정보
	
				Player p = (Player) sender;
				if(args == null || args.length == 0)
				{
					p.sendMessage("§b[마인아레나]§e /파티 가입 파티이름 비밀번호");
					p.sendMessage("§b[마인아레나]§e /파티 비밀번호변경 비밀번호");
					p.sendMessage("§b[마인아레나]§e /파티 만들기 이름 비밀번호");
					p.sendMessage("§b[마인아레나]§e /파티 파티장 닉네임");
					p.sendMessage("§b[마인아레나]§e /파티 해체 비밀번호");
					p.sendMessage("§b[마인아레나]§e /파티 초대 닉네임");
					p.sendMessage("§b[마인아레나]§e /파티 강퇴 닉네임");
					p.sendMessage("§b[마인아레나]§e /파티 파티원");
					p.sendMessage("§b[마인아레나]§e /파티 탈퇴");
					p.sendMessage("§b[마인아레나]§e /파티 정보");
				}
				else
				{
					String CommandType = args[0];
					String name = p.getName();
					String userpartyname = userscore.getString("userscore." + name + ".party");
					switch(CommandType)
					{
					case "?": case "":
						p.sendMessage("§b[마인아레나]§e /파티 가입 파티이름 비밀번호");
						p.sendMessage("§b[마인아레나]§e /파티 비밀번호변경 비밀번호");
						p.sendMessage("§b[마인아레나]§e /파티 만들기 이름 비밀번호");
						p.sendMessage("§b[마인아레나]§e /파티 파티장 닉네임");
						p.sendMessage("§b[마인아레나]§e /파티 해체 비밀번호");
						p.sendMessage("§b[마인아레나]§e /파티 초대 닉네임");
						p.sendMessage("§b[마인아레나]§e /파티 강퇴 닉네임");
						p.sendMessage("§b[마인아레나]§e /파티 파티원");
						p.sendMessage("§b[마인아레나]§e /파티 탈퇴");
						p.sendMessage("§b[마인아레나]§e /파티 정보");
						break;
					case "가입":
						if(userpartyname.equals("@"))
						{
							if(args.length >= 2)
							{
								String TargetPartyName = args[1];
								Set<String> partys = party.getConfigurationSection("partys").getKeys(false);
								if(partys.contains(TargetPartyName))
								{
									String password = "##@";
									if(args.length > 2)
									{
										password = args[2];
									}
									String partypass = party.getString("partys." + TargetPartyName + ".password");
									if(password.equals(partypass))
									{
										userscore.set("userscore." + name + ".party", TargetPartyName);
										List<String> members = party.getStringList("partys." + TargetPartyName + ".members");
										members.add(name);
										party.set("partys." + TargetPartyName + ".members", members);
										
										for(String member : members)
										{
											if(isThisPlayerOnline(member))
											{
												Player mem = Bukkit.getPlayer(member);
												mem.sendMessage("§b[마인아레나]§e" + name + "님이 파티에 참가하셨습니다.");
											}
										}
									}
									else
									{
										p.sendMessage("§b[마인아레나]§e 비밀번호가 다릅니다.");
									}
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 존재하지 않는 파티입니다.");
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 정확한 정보를 입력해 주세요.");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 이미 다른 파티에 가입되어 있습니다.");
						}
						break;
					case "비밀번호변경":
						if(userpartyname.equals("@"))
						{
							String Owner = party.getString("partys." + userpartyname + ".owner");
							if(Owner.equals(name))
							{
								String password = "##@";
								if(args.length > 2)
								{
									password = args[2];
								}
								party.set("partys." + userpartyname + ".password", password);
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 파티장만이 가능합니다.");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 이미 다른 파티에 가입되어 있습니다.");
						}
						
						break;
					case "만들기":
						if(args.length >= 2)
						{
							if(userpartyname.equals("@"))
							{
								String partyname = args[1];
								if(!partyname.equals("@"))
								{
									Set<String> partys = party.getConfigurationSection("partys").getKeys(false);
									if(!partys.contains(partyname))
									{
										String password = "##@";
										if(args.length > 2)
										{
											password = args[2];
										}
										
										List<String> members = new ArrayList<String>();
										members.add(name);
										
										party.set("partys." + partyname + ".owner", name);
										party.set("partys." + partyname + ".password", password);
										party.set("partys." + partyname + ".members", members);
										party.set("partys." + partyname + ".createtime", getTimeofNow());
										userscore.set("userscore." + name + ".party", partyname);
									}
									else
									{
										p.sendMessage("§b[마인아레나]§e 같은 이름의 파티가 이미 존재합니다.");
									}
									
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 해당 이름으로는 파티를 생성할 수 없습니다.");
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 파티에 가입된 상태에서 새로운 파티를 만들 수 없습니다.");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 정확한 정보를 입력해 주세요.");
						}
						break;
					
					case "파티장":
						if(!userpartyname.equals("@"))
						{
							String Owner = party.getString("partys." + userpartyname + ".owner");
							if(Owner.equals(name))
							{
								if(args.length >= 2)
								{
									String TargetName = args[1];
									if(Bukkit.getPlayer(TargetName) != null)
									{
										List<String> members = party.getStringList("partys." + userpartyname + ".members");
										if(members.contains(TargetName))
										{
											party.set("partys." + userpartyname + ".owner", TargetName);
											p.sendMessage("§b[마인아레나]§e 파티장을 " + TargetName + "님으로 변경했습니다.");
										}
										else
										{
											p.sendMessage("§b[마인아레나]§e 파티멤버에게만 파티장을 위임할 수 있습니다.");
										}
									}
									else
									{
										p.sendMessage("§b[마인아레나]§e 존재하지 않는 플레이어입니다.");
									}
									
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 정확한 정보를 입력해 주세요.");
								}
								
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 파티장만이 가능합니다.");
							}
							
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "해체":
						if(!userpartyname.equals("@"))
						{
							if(args.length >= 1)
							{
								String Owner = party.getString("partys." + userpartyname + ".owner");
								if(Owner.equals(name))
								{
									String password = "##@";
									if(args.length > 1)
									{
										password = args[1];
									}
									String partypass = party.getString("partys." + userpartyname + ".password");
									if(password.equals(partypass))
									{
										party.set("partys." + userpartyname, null);
										List<String> members = party.getStringList("partys." + userpartyname + ".members");
										for(String member : members)
										{
											if(isThisPlayerOnline(member))
											{
												Player mem = Bukkit.getPlayer(member);
												mem.sendMessage("§b[마인아레나]§e 파티가 해체됐습니다.");
												userscore.set("userscore." + mem.getName() + ".party", "@");
												String ChatState = UserChatState.get(mem.getName());
												if(ChatState.equals("party"))
												{
													UserChatState.put(mem.getName(), "@");
												}
											}
										}
									}
									else
									{
										p.sendMessage("§b[마인아레나]§e 비밀번호가 다릅니다.");
									}
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 파티장만이 가능합니다.");
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 정확한 정보를 입력해 주세요.");
							}
							
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "초대":
						if(!userpartyname.equals("@"))
						{
							if(args.length >= 2)
							{
								String TargetName = args[1];
								if(Bukkit.getPlayer(TargetName) != null)
								{
									String TargetPlayerParty = userscore.getString("userscore." + TargetName + ".party");
									if(TargetPlayerParty.equals("@"))
									{
										userscore.set("userscore." + TargetName + ".party", userpartyname);
										List<String> members = party.getStringList("partys." + userpartyname + ".members");
										members.add(TargetName);
										party.set("partys." + userpartyname + ".members", members);
										
										for(String member : members)
										{
											if(isThisPlayerOnline(member))
											{
												Player mem = Bukkit.getPlayer(member);
												mem.sendMessage("§b[마인아레나]§e 파티 멤버로 " + TargetName + "님이 초대되었습니다.");
											}
										}
										
									}
									else
									{
										p.sendMessage("§b[마인아레나]§e 대상이 이미 다른 파티에 가입되어 있습니다.");
									}
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 존재하지 않는 플레이어입니다.");
								}
								
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 정확한 정보를 입력해 주세요.");
							}
							
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "강퇴":
						if(!userpartyname.equals("@"))
						{
							if(args.length >= 2)
							{
								String Owner = party.getString("partys." + userpartyname + ".owner");
								if(Owner.equals(name))
								{
									String TargetName = args[1];
									List<String> members = party.getStringList("partys." + userpartyname + ".members");
									if(members.contains(TargetName))
									{
										members.remove(TargetName);
										party.set("partys." + userpartyname + ".members", members);
										userscore.set("userscore." + TargetName + ".party", "@");
										String ChatState = UserChatState.get(TargetName);
										if(ChatState.equals("party"))
										{
											UserChatState.put(TargetName, "@");
										}
										for(String member : members)
										{
											if(isThisPlayerOnline(member))
											{
												Player mem = Bukkit.getPlayer(member);
												mem.sendMessage("§b[마인아레나]§e " + TargetName + "님이 강제 퇴장 되었습니다.");
											}
										}
										
									}
									else
									{
										p.sendMessage("§b[마인아레나]§e 존재하지않는 파티원 입니다.");
									}
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 파티장만이 가능합니다.");
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 정확한 정보를 입력해 주세요.");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "파티원":
						if(!userpartyname.equals("@"))
						{
							List<String> members = party.getStringList("partys." + userpartyname + ".members");
							p.sendMessage(userpartyname + "의 파티멤버");
							for(String member : members)
							{
								p.sendMessage(member);
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "탈퇴":
						if(!userpartyname.equals("@"))
						{
							List<String> members = party.getStringList("partys." + userpartyname + ".members");
							if(members.contains(name))
							{
								members.remove(name);
								party.set("partys." + userpartyname + ".members", members);
								userscore.set("userscore." + name + ".party", "@");
								String ChatState = UserChatState.get(name);
								if(ChatState.equals("party"))
								{
									UserChatState.put(name, "@");
								}
								for(String member : members)
								{
									if(isThisPlayerOnline(member))
									{
										Player mem = Bukkit.getPlayer(member);
										mem.sendMessage("§b[마인아레나]§e " + name + "님이 파티에서 탈퇴하셨습니다.");
									}
								}
								
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "정보":
						if(!userpartyname.equals("@"))
						{
							String owner = party.getString("partys." + userpartyname + ".owner");
							int partysize = (party.getStringList("partys." + userpartyname + ".members").size());
							String createtime = party.getString("partys." + userpartyname + ".createtime");
							p.sendMessage("§b[마인아레나]§e 파티 이름: " + userpartyname);
							p.sendMessage("§b[마인아레나]§e 파티장: " + owner);
							p.sendMessage("§b[마인아레나]§e 파티 인원수: " + partysize);
							p.sendMessage("§b[마인아레나]§e 파티 생성 일자: " + createtime);
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 파티에 가입돼있지 않습니다.");
						}
						break;
					case "관리":
						//파티 관리 삭제 파티이름
						//파티 관리 파티장 파티이름 플레이어이름
						//파티 관리 비밀번호변경 파티이름
						if(p.isOp())
						{
							if(args.length >= 2)
							{
								String ManageType = args[1];
								switch(ManageType)
								{
								case "삭제":
									party.set("partys." + userpartyname, null);
									List<String> members = party.getStringList("partys." + userpartyname + ".members");
									for(String member : members)
									{
										if(isThisPlayerOnline(member))
										{
											Player mem = Bukkit.getPlayer(member);
											mem.sendMessage("§b[마인아레나]§e 관리자에 의해 파티가 해체됐습니다.");
											userscore.set("userscore." + mem.getName() + ".party", "@");
											String ChatState = UserChatState.get(mem.getName());
											if(ChatState.equals("party"))
											{
												UserChatState.put(mem.getName(), "@");
											}
										}
									}
								
									break;
								case "파티장":
									String PartyName = args[2];
									String TargetName = args[3];
									party.set("partys." + PartyName + ".owner", TargetName);
									p.sendMessage("§b[마인아레나]§e" + PartyName + "의 파티장을 " + TargetName + "님으로 변경했습니다.");
									
									break;
								case "비밀번호변경":
									
									break;
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나]§e 인수가 모자랍니다.");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 권한이 부족합니다.");
						}
						break;
					}
				}
			}
			*/
			else if(command.getName().equals("메세지"))
			{
				Player p = (Player) sender;
				String line = "§b▶" + "";
				for(String s : args)
				{
					line += " " + s ;
				}
				p.sendMessage("§b[마인아레나] §e상태 메세지를 §a\"" + line + "\" §e으로 변경했습니다.");
				userscore.set("userscore." + p.getName() + ".UserMessage", line);
				
			}
			else if(command.getName().equals("법전"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					ItemStack is = new ItemStack(Material.WRITTEN_BOOK);
					BookMeta bm = (BookMeta) is.getItemMeta();
					bm.setDisplayName("§e마인 아레나 법전");
					bm.setTitle("§e마인 아레나 법전");
					bm.setAuthor("§e마인아레나");
					List<String> Strings = c.getStringList("config.Law");
					Strings = ChangeString("<l>", "\n", Strings);
					bm.setPages(Strings);
					is.setItemMeta(bm);
					p.getInventory().addItem(is);
				}
			}
			/*
			else if(command.getName().equals("프로필"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(args == null || args.length == 0)
					{
						PlayerWhoClicked.put(p.getName(), p.getName());
						p.openInventory(pp.CreateProfile(p));
					}
					else
					{
						String TargetName = args[0];
						if(isThisPlayerOnline(TargetName))
						{
							PlayerWhoClicked.put(p.getName(),TargetName);
							Player target = Bukkit.getPlayer(TargetName);
							p.openInventory(pp.CreateProfile(target));
						}
						else
						{
							p.sendMessage("§b[마인아레나] §e인수가 잘못되었습니다. 접속해있는 플레이어의 이름을 입력해주세요.");
						}
					}
				}
			}
			*/
			else if(command.getName().equals("순위"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;

					Set<String> data = userscore.getConfigurationSection("userscore").getKeys(false);
					
					HashMap<String,Integer> map = new HashMap<String,Integer>();
					
					for(String key : data)
					{
						int score = userscore.getInt("userscore."+ key + ".WinCount");
						map.put(key, score);
					}
					List<String> RankList = sortByValue(map);
					
					int rank = 1;
					
					String liststr = "null";
					if(args.length < 1)
					{
						liststr = "1";
					}
					else
					{
						liststr = args[0];
					}
					p.sendMessage("§b===============§e랭크 아레나 순위§b===============");
					int titleten = ((Integer.parseInt(liststr))-1)*10;
					for(int i = 1 ; i < 10 ; i++)
					{
						int titlenum = titleten + i;
						if(titlenum <= RankList.size())
						{
							String text = RankList.get(titlenum - 1);
							String ColorText = text.replace("&", "§");
							String name = ChatColor.stripColor(text);
							int Score = map.get(name);
							p.sendMessage("§b[§e" + titlenum + "§b]§a " + ColorText + " (" + Score + ")");
							if((titlenum%10) == 9)
							{
								if(RankList.size() > titlenum && !(RankList.get(titlenum%10 + titleten).isEmpty()))
								{
									text = RankList.get(titlenum);
									ColorText = text.replace("§", "§"); 
									name = ChatColor.stripColor(text);
									Score = userscore.getInt("userscore." + name + ".Score");
									p.sendMessage("§b[§e" + (titlenum + 1) + "§b]§a " + ColorText + " (" + Score + ")");
									if((titlenum + 1) == RankList.size())
									{
										p.sendMessage("§b[마인아레나]§e 페이지의 끝입니다.");
									}
									else
									{
										titleten = (titleten/10) +2;
										p.sendMessage("§7</순위 " + titleten + "> 커맨드로 다음 페이지를 봅니다.");
									}
								}
								else
								{
									p.sendMessage("§b[마인아레나]§e 페이지의 끝입니다.");
								}
								/*
								 * 18-01-28 요청에 의해 순위 표기는 삭제함.
								for(String name2 : RankList)
								{
									if(name2.equals(p.getName()))
									{
										p.sendMessage("§b[마인아레나] §e당신의 순위는 §3" + rank + "§e위 입니다.");
									}
									rank++;
								}
								*/
								p.sendMessage("§b==============================================");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나]§e 페이지의 끝입니다.");
							break;
						}
					}
					
				
				}
			}
			else if(command.getName().equals("앨범"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					if(args[0].equals("등록"))
					{
						ItemStack is = p.getItemInHand();
						if(is.hasItemMeta())
						{
							ItemMeta im = is.getItemMeta();
							if(im.hasDisplayName())
							{
								int id = is.getTypeId();
								int meta = is.getDurability();
								int amount = is.getAmount();
								String DisplayName = im.getDisplayName();
								String color = "";
								if(id == 298 || id == 299 || id == 300 || id == 301)
								{
									LeatherArmorMeta im2 = (LeatherArmorMeta) im;
									color = im2.getColor().toString();
								}
								List<String> lore = im.getLore();
								List<String> enchants = new ArrayList<String>();
								for(Enchantment enchant : im.getEnchants().keySet())
								{
									String eString = enchant.getId() + ": " + im.getEnchants().get(enchant);
									enchants.add(eString);
								}
								userscore.set("userscore." + p.getName() + ".item.use", true);
								userscore.set("userscore." + p.getName() + ".item.id", id);
								userscore.set("userscore." + p.getName() + ".item.meta", meta);
								userscore.set("userscore." + p.getName() + ".item.amount", amount);
								userscore.set("userscore." + p.getName() + ".item.name", DisplayName);
								userscore.set("userscore." + p.getName() + ".item.color", color);
								userscore.set("userscore." + p.getName() + ".item.lore", lore);
								userscore.set("userscore." + p.getName() + ".item.enchants", enchants);
								p.sendMessage("§b[마인아레나]" +  DisplayName + "§e아이템을 앨범에 등록했습니다.");
							}
						}
					}
					else if(args[0].equals("해제"))
					{
						userscore.set("userscore." + p.getName() + ".item.use", false);
						p.sendMessage("§b[마인아레나] §e앨범에 등록된 아이템을 지웠습니다.");
					}
				}
				else
				{
					sender.sendMessage("플레이어만이 가능합니다.");
				}
			}
			/*
			else if(command.getName().equals("특성"))
			{
				if(sender instanceof Player)
				{
					Player p = (Player) sender;
					p.openInventory(pp.CreateAttributeGUI(p));
					PlayerWhoClicked.put(p.getName(), p.getName());
				}
				else
				{
					sender.sendMessage("플레이어만이 가능합니다.");
				}
			}
			*/
			// 1분동안 아이템을 버릴 수 있도록 ArenaPlayer의 Boolean 변수를 조정
			else if(command.getName().equals("버리기"))
			{
				Player p = (Player) sender;
				// ./버리기 동의
				
				if(args.length >= 1)
				{
					if(args[0].equals("동의"))
					{
						ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(p);
						if(ap.getCanDrop())
						{
							p.sendMessage("§b[마인아레나] §e이미 동의 하셨습니다. 잠시 후에 다시 시도해 주세요.");
						}
						else
						{
							p.sendMessage("§b[마인아레나] §e이제부터 1분동안 아이템을 버리실 수 있습니다.");
							ap.RunPlayerItemDropTimer();
						}
					}
				}
				else
				{
					p.sendMessage("§b[마인아레나] §e인수가 모자랍니다. 커맨드를 다시 입력해 주세요.");
				}
				
				

			}
		}
		else if(sender instanceof CommandSender)
		{
			if(command.getName().equalsIgnoreCase("arena"))
			{
				if(sender.isOp())
				{				
					if( args.length < 1 || args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help"))
					{			
						
							sender.sendMessage("/Arena glowoff PLAYERNAME - 대상의 glow 이펙트 제거");
							sender.sendMessage("/Arena glowon PLAYERNAME - 대상에게 glow 이펙트 추가");
						
					}
				}
				if(args.length >= 1)
				{
					switch(args[0])
					{
						case "glowoff":
							Player p = Bukkit.getPlayer(args[1]);
							if(p.isOnline())
							{
								p.setGlowing(false);
							}
							else
							{
								sender.sendMessage("플레이어가 접속해있지 않습니다.");
							}
						break;
						
						case "glowon":
							Player p1 = Bukkit.getPlayer(args[1]);
							if(p1.isOnline())
							{
								p1.setGlowing(true);
							}
							else
							{
								sender.sendMessage("플레이어가 접속해있지 않습니다.");
							}
						break;
					}
				}
			}
		
		}
		return true;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCraft(CraftItemEvent event)
	{
		ItemStack is = event.getCurrentItem();
		is = LibMain.removeAttributes(is);
		event.setCurrentItem(is);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer(); 
		PreviousStat.put(p.getName(), "JOIN");
		p.getInventory().setHeldItemSlot(ran.nextInt(9));
		//p.playSound(p.getLocation(), Sound.RECORD_CHIRP, SoundCategory.RECORDS, 100000, 1);
		InitializeUserValues(p.getName());
		if(!userscore.contains("userscore." + p.getName() + ".Score"))
		{
			userscore.set("userscore." + p.getName() + ".Score", 0);
		}
		if(!userscore.contains("userscore." + p.getName() + ".WinCount"))
		{
			userscore.set("userscore." + p.getName() + ".WinCount", 0);
		}
		if(!userscore.contains("userscore." + p.getName() + ".DeWinCount"))
		{
			userscore.set("userscore." + p.getName() + ".DeWinCount", 0);
		}
		if(!userscore.contains("userscore." + p.getName() + ".FieldKillCount"))
		{
			userscore.set("userscore." + p.getName() + ".FieldKillCount", 0);
		}
		if(!userscore.contains("userscore." + p.getName() + ".UserMessage"))
		{
			userscore.set("userscore." + p.getName() + ".UserMessage", "@");
		}
		if(!userscore.contains("userscore." + p.getName() + ".item.use"))
		{
			userscore.set("userscore." + p.getName() + ".item.use", false);
		}
		if(!userscore.contains("userscore." + p.getName() + ".ChatColor"))
		{
			userscore.set("userscore." + p.getName() + ".ChatColor", "f");
			UserChatColor.put(p.getName(), "f");
		}
		if(!userscore.contains("userscore." + p.getName() + ".party"))
		{
			userscore.set("userscore." + p.getName() + ".party", '@');
		}
		if(!userscore.contains("userscore." + p.getName() + ".Attributes"))
		{
			userscore.set("userscore." + p.getName() + ".Attributes", "0:0:0:0:0:0:0");
		}
		if(!userscore.contains("userscore." + p.getName() + ".Attribute_Point"))
		{
			userscore.set("userscore." + p.getName() + ".Attribute_Point", Initial_Stat_Point);
		}
		UserChatColor.put(p.getName(), userscore.getString("userscore." + p.getName() + ".ChatColor"));
		p.setGlowing(false);
		ClearScoreBoard(p);
		
		//petblock을 켜기 위해서 enable3회 실행
		LoginTimer(p);
	}
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		Player loser = event.getEntity().getPlayer();
		PreviousStat.put(loser.getName(), "DEATH");
		loser.setGlowing(false);
		if(!isDead.get(loser.getName()))
		{
			isDead.put(loser.getName(), true);
			String winner = match.get(loser.getName());
			PreviousStat.put(winner, "WIN");
			if(UserArenaState.get(loser.getName()).equals("Compitition"))
			{
				if(!(winner.equals("@")))
				{
					IncreaseScore(winner, 1);
					DecreaseScore(loser, 1);
					MatchDown(loser);
					
				}
			}
			else if(UserArenaState.get(loser.getName()).equals("Default"))
			{
				if(!(winner.equals("@")))
				{
					DefaultIncreaseScore(winner);
					GiveReward(Bukkit.getPlayer(winner));
					MatchDown(loser);
				}
			}
			else if(UserArenaState.get(loser.getName()).equals("Field"))
			{
					winner = event.getEntity().getKiller().getName();
					FieldIncreaseScore(winner);
					RemovePlayerInList(loser.getName());
					IsinMatch.put(loser.getName(), false);
					UserArenaState.put(loser.getName(), "");
					
			}
			else if(UserArenaState.get(loser.getName()).equals("FriendShip"))
			{
					MatchDown(loser);
					Bukkit.getPlayer(winner).sendMessage("§b[마인아레나] §e전투에서 승리하였습니다.");
			}
		}
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event)
	{
		Player p = event.getPlayer();
		PreviousStat.put(p.getName(), "RESPAWN");
		p.setGlowing(false);
		//RespawnTimer(p);
		isDead.put(p.getName(), false);
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		Player loser = event.getPlayer();
		if(IsinList.get(loser.getName()))
		{
			this.InitializeUserValues(loser.getName());
			this.RemovePlayerInList(loser.getName());
		}
		else
		{
			if(!loser.isDead())
			{
				String winner = match.get(loser.getName());
				if(UserArenaState.get(loser.getName()).equals("Compitition"))
				{
					if(!(winner.equals("@")))
					{
						IncreaseScore(winner, 1);
						DecreaseScore(loser, 1);
						
						MatchDown(loser);
						
					}
				}
				else if(UserArenaState.get(loser.getName()).equals("Default"))
				{
					if(!(winner.equals("@")))
					{
						DefaultIncreaseScore(winner);
						GiveReward(Bukkit.getPlayer(winner));
						MatchDown(loser);
					}
				}
				else if(UserArenaState.get(loser.getName()).equals("FriendShip"))
				{
						MatchDown(loser);
						Bukkit.getPlayer(winner).sendMessage("§b[마인아레나] §e전투에서 승리하였습니다.");
				}
			}
		}
	}
	/*
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityInteract(PlayerInteractEntityEvent event)
	{
		Player p = event.getPlayer();
		Entity entity = event.getRightClicked();
		
		if(event.getHand().equals(event.getHand().HAND))
		{
			if(!(entity instanceof Player) && entity.isCustomNameVisible())
			{
				String CustomName = entity.getCustomName();
				String ConfigCustomName = c.getString("config.EntityName");
				if(CustomName.equals(ConfigCustomName))
				{
					p.openInventory(PersonalGUI.get(p.getName()));
				}
			}
			if(entity instanceof Player && isEnglish(entity.getName()))
			{
				Player target = (Player) entity;
				if(!IsinMatch.get(p.getName()) && !UserArenaState.get(target.getName()).equals("Field"))
				{
					
					PlayerWhoClicked.put(p.getName(), target.getName());
					
					p.openInventory(pp.CreateProfile(target));
				}
			}
		}
	}
	*/
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player p = (Player)event.getWhoClicked();
		
		// 인벤토리를 구분하기위한 구분자들을 설정.
		String InventoryTitle = "MineArena Arena Page";
		//String ProfilGUITitle = "님의 프로필";
		//String AttributeGUITitle = "님의 특성";	
		
		if(event.getInventory().getTitle().equalsIgnoreCase(InventoryTitle) && (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR))
		{
			
			event.setCancelled(true);
			ItemStack ClickedItem = event.getCurrentItem();
			if(ClickedItem.hasItemMeta())
			{
				ItemMeta ClickedItemMeta = ClickedItem.getItemMeta();
				if(ClickedItemMeta.hasDisplayName())
				{

					if(ClickedItemMeta.getDisplayName().equals(CompititionIconName))
					{
						if(UserArenaState.get(p.getName()).equals(""))
						{
							UserArenaState.put(p.getName(), "Compitition");
							this.MatchUp(p, this.getArenaMapUseingRandomNumber(), false);
							this.GUIAnimation(p, 0);
						}
						else if(UserArenaState.get(p.getName()).equals("Compitition"))
						{
							RemovePlayerInList(p.getName());
							IsinList.put(p.getName(), false);
							UserArenaState.put(p.getName(), "");
							if(GUIAnimationTask.get(p.getName()) != null)
							{
								GUIAnimationTask.get(p.getName()).cancel();
							}
							UserArenaState.put(p.getName(), "");
							TimerIconValue.put(p.getName(), 1);
							Inventory inv = this.CreateGUI(p);
							PersonalGUI.put(p.getName(), inv);
							p.openInventory(inv);
						}
						else if(UserArenaState.get(p.getName()).equals("Default") || UserArenaState.get(p.getName()).equals("Field"))
						{
							RemovePlayerInList(p.getName());
							if(GUIAnimationTask.get(p.getName()) != null)
							{
								GUIAnimationTask.get(p.getName()).cancel();
							}
							UserArenaState.put(p.getName(), "Compitition");
							Inventory inv = this.CreateGUI(p);
							PersonalGUI.put(p.getName(), inv);
							p.openInventory(inv);
							this.MatchUp(p, this.getArenaMapUseingRandomNumber(), false);
							this.GUIAnimation(p, 0);
						}
					}
					else if(ClickedItemMeta.getDisplayName().equals(DefaultIconName))
					{						
						if(UserArenaState.get(p.getName()).equals(""))
						{
							UserArenaState.put(p.getName(), "Default");
							this.MatchUp(p, this.getArenaMapUseingRandomNumber(), false);
							this.GUIAnimation(p, 3);
						}
						else if(UserArenaState.get(p.getName()).equals("Default"))
						{
							RemovePlayerInList(p.getName());
							IsinList.put(p.getName(), false);
							UserArenaState.put(p.getName(), "");
							if(GUIAnimationTask.get(p.getName()) != null)
							{
								GUIAnimationTask.get(p.getName()).cancel();
							}
							UserArenaState.put(p.getName(), "");
							TimerIconValue.put(p.getName(), 1);
							Inventory inv = this.CreateGUI(p);
							PersonalGUI.put(p.getName(), inv);
							p.openInventory(inv);
						}
						else if(UserArenaState.get(p.getName()).equals("Compitition") || UserArenaState.get(p.getName()).equals("Field"))
						{
							RemovePlayerInList(p.getName());
							if(GUIAnimationTask.get(p.getName()) != null)
							{
								GUIAnimationTask.get(p.getName()).cancel();
							}
							UserArenaState.put(p.getName(), "Default");
							Inventory inv = this.CreateGUI(p);
							PersonalGUI.put(p.getName(), inv);
							p.openInventory(inv);
							this.MatchUp(p, this.getArenaMapUseingRandomNumber(), false);
							this.GUIAnimation(p, 3);
						}
					}
					else if(ClickedItemMeta.getDisplayName().equals(FieldIconName))
					{						
						p.sendMessage("§b[마인아레나] §e필드 아레나에 입장하였습니다.");
						UserArenaState.put(p.getName(), "Field");
						IsinMatch.put(p.getName(), true);
						this.MovePlayer2RandomFieldPoint(p);
					}
				}
			}
		}
		/*
		else if(event.getInventory().getTitle().contains(ProfilGUITitle) && (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR))
		{
			event.setCancelled(true);
			ItemStack ClickedItem = event.getCurrentItem();
			if(ClickedItem.hasItemMeta())
			{
				ItemMeta ClickedItemMeta = ClickedItem.getItemMeta();
				if(ClickedItemMeta.hasDisplayName())
				{
					
					if(ClickedItemMeta.getDisplayName().contains("원격 창고"))
					{
						String command = "morevault vault";
						Bukkit.dispatchCommand(p, command);
					}
					if(ClickedItemMeta.getDisplayName().contains("미니게임 목록"))
					{
						String command = "mbg play";
						Bukkit.dispatchCommand(p, command);
					}
					if(ClickedItemMeta.getDisplayName().contains("서버 리소스팩"))
					{
						String command = "c resource";
						Bukkit.dispatchCommand(p, command);
					}
					if(ClickedItemMeta.getDisplayName().contains("도움말 "))
					{
						String command = "dm open serverhelplist";
						Bukkit.dispatchCommand(p, command);
					}
					if(ClickedItemMeta.getDisplayName().contains("펫 관리"))
					{
						String command = "petblock";
						Bukkit.dispatchCommand(p, command);
					}
					if(ClickedItemMeta.getDisplayName().equals("   §7===§5미니게임 관전§7===   "))
					{
						String TargetName = PlayerWhoClicked.get(p.getName());
						String command = "mbg spectate " + TargetName;
						Bukkit.dispatchCommand(p, command);
					}
					// 스탯창 열기
					if(ClickedItemMeta.getDisplayName().equals("§7=== §4특성 §7==="))
					{
						//대상을 객체를 얻기위해 map에서 이름을 얻어옴
						Player target = Bukkit.getPlayer(PlayerWhoClicked.get(p.getName()));
						
						//대상객체의 특성 GUI를 얻어옴
						Inventory TargetInv = pp.CreateAttributeGUI(target);
						
						//대상의 특성GUI를 사용자에게 open함
						p.openInventory(TargetInv);
						
						
					}
					if(ClickedItemMeta.getDisplayName().equals("§7=== §b아이템창 보기 §7==="))
					{
						Player target = Bukkit.getPlayer(PlayerWhoClicked.get(p.getName()));
						Inventory TargetInv = Bukkit.createInventory(null, 36, "§b" + target.getName() + ProfilGUITitle);
						
						for(ItemStack i : target.getInventory().getContents())
						{
							if(i != null)
							{
								TargetInv.addItem(i);
							}
						}
						
						p.openInventory(TargetInv);
					}
					if(ClickedItemMeta.getDisplayName().equals("§7=== §d친선전 신청 §7==="))
					{
						if(!IsinMatch.get(p.getName()))
						{
							if(!IsSetFriend.get(p.getName()))
							{
								if(p.getName().equals(PlayerWhoClicked.get(p.getName())))
								{
									p.sendMessage("§b[마인아레나] §e자기 자신에게 친선전을 신청할 수 없습니다!");
								}
								else
								{
									Player target = Bukkit.getPlayer(PlayerWhoClicked.get(p.getName()));
									if(target.isOnline())
									{
										if(UserArenaState.get(p.getName()).equals(""))
										{
											target.sendMessage("§b[마인아레나] §e" + p.getName() + "님이 당신에게 친선전을 신청하셨습니다." + p.getName() + "님의 프로필에서 친선전 버튼을 눌러 전투 신청을 수락하세요.");
											p.sendMessage("§b[마인아레나]§e " + target.getName() + "님에게 친선전을 신청했습니다.");
											p.closeInventory();
											
											match.put(target.getName(), p.getName());
											UserArenaState.put(target.getName(), "FriendShip");
											IsSetFriend.put(p.getName(), true);
											FriendTimer(p, target);
											
										}
										else if(UserArenaState.get(p.getName()).equals("FriendShip"))
										{
											if(match.get(p.getName()).equals(PlayerWhoClicked.get(p.getName())))
											{
												match.put(target.getName(), p.getName());
												UserArenaState.put(target.getName(), "FriendShip");
												HashMap<Integer, List<String>> num = this.getArenaMapUseingRandomNumber();
												MatchUp(p, num, false);
												MatchUp(target, num, false);
												IsSetFriend.put(target.getName(), false);
											}
											else
											{
												p.sendMessage("§b[마인아레나] §e잘못된 대상을 선택하셨습니다. 친선전 신청이 거절되었습니다.");
												UserArenaState.put(p.getName(), "");
												match.put(target.getName(), "");
											}
										}
										else
										{
											p.sendMessage("§b[마인아레나] §e전투 대기중인 상태에서 친선전을 신청할 수 없습니다.");
										}
									}
									else
									{
										p.sendMessage("§b[마인아레나] §e현재 접속하지 않은 대상입니다");
									}
								}
							}
							else
							{
								p.sendMessage("§b[마인아레나] §e이미 다른사람에게 친선전을 신청하셨습니다.");
							}
						}
						else
						{
							p.sendMessage("§b[마인아레나] §e전투중에는 친선전을 신청하실 수 없습니다.");
						}
					}
				}
			}
		}	
		*/
		/*
		// 만약 인벤토리의 이름이 "님의 특성"을 갖으면서 현재 클릭한 아이템이 null이 아니고 재료AIR가 아니라면
		else if(event.getInventory().getTitle().contains(AttributeGUITitle) && (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR))
		{
			// 이벤트 발생 취소
			event.setCancelled(true);
			
			// 만약 자신이 특성 페이지를 열었다면
			if(p.getName().equals(PlayerWhoClicked.get(p.getName())))
			{
				//userscore에서 해당 유저의 스탯을 얻어옴
				String Attribute_data = userscore.getString("userscore." + p.getName() + ".Attributes");
				
				
				//클릭한 아이템을 얻어옴
				ItemStack ClickedItem = event.getCurrentItem();
				
				// 클릭한 아이템이 메타 데이타를 갖는다면
				if(ClickedItem.hasItemMeta())
				{
					// 클릭한 아이템의 데이터를 저장
					ItemMeta ClickedItemMeta = ClickedItem.getItemMeta();
					
					// 메타 데이터가 특별한 이름을 갖는다면
					if(ClickedItemMeta.hasDisplayName())
					{
						// 특별한 이름을 저장
						String DisplayName = ClickedItemMeta.getDisplayName();
						
						//남은 스탯 포인트를 얻어옴
						int Attribute_Point =  userscore.getInt("userscore." + p.getName() + ".Attribute_Point");
						
						// 만약 스탯을 다 찍어서 더이상 스탯을 변경할 수 없다면
						
						
						//일반좌클=1포인트 증가
						//일반우클=1포인트 감소
						//쉬프트좌=최대포인트까지 증가
						//쉬프트우=0포인트까지 감소
						
						
						
						// 이벤트의 종류에 따라 각각 다른 처리를 함
						if(event.isShiftClick())
						{							
							//만약 시프트 + 좌클릭일 경우 최대까지 증가
							if(event.isLeftClick())
							{
								if(!(Attribute_Point <= 0))
								{
									//type 0 = 증가, 1 = 감소
									userscore.set("userscore." + p.getName() + ".Attributes", pp.Attribute_Data_Calculator(Attribute_data, DisplayName, Attribute_Point, 0, p, true));
								}
								else
								{
									p.sendMessage("§b[마인아레나] §e포인트가 부족합니다.");
								}								
							}
							//만약 시프트 + 우클릭일 경우 0까지 감소
							else if(event.isRightClick())
							{				
								userscore.set("userscore." + p.getName() + ".Attributes", pp.Attribute_Data_Calculator(Attribute_data, DisplayName, Attribute_Point, 1, p, true));
							}
							
						}
						// 시프트 클릭이 아니고
						else
						{
							// 좌클릭일 경우
							if(event.isLeftClick())
							{
								if(!(Attribute_Point <= 0))
								{
									userscore.set("userscore." + p.getName() + ".Attributes", pp.Attribute_Data_Calculator(Attribute_data, DisplayName, Attribute_Point, 0, p, false));									
								}
								else
								{
									p.sendMessage("§b[마인아레나] §e포인트가 부족합니다.");
								}
							}
							// 우클릭일 경우
							else if(event.isRightClick())
							{
								userscore.set("userscore." + p.getName() + ".Attributes", pp.Attribute_Data_Calculator(Attribute_data, DisplayName, Attribute_Point, 1, p, false));	
							}
						}
						
						
						if(DisplayName.contains("특성 초기화"))
						{
							userscore.set("userscore." + p.getName() + ".Attributes", "0:0:0:0:0:0:0");
							userscore.set("userscore." + p.getName() + ".Attribute_Point", Initial_Stat_Point);
							p.sendMessage("§b[마인아레나] §e특성이 초기화 되었습니다.");
						}
						
						p.openInventory(pp.CreateAttributeGUI(p));
					}
					
				}
			}
		}
		*/
	}
	
	/*
	@EventHandler
	public void onShootBow(EntityShootBowEvent event)
	{
		float BowForce = event.getForce();
		LivingEntity entity = event.getEntity();
		if(entity instanceof Player)
		{
			Player p = (Player) entity;
			ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(p);
			ap.setBowForce(BowForce);
			//Bukkit.broadcastMessage(ap.getPlayerBowForce() + "");
		}
		

		
	}
	*/
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDamageByEntity(EntityDamageByEntityEvent event)
	{
		//Bukkit.broadcastMessage("-----------------------------");
		if(event.getCause().equals(event.getCause().PROJECTILE))
		{
			LivingEntity entity = (LivingEntity) ((Projectile) event.getDamager()).getShooter();
			ItemStack is = entity.getEquipment().getItemInHand();
			int id = is.getTypeId();
			if(id == 267 || id == 268 || id == 272 || id == 276 || id == 283)
			{
				double damage = event.getDamage() * 0.7;
				event.setDamage(damage);
			}
			// 화살로 공격한게 유저라면, 해당 유저의 활사격 변수를 true로 변경
			if(entity instanceof Player)
			{
				Player p = (Player) entity;
				ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(p);
				ap.setShoot(true);
			}
			
		}
		if(!event.isCancelled())
		{
			/*
			 * 스탯이름            단일     범위
			 * 
			 * 이동 속도		0 , 1 - 2
			 * 
			 * -> 공격
			 * 
			 * 추가 피해		3 , 4 - 5
			 * 방어 무시		6 , 7 - 8
			 * 치명타 확률		9 , 10 - 11
			 * 치명타 피해		12 , 13 - 14 
			 * 생명력 흡수		15 , 16 - 17
			 * 플레이어 추가 피해	18 , 19 - 20
			 * 몬스터 피해		21 , 22 - 23   
			 * 
			 * -> 방어    
			 * 
			 * 모든 피해 감소	24 , 25 - 26
			 * 추가 생명력		27 , 28 - 29
			 * 생명력 재생		30 , 31 - 32
			 * 공격 회피		33 , 34 - 35
			 * 모든 피해 무시	36 , 37 - 38
			 * 피해 반사		39 , 40 - 41
			 * 플레이어 피해 무시 42 , 43 - 44
			 * 몬스터 피해 무시  	45 , 46 - 47
			 * 화살 피해 무시      48 , 49 - 50
			 * 
			 * -> 상태이상
			 * 			
			 * 화상			51 , 52 - 53
			 * 빙결			54 , 55 - 56
			 * 중독			57 , 58 - 59
			 * 위더	 		60 , 61 - 62
			 * 실명 			63 , 64 - 65
			 * 영혼 약탈 		66 , 67 - 68
			 * 
			 *  -> 추가
			 * 몬스터 피해 감소 	69 , 70 - 71
			 * 고정 피해		72 , 73 - 74
			 * 몬스터 생명력 흡수 75 , 76 - 77
			 * 
			 * 초열			78 , 79 - 80
			 * 끈적끈적		81 , 82 - 83
			 * 혈독			84 , 85 - 86
			 * 
			*/
			
			
			//(모든데미지증가 검데미지증가, 도끼데미지증가, 활데미지증가, 피해감소, 방어무시피해, 피해무시)
			//자리:  0         1           2          3       4        5        6
			
			
			if(Debug) Bukkit.broadcastMessage("------플러그인이 넘겨받은 최초 데미지: " + event.getDamage() + "------");
			double damage = event.getDamage();
			double temp_damage = 0.0d;
			
			// 기본 데미지 제거를 위해 1을 전체 데미지에서 감소시킴
			//damage -= 1.0d;
			
			Entity target = event.getEntity();
			Entity damager = event.getDamager();
						
			if(event.getCause().toString().equals("PROJECTILE"))
			{
				damager = (Entity) ((Projectile) damager).getShooter();
			}
			
			// 플레이어끼리의 전투일 경우
			if(damager instanceof Player && target instanceof Player)
			{
				if(Debug) Bukkit.broadcastMessage("-----------------------------");
				if(Debug) Bukkit.broadcastMessage("PVP이벤트가 넘겨받은 데미지: " +  damage);
				Player p = (Player) damager;
				Player target2 = (Player) target;				
				
				// 만약 공격자의 손에 활을 들고있다면 스탯 계산 취소
				if(p.getInventory().getItemInMainHand().getTypeId() == 261)
				{
					return;
				}
				
				double[] Damager_StatInfo= new double[100];
				double[] Target_StatInfo= new double[100];
				Damager_StatInfo[12] = 50.0d;
				Target_StatInfo[12] = 50.0d;
				
				
				// Damager의 활사격변수를 얻어옴
				ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(p);
				boolean isShoot = ap.isShoot();
				
				
				// 아래는 데미지 증가 코드-------------------------------------------------------
				
				Damager_StatInfo = pp.getstat(p, Damager_StatInfo);
				
				// 치명타 피해와 방어 무시 피해를 위한 치명타 확률 발동변수 선언
				double Critical_Percent = pp.getDamageFromStats(Damager_StatInfo, 9, 10, 11);
				boolean Critical = pp.CanPlayerActivateCriticalDamage(Critical_Percent);
				if(Debug) Bukkit.broadcastMessage("치명타가 발생하였는가?" + Critical);
				
				// 모든 피해에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Damager_StatInfo, 3, 4, 5);
				if(Debug) Bukkit.broadcastMessage("모든 피해에 의해 데미지 증가: " + temp_damage);
				damage += temp_damage;
				
				// 플레이어 추가 피해에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Damager_StatInfo, 18, 19, 20);
				if(Debug) Bukkit.broadcastMessage("플레이어 추가 피해에 의해 데미지 증가: " + temp_damage);
				damage += temp_damage;
				
				
				// 화살에 의한 공격일 경우 데미지의 증가폭이 활시위를 당긴 정도에 의해 결정됨.
				if(event.getCause().toString().equals("PROJECTILE"))
				{
					float bowForce = ap.getPlayerBowForce();
					damage =((double)bowForce* damage);
				}
				
				if(Debug) Bukkit.broadcastMessage("현재까지 증감된 후의 데미지: " + damage);
				
				// 특성에 의해 데미지 증가 및 방어무시피해 적용
				damage = pp.get_Player_Attribute_increse_damage(p, target, damage);
				if(Debug) Bukkit.broadcastMessage("특성에 의해 증가된 데미지: " + damage);				
				
				// 치명타 피해에 의해 데미지 증가
				if(Critical)
				{
					double per = pp.getDamageFromStats(Damager_StatInfo, 12, 13, 14);
					temp_damage = damage * (per/100);
					if(Debug) Bukkit.broadcastMessage("치명타 피해의 확률: " + per);
					if(Debug) Bukkit.broadcastMessage("치명타 피해에 의해 데미지 증가: " + temp_damage);
					damage += temp_damage;
				}
				
				// 아래는 데미지 감소 코드-------------------------------------------------------
				
				// getstat 메소드의 3번째 매개변수는 사격변수이고 연산이 제대로 되게 하기 위해서 true를 전달함.
				Target_StatInfo = pp.getstat(target2, Target_StatInfo);
				
				// 모든 피해 감소에 의해 데미지 감소
				temp_damage = damage * (pp.getDamageFromStats(Target_StatInfo, 24, 25, 26) / 100);
				if(Debug) Bukkit.broadcastMessage("모든 피해 감소에 의해 데미지 감소: " + temp_damage);
				damage -= temp_damage;
				
				// 모든 피해 무시에 의한 데미지 감소
				temp_damage = pp.getDamageFromStats(Target_StatInfo, 36, 37, 38);
				if(Debug) Bukkit.broadcastMessage("모든 피해 무시에 의해 데미지 감소: " + temp_damage);
				damage -= temp_damage;
				
				
				
				//화살 피해 무시에 의해 데미지 감소
				if(event.getCause().toString().equals("PROJECTILE"))
				{
					temp_damage = pp.getDamageFromStats(Target_StatInfo, 48, 49, 50);
					damage -= temp_damage;
				}
				
				if(Debug) Bukkit.broadcastMessage("현재까지의 데미지: " + damage);
				
				// 특성에 의해 데미지 감소
				damage = pp.get_Player_Attribute_decrese_damage(target2, damage);
				if(Debug) Bukkit.broadcastMessage("특성에 의해 변경된 데미지: " + damage);
				
				
				if(Debug) Bukkit.broadcastMessage("PVP 최종 데미지: " + temp_damage);
				
				//기타 -------------------------------------------------------------
				// 고정 피해에 의해 데미지를 입힘
				temp_damage = pp.getDamageFromStats(Damager_StatInfo, 72, 73, 74);
				target2.damage(temp_damage);
				if(Debug) Bukkit.broadcastMessage("고정 피해에 의해 데미지를 직접 입힘: " + (temp_damage));
				
				//방어 무시에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Damager_StatInfo, 6, 7, 8);
				if(Critical)
				{
					double temp2 = temp_damage * (pp.getDamageFromStats(Damager_StatInfo, 12, 13, 14)/100);
					double temp3 =  temp2 + temp_damage;
					if(damage < 0.0d)
					{
						temp3 += damage;
					}
					if(temp3 > 0.0d)
					{
						damage += temp3;
						if(Debug) Bukkit.broadcastMessage("치명 방어 무시 피해에 의해 데미지 증가: " + temp3);
					}
					
				}
				else
				{
					if(damage < 0.0d)
					{
						temp_damage += damage;
						
					}
					if(temp_damage > 0.0d)
					{
						damage += temp_damage;
						if(Debug) Bukkit.broadcastMessage("비치명 방어 무시 피해에 의해 데미지증가: " + temp_damage);
					}
				}
				
				// 만약 타겟이 방패를 들고 방어중이라면 1/3 데미지를 직접 줌 (본래 데미지는 0이 들어가야 하는걸 강제로 넣음). 화살일경우 무시.
				if(target2.isBlocking())
				{
					if(!event.getCause().toString().equals("PROJECTILE"))
					{
						target2.damage(damage/3);
					}
				}			
				
				// 흡혈 스텟에 의해 피를 올려줌
				this.BloodSuck(p, Damager_StatInfo, damage);
				
				
				//이벤트 마무리 ------------------------------------------------------
				
				//데미지가 0보다 작다면 이벤트 취소
				if(damage <= 0.0d)
				{
					temp_damage=0;
					event.setDamage(0.0d);
					event.setCancelled(true);
				}
				else
				{
					// damager의 스탯으로 인해 target에 포션이펙트를 추가
					addPotionEffectTotargetEntity(target, Damager_StatInfo);
					
					// 최종 데미지를 서버에 업로드
					temp_damage=0;
					event.setDamage(damage);
				}
				
			}
			// 플레이어가 몬스터를 가격할 경우
			else if(damager instanceof Player && (target instanceof Creature || target instanceof Slime))
			{
				if(Debug) Bukkit.broadcastMessage("-----------------------------");
				if(Debug) Bukkit.broadcastMessage("PVE이벤트가 넘겨받은 데미지: " +  damage);
				
				Player player = (Player)damager;
				
				// 만약 공격자의 손에 활을 들고있다면 스탯 계산 취소
				if(player.getInventory().getItemInMainHand().getTypeId() == 261)
				{
					return;
				}
				
				// 사격변수를 불러오기위해 ArenaPlayer인스턴스 생성
				ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(player);
				boolean isShoot = ap.isShoot();
				
				
				// 플레이어 스탯 정보를 얻어옴
				double[] Player_StatInfo = new double[100];
				Player_StatInfo[12] = 50.0d;
				Player_StatInfo = pp.getstat(player, Player_StatInfo);
				
				
				// 타겟의 스탯 정보를 얻어옴
				double[] Monster_StatInfo = new double[100];
				Monster_StatInfo[12] = 50.0d;
				Monster_StatInfo = pp.getstat((LivingEntity)target, Monster_StatInfo);
				

				// 아래는 데미지 증가 코드-------------------------------------------------------
				
				// 치명타 피해와 방어 무시 피해를 위한 치명타 확률 발동변수 선언
				double Critical_Percent = pp.getDamageFromStats(Player_StatInfo, 9, 10, 11);
				boolean Critical = pp.CanPlayerActivateCriticalDamage(Critical_Percent);
				
				// 모든 피해에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Player_StatInfo, 3, 4, 5);
				if(Debug) Bukkit.broadcastMessage("모든 피해 에 의해 데미지 증가: " +  temp_damage);
				damage += temp_damage;
				
				// 몬스터 추가 피해에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Player_StatInfo, 21, 22, 23);
				if(Debug) Bukkit.broadcastMessage("몬스터 추가 피해에 의해 데미지 증가: " +  temp_damage);
				damage += temp_damage;
				
				
				// 화살에 의한 공격일 경우 데미지의 증가폭이 활시위를 당긴 정도에 의해 결정됨.
				if(event.getCause().toString().equals("PROJECTILE"))
				{
					float bowForce = ap.getPlayerBowForce();
					damage =((double)bowForce* damage);
				}
				
				// 특성에 의해 데미지 증가 - 차후에 던전에 적용할 경우 주석 제거
				if(Debug) Bukkit.broadcastMessage("현재 까지 증감후 데미지: " +  damage);
				damage = pp.get_Player_Attribute_increse_damage((Player) damager, target, damage);
				if(Debug) Bukkit.broadcastMessage("특성에 의해 증가된 현재 데미지: " +  damage);
				
				// 치명타 피해에 의해 데미지 증가
				if(Critical)
				{
					//Bukkit.broadcastMessage("before_damage: " + damage);
					//Bukkit.broadcastMessage("Player_StatInfo[12]: " + Player_StatInfo[12]);
					//Bukkit.broadcastMessage("Player_StatInfo[13]: " + Player_StatInfo[13]);
					//Bukkit.broadcastMessage("Player_StatInfo[14]: " + Player_StatInfo[14]);
					double per = pp.getDamageFromStats(Player_StatInfo, 12, 13, 14);
					temp_damage = damage * (per/100);
					if(Debug) Bukkit.broadcastMessage("치명타 피해의 확률: " + per);
					if(Debug) Bukkit.broadcastMessage("치명타 피해에 의해 데미지 증가: " + temp_damage);
					damage += temp_damage;
					//Bukkit.broadcastMessage("After_damage: " + damage);
					
				}
				
				// 아래는 데미지 감소 코드-------------------------------------------------------
				
				// 모든 피해 감소에 의해 데미지 감소
				temp_damage = damage * (pp.getDamageFromStats(Monster_StatInfo, 24, 25, 26) / 100);
				if(Debug) Bukkit.broadcastMessage("모든 피해 감소에 의해 데미지 감소: " +  temp_damage);
				damage -= temp_damage;
				
				// 모든 피해 무시에 의해 데미지 감소
				temp_damage = pp.getDamageFromStats(Monster_StatInfo, 36, 37, 38);
				if(Debug) Bukkit.broadcastMessage("모든 피해 무시에 의해 데미지 감소: " +  temp_damage);
				damage -= temp_damage;				
				
				// 플레이어 피해 무시에 의해 데미지 감소
				temp_damage = pp.getDamageFromStats(Monster_StatInfo, 42, 43, 44);
				if(Debug) Bukkit.broadcastMessage("플레이어 피해 무시에 의해 데미지 감소: " + temp_damage);
				damage -= temp_damage;
				
				//화살 피해 무시에 의해 데미지 감소
				if(event.getCause().toString().equals("PROJECTILE"))
				{
					temp_damage = pp.getDamageFromStats(Monster_StatInfo, 48, 49, 50);
					if(Debug)  Bukkit.broadcastMessage("화살 피해 무시에 의해 데미지 감소: " + temp_damage);
					damage -= temp_damage;
					
				}
				
				//기타---------------------------------------------------------------------
				
				
				// 고정 피해에 의해 데미지를 입힘
				temp_damage = pp.getDamageFromStats(Player_StatInfo, 72, 73, 74);
				((LivingEntity)target).damage(temp_damage);
				if(Debug) Bukkit.broadcastMessage("고정 피해에 의해 데미지를 직접 입힘: " + (temp_damage));
				
				//방어 무시에 의해 데미지를 직접 입힘
				temp_damage = pp.getDamageFromStats(Player_StatInfo, 6, 7, 8);
				if(Critical)
				{
					double temp2 = temp_damage * (pp.getDamageFromStats(Player_StatInfo, 12, 13, 14)/100);
					double temp3 =  temp2 + temp_damage;
					if(damage < 0.0d)
					{
						temp3 += damage;
					}
					if(temp3 > 0.0d)
					{
						damage += temp3;
						if(Debug) Bukkit.broadcastMessage("치명 방어 무시 피해에 의해 데미지 증가: " + (temp3));
					}
				}
				else
				{
					if(damage < 0.0d)
					{
						temp_damage += damage;
					}
					if(temp_damage > 0.0d)
					{
						damage += temp_damage;
						if(Debug) Bukkit.broadcastMessage("비치명 방어 무시 피해에 의해 데미지증가: " + temp_damage);
					}
				}
						
				//아래는 흡혈 코드
				this.BloodSuck(player, Player_StatInfo, damage);
				
				//아래는 몬스터 흡혈 코드
				Monster_BloodSuck(player, Player_StatInfo, damage);
				
				// 이벤트 마무리 ---------------------------------------------------------------------
				
				//데미지가 0보다 작다면 이벤트 취소
				if(damage <= 0.0d)
				{
					event.setDamage(0.0d);
					event.setCancelled(true);
				}
				else
				{
					// damager의 스탯으로 인해 target에 포션이펙트를 추가
					addPotionEffectTotargetEntity(target, Player_StatInfo);
					
					temp_damage=0;
					// 최종 데미지를 서버에 업로드
					event.setDamage(damage);
				}
				if(Debug) Bukkit.broadcastMessage("PVE 최종 데미지: " +  damage);	
			}
			// 몬스터가 플레이어를 가격하는 경우
			else if((damager instanceof Creature || damager instanceof Slime) && target instanceof Player)
			{
				if(Debug) Bukkit.broadcastMessage("-----------------------------");
				if(Debug) Bukkit.broadcastMessage("EVP이벤트가 넘겨받은 데미지: " +  damage);
				 
				//가격하는 입장인 몬스터의 스탯을 구함
				double[] Monster_StatInfo = new double[100];
				Monster_StatInfo[12] = 50.0d;
				Monster_StatInfo = pp.getstat((LivingEntity)damager, Monster_StatInfo);
				
				
				//피격당하는 입장인 플레이어의 스탯을 구함
				Player player = (Player)target;
				
				/*
				ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(player);
				boolean isShoot = ap.isShoot();
				*/
				
				double[] Player_StatInfo = new double[100];
				Monster_StatInfo[12] = 50.0d;
				Player_StatInfo = pp.getstat(player, Player_StatInfo);
				
				
				// 아래는 데미지 증가 코드-------------------------------------------------------
						
				// 치명타 피해와 방어 무시 피해를 위한 치명타 확률 발동변수 선언
				double Critical_Percent = pp.getDamageFromStats(Monster_StatInfo, 9, 10, 11);
				boolean Critical = pp.CanPlayerActivateCriticalDamage(Critical_Percent);
				
				// 모든 피해에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Monster_StatInfo, 3, 4, 5);
				if(Debug) Bukkit.broadcastMessage("모든 피해에 의해 데미지 증가: " +  temp_damage);
				damage += temp_damage;
				
				// 플레이어 추가 피해에 의해 데미지 증가
				temp_damage = pp.getDamageFromStats(Monster_StatInfo, 18, 19, 20);
				if(Debug) Bukkit.broadcastMessage("플레이어 추가 피해에 의해 데미지 증가: " +  temp_damage);
				damage += temp_damage;
				
				// 치명타 피해에 의해 데미지 증가
				if(Critical)
				{
					double per = pp.getDamageFromStats(Monster_StatInfo, 12, 13, 14);
					temp_damage = damage * (per/100);
					if(Debug) Bukkit.broadcastMessage("치명타 피해의 확률: " + per);
					if(Debug) Bukkit.broadcastMessage("치명타 피해에 의해 데미지 증가: " +  temp_damage);
					damage += temp_damage;
				}
				
				// 아래는 데미지 감소 코드-------------------------------------------------------
				
				// 모든 피해 감소에 의해 데미지 감소
				temp_damage = damage * (pp.getDamageFromStats(Player_StatInfo, 24, 25, 26) / 100);
				if(Debug) Bukkit.broadcastMessage("모든 피해 감소에 의해 데미지 감소: " +  temp_damage);
				damage -= temp_damage;
				
				// 몬스터 피해 무시에 의해 데미지 감소
				temp_damage = pp.getDamageFromStats(Player_StatInfo, 45, 46, 47);
				if(Debug) Bukkit.broadcastMessage("몬스터 피해 무시에 의해 데미지 감소: " +  temp_damage);
				damage -= temp_damage;
				
				// 몬스터 피해 감소에 의해 데미지 감소
				temp_damage = pp.getDamageFromStats(Player_StatInfo, 69, 70, 71);
				if(Debug) Bukkit.broadcastMessage("몬스터 피해 감소에 의해 데미지 감소: " +  temp_damage);
				damage = damage - (damage * (temp_damage/100));
				
				// 화살 피해 무시에 의해 데미지 감소
				if(event.getCause().toString().equals("PROJECTILE"))
				{
					temp_damage = pp.getDamageFromStats(Player_StatInfo, 48, 49, 50);
					if(Debug) Bukkit.broadcastMessage("화살 피해 무시에 의해 데미지 감소: " +  temp_damage);
					damage -= temp_damage;
				}
				
				//기타 -----------------------------------------------------------------------

				// 고정 피해에 의해 데미지를 입힘
				temp_damage = pp.getDamageFromStats(Monster_StatInfo, 72, 73, 74);
				((LivingEntity)target).damage(temp_damage);
				if(Debug) Bukkit.broadcastMessage("고정 피해에 의해 데미지를 직접 입힘: " + (temp_damage));
				
				//방어 무시에 의해 데미지를 직접 입힘
				temp_damage = pp.getDamageFromStats(Monster_StatInfo, 6, 7, 8);
				if(Critical)
				{
					double temp2 = temp_damage * (pp.getDamageFromStats(Monster_StatInfo, 12, 13, 14)/100);
					double temp3 =  temp2 + temp_damage;
					if(damage < 0.0d)
					{
						temp3 += damage;
					}
					if(temp3 > 0.0d)
					{
						damage += temp3;
						if(Debug) Bukkit.broadcastMessage("치명 방어 무시 피해에 의해 데미지증가: " +  temp3);
					}
				}
				else
				{
					if(damage < 0.0d)
					{
						temp_damage += damage;
					}
					if(temp_damage > 0.0d)
					{
						damage += temp_damage;
						if(Debug) Bukkit.broadcastMessage("비치명 방어 무시 피해에 의해 데미지증가: " +  temp_damage);
					}	
				}
				
				// 만약 타겟이 방패를 들고 방어중이라면 1/3 데미지를 직접 줌 (본래 데미지는 0이 들어가야 하는걸 강제로 넣음). 화살일경우 무시.
				if(((Player) target).isBlocking())
				{
					if(!event.getCause().toString().equals("PROJECTILE"))
					{
						((LivingEntity) target).damage(damage/3);
					}
				}
				
				//아래는 흡혈 코드
				this.BloodSuck((LivingEntity)damager, Monster_StatInfo, damage);
				
				//이벤트 마무리 -----------------------------------------------------------------
				
				//데미지가 0보다 작다면 이벤트 취소
				if(damage <= 0.0d)
				{
					temp_damage=0;
					event.setDamage(0.0d);
					event.setCancelled(true);
				}
				else
				{
					// damager의 스탯으로 인해 target에 포션이펙트를 추가
					addPotionEffectTotargetEntity(target, Monster_StatInfo);
					
					temp_damage=0;
					//최종 데미지를 서버에 업로드
					event.setDamage(damage);
				}	
			}
			// 몬스터가 몬스터를 가격하는 경우
			else if((damager instanceof Creature || damager instanceof Slime) && (target instanceof Creature || target instanceof Slime))
			{	
				event.setCancelled(true);
			}
		}
		if(event.getDamage() < 0)
		{
			//데미지가 0보다 작다면 이벤트 취소
			event.setDamage(0.0d);
			event.setCancelled(true);
		}
		if(Debug) Bukkit.broadcastMessage("최종 데미지: " + event.getDamage());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockFade(BlockFadeEvent event)
	{
		int id = event.getBlock().getTypeId();
		if(id == 79)
		{
			event.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLeavesDecay(LeavesDecayEvent event)
	{
		event.setCancelled(true);
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPreCommand(PlayerCommandPreprocessEvent event)
	{
		Player p = event.getPlayer();
		String[] split = event.getMessage().split(" ");
		String CommandName = split[0].replace("/", "");
		List<String> CanNotUseInMatch = c.getStringList("config.CanNotUseInMatch");
		List<String> CanNotNominateInMatch = c.getStringList("config.CanNotNominateInMatch");
		if(!(UserArenaState.get(p.getName()).equals("Field") || (UserArenaState.get(p.getName()).equals(""))))
		{
			if(CanNotUseInMatch.contains(CommandName))
			{
				p.sendMessage("§b[마인아레나] §e전투중에는 사용할 수 없는 명령어 입니다.");
				event.setMessage("/arena sendmessage");
				event.setCancelled(true); 
			}
		}
		if(CanNotNominateInMatch.contains(CommandName))
		{
			if(split.length >= 2)
			{
				if(!IsinMatch.get(split[1]))
				{
					if(!p.hasPermission("ma.commands.use"))
					{
						p.sendMessage("§b[마인아레나] §e전투중인 대상에게 사용할 수 없는 명령어 입니다.");
						event.setCancelled(true);
					}
				}
			}
		}
	}
	@EventHandler
	public void onPickupItem(PlayerPickupItemEvent event)
	{
		Player p = event.getPlayer();
		ItemStack target = event.getItem().getItemStack();
		int itemid = target.getTypeId();
		if(itemid == 262)
		{
			if(!target.hasItemMeta())
			{
				if(!p.isOp())
				{
					event.setCancelled(true);
				}
				
			}
		}
		
	}
	@EventHandler
	public void onPickupArrow(PlayerPickupArrowEvent event)
	{
		event.setCancelled(true);
		
	}
	@EventHandler
	public void onThrowItem(PlayerDropItemEvent event)
	{
		Player p = event.getPlayer();
		ArenaPlayer ap = ArenaPlayerManager.getArtenaPlayer(p);
		
		//버리기에 동의하지 않은 상태라면 이벤트 취소
		if(ap.getCanDrop())
		{
			//버리기에 동의했더라도 게임 중이라면 이벤트 취소
			if(IsinMatch.get(p.getName()))
			{
				event.setCancelled(true);
			}
		}
		else
		{
			event.setCancelled(true);
			p.sendMessage("§b[마인아레나] §e아이템을 버리는것에 동의한다면 §3'/버리기 동의'§e를 입력해주세요.");
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		String[] Locations = c.getString("config.ArcadeJumpLocation").split(":");
		String pos1 = Locations[0];
		String pos2 = Locations[1];
		if(this.IsWithin(event.getBlock().getLocation(), pos1, pos2))
		{
			int id = event.getBlock().getTypeId();
			List<String> items = c.getStringList("config.JumpBlock");
			if(!items.contains(id+""))
			{
				Bukkit.dispatchCommand(p, "c spawn ");
			}
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player p = event.getPlayer();
		String ColorCode = "§" + UserChatColor.get(p.getName());
		event.setMessage(ColorCode + event.getMessage());
		
		String ChatState = UserChatState.get(p.getName());
		switch(ChatState)
		{
		case "party":
			event.setCancelled(true);
			String partyname = userscore.getString("userscore." + p.getName() + ".party");
			List<String> members = party.getStringList("partys." + partyname + ".members");
			System.out.println("§9[파티][§f" + p.getName() +  "§9]{" + event.getMessage()+"§9}");
			for(String member : members)
			{
				Player mem = Bukkit.getPlayer(member);
				if(mem.isOnline())
				{
					mem.sendMessage("§a<§f" + p.getName() +  "§a>" + event.getMessage());
					
				}
			}
		break;
		case "guild":
		break;
		}
	}
	// 플레이어가 다른 월드로 이동할때 이벤트 발생
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerWorldChange(PlayerChangedWorldEvent event)
	{
		// 이벤트를 발생시킨 플레이어를 얻어옴
		Player p = event.getPlayer();
		p.setGlowing(false);
		if(p.getWorld().getName().equalsIgnoreCase("parkour"))
		{
			if(EntityHasEquip(p, 443))
			{
				p.sendMessage("§b[마인아레나] §e엘트라를 입고 다른 월드로 이동할 수 없습니다. 스폰 지점으로 이동합니다.");
				Bukkit.dispatchCommand(p, "c spawn ");
			}
		}
	}
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent event)
	{
		Player p = (Player) event.getPlayer();
		String WorldName = p.getWorld().getName();
		if(WorldName.equalsIgnoreCase("parkour"))
		{
			if(EntityHasEquip(p, 443))
			{
				p.sendMessage("§b[마인아레나] §e파쿠르 월드에서 겉날개를 사용할 수 없습니다. 스폰 지점으로 이동합니다.");
				Bukkit.dispatchCommand(p, "c spawn ");
			}
		}
	}
	@EventHandler
	public void onPlayerMouseClick(PlayerInteractEvent event)
	{
		Player p = (Player) event.getPlayer();
		String WorldName = p.getWorld().getName();
		//Bukkit.broadcastMessage(p.getItemInHand().getType().toString() +": arena match - interact Event");
		if(WorldName.equalsIgnoreCase("parkour"))
		{
			if((p.getItemInHand().getType().toString().equals("ELYTRA")) && 
					 (event.getAction().equals(event.getAction().RIGHT_CLICK_AIR) || event.getAction().equals(event.getAction().RIGHT_CLICK_BLOCK) )
					 )
			{
				p.sendMessage("§b[마인아레나] §e파쿠르 월드에서 겉날개를 사용할 수 없습니다. 스폰 지점으로 이동합니다.");
				Bukkit.dispatchCommand(p, "c spawn ");	
			}
			
			
		}
	}
	public static List<String> sortByValue(final Map<String,Integer> map) 
	{
        List<String> list = new ArrayList<String>();
        list.addAll(map.keySet());
         
        Collections.sort(list,new Comparator() 
        {
            public int compare(Object o1,Object o2)
            {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v2).compareTo(v1);
            }
             
        });
        //Collections.reverse(list); // 주석시 오름차순
        return list;
    }
	public void MatchUp(Player p, HashMap<Integer, List<String>> Arena, boolean Rematchup)
	{
		PreviousStat.put(p.getName(), "MATCHUP");
		if((!IsinMatch.get(p.getName()) || (IsinMatch.get(p.getName()) && UserArenaState.get(p.getName()).equals("Field"))) && !IsinList.get(p.getName()))
		{
			int PlayerScore = (userscore.getInt("userscore." + p.getName() + ".Score"));  //0 300 600 (900) 1000 0 1 2 3
			

			List<String> ArenaUserBuffer =  new ArrayList<String>();
			
			if(UserArenaState.get(p.getName()).equals("Compitition"))
			{
				PlayerScore = this.getPlayerRankQueueNumber(PlayerScore);
				if(Arena.get(PlayerScore).isEmpty())
				{
					ArenaUserBuffer.add(p.getName());
					IsinList.put(p.getName(), true);
				}
				else
				{
					ArenaUserBuffer = Arena.get(PlayerScore);
					if(!ArenaUserBuffer.contains(p.getName()))
					{
						ArenaUserBuffer.add(p.getName());
						IsinList.put(p.getName(), true);
					}
				}
				Arena.put(PlayerScore, ArenaUserBuffer);
			}
			else if(UserArenaState.get(p.getName()).equals("Default"))
			{
				
				int QueNumber = getPlayerDefaultQueueNumber(PlayerScore);
				
				if(Arena.get(QueNumber).isEmpty())
				{
					ArenaUserBuffer.add(p.getName());
					IsinList.put(p.getName(), true);
					
				}
				else
				{
					ArenaUserBuffer = Arena.get(QueNumber);
					if(!ArenaUserBuffer.contains(p.getName()))
					{
						ArenaUserBuffer.add(p.getName());
						IsinList.put(p.getName(), true);
					}
				}
				Arena.put(QueNumber, ArenaUserBuffer);
			}
			else if(UserArenaState.get(p.getName()).equals("FriendShip"))
			{
				if(Arena.get(-3).isEmpty())
				{
					ArenaUserBuffer.add(p.getName());
					IsinList.put(p.getName(), true);
					
				}
				else
				{
					ArenaUserBuffer = Arena.get(-3);
					ArenaUserBuffer.add(p.getName());
					IsinList.put(p.getName(), true);
				}
				Arena.put(-3, ArenaUserBuffer);
			}
			
			if(ArenaUserBuffer.size() == 2)
			{
				
				Player p1 = Bukkit.getPlayer(ArenaUserBuffer.get(0));
				Player p2 = Bukkit.getPlayer(ArenaUserBuffer.get(1));
				if(p1.isOnline() && p2.isOnline())
				{
					if((UserArenaState.get(p1.getName()).equals("FriendShip") && UserArenaState.get(p2.getName()).equals("FriendShip")) || (!(PreviousPlayer.get(p1.getName()).equals(p2.getName())) && !(PreviousPlayer.get(p2.getName()).equals(p1.getName()))))
					{
						
						boolean Destination = getSubMap(p1, p2, Arena);
						if(Destination)
						{
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + p1.getName());
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + p2.getName());
							
							Bukkit.dispatchCommand(p1, "mobkill wolf 65 ");
							Bukkit.dispatchCommand(p2, "mobkill wolf 65 ");
							
							RemovePlayerInList(p2.getName());
							RemovePlayerInList(p1.getName());
							
							match.put(p1.getName(), p2.getName());
							match.put(p2.getName(), p1.getName());
							PreviousPlayer.put(p1.getName(), p2.getName());
							PreviousPlayer.put(p2.getName(), p1.getName());
							IsinMatch.put(p1.getName(), true);
							IsinMatch.put(p2.getName(), true);
							p1.setHealth(p1.getMaxHealth());
							p2.setHealth(p2.getMaxHealth());
							SetMatchTimer(p1);
							p1.setGlowing(true);
							p2.setGlowing(true);
							p1.sendMessage("§b[마인아레나] §e전투가 시작되었습니다. 상대를 처치하거나 죽으면 전투가 종료됩니다.");
							p2.sendMessage("§b[마인아레나] §e전투가 시작되었습니다. 상대를 처치하거나 죽으면 전투가 종료됩니다.");
							
							//p1.openInventory(pp.CreateProfile(p2));
							//p2.openInventory(pp.CreateProfile(p1));
						}
						else
						{
							RemovePlayerInList(p2.getName());
							RemovePlayerInList(p1.getName());
							p1.sendMessage("&b[마인아레나] &e현재 사용 가능한 아레나가 없습니다. 잠시 후에 다시 시도해 주세요.");
							p2.sendMessage("&b[마인아레나] &e현재 사용 가능한 아레나가 없습니다. 잠시 후에 다시 시도해 주세요.");
							IsinMatch.put(p1.getName(), false);
							IsinMatch.put(p2.getName(), false);
							IsinList.put(p1.getName(), false);
							IsinList.put(p2.getName(), false);
							
							UserArenaState.put(p1.getName(), "");
							UserArenaState.put(p2.getName(), "");
							match.put(p1.getName(), "");
							match.put(p2.getName(), "");
							this.GUIAnimation(p, 0);
						}
						
					}
					else
					{
						RemovePlayerInList(p2.getName());
						RemovePlayerInList(p1.getName());
						IsinMatch.put(p1.getName(), false);
						IsinMatch.put(p2.getName(), false);
						IsinList.put(p1.getName(), false);
						IsinList.put(p2.getName(), false);
						this.MatchUp(p1, this.getArenaMapUseingRandomNumber(), true);
						this.MatchUp(p2, this.getArenaMapUseingRandomNumber(), true);
					}					
				}
				else
				{
					String p1name = ArenaUserBuffer.get(0);
					String p2name = ArenaUserBuffer.get(1);
					IsinMatch.put(p1name, false);
					IsinMatch.put(p2name, false);
					IsinList.put(p1name, false);
					IsinList.put(p2name, false);
					RemovePlayerInList(p1name);
					RemovePlayerInList(p2name);
					if(p1.isOnline())
					{
						p1.sendMessage("&b[마인아레나] &e 매칭 상대가 접속중이지 않으므로 전투를 중지합니다.");
					}
					else
					{
						this.InitializeUserValues(p1name);
					}
					if(p2.isOnline())
					{
						p2.sendMessage("&b[마인아레나] &e 매칭 상대가 접속중이지 않으므로 전투를 중지합니다.");
					}
					else
					{
						this.InitializeUserValues(p2name);
					}					
				}
			}
			else
			{
				if(!Rematchup && UserArenaState.get(p.getName()).equals("Default"))
				{
					p.sendMessage("§b[마인아레나] §e상대가 없습니다. 상대를 계속 찾습니다.");
				}
				else if(!Rematchup && UserArenaState.get(p.getName()).equals("Compitition"))
				{
					p.sendMessage("§b[마인아레나] §e현재 비슷한 RP를 가진 상대가 없습니다. 상대를 계속 찾습니다.");
				}
				
			}
		}
		else
		{
			p.sendMessage("§b[마인아레나] §e이미 등록되었습니다.");
			MatchDown(p);
		}
		
	}
	public void MatchDown(Player loser)
	{
		Player winner = (Player) Bukkit.getOfflinePlayer(match.get(loser.getName()));
		PreviousStat.put(loser.getName(), "MATCHDOWN");
		PreviousStat.put(winner.getName(), "MATCHDOWN");
		//this.SetMatchWaitingTimer(winner);
		
		usingmap.remove(usemap.get(loser.getName()));
		EventListener.ToolRepair(winner);
		EventListener.ToolRepair(loser);
		IsinMatch.put(winner.getName(), false);
		IsinMatch.put(loser.getName(), false);
		IsinList.put(winner.getName(), false);
		IsinList.put(loser.getName(), false);
		winner.setGlowing(false);
		UserArenaState.put(winner.getName(), "");
		UserArenaState.put(loser.getName(), "");
		
		PersonalGUI.put(winner.getName(), CreateGUI(winner));
		PersonalGUI.put(loser.getName(), CreateGUI(loser));
		disableMusic(winner);
		//winner.playSound(winner.getLocation(), Sound.RECORD_CHIRP, SoundCategory.RECORDS, 100000, 1);
		modifyScoreBoard(winner);
		match.put(winner.getName(), "@");
		match.put(loser.getName(), "@");
		this.KillTimer(loser, winner);
		
		/*
		//petblock 플러그인의 기능을 enable 시킴
		Bukkit.dispatchCommand(loser, "petblocks enable " + loser.getName());
		Bukkit.dispatchCommand(winner, "petblocks enable " + winner.getName());
		*/
		
	}
	public int getPlayerRankQueueNumber(int PlayerScore)
	{
		/*
		int QueueNumber = 0;
		int EstimateScore = PlayerScore/100;
		String ScoreString = String.valueOf(EstimateScore);
		
		Set<String> keys = tier.getConfigurationSection("tier.rank_tier").getKeys(false);
		for(String key : keys)
		{
			List<String> range = tier.getStringList("tier.rank_tier." + key);
			if(key.equalsIgnoreCase("range_out"))
			{
				break;
			}
			else if(range.contains(ScoreString))
			{
				break;
			}
			else
			{
				QueueNumber += 1;
				continue;
			}
		}
		
		return QueueNumber;
		
		*/
		
		return 1;
	}
	public int getPlayerDefaultQueueNumber(int PlayerScore)
	{
		/*
		int QueueNumber = 0;
		int EstimateScore = PlayerScore/100;
		
		String ScoreString = String.valueOf(EstimateScore);
		List<String> range = tier.getStringList("tier.default_tier.tier1");
		if(range.contains(ScoreString))
		{
			QueueNumber = -1;
		}
		else
		{
			QueueNumber = -2;
		}
		return QueueNumber;
		*/
		return 0;
	}
	public void MovePlayer(Player p1, Player p2, Location Destination)
	{
		p1.teleport(Destination);
		p2.teleport(Destination);
	}
	public boolean isThisPlayerOnline(String name)
	{
		boolean b = false;
		for(Player p : Bukkit.getOnlinePlayers())
		{
			String Name = p.getName();
			if(Name.equals(name))
			{
				b = true;
				break;
			}
		}
		return b;
	}
	public boolean EntityHasEquip(LivingEntity Entity, int Typeid)
	{
		boolean b = false;
		
		//ItemStack RightHand = Entity.getEquipment().getItemInHand();
		//ItemStack LeftHand = Entity.getEquipment().getItemInOffHand();
		ItemStack Helmet = Entity.getEquipment().getHelmet();
		ItemStack Chest = Entity.getEquipment().getChestplate();
		ItemStack Leggings = Entity.getEquipment().getLeggings();
		ItemStack Boots = Entity.getEquipment().getBoots();
		
		/*
		if(!(RightHand.getTypeId() == Typeid))
		{
			b = true;
		}
		if(!(LeftHand.getTypeId() == Typeid))
		{
			b = true;
		}
		*/
		if(Helmet != null && Helmet.getTypeId() == Typeid)
		{
			b = true;
		}
		if(Chest != null && Chest.getTypeId() == Typeid)
		{
			b = true;
		}
		if(Leggings != null && Leggings.getTypeId() == Typeid)
		{
			b = true;
		}
		if(Boots != null && Boots.getTypeId() == Typeid)
		{
			b = true;
		}
		
		return b;
	}
	public void addPotionEffectTotargetEntity(Entity target, double[] StatsInfo)
	{
		if(Debug) Bukkit.broadcastMessage("------------------------------------");
		// 포션이펙트를 target에게 걸기위한 메소드
		
		// 포션 이펙트  index
		/*
		 * -> 상태이상
		 * 			
		 * 화상			51 , 52 - 53
		 * 빙결			54 , 55 - 56 -> 구속
		 * 중독			57 , 58 - 59
		 * 위더	 		60 , 61 - 62
		 * 실명 			63 , 64 - 65
		 * 영혼 약탈 		66 , 67 - 68 -> 즉시 데미지
		 * 
		 * 초열			78 , 79 - 80
		 * 끈적끈적		81 , 82 - 83
		 * 혈독			84 , 85 - 86
		 */
		
		// 화상 스탯을 가져옴
		double burn = pp.getDamageFromStats(StatsInfo, 51, 52, 53);
		if(Debug) Bukkit.broadcastMessage("화상 확률: " + burn);
		
		// 빙결 스탯을 가져옴
		double ice = pp.getDamageFromStats(StatsInfo, 54, 55, 56);
		if(Debug) Bukkit.broadcastMessage("빙결 확률: " + ice);
		
		// 중독 스탯을 가져옴
		double poison = pp.getDamageFromStats(StatsInfo, 57, 58, 59);
		if(Debug) Bukkit.broadcastMessage("중독 확률: " + poison);
		
		// 위더 스탯을 가져옴
		double wither = pp.getDamageFromStats(StatsInfo, 60, 61, 62);
		if(Debug) Bukkit.broadcastMessage("위더 확률: " + wither);
		
		// 실명 스탯을 가져옴
		double blind = pp.getDamageFromStats(StatsInfo, 63, 64, 65);
		if(Debug) Bukkit.broadcastMessage("실명 확률: " + blind);
		
		// 영혼 약탈 스탯을 가져옴
		double soul = pp.getDamageFromStats(StatsInfo, 66, 67, 68);
		if(Debug) Bukkit.broadcastMessage("영혼 약탈 확률: " + soul);
		
		// 초열 스탯을 가져옴
		double Ex_burn = pp.getDamageFromStats(StatsInfo, 78, 79, 80);
		if(Debug) Bukkit.broadcastMessage("화상 확률: " + Ex_burn);
		
		// 끈적끈적 스탯을 가져옴
		double Ex_ice = pp.getDamageFromStats(StatsInfo, 81, 82, 83);
		if(Debug) Bukkit.broadcastMessage("빙결 확률: " + Ex_ice);
		
		// 혈독 스탯을 가져옴
		double Ex_poison = pp.getDamageFromStats(StatsInfo, 84, 85, 86);
		if(Debug) Bukkit.broadcastMessage("중독 확률: " + Ex_poison);
		
		// 화상을 엔티티에 적용함
		SetFiretoEntity(target, burn, "화상");
		
		// 빙결(구속) 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, ice, "빙결");
		
		//중독 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, poison, "중독");
		
		// 화상을 엔티티에 적용함
		SetFiretoEntity(target, burn, "초열");
		
		// 끈적끈적(구속) 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, ice, "끈적끈적");
		
		//혈독 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, poison, "혈독");
		
		// 위더 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, wither, "위더");
		
		// 실명 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, blind, "실명");
		
		// 영혼 약탈 이펙트를 엔티티에게 부여함
		SetPotionEffect(target, soul, "영혼 약탈");
		
		
	}
	public void SetFiretoEntity(Entity target, double stat_value, String EffectName)
	{
		if(Debug) Bukkit.broadcastMessage(EffectName + "메소드 진입");
		// 만약 화상 스탯이 0보다 클 경우
		if(stat_value > 0)
		{
			// 작동할지 안할지를 결정할 랜덤 변수 생성
			double random_value = LibMain.getRangeRandomDouble(0.0d, 100.0d);
			if(Debug) Bukkit.broadcastMessage("랜덤값 생성: " + random_value);
			
			// 만약 작동한다면
			if(random_value <= stat_value)
			{
				// 화상 지속 시간을 얻어옴
				int ticks = Effect.getInt("Effect." + EffectName + ".time") * 20;
				if(Debug) Bukkit.broadcastMessage(EffectName + "지속 시간: " + (ticks/20));
				
				// 엔티티에 불을 지속시간만큼 불을 붙임
				((LivingEntity) target).setFireTicks(ticks);
			}
		}
	}
	public void SetPotionEffect(Entity target, double stat_value, String EffectName)
	{
		if(Debug) Bukkit.broadcastMessage(EffectName + "메소드 진입");
		// 만약 스탯이 0보다 크다면
		if(stat_value > 0)
		{
			// 작동할지 안할지를 결정할 랜덤 변수 생성
			double random_value = LibMain.getRangeRandomDouble(0.0d, 100.0d);
			if(Debug) Bukkit.broadcastMessage("랜덤값 생성: " + random_value);
			
			// 만약 작동한다면
			if(random_value <= stat_value)
			{
				// 이펙트 이름으로부터 포션 이펙트 타입을 얻어옴
				PotionEffectType EffectType = getPotionEffectTypeFromString(EffectName);
				
				// 만약 등록된 이펙트가 맞다면
				if(!EffectType.equals(PotionEffectType.SPEED))
				{
					// 포션 이펙트 지속시간을 얻어옴
					int ticks = Effect.getInt("Effect." + EffectName + ".time") * 20;
					if(Debug) Bukkit.broadcastMessage(EffectName + "지속 시간: " + (ticks/20));
					
					// 포션 이펙트 레벨을 얻어옴
					int level = Effect.getInt("Effect." + EffectName + ".level") - 1;
					if(Debug) Bukkit.broadcastMessage(EffectName + "레벨 : " + (level+1));
					
					// 대상 엔티티에게 이펙트를 추가함.
					((LivingEntity) target).addPotionEffect(new PotionEffect(EffectType, ticks, level));
				}
			}
		}
	}
	public PotionEffectType getPotionEffectTypeFromString(String EffectName)
	{
		switch(EffectName)
		{
			/*
			 * -> 상태이상
			 * 			
			 * 화상			51 , 52 - 53
			 * 빙결			54 , 55 - 56 -> 구속
			 * 중독			57 , 58 - 59
			 * 위더	 		60 , 61 - 62
			 * 실명 			63 , 64 - 65
			 * 영혼 약탈 		66 , 67 - 68 -> 즉시 데미지
			 */
			case "빙결":
				// 구속
				return PotionEffectType.SLOW;
				
			case "중독":
				return PotionEffectType.POISON;

			case "위더":
				return PotionEffectType.WITHER;

			case "실명":
				return PotionEffectType.BLINDNESS;
				
			case "영혼 약탈":
				// 즉시 데미지
				return PotionEffectType.HARM;
		}	
		return PotionEffectType.SPEED;
	}
	public void IncreaseScore(String string, int multiplier)
	{
		Player winner = Bukkit.getPlayer(string);
		int score = userscore.getInt("userscore." + string + ".Score");
		int RankWinCount = userscore.getInt("userscore." + string + ".WinCount");
		
		List<String> scores = c.getStringList("config.InCreaseScore");
		String Increase_score = scores.get(ran.nextInt(scores.size()));
		score += (Integer.parseInt(Increase_score) * multiplier);
		userscore.set("userscore." + string+ ".Score", score);
		winner.sendMessage("§b[마인아레나] §e랭크 아레나에서 승리하여 랭크 포인트가 §a" + (Integer.parseInt(Increase_score) * multiplier) + "§3RP §e올랐습니다.");
		userscore.set("userscore." + string+ ".WinCount", RankWinCount + 1);
		GiveReward(winner);
		
		GiveTitle(RankWinCount+1, string, "Compitition");
		
		this.modifyScoreBoard(winner);
	}

	public void DecreaseScore(Player p, int multiplier)
	{

		int score = userscore.getInt("userscore." + p.getName() + ".Score");
		int Decrease_score = c.getInt("config.DecreateScore");
		score -= Decrease_score * multiplier;
		
		if(!(score < 300))
		{
			userscore.set("userscore." + p.getName()+ ".Score", score);
			p.sendMessage("§b[마인아레나] §e랭크 포인트가 §c" + (Decrease_score * multiplier) + "§3RP §e내려갔습니다.");
			modifyScoreBoard(p);
		}		
	}
	public void DefaultIncreaseScore(String string)
	{
		Player winner = Bukkit.getPlayer(string);
		int DefaultWinCount = userscore.getInt("userscore." + string + ".DeWinCount");
		userscore.set("userscore." + string+ ".DeWinCount", DefaultWinCount + 1);
		winner.sendMessage("§b[마인아레나] §e전투에서 승리하였습니다.");
		this.GiveTitle(DefaultWinCount, string, "Default");
		modifyScoreBoard(winner);
	}
	public void DefaultDecreaseScore()
	{
		
	}
	public void FieldIncreaseScore(String string)
	{
		Player winner = Bukkit.getPlayer(string);
		int FieldKillCount = userscore.getInt("userscore." + string + ".FieldKillCount");
		userscore.set("userscore." + string+ ".FieldKillCount", FieldKillCount + 1);
		this.GiveTitle(FieldKillCount, string, "Field");
		modifyScoreBoard(winner);
	}
	public void GiveReward(Player winner)
	{
		Player loser = Bukkit.getPlayer(match.get(winner.getName()));
		if(UserArenaState.get(winner.getName()).equals("Compitition"))
		{
										// 티어를 없엠으로 인해서 삭제 int WinnerScore = userscore.getInt("userscore." + winner.getName() + ".Score");
			int WinnerMultiplier = 1; 	// 티어를 없엠으로 인해서 삭제 getPlayerRankQueueNumber(WinnerScore)+1;
			
			int WinnerCoin = 30 * WinnerMultiplier;
			
										// 티어를 없엠으로 인해서 삭제 int LoserScore = userscore.getInt("userscore." + loser.getName() + ".Score");
			int LoserMultiplier =  1; 	// 티어를 없엠으로 인해서 삭제 getPlayerRankQueueNumber(LoserScore)+1;
			int LoserCoin = 10 * LoserMultiplier;
			
			try {
				vault.add(winner.getName(), WinnerCoin);
			} catch (NoLoanPermittedException e) {
				e.printStackTrace();
			} catch (UserDoesNotExistException e) {
				e.printStackTrace();
			}
			try {
				vault.add(loser.getName(), LoserCoin);
			} catch (NoLoanPermittedException e) {
				e.printStackTrace();
			} catch (UserDoesNotExistException e) {
				e.printStackTrace();
			}
			
			List<String> Winner_items = submap.getStringList("submap." + UseArena.get(winner.getName()) + ".Rewards");
			List<String> Loser_items = submap.getStringList("submap." + UseArena.get(winner.getName()) + ".Loser_Rewards");
			
			for(String item : Winner_items)
			{
				main.give(winner, item, 1);
			}
			
			for(String item : Loser_items)
			{
				main.give(loser, item, 1);
			}
			
			
			
			winner.sendMessage("§b[마인아레나] §e전투에서 승리하여 복권 §3" + WinnerMultiplier + "§e개와 §3" + WinnerCoin +"§e골드를 획득하였습니다.");
			loser.sendMessage("§b[마인아레나] §e전투에서 패배하여 §3" + LoserCoin + "§e골드를 획득하였습니다.");
		}
		else if(UserArenaState.get(winner.getName()).equals("Default"))
		{
			
			List<String> items = submap.getStringList("submap." + UseArena.get(winner.getName()) + ".Default_Rewards");
			
			for(String item : items)
			{
				main.give(winner, item, 1);
			}

			try {vault.add(winner.getName(), 30);} catch (NoLoanPermittedException e) {e.printStackTrace();} catch (UserDoesNotExistException e) {e.printStackTrace();}
			try {vault.add(loser.getName(), 10);} catch (NoLoanPermittedException e) {e.printStackTrace();} catch (UserDoesNotExistException e) {e.printStackTrace();}
		}
	}
	public void GiveTitle(int score, String PlayerName, String ArenaType)
	{
		String configString = "title." + ArenaType;
		Set<String> TitleScores = title.getConfigurationSection(configString).getKeys(false);
		String ScoreString = score+"";
		if(TitleScores.contains(ScoreString))
		{
			for(String TitleScore : TitleScores)
			{
				if(TitleScore.equals(ScoreString))
				{
					List<String> Titles = title.getStringList(configString + "." + ScoreString);
					for(String Title : Titles)
					{
						MCMANYtitle.AddTitle(Title, PlayerName, "Moon_Eclipse", false);
					}
				}
			}
		}
	}
	public void RemovePlayerInList(String name)
	{
		int score = (userscore.getInt("userscore." + name + ".Score"));
		int RankQueueNumber = getPlayerRankQueueNumber(score);
		int DefaultQueueNumber = getPlayerDefaultQueueNumber(score);
		기본.get(RankQueueNumber).remove(name);
		정글.get(RankQueueNumber).remove(name);
		지옥.get(RankQueueNumber).remove(name);
		중세.get(RankQueueNumber).remove(name);
		설산.get(RankQueueNumber).remove(name);
		묘지.get(RankQueueNumber).remove(name);
		천공섬.get(RankQueueNumber).remove(name);
		기본.get(DefaultQueueNumber).remove(name);
		정글.get(DefaultQueueNumber).remove(name);
		지옥.get(DefaultQueueNumber).remove(name);
		중세.get(DefaultQueueNumber).remove(name);
		설산.get(DefaultQueueNumber).remove(name);
		묘지.get(DefaultQueueNumber).remove(name);
		천공섬.get(DefaultQueueNumber).remove(name);
		기본.get(-3).remove(name);
		정글.get(-3).remove(name);
		지옥.get(-3).remove(name);
		중세.get(-3).remove(name);
		설산.get(-3).remove(name);
		묘지.get(-3).remove(name);
		천공섬.get(-3).remove(name);
		IsinList.put(name, false);
	}
	public void SetMatchTimer(Player p1)
	{
		int second = c.getInt("config.GameTime");
		TimerValueMain.put(p1.getName(), second);
		Player p2 =Bukkit.getPlayer(match.get(p1.getName()));
		BukkitTask task = new BukkitRunnable() 
		{
            @Override
            public void run() 
            {
            	TimerValueMain.put(p1.getName(), (TimerValueMain.get(p1.getName())-1));
            	StateBoard.get(p1.getName()).getObjective(DisplaySlot.SIDEBAR).getScore("남은 전투 시간").setScore(TimerValueMain.get(p1.getName()));
            	p1.setScoreboard(StateBoard.get(p1.getName()));
            	StateBoard.get(p2.getName()).getObjective(DisplaySlot.SIDEBAR).getScore("남은 전투 시간").setScore(TimerValueMain.get(p1.getName()));
            	p1.setScoreboard(StateBoard.get(p1.getName()));
            	
            	if(IsinMatch.get(p1.getName()))
            	{
            		if(TimerValueMain.get(p1.getName()) == 10)
            		{
            			p1.sendMessage("§b[마인아레나] §e전투가 10초 남았습니다.");
            			p2.sendMessage("§b[마인아레나] §e전투가 10초 남았습니다.");
            		}
            		if(TimerValueMain.get(p1.getName()) <= 5 && TimerValueMain.get(p1.getName()) != 0)
            		{
            			p1.sendMessage("§b[마인아레나] §e전투가" + TimerValueMain.get(p1.getName()) + "초 남았습니다." );
            			p2.sendMessage("§b[마인아레나] §e전투가" + TimerValueMain.get(p1.getName()) + "초 남았습니다." );
            		}
            		if(TimerValueMain.get(p1.getName()) == 0)
                	{
            			ClearScoreBoard(p1);
            			ClearScoreBoard(p2);
                		MatchDown(p1);
                		
        				match.put(p1.getName(), "@");
        				IsinMatch.put(p1.getName(), false);
        				IsinList.put(p1.getName(), false);
                		this.cancel();
                		p1.setGlowing(false);
                		p2.setGlowing(false);
                		p1.sendMessage("§b[마인아레나] §e제한시간이 초과되어 전투가 종료되었습니다.");
                		p2.sendMessage("§b[마인아레나] §e제한시간이 초과되어 전투가 종료되었습니다.");
                	}
            	}
            	else
            	{
            		this.cancel();
            		ClearScoreBoard(p2);
            		ClearScoreBoard(p1);
            	}
            }
        }.runTaskTimer(this, 0, 20);
	
	}
	public void SetMatchWaitingTimer(Player p)
	{
		int second = (ran.nextInt(10))+5;
		TimerValue.put(p.getName(), second);
		p.sendMessage(second + " 초 뒤에 대기열에 재참가 합니다.");
		BukkitTask task = new BukkitRunnable() 
		{
            @Override
            public void run() 
            {
            	if(!REjectList.get(p.getName()))
            	{
                	
                	TimerValue.put(p.getName(), (TimerValue.get(p.getName())-1));
                	if(TimerValue.get(p.getName()) <= 0)
                	{
                		MatchUp(p, getArenaMap(p),true);
                		this.cancel();
                	}
            	}
            	else
            	{
            		this.cancel();
            		REjectList.put(p.getName(), false);
            	}
            	
            }
        }.runTaskTimer(this, 0, 20);
	}
	public void MainTimer()
	{
		BukkitTask task = new BukkitRunnable() 
		{
            @Override
            public void run() 
            {
            	saveConfig();
            	saveProfileFile();
            	saveUserScore();
            	saveSubmap();
            	System.out.println("MineArena ArenaMatch 플러그인 자동저장.");
            }
        }.runTaskTimer(this, 0, 12000);
	
	}
	/*
	public void RespawnTimer(Player p)
	{
		BukkitTask task = new BukkitRunnable() 
		{
            @Override
            public void run() 
            {
            	p.playSound(p.getLocation(), Sound.RECORD_CHIRP, SoundCategory.RECORDS, 100000, 1);
            }
        }.runTaskLater(this, 20);
	
	}
	*/
	public void KillTimer(Player loser, Player winner)
	{
		BukkitTask task = new BukkitRunnable() 
		{
            @Override
            public void run() 
            {
        		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + winner.getName());
        		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + winner.getName());
        		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + loser.getName());
            }
        }.runTaskLater(this, 20);
	
	}
	public void FriendTimer(Player p, Player target)
	{
		BukkitTask task = new BukkitRunnable() 
		{
			int i = 0;
            @Override
            public void run() 
            {
            	if(UserArenaState.get(p.getName()).equals("Field") || UserArenaState.get(p.getName()).equals(""))
            	{
            		if(i == 5)
                	{
                		p.sendMessage("§b[마인아레나] §e친선전 수락 대기시간이 초과되어 친선전 신청을 취소합니다.");
                		if(!IsinMatch.get(target.getName()))
                		{
                    		match.put(target.getName(), "@");
        					UserArenaState.put(target.getName(), "");
                		}
    					IsSetFriend.put(p.getName(), false);
    					this.cancel();
                	}
            		i++;
            	}
            	else
            	{
            		this.cancel();
            	}
            }
        }.runTaskTimer(this, 0, 20);
	
	}
	public void LoginTimer(Player p)
	{
		/*
		BukkitTask task = new BukkitRunnable() 
		{
            @Override
            public void run() 
            {
        		Bukkit.dispatchCommand(p, "petblocks enable " + p.getName());
        		Bukkit.dispatchCommand(p, "petblocks enable " + p.getName());
        		Bukkit.dispatchCommand(p, "petblocks enable " + p.getName());
            }
        }.runTaskLater(this, 60);
		 */
	}
	public void ClearScoreBoard(Player p)
	{
		try{StateBoard.get(p.getName()).clearSlot(DisplaySlot.SIDEBAR);}catch(Exception exe){}
		ScoreboardManager SBmanager = Bukkit.getScoreboardManager();
		Scoreboard board = SBmanager.getNewScoreboard();
		Objective title = board.registerNewObjective("test", "dummy");
		title.setDisplaySlot(DisplaySlot.SIDEBAR);
		title.setDisplayName("MineArena");
		board.getObjective(DisplaySlot.SIDEBAR).getScore("랭크 포인트(RP)").setScore(userscore.getInt("userscore." + p.getName() + ".Score"));
		board.getObjective(DisplaySlot.SIDEBAR).getScore("랭크 아레나 승리").setScore(userscore.getInt("userscore." + p.getName() + ".WinCount"));
		board.getObjective(DisplaySlot.SIDEBAR).getScore("일반 아레나 승리").setScore(userscore.getInt("userscore." + p.getName() + ".DeWinCount"));
		board.getObjective(DisplaySlot.SIDEBAR).getScore("필드 아레나 처치").setScore(userscore.getInt("userscore." + p.getName() + ".FieldKillCount"));
		
		Objective obj = board.registerNewObjective("showhealth", "health");
		obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
		obj.setDisplayName("§4♥");
		
		StateBoard.put(p.getName(), board);
		p.setScoreboard(StateBoard.get(p.getName()));
		
		for(Player online : Bukkit.getOnlinePlayers())
		{
			if(!IsinMatch.get(online.getName()))
			{
				online.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2, 0));
			}
		}
	}

	public void modifyScoreBoard(Player p)
	{
		StateBoard.get(p.getName()).getObjective(DisplaySlot.SIDEBAR).getScore("랭크 포인트(RP)").setScore(userscore.getInt("userscore." + p.getName() + ".Score"));
		StateBoard.get(p.getName()).getObjective(DisplaySlot.SIDEBAR).getScore("랭크 아레나 승리").setScore(userscore.getInt("userscore." + p.getName() + ".WinCount"));
		StateBoard.get(p.getName()).getObjective(DisplaySlot.SIDEBAR).getScore("일반 아레나 승리").setScore(userscore.getInt("userscore." + p.getName() + ".DeWinCount"));
		StateBoard.get(p.getName()).getObjective(DisplaySlot.SIDEBAR).getScore("필드 아레나 처치").setScore(userscore.getInt("userscore." + p.getName() + ".FieldKillCount"));
		
		
		p.setScoreboard(StateBoard.get(p.getName()));
	}
	
	public boolean getSubMap(Player p1, Player p2, HashMap<Integer, List<String>> Arena)
	{
		String ArenaName = "";
		boolean b = true;
		if(Arena.toString().equals(기본.toString()))
		{
			ArenaName = "Default";
		}
		else if(Arena.toString().equals(정글.toString()))
		{
			ArenaName = "Jungle";
		}
		else if(Arena.toString().equals(지옥.toString()))
		{
			ArenaName = "Hell";
		}
		else if(Arena.toString().equals(중세.toString()))
		{
			ArenaName = "Medieval";
		}
		else if(Arena.toString().equals(설산.toString()))
		{
			ArenaName = "Snowy_Mountains";
		}
		else if(Arena.toString().equals(묘지.toString()))
		{
			ArenaName = "Cemetery";
		}
		else if(Arena.toString().equals(천공섬.toString()))
		{
			ArenaName = "Sky_island";
		}
		List<String> Subs = submap.getStringList("submap." + ArenaName + ".SubMaps");
		
		
		String LocationName = "";
		String[] SubMapinfo = new String[6];
		int border = 0;
		do
		{
			int randomvalue = ran.nextInt(Subs.size());
			
			SubMapinfo = Subs.get(randomvalue).split(",");
			
			LocationName = SubMapinfo[0];
			
			border++;
			
		}while(usingmap.contains(LocationName) && border < 100);
		if(border >= 100)
		{
			b = false;
		}
		else
		{
			usingmap.add(LocationName);
			
			String[] location = new String[2];
			
			location[0] = SubMapinfo[1];
			location[1] = SubMapinfo[2];
			
			usemap.put(p1.getName(), LocationName);
			usemap.put(p2.getName(), LocationName);
			UseArena.put(p1.getName(), ArenaName);
			UseArena.put(p2.getName(), ArenaName);
			int socre1 = userscore.getInt("userscore." + p1.getName() + ".Score");
			int socre2 = userscore.getInt("userscore." + p2.getName() + ".Score");
			
			p1.sendMessage("§b[마인아레나] §a" + p1.getName()+ "§e님과 §a" + p2.getName() +"§e님의 전투가 (§c" + LocationName + "§e)에서 시작되었습니다!");
			p2.sendMessage("§b[마인아레나] §a" + p1.getName()+ "§e님과 §a" + p2.getName() +"§e님의 전투가 (§c" + LocationName + "§e)에서 시작되었습니다!");
	
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp " + location[0] + " " +p1.getName());
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp " + location[1] + " " + p2.getName());
			
			int rv = ran.nextInt(4);
			/*
			 * 18-01-29 재생되는 노래를 모두 지워달라는 요청에 의해 주석 처리
			switch(rv)
			{
			case 0:
				disableMusic(p1);
				disableMusic(p2);
				p1.playSound(p1.getLocation(), Sound.RECORD_WARD,SoundCategory.RECORDS, 10000, 1);
				p2.playSound(p2.getLocation(), Sound.RECORD_WARD,SoundCategory.RECORDS, 10000, 1);
				break;
			case 1:
				disableMusic(p1);
				disableMusic(p2);
				
				p1.playSound(p1.getLocation(), Sound.RECORD_11,SoundCategory.RECORDS, 10000, 1);
				p2.playSound(p2.getLocation(), Sound.RECORD_11,SoundCategory.RECORDS, 10000, 1);
				break;
			case 2:
				disableMusic(p1);
				disableMusic(p2);
				
				p1.playSound(p1.getLocation(), Sound.RECORD_13,SoundCategory.RECORDS, 10000, 1);
				p2.playSound(p2.getLocation(), Sound.RECORD_13,SoundCategory.RECORDS, 10000, 1);
				break;
			case 3:
				disableMusic(p1);
				disableMusic(p2);
				
				p1.playSound(p1.getLocation(), Sound.RECORD_MELLOHI,SoundCategory.RECORDS, 10000, 1);
				p2.playSound(p2.getLocation(), Sound.RECORD_MELLOHI,SoundCategory.RECORDS, 10000, 1);
				break;
			}
			*/
		}
		return b;
	}
	public Location getSpawnLocation()
	{
		
		String[] SpawnLocationInfo = c.getString("config.SpawnLocation").split(",");
		int x, y, z =0;
		String world = SpawnLocationInfo[0];
		x = Integer.parseInt(SpawnLocationInfo[1]);
		y = Integer.parseInt(SpawnLocationInfo[2]);
		z = Integer.parseInt(SpawnLocationInfo[3]);
		Location Destination = new Location(Bukkit.getWorld(world), x, y, z); //defines new location
		return Destination;
	}
	public void MovePlayer2RandomFieldPoint(Player target)
	{
		List<String> points = submap.getStringList("submap.FieldArena.Points");
		int RandomValue = ran.nextInt(points.size());
		String point = points.get(RandomValue);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "warp " + point + " " + target.getName());
		disableMusic(target);
		//target.playSound(target.getLocation(), Sound.RECORD_BLOCKS, SoundCategory.RECORDS, 100000, 1);
		target.setGlowing(true);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "heal " + target.getName());
	}
	public void GetGUIIcon()
	{
		FieldIconName = c.getString("config.FieldIcon.name");
		DefaultIconName = c.getString("config.DefaultIcon.name");
		CompititionIconName = c.getString("config.CompititionIcon.name");
		
		FieldIconLore = (ArrayList<String>) c.getStringList("config.FieldIcon.lore");
		DefaultIconLore = (ArrayList<String>) c.getStringList("config.DefaultIcon.lore"); 
		CompititionIconLore = (ArrayList<String>) c.getStringList("config.CompititionIcon.lore");
		
		FieldIconId = c.getInt("config.FieldIcon.id");
		FieldIconMeta = c.getInt("config.FieldIcon.meta");
		DefaultIconId = c.getInt("config.DefaultIcon.id");
		DefaultIconMeta = c.getInt("config.DefaultIcon.meta");
		CompititionIconId = c.getInt("config.CompititionIcon.id");
		CompititionIconMeta = c.getInt("config.CompititionIcon.meta");
	}
	public Inventory CreateGUI(Player p)
	{		
		
		ArrayList<String> dummy = new ArrayList<String>();
		
		List<String> FieldIconLore2 = ChangeString("<FieldKillCount>", userscore.getInt("userscore." + p.getName() + ".FieldKillCount")+"", FieldIconLore);
		List<String> DefaultIconLore2 = ChangeString("<DefaultWinCount>", userscore.getInt("userscore." + p.getName() + ".DeWinCount")+"" , DefaultIconLore); 
		
		int moduleDefualtWinCount = userscore.getInt("userscore." + p.getName() + ".DeWinCount") % 3;
		DefaultIconLore2 = ChangeString("<DefaultWinString>", this.getDefaultWinCount(moduleDefualtWinCount) , DefaultIconLore2); 
		
		double percent_rank = get_User_Per_Score(p);
		List<String> CompititionIconLore2 = ChangeString("<CompititionWinCount>", userscore.getInt("userscore." + p.getName() + ".WinCount")+"", CompititionIconLore);
		CompititionIconLore2 = ChangeString("<CompititionPoint>", userscore.getInt("userscore." + p.getName() + ".Score")+"", CompititionIconLore2);
		CompititionIconLore2 = ChangeString("<RankingPercentString>", percent_rank+"" , CompititionIconLore2); 
		UserCompititionLore.put(p.getName(), CompititionIconLore2);
		UserDefaultLore.put(p.getName(), DefaultIconLore2);
		
		ItemStack FieldIcon = LibMain.createItem(FieldIconId, FieldIconMeta, 1, FieldIconName, FieldIconLore2, "FFFFFF", dummy);
		ItemStack DefaultIcon = LibMain.createItem(DefaultIconId, DefaultIconMeta, 1, DefaultIconName, DefaultIconLore2, "FFFFFF", dummy);
		ItemStack CompititionIcon = LibMain.createItem(CompititionIconId, CompititionIconMeta, 1, CompititionIconName, CompititionIconLore2, "FFFFFF", dummy);
		
		Inventory GuiInventory = Bukkit.createInventory(null, 27, "MineArena Arena Page");

		
		for(int i = 0 ; i < 27 ; i++)
		{
			GuiInventory.setItem(i, new ItemStack(160, 1, (short)0));
		}
		ItemStack DummyItem = new ItemStack(160, 1, (short)7);
		
		GuiInventory.setItem(6, DummyItem);
		GuiInventory.setItem(7, DummyItem);
		GuiInventory.setItem(8, DummyItem);
		GuiInventory.setItem(15, DummyItem);
		GuiInventory.setItem(16, DummyItem);
		GuiInventory.setItem(17, DummyItem);
		GuiInventory.setItem(24, DummyItem);
		GuiInventory.setItem(25, DummyItem);
		GuiInventory.setItem(26, DummyItem);
		
		GuiInventory.setItem(10, CompititionIcon);
		GuiInventory.setItem(13, DefaultIcon);
		GuiInventory.setItem(16, FieldIcon);
		
		return GuiInventory; 
	}

	public void GUIAnimation(Player p, Integer adder)
	{
		BukkitTask task = new BukkitRunnable() 
		{
			
            @Override
            public void run() 
            {
            	try
            	{
            		if((!IsinMatch.get(p.getName()) || (IsinMatch.get(p.getName()) && UserArenaState.get(p.getName()).equals("Field"))) && !UserArenaState.get(p.getName()).equals(""))
                	{
            			PreviousStat.put(p.getName(), "GUIANIMATION");
                		switch (TimerValue.get(p.getName()))
        				{
        					case 0:
        						PersonalGUI.get(p.getName()).setItem(0 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(9 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 1:
        						PersonalGUI.get(p.getName()).setItem(1 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(0 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 2:
        						TimerValue.put(p.getName(), 10);
        						PersonalGUI.get(p.getName()).setItem(2 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(1 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 9:
        						TimerValue.put(p.getName(), -1);
        						PersonalGUI.get(p.getName()).setItem(9 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(18 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 11:
        						TimerValue.put(p.getName(), 19);
        						PersonalGUI.get(p.getName()).setItem(11 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(2 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 18:
        						TimerValue.put(p.getName(), 8);
        						PersonalGUI.get(p.getName()).setItem(18 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(19 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 19:
        						TimerValue.put(p.getName(), 17);
        						PersonalGUI.get(p.getName()).setItem(19 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(20 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					case 20:
        						TimerValue.put(p.getName(), 18);
        						PersonalGUI.get(p.getName()).setItem(20 + adder, new ItemStack(160, 1, (short)15));
        						PersonalGUI.get(p.getName()).setItem(11 + adder, new ItemStack(160, 1, (short)0));
        						break;
        					
        				}
        				TimerValue.put(p.getName(), (TimerValue.get(p.getName())+1));
        		
        				TimerIcon.put(p.getName(), TimerIcon.get(p.getName())+1);
        				
        				if((TimerIcon.get(p.getName()) % 10)== 9)
        				{
        					ItemStack is = PersonalGUI.get(p.getName()).getItem(10 + adder);
            				ItemMeta im = is.getItemMeta();    				
            				List<String> lore = im.getLore();
            				if(TimerIconValue.get(p.getName()) <= 1 && !lore.get(lore.size()-1).contains("경과 시간"))
            				{
            					lore.add("§f");
            					lore.add("§4▶ §f상대를 찾고 있습니다.");
            					lore.add("§4▶ §b경과 시간: §e" + TimerIconValue.get(p.getName()) + "§b초");
            				}
            				else
            				{
                				lore.set(lore.size()-2, getIconAnimationDot(p.getName()));
                				lore.set(lore.size()-1, "§4▶ §b경과 시간: §e" + TimerIconValue.get(p.getName()) + "§b초");
            				}
            				im.setLore(lore);
            				is.setItemMeta(im);
            				PersonalGUI.get(p.getName()).setItem(10 + adder, is);
            				TimerIconValue.put(p.getName(), TimerIconValue.get(p.getName())+1);
            				String name = p.getName();
        					IsinMatch.put(name, false);
        					IsinList.put(name, false);    					
        					RemovePlayerInList(p.getName());
        					MatchUp(p, getArenaMapUseingRandomNumber(),true);
        				}
                	}
                	else
                	{
                		this.cancel();
                		TimerIconValue.put(p.getName(), 0);
                		PersonalGUI.get(p.getName()).setItem(0 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(1 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(2 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(9 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(11 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(18 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(19 + adder, new ItemStack(160, 1, (short)0));
                		PersonalGUI.get(p.getName()).setItem(20 + adder, new ItemStack(160, 1, (short)0));
                	}
                
            	}
            	catch(Exception e)
            	{
            		Player Moon_Eclipse = Bukkit.getPlayer("Moon_Eclipse");
            		if(Moon_Eclipse != null && Moon_Eclipse.isOnline())
            		{
            			Moon_Eclipse.sendMessage("[Error] 마인아레나:" + e.getMessage());
            			Moon_Eclipse.sendMessage("[Error] 마인아레나 플레이어 이름:" + p.getName());
            			Moon_Eclipse.sendMessage("[Error] 마인아레나 플레이어 상대:" + match.get(p.getName()));
            			Moon_Eclipse.sendMessage("[Error] 마인아레나 버킷 런에이블 ID:" + this.getTaskId());
            			
            		}
            		RemovePlayerInList(match.get(p.getName()));
            		System.out.println("[Error] 마인아레나:" + e.getMessage());
            		System.out.println("[Error] 마인아레나 플레이어 이름:" + p.getName());
            		for(StackTraceElement s : e.getStackTrace())
        			{
        				String error = s.getFileName() + "의" + s.getClassName() + "클래스," + s.getMethodName() + "메소드에서 오류: 본문" + s.getLineNumber() + " 번째줄";
        				System.out.println(error);
        			}
            		e.printStackTrace();
            		this.cancel();
            	}
            }
        }.runTaskTimer(this, 0, 2);
        GUIAnimationTask.put(p.getName(), task);
	}
	public static List<String> ChangeString(String PlaceHolder, String NextString, List<String> lore)
	{
		ArrayList<String> newlore = new ArrayList<String>();
		for(int i = 0; i < lore.size() ; i++)
		{
			if(lore.get(i).contains(PlaceHolder))
			{
				newlore.add(lore.get(i).replaceAll(PlaceHolder, NextString));
			}
			else
			{
				newlore.add(lore.get(i));
			}
		}
		return newlore;
	}
	public String getIconAnimationDot(String PlayerName)
	{
		String s = "";
		int i = TimerIconValue.get(PlayerName) % 3;
		switch(i)
		{
		case 0:
			s = "§4▶ §f상대를 찾고 있습니다.";
			break;
		case 1:
			s = "§4▶ §f상대를 찾고 있습니다..";
			break;
		case 2:
			s = "§4▶ §f상대를 찾고 있습니다...";
			break;
		}
		return s;
	}
	public HashMap<Integer, List<String>> getArenaMap(Player p)
	{
		String name = p.getName();
		String ArenaName = UseArena.get(name);
		
		HashMap<Integer, List<String>> temp = new HashMap<Integer, List<String>>();
		
		if(ArenaName.equals("Default"))
		{
			temp= 기본;
		}
		else if(ArenaName.equals("Jungle"))
		{
			temp= 정글;
		}
		else if(ArenaName.equals("Hell"))
		{
			temp= 지옥;
		}
		else if(ArenaName.equals("Medieval"))
		{
			temp= 중세;
		}
		else if(ArenaName.equals("Snowy_Mountains"))
		{
			temp= 설산;
		}
		else if(ArenaName.equals("Cemetery"))
		{
			temp= 묘지;
		}
		else if(ArenaName.equals("Sky_island"))
		{
			temp= 천공섬;
		}
		return temp;
	}
	public HashMap<Integer, List<String>> getArenaMap2(String s)
	{
		String ArenaName = s;
		
		HashMap<Integer, List<String>> temp2 = new HashMap<Integer, List<String>>();
		
		if(ArenaName.equals("Default"))
		{
			temp2 = 기본;
		}
		else if(ArenaName.equals("Jungle"))
		{
			temp2 = 정글;
		}
		else if(ArenaName.equals("Hell"))
		{
			temp2 = 지옥;
		}
		else if(ArenaName.equals("Medieval"))
		{
			temp2 = 중세;
		}
		else if(ArenaName.equals("Snowy_Mountains"))
		{
			temp2 = 설산;
		}
		else if(ArenaName.equals("Cemetery"))
		{
			temp2 = 묘지;
		}
		else if(ArenaName.equals("Sky_island"))
		{
			temp2 = 천공섬;
		}
		return temp2;
	}
	public String getArenaName(String s)
	{
		String ArenaName = s;
		
		String temp2 = "";
		
		if(ArenaName.equals("Default"))
		{
			temp2 = "기본";
		}
		else if(ArenaName.equals("Plains"))
		{
			temp2 = "초원";
		}
		else if(ArenaName.equals("Jungle"))
		{
			temp2 = "정글";
		}
		else if(ArenaName.equals("Hell"))
		{
			temp2 = "지옥";
		}
		else if(ArenaName.equals("Medieval"))
		{
			temp2 = "중세";
		}
		else if(ArenaName.equals("Snowy_Mountains"))
		{
			temp2 = "설산";
		}
		else if(ArenaName.equals("Cemetery"))
		{
			temp2 = "묘지";
		}
		else if(ArenaName.equals("Cave"))
		{
			temp2 = "동굴";
		}
		else if(ArenaName.equals("Sky_island"))
		{
			temp2 = "천공섬";
		}
		else if(ArenaName.equals("Lib"))
		{
			temp2 = "도서관";
		}
		return temp2;
	}
	public HashMap<Integer, List<String>> getArenaMapUseingRandomNumber()
	{
		int randomvalue = ran.nextInt(7)+1;
		
		HashMap<Integer, List<String>> temp = new HashMap<Integer, List<String>>();
		
		//Bukkit.broadcastMessage(randomvalue + "랜덤 아레나 리턴 정수");
		
		switch(randomvalue)
		{
		case 1:
			temp = 기본;
			break;
		case 2:
			temp = 정글;
			break;
		case 3:
			temp = 지옥;
			break;
		case 4:
			temp = 중세;
			break;
		case 5:
			temp = 설산;
			break;
		case 6:
			temp = 묘지;
			break;
		case 7:
			temp = 천공섬; 
			break;
		}
		return temp;
	}
	public void saveDefaultUserScore()
	{
		   if (UserScore == null)
		   {
			   UserScore = new File(getDataFolder(), "userscore.yml");
		   }
		   if (!UserScore.exists())
		   {            
			   this.saveResource("userscore.yml", true);
		   }
	}
	public void saveUserScore()
	{
		try 
		{
			userscore.save(UserScore);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + UserScore, ex);
	    }
	}
	public void saveDefaultSubMap()
	{
		   if (SubMap == null)
		   {
			   SubMap = new File(getDataFolder(), "submap.yml");
		   }
		   if (!SubMap.exists())
		   {            
			   this.saveResource("submap.yml", true);
		   }
	}
	public void saveDefaultProfileFile()
	{
		   if (ProfileFile == null)
		   {
			   ProfileFile = new File(getDataFolder(), "profile.yml");
		   }
		   if (!ProfileFile.exists())
		   {            
			   this.saveResource("profile.yml", true);
		   }
	}
	public void saveSubmap()
	{
		try 
		{
			submap.save(SubMap);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + SubMap, ex);
	    }
	}
	public void savePartyFile()
	{
		try 
		{
			party.save(PartyFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + PartyFile, ex);
	    }
	}
	public void saveDefaultPartyFile()
	{
		   if (PartyFile == null)
		   {
			   PartyFile = new File(getDataFolder(), "party.yml");
		   }
		   if (!PartyFile.exists())
		   {            
			   this.saveResource("party.yml", true);
		   }
	}
	public void saveGuildFile()
	{
		try 
		{
			guild.save(GuildFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + GuildFile, ex);
	    }
	}
	public void saveDefaultGuildFile()
	{
		   if (GuildFile == null)
		   {
			   GuildFile = new File(getDataFolder(), "guild.yml");
		   }
		   if (!GuildFile.exists())
		   {            
			   this.saveResource("guild.yml", true);
		   }
	}
	public void saveProfileFile()
	{
		try 
		{
			Profile.save(ProfileFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + ProfileFile, ex);
	    }
	}
	public void saveDefaultTitle()
	{
		   if (TitleFile == null)
		   {
			   TitleFile = new File(getDataFolder(), "title.yml");
		   }
		   if (!TitleFile.exists())
		   {            
			   this.saveResource("title.yml", true);
		   }
	}
	public void saveTitle()
	{
		try 
		{
	       title.save(TitleFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + TitleFile, ex);
	    }
	}
	public void saveDefaultStatsFile()
	{
		   if (StatsFile == null)
		   {
			   StatsFile = new File(getDataFolder(), "stats.yml");
		   }
		   if (!StatsFile.exists())
		   {            
			   this.saveResource("stats.yml", true);
		   }
	}
	public void saveStats()
	{
		try 
		{
			stats.save(StatsFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + StatsFile, ex);
	    }
	}
	public void saveDefaultEffectFile()
	{
		   if (EffectFile == null)
		   {
			   EffectFile = new File(getDataFolder(), "Effect.yml");
		   }
		   if (!EffectFile.exists())
		   {            
			   this.saveResource("Effect.yml", true);
		   }
	}
	public void saveEffect()
	{
		try 
		{
			Effect.save(EffectFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + EffectFile, ex);
	    }
	}
	public Economy getEconomy()
	{
        if(!vaultLoaded)
        {
            vaultLoaded = true;
            if (getServer().getPluginManager().getPlugin(VAULT) != null)
            {
                RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
                if(rsp!=null)
                {
                    vault = rsp.getProvider();
                }
            }
        }
        return vault;
    }
	public void saveTierFile()
	{
		try 
		{
			tier.save(TierFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + TierFile, ex);
	    }
	}
	public void saveDefaultTierFile()
	{
		   if (TierFile == null)
		   {
			   TierFile = new File(getDataFolder(), "tier.yml");
		   }
		   if (!TierFile.exists())
		   {            
			   this.saveResource("tier.yml", true);
		   }
	}
	public void saveAttributeGUIFile()
	{
		try 
		{
			AttributeGUI.save(AttributeGUIFile);
	    }
		catch (IOException ex)
		{
	        getLogger().log(Level.SEVERE, "Could not save config to " + AttributeGUIFile, ex);
	    }
	}
	public void saveDefaultAttributeGUIFile()
	{
		   if (AttributeGUIFile == null)
		   {
			   AttributeGUIFile = new File(getDataFolder(), "AttributeGUI.yml");
		   }
		   if (!AttributeGUIFile.exists())
		   {            
			   this.saveResource("AttributeGUI.yml", true);
		   }
	}
	public static Economy getPluginEconomy()
	{
		return vault;
	}
	public boolean isEnglish(String textInput) 
	{
		char chrInput;
		for (int i = 0; i < textInput.length(); i++) 
		{
			chrInput = textInput.charAt(i); // 입력받은 텍스트에서 문자 하나하나 가져와서 체크
			if ((chrInput >= 0x61 && chrInput <= 0x7A)) 
			{
			    // 영문(소문자) OK!
			} 
			else if (chrInput >=0x41 && chrInput <= 0x5A) 
			{
			    // 영문(대문자) OK!
			}
			else if (chrInput >= 0x30 && chrInput <= 0x39) 
			{
			    // 숫자 OK!
			}
			else if (chrInput == 0x5F)
			{
				
			}
			else 
			{
			    return false;   // 영문자도 아니고 숫자도 아님!
			}
		}
		return true;
	}
	public void BloodSuck(LivingEntity Damager, double[] Damager_StatInfo, double damage)
	{
		if(Debug) Bukkit.broadcastMessage("--------흡혈 메소드에서 넘겨받은 데미지: " + damage + "------");
		
		double health = Damager.getHealth();
		if(Debug) Bukkit.broadcastMessage("플레이어의 현재 체력: " + health);
		
		double SuckValue = pp.getDamageFromStats(Damager_StatInfo, 15, 16, 17);
		if(Debug) Bukkit.broadcastMessage("흡혈할 정도: " + SuckValue + "%");
		
		double UpHealth = (damage/100) * SuckValue;
		if(Debug) Bukkit.broadcastMessage("흡혈체력의 양: " + UpHealth);
		
		double Maxhealth = Damager.getMaxHealth();
		if((health+UpHealth) > Maxhealth)
		{
			Damager.setHealth(Maxhealth);
			if(Debug) Bukkit.broadcastMessage("흡혈 후 최대 채력을 넘으므로 최대 체력으로 회복: " + Maxhealth);
		}
		else if((health+UpHealth) < 0)
		{
			Damager.setHealth(0.1);
			if(Debug) Bukkit.broadcastMessage("흡혈 후 체력이 0보다 낮으므로 0.1로 임시 지정: " + (health + UpHealth));
		}
		else
		{
			Damager.setHealth(health + UpHealth);
			if(Debug) Bukkit.broadcastMessage("흡혈 후 체력: " + (health + UpHealth));
		}
	}
	public void Monster_BloodSuck(LivingEntity Damager, double[] Damager_StatInfo, double damage)
	{
		if(Debug) Bukkit.broadcastMessage("--------몬스터 흡혈 메소드에서 넘겨받은 데미지: " + damage + "------");
		
		double health = Damager.getHealth();
		if(Debug) Bukkit.broadcastMessage("플레이어의 현재 체력: " + health);
		
		double SuckValue = pp.getDamageFromStats(Damager_StatInfo, 75, 76, 77);
		if(Debug) Bukkit.broadcastMessage("흡혈할 정도: " + SuckValue + "%");
		
		double UpHealth = (damage/100) * SuckValue;
		if(Debug) Bukkit.broadcastMessage("흡혈체력의 양: " + UpHealth);
		
		double Maxhealth = Damager.getMaxHealth();
		if((health+UpHealth) > Maxhealth)
		{
			Damager.setHealth(Maxhealth);
			if(Debug) Bukkit.broadcastMessage("흡혈 후 최대 채력을 넘으므로 최대 체력으로 회복: " + Maxhealth);
		}
		else if((health+UpHealth) < 0)
		{
			Damager.setHealth(0.1);
			if(Debug) Bukkit.broadcastMessage("흡혈 후 체력이 0보다 낮으므로 0.1로 임시 지정: " + (health + UpHealth));
		}
		else
		{
			Damager.setHealth(health + UpHealth);
			if(Debug) Bukkit.broadcastMessage("흡혈 후 체력: " + (health + UpHealth));
		}
	}
	public void ToolRepair(Player p)
	{
		ItemStack RightHand = p.getItemInHand();
		ItemStack LeftHand = p.getEquipment().getItemInOffHand();
		ItemStack Helmet = p.getEquipment().getHelmet();
		ItemStack Chest = p.getEquipment().getChestplate();
		ItemStack Leggings = p.getEquipment().getLeggings();
		ItemStack Boots = p.getEquipment().getBoots();
		
		if(!(RightHand.getTypeId() == 0))
		{
			if(RightHand.hasItemMeta())
			{
				ItemMeta im = RightHand.getItemMeta();
				im.setUnbreakable(true);
				RightHand.setItemMeta(im);
				LibMain.removeAttributes(RightHand);
			}
		}
		if(!(LeftHand.getTypeId() == 0))
		{
			if(LeftHand.hasItemMeta())
			{
				ItemMeta im = LeftHand.getItemMeta();
				im.setUnbreakable(true);
				LeftHand.setItemMeta(im);
			}
		}
		if(Helmet != null)
		{
			if(Helmet.hasItemMeta())
			{
				ItemMeta im = Helmet.getItemMeta();
				im.setUnbreakable(true);
				Helmet.setItemMeta(im);
			}
		}
		if(Chest != null)
		{
			if(Chest.hasItemMeta())
			{
				ItemMeta im = Chest.getItemMeta();
				im.setUnbreakable(true);
				Chest.setItemMeta(im);
			}
		}
		if(Leggings != null)
		{
			if(Leggings.hasItemMeta())
			{
				ItemMeta im = Leggings.getItemMeta();
				im.setUnbreakable(true);
				Leggings.setItemMeta(im);
			}
		}
		if(Boots != null)
		{
			if(Boots.hasItemMeta())
			{
				ItemMeta im = Boots.getItemMeta();
				im.setUnbreakable(true);
				Boots.setItemMeta(im);
			}
		}
	}
	public void InitializeArena()
	{
		ArrayList<String> temp1 = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();
		for(int i = -3; i < 30 ; i++)
		{
			
			기본.put(i, temp1);
			정글.put(i, temp1);
			지옥.put(i, temp1);
			중세.put(i, temp1);
			설산.put(i, temp1);
			묘지.put(i, temp1);
			천공섬.put(i, temp1);
			
		}
		temp2.add("Default");
		기본.put(-4, temp2);
		
		temp2 = new ArrayList<String>();
		temp2.clear();
		temp2.add("Jungle");
		정글.put(-4, temp2);
		
		temp2 = new ArrayList<String>();
		temp2.clear();
		temp2.add("Hell");
		지옥.put(-4, temp2);
		
		temp2 = new ArrayList<String>();
		temp2.clear();
		temp2.add("Medieval");
		중세.put(-4, temp2);
		
		temp2 = new ArrayList<String>();
		temp2.clear();
		temp2.add("Snowy_Mountains");
		설산.put(-4, temp2);
		
		temp2 = new ArrayList<String>();
		temp2.clear();
		temp2.add("Cemetery");
		묘지.put(-4, temp2);
		
		temp2 = new ArrayList<String>();
		temp2.clear();
		temp2.add("Sky_island");
		천공섬.put(-4, temp2);
		
	}
	public void InitializeUserValues(String name)
	{
		IsinMatch.put(name, false);
		IsinList.put(name, false);
		REjectList.put(name, false);
		match.put(name, "@");
		PersonalGUI.put(name, CreateGUI(Bukkit.getPlayer(name)));
		TimerValue.put(name, 0);
		TimerIconValue.put(name, 1);
		TimerIcon.put(name, 0);
		UserArenaState.put(name, "");
		PlayerWhoClicked.put(name, "");
		PreviousPlayer.put(name, "");
		UserChatState.put(name, "@");
		isDead.put(name, false);
		PreviousStat.put(name, "");
		IsSetFriend.put(name, false);
		
		String ChatValue = userscore.getString("userscore." + name + ".ChatColor");
		if(ChatValue ==null || ChatValue.equals(""))
		{
			userscore.set("userscore." + name + ".ChatColor", "f");
			ChatValue = "f";
		}
		
		UserChatColor.put(name, ChatValue);
	}
	public String[] getPlayerValues(String name)
	{
		String[] values = new String[16];
		
		values[0] = "매치 안에 있음: " + IsinMatch.get(name);
		values[1] = "리스트 안에 있음: "+IsinList.get(name);
		values[2] = "거부 목록에있음: "+REjectList.get(name);
		values[3] = "죽었음: "+isDead.get(name);
		values[4] = "매치 상대: "+match.get(name);
		values[5] = "타이머 밸류: "+TimerValue.get(name);
		values[6] = "타이머 아이콘 밸류: "+TimerIconValue.get(name);
		values[7] = "타이머 아이콘: "+TimerIcon.get(name);
		values[8] = "유저의 현재 아레나 상태: "+"\""+UserArenaState.get(name) + "\"";
		values[9] = "유저가 클릭한 대상: "+PlayerWhoClicked.get(name);
		values[10] = "최근에 전투한 사람: "+PreviousPlayer.get(name);
		values[11] = "최근에 지나온 메소드: " + PreviousStat.get(name);
		if(GUIAnimationTask.get(name) != null)
		{
			values[12] = "현재 GUI ANIMATION TASK NUMBER: "+GUIAnimationTask.get(name).getTaskId();
		}
		values[13] = "현재 유저의 채팅 상태: " + UserChatState.get(name);
		
		int score = userscore.getInt("userscore." + name + ".Score");
		values[14] = "현재 유저의 경쟁 티어: " + getPlayerRankQueueNumber(score);
		values[15] = "현재 유저의 일반 티어: " + getPlayerDefaultQueueNumber(score);
		return values;
		
	}

	public int getSecond()
	{
		Date date = new Date();
		return date.getSeconds();
	}
	public int getMin()
	{
		Date date = new Date();
		return date.getMinutes();
	}
	public int getHour()
	{
		Date date = new Date();
		return date.getHours();
	}
	public int getDay()
	{
		Date date = new Date();
		return date.getDate();
	}
	public int getMonth()
	{
		Date date = new Date();
		return date.getMonth() + 1;
	}
	public int getYear()
	{
		Date date = new Date();
		return date.getYear() + 1900;
	}
	public String getTimeofNow()
	{
		String s = 
		s = (getYear() + "년 " + getMonth() + "월 " + getDay() + "일 " + getHour() + "시 " + getMin() + "분 " + getSecond() + "초");
		return s;
	}
	public boolean IsWithin(Location PlayerLocation, String position1, String position2)
	{
		boolean b = false;
		int xp,yp,zp,x1,y1,z1,x2,y2,z2;
		xp = (int) PlayerLocation.getX();
		yp = (int) PlayerLocation.getY();
		zp = (int) PlayerLocation.getZ();
		String[] split1 = position1.split(",");
		String[] split2 = position2.split(",");
		x1 = Integer.parseInt(split1[0]);
		y1 = Integer.parseInt(split1[1]);
		z1 = Integer.parseInt(split1[2]);
		x2 = Integer.parseInt(split2[0]);
		y2 = Integer.parseInt(split2[1]);
		z2 = Integer.parseInt(split2[2]);
		
		if((xp >= x1 && xp <= x2) || (xp >= x2 && xp <= x1))
		{
			if((yp >= y1 && yp <= y2) || (yp >= y2 && yp <= y1))
			{
				if((zp >= z1 && zp <= z2) || (zp >= z2 && zp <= z1))
				{
					b = true;
				}
			}
		}
		
		return b;
	}
	public static String getDefaultWinCount(int i)
	{
		String WinString = "";
		switch(i)
		{
		case 0:
			WinString = "§f✔ §f✔ §f✔";
			break;
		case 1:
			WinString = "§4✔ §f✔ §f✔";
			break;
		case 2:
			WinString = "§4✔ §4✔ §f✔";
			break;
		}
		return WinString;
	}
	public static double get_User_Per_Score(Player p)
	{
		double per_score = 0;

		Set<String> UserSet = userscore.getConfigurationSection("userscore").getKeys(false);
		int size_of_UserSet = UserSet.size();
		
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		
		for(String key : UserSet)
		{
			int score = userscore.getInt("userscore."+ key + ".Score");
			map.put(key, score);
		}
		List<String> RankList = sortByValue(map);
		int rank = 1;
		for(String name2 : RankList)
		{
			if(name2.equals(p.getName()))
			{

				per_score = (rank*1.0/size_of_UserSet)*100;
				break;
			}
			rank++;
		}
		return Double.parseDouble(String.format("%.1f",per_score));
	}
	public void disableMusic(Player p)
	{
		p.stopSound(Sound.RECORD_CHIRP, SoundCategory.RECORDS);
		p.stopSound(Sound.RECORD_WAIT, SoundCategory.RECORDS);
		p.stopSound(Sound.RECORD_CHIRP, SoundCategory.RECORDS);
		p.stopSound(Sound.RECORD_WARD, SoundCategory.RECORDS);
		p.stopSound(Sound.RECORD_11, SoundCategory.RECORDS);
		p.stopSound(Sound.RECORD_13, SoundCategory.RECORDS);
		p.stopSound(Sound.RECORD_MELLOHI, SoundCategory.RECORDS);
	}

	public static ArenaMatch getInstance()
	{
		return instance;
	}		
	
}
