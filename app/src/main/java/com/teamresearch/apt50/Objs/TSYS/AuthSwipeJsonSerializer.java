package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 3/6/2018.
 */

public class AuthSwipeJsonSerializer implements JsonSerializer<AuthMiddleRequest> {

    @Override
    public JsonElement serialize(AuthMiddleRequest authMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject object=new JsonObject();
        JsonObject object1=new JsonObject();
        object1.add("deviceID",jsonSerializationContext.serialize(authMiddleRequest.getdeviceID()));
        object1.add("transactionKey",jsonSerializationContext.serialize(authMiddleRequest.gettransactionKey()));
        object1.add("cardDataSource",jsonSerializationContext.serialize(authMiddleRequest.getcardDataSource()));
        object1.add("transactionAmount",jsonSerializationContext.serialize(authMiddleRequest.gettransactionAmount()));
        object1.add("tip",jsonSerializationContext.serialize(authMiddleRequest.gettip()));
        object1.add("currencyCode",jsonSerializationContext.serialize(authMiddleRequest.getcurrencyCode()));
        if(authMiddleRequest.gettrack1Data()!=null&&authMiddleRequest.gettrack1Data().length()>0)
        {
            object1.add("track1Data", jsonSerializationContext.serialize(authMiddleRequest.gettrack1Data()));
        }
        else {
            object1.add("track2Data", jsonSerializationContext.serialize(authMiddleRequest.gettrack2Data()));
        }
        object1.add("securityProtocol",jsonSerializationContext.serialize(authMiddleRequest.getsecurityProtocol()));
        object1.add("ucafCollectionIndicator",jsonSerializationContext.serialize(authMiddleRequest.getucafCollectionIndicator()));
        object1.add("orderNumber",jsonSerializationContext.serialize(authMiddleRequest.getorderNumber()));
        object1.add("tokenRequired",jsonSerializationContext.serialize(authMiddleRequest.gettokenRequired()));
        object1.add("terminalCapability",jsonSerializationContext.serialize(authMiddleRequest.getterminalCapability()));
        object1.add("terminalOperatingEnvironment",jsonSerializationContext.serialize(authMiddleRequest.getterminalOperatingEnvironment()));
        object1.add("cardholderAuthenticationMethod",jsonSerializationContext.serialize(authMiddleRequest.getcardholderAuthenticationMethod()));
        object1.add("terminalAuthenticationCapability",jsonSerializationContext.serialize(authMiddleRequest.getterminalAuthenticationCapability()));
        object1.add("terminalOutputCapability",jsonSerializationContext.serialize(authMiddleRequest.getterminalOutputCapability()));
        object1.add("maxPinLength",jsonSerializationContext.serialize(authMiddleRequest.getmaxPinLength()));
        object.add("Auth",object1);

        return object;

    }
}
