package rwg.biomes;

import java.util.Random;

import rwg.deco.trees.DecoPineTree;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import rwg.util.SnowheightCalculator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeGlacier extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeGlacier(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		if(rand.nextInt(5) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			WorldGenerator worldgenerator = new DecoPineTree(3, 1);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float h = perlin.noise2(x / 300f, y / 300f) * 135f;
		h *= h / 32f;

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
		
		float gh = 0f;
		if(h < 20f)
		{
			float lht = h <= 10f ? 10f : h;
			gh = perlin.noise2(x / 5f, y / 5f) * (15f - lht - 1f) * 1.5f - 1f;
			gh = gh > 2f ? 2f : gh;
		}
		
		float fh = h > 10f + gh ? h : 10f + gh;
		
		return fh + 55f;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.2f ? true : false;
		boolean clay = c > 2.1f ? true : false;
		boolean ice = false;
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

            	if(cliff && k > 69)
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
            		if(depth == 0)
	        		{
            			if(k > 66)
            			{
            				if(k > 67 && k < 80 && perlin.noise2(i / 20f, j / 20f) > 0.1f)
            				{
            					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
            					SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
            					dirt = true;
            				}
            				else
            				{
            					blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            					SnowheightCalculator.calc(x, y, k, blocks, metadata, noise);
            				}
            			}
            			else
            			{
            				ice = true;
            				blocks[(y * 16 + x) * 256 + k] = Blocks.packed_ice;
            			}
	        		}
	        		else if(depth > 0 && depth < 8)
	        		{
	        			if(ice)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.packed_ice;
	        			}
	        			else if(dirt)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        			else if(k < 70)
	        			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
	        			}
	        			else
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
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
