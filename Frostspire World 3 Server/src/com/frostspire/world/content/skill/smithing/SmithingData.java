package com.frostspire.world.content.skill.smithing;

import com.frostspire.world.model.Item;

public enum SmithingData {
	BRONZE_DAGGER(new Item(1205), new Item(2349), 1, 1, 12),
	BRONZE_SWORD(new Item(1277), new Item(2349), 1, 4, 12),
	BRONZE_SCIMITAR(new Item(1321), new Item(2349), 2, 5, 25),
	BRONZE_LONGSWORD(new Item(1291), new Item(2349), 2, 6, 25),
	BRONZE_2H(new Item(1307), new Item(2349), 3, 14, 37),
	
	BRONZE_AXE(new Item(1351), new Item(2349), 1, 1, 12),
	BRONZE_MACE(new Item(1422), new Item(2349), 1, 2, 12),
	BRONZE_WARHAMMER(new Item(1337), new Item(2349), 3, 9, 37),
	BRONZE_BATTLEAXE(new Item(1375), new Item(2349), 3, 10, 37),
	BRONZE_CLAWS(new Item(3095), new Item(2349), 2, 13, 25),
	
	BRONZE_CHAINBODY(new Item(1103), new Item(2349), 3, 11, 37),
	BRONZE_PLATELEGS(new Item(1075), new Item(2349), 3, 16, 37),
	BRONZE_PLATESKIRT(new Item(1087), new Item(2349), 3, 16, 37),
	BRONZE_PLATEBODY(new Item(1117), new Item(2349), 5, 18, 62),
	
	BRONZE_MED_HELM(new Item(1139), new Item(2349), 1, 3, 12),
	BRONZE_FULL_HELM(new Item(1155), new Item(2349), 2, 7, 25),
	BRONZE_SQ_SHIELD(new Item(1173), new Item(2349), 2, 8, 25),
	BRONZE_KITESHIELD(new Item(1189), new Item(2349), 3, 12, 37),
	BRONZE_NAILS(new Item(4819, 15), new Item(2349), 1, 4, 12),
	
	BRONZE_DART_TIPS(new Item(819, 10), new Item(2349),1, 4, 12),
	BRONZE_ARROW_TIPS(new Item(39, 15), new Item(2349),1, 5, 12),
	BRONZE_THROWING_KNIVES(new Item(864, 5), new Item(2349),1, 7, 12),
	BRONZE_BOLTS(new Item(9375, 10), new Item(2349),1, 3, 12),
	
	IRON_DAGGER(new Item(1203), new Item(2351),1, 15, 25),
	IRON_SWORD(new Item(1279), new Item(2351),1, 19, 25),
	IRON_SCIMITAR(new Item(1323), new Item(2351),2, 20, 50),
	IRON_LONGSWORD(new Item(1293), new Item(2351),2, 21, 50),
	IRON_2H(new Item(1309), new Item(2351),3, 29, 75),
	
	IRON_AXE(new Item(1349), new Item(2351),1, 16, 25),
	IRON_MACE(new Item(1420), new Item(2351),1, 17, 25),
	IRON_WARHAMMER(new Item(1335), new Item(2351),3, 24, 75),
	IRON_BATTLEAXE(new Item(1363), new Item(2351),3, 25, 75),
	IRON_CLAWS(new Item(3096), new Item(2351),2, 28, 50),
	
	IRON_CHAINBODY(new Item(1101), new Item(2351),3, 26, 75),
	IRON_PLATELEGS(new Item(1067), new Item(2351),3, 31, 75),
	IRON_PLATESKIRT(new Item(1081), new Item(2351),3, 31, 75),
	IRON_PLATEBODY(new Item(1115), new Item(2351),5, 33, 125),
	
	IRON_MED_HELM(new Item(1137), new Item(2351),1, 18, 25),
	IRON_FULL_HELM(new Item(1153), new Item(2351),2, 22, 50),
	IRON_SQ_SHIELD(new Item(1175), new Item(2351),2, 23, 50),
	IRON_KITESHIELD(new Item(1191), new Item(2351),3, 27, 75),
	IRON_NAILS(new Item(4820, 15), new Item(2351),1, 19, 25),
	
	IRON_DART_TIPS(new Item(820, 10), new Item(2351),1, 19, 25),
	IRON_ARROW_TIPS(new Item(40, 15), new Item(2351),1, 20, 25),
	IRON_THROWING_KNIVES(new Item(863, 5), new Item(2351),1, 22, 25),
	IRON_BOLTS(new Item(9377, 10), new Item(2351),1, 18, 25),
	
	STEEL_DAGGER(new Item(1207), new Item(2353),1, 30, 37),
	STEEL_SWORD(new Item(1281), new Item(2353),1, 34, 37),
	STEEL_SCIMITAR(new Item(1325), new Item(2353),2, 35, 75),
	STEEL_LONGSWORD(new Item(1295), new Item(2353),2, 36, 75),
	STEEL_2H(new Item(1311), new Item(2353),3, 44, 112),
	
