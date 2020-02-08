package blue.thejester.tinkerprofiler;

import blue.thejester.tinkerprofiler.core.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;

@Mod(modid = TinkerProfiler.MODID, name = TinkerProfiler.NAME, version = TinkerProfiler.VERSION, dependencies = TinkerProfiler.DEPENDS)
public class TinkerProfiler
{
    public static final String MODID = "tinkerprofiler";
    public static final String NAME = "Tinker's Profiler";
    public static final String VERSION = "1.0";
    public static final String DEPENDS = "required-after:mantle;"
            + "required-after:tconstruct@[1.12-2.7.2.15,);required-after:forge@[14.23.5.2768,);"
            + "required-after:conarm;";

    public static Logger logger;

    // The instance of your mod that Forge uses.  Optional.
    @Mod.Instance(TinkerProfiler.MODID)
    public static TinkerProfiler instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="blue.thejester.tinkerprofiler.core.ClientOnlyProxy", serverSide="blue.thejester.tinkerprofiler.core.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();

        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit();
    }
}
