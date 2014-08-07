package rwg;

import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import rwg.biomes.BiomeList;
import rwg.config.ConfigRWG;
import rwg.world.WorldTypeRealistic;
import net.minecraft.world.gen.structure.MapGenStructureIO;

@Mod(modid="RWG", name="RealisticWorldGen", version="1.0.0")
public class RWG
{	
	@Instance("RWG")
	public static RWG instance;
	
	public static final WorldTypeRealistic worldtype = (new WorldTypeRealistic("RWG"));  
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		ConfigRWG.init(event);
		BiomeList.load();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
	}
}