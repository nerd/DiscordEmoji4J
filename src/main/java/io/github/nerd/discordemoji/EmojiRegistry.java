package io.github.nerd.discordemoji;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * @author ashley
 * @since 4/27/17 7:00 PM
 */
public class EmojiRegistry {
	private static List<DiscordEmoji> emojis = new ArrayList<>();

	static {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.readValue(EmojiRegistry.class.getResource("/discord_emoji.json"), EmojiRegistry.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private EmojiRegistry() {
	}

	public static Optional<DiscordEmoji> getEmojiByAlias(String name) {
		Matcher matcher = EmojiDiversity.DIVERSITY_PATTERN_ALIAS.matcher(name.toLowerCase());
		EmojiDiversity diversity = EmojiDiversity.NONE;
		if (matcher.find())
			diversity = EmojiDiversity.fromAlias(matcher.group());
		final String name1 = matcher.replaceAll("").replaceAll(":", "");
		Optional<DiscordEmoji> opt = emojis.stream().filter(emoji -> emoji.getAliases().contains(name1)).findFirst();
		if (opt.isPresent())
			return Optional.of(opt.get().withDiversity(diversity));
		return opt;
	}

	public static Optional<DiscordEmoji> getEmojiByUnicode(String unicode) {
		Matcher matcher = EmojiDiversity.DIVERSITY_PATTERN.matcher(unicode);
		EmojiDiversity diversity = EmojiDiversity.NONE;
		if (matcher.find())
			diversity = EmojiDiversity.fromUnicode(matcher.group());
		final String unicode1 = matcher.replaceAll("");
		Optional<DiscordEmoji> opt = emojis.stream().filter(emoji -> emoji.getUnicode().equalsIgnoreCase(unicode1)).findFirst();
		if (opt.isPresent()) {
			return Optional.of(opt.get().withDiversity(diversity));
		}
		return opt;
	}

	public static List<DiscordEmoji> getEmojisWithDiversity() {
		return emojis.stream().filter(DiscordEmoji::hasDiversity).collect(Collectors.toList());
	}

	public static List<DiscordEmoji> getEmojisWithoutDiversity() { // you racist bastard
		return emojis.stream().filter(emoji -> !emoji.hasDiversity()).collect(Collectors.toList());
	}

	public static List<DiscordEmoji> getEmojisByCategory(EmojiCategory category) {
		return emojis.stream().filter(emoji -> emoji.getCategory().equals(category)).collect(Collectors.toList());
	}

	public static List<DiscordEmoji> getEmojis() {
		return emojis;
	}

	private void setEmojis(List<DiscordEmoji> emojis) {
		EmojiRegistry.emojis = emojis;
	}
}
