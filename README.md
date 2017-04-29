# DiscordEmoji4J
An emoji library that uses Discord's definitions

## how to use this steaming pile of garbage
```java
EmojiRegistry.getGlobalEmojis(); // get global emojis, like :raised_hands:
EmojiRegistry registry = new EmojiRegistry(); // create an EmojiRegistry
registry.getEmojiByUnicode("\uD83D\uDE4C\uD83C\uDFFD"); // get an emoji from its unicode form
CustomEmoji emoji = new CustomEmoji(69, "meme", 420); // creates a custom emoji with the id 69, alias meme, and guild id 420
registry.registerEmoji(emoji); // register it with the registry
registry.getEmojis(); // get all local emojis for this registry
registry.getEmojiByAlias("meme"); // get an emoji with the alias meme
```