	STEEL_AXE(new Item(1353), new Item(2353),1, 31, 37),
	STEEL_MACE(new Item(1424), new Item(2353),1, 32, 37),
	STEEL_WARHAMMER(new Item(1339), new Item(2353),3, 39, 112),
	STEEL_BATTLEAXE(new Item(1365), new Item(2353),3, 40, 112),
	STEEL_CLAWS(new Item(3097), new Item(2353),2, 43, 75),
	
	STEEL_CHAINBODY(new Item(1105), new Item(2353),3, 41, 112),
	STEEL_PLATELEGS(new Item(1069), new Item(2353),3, 46, 112),
	STEEL_PLATESKIRT(new Item(1083), new Item(2353),3, 46, 112),
	STEEL_PLATEBODY(new Item(1119), new Item(2353),5, 48, 187),
	
	STEEL_MED_HELM(new Item(1141), new Item(2353),1, 33, 37),
	STEEL_FULL_HELM(new Item(1157), new Item(2353),2, 37, 75),
	STEEL_SQ_SHIELD(new Item(1177), new Item(2353),2, 38, 75),
	STEEL_KITESHIELD(new Item(1193), new Item(2353),3, 42, 112),
	STEEL_NAILS(new Item(1539, 15), new Item(2353),1, 34, 37),
	
	STEEL_DART_TIPS(new Item(821, 10), new Item(2353),1, 34, 37),
	STEEL_ARROW_TIPS(new Item(41, 15), new Item(2353),1, 35, 37),
	STEEL_THROWING_KNIVES(new Item(865, 5), new Item(2353),1, 37, 37),
	STEEL_BOLTS(new Item(9378, 10), new Item(2353),1, 33, 37),
	
	MITHRIL_DAGGER(new Item(1209), new Item(2359),1, 50, 50),
	MITHRIL_SWORD(new Item(1285), new Item(2359),1, 54, 50),
	MITHRIL_SCIMITAR(new Item(1329), new Item(2359),2, 55, 100),
	MITHRIL_LONGSWORD(new Item(1299), new Item(2359),2, 56, 100),
	MITHRIL_2H(new Item(1315), new Item(2359),3, 64, 150),
	
	MITHRIL_AXE(new Item(1355), new Item(2359),1, 51, 50),
	MITHRIL_MACE(new Item(1428), new Item(2359),1, 52, 50),
	MITHRIL_WARHAMMER(new Item(1343), new Item(2359),3, 59, 150),
	MITHRIL_BATTLEAXE(new Item(1369), new Item(2359),3, 60, 150),
	MITHRIL_CLAWS(new Item(3099), new Item(2359),2, 63, 100),
	
	MITHRIL_CHAINBODY(new Item(1109), new Item(2359),3, 61, 150),
	MITHRIL_PLATELEGS(new Item(1071), new Item(2359),3, 66, 150),
	MITHRIL_PLATESKIRT(new Item(1085), new Item(2359),3, 66, 150),
	MITHRIL_PLATEBODY(new Item(1121), new Item(2359),5, 68, 250),
	
	MITHRIL_MED_HELM(new Item(1143), new Item(2359),1, 53, 50),
	MITHRIL_FULL_HELM(new Item(1159), new Item(2359),2, 57, 100),
	MITHRIL_SQ_SHIELD(new Item(1181), new Item(2359),2, 58, 100),
	MITHRIL_KITESHIELD(new Item(1197), new Item(2359),3, 62, 150),
	MITHRIL_NAILS(new Item(4822, 15), new Item(2359),1, 54, 50),
	
	MITHRIL_DART_TIPS(new Item(822, 10), new Item(2359),1, 54, 50),
	MITHRIL_ARROW_TIPS(new Item(42, 15), new Item(2359),1, 55, 50),
	MITHRIL_THROWING_KNIVES(new Item(866, 5), new Item(2359),1, 57, 50),
	MITHRIL_BOLTS(new Item(9379, 10), new Item(2359),1, 53, 50),
	
	ADAMANT_DAGGER(new Item(1211), new Item(2361),1, 70, 62),
	ADAMANT_SWORD(new Item(1287), new Item(2361),1, 74, 62),
	ADAMANT_SCIMITAR(new Item(1331), new Item(2361),2, 75, 125),
	ADAMANT_LONGSWORD(new Item(1301), new Item(2361),2, 76, 125),
	ADAMANT_2H(new Item(1317), new Item(2361),3, 84, 187),
	
