package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoLog;
import rwg.deco.DecoWildWheat;
import rwg.deco.trees.DecoBigTree;
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
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeTaigaHills extends RealisticBiomeBase
{
	public RealisticBiomeTaigaHills(int sub) 
	{
		super(sub, BaseBiomes.baseColdForest);
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(subID == 0) //shield hills
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
	        
			for (int l = 0; l < 1f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95)
				{
			    	(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 5f + 2f;
			for (int b1 = 0; b1 < l * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 90)
				{
					WorldGenerator worldgenerator = rand.nextInt(6) != 0 ? new DecoPineTree(4, rand.nextInt(2)) : rand.nextInt(3) != 0 ? new DecoSmallPine(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0) : new DecoSmallSpruce(1 + rand.nextInt(2));
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else if(z52 < 120)
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoSmallPine(1 + rand.nextInt(3), 3 + rand.nextInt(5), 0) : new DecoSmallSpruce(rand.nextInt(2));
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
	    		
			for(int f24 = 0; f24 < 3f * strength; f24++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 110)
				{
					(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
				}
	    	}
			
			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
	    	
			if(rand.nextInt((int)(20f / strength)) == 0)
			{
				int j16 = chunkX + rand.nextInt(16) + 8;
				int j18 = rand.nextInt(100);
				int j21 = chunkY + rand.nextInt(16) + 8;
				(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
			}
	    	
			for(int l14 = 0; l14 < 8f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = rand.nextInt(128);
				int j24 = chunkY + rand.nextInt(16) + 8;
				(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
			}
		}
		else if(subID == 1) //rivers
		{
			for (int l = 0; l < 6f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95 && (k1 < 64 || rand.nextInt(15) == 0))
				{
			    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 12f + 4f;
			for (int b1 = 0; b1 < l * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 90)
				{
					WorldGenerator worldgenerator = rand.nextInt(10) != 0 ? new DecoPineTree(4, rand.nextInt(4) == 0 ? 1 : 0) : rand.nextInt(3) != 0 ? new DecoSmallPine(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0) : new DecoSmallSpruce(rand.nextInt(2) + 1);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else if(z52 < 120)
				{
					WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new DecoSmallPine(1 + rand.nextInt(3), 3 + rand.nextInt(5), 0) : new DecoSmallSpruce(rand.nextInt(2));
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}

	    	if(rand.nextInt((int)(4f / strength)) == 0)
	    	{
				int x22 = chunkX + rand.nextInt(16) + 8;
				int z22 = chunkY + rand.nextInt(16) + 8;
				int y22 = world.getHeightValue(x22, z22);
				if(y22 < 100)
				{
					(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
				}
	    	}
	    		
			for(int f24 = 0; f24 < 4f * strength; f24++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 110)
				{
					(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
				}
	    	}

			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
	    	
			if(rand.nextInt((int)(20f / strength)) == 0)
			{
				int j16 = chunkX + rand.nextInt(16) + 8;
				int j18 = rand.nextInt(100);
				int j21 = chunkY + rand.nextInt(16) + 8;
				(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
			}
	    	
			for(int l14 = 0; l14 < 12f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = rand.nextInt(128);
				int j24 = chunkY + rand.nextInt(16) + 8;
				(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
			}
		}
		else if(subID == 2) //spikes
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
	        
			for (int l = 0; l < 1f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95 && rand.nextInt(2) == 0)
				{
			    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 14f + 4f;
			for (int b1 = 0; b1 < l * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 90)
				{
					WorldGenerator worldgenerator = rand.nextInt(10) != 0 ? new DecoPineTree(4, rand.nextInt(4) == 0 ? 1 : 0) : rand.nextInt(3) != 0 ? new DecoSmallPine(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0) : new DecoSmallSpruce(rand.nextInt(2) + 1);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else if(z52 < 120)
				{
					WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new DecoSmallPine(1 + rand.nextInt(3), 3 + rand.nextInt(5), 0) : new DecoSmallSpruce(rand.nextInt(2));
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}

	    	if(rand.nextInt((int)(3f / strength)) == 0)
	    	{
				int x22 = chunkX + rand.nextInt(16) + 8;
				int z22 = chunkY + rand.nextInt(16) + 8;
				int y22 = world.getHeightValue(x22, z22);
				if(y22 < 100)
				{
					(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
				}
	    	}
	    		
			for(int f24 = 0; f24 < 4f * strength; f24++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 110)
				{
					(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
				}
	    	}

			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
	    	
			if(rand.nextInt((int)(20f / strength)) == 0)
			{
				int j16 = chunkX + rand.nextInt(16) + 8;
				int j18 = rand.nextInt(100);
				int j21 = chunkY + rand.nextInt(16) + 8;
				(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
			}
	    	
			for(int l14 = 0; l14 < 12f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = rand.nextInt(128);
				int j24 = chunkY + rand.nextInt(16) + 8;
				(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
			}
		}
		else //mix
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
	        
			for (int l = 0; l < 1f * strength; ++l)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 95 && rand.nextInt(2) == 0)
				{
			    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
				}
			}
			
			float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 10f + 3f;
			for (int b1 = 0; b1 < l * strength; b1++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 90)
				{
					WorldGenerator worldgenerator = rand.nextInt(12) == 0 ? new WorldGenBigTree(false) : rand.nextInt(3) != 0 ? new DecoPineTree(4, rand.nextInt(4) == 0 ? 1 : 0) : rand.nextInt(5) == 0 ? new DecoSmallPine(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0) : rand.nextInt(6) != 0 ? new WorldGenTrees(false) : new WorldGenForest(false, false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
				else if(z52 < 120)
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new DecoSmallPine(2 + rand.nextInt(3), 4 + rand.nextInt(5), 0) : rand.nextInt(6) != 0 ? new WorldGenTrees(false) : new WorldGenForest(false, false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}

	    	if(rand.nextInt((int)(3f / strength)) == 0)
	    	{
				int x22 = chunkX + rand.nextInt(16) + 8;
				int z22 = chunkY + rand.nextInt(16) + 8;
				int y22 = world.getHeightValue(x22, z22);
				if(y22 < 100)
				{
					(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
				}
	    	}
	    		
			for(int f24 = 0; f24 < 4f * strength; f24++)
	    	{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
			    int k1 = world.getHeightValue(i1, j1);
				if(k1 < 110)
				{
					(new DecoShrub(rand.nextInt(4) + 1, 0, rand.nextBoolean() ? 0 : 2)).generate(world, rand, i1, k1, j1);
				}
	    	}

			if(rand.nextInt((int)(150f / strength)) == 0)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(60) + 60;
				int k24 = chunkY + rand.nextInt(16) + 8;
				(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
			}
	    	
			if(rand.nextInt((int)(20f / strength)) == 0)
			{
				int j16 = chunkX + rand.nextInt(16) + 8;
				int j18 = rand.nextInt(100);
				int j21 = chunkY + rand.nextInt(16) + 8;
				(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
			}
			
			for(int f23 = 0; f23 < 4f * strength; f23++)
			{
				int j15 = chunkX + rand.nextInt(16) + 8;
				int j17 = rand.nextInt(128);
				int j20 = chunkY + rand.nextInt(16) + 8;
				(new DecoFlowers(new int[]{9,0,3,8,10})).generate(world, rand, j15, j17, j20);
			}
	    	
			for(int l14 = 0; l14 < 12f * strength; l14++)
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
		if(subID == 0) //shield hills
		{
			float h = perlin.noise2(x / 300f, y / 300f) * 130f;
			h *= h > -30f ? ((h + 30f) / 70f) + 1f : 1f;
			h = h > 92f ? 92f : h < -50f ? -50f : h;
	
			if(h > -40f)
			{
				float d = (h + 40f) / 2f > 8f ? 8f : (h + 40f) / 2f;
				h += perlin.noise2(x / 25f, y / 25f) * d;
				h += perlin.noise2(x / 50f, y / 50f) * d * 0.5f;
	
				if(h > -15f)
				{
					float d2 = (h + 15f) / 1.5f > 30f ? 30f : (h + 15f) / 1.5f;
					h += cell.noise(x / 35D, y / 35D, 1D) * d2;
				}
			}
			
			h += perlin.noise2(x / 18f, y / 18f) * 4;
			h += perlin.noise2(x / 8f, y / 8f) * 2;
			
			return h + 120f;
		}
		else if(subID == 1) //rivers
		{
    		float h = perlin.noise2(x / 300f, y / 300f) * 135f;
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
			
    		if(h > 10f)
    		{
    			float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
    			h += perlin.noise2(x / 35f, y / 35f) * d;
    			h += perlin.noise2(x / 60f, y / 60f) * d * 0.5f;

    			if(h > 35f)
    			{
    				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
    				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
    			}
    		}

			if(h > 2f)
			{
				float d = (h - 2f) / 2f > 4f ? 4f : (h - 2f) / 2f;
	    		h += perlin.noise2(x / 28f, y / 28f) * d;
	    		h += perlin.noise2(x / 18f, y / 18f) * (d / 2f);
	    		h += perlin.noise2(x / 8f, y / 8f) * (d / 2f);
			}
    		
    		return h + 63f - bn;
		}
		else //spikes and mix
		{
			float b = (12f + (perlin.noise2(x / 300f, y / 300f) * 6f));
			float h = cell.noise(x / 200D, y / 200D, 1D) * b;
			h *= h * 1.5f;// / 2f;
			h = h > 155f ? 155f : h;
			
    		if(h > 2f)
    		{
    			float d = (h - 2f) / 2f > 8f ? 8f : (h - 2f) / 2f;
    			h += perlin.noise2(x / 30f, y / 30f) * d;
    			h += perlin.noise2(x / 50f, y / 50f) * d * 0.5f;

    			if(h > 35f)
    			{
    				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
    				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
    			}
    		}
    		
    		h += perlin.noise2(x / 18f, y / 18f) * 3;
    		h += perlin.noise2(x / 8f, y / 8f) * 2;
					
			return 45f + h + (b * 2);
		}
    }

    @Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise) 
    {
		float p = perlin.noise2(i / 8f, j / 8f) * 0.5f;
		float c = CliffCalculator.calc(x, y, noise);
		int cliff = 0;
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
            		
        			if((subID == 0 && p > 0.25f - ((k - 50) / 130f)) || (c > 0.45f && c > 1.5f - ((k - 60f) / 65f) + p))
        			{
        				cliff = 1;
        			}
            		if(c > 1.5f)
        			{
        				cliff = 2;
        			}
        			if(k > 110 + (p * 4) && c < 0.3f + ((k - 100f) / 50f) + p)
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
            		else if(perlin.noise2(i / 50f, j / 50f) + p * 0.6f > 0.24f)
        			{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            			metadata[(y * 16 + x) * 256 + k] = 2;
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