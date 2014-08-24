package rwg.biomes.base;

import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeSnowDesert extends BiomeGenBase
{
	public BaseBiomeSnowDesert(int id) 
	{
		super(id);
		setTemperatureRainfall(0.0f, 0.1f);
		setBiomeName("Realistic World Gen");
		spawnableCreatureList.clear();
	}
}
