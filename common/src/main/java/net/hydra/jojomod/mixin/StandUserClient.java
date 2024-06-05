package net.hydra.jojomod.mixin;

import net.hydra.jojomod.event.powers.StandUser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public class StandUserClient implements net.hydra.jojomod.event.powers.StandUserClient {
    public SoundInstance queSound;
    public boolean soundCancel = false;
    public boolean soundPlay = false;
    public boolean shouldCancel = false;
    public float roundaboutPrevTickDelta = 0;

    @Override
    public boolean getSoundPlay(){
        return this.soundPlay;
    }
    @Override
    public boolean getSoundCancel(){
        return this.soundCancel;
    }

    /**This is called second by the packets, it sets up the client to play the sound on a game tick.
     * If you play it during the packet, it can crash the client because of HashMap problems*/
    @Override
    public void clientQueSound(byte soundChoice, Entity User){
       SoundEvent barrageCrySound = ((StandUser) this).getStandPowers().getSoundFromByte(soundChoice);
       if (barrageCrySound != null) {
            this.queSound = new EntityBoundSoundInstance(
                    barrageCrySound,
                    SoundSource.PLAYERS,
                    1F,
                    ((StandUser) this).getStandPowers().getSoundPitchFromByte(soundChoice),
                    User,
                    User.level().random.nextLong()
            );
                this.soundPlay = true;
            }
    }

    /**This is called third by the client, it actually plays the sound.*/

    @Override
    public void clientPlaySound(){
        if (this.queSound != null) {
            Minecraft.getInstance().getSoundManager().play(this.queSound);
        }
        this.soundPlay = false;
    }

    /**This is called fifth by the client, it ques the sound for canceling
     * If you play it during the packet, it can crash the client because of HashMap problems*/

    @Override
    public void clientQueSoundCanceling(){
        this.soundCancel = true;
    }

    /**This is called sixth by the client, it finally cancels the sound*/

    @Override
    public void clientSoundCancel(){
        if (this.queSound != null) {
            Minecraft.getInstance().getSoundManager().stop(this.queSound);
            this.queSound = null;
        }
        this.soundCancel = false;
    }

    @Override
    public float getPreTSTickDelta() {
        return this.roundaboutPrevTickDelta;
    }

    @Override
    public void setPreTSTickDelta() {
        Minecraft mc = Minecraft.getInstance();
        roundaboutPrevTickDelta = mc.getFrameTime();
    }
    @Override
    public void resetPreTSTickDelta() {
        roundaboutPrevTickDelta = 0;
    }
}
