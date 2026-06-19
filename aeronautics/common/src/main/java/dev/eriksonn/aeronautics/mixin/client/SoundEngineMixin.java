package dev.eriksonn.aeronautics.mixin.client;

import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {
    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    private void blockAeronauticsSounds(SoundInstance sound, CallbackInfo ci) {
        if (sound == null) return;
        ResourceLocation id = sound.getLocation();
        if (id == null) return;
        String idString = id.toString();
        if (idString.equals("aeronautics:block.steam_vent.head")
                || idString.equals("aeronautics:block.hot_air_burner.head")) {
            ci.cancel();
        }
    }
}