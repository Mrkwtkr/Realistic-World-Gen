package rwg.biomes;

import java.util.Random;

import rwg.deco.DecoCacti;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoSavannah;
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
		if(rand.nextInt(2) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 68)
		    {
		    	(new WorldGenBlockBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
		    }
		}
		
		for(int b33 = 0; b33 < 7; b33++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

		    if(z52 < 73)
		    {
				WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(3) == 0 ? new WorldGenBigTree(false): rand.nextInt(5) == 0 ? new DecoSavannah(1) : new WorldGenTrees(false);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
		    }
		}
		
		if(rand.nextInt(2) == 0) 
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}
		
		for(int f23 = 0; f23 < 6; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{11,11,9,3,3,3,2,1,1})).generate(world, rand, j15, j17, j20);
		}
		
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
		
		for(int l14 = 0; l14 < 15; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float l = perlin.noise2(x / 170f, y / 170f) * 38f;
		l *= l / 21f;
		l *= l / 21f;
		l = -l;
		
		float c = 0f;
		if(l < -7f)
		{
			c = l < -8f ? -1f : l + 7f;
			c *= 8;
		}
		l += c;
		
		l = l < -23 ? -23 : l;

		float h1 = 0f;
		if(l < -6f)
		{
			h1 = perlin.noise2(x / 23f, y / 23f) * (l + 8f);
			h1 = h1 > 3f ? 3f : h1 < -3f ? -3f : h1;
		}
		
		return 85f + l + h1;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		boolean dirt = false;
		
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
            		if(depth > -1 && depth < 5)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
            		}
            	}
            	else
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			if(k < 65)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        				dirt = true;
	        			}
	        			else if(perlin.noise2(i / 12f, j / 12f) > -0.4f + ((k - 65f) / 13f))
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        				dirt = true;
	        			}
	        			else
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        			}
	        		}
	        		else if(depth < 6 && k > 61)
	        		{
	        			if(dirt)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        			else
	        			{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        			}
	        		}
	        		else if(depth < 4)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
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
