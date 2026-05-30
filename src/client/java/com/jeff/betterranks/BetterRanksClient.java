package com.jeff.betterranks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringUtil;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;

import java.util.List;
import java.util.regex.Pattern;

public class BetterRanksClient implements ClientModInitializer {

    private static final BetterRanksClient INSTANCE = new BetterRanksClient();
    
    String formattingSign = "§";

    private static final String MVP_COLOR = "0ea4e5";

    public static final String MVP_PLUS_BLACK = "§b[MVP§0+§b]";
    public static final String MVP_PLUS_BLACK_CODE = "\uE001";

    private static final String MVP_PLUS_BLUE = "§b[MVP§9+§b]";
    private static final String MVP_PLUS_BLUE_CODE = "\uE002";

    private static final String MVP_PLUS_DARK_AQUA = "§b[MVP§3+§b]";
    private static final String MVP_PLUS_DARK_AQUA_CODE = "\uE003";

    private static final String MVP_PLUS_DARK_BLUE = "§b[MVP§1+§b]";
    private static final String MVP_PLUS_DARK_BLUE_CODE = "\uE004";

    private static final String MVP_PLUS_DARK_GREEN = "§b[MVP§2+§b]";
    private static final String MVP_PLUS_DARK_GREEN_CODE = "\uE005";

    private static final String MVP_PLUS_DARK_PURPLE = "§b[MVP§5+§b]";
    private static final String MVP_PLUS_DARK_PURPLE_CODE = "\uE006";

    private static final String MVP_PLUS_DARK_RED = "§b[MVP§4+§b]";
    private static final String MVP_PLUS_DARK_RED_CODE = "\uE007";

    private static final String MVP_PLUS_GOLD = "§b[MVP§6+§b]";
    private static final String MVP_PLUS_GOLD_CODE = "\uE008";

    private static final String MVP_PLUS_GRAY = "§b[MVP§8+§b]";
    private static final String MVP_PLUS_GRAY_CODE = "\uE009";

    private static final String MVP_PLUS_GREEN = "§b[MVP§a+§b]";
    private static final String MVP_PLUS_GREEN_CODE = "\uE011";

    private static final String MVP_PLUS_PINK = "§b[MVP§d+§b]";
    private static final String MVP_PLUS_PINK_CODE = "\uE012";

    private static final String MVP_PLUS_RED = "§b[MVP§c+§b]";
    private static final String MVP_PLUS_RED_CODE = "\uE013";

    private static final String MVP_PLUS_WHITE = "§b[MVP§f+§b]";
    private static final String MVP_PLUS_WHITE_CODE = "\uE014";

    private static final String MVP_PLUS_YELLOW = "§b[MVP§e+§b]";
    private static final String MVP_PLUS_YELLOW_CODE = "\uE015";

    private static final String AQUA_MVP_PLUS_PLUS_BLACK = "§b[MVP§0++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_BLACK_CODE = "\uE016";

    private static final String AQUA_MVP_PLUS_PLUS_BLUE = "§b[MVP§9++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_BLUE_CODE = "\uE017";

    private static final String AQUA_MVP_PLUS_PLUS_DARK_AQUA = "§b[MVP§3++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_DARK_AQUA_CODE = "\uE018";

    private static final String AQUA_MVP_PLUS_PLUS_DARK_BLUE = "§b[MVP§1++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_DARK_BLUE_CODE = "\uE019";

    private static final String AQUA_MVP_PLUS_PLUS_DARK_GREEN = "§b[MVP§2++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_DARK_GREEN_CODE = "\uE020";

    private static final String AQUA_MVP_PLUS_PLUS_DARK_PURPLE = "§b[MVP§5++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_DARK_PURPLE_CODE = "\uE021";

    private static final String AQUA_MVP_PLUS_PLUS_DARK_RED = "§b[MVP§4++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_DARK_RED_CODE = "\uE022";

    private static final String AQUA_MVP_PLUS_PLUS_GOLD = "§b[MVP§6++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_GOLD_CODE = "\uE023";

