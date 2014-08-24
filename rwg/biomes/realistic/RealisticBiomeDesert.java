package rwg.biomes.realistic;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoCacti;
import rwg.deco.DecoIceSpike;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import rwg.util.SnowheightCalculator;

public class RealisticBiomeDesert extends RealisticBiomeBase
{
	public RealisticBiomeDesert(int sub)
	{
		super(sub, BaseBiomes.baseHotDesert);
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
    	
    	if(subID == 0 || subID == 2) //DESERT
    	{
			for(int k18 = 0; k18 < 12f * strength; k18++)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(160);
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
			}
			
			for(int i15 = 0; i15 < 3f * strength; i15++)
			{
				int i17 = chunkX + rand.nextInt(16) + 8;
				int i20 = rand.nextInt(160);
				int l22 = chunkY + rand.nextInt(16) + 8;
				(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
			}
    	}
    	else //DESERT MOUNTAINS
    	{
			for (int b1 = 0; b1 < 6f * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
				if(z52 < 68)
				{
					WorldGenerator worldgenerator = new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);	
				}
			}
    		
			for(int k18 = 0; k18 < 35f * strength; k18++)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(160);
				int k24 = chunkY + rand.nextInt(16) + 8;
				if(j23 < 80 || rand.nextInt(10) == 0)
				{
					(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
				}
			}
			
			for(int i15 = 0; i15 < 6f * strength; i15++)
			{
				int i17 = chunkX + rand.nextInt(16) + 8;
				int i20 = rand.nextInt(160);
				int l22 = chunkY + rand.nextInt(16) + 8;
				if(i20 < 80 || rand.nextInt(6) == 0)
				{
					(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
				}
			}
    	}
    }

    @Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {
    	if(subID == 0) //DESERT
    	{
	    	float h = (perlin.noise2(x / 300f, y / 300f) + 0.25f) * 65f;
	    	h = h < 1f ? 1f : h;
	    	
	    	h += cell.noise(x / 50D, y / 50D, 1D) * h * 2;
	
			h += perlin.noise2(x / 40f, y / 40f) * 8;
			h += perlin.noise2(x / 14f, y / 14f) * 2;
			
	    	return 80f + h;
    	}
    	else if(subID == 1) //DESERT MOUNTAINS
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
			
	    	return 74f + h;
    	}
    	else //DESERT DUNES
    	{
    		float st = (perlin.noise2(x / 160f, y / 160f) + 0.38f) * 35f;
    		st = st < 0.2f ? 0.2f : st;
    		
    		float h = perlin.noise2(x / 60f, y / 60f) * st * 2f;
    		h = h > 0f ? -h : h;
    		h += st;
    		h *= h / 50f;
    		h += st;
    		
    		if(h < 10f)
    		{
    			float d = (h - 10f) / 2f;
    			d = d > 4f ? 4f : d;
		    	h += cell.noise(x / 25D, y / 25D, 1D) * d;
				h += perlin.noise2(x / 30f, y / 30f) * d;
				h += perlin.noise2(x / 14f, y / 14f) * d * 0.5f;
    		}
    		
    		return 70f + h;
    	}
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
    	if(subID == 0 || subID == 2) //DESERT HILLS
    	{
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
    	else //DESERT MOUNTAINS
    	{
			float c = CliffCalculator.calc(x, y, noise);
			boolean cliff = false;
    		boolean grass = false;
    		
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
		        			else if (k < 67 && perlin.noise2(x / 5f, y / 5f) - ((k - 63) / 5f) > 0f)
	                		{
	                			grass = true;
	                		}
	                	}
	                	
	                	if(cliff && depth < 2)
	                	{
	        				blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
	                	}
	                	else if(grass)
                		{
    	            		if(depth == 0)
    	            		{
    	            			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    	            		}
    	            		else if(depth < 3)
    	            		{
    	            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
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
}
