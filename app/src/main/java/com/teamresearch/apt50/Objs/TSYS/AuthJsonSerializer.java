package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 2/13/2018.
 */

public class AuthJsonSerializer implements JsonSerializer<AuthMiddleRequest> {
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
            object1.add("cardNumber",jsonSerializationContext.serialize(authMiddleRequest.getcardNumber()));
            object1.add("expirationDate",jsonSerializationContext.serialize(authMiddleRequest.getexpirationDate()));
            object1.add("cvv2",jsonSerializationContext.serialize(authMiddleRequest.getcvv2()));
            object1.add("addressLine1",jsonSerializationContext.serialize(authMiddleRequest.getaddressLine1()));
            object1.add("zip",jsonSerializationContext.serialize(authMiddleRequest.getzip()));
            object1.add("orderNumber",jsonSerializationContext.serialize(authMiddleRequest.getorderNumber()));
            object1.add("firstName",jsonSerializationContext.serialize(authMiddleRequest.getfirstName()));
            object1.add("lastName",jsonSerializationContext.serialize(authMiddleRequest.getlastName()));
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