    private static final String AQUA_MVP_PLUS_PLUS_GRAY = "§b[MVP§8++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_GRAY_CODE = "\uE024";

    private static final String AQUA_MVP_PLUS_PLUS_GREEN = "§b[MVP§a++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_GREEN_CODE = "\uE025";

    private static final String AQUA_MVP_PLUS_PLUS_PINK = "§b[MVP§d++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_PINK_CODE = "\uE026";

    private static final String AQUA_MVP_PLUS_PLUS_RED = "§b[MVP§c++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_RED_CODE = "\uE027";

    private static final String AQUA_MVP_PLUS_PLUS_WHITE = "§b[MVP§f++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_WHITE_CODE = "\uE028";

    private static final String AQUA_MVP_PLUS_PLUS_YELLOW = "§b[MVP§e++§b]";
    private static final String AQUA_MVP_PLUS_PLUS_YELLOW_CODE = "\uE029";

    private static final String MVP_PLUS_PLUS_COLOR = "e5b008";

    private static final String MVP_PLUS_PLUS_BLACK = "§6[MVP§0++§6]";
    private static final String MVP_PLUS_PLUS_BLACK_CODE = "\uE030";

    private static final String MVP_PLUS_PLUS_BLUE = "§6[MVP§9++§6]";
    private static final String MVP_PLUS_PLUS_BLUE_CODE = "\uE031";

    private static final String MVP_PLUS_PLUS_DARK_AQUA = "§6[MVP§3++§6]";
    private static final String MVP_PLUS_PLUS_DARK_AQUA_CODE = "\uE032";

    private static final String MVP_PLUS_PLUS_DARK_BLUE = "§6[MVP§1++§6]";
    private static final String MVP_PLUS_PLUS_DARK_BLUE_CODE = "\uE033";

    private static final String MVP_PLUS_PLUS_DARK_GREEN = "§6[MVP§2++§6]";
    private static final String MVP_PLUS_PLUS_DARK_GREEN_CODE = "\uE034";

    private static final String MVP_PLUS_PLUS_DARK_PURPLE = "§6[MVP§5++§6]";
    private static final String MVP_PLUS_PLUS_DARK_PURPLE_CODE = "\uE035";

    private static final String MVP_PLUS_PLUS_DARK_RED = "§6[MVP§4++§6]";
    private static final String MVP_PLUS_PLUS_DARK_RED_CODE = "\uE036";

    private static final String MVP_PLUS_PLUS_GOLD = "§6[MVP++]";
    private static final String MVP_PLUS_PLUS_GOLD_CODE = "\uE037";

    private static final String MVP_PLUS_PLUS_GRAY = "§6[MVP§8++§6]";
    private static final String MVP_PLUS_PLUS_GRAY_CODE = "\uE038";

    private static final String MVP_PLUS_PLUS_GREEN = "§6[MVP§a++§6]";
    private static final String MVP_PLUS_PLUS_GREEN_CODE = "\uE039";

    private static final String MVP_PLUS_PLUS_PINK = "§6[MVP§d++§6]";
    private static final String MVP_PLUS_PLUS_PINK_CODE = "\uE040";

    private static final String MVP_PLUS_PLUS_PURPLE = "";
    private static final String MVP_PLUS_PLUS_PURPLE_CODE = "\uE041";

    private static final String MVP_PLUS_PLUS_RED = "§6[MVP§c++§6]";
    private static final String MVP_PLUS_PLUS_RED_CODE = "\uE042";

    private static final String MVP_PLUS_PLUS_WHITE = "§6[MVP§f++§6]";
    private static final String MVP_PLUS_PLUS_WHITE_CODE = "\uE043";

    private static final String MVP_PLUS_PLUS_YELLOW = "§6[MVP§e++§6]";
    private static final String MVP_PLUS_PLUS_YELLOW_CODE = "\uE044";

    private static final String MVP = "§b[MVP]";
    private static final String MVP_CODE = "\uE045";

