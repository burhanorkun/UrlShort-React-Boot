package com.orkun.shorturl.strategies.barcode;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.strategies.Shortener;
import org.springframework.stereotype.Component;

@Component
public class BarCodeStrategy implements Shortener {


    @Override
    public ActionEnum getAction() {
        return ActionEnum.BARCODE;
    }
    @Override
    public String longToShort(String input) {
        // not implemented
        // java.lang.UnsupportedOperationException("Not supported yet.");
        return "not implemented";
    }

    @Override
    public String shortToLong(String input) {
        // not implemented
        // java.lang.UnsupportedOperationException("Not supported yet.");
        return "not implemented";
    }

}
