package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoJungleCane;
import rwg.deco.DecoLog;
import rwg.deco.ruins.DecoRuinsAncient;
import rwg.deco.trees.DecoEuroPine;
import rwg.deco.trees.DecoLargePine;
import rwg.deco.trees.DecoSmallCocoa;
import rwg.deco.trees.DecoSmallJungle;
import rwg.deco.trees.DecoSmallPine;
import rwg.deco.trees.DecoSmallSpruce;
import rwg.deco.trees.DecoPineTree;
import rwg.deco.trees.DecoShrub;
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
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeTest extends RealisticBiomeBase
{
	public RealisticBiomeTest() 
	{
		super(0, BaseBiomes.baseColdForest);
	}
	
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {

    	if(rand.nextInt(4) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = 64 + rand.nextInt(64);
			(new DecoJungleCane(2)).generate(world, rand, x22, y22, z22);	
    	}
    	
		for (int b1 = 0; b1 < 3; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			WorldGenerator worldgenerator = rand.nextInt(3) == 0 ? new DecoSmallCocoa() : new DecoSmallJungle();
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
    	
		/*if(rand.nextInt(1) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			WorldGenerator worldgenerator = new DecoLargePine(18 + rand.nextInt(10), 20 + rand.nextInt(10));
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);	
		}
    	
		for (int b1 = 0; b1 < 2; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new DecoSmallSpruce(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new DecoSmallPine(1 + rand.nextInt(3), 4 + rand.nextInt(4)) : new DecoSmallPine(4 + rand.nextInt(6), 5 + rand.nextInt(10));
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
		
    	if(rand.nextInt(5) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = world.getHeightValue(x22, z22);
			(new DecoLog(rand.nextInt(2), 3 + rand.nextInt(4), rand.nextBoolean())).generate(world, rand, x22, y22, z22);	
    	}
    	
    	int a = 2 + (int)(perlin.noise2(chunkX / 40f, chunkY / 40f) * 2f);
    	a = a < 1 ? 1 : a;
    	if(rand.nextInt(a) == 0)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    (new DecoShrub(8, rand.nextInt(5), rand.nextInt(6))).generate(world, rand, i1, k1, j1);
    	}
    	
		for(int l14 = 0; l14 < 10; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}*/
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {
		//float h = perlin.noise2(x / 300f, y / 300f) * 40f;
		//h = h > 3f ? 3f + (h - 3f) / 2f : h; 
		//h += perlin.noise2(x / 50f, y / 50f) * (12f - h) * 0.4f;
		//h += perlin.noise2(x / 15f, y / 15f) * (12f - h) * 0.15f;
		
		return 65f;// + h;
    }
}
