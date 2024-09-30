package me.cliff.funnytotem;

import com.mojang.blaze3d.systems.RenderSystem;
import me.cliff.funnytotem.event.TotemPopEvent;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.util.Identifier;


import static me.cliff.funnytotem.FunnyTotem.MOD_ID;
import static me.cliff.funnytotem.FunnyTotem.mc;

public class pop {

    private static Identifier TEXID = Identifier.of(MOD_ID, "textures/gui/popimg.png");
    private static  long startTime = -1;

    public static void onTotemPop(TotemPopEvent event){
        if (mc.world == null) return;

        SoundManager soundManager = mc.getSoundManager();
        PositionedSoundInstance sound = PositionedSoundInstance.master(FunnyTotem.MY_SOUND_EVENT,1f,5);
        soundManager.play(sound);

        startTime = System.currentTimeMillis();

        HudRenderCallback.EVENT.register((drawContext, tickDeltaManager) -> {
            if (startTime < 0){
                return;
            }

            int sWidth = mc.getWindow().getScaledWidth();
            int sHeight = mc.getWindow().getScaledHeight();

            long cTime = System.currentTimeMillis();
            long eTime = cTime - startTime;

            if (eTime >= 800){
                startTime = -1;
                return;
            }

            float alph = 1.0f - (float) eTime / 800 ;

            System.out.println(alph);

            RenderSystem.setShaderTexture(0,TEXID);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1.0f,1.0f,1.0f,alph);

            drawContext.drawTexture(TEXID,0,0,0,0,sWidth,sHeight,sWidth,sHeight);
            RenderSystem.disableBlend();
        });

    }




}
