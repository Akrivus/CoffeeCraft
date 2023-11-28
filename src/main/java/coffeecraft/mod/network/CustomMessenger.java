package coffeecraft.mod.network;

import coffeecraft.mod.CoffeeCraft;
import coffeecraft.mod.network.message.SJitters;
import coffeecraft.mod.network.message.SRemoveJitters;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fmllegacy.network.NetworkRegistry;
import net.minecraftforge.fmllegacy.network.simple.SimpleChannel;

import java.util.function.Function;
import java.util.function.Predicate;

public class CustomMessenger {
    private static final Predicate<String> version = (version) -> version.equals(CoffeeCraft.VERSION);
    private static int messageID = 0;

    public static SimpleChannel create() {
        return NetworkRegistry.ChannelBuilder.named(CoffeeCraft.source(CoffeeCraft.ID)).clientAcceptedVersions(version).serverAcceptedVersions(version).networkProtocolVersion(CoffeeCraft::getVersion).simpleChannel();
    }

    public static void register(IEventBus bus) {
        bus.addListener(CustomMessenger::registerClient);
        bus.addListener(CustomMessenger::registerServer);
    }

    public static void registerServer(FMLCommonSetupEvent e) {

    }

    public static void registerClient(FMLClientSetupEvent e) {
        register(SJitters.class, SJitters::new);
        register(SRemoveJitters.class, SRemoveJitters::new);
    }

    public static <T extends AbstractMessage> void register(Class<T> packet, Function<FriendlyByteBuf, T> con) {
        CoffeeCraft.MESSENGER.messageBuilder(packet, ++CustomMessenger.messageID).decoder(con).encoder(AbstractMessage::prepare).consumer(AbstractMessage::consume).add();
    }
}
