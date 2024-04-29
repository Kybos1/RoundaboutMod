package net.hydra.jojomod.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hydra.jojomod.RoundaboutMod;
import net.hydra.jojomod.access.IEntityDataSaver;
import net.hydra.jojomod.access.IHudAccess;
import net.hydra.jojomod.client.hud.StandHudRender;
import net.hydra.jojomod.networking.MyComponents;
import net.hydra.jojomod.networking.component.StandUserComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class GameHudMixin implements IHudAccess {

    /** Code for when the client renders its huds*/
    @Shadow
    @Final
    @Mutable
    private MinecraftClient client;
    @Shadow
    private int ticks;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    @Unique
    private float flashAlpha = 0f;
    @Unique
    private float otherFlashAlpha = 0f;

    //private void renderHotbar(float tickDelta, DrawContext context) {


    /** The stand move HUD renders with the hotbar so that it may exist in all gamemodes.*/
    @Inject(method = "renderHotbar", at = @At(value = "TAIL"))
    private void renderHotbarMixin(float tickDelta, DrawContext context, CallbackInfo info) {
        StandHudRender.renderStandHud(context, client, this.getCameraPlayer(), scaledWidth, scaledHeight, ticks, this.getHeartCount(this.getRiddenEntity()), flashAlpha, otherFlashAlpha);
    }

    /** The guard HUD renders with status bars because it is arbitrary in creative mode.*/
    @Inject(method = "renderStatusBars", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", ordinal = 1))
    private void renderStatusBarsMixin(DrawContext context, CallbackInfo info) {
        //StandHudRender.renderGuardHud(context, client, this.getCameraPlayer(), scaledWidth, scaledHeight, ticks, this.getHeartCount(this.getRiddenEntity()), flashAlpha, otherFlashAlpha);
    }

    /** The guard HUD uses the exp bar, because you dont need to check exp
     *  while you are blocking, efficient HUD use.*/
    @Inject(method = "renderExperienceBar", at = @At(value = "HEAD"), cancellable = true)
    public void roundaboutRenderExperienceBar(DrawContext context, int x, CallbackInfo ci){
        assert client.player != null;
        StandUserComponent standUserData = MyComponents.STAND_USER.get(client.player);
        if (standUserData.isGuarding()) {
            StandHudRender.renderGuardHud(context, client, this.getCameraPlayer(), scaledWidth, scaledHeight, ticks, x, flashAlpha, otherFlashAlpha);
            ci.cancel();
        }
    }

    @Inject(method = "renderCrosshair", at = @At(value = "TAIL"))
    private void renderCrosshairMixin(DrawContext context, CallbackInfo info) {
        StandHudRender.renderAttackHud(context, client, this.getCameraPlayer(), scaledWidth, scaledHeight, ticks, this.getHeartCount(this.getRiddenEntity()), flashAlpha, otherFlashAlpha);
    }

    @Shadow
    private PlayerEntity getCameraPlayer() {
        return null;
    }

    @Shadow
    private LivingEntity getRiddenEntity() {
        return null;
    }

    @Shadow
    private int getHeartCount(LivingEntity entity) {
        return 0;
    }

    @Override
    public void setFlashAlpha(float flashAlpha) {
        this.flashAlpha = flashAlpha;
    }

    @Override
    public void setOtherFlashAlpha(float otherFlashAlpha) {
        this.otherFlashAlpha = otherFlashAlpha;
    }
//    public static final String DEBUG_TEXT_1 = "hud.roundabout.standout";
}
