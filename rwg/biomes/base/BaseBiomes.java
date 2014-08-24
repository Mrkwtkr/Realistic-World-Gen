package rwg.biomes.base;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

public class BaseBiomes 
{
	public static BiomeGenBase baseSnowDesert;
	public static BiomeGenBase baseSnowForest;
	public static BiomeGenBase baseColdPlains;
	public static BiomeGenBase baseColdForest;
	public static BiomeGenBase baseHotPlains;
	public static BiomeGenBase baseHotForest;
	public static BiomeGenBase baseHotDesert;
	public static BiomeGenBase basePlains;
	
	public static void load()
	{
		baseSnowDesert = new BaseBiomeSnowDesert(200);
		baseSnowForest = new BaseBiomeSnowForest(201);
		baseColdPlains = new BaseBiomeColdPlains(202);
		baseColdForest = new BaseBiomeColdForest(203);
		baseHotPlains = new BaseBiomeHotPlains(204);
		baseHotForest = new BaseBiomeHotForest(205);
		baseHotDesert = new BaseBiomeHotDesert(206);
		basePlains = new BaseBiomePlains(207);
		
		BiomeDictionary.registerBiomeType(baseSnowDesert, Type.SNOWY);
		BiomeDictionary.registerBiomeType(baseSnowForest, Type.SNOWY);
		BiomeDictionary.registerBiomeType(baseColdPlains, Type.COLD);
		BiomeDictionary.registerBiomeType(baseColdForest, Type.COLD);
		BiomeDictionary.registerBiomeType(baseHotPlains, Type.DRY);
		BiomeDictionary.registerBiomeType(baseHotForest, Type.SAVANNA);
		BiomeDictionary.registerBiomeType(baseHotDesert, Type.HOT);
		BiomeDictionary.registerBiomeType(baseHotDesert, Type.PLAINS);
	}
}
