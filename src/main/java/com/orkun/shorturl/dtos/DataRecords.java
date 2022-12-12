package com.orkun.shorturl.dtos;

import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.models.ShortUrl;
import lombok.Data;

import java.util.List;

@Data
public class DataRecords {
    private List<DataRecord> allUrlRecords;
}
