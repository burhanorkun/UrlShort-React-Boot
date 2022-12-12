package com.orkun.shorturl.strategies.qrcode;

import com.orkun.shorturl.enums.ActionEnum;
import com.orkun.shorturl.strategies.Shortener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

// not implemented
@Component
public class QrCodeStrategy implements Shortener {

    @Override
    public ActionEnum getAction() {
        return ActionEnum.QRCODE;
    }

    @Override
    public String longToShort(String input){
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "QRCODE not implemented yet");
    }

    @Override
    public String shortToLong(String input) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "QRCODE not implemented yet");
    }

}
