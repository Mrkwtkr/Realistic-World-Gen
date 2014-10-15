package rwg.biomes.realistic;

import java.util.Random;

import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeBase
{
	private static final RealisticBiomeBase[] biomeList = new RealisticBiomeBase[256];
	private static int nextBiomeID = 0;
	
	//POLAR BIOMES
	public static RealisticBiomeBase landPolarPlains = new RealisticBiomePolar(0);
	public static RealisticBiomeBase landPolarLakes = new RealisticBiomePolar(1);
	
	//TUNDRA BIOMES
	public static RealisticBiomeBase landTundraHillsHigh = new RealisticBiomeTundraHills(0);
	public static RealisticBiomeBase landTundraHillsValley = new RealisticBiomeTundraHills(1);
	public static RealisticBiomeBase landTundraHillsSpikes = new RealisticBiomeTundraHills(2);
	public static RealisticBiomeBase landTundraPlainsPolar = new RealisticBiomeTundraPlains(0);
	public static RealisticBiomeBase landTundraPlainsShield = new RealisticBiomeTundraPlains(1);
	public static RealisticBiomeBase landTundraPlainsMix = new RealisticBiomeTundraPlains(2);
	public static RealisticBiomeBase landTundraLakesIslands = new RealisticBiomeTundraLakes(0);
	public static RealisticBiomeBase landTundraLakesShield = new RealisticBiomeTundraLakes(1);

	//SNOW BIOMES
	public static RealisticBiomeBase landSnowHillsHigh = new RealisticBiomeSnowHills(0);
	public static RealisticBiomeBase landSnowHillsRivers = new RealisticBiomeSnowHills(1);
	public static RealisticBiomeBase landSnowHillsSpikes = new RealisticBiomeSnowHills(2);
	public static RealisticBiomeBase landSnowPlainsField = new RealisticBiomeSnowPlains(0);
	public static RealisticBiomeBase landSnowLakesIslands = new RealisticBiomeSnowLakes(0);
	
	//TAIGA BIOMES
	public static RealisticBiomeBase landTaigaHillsShield = new RealisticBiomeTaigaHills(0);
	public static RealisticBiomeBase landTaigaHillsRivers = new RealisticBiomeTaigaHills(1);
	public static RealisticBiomeBase landTaigaHillsSpikes = new RealisticBiomeTaigaHills(2);
	public static RealisticBiomeBase landTaigaHillsMix = new RealisticBiomeTaigaHills(3);
	public static RealisticBiomeBase landTaigaPlainsShield = new RealisticBiomeTaigaPlains(0);
	public static RealisticBiomeBase landTaigaPlainsMix = new RealisticBiomeTaigaPlains(1);
	public static RealisticBiomeBase landTaigaLakesIslands = new RealisticBiomeTaigaLakes(0);
	public static RealisticBiomeBase landTaigaLakesSwamp = new RealisticBiomeTaigaLakes(1);
	public static RealisticBiomeBase landTaigaLakesMix = new RealisticBiomeTaigaLakes(2);
	
	//REDWOOD BIOMES
	//public static RealisticBiomeBase landRedwoodHillsRivers;
	//public static RealisticBiomeBase landRedwoodHillsSpikes;
	//public static RealisticBiomeBase landRedwoodPlainsField;
	//public static RealisticBiomeBase landRedwoodPlainsLakes;
	
	//WIP ===========================================================================================
	public static RealisticBiomeBase test = new RealisticBiomeTest();
	public static RealisticBiomeBase low = new RealisticBiomeTestHeight(0);
	public static RealisticBiomeBase high = new RealisticBiomeTestHeight(1);
	
	public static RealisticBiomeBase landRedwoodSpikes = new RealisticBiomeRedwood(0);
	
	//public static RealisticBiomeBase landGlacierHigh = new RealisticBiomeGlacier(0);
	//public static RealisticBiomeBase landGlacierLow = new RealisticBiomeGlacier(1);
	public static RealisticBiomeBase landPlainsPrairie = new RealisticBiomePlainsHot(0);
	public static RealisticBiomeBase landPlainsSavanna = new RealisticBiomePlainsHot(1);
	public static RealisticBiomeBase landPlainsPlains = new RealisticBiomePlains(0);
	public static RealisticBiomeBase landPlainsGrassland = new RealisticBiomePlains(1);
	public static RealisticBiomeBase landDesertHills = new RealisticBiomeDesert(0);
	public static RealisticBiomeBase landDesertMountains = new RealisticBiomeDesert(1);
	public static RealisticBiomeBase landDesertDunes = new RealisticBiomeDesert(2);
	
	//public static RealisticBiomeBase landTaigaHills = new RealisticBiomeTaiga(0);
	//public static RealisticBiomeBase landTaigaFields = new RealisticBiomeTaiga(1);
	
	//public static RealisticBiomeBase landSnowHills = new RealisticBiomeSnow(0);
	//public static RealisticBiomeBase landSnowFields = new RealisticBiomeSnow(1);
	//public static RealisticBiomeBase landSnowLakes = new RealisticBiomeSnow(2);
	public static RealisticBiomeBase landSaltLakes = new RealisticBiomeSaltLakes();
	
	//public static RealisticBiomeBase landRedwoodLakes
	//public static RealisticBiomeBase landRedwoodFields
	//public static RealisticBiomeBase landRedwoodHills
	
	public static RealisticBiomeBase landRockDesert = new RealisticBiomeRockDesert();
	public static RealisticBiomeBase landCanyonMesa = new RealisticBiomeMesa();
	//public static RealisticBiomeBase landCanyonCanyon
	//public static RealisticBiomeBase landCanyonForest
	//public static RealisticBiomeBase landCanyonGreen
	//public static RealisticBiomeBase landCanyonJungle
	
	//public static RealisticBiomeBase landSavannaFields
	//public static RealisticBiomeBase landSavannaForest
	//public static RealisticBiomeBase landSavannaHills
	
	public static RealisticBiomeBase oceanTropicalAtoll = new RealisticBiomeAtoll();
	
	public final int biomeID;
	public final int subID;
	public final BiomeGenBase baseBiome;
	
	public RealisticBiomeBase(int sub, BiomeGenBase biome)
	{
		biomeID = nextBiomeID;
		biomeList[biomeID] = this;
		nextBiomeID++;
		
		subID = sub;
		baseBiome = biome;
	}
	
	public static RealisticBiomeBase getBiome(int id)
	{
		return biomeList[id];
	}
	
	
	//======================================================================================================================================
	
	
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
    	return 63f;
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
    	Block b;
		for(int k = 255; k > -1; k--)
		{
			b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

        		if(depth == 0)
        		{
    				if(k < 62)
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    				}
    				else
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    				}
        		}
        		else if(depth < 6)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
        		}
            }
		}
    }
    
    public float r3Dnoise(float z)
    {
    	return 0f;
    }
}
