package rwg.world;

import java.util.ArrayList;

import rwg.biomes.BiomeList;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeLayer 
{
	private BiomeGenBase[] biomeLookupTable;
	private ArrayList<BiomeLayerData> biomeList;
	private int biomeListSize;
	
	public BiomeLayer()
	{
		biomeList = new ArrayList<BiomeLayerData>();
		biomeListSize = 0;
	}
	
	public void addBiome(float temp, float hum, BiomeGenBase biome)
	{
		biomeList.add(new BiomeLayerData(biome, temp, hum));
		biomeListSize++;
	}
	
	public void calculate()
	{
		biomeLookupTable = new BiomeGenBase[4096];
		for(int i = 0; i < 64; i++)
		{
			for(int j = 0; j < 64; j++)
			{
				biomeLookupTable[i + j * 64] = getBiomeByTempHum((float)i / 63F, (float)j / 63F);
			}
		}
	}
	
    public BiomeGenBase getBiome(float t, float h)
    {
        int i = (int)(t * 63D);
        int j = (int)(h * 63D);
        return biomeLookupTable[i + j * 64];
    }
    
    private BiomeGenBase getBiomeByTempHum(float t, float h) 
    {
    	BiomeGenBase closestBiome = BiomeList.REALISTICpole;
    	float closestDistance = Float.MAX_VALUE;
    	
    	float dis;
    	BiomeLayerData d;
    	for(int i = 0; i < biomeListSize; i++)
    	{
    		d = biomeList.get(i);
    		dis = (t-d.temp)*(t-d.temp) + (h-d.hum)*(h-d.hum);
    		if(dis < closestDistance)
    		{
    			closestDistance = dis;
    			closestBiome = d.biome;
    		}
    	}
    	
    	return closestBiome;
    }
}