    private static final String VIP = "§a[VIP]";
    private static final String VIP_CODE = "\uE010";
    private static final String VIP_COLOR = "85cd16";

    private static final String VIP_PLUS = "§a[VIP§6+§a]";
    private static final String VIP_PLUS_CODE = "\uE046";

    private static final String YOUTUBE = "§c[§fYOUTUBE§c]";
    private static final String YOUTUBE_CODE = "\uE047";
    private static final String YOUTUBE_COLOR = "ff2f2f";

    private static final String PLAYER = "";
    private static final String PLAYER_CODE = "\uE048";

    private static final String NPC = "§8[NPC]";
    private static final String NPC_CODE = "\uE049";
    private static final String NON_COLOR = "7d838e";

	@Override
	public void onInitializeClient() {
        INSTANCE.updateRankTag(MVP_PLUS_BLACK, MVP_PLUS_BLACK_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_BLUE, MVP_PLUS_BLUE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(VIP, VIP_CODE, VIP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_DARK_AQUA, MVP_PLUS_DARK_AQUA_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_DARK_BLUE, MVP_PLUS_DARK_BLUE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_DARK_GREEN, MVP_PLUS_DARK_GREEN_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_DARK_PURPLE, MVP_PLUS_DARK_PURPLE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_DARK_RED, MVP_PLUS_DARK_RED_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_GOLD, MVP_PLUS_GOLD_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_GRAY, MVP_PLUS_GRAY_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_GREEN, MVP_PLUS_GREEN_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PINK, MVP_PLUS_PINK_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_RED, MVP_PLUS_RED_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_WHITE, MVP_PLUS_WHITE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_YELLOW, MVP_PLUS_YELLOW_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_BLACK, AQUA_MVP_PLUS_PLUS_BLACK_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_BLUE, AQUA_MVP_PLUS_PLUS_BLUE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_DARK_AQUA, AQUA_MVP_PLUS_PLUS_DARK_AQUA_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_DARK_BLUE, AQUA_MVP_PLUS_PLUS_DARK_BLUE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_DARK_GREEN, AQUA_MVP_PLUS_PLUS_DARK_GREEN_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_DARK_PURPLE, AQUA_MVP_PLUS_PLUS_DARK_PURPLE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_DARK_RED, AQUA_MVP_PLUS_PLUS_DARK_RED_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_GOLD, AQUA_MVP_PLUS_PLUS_GOLD_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_GRAY, AQUA_MVP_PLUS_PLUS_GRAY_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_GREEN, AQUA_MVP_PLUS_PLUS_GREEN_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_PINK, AQUA_MVP_PLUS_PLUS_PINK_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_RED, AQUA_MVP_PLUS_PLUS_RED_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_WHITE, AQUA_MVP_PLUS_PLUS_WHITE_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(AQUA_MVP_PLUS_PLUS_YELLOW, AQUA_MVP_PLUS_PLUS_YELLOW_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_BLACK, MVP_PLUS_PLUS_BLACK_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_BLUE, MVP_PLUS_PLUS_BLUE_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_DARK_AQUA, MVP_PLUS_PLUS_DARK_AQUA_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_DARK_BLUE, MVP_PLUS_PLUS_DARK_BLUE_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_DARK_GREEN, MVP_PLUS_PLUS_DARK_GREEN_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_DARK_PURPLE, MVP_PLUS_PLUS_DARK_PURPLE_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_DARK_RED, MVP_PLUS_PLUS_DARK_RED_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_GOLD, MVP_PLUS_PLUS_GOLD_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_GRAY, MVP_PLUS_PLUS_GRAY_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_GREEN, MVP_PLUS_PLUS_GREEN_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_PINK, MVP_PLUS_PLUS_PINK_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_RED, MVP_PLUS_PLUS_RED_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_WHITE, MVP_PLUS_PLUS_WHITE_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP_PLUS_PLUS_YELLOW, MVP_PLUS_PLUS_YELLOW_CODE, MVP_PLUS_PLUS_COLOR);
        INSTANCE.updateRankTag(MVP, MVP_CODE, MVP_COLOR);
        INSTANCE.updateRankTag(VIP_PLUS, VIP_PLUS_CODE, VIP_COLOR);
        INSTANCE.updateRankTag(YOUTUBE, YOUTUBE_CODE, YOUTUBE_COLOR);
        //INSTANCE.updateRankTag(PLAYER, PLAYER_CODE, NON_COLOR);
        INSTANCE.updateRankTag(NPC, NPC_CODE, NON_COLOR);
    }

    public void updateRankTag(String formattedRank, String imageCode, String usernameColor) {
        ClientReceiveMessageEvents.ALLOW_CHAT.register((component, message, gameProfile, bound, instant) -> {
            if (message.decoratedContent() != null && message.decoratedContent().getString().contains("test")) {
                String username = gameProfile.name();
                String replacedMessage = message.decoratedContent().getString().replace("test", "\uE001");
                Minecraft.getInstance().execute(() -> {
                    Minecraft.getInstance().gui.getChat().addMessage(Component.literal(username
                            +": " + Component.literal(replacedMessage)
                            .withColor(Integer.parseInt("0ea4e5", 16))
                    ), message.signature(), null);
                    Minecraft.getInstance().player.connection.sendChat("This is an automated message used in testing for my mod.");
                });
                return false;
            }
            return true;
        });
        ClientReceiveMessageEvents.ALLOW_GAME.register((component, b) -> {
            /*This if statement seems to be the issue, it does not seem to be firing
            when in a game.
            Scratch that, it is only not firing when it is the user who sends it.
             */
            String username = List.of(component.getString().split(Pattern.quote(":"), 2)).getFirst();
            if (component.getString().contains(formattedRank)) {
                List<String> textBeforeRank = List.of(component.getString().split(Pattern.quote(formattedRank), 2));
                MutableComponent componentBeforeRank = Component.literal(textBeforeRank.getFirst());
                MutableComponent rankIcon = Component.literal(imageCode);
                MutableComponent replacedMessage = Component.literal(textBeforeRank.getLast()
                                .replace(formattedRank, ""))
                        .withColor(Integer.parseInt(usernameColor, 16));
                Minecraft.getInstance().execute(() -> {
                    Minecraft.getInstance().gui.getChat().addMessage(componentBeforeRank
                            .append((rankIcon.append(replacedMessage
                                    .append((Component.literal("§b")))))));
                });
                return false;
            }
            return true;
        });
        ClientTickEvents.END_CLIENT_TICK.register((client) -> {
        if (client.getCurrentServer() != null && client.player != null && !client.getConnection().getListedOnlinePlayers().isEmpty() && client.getConnection() != null) {
            int color;

            for (PlayerInfo entry : client.getConnection().getListedOnlinePlayers()) {
                if (entry.getTeam() != null) {
                    if (isMatchingAnyMvpRankTagCode(entry.getTeam().getPlayerPrefix().toString())) {
                        color = Integer.parseInt(MVP_COLOR, 16);
                    } else if (entry.getTeam().getPlayerPrefix().toString()
                            .contains(VIP_CODE) || entry.getTeam().getPlayerPrefix().toString()
                            .contains(VIP_PLUS_CODE)) {
                        color = Integer.parseInt(VIP_COLOR, 16);
                    } else if (isMatchingAnyMvpPlusPlusRankTagCode(
                            entry.getTeam().getPlayerPrefix().toString())) {
                        color = Integer.parseInt(MVP_PLUS_PLUS_COLOR, 16);
                    } else if (entry.getTeam().getPlayerPrefix().toString()
                            .contains(YOUTUBE_CODE)) {
                        color = Integer.parseInt(YOUTUBE_COLOR, 16);
                    } else {
                        if (entry.getTeam().getColor().getColor() != null) {
                            color = Integer.parseInt(entry.getTeam().getColor().getColor().toString());
                        } else {
                            color = Integer.parseInt("7d838e", 16);
                        }
                    }
                } else {
                    color = Integer.parseInt("7d838e", 16);
                }
                MutableComponent displayName = Component.literal(entry.getProfile().name())
                        .withColor(color);
                if (entry.getTeam() != null) {
                    entry.getTeam().setPlayerPrefix(Component.literal(entry.getTeam().getPlayerPrefix()
                            .getString().replace(formattedRank, imageCode)));
                }
                MutableComponent rank = Component.literal(entry.getTeam() != null ? entry.getTeam()
                        .getPlayerPrefix().getString().replace(formattedRank, imageCode): "");
                String tag = entry.getTeam() != null ? entry.getTeam()
                                .getPlayerSuffix().getString() : "";
                if (!isInSkyblock()) {
                    entry.setTabListDisplayName(
                            rank.append(displayName).append(tag));
                }
            }
            }
        });
    }
    boolean isMatchingAnyMvpRankTagCode(String string) {
        return string.contains(MVP_PLUS_BLACK_CODE)
                || string.contains(MVP_CODE)
                || string.contains(MVP_PLUS_BLUE_CODE)
                || string.contains(MVP_PLUS_DARK_AQUA_CODE)
                || string.contains(MVP_PLUS_DARK_BLUE_CODE)
                || string.contains(MVP_PLUS_DARK_GREEN_CODE)
                || string.contains(MVP_PLUS_DARK_PURPLE_CODE)
                || string.contains(MVP_PLUS_DARK_RED_CODE)
                || string.contains(MVP_PLUS_GOLD_CODE)
                || string.contains(MVP_PLUS_GRAY_CODE)
                || string.contains(MVP_PLUS_GREEN_CODE)
                || string.contains(MVP_PLUS_PINK_CODE)
                || string.contains(MVP_PLUS_RED_CODE)
                || string.contains(MVP_PLUS_WHITE_CODE)
                || string.contains(MVP_PLUS_YELLOW_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_BLACK_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_BLUE_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_DARK_AQUA_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_DARK_BLUE_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_DARK_GREEN_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_DARK_PURPLE_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_DARK_RED_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_GOLD_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_GRAY_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_GREEN_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_PINK_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_RED_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_WHITE_CODE)
                || string.contains(AQUA_MVP_PLUS_PLUS_YELLOW_CODE);
    }
    boolean isMatchingAnyMvpPlusPlusRankTagCode(String string) {
        return string.contains(MVP_PLUS_PLUS_BLACK_CODE)
                || string.contains(MVP_PLUS_PLUS_BLUE_CODE)
                || string.contains(MVP_PLUS_PLUS_DARK_AQUA_CODE)
                || string.contains(MVP_PLUS_PLUS_DARK_BLUE_CODE)
                || string.contains(MVP_PLUS_PLUS_DARK_GREEN_CODE)
                || string.contains(MVP_PLUS_PLUS_DARK_PURPLE_CODE)
                || string.contains(MVP_PLUS_PLUS_DARK_RED_CODE)
                || string.contains(MVP_PLUS_PLUS_GOLD_CODE)
                || string.contains(MVP_PLUS_PLUS_GRAY_CODE)
                || string.contains(MVP_PLUS_PLUS_GREEN_CODE)
                || string.contains(MVP_PLUS_PLUS_PINK_CODE)
                || string.contains(MVP_PLUS_PLUS_RED_CODE)
                || string.contains(MVP_PLUS_PLUS_WHITE_CODE)
                || string.contains(MVP_PLUS_PLUS_YELLOW_CODE);

    }
    boolean isInSkyblock() {
        Scoreboard scoreboard = Minecraft.getInstance().level.getScoreboard();

        if (scoreboard != null) {
            Objective objective = scoreboard.getDisplayObjective(DisplaySlot.SIDEBAR);
            if (objective != null) {
                String name = StringUtil.stripColor(objective.getName());
                return name.contains("SBScoreboard");
            }
        }
        return false;
    }
}