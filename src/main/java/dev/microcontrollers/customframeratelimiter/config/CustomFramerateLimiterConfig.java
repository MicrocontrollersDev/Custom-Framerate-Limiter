package dev.microcontrollers.customframeratelimiter.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.controllers.string.number.IntegerFieldController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class CustomFramerateLimiterConfig {
    public static final ConfigClassHandler<CustomFramerateLimiterConfig> CONFIG = ConfigClassHandler.createBuilder(CustomFramerateLimiterConfig.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("customframeratelimiter.json"))
                    .build())
            .build();


    @SerialEntry public int fpsLimit = 0;

    public static Screen configScreen(Screen parent) {
        return YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) -> builder
                .title(Text.translatable("custom-framerate-limiter.custom-framerate-limiter"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("custom-framerate-limiter.custom-framerate-limiter"))
                        .option(Option.<Integer>createBuilder()
                                .name(Text.translatable("custom-framerate-limiter.set-framerate"))
                                .description(OptionDescription.of(Text.translatable("custom-framerate-limiter.set-framerate.description")))
                                .binding(defaults.fpsLimit, () -> config.fpsLimit, newVal -> config.fpsLimit = newVal)
                                .customController(IntegerFieldController::new)
                                .build())
                        .build())
        )).generateScreen(parent);
    }
}
