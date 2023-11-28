package coffeecraft.mod.network.message;

import coffeecraft.mod.effect.CaffeinatedEffect;
import coffeecraft.mod.network.AbstractMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

public class SJitters extends AbstractMessage.Server {
    private int entityID;
    private int jitters;

    public SJitters(FriendlyByteBuf buffer) {
        this.entityID = buffer.readInt();
        this.jitters = buffer.readInt();
    }

    public SJitters(LivingEntity entity, int jitters) {
        this.entityID = entity.getId();
        this.jitters = jitters;
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityID);
        buffer.writeInt(this.jitters);
    }

    @Override
    public void handle(NetworkEvent.Context context, Minecraft minecraft) {
        Entity entity = minecraft.level.getEntity(this.entityID);
        if (entity != null)
            CaffeinatedEffect.EFFECTED.put(entity.getUUID(), this.jitters);
    }

}
