package net.starmarine06.prismcraft.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.starmarine06.prismcraft.PrismCraftMod;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, PrismCraftMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<DyeMixerMenu>> DYE_MIXER =
            MENUS.register("dye_mixer", () -> IMenuTypeExtension.create(DyeMixerMenu::new));


    public static final DeferredHolder<MenuType<?>, MenuType<PrismBarrelMenu>> PRISM_BARREL =
            MENUS.register("prism_barrel", () ->
                    new MenuType<>((id, inv) -> new PrismBarrelMenu(id, inv, null), FeatureFlags.DEFAULT_FLAGS));

}