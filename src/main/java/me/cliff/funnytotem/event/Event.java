package me.cliff.funnytotem.event;

import net.minecraft.client.MinecraftClient;

public class Event {


    private boolean cancelled;

    public Event() {
    }

    public boolean isCancelled() {
        return this.cancelled;
    }


    public void cancel() {
        cancelled = true;
    }

    public void setCancelled(boolean cancel){
        cancelled = cancel;
    }


}
