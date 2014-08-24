package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoWildWheat;
import rwg.deco.trees.DecoSmallSpruce;
import rwg.deco.trees.DecoPineTree;
import rwg.util.CellNoise;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeSnow extends RealisticBiomeBase
{
	public RealisticBiomeSnow(int sub) 
	{
		super(sub, BaseBiomes.baseSnowForest);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(subID == 0) //Hills
		{
			//boulders
			for (int l = 0; l < 8f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95 && (k1 < 64 || rand.nextInt(15) == 0))
				{
			    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			//trees
			boolean bush = false;
			float l = perlin.noise2(chunkX / 60f, chunkY / 60f) * 9f + 4f;
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
						WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? /*new DecoPineTree(4, rand.nextInt(2))*/ new DecoSmallSpruce(rand.nextInt(3)) : new WorldGenShrub(0, 0);
						worldgenerator.setScale(1.0D, 1.0D, 1.0D);
						worldgenerator.generate(world, rand, j6, z52, k10);
					}
				}
				else if(z52 < 130)
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? /*new DecoPineTree(3, rand.nextInt(2))*/ new DecoSmallSpruce(rand.nextInt(3)) : new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
	
			if(rand.nextInt((int)(2f / strength)) == 0)
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
			
			if(bush)
			{
				if(rand.nextInt((int)(25f / strength)) == 0)
				{
					int j16 = chunkX + rand.nextInt(16) + 8;
					int j18 = rand.nextInt(128);
					int j21 = chunkY + rand.nextInt(16) + 8;
					(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
				}
			}
			
			for(int f23 = 0; f23 < 1f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
			}
		}
		else if(subID == 1) //fields
		{
			//boulders
			for (int l = 0; l < 1f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95)
				{
			    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			//trees
			float t = perlin.noise2(chunkX / 300f, chunkY / 300f) * 2 + perlin.noise2(chunkX / 40f, chunkY / 40f);
			boolean field = t < 0.1f ? true : false;
			
			for (int b1 = 0; b1 < 8f * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 100)
				{
					if(!field)
					{
						WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoPineTree(3, rand.nextInt(2)) : new WorldGenShrub(0, 0);
						worldgenerator.setScale(1.0D, 1.0D, 1.0D);
						worldgenerator.generate(world, rand, j6, z52, k10);
					}
					else if(rand.nextInt(20) == 0)
					{
						WorldGenerator worldgenerator = rand.nextInt(12) == 0 ? new DecoPineTree(3, rand.nextInt(2)) : new WorldGenShrub(0, 0);
						worldgenerator.setScale(1.0D, 1.0D, 1.0D);
						worldgenerator.generate(world, rand, j6, z52, k10);
					}
				}
				else if(z52 < 130)
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoPineTree(2, rand.nextInt(2)) : new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
	
			if(rand.nextInt((int)(2f / strength)) == 0)
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
			
			if(field)
			{
				if(rand.nextInt((int)(20f / strength)) == 0)
				{
					int j16 = chunkX + rand.nextInt(16) + 8;
					int j18 = rand.nextInt(128);
					int j21 = chunkY + rand.nextInt(16) + 8;
					(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
				}
			}
			
			for(int f23 = 0; f23 < 1f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
			}
		}
		else //lakes
		{
	        if(rand.nextInt((int)(15f / strength)) == 0)
			{
				int i2 = chunkX + rand.nextInt(16) + 8;
				int i8 = chunkY + rand.nextInt(16) + 8;
				int l4 = world.getHeightValue(i2, i8);
				if(l4 > 63)
				{
					(new WorldGenLakes(Blocks.water)).generate(world, rand, i2, l4, i8);
				}
			}
	        
			//boulders
			for (int l = 0; l < 3f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95 && (k1 < 64 || rand.nextInt(7) == 0))
				{
			    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			//trees
			boolean bush = false;
			float l = perlin.noise2(chunkX / 60f, chunkY / 60f) * 12f + 4f;
			if(l < 0f) { l = 1; bush = true; }
			for (int b1 = 0; b1 < l * 2f * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(bush)
				{
					WorldGenerator worldgenerator = new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoPineTree(3 + rand.nextInt(2), rand.nextInt(2)) : new WorldGenShrub(0, 0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
			if(rand.nextInt((int)(2f / strength)) == 0)
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
			
			if(bush)
			{
				if(rand.nextInt((int)(10f / strength)) == 0)
				{
					int j16 = chunkX + rand.nextInt(16) + 8;
					int j18 = rand.nextInt(128);
					int j21 = chunkY + rand.nextInt(16) + 8;
					(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
				}
			}
			
			for(int f23 = 0; f23 < 1f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
			}
		}
    }
	
	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {
		if(subID == 0) //hills
		{
			float h = perlin.noise2(x / 300f, y / 300f) * 135f;
			h *= h / 32f;
			
			float bn = 0f;
			if(h < 1f)
			{
				bn = 1f - h;
				for(int i = 0; i < 3; i++)
				{
					bn *= bn * 1.25f;
				}
				
				bn = bn > 3f ? 3f : bn;
			}
			
			if(h < 3f)
			{
				h += perlin.noise2(x / 13f, y / 13f) * (bn + 3f - h) * 0.8f;
			}
	
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
			
			return h + 63f - bn;
		}
		else if(subID == 1) //fields
		{
			float h = perlin.noise2(x / 300f, y / 300f) * 130f;
			h = h < 0f ? 0f : h;
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
			
			h += perlin.noise2(x / 120f, y / 120f) * 8f;
    		h += perlin.noise2(x / 34f, y / 34f) * 4;
    		h += perlin.noise2(x / 17f, y / 17f) * 2;
			
			return h + 70f;
		}
		else //lakes
		{
    		float h = perlin.noise2(x / 300f, y / 300f) * 40f;
    		h = h > 3f ? 3f + (h - 3f) / 2f : h; 
			h += perlin.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
			h += perlin.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;
    		
    		return 65f + h;
		}
    }
	
	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		int cliff = 0;
		float p = perlin.noise2(i / 8f, j / 8f) * 0.5f;
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
            		if(k < 63)
            		{
            			gravel = true;
            		}
            		
        			if(c > 0.45f && c > 1.5f - ((k - 60f) / 65f) + p)
        			{
        				cliff = 1;
        			}
            		if(c > 1.4f)
        			{
        				cliff = 2;
        			}
        			if(c < 0.3f + ((k - 80f) / 40f) + p)
        			{
        				cliff = 3;
        			}
            		
            		if(cliff == 1)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
        				metadata[(y * 16 + x) * 256 + k] = 9; 
            		}
            		else if(cliff == 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
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
            	else if(depth < 6)
        		{
            		if(cliff == 1)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone; 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
        				metadata[(y * 16 + x) * 256 + k] = 9; 
            		}
            		else if(cliff == 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            		}
            		else if(gravel)
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
