package com.elvarg.world.content.skill.fletching.fletchable;

import com.elvarg.world.model.Item;

public interface Fletchable {
	
	public int getAnimation();
	
	public Item getUse();
	
	public Item getWith();
	
	public FletchableItem[] getFletchableItems();
	
	public Item[] getIngediants();
	
	public String getProductionMessage();
}
