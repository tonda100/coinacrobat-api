package net.osomahe.coinacrobat.api.predictions.boundary;

import net.osomahe.coinacrobat.api.exchange.entity.ExchangePair;
import net.osomahe.coinacrobat.api.predictions.entity.FutureDirection;


/**
 * TODO write JavaDoc
 *
 * @author Antonin Stoklasek
 */
public interface Predictions {

    FutureDirection getImagePipelinePrediction(ExchangePair exchangePair, String modelName);

    FutureDirection getImagePipelinePrediction(ExchangePair exchangePair);

}
