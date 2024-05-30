package net.hydra.jojomod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hydra.jojomod.networking.packet.c2s.MoveSyncPacket;
import net.hydra.jojomod.networking.packet.c2s.StandAbilityPacket;
import net.hydra.jojomod.networking.packet.s2c.*;

public class FabricPacketManager {


    //Client To Server
    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.STAND_SUMMON_PACKET, StandAbilityPacket::summon);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.STAND_POWER_PACKET, StandAbilityPacket::switchPower);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.STAND_PUNCH_PACKET, StandAbilityPacket::punch);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.STAND_BARRAGE_HIT_PACKET, StandAbilityPacket::barrageHit);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.MOVE_SYNC_ID, MoveSyncPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.STAND_GUARD_CANCEL_PACKET, StandAbilityPacket::guardCancel);
        ServerPlayNetworking.registerGlobalReceiver(ModMessages.BARRAGE_CLASH_UPDATE_PACKET, StandAbilityPacket::clashUpdate);
    }
    //Server to Client
    public static void registerS2CPackets(){
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.NBT_SYNC_ID, NbtSyncPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.POWER_COOLDOWN_SYNC_ID, CooldownSyncPacket::updateAttackCooldowns);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.STAND_GUARD_POINT_ID, CooldownSyncPacket::updateGuard);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.DAZE_ID, CooldownSyncPacket::updateDaze);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.SOUND_CANCEL_ID, SoundStopPacket::stopSound);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.SOUND_PLAY_ID, SoundStopPacket::playSound);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.BARRAGE_CLASH_UPDATE_S2C_PACKET, StandS2CPacket::clashUpdate);
        ClientPlayNetworking.registerGlobalReceiver(ModMessages.TIME_STOP_ENTITY_PACKET, TimeEventPackets::updateTSList);
    }

}
