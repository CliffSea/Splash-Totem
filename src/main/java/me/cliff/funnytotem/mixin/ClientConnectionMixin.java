package me.cliff.funnytotem.mixin;

import me.cliff.funnytotem.FunnyTotem;
import me.cliff.funnytotem.event.PacketEvent;
import me.cliff.funnytotem.util.Wrapper;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.network.listener.PacketListener;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {


    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo info) {
        if(!Wrapper.canUpdate()) return;
        PacketEvent.Receive event = new PacketEvent.Receive(packet);
        FunnyTotem.getInstance().getEventBus().post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "handlePacket", at = @At("TAIL"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacketPost(Packet<T> packet, PacketListener listener, CallbackInfo info) {
        if(!Wrapper.canUpdate()) return;
        PacketEvent.ReceivePost event = new PacketEvent.ReceivePost(packet);
        FunnyTotem.getInstance().getEventBus().post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("HEAD"),cancellable = true)
    private void onSendPacketPre(Packet<?> packet, CallbackInfo info) {
        if(!Wrapper.canUpdate()) return;
        PacketEvent.Send event = new PacketEvent.Send(packet);
        FunnyTotem.getInstance().getEventBus().post(event);
        if (event.isCancelled()) info.cancel();
    }

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;)V", at = @At("RETURN"),cancellable = true)
    private void onSendPacketPost(Packet<?> packet, CallbackInfo info) {
        if(!Wrapper.canUpdate()) return;
        PacketEvent.SendPost event = new PacketEvent.SendPost(packet);
        FunnyTotem.getInstance().getEventBus().post(event);
        if (event.isCancelled()) info.cancel();
    }


}
