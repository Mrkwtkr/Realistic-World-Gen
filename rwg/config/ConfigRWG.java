package rwg.config;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigRWG 
{
	public static Configuration config;
	public static int[] biomeIDs = new int[16];
	
	public static void init(FMLPreInitializationEvent event)
	{
		config = new Configuration(event.getSuggestedConfigurationFile());
		
		for(int c = 0; c < biomeIDs.length; c++)
		{
			biomeIDs[c] = 200 + c;
		}
		
		try 
		{
			config.load();

			//200+
			//biomeIDs[0] = config.get("1 - Beta & Alpha", "Desert", 80).getInt();
		}
		catch (Exception e) 
		{
			for(int c = 0; c < biomeIDs.length; c++)
			{
				biomeIDs[c] = 200 + c;
			}
		}
		finally 
		{
			if (config.hasChanged()) 
			{
				config.save();
			}
		}
	}
}
