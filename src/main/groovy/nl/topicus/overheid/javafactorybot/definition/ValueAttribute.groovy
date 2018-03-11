package nl.topicus.overheid.javafactorybot.definition

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType
import nl.topicus.overheid.javafactorybot.Evaluator

/**
 * Attribute which has a single value. The value is either the user specified override, or the result of the given
 * value generator closure.
 */
class ValueAttribute implements Attribute {
    private Closure defaultValueGenerator

    /**
     * Create a new ValueAttribute which yields the result of the given closure when no override is specified, or the
     * override otherwise.
     * @param defaultValueGenerator The closure of which the result is yielded when no override is speicified.
     */
    ValueAttribute(
            @DelegatesTo(Evaluator) @ClosureParams(value = SimpleType, options = "Evaluator") Closure defaultValueGenerator) {
        this.defaultValueGenerator = defaultValueGenerator
    }

    @Override
    def evaluate(Evaluator evaluator) {
        defaultValueGenerator.delegate = evaluator
        defaultValueGenerator(evaluator)
    }

    @Override
    def evaluate(Object override, Evaluator evaluator) {
        override
    }
}
