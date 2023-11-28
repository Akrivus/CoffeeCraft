package coffeecraft.mod.network.message;

import coffeecraft.mod.effect.CaffeinatedEffect;
import coffeecraft.mod.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class SRemoveJitters extends AbstractMessage.Server {
    private int entityID;

    public SRemoveJitters(FriendlyByteBuf buffer) {
        this.entityID = buffer.readInt();
    }

    public SRemoveJitters(LivingEntity entity) {
        this.entityID = entity.getId();
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityID);
    }

    @Override
    public void handle(NetworkEvent.Context context, Minecraft minecraft) {
        Entity entity = minecraft.level.getEntity(this.entityID);
        if (entity != null)
            CaffeinatedEffect.EFFECTED.remove(entity.getUUID());
    }
}
