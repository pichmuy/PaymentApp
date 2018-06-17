package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 3/17/2018.
 */

public class ReturnReferenceJsonSerializer  implements JsonSerializer<ReturnMiddleRequest> {

    @Override
    public JsonElement serialize(ReturnMiddleRequest returnMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject object=new JsonObject();
        JsonObject object1=new JsonObject();
        object1.add("deviceID",jsonSerializationContext.serialize(returnMiddleRequest.getdeviceID()));
        object1.add("transactionKey",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionKey()));
        object1.add("transactionAmount",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionAmount()));
        object1.add("transactionID",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionID()));
        object.add("Return",object1);

        return object;

    }
}
