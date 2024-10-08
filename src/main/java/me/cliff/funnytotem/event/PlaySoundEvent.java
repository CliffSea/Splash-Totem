package me.cliff.funnytotem.event;

import net.minecraft.client.sound.SoundInstance;

public class PlaySoundEvent extends Event {
    private static final PlaySoundEvent INSTANCE = new PlaySoundEvent();

    public SoundInstance sound;

    public static PlaySoundEvent get(SoundInstance sound) {
        INSTANCE.setCancelled(false);
        INSTANCE.sound = sound;
        return INSTANCE;
    }

    /* Posted on SoundSystemMixin */
}
