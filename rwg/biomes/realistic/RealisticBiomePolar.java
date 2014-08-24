package rwg.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoIceSpike;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import rwg.util.SnowheightCalculator;

public class RealisticBiomePolar extends RealisticBiomeBase
{
	public RealisticBiomePolar(int sub)
	{
		super(sub, BaseBiomes.baseSnowDesert);
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {
    	if(subID == 0) //PLAINS
    	{
    		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f;
    		st = st < 0.2f ? 0.2f : st;
    		
    		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
    		h = h > 0f ? -h : h;
    		h += st;
    		h *= h / 50f;
    		h += st;
    		
        	return 70f + h;
    	}
    	else if(subID == 1) //HILLS
    	{
    		float h = perlin.noise2(x / 300f, y / 300f) * 115f;
    		h *= h / 32f;

    		if(h > 2f)
    		{
    			float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
    			h += perlin.noise2(x / 18f, y / 18f) * d;
    			h += perlin.noise2(x / 50f, y / 50f) * d * 0.5f;

    			if(h > 60f)
    			{
    				float d2 = (h - 60f) / 1.5f > 35f ? 35f : (h - 60f) / 1.5f;
    				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
    			}
    		}

    		h += perlin.noise2(x / 22f, y / 22f) * 3;
    		
    		return h + 67f;
    	}
    	else //LAKES
    	{
    		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f;
    		st = st < 0.2f ? 0.2f : st;
    		
    		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
    		h = h > 0f ? -h : h;
    		h += st;
    		h *= h / 50f;
    		h += st;
    		
        	return 50f + h;
    	}
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
    	if(subID == 0 || subID == 2)
    	{
    		boolean water = false;
    		
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
	        		if(depth > -1 && depth < 9)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
	            		if(depth == 0 && k > 61 && k < 254)
	            		{
	            			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
	            		}
	        		}
	            }
	            else if(!water && b == Blocks.water)
	            {
        			blocks[(y * 16 + x) * 256 + k] = Blocks.ice;
	            	water = true;
	            }
			}
    	}
    	else
    	{
			float c = CliffCalculator.calc(x, y, noise);
			boolean cliff = c > 1.2f ? true : false;
			boolean clay = c > 1.6f ? true : false;
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
	            	if(cliff)
	            	{
		        		if(depth > -1 && depth < 6)
		        		{
	        				if(clay)
	        				{
	            				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
	            				metadata[(y * 16 + x) * 256 + k] = 9; 
	        				}
	        				else
	        				{
	                    		if(depth > -1 && depth < 2)
	                    		{
	                    			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
	                    		}
	                    		else
	                    		{
	                    			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
	                    		}
	        				}
		        		}
	            	}
	            	else
	            	{
		        		if(depth > -1 && depth < 9)
		        		{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
		            		if(depth == 0 && k > 61 && k < 254)
		            		{
		            			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
		            		}
		        		}
	            	}
	            }
			}
    	}
    }
}
