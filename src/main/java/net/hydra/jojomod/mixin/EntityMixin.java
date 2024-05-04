package net.hydra.jojomod.mixin;

import net.hydra.jojomod.RoundaboutMod;
import net.hydra.jojomod.networking.MyComponents;
import net.hydra.jojomod.networking.component.StandUserComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public class EntityMixin {

    /** Honestly, I don't know if this code actually does anything significant, but allegedly it updates
     * the position when a stand is teleported. Mount code in vanilla does this, so it is a safety
     * measurement. Injects into the Entity Class.*/
    @Inject(method = "teleportPassengers", at = @At(value = "TAIL"))
    private void teleportPassengersRoundabout(CallbackInfo ci) {
        if (((Entity) (Object) this) instanceof LivingEntity) {
            LivingEntity living = ((LivingEntity) (Object) this);
            StandUserComponent standUserData = MyComponents.STAND_USER.get(living);
            if (standUserData.hasStandOut()) {
                standUserData.updateStandOutPosition(standUserData.getStand(), Entity::refreshPositionAfterTeleport);
            }
        }
    }


    @Inject(method = "hasNoGravity", at = @At(value = "HEAD"), cancellable = true)
    public void roundaboutHasNoGravity(CallbackInfoReturnable<Boolean> ci) {
        if (((Entity) (Object) this) instanceof LivingEntity) {
            StandUserComponent standUserData = MyComponents.STAND_USER.get(this);
            if (standUserData.isDazed()) {
                ci.setReturnValue(true);
            }
        }
    }

}
