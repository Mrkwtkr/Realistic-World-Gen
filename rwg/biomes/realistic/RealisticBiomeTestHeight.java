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

public class RealisticBiomeTestHeight extends RealisticBiomeBase
{
    private CellNoise celltest;
    
	public RealisticBiomeTestHeight(int i) 
	{
		super(i, BaseBiomes.baseColdForest);

		celltest = new CellNoise(0, (short)0);
		//celltest.setUseDistance(true);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength)
    {
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border)
    {
		float p = perlin.noise2(x / 100f, y / 100f) * 16;
		p = p > 8f ? 8f : p < -8f ? -8f : p;

		return 80f + p;
    }
	
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise)
    {
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
        			int c = k & 0xf;
        			blocks[(y * 16 + x) * 256 + k] = Blocks.wool;
        			metadata[(y * 16 + x) * 256 + k] = (byte)c;
        		}
            }
		}
    }
}
