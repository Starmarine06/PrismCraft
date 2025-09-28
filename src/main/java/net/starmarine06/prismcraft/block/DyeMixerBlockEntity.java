package net.starmarine06.prismcraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.starmarine06.prismcraft.registry.ModBlockEntities;
import net.starmarine06.prismcraft.util.ColorUtil;

public class DyeMixerBlockEntity extends BlockEntity {
    private final SimpleContainer inventory = new SimpleContainer(4);

    public DyeMixerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DYE_MIXER_BE.get(), pos, state);
    }

    public SimpleContainer getInventory() { return inventory; }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.createTag());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("Inventory")) inventory.fromTag(tag.getList("Inventory", 10));
    }

    private Integer readColorFromItem(ItemStack stack) {
        if (stack.hasTag() && stack.getOrCreateTag().contains("CustomColor")) {
            return stack.getOrCreateTag().getInt("CustomColor");
        }
        return null;
    }

    public void tryMix() {
        ItemStack in = inventory.getItem(0);
        ItemStack d1 = inventory.getItem(1);
        ItemStack d2 = inventory.getItem(2);

        if (in.isEmpty() || d1.isEmpty() || d2.isEmpty()) return;
        if (!(d1.getItem() instanceof DyeItem) || !(d2.getItem() instanceof DyeItem)) return;

        int rgb1 = ColorUtil.getColorFromDye((DyeItem)d1.getItem());
        int rgb2 = ColorUtil.getColorFromDye((DyeItem)d2.getItem());
        int dyeBlend = ColorUtil.blendAverage(rgb1, rgb2);

        Integer old = readColorFromItem(in);
        int finalColor = (old == null) ? dyeBlend : ColorUtil.blendWeighted(old.intValue(), dyeBlend, 0.7f);

        ItemStack out = in.copy();
        out.getOrCreateTag().putInt("CustomColor", finalColor);
        inventory.setItem(3, out);

        d1.shrink(1);
        d2.shrink(1);
        if (d1.isEmpty()) inventory.setItem(1, ItemStack.EMPTY);
        if (d2.isEmpty()) inventory.setItem(2, ItemStack.EMPTY);

        setChanged();

        if (level instanceof ServerLevel server) {
            double x = worldPosition.getX() + 0.5;
            double y = worldPosition.getY() + 1.0;
            double z = worldPosition.getZ() + 0.5;
            float r = ((finalColor >> 16) & 0xFF) / 255f;
            float g = ((finalColor >> 8) & 0xFF) / 255f;
            float b = (finalColor & 0xFF) / 255f;
            DustParticleOptions opts = new DustParticleOptions(r, g, b, 1.0f);
            for (int i = 0; i < 12; i++) {
                double dx = (server.random.nextDouble() - 0.5) * 0.6;
                double dy = server.random.nextDouble() * 0.5;
                double dz = (server.random.nextDouble() - 0.5) * 0.6;
                server.sendParticles(opts, x + dx, y + dy, z + dz, 1, 0.0, 0.0, 0.0, 0.0);
            }
        }
    }
}
