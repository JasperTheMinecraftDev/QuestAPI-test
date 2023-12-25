package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.Quest_API;
import nl.juriantech.questapi.objects.Quest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

import java.util.Map;
import java.util.UUID;

public class GetStats {

    private final Quest_API api;

    public GetStats(Quest_API api) {
        this.api = api;
    }

    @Command("getstats")
    public void onGetStats(Player player, String questId) {
        Quest quest = api.getQuestManager().getQuest(questId);

        if (quest == null) {
            player.sendMessage("That quest doesn't exist!");
            return;
        }

        Map<UUID, Integer> playerProgress = quest.getPlayerProgress();

        if (playerProgress.isEmpty()) {
            player.sendMessage("No player progress found for quest '" + questId + "'.");
            return;
        }

        StringBuilder statsMessage = new StringBuilder("Stats for quest '" + questId + "':\n");

        for (Map.Entry<UUID, Integer> entry : playerProgress.entrySet()) {
            String playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
            int playerLevel = entry.getValue();

            statsMessage.append(playerName).append(" - Level ").append(playerLevel).append("\n");
        }

        player.sendMessage(statsMessage.toString());
    }
}
