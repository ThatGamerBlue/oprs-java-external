package com.thatgamerblue.plugins.example;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginType;

@Slf4j
@PluginDescriptor(
	name = "Example Plugin",
	description = "Example Java Plugin for OPRS",
	tags = {"example", "plugin", "duhhhhh"},
	type = PluginType.EXTERNAL
)
public class ExamplePlugin extends Plugin
{
	public static final String VERSION = "@version@";

	@Inject
	private Client client;

	@Override
	public void startUp()
	{
		log.info("Example plugin v{} starting up", VERSION);
	}

	@Override
	public void shutDown()
	{
		log.info("Example plugin shutting down");
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Game tick!", "");
	}
}
