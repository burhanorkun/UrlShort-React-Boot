package com.orkun.shorturl.strategies;

import com.orkun.shorturl.enums.ActionEnum;

public interface Shortener {

    String longToShort(String input) throws Exception;

    String shortToLong(String input);

    ActionEnum getAction();
}
