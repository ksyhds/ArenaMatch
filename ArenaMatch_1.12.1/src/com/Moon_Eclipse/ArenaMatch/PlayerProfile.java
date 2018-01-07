package com.Moon_Eclipse.ArenaMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.Moon_Eclipse.MCMANYtitle.*;
import com.Moon_eclipse.EclipseLib.LibMain;
public class PlayerProfile 
{
	Configuration Profile = ArenaMatch.Profile;
	Configuration userscore = ArenaMatch.userscore;
	Configuration stats = ArenaMatch.stats;
	
	Economy vault = ArenaMatch.getPluginEconomy();
	
	
	public Inventory CreateProfile(Player p) 
	{
		ItemStack item = new ItemStack(0);
		Inventory inv = Bukkit.createInventory(null, 54, "§b" + p.getName() + "님의 프로필");
		String title = MCMANYtitle.getPlayerTitle(p).replace("&", "§");
		if(title == null || title.isEmpty() || title.equals("") || title.equals("null"))
		{
			title = "§7착용중인 칭호가 없습니다.";
		}
		for(int i = 0 ; i < 54 ; i++)
		{
			int typeId = Profile.getInt("profile." + i + ".id");
			int damage = Profile.getInt("profile." + i + ".meta");
			String name = Profile.getString("profile." + i + ".name");
			if(name == null)
			{
				name = "";
			}
			else
			{
				name = name.replaceAll("<playername>", p.getName());
			}
			List<String> lore = Profile.getStringList("profile." + i + ".lore");
			List<String> enchants = new ArrayList<String>();
			if(lore == null || lore.isEmpty())
			{
				lore = new ArrayList<String>();
			}
			lore = ArenaMatch.ChangeString("<FieldKillCount>", userscore.getInt("userscore." + p.getName() + ".FieldKillCount")+"", lore);
			lore = ArenaMatch.ChangeString("<DefaultWinCount>", userscore.getInt("userscore." + p.getName() + ".DeWinCount")+"" , lore); 
			
			int moduleDefualtWinCount = userscore.getInt("userscore." + p.getName() + ".DeWinCount") % 3;
			lore = ArenaMatch.ChangeString("<DefaultWinString>", ArenaMatch.getDefaultWinCount(moduleDefualtWinCount) , lore); 
			
			lore = ArenaMatch.ChangeString("<CompititionWinCount>", userscore.getInt("userscore." + p.getName() + ".WinCount")+"", lore);
			lore = ArenaMatch.ChangeString("<CompititionPoint>", userscore.getInt("userscore." + p.getName() + ".Score")+"", lore);
			lore = ArenaMatch.ChangeString("<RankingPercentString>", ArenaMatch.get_User_Per_Score(p)+"", lore);
			
			try {
				lore = ArenaMatch.ChangeString("<money>", (int) vault.getMoney(p.getName())+"", lore);
			} catch (UserDoesNotExistException e) {
				e.printStackTrace();
			}
			lore = ArenaMatch.ChangeString("<playertitle>", title, lore);
			if(userscore.getString("userscore." + p.getName() + ".UserMessage").equals("@"))
			{
				lore = ArenaMatch.ChangeString("<PlayerMessage>", "§b▶ §f상태메세지를 등록하지 않았습니다.", lore);				
			}
			else
			{
				lore = ArenaMatch.ChangeString("<PlayerMessage>", userscore.getString("userscore." + p.getName() + ".UserMessage").replace("&", "§"), lore); 
			}
			item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
			inv.setItem(i, item);
			switch(i)
			{
			case 3:
				item = p.getEquipment().getItemInOffHand();
				if(item.getTypeId() == 0)
				{
					
					item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
					continue;
				}
				inv.setItem(i, item);
				break;
			case 4:

				item = LibMain.createItem(typeId, damage, 1, name, this.EquipStat(p), "", enchants);
			
				inv.setItem(i, item);
				break;
			case 5:
				item = p.getItemInHand();
				if(item.getTypeId() == 0)
				{
					item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
					continue;
				}
				inv.setItem(i, item);
				break;
			case 10:
				item = p.getEquipment().getHelmet();
				if(item == null)
				{
					item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
					continue;
				}
				inv.setItem(i, item);
				break;
			case 19:
				item = p.getEquipment().getChestplate();
				if(item == null)
				{
					item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
					continue;
				}
				inv.setItem(i, item);
				break;
			case 22:
				boolean use = userscore.getBoolean("userscore." + p.getName() + ".item.use");
				if(use)
				{
					int id = userscore.getInt("userscore." + p.getName() + ".item.id");
					int meta = userscore.getInt("userscore." + p.getName() + ".item.meta");
					int amount = userscore.getInt("userscore." + p.getName() + ".item.amount");
					String DisplayName = userscore.getString("userscore." + p.getName() + ".item.name");
					String color = userscore.getString("userscore." + p.getName() + ".item.color");
					List<String> lore2 = userscore.getStringList("userscore." + p.getName() + ".item.lore");
					List<String> enchants2 = userscore.getStringList("userscore." + p.getName() + ".item.enchants");
					
					lore2.add("");
					lore2.add("§b▶ §3\"/앨범 등록\" §f명령어를 이용하여 등록하세요.");
					item = LibMain.createItem(id, meta, amount, DisplayName, lore2, color, enchants2);
					inv.setItem(i, item);
				}
				break;
			case 28:
				item = p.getEquipment().getLeggings();
				if(item == null)
				{
					item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
					continue;
				}
				inv.setItem(i, item);
				break;
			case 37:
				item = p.getEquipment().getBoots();
				if(item == null)
				{
					item = LibMain.createItem(typeId, damage, 1, name, lore, "", enchants);
					continue;
				}
				inv.setItem(i, item);
				break;
			}
		}
		return inv;
	}
	
