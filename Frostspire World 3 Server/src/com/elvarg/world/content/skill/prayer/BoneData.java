package com.elvarg.world.content.skill.prayer;

public enum BoneData {
	NORMAL_BONES(526, 4),
	WOLF_BONES(2859, 4),
	BAT_BONES(530, 4),
	BIG_BONES(532, 18),
	BABYDRAGON_BONES(534, 30),
	DRAGON_BONES(536, 72),
	DAGG_BONES(6729, 75),
	OURG_BONES(4834, 100),
	LONG_BONE(10976, 100),
	SKELETAL_WYVERN_BONES(6812, 95),
	LAVA_DRAGON_BONES(11943, 85);

	private int id;
	private int experience;

	private BoneData(int id, int experience) {
		this.id = id;
		this.experience = experience;
	}

	public int getId() {
		return id;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public static BoneData getBone(int id) {
		for(BoneData bone : BoneData.values()) {
			if(bone.getId() == id) {
				return bone;
			}
		}
		return null;
	}
}
