package rwg.world;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeLayerData 
{
	public BiomeGenBase biome;
	public float temp;
	public float hum;
	
	public BiomeLayerData(BiomeGenBase b, float t, float h)
	{
		biome = b;
		temp = t;
		hum = h;
	}
}
