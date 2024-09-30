package me.cliff.funnytotem.util;

import me.cliff.funnytotem.FunnyTotem;

public class Wrapper {

    public static boolean canUpdate() {
        return FunnyTotem.mc != null && FunnyTotem.mc.world != null && FunnyTotem.mc.player != null;
    }

}
