package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 3/8/2018.
 */

public class SaleSwipeJsonSerializer implements JsonSerializer<SaleMiddleRequest> {

    @Override
    public JsonElement serialize(SaleMiddleRequest saleMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext)
    {
        JsonObject object=new JsonObject();
        JsonObject object1=new JsonObject();
        object1.add("deviceID",jsonSerializationContext.serialize(saleMiddleRequest.getdeviceID()));
        object1.add("transactionKey",jsonSerializationContext.serialize(saleMiddleRequest.gettransactionKey()));
        object1.add("cardDataSource",jsonSerializationContext.serialize(saleMiddleRequest.getcardDataSource()));
        object1.add("transactionAmount",jsonSerializationContext.serialize(saleMiddleRequest.gettransactionAmount()));
        object1.add("tip",jsonSerializationContext.serialize(saleMiddleRequest.gettip()));
        object1.add("currencyCode",jsonSerializationContext.serialize(saleMiddleRequest.getcurrencyCode()));
        if(saleMiddleRequest.gettrack1Data()!=null&&saleMiddleRequest.gettrack1Data().length()>0)
        {
            object1.add("track1Data", jsonSerializationContext.serialize(saleMiddleRequest.gettrack1Data()));
        }
        else {
            object1.add("track2Data", jsonSerializationContext.serialize(saleMiddleRequest.gettrack2Data()));
        }
        object1.add("securityProtocol",jsonSerializationContext.serialize(saleMiddleRequest.getsecurityProtocol()));
        object1.add("ucafCollectionIndicator",jsonSerializationContext.serialize(saleMiddleRequest.getucafCollectionIndicator()));
        object1.add("orderNumber",jsonSerializationContext.serialize(saleMiddleRequest.getorderNumber()));
        object1.add("tokenRequired",jsonSerializationContext.serialize(saleMiddleRequest.gettokenRequired()));
        object1.add("terminalCapability",jsonSerializationContext.serialize(saleMiddleRequest.getterminalCapability()));
        object1.add("terminalOperatingEnvironment",jsonSerializationContext.serialize(saleMiddleRequest.getterminalOperatingEnvironment()));
        object1.add("cardholderAuthenticationMethod",jsonSerializationContext.serialize(saleMiddleRequest.getcardholderAuthenticationMethod()));
        object1.add("terminalAuthenticationCapability",jsonSerializationContext.serialize(saleMiddleRequest.getterminalAuthenticationCapability()));
        object1.add("terminalOutputCapability",jsonSerializationContext.serialize(saleMiddleRequest.getterminalOutputCapability()));
        object1.add("maxPinLength",jsonSerializationContext.serialize(saleMiddleRequest.getmaxPinLength()));
        object.add("Sale",object1);

        return object;

    }
}
