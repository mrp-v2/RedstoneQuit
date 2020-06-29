package com.mrp_v2.redstonequit.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ConfigOptions {

	private static final String translationKey = "mrp_v2.redstonequit.configgui.";

	public static IntValue redstoneStrength_1_2_Range;
	public static IntValue redstoneStrength_3_4_Range;
	public static IntValue redstoneStrength_5_6_Range;
	public static IntValue redstoneStrength_7_8_Range;
	public static IntValue redstoneStrength_9_10_Range;
	public static IntValue redstoneStrength_11_12_Range;
	public static IntValue redstoneStrength_13_14_Range;

	public static class Server {
		Server(final ForgeConfigSpec.Builder builder) {
			builder.comment("Server configuration settings").push("server");

			redstoneStrength_1_2_Range = builder.comment("The range for redstone strengths 1 and 2.")
					.translation(translationKey + "redstoneStrength_1_2_Range")
					.defineInRange("redstoneStrength_1_2_Range", 1, 1, 128);

			redstoneStrength_3_4_Range = builder.comment("The range for redstone strengths 3 and 4.")
					.translation(translationKey + "redstoneStrength_3_4_Range")
					.defineInRange("redstoneStrength_3_4_Range", 2, 1, 128);

			redstoneStrength_5_6_Range = builder.comment("The range for redstone strengths 5 and 6.")
					.translation(translationKey + "redstoneStrength_5_6_Range")
					.defineInRange("redstoneStrength_5_6_Range", 4, 1, 128);

			redstoneStrength_7_8_Range = builder.comment("The range for redstone strengths 7 and 8.")
					.translation(translationKey + "redstoneStrength_7_8_Range")
					.defineInRange("redstoneStrength_7_8_Range", 8, 1, 128);

			redstoneStrength_9_10_Range = builder.comment("The range for redstone strengths 9 and 10.")
					.translation(translationKey + "redstoneStrength_9_10_Range")
					.defineInRange("redstoneStrength_9_10_Range", 16, 1, 128);

			redstoneStrength_11_12_Range = builder.comment("The range for redstone strengths 11 and 12.")
					.translation(translationKey + "redstoneStrength_11_12_Range")
					.defineInRange("redstoneStrength_11_12_Range", 32, 1, 128);

			redstoneStrength_13_14_Range = builder.comment("The range for redstone strengths 13 and 14.")
					.translation(translationKey + "redstoneStrength_13_14_Range")
					.defineInRange("redstoneStrength_13_14_Range", 64, 1, 128);
		}
	}

	public static final ForgeConfigSpec serverSpec;
	public static final Server SERVER;
	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
	}
}
