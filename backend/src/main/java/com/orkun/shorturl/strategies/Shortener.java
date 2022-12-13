package com.orkun.shorturl.strategies;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.models.DataRecord;

import java.util.List;

public interface Shortener {

    String longToShort(String input);

    String shortToLong(String input);

    ActionEnum getAction();

    List<DataRecord> getAllRecords();

    void deleteRecord(Long id);
}
