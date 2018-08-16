package net.osomahe.coinacrobat.api.strategy.entity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation to add description to strategy parameters.
 *
 * @author Antonin Stoklasek
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface LocalizedText {

    Language language();

    String text();
}
