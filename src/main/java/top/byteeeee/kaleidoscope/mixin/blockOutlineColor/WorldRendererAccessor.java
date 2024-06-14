package top.byteeeee.kaleidoscope.mixin.blockOutlineColor;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WorldRenderer.class)
public interface WorldRendererAccessor {
    @Accessor("world")
    ClientWorld getWorld();
}
