package edu.osu.cse._2221.project11;

/**
 * Controller interface.
 * 
 * @author Bruce W. Weide
 * 
 * @mathmodel <pre>
 * {@code type NNCalcController is modeled by
 *   (model: NNCalcModel,
 *    view: NNCalcView)}
 * </pre>
 * @initially <pre>
 * {@code (NNCalcModel model, NNCalcView view):
 *   ensures
 *     this.model = model  and
 *     this.view = view}
 * </pre>
 */
public interface NNCalcController {

    /**
     * Processes event to clear bottom operand.
     * 
     * @updates {@code this.model.bottom, this.view}
     * @ensures <pre>
     * {@code this.model.bottom = 0  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processClearEvent();

    /**
     * Processes event to swap operands.
     * 
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.top = #this.model.bottom  and
     * this.model.bottom = #this.model.top  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processSwapEvent();

    /**
     * Processes event to enter bottom operand to top.
     * 
     * @updates {@code this.model.top, this.view}
     * @ensures <pre>
     * {@code this.model.top = this.model.bottom  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processEnterEvent();

    /**
     * Processes event to do an add operation.
     * 
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.model.top = 0  and
     * this.model.bottom = #this.model.top + #this.model.bottom  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processAddEvent();

    /**
     * Processes event to do a subtract operation.
     * 
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code this.model.bottom <= this.model.top}
     * </pre>
     * @ensures <pre>
     * {@code this.model.top = 0  and
     * this.model.bottom = #this.model.top - #this.model.bottom  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processSubtractEvent();

    /**
     * Processes event to do a multiply operation.
     * 
     * @updates {@code this.model, this.view}
     * @ensures <pre>
     * {@code this.mode.top = 0  and
     * this.model.bottom = #this.model.top * #this.model.bottom  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processMultiplyEvent();

    /**
     * Processes event to do a divide operation.
     * 
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code this.model.bottom > 0}
     * </pre>
     * @ensures <pre>
     * {@code #this.model.top =
     *   this.model.bottom * #this.model.bottom + this.model.top  and
     * 0 <= this.model.top < #this.model.bottom  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processDivideEvent();

    /**
     * Processes event to do a power operation.
     * 
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code this.model.bottom <= INT_LIMIT}
     * </pre>
     * @ensures <pre>
     * {@code this.model.top = 0  and
     * this.model.bottom = #this.model.top ^ (#this.model.bottom)  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processPowerEvent();

    /**
     * Processes event to do a root operation.
     * 
     * @updates {@code this.model, this.view}
     * @requires <pre>
     * {@code 2 <= this.model.bottom <= INT_LIMIT}
     * </pre>
     * @ensures <pre>
     * {@code this.model.top = 0  and
     * this.model.bottom =
     *   [the floor of the #this.model.bottom root of #this.model.top]  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processRootEvent();

    /**
     * Processes event to add a new (low-order) digit to the bottom operand.
     * 
     * @param digit
     *            the low-order digit to be added
     * 
     * @updates {@code this.model.bottom, this.view}
     * @requires <pre>
     * {@code 0 <= digit < 10}
     * </pre>
     * @ensures <pre>
     * {@code this.model.bottom = #this.model.bottom * 10 + digit  and
     * [this.view has been updated to match this.model]}
     * </pre>
     */
    void processAddNewDigitEvent(int digit);

}
