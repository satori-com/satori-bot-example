console.log('initializing bot...')

var outputChannel = config['outputChannel']
console.log('output channel: %s', outputChannel)

//forward message received from example.in channel to example.out channel
function onMessage(channel, message) {
    console.log("message received: '%s'", message);
    rtm.publish(outputChannel, message)
}