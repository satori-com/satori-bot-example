package com.satori.bots.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.satori.bots.framework.Ack;
import com.satori.bots.framework.Bot;
import com.satori.bots.framework.BotContext;
import com.satori.bots.framework.BotExecutor;
import com.satori.bots.framework.RtmException;
import com.satori.bots.framework.RtmSubscriptionData;
import org.slf4j.Logger;

/**
 * This simple bot republishes all received messages to a configurable outputChannel
 */
public class MyBot implements Bot {
  private static final Logger logger = BotContext.getLogger(MyBot.class);

  private String outputChannel;
  private static final String CONFIG_KEY = "outputChannel";
  private static final String DEFAULT_OUTPUT_CHANNEL = "output";

  @Override
  public void onSetup(BotContext botContext) {

    final JsonObject object = botContext.getCustomConfiguration().getAsJsonObject();
    outputChannel = object.get(CONFIG_KEY).getAsString();

    logger.info("Setup done for example bot");
  }

  @Override
  public void onSubscriptionData(BotContext ctx, RtmSubscriptionData messages) {
    for (JsonElement msg : messages.getMessages()) {
      try {
        ctx.getRtmProxy().publish(outputChannel, msg, Ack.YES);
        logger.info("Published message: '{}'", msg);
      } catch (InterruptedException e) {
        logger.error("Execution interrupted", e);
        Thread.currentThread().interrupt();
      } catch (RtmException e) {
        logger.error("RTM problem: ", e);
      }
    }
  }

  public static void main(String[] args) throws Exception {
    BotExecutor executor = new BotExecutor("config.json");
    executor.start(new MyBot());
  }

}
