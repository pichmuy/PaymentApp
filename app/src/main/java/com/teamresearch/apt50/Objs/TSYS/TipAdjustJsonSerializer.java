package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 2/19/2018.
 */

public class TipAdjustJsonSerializer implements JsonSerializer<TipAdjustMiddleRequest> {


    @Override
    public JsonElement serialize(TipAdjustMiddleRequest tipAdjustMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add("deviceID", jsonSerializationContext.serialize(tipAdjustMiddleRequest.getdeviceID()));
        object1.add("transactionKey", jsonSerializationContext.serialize(tipAdjustMiddleRequest.gettransactionKey()));
        object1.add("tip", jsonSerializationContext.serialize(tipAdjustMiddleRequest.gettip()));
        object1.add("transactionID", jsonSerializationContext.serialize(tipAdjustMiddleRequest.gettransactionID()));
        object1.add("developerID", jsonSerializationContext.serialize(tipAdjustMiddleRequest.getdeveloperID()));
        object.add("TipAdjustment", object1);

        return object;
    }
}
