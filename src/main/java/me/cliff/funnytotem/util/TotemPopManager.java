package me.cliff.funnytotem.util;

import com.google.common.eventbus.Subscribe;
import me.cliff.funnytotem.event.PacketEvent;
import me.cliff.funnytotem.event.PlaySoundEvent;
import me.cliff.funnytotem.event.TotemPopEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import me.cliff.funnytotem.pop;
import net.minecraft.sound.SoundEvents;


import java.io.IOException;

import static me.cliff.funnytotem.FunnyTotem.mc;

public class TotemPopManager {

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event){
        if (!Wrapper.canUpdate()) return;

        if (event.getPacket() instanceof EntityStatusS2CPacket pac) {
            if (pac.getStatus() == EntityStatuses.USE_TOTEM_OF_UNDYING) {
                Entity ent = pac.getEntity(mc.world);
                if (ent.getType() != EntityType.PLAYER || ent != mc.player) return;
                pop.onTotemPop(new TotemPopEvent());

            }
        }
    }

    @Subscribe
    public static void onPlaySound(PlaySoundEvent event) {
        if (mc.world == null || event.sound == null) return;
        if (SoundEvents.ITEM_TOTEM_USE.getId().equals(event.sound.getId())) {
            event.cancel();
        }
    }

}
