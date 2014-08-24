package rwg.biomes.realistic;

import java.util.Random;

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
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomePlains extends RealisticBiomeBase
{
	public RealisticBiomePlains(int sub) 
	{
		super(sub, BaseBiomes.basePlains);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(subID == 0) //PLAINS
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
				(new DecoFlowers(new int[]{4,5,6,7,3,3,8,10,9,9,11})).generate(world, rand, j15, j17, j20);
			}
			
			if(rand.nextInt((int)(100f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
			
			float h = perlin.noise2((chunkX + 8) / 200f, (chunkY + 8) / 200f) * 28f;
			
			if(h < -2f)
			{
				for (int b1 = 0; b1 < 5f * strength; b1++)
				{
					int j6 = chunkX + rand.nextInt(16) + 8;
					int k10 = chunkY + rand.nextInt(16) + 8;
					int z52 = world.getHeightValue(j6, k10);
					WorldGenerator worldgenerator = rand.nextBoolean() ? new WorldGenShrub(0, 0) : rand.nextInt(7) == 0 ? new WorldGenForest(false, false) : rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false);
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
		}
		else //GRASSLAND
		{
			float a = perlin.noise2(chunkX / 90f, chunkY / 90f) * 35 - 5f;
			for (int b1 = 0; b1 < a * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
				if(z52 < 80)
				{
					WorldGenerator worldgenerator = rand.nextBoolean() ? new WorldGenShrub(0, 0) : rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);	
				}
			}
			
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
				(new DecoFlowers(new int[]{0,3})).generate(world, rand, j15, j17, j20);
			}
			
			if(rand.nextInt((int)(100f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
			
			for(int l14 = 0; l14 < 12f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = 60 + rand.nextInt(120);
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
		if(subID == 0) //PLAINS
		{
			float h = perlin.noise2(x / 200f, y / 200f) * 34f;
			h += cell.noise(x / 50f, y / 50f, 1D) * 15f;
			h += perlin.noise2(x / 50f, y / 50f) * 10f;
			h += perlin.noise2(x / 15f, y / 15f) * 4f;
			
			return 70f + h;
		}
		else //GRASSLAND
		{
			float h = perlin.noise2(x / 200f, y / 200f) * 120f;
			h = h < 0f ? h / 4.5f : h;
			if(h > 0f)
			{
				float st = h * 1.5f > 15f ? 15f : h * 1.5f;
				h += cell.noise(x / 70D, y / 70D, 1D) * st;
			}
			h += perlin.noise2(x / 50f, y / 50f) * 8f;
			h += perlin.noise2(x / 20f, y / 20f) * 4f;
			h += perlin.noise2(x / 10f, y / 10f) * 2f;
			
	    	return 72f + h;
		}
    }

	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
		if(subID == 0) //PLAINS
		{
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
		else //GRASSLAND
		{
			float c = CliffCalculator.calc(x, y, noise);
			boolean cliff = false;
			boolean gravel = false;
			
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
	        			float c2 = 1.4f - ((k - 60f) / 60f) + perlin.noise2(i / 8f, j / 8f) * 0.5f;
	        			if(c > c2 && c2 > 0.4f)
	        			{
	        				cliff = true;
	        				blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
	        			}
	        			else if(k < 63)
	    				{
	    					blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
	    					gravel = true;
	    				}
	    				else
	    				{
	    					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	    				}
	        		}
	        		else if(depth < 6 && !cliff)
	        		{
	        			if(gravel)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
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
