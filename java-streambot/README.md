This howto shows how to develop and test a Satori template streambot.

# Running a streambot locally

In order to run a streambot locally, you'll first need to update the `config.json` file and replace all instances of the words `APPKEY` and `HOST` 
with your endpoint and appkeys that you received from DevPortal.

### To start your streambot in your IDE
Start the `MyBot.java` as an Java application.  For example, to run your streambot from within the IntelliJ IDE,
follow these steps.

* Click on the the green arrow next to the `main` method in `MyBot.java`. This will create a new run configuration called `MyBot`.  
* Click on the green arrow next to your new configuration to run the application.

### To start your streambot on the command line using gradle
Simply run
```
./gradlew runBot
```
 
The MyBot example is now up and running and listening for incoming messages.  

# Testing your Bot
Satori provides a tool to send/receive messages and to test your streambot. To install the satori cli utilities,
run the following command on your machine: `pip install satori-cli`.

The `MyBot.java` example copies traffic from the channel `example.in` to the channel `example.out`. Have a look at `config.json` to see how we configure it.

* Let's start a client that listens to the `example.out` channel:
```
satori_cli.py --endpoint=<your_endpoint> --appkey=<your_appkey> subscribe example.out
```

* Let's now publish to `example.in` channel:
```
echo '{"brand":"batman", "model":"forever", "catchphrase":"ILikePenguins"}' | satori_cli.py --endpoint=<your_endpoint> --appkey=<your_appkey> publish example.in
```

The RTM subscriber started above should now start receiving data.

Alternatively, the `MyBot.java` example also uses a logger to log the incoming messages.

Once you are satisfied with your changes to your streambot and want to upload your streambot to DevPortal, 
you'll need to create an archive file.  Run the following command (or run the corresponding gradle task from the IDE):
```
./gradlew botJar
```
This will create a new jar file which can be uploaded.
