package com.ryutheghost.themysterybox;

import com.mojang.logging.LogUtils;
import com.progwml6.ironchest.IronChests;
import com.ryutheghost.themysterybox.block.creativetab.ModBlocksTab;
import com.ryutheghost.themysterybox.block.registry.MysteryBoxModBlocks;
import com.ryutheghost.themysterybox.item.creativetab.ModItemsTab;
import com.ryutheghost.themysterybox.item.registry.MysteryBoxModItems;
import com.ryutheghost.themysterybox.particles.ModParticles;
import com.ryutheghost.themysterybox.sound.ModSounds;
import com.ryutheghost.themysterybox.util.events.OnLevelJoined;
import com.tiviacz.travelersbackpack.TravelersBackpack;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Objects;

@Mod(MainMod.MODID)
public class MainMod {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "mysterybox";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public MainMod() {
        IEventBus modEventBus = Objects.requireNonNull(FMLJavaModLoadingContext.get()).getModEventBus();

        // Register the commonSetup method for modloading
        Objects.requireNonNull(modEventBus).addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        Objects.requireNonNull(MinecraftForge.EVENT_BUS).register(this);

        // Register our Blocks and Items
        MysteryBoxModItems.register(modEventBus);
        MysteryBoxModBlocks.register(modEventBus);

        // Register our CreativeModeTabs
        ModItemsTab.register(modEventBus);
        ModBlocksTab.register(modEventBus);

        // Register our particles
        ModParticles.register(modEventBus);

        // Register our Sounds
        ModSounds.register(modEventBus);

        // Register our OnJoinLevelEvent
        MinecraftForge.EVENT_BUS.register(new OnLevelJoined());

        // Enable our mod usage after traveler's backpack is finished and loaded
        Objects.requireNonNull(ModList.get()).isLoaded(TravelersBackpack.MODID);
        // Enable our mod usage after iron chests is finished and loaded
        Objects.requireNonNull(ModList.get()).isLoaded(IronChests.MOD_ID);
    }

    @SuppressWarnings("EmptyMethod")
    private void commonSetup(final @Nullable FMLCommonSetupEvent ignoredEvent) {
        // Do any common setup tasks here
    }

    // Use @SubscribeEvent to listen for ServerStartingEvent
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("Inserting Mystery Box Inventory Items.");
        LOGGER.info("Mystery Box Filled.");
        LOGGER.info("Sorting Inventory For The Mystery Box.");
        LOGGER.info("Mystery Box Ready!.");
    }

    // Use EventBusSubscriber to automatically register static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SuppressWarnings("ConstantValue")
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("Inserting Mystery Box Inventory Items.");
            LOGGER.info("Mystery Box Filled.");
            LOGGER.info("Sorting Inventory For The Mystery Box.");
            LOGGER.info("Mystery Box Ready!.");
            if (MysteryBoxModBlocks.MYSTERY_BOX_BLOCK != null) {
                //noinspection deprecation
                ItemBlockRenderTypes.setRenderLayer(MysteryBoxModBlocks.MYSTERY_BOX_BLOCK.get(), RenderType.solid());
            }
        }
    }
}