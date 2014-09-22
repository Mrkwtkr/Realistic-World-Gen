package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.trees.DecoPalmTree;
import rwg.deco.trees.DecoShrub;
import rwg.deco.trees.DecoSmallPine;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeAtoll extends RealisticBiomeBase
{
	public RealisticBiomeAtoll() 
	{
		super(0, BaseBiomes.baseTropicalIsland);
	}

    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    	for(int b1 = 0; b1 < 2; b1++)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    (new DecoShrub(rand.nextInt(5) + 4, 0, 0)).generate(world, rand, i1, k1, j1);
    	}
    	
		for(int t = 0; t < 3; t++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			
			if(z52 < 69)
			{
				WorldGenerator worldgenerator = rand.nextInt(6) == 0 ? new WorldGenBigTree(false) : rand.nextInt(2) == 0 ? new WorldGenTrees(false) : new DecoPalmTree(8 + rand.nextInt(8), false);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
    	float h = perlin.noise2(x / 260f, y / 260f) * 55f;
    	h = h > 17f ? 17f - (h - 17f) : h;
    	
    	h += perlin.noise2(x / 35f, y / 35f) * 5.6f;
    	h += perlin.noise2(x / 25f, y / 25f) * 4.3f;
    	h += perlin.noise2(x / 13f, y / 13f) * 2.8f;
    	
    	return 55f + h;
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
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

        		if(depth == 0)
        		{
        			if(k > 65)
        			{
        				grass = true;
        			}
        		}
        		
        		if(grass)
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
