package net.starmarine06.prismcraft.recipes;

import net.starmarine06.prismcraft.PrismCraftMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, PrismCraftMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, PrismCraftMod.MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(BuiltInRegistries.MENU, PrismCraftMod.MOD_ID);

    // Recipe Type
    public static final Supplier<RecipeType<DyeMixerRecipe>> DYE_MIXER_TYPE =
            TYPES.register("dye_mixing", () -> RecipeType.simple(PrismCraftMod.MOD_ID + ":dye_mixing"));

    // Recipe Serializer
    public static final Supplier<RecipeSerializer<DyeMixerRecipe>> DYE_MIXER_SERIALIZER =
            SERIALIZERS.register("dye_mixing", DyeMixerRecipe.Serializer::new);

    // Menu Type
    public static final Supplier<MenuType<DyeMixerMenu>> DYE_MIXER_MENU =
            MENUS.register("dye_mixer_menu", () -> IMenuTypeExtension.create(DyeMixerMenu::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
        MENUS.register(eventBus);
    }
}