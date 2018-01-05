package com.Moon_Eclipse.ArenaMatch;

import java.util.ArrayList;
import java.util.List;

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
		List<String> stats = new ArrayList<String>();
		double[] StatInfo = new double[30];
		
		/*
		 * 이동 속도		0
		 * 
		 * 추가 피해		1 - 2
		 * 방어 무시		3
		 * 치명타 확률		4
		 * 치명타 피해		5
		 * 생명력 흡수		6
		 * 플레이어 추가 피해	7 - 8
		 * 몬스터 피해		9     
		 * 				10번은 본래 몬스터 피해의 최대범위 표현인자였으나 차후 수정에 의해 제거되고 몬스터 피해는 단일 피해가 됨. 17.10.27
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
		this.getstat(Entity, StatInfo);
		
		stats.add("§f");
		stats.add("§f〔 §b이동 속도: §d" + PlusMinus(StatInfo[0], false) + "% §f〕");
		stats.add("§f");
		stats.add("§a▶ §c공격");
		if((StatInfo[1] + StatInfo[2]) != 0d)
		{
			stats.add("§f( §e추가 피해: §d" + PlusMinus(StatInfo[1], true) + " - " + PlusMinus(StatInfo[2], true) + " §f)");
		}
		if(StatInfo[3] != 0d)
		{
			stats.add("§f( §c방어 무시 피해: §d"+ PlusMinus(StatInfo[3], true) +" §f)");
		}
		if(StatInfo[4] != 0d)
		{
			stats.add("§f( §4치명타 확률: §d" + PlusMinus(StatInfo[4], false) + "% §f)");
		}
		if(StatInfo[5] != 0d)
		{
			stats.add("§f( §4치명타 피해: §d50 " + PlusMinus((StatInfo[5]), false) +"% §f)");
		}
		else
		{
			stats.add("§f( §4치명타 피해: §d50% §f)");
		}
		if(StatInfo[6] != 0d)
		{
			String newStat = PlusMinus((StatInfo[6]), false);
			stats.add("§f( §6생명력 흡수: §d" + newStat.replaceAll("\\+", "+ ").replaceAll("\\-", "- ") + "% §f)"); // 문장의 제일 처음에 + 나오면 에러가 발생하므로 \\를 추가
		}
		if((StatInfo[7] + StatInfo[8]) != 0d)
		{
			stats.add("§f( §c플레이어 피해: §d" + PlusMinus(StatInfo[7], true) + " - " + PlusMinus(StatInfo[8], true) + " §f)");
		}
		if((StatInfo[9] /*+ StatInfo[10]*/) != 0d)
		{
			stats.add("§f( §c몬스터 피해: §d" + PlusMinus(StatInfo[9], true)/* + " - " + PlusMinus(StatInfo[10], true)*/ + " §f)");
		}
		stats.add("§f");
		stats.add("§a▶ §3방어");
		if(StatInfo[11] != 0d)
		{
			stats.add("§f( §3피해 감소: §d" + PlusMinus(StatInfo[11], false) + "% §f)");
		}
		if(StatInfo[12] != 0d)
		{
			String newStat = PlusMinus((StatInfo[12]), true);
			stats.add("§f( §a생명력: §d100 "+ newStat.replaceAll("\\+", "+ ").replaceAll("\\-", "- ") + " §f)");
		}
		else
		{
			stats.add("§f( §a생명력: §d100 §f)");
		}
		if(StatInfo[13] != 0d)
		{
			stats.add("§f( §a생명력 재생: §d"+ PlusMinus(StatInfo[13], false) + "% §f)");
		}
		if(StatInfo[14] != 0d)
		{
			stats.add("§f( §6공격 회피: §d" + PlusMinus(StatInfo[14], false) + "% §f)");
		}
		if(StatInfo[15] != 0d)
		{
			stats.add("§f( §3피해 무시: §d" + PlusMinus(StatInfo[15], true) + " §f)");
		}
		if(StatInfo[23] != 0d)
		{
			stats.add("§f( §3플레이어 피해 무시: §d" + PlusMinus(StatInfo[23], true) + " §f)");
		}
		if(StatInfo[24] != 0d)
		{
			stats.add("§f( §3몬스터 피해 무시: §d" + PlusMinus(StatInfo[24], true) + " §f)");
		}
		if(StatInfo[25] != 0d)
		{
			stats.add("§f( §3화살 피해 무시: §d" + PlusMinus(StatInfo[25], true) + " §f)");
		}
		if(StatInfo[16] != 0d)
		{
			stats.add("§f( §6피해 반사: §d" + PlusMinus(StatInfo[16], false) + "% §f)");
		}
		stats.add("§f");
		stats.add("§a▶ §2상태이상");
		if(StatInfo[17] != 0d)
		{
			stats.add("§f( §c화상: §d" + PlusMinus(StatInfo[17], false) + "% §f)");
		}
		if(StatInfo[18] != 0d)
		{
			stats.add("§f( §c빙결: §d" + PlusMinus(StatInfo[18], false) + "% §f)");
		}
		if(StatInfo[19] != 0d)
		{
			stats.add("§f( §c중독: §d" + PlusMinus(StatInfo[19], false) + "% §f)");
		}
		if(StatInfo[20] != 0d)
		{
			stats.add("§f( §c위더: §d" + PlusMinus(StatInfo[20], false) + "% §f)");
		}
		if(StatInfo[21] != 0d)
		{
			stats.add("§f( §c실명: §d" + PlusMinus(StatInfo[21], false) + "% §f)");
		}
		if(StatInfo[22] != 0d)
		{
			stats.add("§f( §5영혼 약탈: §d" + PlusMinus(StatInfo[22], false) + "% §f)");
		}
		
		
		
		
		
		
		
		return stats;
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
		return s;
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
					StatInfo[0] = this.nonRange(StatInfo[0], lore, true);
					break;
				case 1:
					if(!isOffHand)
					{
						double[] ranges = this.Range(StatInfo[1], StatInfo[2], lore);
						StatInfo[1] = ranges[0];
						StatInfo[2] = ranges[1];
					}
					break;
				case 2:
					if(!isOffHand)
					{
						StatInfo[3] = this.nonRange(StatInfo[3], lore, false);
					}
					break;
				case 3:
					if(!isOffHand)
					{
						StatInfo[4] = this.nonRange(StatInfo[4], lore, true);
					}
					break;
				case 4:
					if(!isOffHand)
					{
						StatInfo[5] = this.nonRange(StatInfo[5], lore, true);
					}
					break;
				case 5:
					if(!isOffHand)
					{
						StatInfo[6] = this.nonRange(StatInfo[6], lore, true);
					}
					break;
				case 6:
					if(!isOffHand)
					{
						double[] ranges = this.Range(StatInfo[7], StatInfo[8], lore);
						StatInfo[7] = ranges[0];
						StatInfo[8] = ranges[1];
					}
					break;
				case 7:
					if(!isOffHand)
					{
						/*
						double[] ranges = this.Range(StatInfo[9], StatInfo[10], lore);
						StatInfo[9] = ranges[0];
						StatInfo[10] = ranges[1];
						*/
						StatInfo[9] = this.nonRange(StatInfo[9], lore, false);
					}
					break;
				case 8:
					StatInfo[11] = this.nonRange(StatInfo[11], lore, true);
					break;
				case 9:
					StatInfo[12] = this.nonRange(StatInfo[12], lore, false);
					break;
				case 10:
					StatInfo[13] = this.nonRange(StatInfo[13], lore, true);
					break;
				case 11:
					StatInfo[14] = this.nonRange(StatInfo[14], lore, true);
					break;
				case 12:
					StatInfo[15] = this.nonRange(StatInfo[15], lore, false);
					break;
				case 13:
					StatInfo[16] = this.nonRange(StatInfo[16], lore, true);
					break;
				case 14:
					StatInfo[17] = this.nonRange(StatInfo[17], lore, true);
					break;
				case 15:
					StatInfo[18] = this.nonRange(StatInfo[18], lore, true);
					break;
				case 16:
					StatInfo[19] = this.nonRange(StatInfo[19], lore, true);
					break;
				case 17:
					StatInfo[20] = this.nonRange(StatInfo[20], lore, true);
					break;
				case 18:
					StatInfo[21] = this.nonRange(StatInfo[21], lore, true);
					break;
				case 19:
					StatInfo[22] = this.nonRange(StatInfo[22], lore, true);
					break;
				case 20:
					StatInfo[23] = this.nonRange(StatInfo[23], lore, false);
					break;
				case 21:
					StatInfo[24] = this.nonRange(StatInfo[24], lore, false);
					break;
				case 22:
					StatInfo[25] = this.nonRange(StatInfo[25], lore, false);
					break;
				}
			}
		}
		return StatInfo;
	}
	public int getStatCase(String s)
	{
		//( 플레이어 추가 피해: +@ - +@ )
		int b = -1;
		if(s.contains(":"))
		{
			String[] split = s.split(":");
			String statname = split[0].substring(2);
			if(statname.equals("이동 속도"))
			{
				b = 0;
			}
			if(statname.equals("추가 피해"))
			{
				b = 1;
			}
			if(statname.equals("방어 무시 피해"))
			{
				b = 2;
			}
			if(statname.equals("치명타 확률"))
			{
				b = 3;
			}
			if(statname.equals("치명타 피해"))
			{
				b = 4;
			}
			if(statname.equals("생명력 흡수"))
			{
				b = 5;
			}
			if(statname.equals("플레이어 피해"))
			{
				b = 6;
			}
			if(statname.equals("몬스터 피해"))
			{
				b = 7;
			}
			if(statname.equals("피해 감소"))
			{
				b = 8;
			}
			if(statname.equals("추가 생명력"))
			{
				b = 9;
			}
			if(statname.equals("생명력 재생"))
			{
				b = 10;
			}
			if(statname.equals("공격 회피"))
			{
				b = 11;
			}
			if(statname.equals("피해 무시"))
			{
				b = 12;
			}
			if(statname.equals("피해 반사"))
			{
				b = 13;
			}
			if(statname.equals("화상"))
			{
				b = 14;
			}
			if(statname.equals("빙결"))
			{
				b = 15;
			}
			if(statname.equals("중독"))
			{
				b = 16;
			}
			if(statname.equals("위더"))
			{
				b = 17;
			}
			if(statname.equals("실명"))
			{
				b = 18;
			}
			if(statname.equals("영혼 약탈"))
			{
				b = 19;
			}
			if(statname.equals("플레이어 피해 무시"))
			{
				b = 20;
			}
			if(statname.equals("몬스터 피해 무시"))
			{
				b = 21;
			}
			if(statname.equals("화살 피해 무시"))
			{
				b = 22;
			}
		}
		return b;
	}
	public double nonRange(double ValueInt, String s, boolean percent)
	{
		//( 치명타 확률: +5.1% )
		String[] split = s.split(": ");
		String ValueString = split[1].replace("〕", ")");
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
				ValueString = ValueString.substring(ValueString.indexOf("+"), ValueString.indexOf(")")-1);
				ValueInt += Double.parseDouble(ValueString.replace("+", ""));
			}
			else if(ValueString.contains("-"))
			{
				ValueString = ValueString.substring(ValueString.indexOf("-"), ValueString.indexOf(")")-1);
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
		String[] split2 = split[1].split(" - ");
		split2[1] = split2[1].replace("〕", ")");
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
			String ValueString = split2[1].substring(split2[1].indexOf("+"), split2[1].indexOf(" )"));
			ValueInt2 += Double.parseDouble(ValueString.replace("+", ""));
		}
		else if(split2[1].contains("-"))
		{
			String ValueString = split2[1].substring(split2[1].indexOf("-"), split2[1].indexOf(" )"));
			ValueInt2 += Double.parseDouble(ValueString.replace("-", ""));
		}
		i[0] = ValueInt1;
		i[1] = ValueInt2;
		
		return i;
	}
	
}
