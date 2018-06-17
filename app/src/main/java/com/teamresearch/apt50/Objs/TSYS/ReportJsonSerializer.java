package com.teamresearch.apt50.Objs.TSYS;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by Me on 4/21/2018.
 */

public class ReportJsonSerializer implements JsonSerializer<ReportMiddleRequest> {


    @Override
    public JsonElement serialize(ReportMiddleRequest reportMiddleRequest, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        JsonObject object1 = new JsonObject();
        object1.add("deviceID", jsonSerializationContext.serialize(reportMiddleRequest.getdeviceID()));
        object1.add("transactionKey", jsonSerializationContext.serialize(reportMiddleRequest.gettransactionKey()));
        object1.add("reportName", jsonSerializationContext.serialize(reportMiddleRequest.getreportName()));
        object.add("GetReportData", object1);

        return object;
    }
}
