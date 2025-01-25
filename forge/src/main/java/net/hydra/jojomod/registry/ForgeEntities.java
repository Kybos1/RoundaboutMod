package net.hydra.jojomod.registry;

import net.hydra.jojomod.Roundabout;
import net.hydra.jojomod.entity.ModEntities;
import net.hydra.jojomod.entity.Terrier.TerrierEntity;
import net.hydra.jojomod.entity.corpses.*;
import net.hydra.jojomod.entity.projectile.*;
import net.hydra.jojomod.entity.stand.*;
import net.hydra.jojomod.entity.visages.mobs.JotaroNPC;
import net.hydra.jojomod.entity.visages.mobs.OVAEnyaNPC;
import net.hydra.jojomod.entity.visages.mobs.PlayerAlexNPC;
import net.hydra.jojomod.entity.visages.mobs.PlayerSteveNPC;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ForgeEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Roundabout.MOD_ID);
    public static final RegistryObject<EntityType<TerrierEntity>> TERRIER_DOG =
            ENTITY_TYPES.register("terrier", () ->
                    EntityType.Builder.of(TerrierEntity::new, MobCategory.CREATURE).sized(0.6f, 0.55f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "terrier").toString())
            );
    public static final RegistryObject<EntityType<OVAEnyaNPC>> OVA_ENYA =
            ENTITY_TYPES.register("jnpc_ova_enya", () ->
                    EntityType.Builder.of(OVAEnyaNPC::new, MobCategory.MISC).sized(0.6f, 1.8f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "jnpc_ova_enya").toString())
            );
    public static final RegistryObject<EntityType<JotaroNPC>> JOTARO =
            ENTITY_TYPES.register("jnpc_jotaro", () ->
                    EntityType.Builder.of(JotaroNPC::new, MobCategory.MISC).sized(0.6f, 1.8f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "jnpc_jotaro").toString())
            );
    public static final RegistryObject<EntityType<PlayerSteveNPC>> STEVE_NPC =
            ENTITY_TYPES.register("jnpc_steve", () ->
                    EntityType.Builder.of(PlayerSteveNPC::new, MobCategory.MISC).sized(0.6f, 1.8f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "jnpc_steve").toString())
            );
    public static final RegistryObject<EntityType<PlayerAlexNPC>> ALEX_NPC =
            ENTITY_TYPES.register("jnpc_alex", () ->
                    EntityType.Builder.of(PlayerAlexNPC::new, MobCategory.MISC).sized(0.6f, 1.8f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "jnpc_alex").toString())
            );
    public static final RegistryObject<EntityType<FallenZombie>> FALLEN_ZOMBIE =
            ENTITY_TYPES.register("fallen_zombie", () ->
                    EntityType.Builder.of(FallenZombie::new, MobCategory.MISC).sized(0.6F, 1.95F).clientTrackingRange(8).
                            build(new ResourceLocation(Roundabout.MOD_ID, "fallen_zombie").toString())
            );
    public static final RegistryObject<EntityType<FallenSkeleton>> FALLEN_SKELETON =
            ENTITY_TYPES.register("fallen_skeleton", () ->
                    EntityType.Builder.of(FallenSkeleton::new, MobCategory.MISC).sized(0.6F, 1.99F).clientTrackingRange(8).
                            build(new ResourceLocation(Roundabout.MOD_ID, "fallen_skeleton").toString())
            );
    public static final RegistryObject<EntityType<FallenSpider>> FALLEN_SPIDER =
            ENTITY_TYPES.register("fallen_spider", () ->
                    EntityType.Builder.of(FallenSpider::new, MobCategory.MISC).sized(0.8F, 0.8F).clientTrackingRange(8).
                            build(new ResourceLocation(Roundabout.MOD_ID, "fallen_spider").toString())
            );
    public static final RegistryObject<EntityType<FallenVillager>> FALLEN_VILLAGER =
            ENTITY_TYPES.register("fallen_villager", () ->
                    EntityType.Builder.of(FallenVillager::new, MobCategory.MISC).sized(0.6F, 1.95F).clientTrackingRange(8).
                            build(new ResourceLocation(Roundabout.MOD_ID, "fallen_villager").toString())
            );
    public static final RegistryObject<EntityType<FallenCreeper>> FALLEN_CREEPER =
            ENTITY_TYPES.register("fallen_creeper", () ->
                    EntityType.Builder.of(FallenCreeper::new, MobCategory.MISC).sized(0.6F, 1.7F).clientTrackingRange(8).
                            build(new ResourceLocation(Roundabout.MOD_ID, "fallen_creeper").toString())
            );
    public static final RegistryObject<EntityType<TheWorldEntity>> THE_WORLD =
            ENTITY_TYPES.register("the_world", () ->
                    EntityType.Builder.of(TheWorldEntity::new, MobCategory.MISC).sized(0.75F, 2.05f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "the_world").toString())
            );
    public static final RegistryObject<EntityType<StarPlatinumEntity>> STAR_PLATINUM =
            ENTITY_TYPES.register("star_platinum", () ->
                    EntityType.Builder.of(StarPlatinumEntity::new, MobCategory.MISC).sized(0.75F, 2.05f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "star_platinum").toString())
            );
    public static final RegistryObject<EntityType<JusticeEntity>> JUSTICE =
            ENTITY_TYPES.register("justice", () ->
                    EntityType.Builder.of(JusticeEntity::new, MobCategory.MISC).sized(ModEntities.justiceWidth, ModEntities.justiceHeight).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "justice").toString())
            );
    public static final RegistryObject<EntityType<JusticePirateEntity>> JUSTICE_PIRATE =
            ENTITY_TYPES.register("justice_pirate", () ->
                    EntityType.Builder.of(JusticePirateEntity::new, MobCategory.MISC).sized(ModEntities.justiceWidth, ModEntities.justiceHeight).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "justice_pirate").toString())
            );
    public static final RegistryObject<EntityType<DarkMirageEntity>> DARK_MIRAGE =
            ENTITY_TYPES.register("dark_mirage", () ->
                    EntityType.Builder.of(DarkMirageEntity::new, MobCategory.MISC).sized(0.75F, 2.05f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "dark_mirage").toString())
            );
    public static final RegistryObject<EntityType<StarPlatinumBaseballEntity>> STAR_PLATINUM_BASEBALL =
            ENTITY_TYPES.register("star_platinum_baseball", () ->
                    EntityType.Builder.of(StarPlatinumBaseballEntity::new, MobCategory.MISC).sized(0.75F, 2.05f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "star_platinum_baseball").toString())
            );
    public static final RegistryObject<EntityType<KnifeEntity>> THROWN_KNIFE =
            ENTITY_TYPES.register("knife", () ->
                    EntityType.Builder.<KnifeEntity>of(KnifeEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "knife").toString())
            );
    public static final RegistryObject<EntityType<HarpoonEntity>> THROWN_HARPOON =
            ENTITY_TYPES.register("harpoon", () ->
                    EntityType.Builder.<HarpoonEntity>of(HarpoonEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "harpoon").toString())
            );
    public static final RegistryObject<EntityType<MatchEntity>> THROWN_MATCH =
            ENTITY_TYPES.register("match", () ->
                    EntityType.Builder.<MatchEntity>of(MatchEntity::new, MobCategory.MISC).sized(0.5f, 0.5f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "match").toString())
            );
    public static final RegistryObject<EntityType<GasolineCanEntity>> GASOLINE_CAN =
            ENTITY_TYPES.register("gasoline_can", () ->
                    EntityType.Builder.<GasolineCanEntity>of(GasolineCanEntity::new, MobCategory.MISC).sized(0.8f, 0.8f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "gasoline_can").toString())
            );
    public static final RegistryObject<EntityType<GasolineSplatterEntity>> GASOLINE_SPLATTER =
            ENTITY_TYPES.register("gasoline_splatter", () ->
                    EntityType.Builder.<GasolineSplatterEntity>of(GasolineSplatterEntity::new, MobCategory.MISC).sized(0.8f, 0.8f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "gasoline_splatter").toString())
            );
    public static final RegistryObject<EntityType<StandArrowEntity>> STAND_ARROW =
            ENTITY_TYPES.register("stand_arrow", () ->
                    EntityType.Builder.<StandArrowEntity>of(StandArrowEntity::new, MobCategory.MISC).sized(0.7f, 0.7f).
                            clientTrackingRange(6).
                            build(new ResourceLocation(Roundabout.MOD_ID, "stand_arrow").toString())
            );
    public static final RegistryObject<EntityType<ThrownObjectEntity>> THROWN_OBJECT =
            ENTITY_TYPES.register("thrown_object", () ->
                    EntityType.Builder.<ThrownObjectEntity>of(ThrownObjectEntity::new, MobCategory.MISC).sized(1f, 1f).
                            clientTrackingRange(10).
                            build(new ResourceLocation(Roundabout.MOD_ID, "thrown_object").toString())
            );
}
