package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 3/15/2018.
 */

public class ReturnSwipeJsonSerializer implements JsonSerializer<ReturnMiddleRequest> {
    @Override
    public JsonElement serialize(ReturnMiddleRequest returnMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject object=new JsonObject();
        JsonObject object1=new JsonObject();
        object1.add("deviceID",jsonSerializationContext.serialize(returnMiddleRequest.getdeviceID()));
        object1.add("transactionKey",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionKey()));
        object1.add("transactionAmount",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionAmount()));
        object1.add("track2Data",jsonSerializationContext.serialize(returnMiddleRequest.gettrack2Data()));
        object1.add("securityProtocol",jsonSerializationContext.serialize(returnMiddleRequest.getsecurityProtocol()));
        object1.add("ucafCollectionIndicator",jsonSerializationContext.serialize(returnMiddleRequest.getucafCollectionIndicator()));
        object1.add("terminalCapability",jsonSerializationContext.serialize(returnMiddleRequest.getterminalCapability()));
        object1.add("terminalOperatingEnvironment",jsonSerializationContext.serialize(returnMiddleRequest.getterminalOperatingEnvironment()));
        object1.add("cardholderAuthenticationMethod",jsonSerializationContext.serialize(returnMiddleRequest.getcardholderAuthenticationMethod()));
        object1.add("terminalAuthenticationCapability",jsonSerializationContext.serialize(returnMiddleRequest.getterminalAuthenticationCapability()));
        object1.add("terminalOutputCapability",jsonSerializationContext.serialize(returnMiddleRequest.getterminalOutputCapability()));
        object1.add("maxPinLength",jsonSerializationContext.serialize(returnMiddleRequest.getmaxPinLength()));
        object.add("Return",object1);

        return object;

    }
}
