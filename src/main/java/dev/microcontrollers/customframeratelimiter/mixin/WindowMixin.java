package dev.microcontrollers.customframeratelimiter.mixin;

import dev.microcontrollers.customframeratelimiter.config.CustomFramerateLimiterConfig;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow private int framerateLimit;

    // TODO: this only gets updated when changed in video settings so we need to manually call an update
    @Inject(method = "setFramerateLimit", at = @At("HEAD"), cancellable = true)
    private void setCustomLimiter(int framerateLimit, CallbackInfo ci) {
        if (CustomFramerateLimiterConfig.CONFIG.instance().fpsLimit != 0) {
            this.framerateLimit = CustomFramerateLimiterConfig.CONFIG.instance().fpsLimit;
            ci.cancel();
        }
    }
}
