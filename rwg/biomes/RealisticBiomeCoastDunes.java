package rwg.biomes;

import java.util.Random;

import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.trees.DecoPalmTree;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoSavannah;
import rwg.util.CliffCalculator;
import rwg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeCoastDunes extends BiomeGenBase implements RealisticBiome
{
	public RealisticBiomeCoastDunes(int id) 
	{
		super(id);
	}

	@Override
	public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, float strength) 
	{
		if(rand.nextInt((int)(1f / strength)) == 0)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int l22 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i17, l22);
		    
		    if(k1 > 68)
		    {
		    	(new DecoGrass(Blocks.deadbush, 0)).generate(world, rand, i17, k1, l22);
		    }
		}
		
		for(int l14 = 0; l14 < 22f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 60 + rand.nextInt(25);
			int j24 = chunkY + rand.nextInt(16) + 8;

			int r = rand.nextInt(7);
			if(r < 2)
			{
				(new DecoGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
			}
			else if(r < 5 && strength > 0.5f)
			{
				(new DecoGrass(Blocks.leaves, 4)).generate(world, rand, l19, k22, j24);
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
		ocean -= 0.45f;
		ocean *= 10f;
		ocean += (perlin.noise2(x / 50f, y / 50f) * 0.15f) + (perlin.noise2(x / 20f, y / 20f) * 0.07f);
		
		float h = 10f - (1f - (ocean)) * 17f;
		
		if(ocean > 0.5f)
		{
			float power = (ocean - 0.5f) * 60f;
			power = power > 7f ? 7f : power;

			h += power;
			h += perlin.noise2(x / 5f, y / 5f) * power * 0.3f;
			h += perlin.noise2(x / 10f, y / 10f) * power * 0.7f;
			h += perlin.noise2(x / 20f, y / 20f) * power * 1.2f;
		}

		return 65f + h;
	}

	@Override
	public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, float[] noise) 
	{
		float cliff = CliffCalculator.calc(x, y, noise) + (perlin.noise2(x / 8f, y / 8f) * 1f) + (perlin.noise2(x / 2f, y / 2f) * 0.8f);
		
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
        		if(k > 66 && cliff > 0.6f)
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

	@Override
	public float r3Dnoise(float z) 
	{
		return 0;
	}
}
