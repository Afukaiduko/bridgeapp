package io;

import com.google.gson.*;
import model.Bid;

import java.lang.reflect.Type;

public class BidAdapter implements JsonSerializer<Bid>, JsonDeserializer<Bid> {

    private final String BID_TYPE = "BID_TYPE";

    /**
     * Saving the bid
     */
    @Override
    public JsonElement serialize(Bid bid, Type type, JsonSerializationContext context) {
        JsonElement elt = context.serialize(bid, bid.getClass());
        elt.getAsJsonObject().addProperty(BID_TYPE, bid.getClass().getCanonicalName()); // Save the Bid's type.
        return elt;
    }

    /**
     * Reading the bid
     */
    @Override
    public Bid deserialize(JsonElement elt, Type type, JsonDeserializationContext context) throws JsonParseException {
        //Get the type saved in order to distinguish between which Bid type to use
        JsonObject obj = elt.getAsJsonObject();
        String className = obj.get(BID_TYPE).getAsString();
        try {
            Class<?> clazz = Class.forName(className);
            return context.deserialize(elt, clazz); // Deserialize it as this particular class.
        } catch (ClassNotFoundException e) {
            System.out.println("For some reason the class type wasn't recognized o.O... shouldn't happen :(");
            e.printStackTrace();
            throw new JsonParseException(e); // Surface the error
        }
    }

}
