package dev.soterocra.model;

public enum RegionEnum {

    BRAZIL(16224, "X-Magento-Vary=fe5f6dd68ab091a674561609c6f34ee899a35aaf;store=accor_samerica_pt_br;delivery=BR;cash_currency=BRL;"),
    FRANCE_AND_REGION(null, "X-Magento-Vary=7029b7a11a0390190f2fceef82aeb2482cc2de2f;store=accor_en_gb;delivery=FR;cash_currency=EUR;");

    private final Integer placeForRegion;
    private final String cookieForRegion;
    RegionEnum(Integer placeForRegion, String cookieForRegion) {
        this.placeForRegion = placeForRegion;
        this.cookieForRegion = cookieForRegion;
    }

    public Integer getPlaceForRegion() {
        return placeForRegion;
    }

    public String getCookieForRegion() {
        return cookieForRegion;
    }
}
