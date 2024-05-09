package net.hydra.jojomod.mixin;

import net.hydra.jojomod.RoundaboutMod;
import net.hydra.jojomod.event.powers.ModDamageTypes;
import net.hydra.jojomod.networking.MyComponents;
import net.hydra.jojomod.networking.component.StandUserComponent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    /**if your stand guard is broken, disable shields. Also, does not run takeshieldhit code if stand guarding.*/
    @Inject(method = "takeShieldHit", at = @At(value = "HEAD"), cancellable = true)
    protected void roundaboutTakeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        StandUserComponent standUserData = MyComponents.STAND_USER.get(this);
        if (standUserData.isGuarding()) {
            if (standUserData.getGuardBroken()){

                ItemStack itemStack = ((LivingEntity) (Object) this).getActiveItem();
                Item item = itemStack.getItem();
                if (item.getUseAction(itemStack) == UseAction.BLOCK) {
                    ((LivingEntity) (Object) this).stopUsingItem();
                    ((PlayerEntity) (Object) this).clearActiveItem();
                }
                ((PlayerEntity) (Object) this).getItemCooldownManager().set(Items.SHIELD, 100);
                ((PlayerEntity) (Object) this).getWorld().sendEntityStatus(((PlayerEntity) (Object) this), EntityStatuses.BREAK_SHIELD);
            }
            ci.cancel();
        } else if (MyComponents.STAND_USER.get(attacker).getMainhandOverride()){
            ci.cancel();
        }
    }

    /**your shield does not take damage if the stand blocks it*/
    @Inject(method = "damageShield", at = @At(value = "HEAD"), cancellable = true)
    protected void roundaboutDamageShield(float amount, CallbackInfo ci) {
        StandUserComponent standUserData = MyComponents.STAND_USER.get(this);
        if (standUserData.isGuarding()) {
            ci.cancel();
        }
    }

    /**If you are in a barrage, does not play the hurt sound*/
    @Inject(method = "getHurtSound", at = @At(value = "HEAD"), cancellable = true)
    protected void RoundaboutGetHurtSound(DamageSource source, CallbackInfoReturnable<SoundEvent> ci) {
        StandUserComponent standUserData = MyComponents.STAND_USER.get(this);
        if (standUserData.isDazed() && source.isOf(ModDamageTypes.STAND)) {
            ci.setReturnValue(null);
        }
    }
}
