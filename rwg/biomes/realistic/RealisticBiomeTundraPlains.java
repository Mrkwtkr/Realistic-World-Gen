package rwg.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoLog;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoShrub;
import rwg.deco.trees.DecoSmallPine;
import rwg.deco.trees.DecoSmallSpruce;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import rwg.util.SnowheightCalculator;

public class RealisticBiomeTundraPlains extends RealisticBiomeBase
{
	public RealisticBiomeTundraPlains(int sub)
	{
		super(sub, sub == 0 ? BaseBiomes.baseSnowDesert : BaseBiomes.baseColdPlains);
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(rand.nextInt((int)(2f / strength)) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 69)
			{
		    	(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
    	if(rand.nextInt((int)(1f / strength)) == 0)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 69)
		    {
		    	(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
		    }
    	}
    	
		if(rand.nextInt((int)(5f / strength)) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			
			if(z52 < 69)
			{
				WorldGenerator worldgenerator = new DecoSmallSpruce(rand.nextInt(5) == 0 ? 1 : 0);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
    	if(subID == 0) //polar mix
    	{
    		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f;
    		st = st < 0.2f ? 0.2f : st;
    		
    		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
    		h = h > 0f ? -h : h;
    		h += st;
    		h *= h / 50f;
    		h += st;
    		
    		h -= 13f;
    		
    		float b = 0f;
			b += perlin.noise2(x / 50f, y / 50f) * 3.2f;
			b += perlin.noise2(x / 15f, y / 15f) * 1.35f;
			
			h = b > h ? b : h;
    		
        	return 65f + h;
    	}
    	else if(subID == 1) //lakes
    	{
    		return 70f;
    	}
    	else //mix
    	{
    		return 70f;
    	}
    }

    @Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise) 
    {
    	if(subID == 0) //polar mix
    	{
			float p = perlin.noise2(i / 8f, j / 8f) * 0.5f;
			float c = CliffCalculator.calc(x, y, noise);
			boolean grass = false;
			boolean snow = false;
			
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
	            	if(depth < 5)
	            	{
		            	if(depth == 0)
		            	{		            		
		            		if(depth == 0 && k > 68f + (perlin.noise2(x / 10f, y / 10f) * 5f) && k < 254)
		            		{
		            			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
			        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
		            			snow = true;
		            		}
		            		else if(k > 61)
		            		{
	    	        			if(perlin.noise2(i / 50f, j / 50f) + p * 0.6f > 0.24f)
	                			{
	                    			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	                    			metadata[(y * 16 + x) * 256 + k] = 2;
	                			}
	    	        			else if(p < 0f)
	    	        			{
	    	        				grass = true;
	    	        				blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	    	        			}
	    	        			else
	    	        			{
	    	        				blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
	    	        			}
		            		}
		            	}
		            	else
		            	{
		            		if(snow)
		            		{
		            			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
		            		}
		            		else if(grass)
		            		{
		            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
		            		}
		            	}
	            	}
	            }
			}
    	}
    	else if(subID == 1) //lakes
    	{
    	}
    	else //mix
    	{
    	}
    }
}
