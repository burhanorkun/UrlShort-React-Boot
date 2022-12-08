package com.orkun.shorturl.strategies;

public interface ShortenerConverter {

    String longToShort(String input);

    String shortToLong(String input);
}
