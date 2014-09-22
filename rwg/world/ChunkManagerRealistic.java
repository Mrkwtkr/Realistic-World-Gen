package rwg.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;

public class ChunkManagerRealistic extends WorldChunkManager
{
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;

    private PerlinNoise perlin;
    private CellNoise cell;
    
    private int biomeLength;
	
	protected ChunkManagerRealistic()
	{
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
	}

    public ChunkManagerRealistic(World par1World)
    {
        this();
        long seed = par1World.getSeed();
        
    	perlin = new PerlinNoise(seed);
    	cell = new CellNoise(seed, (short)0);
    	cell.setUseDistance(true);
    }    
	
    public int[] getBiomesGens(int par1, int par2, int par3, int par4)
    {	
    	int[] d = new int[par3 * par4];
    	
		for(int i = 0; i < par3; i++)
		{
			for(int j = 0; j < par4; j++)
    		{
    			d[j * par3 + i] = getBiomeGenAt(par1 + i, par2 + j).biomeID;
    		}
		}
    	return d;
    }
    
    public RealisticBiomeBase[] getBiomesGensData(int par1, int par2, int par3, int par4)
    {	
    	RealisticBiomeBase[] data = new RealisticBiomeBase[par3 * par4];
    	
		for(int i = 0; i < par3; i++)
		{
			for(int j = 0; j < par4; j++)
    		{
    			data[j * par3 + i] = getBiomeDataAt(par1 + i, par2 + j);
    		}
		}
    	return data;
    }
    
    public float getOceanValue(int x, int y)
    {
    	return 1f;// 0.5f + perlin.noise2(x / 2000f, y / 2000f);
    }

    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
    	return getBiomeDataAt(par1, par2, getOceanValue(par1, par2)).baseBiome;
    }
    
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2)
    {
    	return getBiomeDataAt(par1, par2, getOceanValue(par1, par2));
    }
    
    public RealisticBiomeBase getBiomeDataAt(int par1, int par2, float ocean)
    {
    	//return RealisticBiomeBase.landTundraPlainsPolar;
    	
    	if(par1 < 0 + perlin.noise2(par1 / 50f, par2 / 50f) * 25f)
    	{
        	return RealisticBiomeBase.landTundraPlainsPolar;
    	}
    	else
    	{
        	return RealisticBiomeBase.landTundraHillsFull;
    	}

    	/*
    	float temp = 0.5f + perlin.noise2(par1 / 2000f, par2 / 2000f);
    	float hum = 0.5f + perlin.noise2(par1 / 700f, par2 / 700f);
    	
    	temp = temp > 1f ? 1f : temp < 0f ? 0f : temp;
    	hum = hum > 1f ? 1f : hum < 0f ? 0f : hum;
    	ocean = ocean > 1f ? 1f : ocean < 0f ? 0f : ocean;
    	
    	if(ocean < 0.45f)
    	{
    		return biomeLayerOcean.getBiome(temp, hum);
    	}
    	else if (ocean > 0.55f)
    	{
    		return biomeLayerLand.getBiome(temp, hum);
    	}
    	else
    	{
    		return biomeLayerCoast.getBiome(temp, hum);
    	}*/
    }
    
    public float getNoiseAt(int x, int y)
    {
    	float ocean = getOceanValue(x, y);
    	return getBiomeDataAt(x, y, ocean).rNoise(perlin, cell, x, y, ocean, 1f);
    }
    
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

		int var6[] = getBiomesGens(par2, par3, par4, par5);

        for (int var7 = 0; var7 < par4 * par5; ++var7)
        {
            float var8 = (float)BiomeGenBase.getBiome(var6[var7]).getIntRainfall() / 65536.0F;

            if (var8 > 1.0F)
            {
                var8 = 1.0F;
            }

            par1ArrayOfFloat[var7] = var8;
        }

        return par1ArrayOfFloat;
    }

    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
    	
		int var7[] = getBiomesGens(par2, par3, par4, par5);

        for (int var8 = 0; var8 < par4 * par5; ++var8)
        {
            par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
    	
		int var7[] = getBiomesGens(par2, par3, par4, par5);

        for (int var8 = 0; var8 < par4 * par5; ++var8)
        {
            par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.getBiome(var7[var8]);
        }

        return par1ArrayOfBiomeGenBase;
    }

    public boolean areBiomesViable(int x, int y, int par3, List par4List)
    {
    	float centerNoise = getNoiseAt(x,y);
    	if(centerNoise < 62)
    	{
    		return false;
    	}
    	
    	float lowestNoise = centerNoise;
    	float highestNoise = centerNoise;
    	for(int i = -2; i <= 2; i++)
    	{
    		for(int j = -2; j <= 2; j++)
    		{
    			if(i != 0 && j != 0)
    			{
    				float n = getNoiseAt(x + i * 16, y + j * 16);
    				if(n < lowestNoise) { lowestNoise = n; }
    				if(n > highestNoise) { highestNoise = n; }
    			}
    		}
    	}
    	
    	if(highestNoise - lowestNoise < 22)
    	{
    		return true;
    	}
    	
    	return false;
    }

    public ChunkPosition findBiomePosition(int p_150795_1_, int p_150795_2_, int p_150795_3_, List p_150795_4_, Random p_150795_5_)
    {
    	return null;
    }

    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }
}
