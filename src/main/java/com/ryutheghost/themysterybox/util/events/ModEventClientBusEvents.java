package com.ryutheghost.themysterybox.util.events;

import com.ryutheghost.themysterybox.MainMod;
import com.ryutheghost.themysterybox.particles.ConfettiParticles;
import com.ryutheghost.themysterybox.particles.ModParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MainMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventClientBusEvents {
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.CONFETTI_PARTICLES.get(), ConfettiParticles.Provider::new);
    }
}
