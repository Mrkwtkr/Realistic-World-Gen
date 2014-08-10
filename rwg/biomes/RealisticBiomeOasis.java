package rwg.biomes;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import rwg.deco.DecoCacti;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoPalmTree;
import rwg.deco.trees.DecoSavannah;
import rwg.util.CanyonColor;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeOasis extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeOasis(int id)
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, float strength) 
	{
		if(rand.nextInt((int)(2f / strength)) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 65)
		    {
		    	(new WorldGenBlockBlob(Blocks.sandstone, 0)).generate(world, rand, i1, k1, j1);
		    }
		}
		
		for(int b33 = 0; b33 < 12f * strength; b33++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

		    if(z52 < 70)
		    {
				WorldGenerator worldgenerator = rand.nextInt(5) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(4) == 0 ? new DecoSavannah(1): new DecoPalmTree(rand.nextInt(10) + 10);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
		    }
		}

		if(rand.nextInt((int)(2f / strength)) == 0) 
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}
		
		for(int f23 = 0; f23 < 5f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{11,11,9,3,3,3,2,1,1})).generate(world, rand, j15, j17, j20);
		}
		
		for(int k18 = 0; k18 < 15f * strength; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoCacti(true)).generate(world, rand, k21, j23, k24);
		}
		
		for(int i15 = 0; i15 < 3f * strength; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = rand.nextInt(160);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}

		for(int l14 = 0; l14 < 10f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(50) + 50;
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(k22 < 78 && rand.nextInt(4) != 0)
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
	public float rNoise(PerlinNoise perlin, int x, int y, float ocean) 
	{
		float m = perlin.noise2(x / 150f, y / 150f) * 100f;
		float mh = perlin.noise2(x / 150f, y / 150f) * 50f + 10f;
		m = mh > m ? mh : m;
		
		float h = m;
		h += perlin.noise2(x / 50f, y / 50f) * 8;
		h += perlin.noise2(x / 20f, y / 20f) * 4;
		
		return 70f + h;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 0.8f ? true : false;
		float hills = perlin.noise2(i / 25f, j / 25f) + perlin.noise2(i / 2f, j / 2f) * 0.3f;
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

            	if(cliff || (k > 87f && hills > 0.5f - ((k - 87f) / 20f)))
            	{
            		if(depth < 5)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	        			metadata[(y * 16 + x) * 256 + k] = 8;
            		}
            	}
            	else
            	{
            		if(depth == 0)
            		{
            			if(perlin.noise2(i / 12f, j / 12f) > -0.3f + ((k - 66f) / 9f))
            			{
            				if(k < 62)
            				{
            					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            				}
            				else
            				{
            					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
            				}
                		}
            			else
            			{
    	        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
            			}
            		}
            		else if(depth < 6)
            		{
            			if(dirt)
            			{
            				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
            			}
            			else
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
		return 0f;
	}
	
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int i, int dont, int care)
    {
        return ColorizerGrass.getGrassColor(1f, 0.4f);
    }

    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int i, int dont, int care)
    {
        return ColorizerFoliage.getFoliageColor(0.9f, 0.6f);
    }
}
