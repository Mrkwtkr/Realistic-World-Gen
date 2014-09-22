package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoCacti;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

public class RealisticBiomeSaltLakes extends RealisticBiomeBase
{
	public RealisticBiomeSaltLakes() 
	{
		super(0, BaseBiomes.baseHotDesert);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		for(int k18 = 0; k18 < 5f * strength; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
		}
		
		for(int i15 = 0; i15 < 2f * strength; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int l22 = chunkY + rand.nextInt(16) + 8;
			int i20 = world.getHeightValue(i17, l22);
			if(i20 > 63)
			{
				(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
			}
		}
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
		float h = perlin.noise2(x / 200f, y / 200f) * 32f;

		if(h > 5f)
		{
			h = 5f + (h - 5f) * (4f * border);
			float st = (h - 5f) * 1.5f > 15f ? 15f : (h - 5f) * 1.5f;
			h += cell.noise(x / 70D, y / 70D, 1D) * st;
		}
		
		if(border < 1f)
		{
			h += (1f - border) * 35f;
		}

		if(h > -4f)
		{
			float st = (h + 4f) > 8f ? 8f : (h + 4f);
			h += perlin.noise2(x / 50f, y / 50f) * st;
			h += perlin.noise2(x / 20f, y / 20f) * (st / 2f);
			h += perlin.noise2(x / 10f, y / 10f) * (st / 4f);
		}
		
		h = h < -2 ? -2 : h;
		
    	return 65f + h;
    }

    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = false;
		boolean salt = false;
		
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
            	
            	if(k < 62 && depth < 10)
            	{
            		if(depth < 4)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            		}
            		if(depth < 7)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
            		}
            		else
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
            		}
            	}
            	else if(depth < 10)
            	{
                	if(depth == 0)
                	{
	        			float c2 = 1.4f - ((k - 60f) / 60f) + perlin.noise2(i / 8f, j / 8f) * 0.5f;
	        			if(c > c2 && c2 > 0.4f)
	        			{
	        				cliff = true;
	        			}
	        			else if (k < 63)
                		{
	        				salt = true;
                		}
                	}
                	
                	if(cliff && depth < 2)
                	{
        				blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
                	}
                	else if(salt)
            		{
	            		if(depth < 3)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	            			metadata[(y * 16 + x) * 256 + k] = 0;
	            		}
	            		else if(depth < 7)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	            		}
	            		else if(depth < 10)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	            		}
            		}
            		else
            		{
	            		if(depth < 7)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	            		}
	            		else if(depth < 10)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	            		}
            		}
            	}
            }
		}
    }
}
