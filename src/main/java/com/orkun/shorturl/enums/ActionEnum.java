package com.orkun.shorturl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionEnum {
    URL("Shortener URL Service", 1),
    QRCODE("Shortener QR Service", 2),
    BARCODE("Shortener BarCode Service", 3);

    private final String name;
    private final int order;

}
