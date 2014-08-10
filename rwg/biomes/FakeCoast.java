package rwg.biomes;

import java.util.Random;

import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoPalmTree;
import rwg.deco.trees.DecoPineTree;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FakeCoast extends BiomeGenBase implements RealisticBiome
{
	public FakeCoast(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, float strength) 
	{
		//boulders
		for (int l = 0; l < 4f * strength; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 100 && (k1 < 64 || rand.nextInt(15) == 0))
			{
		    	(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		//trees
		boolean bush = false;
		float l = perlin.noise2(chunkX / 60f, chunkY / 60f) * 15f + 4f;
		if(l < 0f) { l = 1; bush = true; }
		for (int b1 = 0; b1 < l * 2f * strength; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 100)
			{
				if(bush)
				{
					WorldGenerator worldgenerator = new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoPineTree(5, rand.nextInt(2)) : new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			else if(z52 < 130)
			{
				WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoPineTree(3, rand.nextInt(2)) : new WorldGenShrub(0, 0);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}

		if(rand.nextInt((int)(3f / strength)) == 0)
		{
			int k15 = chunkX + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(64) + 64;
			int k20 = chunkY + rand.nextInt(16) + 8;
			
			if(rand.nextBoolean())
			{
				(new WorldGenFlowers(Blocks.brown_mushroom)).generate(world, rand, k15, k17, k20);
			}
			else
			{
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(world, rand, k15, k17, k20);
			}
		}
		
		if(rand.nextInt((int)(20f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		for(int f23 = 0; f23 < 2f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
		}

		for(int l14 = 0; l14 < 10f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y, float ocean) 
	{
		ocean -= 0.45f;
		ocean *= 10f;
		ocean += (perlin.noise2(x / 50f, y / 50f) * 0.15f) + (perlin.noise2(x / 20f, y / 20f) * 0.07f);
		
		float h = 0f;
		if(ocean < 0.3f)
		{
			h = -(1f - (ocean * 3)) * 15 + 2f;
		}
		else if(ocean > 0.35f)
		{
			float noise = perlin.noise2(x / 100f, y / 100f) * 30f;
			h = 20f + noise;
		}
		else
		{
			float noise = perlin.noise2(x / 100f, y / 100f) * 30f;
			h = (ocean - 0.3f) * (400f + noise * 20); 
		}
		
		if(h > 2f)
		{
			float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
			h += perlin.noise2(x / 14f, y / 14f) * d * 0.3f;
			h += perlin.noise2(x / 25f, y / 25f) * d;
		}
		
		return 65f + h;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.5f ? true : false;
		
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
            		if(depth > -1 && depth < 2)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
            		}
        		}
        		else
        		{
        			if(k > 65)
        			{
	            		if(depth == 0)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	            		}
	            		else if(depth < 5)
	            		{
	                		blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	            		}
        			}
        			else
        			{
	            		if(depth < 3)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	            		}
	            		else if(depth < 6)
	            		{
	                		blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
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
