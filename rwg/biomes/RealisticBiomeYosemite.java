package rwg.biomes;

import java.util.Random;

import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoEuroPine;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoRedWood;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeYosemite extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeYosemite(int id)
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin) 
	{
		//boulders
		for (int l = 0; l < 8; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 95 && (k1 < 64 || rand.nextInt(15) == 0))
			{
		    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}

		float l = perlin.noise2(chunkX / 120f, chunkY / 120f);
		if(rand.nextInt(l > 0.2f ? 5 : 1) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 75)
			{
				WorldGenerator worldgenerator = rand.nextInt(3) == 0 ? new DecoRedWood(34 + rand.nextInt(10), 20 + rand.nextInt(10), 6, rand.nextInt(2)) : new DecoRedWood(25 + rand.nextInt(5), 13 + rand.nextInt(5), 4, rand.nextInt(2));
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
			else if(z52 < 85)
			{
				WorldGenerator worldgenerator = rand.nextInt(3) == 0 ? new DecoRedWood(22 + rand.nextInt(10), 14 + rand.nextInt(10), 5, rand.nextInt(2)) : new DecoRedWood(25 + rand.nextInt(5), 13 + rand.nextInt(5), 4, rand.nextInt(2));
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}

		for (int b1 = l > 0.2f ? 1 : 4; b1 > -1; b1--)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 100)
			{
				if(l > 0.2f)
				{
					WorldGenerator worldgenerator = new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else
				{
					WorldGenerator worldgenerator = rand.nextInt(10) == 0 ? new WorldGenForest(false, false) : rand.nextInt(8) == 0 ? new DecoPineTree(5, rand.nextInt(2)) : rand.nextInt(4) == 0 ? new WorldGenTrees(false) : new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}

		for (int b1 = 0; b1 < 7; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			if(z52 < 75) { break; }
			if(z52 > 90)
			{
				WorldGenerator worldgenerator = rand.nextInt(3) == 0 ? new DecoPineTree(3, rand.nextInt(2)) : new WorldGenShrub(0, 0);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}

		if(rand.nextInt(3) == 0)
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
		
		if(rand.nextInt(24) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = 50 + rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		for(int f23 = 0; f23 < 2; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = 50 + rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
		}

		for(int l14 = 0; l14 < 8; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 50 + rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(k22 < 90 && rand.nextBoolean())
			{
				(new DecoGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
			}
			else
			{
				(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
			}
		}
	}

	@Override
	public float rNoise(PerlinNoise perlin, int x, int y) 
	{
		float h = perlin.noise2(x / 300f, y / 300f) * 135f;
		h = h < 0f ? 0f : h;
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
		
		if(h < 30f)
		{
			if(h < 0f)
			{
				h = 0f;
			}

			h += perlin.noise2(x / 120f, y / 120f) * (30f - h) * 1f + perlin.noise2(x / 15f, y / 15f) * (30f - h) * 0.1f;
		}
		
		return h + 63f;
	}
	
	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.1f ? true : false;
		boolean clay = c > 1.6f ? true : false;
		boolean gravel = false;
		
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
            		if(cliff)
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
            		else
            		{
		            	if(depth == 0)
		            	{
		            		if(k < 63)
		            		{
		            			gravel = true;
		            			blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
		            		}
		            		else
		            		{
		    	        		blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
		            		}
		            	}
		            	else
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

	@Override
	public float r3Dnoise(float z) 
	{
		return 0;
	}
}
