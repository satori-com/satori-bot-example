This howto shows how to develop and test your javascript Satori streambot code. The sample utilizes a
configuration file called `config.json` and a javascript file called `bot.js` to setup your streambot
and run custom code, respectively.

# Running a Javascript streambot locally
In order to run a streambot locally, you'll first need to update the `config.json` file and replace
the words `APPKEY` and `HOST` with appkey and endpoint that you received from DevPortal, respectively.
Then run
```
./gradlew runBot
```
The `bot.js` example is now up and running, listening for incoming messages on a channel called `example.in`.

# Testing your streambot
Satori provides a tool to send and receive messages, which can be used to test your streambot. To
install the satori cli utilities, run the following command on your machine: `pip install satori-rtm-cli`.

The `bot.js` example copies traffic from the channel `example.in` to the channel `example.out`. Refer
`config.json` to see how we configure it.

* Let's start a subscriber client that listens to the `example.out` channel:
```
satori-rtm-cli --endpoint=<your_endpoint> --appkey=<your_appkey> subscribe example.out
```

* Let's now publish to `example.in` channel:
```
echo '{"brand":"batman", "model":"forever", "catchphrase":"ILikePenguins"}' | satori-rtm-cli --endpoint=<your_endpoint> --appkey=<your_appkey> publish example.in
```

The subscriber started above should have received the published data.

Alternatively, the `bot.js` example also logs the incoming messages. The logs can indicate whether
the streambot is setup and working properly.

# Uploading your Javascript bot to DevPortal
Using the provided template streambot, you can make changes to create your own streambot. Once you
are satisfied with the changes, you can navigate to DevPortal's Streambots page to `Add a streambot`
by drag&drop the `bot.js` within the page.
