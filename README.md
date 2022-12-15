# Intelligent bot for Robocode Tank Royal

This project focuses on creating a bot which will be able
to defeat every opponent on the map. All directories contain
sample bots except IntelligentBot - this directory contains
our extended implementation. Any changes from us are made here.

### Running

In terminal:

```shell
java -jar robocode-tankroyale-gui-0.17.4.jar
```

Then in ```Config/Bot Root Directories``` add this root project
directory. After running server (```Server/Start Server```) 
and creating battle (```Battle/Start Battle```), all 
sample bots including the extended one should be visible.

### Debugging

Ensure server is running, and you have inserted some printing
to the bot implementation. Then it is time to run bot from console.

```shell
cd IntelligentBot
./IntelligentBot.sh
```

If error with no secret appears, copy the bot-secret from
automatically created server.properties file and paste it
to this command:

```shell
export SERVER_SECRET={BOT_SECRET}
```

And then, execute it of course.
