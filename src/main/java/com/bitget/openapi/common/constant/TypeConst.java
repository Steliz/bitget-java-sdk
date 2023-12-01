package com.bitget.openapi.common.constant;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class TypeConst {

    public static final Type DEFUALT_PARAMS_TYPE = new TypeToken<Map<String, String>>() {}.getType();
}