	/*
	&f
	&f〔 &b이동 속도: &d@% &f〕
	&f
	&a▶ &c공격
	&f( &e추가 피해: &d+@ - +@ &f)
	&f( &c방어 무시 피해: &d+@ &f)
	&f( &4치명타 확률: &d+@% &f)
	&f( &4치명타 피해: &d+@% &f) - 기본 50%
	&f( &6생명력 흡수: &d+@% &f)
	&f( &c플레이어 추가 피해: &d+@ - +@ &f)
	&f( &c몬스터 피해: &d+@ &f)
	&f
	&a▶ &3방어
	&f( &3피해 감소: &d+@% &f)
	&f( &a추가 생명력: &d+@ &f) - 기본 100
	&f( &a생명력 재생: &d+@% &f)
	&f( &6공격 회피: &d+@% &f)
	&f( &3피해 무시: &d+@ &f)
	&f( &3플레이어 피해 무시: &d+@ &f)
	&f( &3몬스터 피해 무시: &d+@ &f)
	&f( &3화살 피해 무시: &d+@ &f)
	&f( &6피해 반사: &d+@% &f)
	&f
	&a▶ &2상태이상
	&f( &c화상: &d+@% &f)
	&f( &9빙결: &d+@% &f)
	&f( &2중독: &d+@% &f)
	&f( &7위더: &d+@% &f)
	&f( &8실명: &d+@% &f)
	&f( &5영혼 약탈: &d+@% &f)
	*/
	public List<String> EquipStat(LivingEntity Entity)
	{
		List<String> StatLore = new ArrayList<String>();
		double[] StatInfo = new double[100];
		for(int i = 0 ; i > StatInfo.length ; i++)
		{
			StatInfo[i] = 0;
		}
		
		/*
		 * 이동 속도		0
		 * 
		 * 추가 피해		1 - 2
		 * 방어 무시		3
		 * 치명타 확률		4
		 * 치명타 피해		5
		 * 생명력 흡수		6
		 * 플레이어 추가 피해	7 - 8
		 * 몬스터 피해		9 - 10
		 * 
		 * 피해 감소		11
		 * 추가 생명력		12
		 * 생명력 재생		13
		 * 공격 회피		14
		 * 피해 무시		15
		 * 피해 반사		16
		 * 
		 * 화상			17
		 * 빙결			18
		 * 중독			19
		 * 위더			20
		 * 실명			21
		 * 영혼 약탈		22
		 * 플레이어 피해 무시 23
		 * 몬스터 피해 무시 	24
		 * 화살 피해 무시	25
		*/
		
		//-----------------------------------------------------------
		
		/*
		 * 스탯이름            단일     범위
		 * 
		 * 이동 속도		0 , 1 - 2
		 * -> 공격
		 * 추가 피해		3 , 4 - 5
		 * 방어 무시		6 , 7 - 8
		 * 치명타 확률		9 , 10 - 11
		 * 치명타 피해		12 , 13 - 14 
		 * 생명력 흡수		15 , 16 - 17
		 * 플레이어 추가 피해	18 , 19 - 20
		 * 몬스터 피해		21 , 22 - 23   
		 * -> 방어    
		 * 피해 감소		24 , 25 - 26
		 * 추가 생명력		27 , 28 - 29
		 * 생명력 재생		30 , 31 - 32
		 * 공격 회피		33 , 34 - 35
		 * 피해 무시		36 , 37 - 38
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
		*/
		//-----------------------------------------------------------

		
		// 범위 스탯정보의 각 합이 0이라면 단일 정보만 존재하는 것이므로 단일 정보만 프로필에 기재되도록 수정해야 함.
		// 만일 범위 스탯과 단일 스탯이 동시에 존재한다면 최종 적으로 나타나야하는 값은 '최소 + 단일 ~ 최대 + 단일'  값이여야 함.
		// 범위와 단일 값을 모두 얻어야 하기 때문에 스탯 연산을 적어도 2번 해야함.
		// getstat -> StatCalculator -> getStatCase -> StatCalculator -> Range,nonRange
		
		//스탯을 얻어옴
		this.getstat(Entity, StatInfo);
		
		//스탯을 프로필에 직접 추가함
		StatLore.add("　");
		StatLore = MurgeProfileStatLore(StatLore,"　　§f• §b이동 속도: §d", StatInfo[0], StatInfo[1], StatInfo[2],false, 0.0d);
		//stats.add("§f• §b이동 속도: §d" + PlusMinus(StatInfo[0], false));
		StatLore.add("　");	
		
		//stats.add("§f〔 §b이동 속도: §d" + PlusMinus(StatInfo[0], false) + "% §f〕");
		
		StatLore.add("§f");
		StatLore.add("§a▶ §c공격");
		StatLore = MurgeProfileStatLore(StatLore,"　　§f• §e모든 피해 : §d", StatInfo[3], StatInfo[4], StatInfo[5],false, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore,"　　§f• §c방어 무시 피해: §d", StatInfo[6], StatInfo[7], StatInfo[8],false, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore,"　　§f• §4치명타 확률: §d", StatInfo[9], StatInfo[10], StatInfo[11],true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore,"　　§f• §4치명타 피해: §d ", StatInfo[12], StatInfo[13], StatInfo[14],true, 50.0d);
		StatLore = MurgeProfileStatLore(StatLore,"　　§f• §6생명력 흡수: §d", StatInfo[15], StatInfo[16], StatInfo[17], true, 0.0d);

		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c플레이어 피해: §d", StatInfo[18], StatInfo[19], StatInfo[20], false, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c몬스터 피해: §d", StatInfo[21], StatInfo[22], StatInfo[23], false, 0.0d);

		
		StatLore.add("§f");
		StatLore.add("§a▶ §3방어");
		
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §3피해 감소: §d", StatInfo[24], StatInfo[25], StatInfo[26], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §a생명력: §d100 ", StatInfo[27], StatInfo[28], StatInfo[29], false, 100.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §a생명력 재생: §d", StatInfo[30], StatInfo[31], StatInfo[32], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §6공격 회피: §d", StatInfo[33], StatInfo[34], StatInfo[35], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §3피해 무시: §d", StatInfo[36], StatInfo[37], StatInfo[38], false, 0.0d);  
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §6피해 반사: §d", StatInfo[39], StatInfo[40], StatInfo[41], true, 0.0d);  
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §3플레이어 피해 무시: §d", StatInfo[42], StatInfo[43], StatInfo[44], false, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §3몬스터 피해 무시: §d", StatInfo[45], StatInfo[46], StatInfo[47], false, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §3화살 피해 무시: §d", StatInfo[48], StatInfo[49], StatInfo[50], false, 0.0d);

		


