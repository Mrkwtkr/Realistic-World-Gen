package rwg.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoCacti;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoWildWheat;
import rwg.deco.trees.DecoSavannah;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;

public class RealisticBiomePlainsHot extends RealisticBiomeBase
{
	public RealisticBiomePlainsHot(int sub) 
	{
		super(sub, BaseBiomes.baseHotPlains);
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(rand.nextInt((int)(2f / strength)) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 85)
			{
				(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		if(rand.nextInt((int)(22f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		if(rand.nextInt((int)(3f / strength)) == 0) 
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}
		
		for(int f23 = 0; f23 < 3f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,9,9,9,3,3,3,3,3,2,2,2,11,11,11})).generate(world, rand, j15, j17, j20);
		}
		
		if(rand.nextInt((int)(100f / strength)) == 0)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(60) + 60;
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
		}
		
		if(subID == 0) //PRAIRIE
		{
			float h = perlin.noise2((chunkX + 8) / 200f, (chunkY + 8) / 200f) * 20f;
			
			if(h < -4f)
			{
				for (int b1 = 0; b1 < 3f * strength; b1++)
				{
					int j6 = chunkX + rand.nextInt(16) + 8;
					int k10 = chunkY + rand.nextInt(16) + 8;
					int z52 = world.getHeightValue(j6, k10);
					WorldGenerator worldgenerator = rand.nextBoolean() ? new WorldGenShrub(0, 0) : rand.nextInt(10) == 0 ? new DecoSavannah(1) : rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);	
				}
							
			}
			
			for(int l14 = 0; l14 < 7f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = 60 + rand.nextInt(40);
				int j24 = chunkY + rand.nextInt(16) + 8;

				if(rand.nextInt(5) == 0)
				{
					(new DecoGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
				}
				else
				{
					(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
				}
			}
			
			for(int k18 = 0; k18 < 4f * strength; k18++)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(160);
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
			}
		}
		else //SAVANNA FIELDS
		{
			if(rand.nextInt((int)(30f / strength)) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 85)
				{
					WorldGenerator worldgenerator = new DecoSavannah(1);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
			for(int k18 = 0; k18 < 36f * strength; k18++)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(160);
				int k24 = chunkY + rand.nextInt(16) + 8;
				if(j23 < 90)
				{
					(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
				}
			}
			
			for(int l14 = 0; l14 < 8f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = 60 + rand.nextInt(40);
				int j24 = chunkY + rand.nextInt(16) + 8;

				if(rand.nextInt(3) == 0)
				{
					(new DecoGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
				}
				else
				{
					(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
				}
			}
		}
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {
		if(subID == 0) //PRAIRIE
		{
			float h = perlin.noise2(x / 200f, y / 200f) * 28f;
			h += cell.noise(x / 50f, y / 50f, 1D) * 15f;
			h += perlin.noise2(x / 50f, y / 50f) * 10f;
			h += perlin.noise2(x / 15f, y / 15f) * 4f;
			
			return 70f + h;
		}
		else //SAVANNA PLAINS
		{
	    	float h = (perlin.noise2(x / 300f, y / 300f) + 0.25f) * 65f;
	    	h = h < 1f ? 1f : h;
	    	
	    	float r = cell.noise(x / 50D, y / 50D, 1D) * h * 2;
	    	h += r;
	
			h += perlin.noise2(x / 40f, y / 40f) * 8;
			h += perlin.noise2(x / 14f, y / 14f) * 2;
			
	    	return 70f + h;
		}
    }

	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
		if(subID == 0) //PRAIRE
		{
			float m = perlin.noise2(i / 8f, j / 8f) + (perlin.noise2(i / 75f, j / 75f) * 0.8f);
			boolean sand = false;
			
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
	    				if(k < 63)
	    				{
	    					blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	    					sand = true;
	    				}
	    				else if(m < -0.3f)
    					{
    						blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    						metadata[(y * 16 + x) * 256 + k] = 1;
    					}
    					else
    					{
    						blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    					}
	        		}
	        		else if(depth < 6)
	        		{
	        			if(sand)
	        			{
	        				if(depth < 4)
	        				{
	        					blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        				}
	        				else
	        				{
	        					blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        				}
	        			}
	        			else
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        		}
	            }
			}
		}
		else //SAVANNA PLAINS
		{
	    	float h = (perlin.noise2(i / 300f, j / 300f) + 0.25f) * 65f;
	    	h = h < 1f ? 1f : h;
			float m = perlin.noise2(i / 12f, j / 12f);
			boolean sand = false;
			
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
	        			if(k > 90f + perlin.noise2(i / 24f, j / 24f) * 10f - h || m < -0.28f)
	        			{
	    					blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	    					sand = true;
	        			}
	        			else if(m < 0.22f || k < 62)
	        			{
	    					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	    					metadata[(y * 16 + x) * 256 + k] = 1;
	        			}
	        			else
	        			{
	    					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        			}
	        		}
	        		else if(depth < 6)
	        		{
	        			if(sand)
	        			{
	        				if(depth < 4)
	        				{
	            				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        				}
	        				else
	        				{
	            				blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        				}
	        			}
	        			else
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        		}
	            }
			}
		}
    }
}
