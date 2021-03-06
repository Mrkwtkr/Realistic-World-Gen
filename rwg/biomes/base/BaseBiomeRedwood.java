package rwg.biomes.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeRedwood extends BiomeGenBase
{
	public BaseBiomeRedwood(int id) 
	{
		super(id);
		setTemperatureRainfall(0.7f, 0.6f);
		setBiomeName("RWG - BiomeType: Redwood");
		spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 1, 2));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 3, 2, 3));
	}
}
