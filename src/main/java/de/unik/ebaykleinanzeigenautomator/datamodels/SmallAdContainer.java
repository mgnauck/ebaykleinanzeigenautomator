package de.unik.ebaykleinanzeigenautomator.datamodels;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import de.unik.ebaykleinanzeigenautomator.util.Context;

public class SmallAdContainer
{
	public String contact = "";
	
	public List<SmallAd> smallAdds = new ArrayList<SmallAd>();
	
	public JSONObject toJson()
	{
		JSONArray jsonArray = new JSONArray();
		smallAdds.forEach(a -> jsonArray.put(a.toJson()));
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("contact", contact);
		jsonObject.put("smallAds", jsonArray);
		
		return jsonObject;
	}
	
	public String toString()
	{
		return toJson().toString(4);
	}
	
	public void readFromDisk()
	{
		readFromDisk(Context.get().getSessionIdentifier() + "");
	}

	public void readFromDisk(String sessionIdentifier)
	{
		// TODO
	}

	public void writeToDisk()
	{
		Path workingDirectoryPath = new File(Context.get().getPullPath()).toPath();
		if(!Files.exists(workingDirectoryPath))
		{
			try
			{
				Files.createDirectories(workingDirectoryPath);
			}
			catch (IOException e)
			{
				System.out.println("Failed to create directory " + workingDirectoryPath);
				e.printStackTrace();
			}
		}
		
		Path outputFilePath = workingDirectoryPath.resolve(Context.get().getConfiguration().projectDataFile());
		try
		{
			Files.write(outputFilePath, this.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		}
		catch (IOException e)
		{
			System.out.println("Failed to write file " + outputFilePath);
			e.printStackTrace();
		}
	}
}
