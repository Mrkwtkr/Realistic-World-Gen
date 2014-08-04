package rwg.util;

public class CliffCalculator 
{
	public static float calc(int x, int y, float[] noise)
	{
		float cliff = 0f;
		if(x > 0)
		{
			cliff = test(cliff, Math.abs(noise[y * 16 + x] - noise[y * 16 + x - 1]));
		}
		if(y > 0)
		{
			cliff = test(cliff, Math.abs(noise[y * 16 + x] - noise[(y - 1) * 16 + x]));
		}
		if(x < 15)
		{
			cliff = test(cliff, Math.abs(noise[y * 16 + x] - noise[y * 16 + x + 1]));
		}
		if(y < 15)
		{
			cliff = test(cliff, Math.abs(noise[y * 16 + x] - noise[(y + 1) * 16 + x]));
		}
		return cliff;
	}
	
	private static float test(float cliff, float value)
	{
		if(value > cliff)
		{
			return value;
		}
		return cliff;
	}
}
