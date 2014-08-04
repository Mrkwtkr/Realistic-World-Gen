package rwg.deco.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoBigBirch extends WorldGenerator
{
	public DecoBigBirch()
	{
	}

	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	int height = 17;
    	int branches = 7;
    	int branchLenght = 10;
    	
    	for(int i = 0; i <= height; i++)
    	{
    		world.setBlock(x, y + i, z, Blocks.log, 2, 0);
    	}
    	createTrunk(world, rand, x, y, z);
    	
    	int dir = rand.nextInt((int)(360f / branches));
    	int bl;
    	float xd, yd, zd, c;
    	int l;
    	for(int b = 0; b < branches; b++)
    	{
    		c = 0;
    		dir += (int)(360f / branches) * (0.9f + rand.nextFloat() * 0.2f);
			xd = (float)Math.cos(dir * Math.PI / 180f);
			yd = (float)Math.sin(dir * Math.PI / 180f);
			zd = rand.nextFloat() * 1.5f + 0.5f;
			l = branchLenght - rand.nextInt((int)(branchLenght / 2f));
    		
    		while(c < l)
    		{
    			c++;
    			for(int zzd = 0; zzd < zd; zzd++)
    			{
            		world.setBlock(x + (int)(c * xd), y + height + (int)(c * zd) + zzd, z + (int)(c * yd), Blocks.log, 14, 0);
    			}
    		}
    	}
    	
		return true;
	}
    
    private void createTrunk(World world, Random rand, int x, int y, int z)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1};
    	int sh;
    	for(int t = 0; t < 5; t++)
    	{    	
    		sh = rand.nextInt(3) + y;
    		while(sh > y - 3)
    		{
    			world.setBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1], Blocks.log, 14, 0);
    			sh--;
    		}
    	}
    }
}