		StatLore.add("§f");
		StatLore.add("§a▶ §2상태이상");
		
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c화상: §d", StatInfo[51], StatInfo[52], StatInfo[53], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c빙결: §d", StatInfo[54], StatInfo[55], StatInfo[56], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c중독: §d", StatInfo[57], StatInfo[58], StatInfo[59], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c위더: §d", StatInfo[60], StatInfo[61], StatInfo[62], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §c실명: §d", StatInfo[63], StatInfo[64], StatInfo[65], true, 0.0d);
		StatLore = MurgeProfileStatLore(StatLore, "　　§f• §5영혼 약탈: §d", StatInfo[66], StatInfo[67], StatInfo[68], true, 0.0d);

		return StatLore;
	}
	public double[] getstat(LivingEntity Entity, double[] StatInfo)
	{
		ItemStack RightHand = Entity.getEquipment().getItemInHand();
		ItemStack LeftHand = Entity.getEquipment().getItemInOffHand();
		ItemStack Helmet = Entity.getEquipment().getHelmet();
		ItemStack Chest = Entity.getEquipment().getChestplate();
		ItemStack Leggings = Entity.getEquipment().getLeggings();
		ItemStack Boots = Entity.getEquipment().getBoots();
		
		if(!(RightHand.getTypeId() == 0))
		{
			if(RightHand.hasItemMeta())
			{
				ItemMeta im = RightHand.getItemMeta();
				if(im.hasLore())
				{
					List<String> lores = im.getLore();
					StatInfo = StatCalculator(lores, StatInfo, false);
				}
			}
		}
		if(!(LeftHand.getTypeId() == 0))
		{
			if(LeftHand.hasItemMeta())
			{
				ItemMeta im = LeftHand.getItemMeta();
				if(im.hasLore())
				{
					List<String> lores = im.getLore();
					StatInfo = StatCalculator(lores, StatInfo, true);
				}
			}
		}
		if(Helmet != null)
		{
			if(Helmet.hasItemMeta())
			{
				ItemMeta im = Helmet.getItemMeta();
				if(im.hasLore())
				{
					List<String> lores = im.getLore();
					StatInfo = StatCalculator(lores, StatInfo, false);
				}
			}
		}
		if(Chest != null)
		{
			if(Chest.hasItemMeta())
			{
				ItemMeta im = Chest.getItemMeta();
				if(im.hasLore())
				{
					List<String> lores = im.getLore();
					StatInfo = StatCalculator(lores, StatInfo, false);
				}
			}
		}
		if(Leggings != null)
		{
			if(Leggings.hasItemMeta())
			{
				ItemMeta im = Leggings.getItemMeta();
				if(im.hasLore())
				{
					List<String> lores = im.getLore();
					StatInfo = StatCalculator(lores, StatInfo, false);
				}
			}
		}
		if(Boots != null)
		{
			if(Boots.hasItemMeta())
			{
				ItemMeta im = Boots.getItemMeta();
				if(im.hasLore())
				{
					List<String> lores = im.getLore();
					StatInfo = StatCalculator(lores, StatInfo, false);
				}
			}
		}
		return StatInfo;
	}
	public String PlusMinus(double d, boolean round)
	{
		String s = "";
		if(d < 0)
		{
			if(round)
			{
				d = Math.round(d*100)/100;
				int buf = (int) d;
				s = ""+buf;
			}
			else
			{
				d = Math.round(d*100);
				d = d/100;
				s = ""+d;
			}
			
		}
		else
		{
			// 0.499999  49.999 50.000 0.5 
			// 2.3 2300 2.3
			if(round)
			{
				d = Math.round(d*100)/100;
				int buf = (int) d;
				s = "+"+buf;
			}
			else
			{
				d = Math.round(d*100);
				d = d/100;
				s = "+"+d;
			}
		}
		return s.replaceAll("\\+", "+ ").replaceAll("\\-", "- ");
	}
	public double[] StatCalculator(List<String> lores, double[] StatInfo, boolean isOffHand)
	{
		for(String lore : lores)
		{
			lore = ChatColor.stripColor(lore);
			int Case = getStatCase(lore);
			if(Case != -1)
			{
				switch(Case)
				{
				case 0:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 0, 1, 2, true);

					}
					break;
				case 1:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 3, 4, 5, false);

					}
					break;
				case 2:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 6, 7, 8, false);

					}
					break;
				case 3:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 9, 10, 11, true);

					}
					break;
				case 4:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 12, 13, 14, true);

					}
					break;
				case 5:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 15, 16, 17, true);

					}
					break;
				case 6:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 18, 19, 29, false);
					}
					break;
				case 7:
					if(!isOffHand)
					{
						StatInfo = LoreContainString(StatInfo, lore, 21, 22, 23, false);
					}
					break;
				case 8:
						StatInfo = LoreContainString(StatInfo, lore, 24, 25, 26, true);
					
					break;
				case 9:
						StatInfo = LoreContainString(StatInfo, lore, 27, 28, 29, false);
					break;
				case 10:
						StatInfo = LoreContainString(StatInfo, lore, 30, 31, 32, true);
					break;
				case 11:
						StatInfo = LoreContainString(StatInfo, lore, 33, 32, 35, true);
					break;
				case 12:
						StatInfo = LoreContainString(StatInfo, lore, 36, 37, 38, false);
					break;
				case 13:
						StatInfo = LoreContainString(StatInfo, lore, 39, 40, 31, true);
					break;
				case 14:
						StatInfo = LoreContainString(StatInfo, lore, 42, 43, 44, true);
					break;
				case 15:
						StatInfo = LoreContainString(StatInfo, lore, 45, 46, 47, true);
					break;
				case 16:
						StatInfo = LoreContainString(StatInfo, lore, 48, 49, 50, true);
					break;
				case 17:
						StatInfo = LoreContainString(StatInfo, lore, 51, 52, 53, true);
					break;
				case 18:
						StatInfo = LoreContainString(StatInfo, lore, 54, 55, 56, true);
					break;
				case 19:
						StatInfo = LoreContainString(StatInfo, lore, 57, 58, 59, true);
					break;
				case 20:
						StatInfo = LoreContainString(StatInfo, lore, 60, 61, 62, false);
					break;
				case 21:
						StatInfo = LoreContainString(StatInfo, lore, 63, 64, 65, false);
					break;
				case 22:
						StatInfo = LoreContainString(StatInfo, lore, 66, 67, 68, false);
					break;
				}
			}
		}
		return StatInfo;
	}
	public int getStatCase(String s)
	{
		//&f⦁  &3피해 감소 : &d+6.5%'
		int b = -1;
		if(s.contains(":"))
		{
			String[] split = s.split(":");
			String statname = split[0].substring(split[0].indexOf("⦁")+2,split[0].length()-1 );
			// 읽어들인 스탯의 이름으로부터 숫자를 구함. 이후에 스탯 배열을 참고할때 사용함.
			
			Set<String> keys = stats.getConfigurationSection("stats").getKeys(false);
			int statvalue = 0;
			for(String key : keys)
			{
				if(statname.equals(stats.getString("stats." + key)))
				{
					return statvalue;
				}
				statvalue++;
			}
		}
		
		return b;
	}
	public double nonRange(double ValueInt, String s, boolean percent)
	{
		//( 치명타 확률: +5.1% )
		//rxwv
		//0123
		
		String[] split = s.split(": ");
		String ValueString = split[1];
		if(percent)
		{
			if(ValueString.contains("+"))
			{
				ValueString = ValueString.substring(ValueString.indexOf("+"), ValueString.indexOf("%"));
				ValueInt += Double.parseDouble(ValueString.replace("+", ""));
			}
			else if(ValueString.contains("-"))
			{
				ValueString = ValueString.substring(ValueString.indexOf("-"), ValueString.indexOf("%"));
				ValueInt -= Double.parseDouble(ValueString.replace("-", ""));
			}
		}
		else
		{
			if(ValueString.contains("+"))
			{
				ValueString = ValueString.substring(ValueString.indexOf("+"), ValueString.length());
				ValueInt += Double.parseDouble(ValueString.replace("+", ""));
			}
			else if(ValueString.contains("-"))
			{
				ValueString = ValueString.substring(ValueString.indexOf("-"), ValueString.length());
				ValueInt -= Double.parseDouble(ValueString.replace("-", ""));
			}
		}
		
		return ValueInt;
	}
	public double[] Range(double ValueInt1, double ValueInt2, String s)
	{

		//( 추가 피해: +@ - +@ )
		double[] i = new double[2];
		String[] split = s.split(": ");
		String[] split2 = split[1].split(" ~ ");
		//split2[1] = split2[1];
		if(split2[0].contains("+"))
		{
			String ValueString = split2[0].substring(split2[0].indexOf("+"));
			ValueInt1 += Double.parseDouble(ValueString.replace("+", ""));
		}
		else if(split2[0].contains("-"))
		{
			String ValueString = split2[0].substring(split2[0].indexOf("-"));
			ValueInt1 += Double.parseDouble(ValueString.replace("-", ""));
		}
		if(split2[1].contains("+"))
		{
			String ValueString = split2[1].substring(split2[1].indexOf("+"), split2[1].length());
			ValueInt2 += Double.parseDouble(ValueString.replace("+", ""));
		}
		else if(split2[1].contains("-"))
		{
			String ValueString = split2[1].substring(split2[1].indexOf("-"), split2[1].length());
			ValueInt2 += Double.parseDouble(ValueString.replace("-", ""));
		}
		i[0] = ValueInt1;
		i[1] = ValueInt2;
		
		return i;
	}
	public List<String> MurgeProfileStatLore(List<String> StatLore, String Prefix, double Single_value, double Min_Value, double Max_value,boolean percent,double offset)
	{
		// prefix = "　　§f• §b이동 속도 : §d";
		String return_string = "";
		if((Min_Value + Max_value + Single_value) == 0d)
		{
			if(offset != 0.0d)
			{
				return_string = Prefix + PlusMinus(offset, false);
				if(percent)
				{
					return_string = return_string + "%";
				}
				StatLore.add(return_string);
				return StatLore;	
			}
		}
		else if((Min_Value + Max_value) == 0d)
		{
			return_string =  Prefix + PlusMinus(Single_value, false);
			if(percent)
			{
				return_string = return_string + "%";
			}
			StatLore.add(return_string);
			return StatLore;
		}
		else if ((Min_Value + Max_value) != 0d)
		{
			
			Min_Value += Single_value;
			Max_value += Single_value;
			
			return_string = Prefix + PlusMinus(Min_Value, false) + " ~ " + PlusMinus(Max_value, false);
			if(percent)
			{
				return_string =Prefix + PlusMinus(Min_Value, false) + "% ~ " + PlusMinus(Max_value, false) + "%"; 
			}
			StatLore.add(return_string);
			return StatLore;
		}
		
		return StatLore;
	}
	public double[] LoreContainString(double[] StatInfo, String lore, int Single_value, int Min_Value, int Max_value, boolean percent)
	{
		Bukkit.broadcastMessage(Min_Value + ", " + Max_value + ", " + Single_value);
		if(lore.contains("~"))
		{
			double[] ranges = this.Range(StatInfo[Min_Value], StatInfo[Max_value], lore);
			StatInfo[Min_Value] = ranges[0];
			StatInfo[Max_value] = ranges[1];
		}
		else
		{
			StatInfo[Single_value] = this.nonRange(StatInfo[Single_value], lore, percent);
		}
		
		return StatInfo;
		
	}
}
