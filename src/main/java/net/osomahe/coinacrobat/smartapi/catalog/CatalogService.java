package net.osomahe.coinacrobat.smartapi.catalog;

import java.util.List;

public interface CatalogService {

    <T extends Catalogable> List<T> getCatalogValues(Class<T> tClass);
}
