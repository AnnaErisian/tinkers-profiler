package blue.thejester.tinkerprofiler.item;

import blue.thejester.tinkerprofiler.TinkerProfiler;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.ITrait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blue.thejester.tinkerprofiler.item.TraitProfiler.Formats.*;
import static blue.thejester.tinkerprofiler.item.TraitProfiler.Formats.CSV;
import static blue.thejester.tinkerprofiler.item.TraitProfiler.Formats.READABLE;

public class TraitProfiler extends Item {

    PrintWriter printWriter;

    public static enum Formats {JSON, CSV, READABLE}

    private Formats format;

    public TraitProfiler(String name, Formats format) {
        setTranslationKey(name);
        setRegistryName(name);
        this.format = format;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if(worldIn.isRemote) {
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        File outFile = new File("tinkers-data.log");
        if (outFile.exists()) {
            outFile.delete();
            try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            printWriter = new PrintWriter(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (printWriter != null) {
            switch (this.format) {
                case JSON:
                    printWriter.println(printJSON());
                    break;
                case CSV:
                    printWriter.println(printCSV());
                    break;
                case READABLE:
                    printWriter.println(printReadable());
                    break;
            }

            printWriter.close();
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    private String printJSON() {
        ArrayList<MatRow> mats = new ArrayList<>();
        for (Material mat : TinkerRegistry.getAllMaterials()) {
            MatRow row = new MatRow();
            row.name = mat.getLocalizedName();
            for (IMaterialStats stats : mat.getAllStats()) {
                switch (stats.getIdentifier()) {
                    case "head": {
                        row.setHeadStats((HeadMaterialStats) stats);
                        break;
                    }
                    case "handle": {
                        row.setHandleStats((HandleMaterialStats) stats);
                        break;
                    }
                    case "extra": {
                        row.setExtraStats((ExtraMaterialStats) stats);
                        break;
                    }
                    case "bow": {
                        row.setBowStats((BowMaterialStats) stats);
                        break;
                    }
                    case "bowstring": {
                        row.setStringStats((BowStringMaterialStats) stats);
                        break;
                    }
                    case "shaft": {
                        row.setShaftStats((ArrowShaftMaterialStats) stats);
                        break;
                    }
                    case "fletching": {
                        row.setFletchingStats((FletchingMaterialStats) stats);
                        break;
                    }
                    case "core": {
                        row.setCoreStats((CoreMaterialStats) stats);
                        break;
                    }
                    case "plates": {
                        row.setPlateStats((PlatesMaterialStats) stats);
                        break;
                    }
                    case "trim": {
                        row.setTrimStats((TrimMaterialStats) stats);
                        break;
                    }
                }
            }
            row.configureTraits(mat);
            row.setImage(mat);
            mats.add(row);
        }
        return String.format("{\"materials\": [%s], \"traits\": %s}",
                String.join(",",
                        mats.stream().map(matRow -> matRow.toStringJSON()).collect(Collectors.toList())),
                MatRow.traitIndexJSON()
        );
    }

    private String printCSV() {
        StringBuilder builder = new StringBuilder("name, headdur, headlevel, headspeed, headattack, handlemod, handledur, extradur, bowspeed, bowrange, bowdamage, stringmod, shaftmod, shaftbonus, fletchmod, fletchacc, coredur, coredef, platemod, platedur, platetough, trimdur, trait...");
        for (Material mat : TinkerRegistry.getAllMaterials()) {
            MatRow row = new MatRow();
            row.name = mat.getLocalizedName();
            for (IMaterialStats stats : mat.getAllStats()) {
                switch (stats.getIdentifier()) {
                    case "head": {
                        row.setHeadStats((HeadMaterialStats) stats);
                        break;
                    }
                    case "handle": {
                        row.setHandleStats((HandleMaterialStats) stats);
                        break;
                    }
                    case "extra": {
                        row.setExtraStats((ExtraMaterialStats) stats);
                        break;
                    }
                    case "bow": {
                        row.setBowStats((BowMaterialStats) stats);
                        break;
                    }
                    case "bowstring": {
                        row.setStringStats((BowStringMaterialStats) stats);
                        break;
                    }
                    case "shaft": {
                        row.setShaftStats((ArrowShaftMaterialStats) stats);
                        break;
                    }
                    case "fletching": {
                        row.setFletchingStats((FletchingMaterialStats) stats);
                        break;
                    }
                    case "core": {
                        row.setCoreStats((CoreMaterialStats) stats);
                        break;
                    }
                    case "plates": {
                        row.setPlateStats((PlatesMaterialStats) stats);
                        break;
                    }
                    case "trim": {
                        row.setTrimStats((TrimMaterialStats) stats);
                        break;
                    }
                }
            }
            row.configureTraits(mat);
            builder.append(row.toStringCSV());
            builder.append("\n");
        }
        return builder.toString();
    }

    private String printReadable() {
        StringBuilder builder = new StringBuilder();
        for (Material mat : TinkerRegistry.getAllMaterials()) {
            builder.append("\n========================\n");
            builder.append(mat.identifier).append(" (").append(mat.getLocalizedName()).append(")\n");
            for (IMaterialStats stats : mat.getAllStats()) {
                builder.append("  -" + stats.getLocalizedName() + "-").append("\n    ");
                for (String s : stats.getLocalizedInfo()) {
                    builder.append(s.replaceAll(TextFormatting.RESET.toString(), "").replaceAll("[^\\x00-\\x7F]", "")).append(", ");
                }
                builder.append("\n    Traits: ");
                for (ITrait trait : mat.getAllTraitsForStats(stats.getIdentifier())) {
                    builder.append(trait.getLocalizedName()).append(", ");
                }
                builder.append("\n");
            }
            builder.append("  =====\n");
            for (ITrait trait : mat.getAllTraits()) {
                builder.append("    " + trait.getLocalizedName()).append(": ").append(trait.getLocalizedDesc().replaceAll("\n", "\\n"));
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}