	ADAMANT_AXE(new Item(1357), new Item(2361),1, 71, 62),
	ADAMANT_MACE(new Item(1430), new Item(2361),1, 72, 62),
	ADAMANT_WARHAMMER(new Item(1345), new Item(2361),3, 79, 187),
	ADAMANT_BATTLEAXE(new Item(1371), new Item(2361),3, 80, 187),
	ADAMANT_CLAWS(new Item(3100), new Item(2361),2, 83, 125),
	
	ADAMANT_CHAINBODY(new Item(1111), new Item(2361),3, 81, 187),
	ADAMANT_PLATELEGS(new Item(1073), new Item(2361),3, 86, 187),
	ADAMANT_PLATESKIRT(new Item(1091), new Item(2361),3, 86, 187),
	ADAMANT_PLATEBODY(new Item(1123), new Item(2361),5, 88, 312),
	
	ADAMANT_MED_HELM(new Item(1145), new Item(2361),1, 73, 62),
	ADAMANT_FULL_HELM(new Item(1161), new Item(2361),2, 77, 125),
	ADAMANT_SQ_SHIELD(new Item(1183), new Item(2361),2, 78, 125),
	ADAMANT_KITESHIELD(new Item(1199), new Item(2361),3, 82, 187),
	ADAMANT_NAILS(new Item(4823, 15), new Item(2361),1, 74, 62),
	
	ADAMANT_DART_TIPS(new Item(823, 10), new Item(2361),1, 74, 62),
	ADAMANT_ARROW_TIPS(new Item(43, 15), new Item(2361),1, 75, 62),
	ADAMANT_THROWING_KNIVES(new Item(867, 5), new Item(2361),1, 77, 62),
	ADAMANT_BOLTS(new Item(9380, 10), new Item(2361),1, 73, 62),
	
	RUNE_DAGGER(new Item(1213), new Item(2363),1, 85, 75),
	RUNE_SWORD(new Item(1289), new Item(2363),1, 89, 75),
	RUNE_SCIMITAR(new Item(1333), new Item(2363),2, 90, 150),
	RUNE_LONGSWORD(new Item(1303), new Item(2363),2, 91, 150),
	RUNE_2H(new Item(1319), new Item(2363),3, 99, 225),
	
	RUNE_AXE(new Item(1359), new Item(2363),1, 86, 75),
	RUNE_MACE(new Item(1432), new Item(2363),1, 87, 75),
	RUNE_WARHAMMER(new Item(1347), new Item(2363),3, 94, 225),
	RUNE_BATTLEAXE(new Item(1373), new Item(2363),3, 95, 225),
	RUNE_CLAWS(new Item(3101), new Item(2363),2, 98, 150),
	
	RUNE_CHAINBODY(new Item(1113), new Item(2363),3, 96, 225),
	RUNE_PLATELEGS(new Item(1079), new Item(2363),3, 99, 225),
	RUNE_PLATESKIRT(new Item(1093), new Item(2363),3, 99, 225),
	RUNE_PLATEBODY(new Item(1127), new Item(2363),5, 99, 375),
	
	RUNE_MED_HELM(new Item(1147), new Item(2363),1, 88, 75),
	RUNE_FULL_HELM(new Item(1163), new Item(2363),2, 92, 150),
	RUNE_SQ_SHIELD(new Item(1185), new Item(2363),2, 93, 150),
	RUNE_KITESHIELD(new Item(1201), new Item(2363),3, 97, 225),
	RUNE_NAILS(new Item(4824, 15), new Item(2363),1, 89, 75),
	
	RUNE_DART_TIPS(new Item(824, 10), new Item(2363),1, 89, 75),
	RUNE_ARROW_TIPS(new Item(44, 15), new Item(2363),1, 90, 75),
	RUNE_THROWING_KNIVES(new Item(868, 5), new Item(2363),1, 92, 75),
	RUNE_BOLTS(new Item(9381, 10), new Item(2363),1, 88, 75);
	
	private Item item;
	private Item bar;
	private int barsRequired;
	private int levelRequired;
	private int xp;
	
	private SmithingData(Item item, Item bar, int barsRequired, int levelRequired, int xp) {
		this.item = item;
		this.bar = bar;
		this.barsRequired = barsRequired;
		this.levelRequired = levelRequired;
		this.xp = xp;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Item getBar() {
		return bar;
	}
	
	public int getBarsRequired() {
		return barsRequired;
	}
	
	public int getLevelRequired() {
		return levelRequired;
	}
	
	public int getExperience() {
		return xp;
	}
	
	public static SmithingData getItem(int itemId) {
		for(SmithingData item : SmithingData.values()) {
			if(item.getItem().getId() == itemId) {
				return item;
			}
		}
		return null;
	}
	
	public static SmithingData[] getItems(String itemName) {
		SmithingData[] items = new SmithingData[23];
		int index = 0;
		for(SmithingData item : SmithingData.values()) {
			if(item.name().substring(0, 3).equalsIgnoreCase(itemName)) {
				items[index] = item;
				index++;
			}
		}
		return items;
	}
	
}
