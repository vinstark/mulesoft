package MuleUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import hudson.model.BuildListener;
public class PropertyFileValidator {

	public static void getDescription(Map<String, String> maps,BuildListener listener) {
		Properties prop = new Properties();
		InputStream in;

		try {
			in = new FileInputStream("src/main/resources/config.properties");
			prop.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<String> keys = maps.keySet();
		for (String key : keys) {
			String finalKey = key.substring(key.indexOf("_") + 1, key.length());
			 listener.getLogger().println(prop.getProperty(key) + " for tag_Name : " + key.substring(0, key.indexOf("_"))
				+ " and attribute_Name : " + finalKey);
			/*System.out.println(prop.getProperty(key) + " for tag_Name : " + key.substring(0, key.indexOf("_"))
					+ " and attribute_Name : " + finalKey);*/
		}

	}

}