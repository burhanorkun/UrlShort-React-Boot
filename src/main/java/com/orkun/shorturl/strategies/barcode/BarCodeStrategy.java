package com.orkun.shorturl.strategies.barcode;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.models.DataRecord;
import com.orkun.shorturl.strategies.Shortener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class BarCodeStrategy implements Shortener {


    @Override
    public ActionEnum getAction() {
        return ActionEnum.BARCODE;
    }

    @Override
    public String longToShort(String input) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "BARCODE not implemented yet");
    }

    @Override
    public String shortToLong(String input) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "BARCODE not implemented yet");
    }

    @Override
    public List<DataRecord> getAllRecords() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "BARCODE not implemented yet");
    }

    @Override
    public void deleteRecord(Long id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "BARCODE not implemented yet");
    }

}
