package com.ryutheghost.themysterybox;

import com.mojang.logging.LogUtils;
import com.ryutheghost.themysterybox.block.creativetab.ModBlocksTab;
import com.ryutheghost.themysterybox.block.registry.ModBlocks;
import com.ryutheghost.themysterybox.item.creativetab.ModItemsTab;
import com.ryutheghost.themysterybox.item.registry.ModItems;
import com.ryutheghost.themysterybox.util.OnLevelJoined;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MainMod.MODID)
public class MainMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mysterybox";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public MainMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        //Register our Blocks and Items
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        //Register our CreativeModeTabs
        ModItemsTab.register(modEventBus);
        ModBlocksTab.register(modEventBus);

        //Register our OnJoinLevelEvent
        MinecraftForge.EVENT_BUS.register(new OnLevelJoined());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("Inserting Mystery Box Inventory Items.");
        LOGGER.info("Mystery Box Filled.");
        LOGGER.info("Sorting Inventory For The Mystery Box.");
        LOGGER.info("Mystery Box Ready!.");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("Inserting Mystery Box Inventory Items.");
            LOGGER.info("Mystery Box Filled.");
            LOGGER.info("Sorting Inventory For The Mystery Box.");
            LOGGER.info("Mystery Box Ready!.");
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.MYSTERY_BOX_BLOCK.get(), RenderType.solid());
        }
    }
}
