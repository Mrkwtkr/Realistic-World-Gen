package rwg.world;

import rwg.RWG;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class ProviderRealistic extends WorldProvider
{
	@Override
    protected void registerWorldChunkManager()
    {
		if(this.worldObj.getWorldInfo().getTerrainType() == RWG.worldtype)
		{
			worldChunkMgr = new ChunkManagerRealistic(worldObj);
		}
		else
		{
			worldChunkMgr = terrainType.getChunkManager(worldObj);
		}
    }

	@Override
    public IChunkProvider createChunkGenerator()
    {		
		if(terrainType == RWG.worldtype)
		{
			return new ChunkGeneratorRealistic(worldObj, worldObj.getSeed());
		}
		return terrainType.getChunkGenerator(this.worldObj, worldObj.getWorldInfo().getGeneratorOptions());
    }
	
	@Override
    public float getCloudHeight()
    {
		if(terrainType == RWG.worldtype)
		{
			return 256F;
		}
		return super.getCloudHeight();
    }

	@Override
	public String getDimensionName() 
	{
		return "Overworld";
	}
}
