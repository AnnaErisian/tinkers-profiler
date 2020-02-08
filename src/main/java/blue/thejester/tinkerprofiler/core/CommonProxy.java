package blue.thejester.tinkerprofiler.core;

import blue.thejester.tinkerprofiler.TinkerProfiler;
import blue.thejester.tinkerprofiler.item.TraitProfiler;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

@Mod.EventBusSubscriber(modid = TinkerProfiler.MODID)
public class CommonProxy {

    private static TraitProfiler traitProfilerC;
    private static TraitProfiler traitProfilerJ;
    private static TraitProfiler traitProfilerR;

    /**
     * Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry
     */
    public void preInit() {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        traitProfilerC = new TraitProfiler("trait_profiler_csv", TraitProfiler.Formats.CSV);
        traitProfilerJ = new TraitProfiler("trait_profiler_json", TraitProfiler.Formats.JSON);
        traitProfilerR = new TraitProfiler("trait_profiler_readable", TraitProfiler.Formats.READABLE);
        event.getRegistry().registerAll(CommonProxy.traitProfilerC);
        event.getRegistry().registerAll(CommonProxy.traitProfilerJ);
        event.getRegistry().registerAll(CommonProxy.traitProfilerR);
    }


    /**
     * Do your mod setup. Build whatever data structures you care about. Register recipes,
     * send FMLInterModComms messages to other mods.
     */
    public void init() {
    }

    /**
     * Handle interaction with other mods, complete your setup based on this.
     */
    public void postInit() {
    }

    /**
     * is this a dedicated server?
     *
     * @return true if this is a dedicated server, false otherwise
     */
    public boolean isDedicatedServer() {
        return true;
    }

    public void registerFluidModels(Fluid fluid) {

    }
}
