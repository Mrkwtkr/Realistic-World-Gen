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
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
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
}
