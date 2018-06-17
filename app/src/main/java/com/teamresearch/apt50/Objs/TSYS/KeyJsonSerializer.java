package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.teamresearch.apt50.Objs.KeyRequest;

import java.lang.reflect.Type;

/**
 * Created by Me on 3/20/2018.
 */

public class KeyJsonSerializer implements JsonSerializer<KeyRequest>
{
    @Override
    public JsonElement serialize(KeyRequest keyRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add("mid", jsonSerializationContext.serialize(keyRequest.getmid()));
        object1.add("userID", jsonSerializationContext.serialize(keyRequest.getuserID()));
        object1.add("password", jsonSerializationContext.serialize(keyRequest.getpassword()));
        object.add("GenerateKey", object1);
        return object;
    }
}

