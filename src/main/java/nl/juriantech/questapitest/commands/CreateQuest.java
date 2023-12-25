package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.Quest_API;
import nl.juriantech.questapi.objects.Quest;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class CreateQuest {

    private final Quest_API api;

    public CreateQuest(Quest_API api) {
        this.api = api;
    }

    @Command("createquest")
    public void onCreateQuest(Player player, String questId, int maxLevels) {
        if (maxLevels <= 0) {
            player.sendMessage("Usage: /createquest <questId> <maxLevels>");
            return;
        }

        Quest newQuest = new Quest(questId, maxLevels, api.getDatabase());
        api.getQuestManager().addQuest(newQuest);

        player.sendMessage("Quest '" + questId + "' created successfully with " + maxLevels + " levels!");
    }
}
