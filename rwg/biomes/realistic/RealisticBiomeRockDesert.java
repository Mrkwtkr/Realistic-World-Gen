package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoCacti;
import rwg.deco.trees.DecoDeadDesertTrees;
import rwg.deco.trees.DecoShrub;
import rwg.deco.trees.DecoSmallPine;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeRockDesert extends RealisticBiomeBase
{
	public RealisticBiomeRockDesert() 
	{
		super(0, BaseBiomes.baseHotPlains);
	}

    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
		if(rand.nextInt((int)(1f / strength)) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			WorldGenerator worldgenerator = new DecoDeadDesertTrees(rand.nextInt(15) == 0 ? 0 : 1);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
    	
    	if(rand.nextInt((int)(1f / strength)) == 0)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    (new DecoShrub(rand.nextInt(4) + 1, 3, 4, true)).generate(world, rand, i1, k1, j1);
    	}
    	
		for(int b2 = 0; b2 < 2f * strength; b2++)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			(new DecoBlob(Blocks.cobblestone, rand.nextInt(12) == 0 ? 1 : 0)).generate(world, rand, i1, k1, j1);
		}
		
		for(int k18 = 0; k18 < 5f * strength; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int k24 = chunkY + rand.nextInt(16) + 8;
			int j23 = world.getHeightValue(k21, k24);
			(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
		}
		
		for(int i15 = 0; i15 < 5f * strength; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = rand.nextInt(160);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
    }
	
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
		return 70f + (perlin.noise2(x / 30f, y / 30f) * 6) + (perlin.noise2(x / 12f, y / 12f) * 4);
	}
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
    	boolean stone = false;
    	boolean dirt = false;
    	float p = (perlin.noise2(i / 50f, j / 50f) * 0.5f) + (perlin.noise2(i / 8f, j / 8f) * 0.5f) * 0.6f;
    	
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
        			if(p > 0.1f)
        			{
        				stone = true;
        			}
        			if(p < -0.1f)
        			{
        				dirt = true;
        			}
            	}
            	
            	if(stone)
            	{
            		if(depth == 0)
            		{
						blocks[(y * 16 + x) * 256 + k] = rand.nextBoolean() ? Blocks.cobblestone : Blocks.stone;
            		}
            	}
            	else if(dirt)
            	{
	        		if(depth < 3)
	        		{
						blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
						metadata[(y * 16 + x) * 256 + k] = 1;
	        		}
            	}
            	else
            	{
	        		if(depth < 3)
	        		{
						blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
	        		}
	        		else if(depth < 5)
	        		{
						blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        		}
            	}
            }
		}
    }
}
