package rwg.biomes;

import java.util.Random;

import rwg.deco.DecoCacti;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoSavannah;
import rwg.util.CanyonColor;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeDesert extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeDesert(int id)
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		for(int k18 = 0; k18 < 12; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
		}
		
		for(int i15 = 0; i15 < 3; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = rand.nextInt(160);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float m = perlin.noise2(x / 150f, y / 150f) * 100f;
		float mh = perlin.noise2(x / 150f, y / 150f) * 50f + 10f;
		m = mh > m ? mh : m;
		
		float h = 0f;
		h = m > h ? m : h;
		
		h += perlin.noise2(x / 50f, y / 50f) * 8;
		h += perlin.noise2(x / 20f, y / 20f) * 4;
		
		return 70f + h;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 0.8f ? true : false;
		float m = perlin.noise2(i / 25f, j / 25f) + perlin.noise2(i / 2f, j / 2f) * 0.3f;
		
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

            	if(cliff || (k > 87f && m > 0.5f - ((k - 87f) / 20f)))
            	{
            		if(depth < 5)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	        			metadata[(y * 16 + x) * 256 + k] = 8;
            		}
            	}
            	else
            	{
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
	}

	@Override
	public float r3Dnoise(float z) 
	{
		return 0f;
	}
}
