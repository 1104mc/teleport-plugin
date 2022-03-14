package me.peterhorvath.teleport.model;


import me.peterhorvath.teleport.Teleport;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

public class Config {
    protected File configFile;
    private JSONObject content;

    public Config(String filename, Teleport plugin) {
        configFile = new File(plugin.getDataFolder(), filename + ".json");
        if(!configFile.exists()) {
            try{
                configFile.createNewFile();
            } catch (IOException exception) {
                plugin.getLogger().log(Level.CONFIG, "Can't create config file!");
            }
        }
        try{
            this.ReadJSON();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    private void ReadJSON() throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(configFile.getPath())));
        content = new JSONObject(text);
    }

    public JSONObject getContent() {
        return content;
    }
}
