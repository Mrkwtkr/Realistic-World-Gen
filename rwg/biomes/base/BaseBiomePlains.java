package rwg.biomes.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomePlains extends BiomeGenBase
{
	public BaseBiomePlains(int id) 
	{
		super(id);
		setTemperatureRainfall(0.6f, 0.4f);
		setBiomeName("RWG - BiomeType: Plains");
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 3, 2, 3));
	}
}
