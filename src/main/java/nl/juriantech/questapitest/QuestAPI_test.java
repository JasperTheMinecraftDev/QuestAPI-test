package nl.juriantech.questapitest;

import nl.juriantech.questapi.Quest_API;
import nl.juriantech.questapitest.commands.*;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

public final class QuestAPI_test extends JavaPlugin {

    private Quest_API api;

    @Override
    public void onEnable() {
        api = Quest_API.getPlugin(Quest_API.class);
        BukkitCommandHandler commandHandler = BukkitCommandHandler.create(this);

        commandHandler.register(new CreateQuest(api));
        commandHandler.register(new StartQuest(api));
        commandHandler.register(new LevelUp(api));
        commandHandler.register(new GetStats(api));
        commandHandler.register(new ListQuests(api));
        commandHandler.registerBrigadier();
    }
}