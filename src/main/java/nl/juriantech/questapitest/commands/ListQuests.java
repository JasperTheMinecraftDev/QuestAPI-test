package nl.juriantech.questapitest.commands;

import nl.juriantech.questapi.QuestAPI;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

public class ListQuests {

    private final QuestAPI api;

    public ListQuests(QuestAPI api) {
        this.api = api;
    }

    @Command("listquests")
    public void onListQuests(Player player) {
        StringBuilder questList = new StringBuilder("Available quests:\n");

        for (String questId : api.getQuestManager().getAllQuests().keySet()) {
            questList.append("- ").append(questId).append("\n");
        }

        player.sendMessage(questList.toString());
    }
}
