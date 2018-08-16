package net.osomahe.coinacrobat.api.strategy.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


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
