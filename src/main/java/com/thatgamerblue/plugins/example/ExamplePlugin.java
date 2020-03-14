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
	name = ExamplePlugin.PLUGIN_NAME,
	description = "Example Java Plugin for OPRS",
	tags = {"example", "plugin", "duhhhhh"},
	type = PluginType.EXTERNAL
)
public class ExamplePlugin extends Plugin
{
	public static final String VERSION = "@version@";
	public static final String PLUGIN_NAME = "@pluginname@";

	@Inject
	private Client client;

	@Override
	public void startUp()
	{
		log.info("{} v{} starting up", PLUGIN_NAME, VERSION);
	}

	@Override
	public void shutDown()
	{
		log.info("{} shutting down", PLUGIN_NAME);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Game tick!", "");
	}
}
