package dev.soterocra.model;

public enum RegionEnum {

    BRAZIL("X-Magento-Vary=fe5f6dd68ab091a674561609c6f34ee899a35aaf;store=accor_samerica_pt_br;delivery=BR;cash_currency=BRL;");

    private final String cookieForRegion;
    RegionEnum(String cookieForRegion) {
        this.cookieForRegion = cookieForRegion;
    }

    public String getCookieForRegion() {
        return cookieForRegion;
    }
}
