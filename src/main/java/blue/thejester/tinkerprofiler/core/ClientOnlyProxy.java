package blue.thejester.tinkerprofiler.core;

import blue.thejester.tinkerprofiler.TinkerProfiler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;

public class ClientOnlyProxy extends CommonProxy {

    @Override
    public void preInit() {

        super.preInit();
    }

    @Override
    public boolean isDedicatedServer() {
        return false;
    }

}
