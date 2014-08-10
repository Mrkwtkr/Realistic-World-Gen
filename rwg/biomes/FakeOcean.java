package rwg.biomes;

import java.util.Random;

import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoPalmTree;
import rwg.deco.trees.DecoPineTree;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FakeOcean extends BiomeGenBase implements RealisticBiome
{
	public FakeOcean(int id) 
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
		return 35f;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

        		if(depth == 0)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
        		}
        		else if(depth < 6)
        		{
            		blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
        		}
            }
		}
	}

	@Override
	public float r3Dnoise(float z) 
	{
		return 0;
	}
}
