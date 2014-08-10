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

public class RealisticBiomeOceanTropical extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeOceanTropical(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, float strength) 
	{
		for (int b1 = 0; b1 < 16f * strength; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 > 73)
			{
				WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new WorldGenShrub(0, 0) : rand.nextInt(6) != 0 ? new WorldGenTrees(false) : new WorldGenBigTree(false);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
			else if (z52 > 63 && rand.nextInt(4) == 0)
			{
				WorldGenerator worldgenerator = rand.nextBoolean() ? new WorldGenShrub(0, 0) : rand.nextInt(4) == 0 ? new DecoPalmTree(14 + rand.nextInt(8)) : new WorldGenTrees(false);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}

		if(rand.nextInt((int) (3f / strength)) == 0) 
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}

		if(rand.nextInt((int) (7f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			if(rand.nextInt(3) == 0)
			{
				(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
			}
			else
			{
				(new WorldGenMelon()).generate(world, rand, j16, j18, j21);
			}
		}
		
		for(int f23 = 0; f23 < 3f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{11,11,11,11,2,2,2,1,3,3,3})).generate(world, rand, j15, j17, j20);
		}
		
		for(int l14 = 0; l14 < 12f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			int r = rand.nextInt(5);
			if(r < 2 && k22 > 71)
			{
				(new DecoGrass(Blocks.double_plant, 3)).generate(world, rand, l19, k22, j24);
			}
			else if(k22 > 71 || rand.nextInt(4) == 0)
			{
				(new DecoGrass(Blocks.tallgrass, 1 + rand.nextInt(2))).generate(world, rand, l19, k22, j24);
			}
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y, float ocean) 
	{
		float h = perlin.noise2(x / 300f, y / 300f) * 29f;
		float s = perlin.noise2(x / 30f, y / 30f) * 10f;
		
		if(h > 4f + s)
		{
			h *= 1f + (h - 4f - s) / 2f;
		}
		
		if(h < 6f)
		{
			h += perlin.noise2(x / 15f, y / 15f) * (6f - h) * 0.4f;
		}
		
		float c = 0f;
		if(h > 4f + s)
		{
			c = h > 4f + s + 3f ? 3f : h - (4f + s);
			c *= 3f + perlin.noise2(x / 30f, y / 30f) * 5f;
		}
		
		float sh = 0f;
		if(h > 0f)
		{
			float power = h / 4f > 7f ? 7f : h / 4f;
			sh += perlin.noise2(x / 15f, y / 15f) * power;
			sh += perlin.noise2(x / 6f, y / 6f) * power * 0.25f;
		}
		
		return 60f + h + sh + c;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.5f ? true : false;
		
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

            	if(cliff)
            	{
            		if(depth < 2)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            	}
            	else
            	{
            		if(k > 64)
            		{
    	        		if(depth == 0)
    	        		{
    	        			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    	        		}
    	        		else if(depth < 6)
    	        		{
    	            		blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
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
	}

	@Override
	public float r3Dnoise(float z) 
	{
		return 0;
	}
}
