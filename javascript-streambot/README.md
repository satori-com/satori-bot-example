This howto shows how to develop and test your javascript Satori streambot code.  The sample utilizes a 
javascript file called `bot.js` and a configuration file called `config.json` to run custom code 
and setup your streambot, respectively.

# Running your javascript streambot locally

In order to run a streambot locally, you'll first need to update the `config.json` file and replace all instances of the words `APPKEY` and `HOST` 
with your endpoint and appkeys that you received from DevPortal.  Then run
```
./gradlew runBot
```

The `bot.js` example is now up and running and listening for incoming messages on a channel called `example.in`.  

# Verifying your streambot
Satori provides a tool to send/receive messages and to test your streambot. To install the satori cli utilities,
run the following command on your machine: `pip install satori-cli`.

* Let's now publish to `example.in` channel:
```
echo '{"brand":"batman", "model":"forever", "catchphrase":"ILikePenguins"}' | satori_cli.py --endpoint=<your_endpoint> --appkey=<your_appkey> publish example.in
```

To verify whether your javascript logic is properly configured to process incoming messages, take a look at your log files.  When running locally, 
the streambot framework creates a directory called `log` at the root of your project directory.  In that directory, you will
find log files name `user.log` and `stderr-bot-xx.log`.  The sample `bot.js` echoes the incoming RTM message into the `user.log` file.  Verify that this is indeed being output to this log file.

Once you are satisfied with the changes to your streambot, you can navigate to DevPortal's Streambots page to `Add a streambot` by drag&drop the `bot.js` within the page.  
