package blue.thejester.tinkerprofiler.item;

import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.ITrait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MatRow {

    public static Map<String, TraitSimple> traitIndex = new HashMap<>();

    public String name;
    public float headdur, headlevel, headspeed, headattack, handlemod, handledur, extradur, bowspeed, bowrange, bowdamage, stringmod, shaftmod, shaftbonus, fletchmod, fletchacc, coredur, coredef, platemod, platedur, platetough, trimdur;
    public List<String> traitsAll;
    public List<String> traitsDefault;
    public List<String> traitsHead;
    public List<String> traitsHandle;
    public List<String> traitsExtra;
    public List<String> traitsBow;
    public List<String> traitsBowstring;
    public List<String> traitsProjectile;
    public List<String> traitsShaft;
    public List<String> traitsFletching;
    public List<String> traitsCore;
    public List<String> traitsPlates;
    public List<String> traitsTrim;

    public MatRow() {
        name = "";
        headdur = Float.NEGATIVE_INFINITY;
        headlevel = Float.NEGATIVE_INFINITY;
        headspeed = Float.NEGATIVE_INFINITY;
        headattack = Float.NEGATIVE_INFINITY;
        handlemod = Float.NEGATIVE_INFINITY;
        handledur = Float.NEGATIVE_INFINITY;
        extradur = Float.NEGATIVE_INFINITY;
        bowspeed = Float.NEGATIVE_INFINITY;
        bowrange = Float.NEGATIVE_INFINITY;
        bowdamage = Float.NEGATIVE_INFINITY;
        stringmod = Float.NEGATIVE_INFINITY;
        shaftmod = Float.NEGATIVE_INFINITY;
        shaftbonus = Float.NEGATIVE_INFINITY;
        fletchmod = Float.NEGATIVE_INFINITY;
        fletchacc = Float.NEGATIVE_INFINITY;
        coredur = Float.NEGATIVE_INFINITY;
        coredef = Float.NEGATIVE_INFINITY;
        platemod = Float.NEGATIVE_INFINITY;
        platedur = Float.NEGATIVE_INFINITY;
        platetough = Float.NEGATIVE_INFINITY;
        trimdur = Float.NEGATIVE_INFINITY;
        traitsAll = new ArrayList<>();
        traitsDefault = new ArrayList<>();
        traitsHead = new ArrayList<>();
        traitsHandle = new ArrayList<>();
        traitsExtra = new ArrayList<>();
        traitsBow = new ArrayList<>();
        traitsBowstring = new ArrayList<>();
        traitsProjectile = new ArrayList<>();
        traitsShaft = new ArrayList<>();
        traitsFletching = new ArrayList<>();
        traitsCore = new ArrayList<>();
        traitsPlates = new ArrayList<>();
        traitsTrim = new ArrayList<>();

    }

    //        headdur, headlevel, headspeed, headattack,
    public void setHeadStats(HeadMaterialStats h) {
        headdur = h.durability;
        headlevel = h.harvestLevel;
        headspeed = h.miningspeed;
        headattack = h.attack;
    }

    //        handlemod, handledur,
    public void setHandleStats(HandleMaterialStats h) {
        handledur = h.durability;
        handlemod = h.modifier;
    }

    //        extradur,
    public void setExtraStats(ExtraMaterialStats e) {
        extradur = e.extraDurability;
    }

    //        bowspeed, bowrange, bowdamage,
    public void setBowStats(BowMaterialStats b) {
        bowspeed = b.drawspeed;
        bowrange = b.range;
        bowdamage = b.bonusDamage;
    }

    //        stringmod,
    public void setStringStats(BowStringMaterialStats b) {
        stringmod = b.modifier;
    }

    //        shaftmod, shaftbonus,
    public void setShaftStats(ArrowShaftMaterialStats s) {
        shaftbonus = s.bonusAmmo;
        shaftmod = s.modifier;
    }

    //        fletchmod, fletchacc,
    public void setFletchingStats(FletchingMaterialStats f) {
        fletchacc = f.accuracy;
        fletchmod = f.modifier;
    }

    //        coredur, coredef,
    public void setCoreStats(CoreMaterialStats c) {
        coredef = c.defense;
        coredur = c.durability;
    }

    //        platemod, platedur, platetough,
    public void setPlateStats(PlatesMaterialStats p) {
        platedur = p.durability;
        platemod = p.modifier;
        platetough = p.toughness;
    }

    //        trimdur)
    public void setTrimStats(TrimMaterialStats t) {
        trimdur = t.extraDurability;
    }

    protected static String jsonList(List<String> list) {
        return ("\"" + String.join(",", list) + "\"").replace(",", "\",\"");
    }

    public String toStringCSV() {
        return String.format("%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,",
                name,
                headdur, headlevel, headspeed, headattack,
                handlemod, handledur,
                extradur,
                bowspeed, bowrange, bowdamage,
                stringmod,
                shaftmod, shaftbonus,
                fletchmod, fletchacc,
                coredur, coredef,
                platemod, platedur, platetough,
                trimdur).replace("-Infinity", "-Inf")
                + String.join(",", traitsAll);
    }

    public String toStringJSON() {
        return String.format("\n{\"name\": \"%s\"," +
                        "\"headdur\": \"%.2f\"," +
                        "\"headlevel\": \"%.2f\"," +
                        "\"headspeed\": \"%.2f\"," +
                        "\"headattack\": \"%.2f\"," +
                        "\"handlemod\": \"%.2f\"," +
                        "\"handledur\": \"%.2f\"," +
                        "\"extradur\": \"%.2f\"," +
                        "\"bowspeed\": \"%.2f\"," +
                        "\"bowrange\": \"%.2f\"," +
                        "\"bowdamage\": \"%.2f\"," +
                        "\"stringmod\": \"%.2f\"," +
                        "\"shaftmod\": \"%.2f\"," +
                        "\"shaftbonus\": \"%.2f\"," +
                        "\"fletchmod\": \"%.2f\"," +
                        "\"fletchacc\": \"%.2f\"," +
                        "\"coredur\": \"%.2f\"," +
                        "\"coredef\": \"%.2f\"," +
                        "\"platemod\": \"%.2f\"," +
                        "\"platedur\": \"%.2f\"," +
                        "\"platetough\": \"%.2f\"," +
                        "\"trimdur\": \"%.2f\"," +
                        "\"traitsDefault\": [%s]," +
                        "\"traitsHead\": [%s]," +
                        "\"traitsHandle\": [%s]," +
                        "\"traitsExtra\": [%s]," +
                        "\"traitsBow\": [%s]," +
                        "\"traitsBowstring\": [%s]," +
                        "\"traitsProjectile\": [%s]," +
                        "\"traitsShaft\": [%s]," +
                        "\"traitsFletching\": [%s]," +
                        "\"traitsCore\": [%s]," +
                        "\"traitsPlates\": [%s]," +
                        "\"traitsTrim\": [%s]}",
                name,
                headdur, headlevel, headspeed, headattack,
                handlemod, handledur,
                extradur,
                bowspeed, bowrange, bowdamage,
                stringmod,
                shaftmod, shaftbonus,
                fletchmod, fletchacc,
                coredur, coredef,
                platemod, platedur, platetough,
                trimdur,
                jsonList(traitsDefault),
                jsonList(traitsHead),
                jsonList(traitsHandle),
                jsonList(traitsExtra),
                jsonList(traitsBow),
                jsonList(traitsBowstring),
                jsonList(traitsProjectile),
                jsonList(traitsShaft),
                jsonList(traitsFletching),
                jsonList(traitsCore),
                jsonList(traitsPlates),
                jsonList(traitsTrim)
        );
    }

    public void configureTraits(Material mat) {
        for (ITrait trait : mat.getAllTraits()) {
            traitsAll.add(trait.getLocalizedName());
            TraitSimple ts = new TraitSimple();
            ts.display = trait.getLocalizedName();
            ts.description = trait.getLocalizedDesc();
            traitIndex.put(trait.getIdentifier(), ts);
        }
        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.HEAD)) {
            traitsHead.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.HANDLE)) {
            traitsHandle.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.EXTRA)) {
            traitsExtra.add(trait.getIdentifier());
        }

        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.BOW)) {
            traitsBow.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.BOWSTRING)) {
            traitsBowstring.add(trait.getIdentifier());
        }

        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.PROJECTILE)) {
            traitsProjectile.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.SHAFT)) {
            traitsShaft.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(MaterialTypes.FLETCHING)) {
            traitsFletching.add(trait.getIdentifier());
        }

        for (ITrait trait : mat.getAllTraitsForStats(ArmorMaterialType.CORE)) {
            traitsCore.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(ArmorMaterialType.PLATES)) {
            traitsPlates.add(trait.getIdentifier());
        }
        for (ITrait trait : mat.getAllTraitsForStats(ArmorMaterialType.TRIM)) {
            traitsTrim.add(trait.getIdentifier());
        }
    }

    public static String traitIndexJSON() {
        return "{" + String.join(",", traitIndex.keySet().stream().map(s -> ("\"" + s + "\": " + traitIndex.get(s).toStringJSON())).collect(Collectors.toList())) + "}";
    }

    private class TraitSimple {
        public String display;
        public String description;

        public String toStringJSON() {
            return String.format("\n{\"display\": \"%s\", \"desc\": \"%s\"}", display, escape(description));
        }
    }

    private String escape(String str) {
        return str.replaceAll("\n", "\\n").replaceAll("\\\"", "\\\\\"");
    }
}