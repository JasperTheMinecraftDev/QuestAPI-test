package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.QuestAPI;
import nl.juriantech.questapi.objects.Quest;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class CreateQuest {

    private final QuestAPI api;

    public CreateQuest(QuestAPI api) {
        this.api = api;
    }

    @Command("createquest")
    public void onCreateQuest(Player player, String questId, int maxLevels, int databaseUpdateIntervalSeconds) {
        if (maxLevels <= 0) {
            player.sendMessage("Usage: /createquest <questId> <maxLevels> <databaseUpdateIntervalSeconds>");
            return;
        }

        Quest newQuest = new Quest(questId, maxLevels, api.getDatabase(), databaseUpdateIntervalSeconds);
        api.getQuestManager().addQuest(newQuest);

        player.sendMessage("Quest '" + questId + "' created successfully with " + maxLevels + " levels!");
    }
}
