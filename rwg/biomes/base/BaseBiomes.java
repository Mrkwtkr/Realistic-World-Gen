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
	public static BiomeGenBase baseTropicalIsland;
	public static BiomeGenBase baseRedwood;
	
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
		baseTropicalIsland = new BaseBiomeTropicalIsland(208);
		baseRedwood = new BaseBiomeRedwood(209);
		
		BiomeDictionary.registerBiomeType(baseSnowDesert, Type.COLD, Type.SNOWY, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(baseSnowForest, Type.COLD, Type.SNOWY, Type.CONIFEROUS, Type.FOREST);
		BiomeDictionary.registerBiomeType(baseColdPlains, Type.COLD, Type.WASTELAND);
		BiomeDictionary.registerBiomeType(baseColdForest, Type.COLD, Type.CONIFEROUS, Type.FOREST);
		BiomeDictionary.registerBiomeType(baseHotPlains, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		BiomeDictionary.registerBiomeType(baseHotForest, Type.HOT, Type.SAVANNA, Type.PLAINS, Type.SPARSE);
		BiomeDictionary.registerBiomeType(baseHotDesert, Type.HOT, Type.DRY, Type.SANDY );
		BiomeDictionary.registerBiomeType(basePlains, Type.PLAINS);
		BiomeDictionary.registerBiomeType(baseTropicalIsland, Type.HOT, Type.WET, Type.JUNGLE);
		BiomeDictionary.registerBiomeType(baseRedwood, Type.COLD, Type.CONIFEROUS, Type.FOREST);
	}
}
