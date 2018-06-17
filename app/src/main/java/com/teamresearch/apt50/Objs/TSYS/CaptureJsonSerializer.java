package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 2/14/2018.
 */

public class CaptureJsonSerializer implements JsonSerializer<CaptureMiddleRequest> 
{
    @Override
    public JsonElement serialize(CaptureMiddleRequest captureMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add("deviceID", jsonSerializationContext.serialize(captureMiddleRequest.getdeviceID()));
        object1.add("transactionKey", jsonSerializationContext.serialize(captureMiddleRequest.gettransactionKey()));
        object1.add("transactionAmount", jsonSerializationContext.serialize(captureMiddleRequest.gettransactionAmount()));
        object1.add("tip", jsonSerializationContext.serialize(captureMiddleRequest.gettip()));
        object1.add("transactionID", jsonSerializationContext.serialize(captureMiddleRequest.gettransactionID()));
        object1.add("orderNotes", jsonSerializationContext.serialize(captureMiddleRequest.getorderNotes()));
        object.add("Capture", object1);
        return object;
    }
}
