package net.osomahe.coinacrobat.smartapi;

import java.lang.annotation.*;

/**
 * Annotation for describing strategy parameter in multiple languages.
 *
 * @author Antonin Stoklasek
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface StrategyParam {

    LocalizedText[] name();

    LocalizedText[] description();

    int index();
}