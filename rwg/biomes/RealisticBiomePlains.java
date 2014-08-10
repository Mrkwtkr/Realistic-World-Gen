package rwg.biomes;

import java.util.Random;

import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomePlains extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomePlains(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, float strength) 
	{
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y, float ocean) 
	{
		return 0;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise)
	{
	}

	@Override
	public float r3Dnoise(float z) 
	{
		return 0;
	}

}