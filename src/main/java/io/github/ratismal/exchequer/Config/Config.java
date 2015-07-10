package io.github.ratismal.exchequer.Config;

import io.github.ratismal.exchequer.Exchequer;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	Exchequer plugin;
	FileConfiguration pluginconfig;

	/**
	 * Booleans
	 */
	private boolean metrics = true;

	/**
	 * Config constructor
	 * @param exchequer - Exchequer class
	 * @param pluginconfig - Plugin config
	 */
	public Config(Exchequer exchequer, FileConfiguration pluginconfig) {
		this.plugin = exchequer;
		this.pluginconfig = pluginconfig;
	}

	public void reload() {
		this.metrics = pluginconfig.getBoolean("metrics");
	}

	/**
	 * Returns
	 */
	/**
	 * Booleans
	 */
	public boolean isMetrics() {return metrics;}

}
