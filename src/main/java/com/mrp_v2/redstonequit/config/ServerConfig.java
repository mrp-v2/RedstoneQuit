package com.mrp_v2.redstonequit.config;

import com.mrp_v2.redstonequit.RedstoneQuit;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;

public class ServerConfig
{
    public static final ForgeConfigSpec serverSpec;
    public static final Server SERVER;
    private static final String TRANSLATION_KEY = RedstoneQuit.ID + ".config.gui.";
    private static final int MIN_RANGE = -1;
    private static final int MAX_RANGE = 256;

    static
    {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        serverSpec = specPair.getRight();
        SERVER = specPair.getLeft();
    }

    public static int getRangeForStrength(int strength)
    {
        switch (strength)
        {
            case 1:
            case 2:
                return SERVER.redstoneStrength_1_2_Range.get();
            case 3:
            case 4:
                return SERVER.redstoneStrength_3_4_Range.get();
            case 5:
            case 6:
                return SERVER.redstoneStrength_5_6_Range.get();
            case 7:
            case 8:
                return SERVER.redstoneStrength_7_8_Range.get();
            case 9:
            case 10:
                return SERVER.redstoneStrength_9_10_Range.get();
            case 11:
            case 12:
                return SERVER.redstoneStrength_11_12_Range.get();
            case 13:
            case 14:
                return SERVER.redstoneStrength_13_14_Range.get();
            default:
                return 0;
        }
    }

    @SubscribeEvent public static void onLoad(final ModConfig.Loading configEvent)
    {
        LogManager.getLogger().debug("Loaded Redstone Quit config file {}", configEvent.getConfig().getFileName());
    }

    @SubscribeEvent public static void onFileChange(final ModConfig.Reloading configEvent)
    {
        LogManager.getLogger().debug("Redstone Quit config just got changed on the file system!");
    }

    public static class Server
    {
        public final IntValue redstoneStrength_1_2_Range;
        public final IntValue redstoneStrength_3_4_Range;
        public final IntValue redstoneStrength_5_6_Range;
        public final IntValue redstoneStrength_7_8_Range;
        public final IntValue redstoneStrength_9_10_Range;
        public final IntValue redstoneStrength_11_12_Range;
        public final IntValue redstoneStrength_13_14_Range;

        Server(final ForgeConfigSpec.Builder builder)
        {
            builder.comment(" Server configuration settings.",
                    " Redstone strengths of 0 or less and 15 or more have no effect.",
                    " Otherwise, even redstone strengths disconnect all players within range,",
                    " while odd redstone strengths disconnect only the nearest player within range.",
                    " Ranges are given in block units.", "A range of 0 disables all effects at that redstone strength.",
                    " A range of -1 gives that redstone strength infinite range.").push("server");
            redstoneStrength_1_2_Range = builder.comment(" The range for redstone strengths 1 and 2.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_1_2_Range")
                    .defineInRange("redstoneStrength_1_2_Range", 1, MIN_RANGE, MAX_RANGE);
            redstoneStrength_3_4_Range = builder.comment(" The range for redstone strengths 3 and 4.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_3_4_Range")
                    .defineInRange("redstoneStrength_3_4_Range", 2, MIN_RANGE, MAX_RANGE);
            redstoneStrength_5_6_Range = builder.comment(" The range for redstone strengths 5 and 6.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_5_6_Range")
                    .defineInRange("redstoneStrength_5_6_Range", 4, MIN_RANGE, MAX_RANGE);
            redstoneStrength_7_8_Range = builder.comment(" The range for redstone strengths 7 and 8.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_7_8_Range")
                    .defineInRange("redstoneStrength_7_8_Range", 8, MIN_RANGE, MAX_RANGE);
            redstoneStrength_9_10_Range = builder.comment(" The range for redstone strengths 9 and 10.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_9_10_Range")
                    .defineInRange("redstoneStrength_9_10_Range", 16, MIN_RANGE, MAX_RANGE);
            redstoneStrength_11_12_Range = builder.comment(" The range for redstone strengths 11 and 12.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_11_12_Range")
                    .defineInRange("redstoneStrength_11_12_Range", 32, MIN_RANGE, MAX_RANGE);
            redstoneStrength_13_14_Range = builder.comment(" The range for redstone strengths 13 and 14.")
                    .translation(TRANSLATION_KEY + "redstoneStrength_13_14_Range")
                    .defineInRange("redstoneStrength_13_14_Range", 64, MIN_RANGE, MAX_RANGE);
            builder.pop();
        }
    }
}
