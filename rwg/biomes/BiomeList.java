package rwg.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeList 
{
	public static BiomeGenBase REALISTICpole;
	public static BiomeGenBase REALISTICglacier;
	public static BiomeGenBase REALISTICsnowtaiga;
	public static BiomeGenBase REALISTICtaiga;
	
	public static BiomeGenBase REALISTICcanyon;
	public static BiomeGenBase REALISTICcanyonForest;
	public static BiomeGenBase REALISTICmesa;
	public static BiomeGenBase REALISTICsavannah;
	public static BiomeGenBase REALISTICredDesert;
	public static BiomeGenBase REALISTICdesert;

	public static BiomeGenBase REALISTICtropicalHills;
	
	public static void load()
	{
		//REALISTIC COLD BIOMES
		REALISTICpole = (new RealisticBiomePole(200)).setColor(16777215).setBiomeName("Polar").setTemperatureRainfall(0.0F, 0.1F);
		REALISTICglacier = (new RealisticBiomeGlacier(201)).setColor(16777215).setBiomeName("Glacier").setTemperatureRainfall(0.0F, 0.1F);
		REALISTICsnowtaiga = (new RealisticBiomeSnowTaiga(202)).setColor(16777215).setBiomeName("SnowTaiga").setTemperatureRainfall(0.0F, 0.1F);
		REALISTICtaiga = (new RealisticBiomeTaiga(203)).setColor(16777215).setBiomeName("Taiga").setTemperatureRainfall(0.7F, 0.5F);
		
		//REALISTIC WARM BIOMES
		REALISTICcanyon = (new RealisticBiomeCanyon(204)).setColor(16421912).setBiomeName("Canyon").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICcanyonForest = (new RealisticBiomeCanyonForest(205)).setColor(16421912).setBiomeName("Canyon Forest").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICmesa = (new RealisticBiomeMesa(206)).setColor(16421912).setBiomeName("Mesa").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICsavannah = (new RealisticBiomeSavannah(207)).setColor(16421912).setBiomeName("Savannah").setTemperatureRainfall(0.9f, 0.1f);
		REALISTICdesert = (new RealisticBiomeDesert(208)).setColor(16421912).setBiomeName("Desert").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICredDesert = (new RealisticBiomeRedDesert(209)).setColor(16421912).setBiomeName("Red Desert").setTemperatureRainfall(1.0f, 0.4f); 
		
		//REALISTIC WET BIOMES
		REALISTICtropicalHills = (new RealisticBiomeTropicalHills(210)).setColor(16421912).setBiomeName("Tropical Hills").setTemperatureRainfall(1.0f, 0.8f); 
		//REALISTICjungleRivers = (new RealisticBiomeJungleRivers(210)).setColor(353825).setBiomeName("Jungle Rivers").setTemperatureRainfall(2.0f, 0.0f);
		//REALISTICjungleHills = (new RealisticBiomeJungleHills(211)).setColor(353825).setBiomeName("Jungle Hills").setTemperatureRainfall(1.0f, 1.0f);

		//BiomeManager.addBiome("Taiga", BiomeList.REALISTICtaiga, 0f);
	}
}
