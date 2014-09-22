package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoLog;
import rwg.deco.DecoWildWheat;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoShrub;
import rwg.deco.trees.DecoSmallPine;
import rwg.deco.trees.DecoSmallSpruce;
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

public class RealisticBiomeTaiga extends RealisticBiomeBase
{
	public RealisticBiomeTaiga(int sub) 
	{
		super(sub, BaseBiomes.baseColdForest);
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(subID == 0) //Hills
		{
	        if(rand.nextInt((int)(15f / strength)) == 0)
			{
				int i2 = chunkX + rand.nextInt(16) + 8;
				int i8 = chunkY + rand.nextInt(16) + 8;
				int l4 = world.getHeightValue(i2, i8);
				if(l4 > 63 && l4 < 105)
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
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 8f + 2f;
			for (int b1 = 0; b1 < l * 2f * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 110)
				{
					WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new DecoSmallSpruce(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new DecoSmallPine(1 + rand.nextInt(3), 4 + rand.nextInt(4)) : new DecoSmallPine(4 + rand.nextInt(6), 5 + rand.nextInt(10));
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
	    	if(l > 0f && rand.nextInt(6) == 0)
	    	{
				int x22 = chunkX + rand.nextInt(16) + 8;
				int z22 = chunkY + rand.nextInt(16) + 8;
				int y22 = world.getHeightValue(x22, z22);
				(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
	    	}
			
	    	for(int b = 0; b < 2f * strength; b++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
			    if(rand.nextInt(10) == 0)
			    {
	    		    (new DecoShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
			    }
			    else
			    {
			    	(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
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
			
			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
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
		else if(subID == 1) //fields
		{
	        if(rand.nextInt((int)(15f / strength)) == 0)
			{
				int i2 = chunkX + rand.nextInt(16) + 8;
				int i8 = chunkY + rand.nextInt(16) + 8;
				int l4 = world.getHeightValue(i2, i8);
				if(l4 > 63 && l4 < 105)
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
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 12f - 3f;
			for (int b1 = 0; b1 < l * 2f * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 110)
				{
					WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new DecoSmallSpruce(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new DecoSmallPine(1 + rand.nextInt(3), 4 + rand.nextInt(4)) : new DecoSmallPine(4 + rand.nextInt(6), 5 + rand.nextInt(10));
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
	    	if(l > 0f && rand.nextInt(6) == 0)
	    	{
				int x22 = chunkX + rand.nextInt(16) + 8;
				int z22 = chunkY + rand.nextInt(16) + 8;
				int y22 = world.getHeightValue(x22, z22);
				(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
	    	}
			
	    	for(int b = 0; b < 2f * strength; b++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
			    if(rand.nextInt(10) == 0)
			    {
	    		    (new DecoShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
			    }
			    else
			    {
			    	(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
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
			
			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
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
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
			for (int b1 = 0; b1 < l * 2f * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new DecoSmallSpruce(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new DecoSmallPine(1 + rand.nextInt(3), 4 + rand.nextInt(4)) : new DecoSmallPine(4 + rand.nextInt(6), 5 + rand.nextInt(10));
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
			
	    	if(l > 0f && rand.nextInt(6) == 0)
	    	{
				int x22 = chunkX + rand.nextInt(16) + 8;
				int z22 = chunkY + rand.nextInt(16) + 8;
				int y22 = world.getHeightValue(x22, z22);
				(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
	    	}
			
	    	for(int b = 0; b < 2f * strength; b++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
			    if(rand.nextInt(10) == 0)
			    {
	    		    (new DecoShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
			    }
			    else
			    {
			    	(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
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
			
			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
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
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
		if(subID == 0) //hills
		{
			float h = perlin.noise2(x / 300f, y / 300f) * 135f;//(35f + 100f * border);
			h *= h / 32f;
			h = h > 150f ? 150f : h;
			
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
			float h = perlin.noise2(x / 300f, y / 300f) * (35f + 100f * border);
			h = h < 0f ? 0f : h;
			h *= h / 32f;
			h = h > 200f ? 200f : h;
	
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
    		//h = h > 3f ? 3f + (h - 3f) / 2f : h; 
			h += perlin.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
			h += perlin.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;
    		
    		return 65f + h;
		}
    }

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise) 
	{
		if(subID == 0) //hills
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
	            		
	        			if(c > 0.45f && c > 1.5f - ((k - 70f) / 65f) + p)
	        			{
	        				cliff = 1;
	        			}
	            		if(c > 1.5f)
	        			{
	        				cliff = 2;
	        			}
	        			if(c < 0.3f + ((k - 120f) / 45f) + p)
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
	            		else if(perlin.noise2(i / 60f, j / 60f) + p * 0.4f > 0.24f && k < 90)
	        			{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	            			metadata[(y * 16 + x) * 256 + k] = 2;
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
		else if(subID == 1) //fields
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
	            		
	        			if(c > 0.45f && c > 1.5f - ((k - 70f) / 65f) + p)
	        			{
	        				cliff = 1;
	        			}
	            		if(c > 1.5f)
	        			{
	        				cliff = 2;
	        			}
	        			if(c < 0.3f + ((k - 120f) / 45f) + p)
	        			{
	        				cliff = 3;
	        			}
	            		
	            		if(cliff == 1)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
	            		}
	            		else if(k < 63)
	            		{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
	            			gravel = true;
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
		else //lakes
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
	            		
	        			if(c > 0.45f && c > 1.5f - ((k - 70f) / 65f) + p)
	        			{
	        				cliff = 1;
	        			}
	            		if(c > 1.5f)
	        			{
	        				cliff = 2;
	        			}
	        			if(c < 0.3f + ((k - 120f) / 45f) + p)
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
	            		else if(perlin.noise2(i / 60f, j / 60f) + p * 0.4f > 0.1f)
	        			{
	            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	            			metadata[(y * 16 + x) * 256 + k] = 2;
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
}