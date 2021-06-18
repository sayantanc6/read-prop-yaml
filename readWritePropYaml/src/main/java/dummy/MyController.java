package dummy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.Yaml;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class MyController {
	
	@Autowired
	Properties prop;
	
	HashMap<String, String> mymap; 

	@GetMapping("/readprop")
	public void readprop() throws IOException {
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
	    prop.load(stream); 
	    mymap = Maps.newHashMap(Maps.fromProperties(prop));
	    for (Entry<String, String> entry : mymap.entrySet()) {
			 if (entry.getValue().indexOf("http://") != -1) 
			        System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}
	
	@GetMapping("/readyml")
	public void readyml() throws FileNotFoundException {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.yml");
		Yaml yaml = new Yaml();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(yaml.load(inputStream),LinkedHashMap.class);
		System.out.println(json);
	}
}
