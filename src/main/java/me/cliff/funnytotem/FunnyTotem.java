package me.cliff.funnytotem;

import com.google.common.eventbus.EventBus;
import me.cliff.funnytotem.event.TotemPopEvent;
import me.cliff.funnytotem.util.TotemPopManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class FunnyTotem implements ClientModInitializer {

    private static FunnyTotem INSTANCE;
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    public static final String MOD_ID = "funnytotem";

    private final EventBus EVENT_BUS = new EventBus();
    private final TotemPopManager POP_MANAGER = new TotemPopManager();


    public static final Identifier MY_SOUND_ID = Identifier.of(MOD_ID,"boom");
    public static SoundEvent MY_SOUND_EVENT = SoundEvent.of(MY_SOUND_ID);

    public static FunnyTotem getInstance() {
        return INSTANCE;
    }

    public FunnyTotem(){
        INSTANCE = this;
    }

    @Override
    public void onInitializeClient() {
        EVENT_BUS.register(POP_MANAGER);

        Registry.register(Registries.SOUND_EVENT, FunnyTotem.MY_SOUND_ID, MY_SOUND_EVENT);
    }



    public EventBus getEventBus() {
        return EVENT_BUS;
    }
}
