package dev.microcontrollers.customframeratelimiter;

import dev.microcontrollers.customframeratelimiter.config.CustomFramerateLimiterConfig;
import net.fabricmc.api.ModInitializer;

public class CustomFramerateLimiter implements ModInitializer {
	@Override
	public void onInitialize() {
		CustomFramerateLimiterConfig.CONFIG.load();
	}
}