package coffeecraft.mod;

import coffeecraft.mod.block.AddedBlocks;
import coffeecraft.mod.block.entity.AddedBlockEntities;
import coffeecraft.mod.effect.AddedEffects;
import coffeecraft.mod.entity.CustomVillagers;
import coffeecraft.mod.items.AddedItems;
import coffeecraft.mod.network.AbstractMessage;
import coffeecraft.mod.network.CustomMessenger;
import coffeecraft.mod.init.*;
import coffeecraft.mod.items.CoffeeBeanItem;
import coffeecraft.mod.network.CustomSounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

@Mod(CoffeeCraft.ID)
public class CoffeeCraft {
    public static final SimpleChannel MESSENGER = CustomMessenger.create();

    public static final String VERSION = "21.1.31";
    public static final String ID = "coffeecraft";

    public static final CreativeModeTab CreativeModeTab = new CreativeModeTab(CoffeeCraft.ID) {
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(AddedItems.TASSE.get());
        }
    };

    public CoffeeCraft() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onCommonSetup);
        bus.addListener(this::onClientSetup);
        CustomMessenger.register(bus);
        AddedBlocks.REGISTRY.register(bus);
        AddedBlockEntities.REGISTRY.register(bus);
        AddedEffects.REGISTRY.register(bus);
        CoffeeFeatures.REGISTRY.register(bus);
        AddedItems.REGISTRY.register(bus);
        CustomSounds.REGISTRY.register(bus);
        CustomVillagers.POI.REGISTRY.register(bus);
        CustomVillagers.Professions.REGISTRY.register(bus);
    }

    private void onCommonSetup(final FMLCommonSetupEvent e) {
        AddedBlocks.registerPottedPlants();
        AddedItems.registerDispenserBehaviors();
        CoffeeBeanItem.registerBeanToBag();
    }

    private void onClientSetup(final FMLClientSetupEvent e) {
        AddedBlocks.registerRenderTypes();
    }

    public static void send(Player player, AbstractMessage message) {
        if (player instanceof ServerPlayer client)
            MESSENGER.sendTo(message, client.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static void send(AbstractMessage message) {
        MESSENGER.sendToServer(message);
    }

    public static String getVersion() {
        return VERSION;
    }

    public static ResourceLocation source(String value) {
        return new ResourceLocation(ID, value);
    }
}