package net.osomahe.coinacrobat.smartapi;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface LocalizedText {

    Language language();

    String text();
}
