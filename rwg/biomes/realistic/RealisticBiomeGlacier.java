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
import rwg.deco.trees.DecoPineTree;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import rwg.util.SnowheightCalculator;

public class RealisticBiomeGlacier extends RealisticBiomeBase
{
	public RealisticBiomeGlacier(int sub)
	{
		super(sub, BaseBiomes.baseSnowDesert);
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
    	if(subID == 0) //HIGH
    	{
			return 65f;
    	}
    	else //LOW
    	{
    		float h = perlin.noise2(x / 300f, y / 300f) * 120f;
    		h *= h / 32f;
    		
    		if(h < 12f)
    		{
    			h += perlin.noise2(x / 50f, y / 50f) * (12f - h) * 0.3f;
    		}

    		if(h > 2f)
    		{
    			float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
    			h += perlin.noise2(x / 18f, y / 18f) * d;
    			h += perlin.noise2(x / 50f, y / 50f) * d * 0.5f;

    			if(h > 15f)
    			{
    				float d2 = (h - 15f) / 2f > 8f ? 8f : (h - 15f) / 2f;
    				h += perlin.noise2(x / 60f, y / 60f) * d2 * 2;
    				h += perlin.noise2(x / 12f, y / 12f) * d2 * 0.5f;
    			}
    		}
    		
    		h = h < 5f ? 5f : h;
    		return 65f + h;
    	}
    }

    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.1f ? true : false;
		boolean clay = c > 1.6f ? true : false;
		Block b;
		
		boolean ice = false;
    	if(subID == 1)
    	{
    		float h = perlin.noise2(i / 300f, j / 300f) * 120f;
    		h *= h / 32f;
    		if(h < 5f)
    		{
    			ice = true;
    		}
    	}
		
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
	        			if(ice && k < 70)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.packed_ice;
	        				if(depth == 0 && perlin.noise2(i / 3f, j / 3f) > 0f)
	        				{
		        				blocks[(y * 16 + x) * 256 + k + 1] = Blocks.packed_ice;
	        				}
	        			}
	        			else
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
		            		if(depth == 0 && k > 61 && k < 254)
		            		{
		            			SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
			            		ice = false;
		            		}
	        			}
	        		}
            	}
            }
		}
    }
}

