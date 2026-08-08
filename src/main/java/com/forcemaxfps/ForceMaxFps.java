package com.forcemaxfps;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("forcemaxfps")
public class ForceMaxFps {

    public ForceMaxFps() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        // Client setup
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.options != null) {
                // Force unlimited FPS
                OptionInstance<Integer> maxFps = mc.options.framerateLimit();
                if (maxFps != null) {
                    maxFps.set(260); // High value = basically unlimited
                }

                // Force VSync OFF
                OptionInstance<Boolean> vsync = mc.options.enableVsync();
                if (vsync != null) {
                    vsync.set(false);
                }
            }
        }
    }
}
