package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 2/13/2018.
 */

public class ReturnJsonSerializer implements JsonSerializer<ReturnMiddleRequest> {

    @Override
    public JsonElement serialize(ReturnMiddleRequest returnMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject object=new JsonObject();
        JsonObject object1=new JsonObject();
        object1.add("deviceID",jsonSerializationContext.serialize(returnMiddleRequest.getdeviceID()));
        object1.add("transactionKey",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionKey()));
        object1.add("cardDataSource",jsonSerializationContext.serialize(returnMiddleRequest.getcardDataSource()));
        object1.add("transactionAmount",jsonSerializationContext.serialize(returnMiddleRequest.gettransactionAmount()));
        object1.add("cardNumber",jsonSerializationContext.serialize(returnMiddleRequest.getcardNumber()));
        object1.add("expirationDate",jsonSerializationContext.serialize(returnMiddleRequest.getexpirationDate()));
        object1.add("cvv2",jsonSerializationContext.serialize(returnMiddleRequest.getcvv2()));
        object1.add("terminalCapability",jsonSerializationContext.serialize(returnMiddleRequest.getterminalCapability()));
        object1.add("terminalOperatingEnvironment",jsonSerializationContext.serialize(returnMiddleRequest.getterminalOperatingEnvironment()));
        object1.add("cardholderAuthenticationMethod",jsonSerializationContext.serialize(returnMiddleRequest.getcardholderAuthenticationMethod()));
        object1.add("terminalAuthenticationCapability",jsonSerializationContext.serialize(returnMiddleRequest.getterminalAuthenticationCapability()));
        object1.add("terminalOutputCapability",jsonSerializationContext.serialize(returnMiddleRequest.getterminalOutputCapability()));
        object1.add("maxPinLength",jsonSerializationContext.serialize(returnMiddleRequest.getmaxPinLength()));
        object1.add("developerID",jsonSerializationContext.serialize(returnMiddleRequest.getdeveloperID()));
        object.add("Return",object1);

        return object;

    }
}
