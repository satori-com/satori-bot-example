This howto shows how to develop and test a Satori template streambot.

# Running a Java streambot locally

In order to run a streambot locally, you'll first need to update the `config.json` file and replace the words `APPKEY` and `HOST` with appkey and endpoint that you received from DevPortal, respectively.

### To start your streambot in your IDE
Start the `MyBot.java` as a Java application. For example, to run your streambot from within the IntelliJ IDE,
follow these steps.

* Click on the the green arrow next to the `main` method in `MyBot.java`. This will create a new run configuration called `MyBot`.
* Click on the green arrow next to your new configuration to run the application.

### To start your streambot on the command line using gradle
Simply run
```
./gradlew runBot
```

The MyBot example is now up and running and listening for incoming messages.

# Testing your Streambot
Satori provides a tool to send and receive messages, which can be used to test your streambot. To install the satori cli utilities, run the following command on your machine: `pip install satori-rtm-cli`.

The `MyBot.java` example copies traffic from the channel `example.in` to the channel `example.out`. Refer `config.json` to see how we configure it.

* Let's start a subscriber client that listens to the `example.out` channel:
```
satori-rtm-cli --endpoint=<your_endpoint> --appkey=<your_appkey> subscribe example.out
```

* Let's now publish to `example.in` channel:
```
echo '{"brand":"batman", "model":"forever", "catchphrase":"ILikePenguins"}' | satori-rtm-cli --endpoint=<your_endpoint> --appkey=<your_appkey> publish example.in
```

The subscriber started above should have received the published data.

Alternatively, the `MyBot.java` example also logs the incoming messages. The logs can indicate whether the streambot is setup
and working properly.

# Uploading your bot to DevPortal
Using the provided template bot, you can make changes to create your own bot. Once you are satisfied with your changes to your streambot and want to upload your streambot to DevPortal, you'll need to create an archive file.  Run the following command (or run the corresponding gradle task from the IDE):
```
./gradlew botJar
```
This will create a new jar file which can be uploaded to DevPortal
