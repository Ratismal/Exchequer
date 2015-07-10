package io.github.ratismal.exchequer;

import io.github.ratismal.exchequer.Config.Config;
import io.github.ratismal.exchequer.util.MessageUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;

public class Exchequer extends JavaPlugin {

	private Config pluginconfig;

	/**
	 * Configs
	 */
	public File blocks = null;
	public FileConfiguration blockConfig = null;
	public File tools = null;
	public FileConfiguration toolConfig = null;

	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		this.pluginconfig = new Config(this,getConfig());

		/* enable metrics */
		if (pluginconfig.isMetrics()) {
			try {
				Metrics metrics = new Metrics(this);
				metrics.start();
			} catch (IOException e) {
				// Failed to submit the stats :-(
				MessageUtil.sendConsoleMessage("Failed to start Metrics!");
			}

		}


	}

	public void onDisable() {
		MessageUtil.sendConsoleMessage("Stopping plugin!");
	}


	public void reloadBlocks() {
		if (blocks == null) {
			blocks = new File(getDataFolder(), "blocks.yml");
		}
		blockConfig = YamlConfiguration.loadConfiguration(blocks);

		// Look for defaults in the jar
		Reader defConfigStream = new InputStreamReader(this.getResource("blocks.yml"));
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			blockConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getBlocks() {
		if (blockConfig == null) {
			reloadBlocks();
		}
		return blockConfig;
	}

	public void saveBlocks() {
		if (blockConfig == null || blocks == null) {
			return;
		}
		try {
			getBlocks().save(blocks);
		} catch (IOException ex) {
			getLogger().log(Level.SEVERE, "Could not save config to " + blocks, ex);
		}
	}

	public void reloadTools() {
		if (tools == null) {
			tools = new File(getDataFolder(), "tools.yml");
		}
		toolConfig = YamlConfiguration.loadConfiguration(tools);

		// Look for defaults in the jar
		Reader defConfigStream = new InputStreamReader(this.getResource("tools.yml"));
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			toolConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getTools() {
		if (toolConfig == null) {
			reloadTools();
		}
		return toolConfig;
	}

	public void saveTools() {
		if (toolConfig == null || tools == null) {
			return;
		}
		try {
			getTools().save(tools);
		} catch (IOException ex) {
			getLogger().log(Level.SEVERE, "Could not save config to " + tools, ex);
		}
	}

}
