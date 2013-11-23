package edu.osu.cse._2221.project11;

import components.naturalnumber.NaturalNumber;

/**
 * Model interface.
 * 
 * The Natural Number Calculator model consists of the top and bottom operands
 * (which have only getter methods).
 * 
 * @author Bruce W. Weide
 * 
 * @mathmodel <pre>
 * {@code type NNCalcModel is modeled by
 *   (top: NATURAL_NUMBER,
 *    bottom: NATURAL_NUMBER)}
 * </pre>
 * @initially <pre>
 * {@code default:
 *  ensures
 *   this = (0, 0)}
 * </pre>
 */
public interface NNCalcModel {

    /**
     * Reports top operand.
     * 
     * @return this.top
     * @aliases reference returned by {@code top}
     * @ensures <pre>
     * {@code top = this.top}
     * </pre>
     */
    NaturalNumber top();

    /**
     * Reports bottom operand.
     * 
     * @return this.bottom
     * @aliases reference returned by {@code bottom}
     * @ensures <pre>
     * {@code bottom = this.bottom}
     * </pre>
     */
    NaturalNumber bottom();

}
