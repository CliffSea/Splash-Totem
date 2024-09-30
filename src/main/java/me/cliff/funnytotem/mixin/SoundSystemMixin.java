package me.cliff.funnytotem.mixin;


import me.cliff.funnytotem.FunnyTotem;
import me.cliff.funnytotem.event.PlaySoundEvent;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(SoundSystem.class)
public class SoundSystemMixin {

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"),cancellable = true)
    private void onPlay(SoundInstance soundInstance, CallbackInfo info){
        PlaySoundEvent event = PlaySoundEvent.get(soundInstance);
        FunnyTotem.getInstance().getEventBus().post(event);

        if (event.isCancelled()) info.cancel();

    }
}
