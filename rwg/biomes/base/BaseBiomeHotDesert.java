package rwg.biomes.base;

import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeHotDesert extends BiomeGenBase
{
	public BaseBiomeHotDesert(int id) 
	{
		super(id);
		setTemperatureRainfall(1f, 0f);
		setBiomeName("Realistic World Gen");
		spawnableCreatureList.clear();
	}
}
