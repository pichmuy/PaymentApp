package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 2/14/2018.
 */

public class VoidJsonSerializer implements JsonSerializer<VoidMiddleRequest> {
    @Override
    public JsonElement serialize(VoidMiddleRequest voidMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add("deviceID", jsonSerializationContext.serialize(voidMiddleRequest.getdeviceID()));
        object1.add("transactionKey", jsonSerializationContext.serialize(voidMiddleRequest.gettransactionKey()));
        object1.add("transactionID", jsonSerializationContext.serialize(voidMiddleRequest.gettransactionID()));
        object.add("Void", object1);
        return object;
    }
}
