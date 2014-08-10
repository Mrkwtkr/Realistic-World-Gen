package rwg.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeList 
{
	public static BiomeGenBase fakeOcean;
	public static BiomeGenBase fakeCoast;
	public static BiomeGenBase fakeLand;
	
	//LAND BIOMES
	public static BiomeGenBase REALISTICpole;
	public static BiomeGenBase REALISTICglacier;
	public static BiomeGenBase REALISTICsnowtaiga;
	public static BiomeGenBase REALISTICtaiga;
	public static BiomeGenBase REALISTICtundra;
	public static BiomeGenBase REALISTICredwood;
	public static BiomeGenBase REALISTICcanyon;
	public static BiomeGenBase REALISTICcanyonForest;
	public static BiomeGenBase REALISTICmesa;
	public static BiomeGenBase REALISTICsavannah;
	public static BiomeGenBase REALISTICredDesert;
	public static BiomeGenBase REALISTICdesert;
	public static BiomeGenBase REALISTICoasis;
	public static BiomeGenBase REALISTICplains;
	public static BiomeGenBase REALISTICplainsdry;
	
	//OCEAN BIOMES
	public static BiomeGenBase REALISTICoceanTropical;
	
	//COAST BIOMES
	public static BiomeGenBase REALISTICcoastFjords;
	public static BiomeGenBase REALISTICcoastDunes;
	
	public static void load()
	{
		fakeOcean = (new FakeOcean(197)).setColor(16777215).setBiomeName("Fake Ocean").setTemperatureRainfall(0.7F, 0.7F);
		fakeCoast = (new FakeCoast(198)).setColor(16777215).setBiomeName("Fake Coast").setTemperatureRainfall(0.7F, 0.7F);
		fakeLand = (new FakeLand(199)).setColor(16777215).setBiomeName("Fake Land").setTemperatureRainfall(0.7F, 0.7F);
		
		//LAND BIOMES
		REALISTICpole = (new RealisticBiomePole(200)).setColor(16777215).setBiomeName("Polar").setTemperatureRainfall(0.0F, 0.1F);
		REALISTICglacier = (new RealisticBiomeGlacier(201)).setColor(16777215).setBiomeName("Glacier").setTemperatureRainfall(0.0F, 0.1F);
		REALISTICsnowtaiga = (new RealisticBiomeSnowTaiga(202)).setColor(16777215).setBiomeName("SnowTaiga").setTemperatureRainfall(0.0F, 0.1F);
		REALISTICtaiga = (new RealisticBiomeTaiga(203)).setColor(16777215).setBiomeName("Taiga").setTemperatureRainfall(0.7F, 0.5F);
		REALISTICtundra = (new RealisticBiomeTundra(204)).setColor(16777215).setBiomeName("Tundra").setTemperatureRainfall(0.7F, 0.5F);
		REALISTICredwood = (new RealisticBiomeRedwood(205)).setColor(16777215).setBiomeName("Redwood forest").setTemperatureRainfall(0.7F, 0.5F);
		REALISTICcanyon = (new RealisticBiomeCanyon(206)).setColor(16421912).setBiomeName("Canyon").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICcanyonForest = (new RealisticBiomeCanyonForest(207)).setColor(16421912).setBiomeName("Canyon Forest").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICmesa = (new RealisticBiomeMesa(208)).setColor(16421912).setBiomeName("Mesa").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICsavannah = (new RealisticBiomeSavannah(209)).setColor(16421912).setBiomeName("Savannah").setTemperatureRainfall(0.9f, 0.1f);
		REALISTICredDesert = (new RealisticBiomeRedDesert(210)).setColor(16421912).setBiomeName("Red Desert").setTemperatureRainfall(1.0f, 0.4f); 
		REALISTICdesert = (new RealisticBiomeDesert(211)).setColor(16421912).setBiomeName("Desert").setTemperatureRainfall(2.0f, 0.0f);
		REALISTICoasis = (new RealisticBiomeOasis(212)).setColor(16421912).setBiomeName("Desert Oasis").setTemperatureRainfall(2.0f, 0.0f);
		
		//OCEAN BIOMES
		REALISTICoceanTropical = (new RealisticBiomeOceanTropical(230)).setColor(16421912).setBiomeName("Tropical Ocean").setTemperatureRainfall(0.8f, 0.8f);
		
		//COAST BIOMES
		REALISTICcoastFjords = (new RealisticBiomeCoastFjords(240)).setColor(16777215).setBiomeName("Fjords").setTemperatureRainfall(0.7F, 0.5F);
		REALISTICcoastDunes = (new RealisticBiomeCoastDunes(241)).setColor(16777215).setBiomeName("Dunes").setTemperatureRainfall(0.8F, 0.3F);
		
		//BiomeManager.addBiome("Taiga", BiomeList.REALISTICtaiga, 0f);
	}
}
