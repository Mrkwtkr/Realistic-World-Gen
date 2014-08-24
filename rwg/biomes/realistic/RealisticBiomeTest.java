package rwg.biomes.realistic;

import java.util.Random;

import rwg.biomes.base.BaseBiomes;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoLog;
import rwg.deco.ruins.DecoRuinsAncient;
import rwg.deco.trees.DecoEuroPine;
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
		/*if(rand.nextInt(10) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
			WorldGenerator worldgenerator = new DecoSmallSpruce(rand.nextInt(3));
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);	
		}*/
    	
    	/*if(rand.nextInt(3) == 0)
    	{
			int x22 = chunkX + 16;
			int z22 = chunkY + 16;
			int y22 = world.getHeightValue(x22, z22);
			(new DecoRuinsAcient(Blocks.mossy_cobblestone)).generate(world, rand, x22, y22, z22);	
    	}
    	
    	*/
    	if(rand.nextInt(5) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = world.getHeightValue(x22, z22);
			(new DecoLog(rand.nextInt(2), 3 + rand.nextInt(4), rand.nextBoolean())).generate(world, rand, x22, y22, z22);	
    	}
    	
    	/*int a = 2 + (int)(perlin.noise2(chunkX / 40f, chunkY / 40f) * 2f);
    	a = a < 1 ? 1 : a;
    	if(rand.nextInt(a) == 0)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    (new DecoShrub(8, rand.nextInt(5), rand.nextInt(6))).generate(world, rand, i1, k1, j1);
    	}*/
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean)
    {
		return 70f;
    }
}
