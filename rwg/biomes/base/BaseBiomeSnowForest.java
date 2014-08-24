package rwg.biomes.base;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeSnowForest extends BiomeGenBase
{
	public BaseBiomeSnowForest(int id) 
	{
		super(id);
		setTemperatureRainfall(0.0f, 0.1f);
		setBiomeName("Realistic World Gen");
		spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
	}
}
