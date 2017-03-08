This howto shows how to develop and test a Satori template bot.

# Build the bot

You can build the bot using the following command

```./gradlew botJar```

# Running a bot locally

In order to run a bot locally, we need to download the standalone execution framework version relevant to our bot (the one that the bot was built against). You can get the current version of the execution framework from the following location:

```
https://github.com/satori-com/satori-bots-java-core/releases/tag/1.0
```

Update the `config-example.json` with your endpoint and appkeys that you received from DevPortal

You can use the provided helper script to launch a bot locally:
```
./local_run.sh execution_framework_jar bot_jar bot_config_file
```

# Example
Let's run a simple bot that copies traffic from the channel `example.in` to the channel `example.out`. Have a look at `config-example.json` to see how we configure it.

In order to run the examples, make sure satori cli is installed on your machine: `pip install satori-cli`

* Let's start a client that listens to the `example.out` channel:
```
satori_cli.py --endpoint=<your_endpoint> --appkey=<your_appkey> subscribe example.out
```

* Let's now publish to `example.in` channel:
```
echo '{"brand":"batman", "model":"forever", "catchphrase":"ILikePenguins"}' | satori_cli.py --endpoint=<your_endpoint> --appkey=<your_appkey> publish example.in
```

You should not see any messages because our bot is not running yet (if you do, it means someone else is running the example at the same time. Change the channel names you are using and try again).

* Now that our test setup is ready, let's build the template bot. The template bot is a simple bot that can copy data between two channels, possibly running a filter on the stream:
```
./gradlew botJar
```

* Once the code is built, let's run it locally (Need to first download the execution-framework-public.jar as described above)

```
./local_run.sh execution-framework-public.jar build/libs/satori-bot-example-0.1.jar config-example.json
```

* The RTM subscriber started above should now start receiving data

# Developing


You need the Satori Bots API jar to build the bot. The API jar can be added to gradle dependencies like so:
```
compile group: 'com.satori', name:'satori-bots-java-core', version: '1.0'
```

You can import this repository as an IntelliJ IDEA project.
