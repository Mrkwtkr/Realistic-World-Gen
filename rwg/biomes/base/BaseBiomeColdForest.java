package rwg.biomes.base;

import rwg.data.VillageMaterialData;
import rwg.data.VillageMaterials;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeColdForest extends BiomeGenBase
{
	public BaseBiomeColdForest(int id) 
	{
		super(id);
		setTemperatureRainfall(0.5f, 0.4f);
		setBiomeName("RWG - BiomeType: Cold Forest");
		spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 1, 2));
	}
}
