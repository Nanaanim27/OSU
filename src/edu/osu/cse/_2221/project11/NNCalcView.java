package edu.osu.cse._2221.project11;

import java.awt.event.ActionListener;

import components.naturalnumber.NaturalNumber;

/**
 * View interface.
 * 
 * @author Bruce W. Weide
 */
public interface NNCalcView extends ActionListener {

    /**
     * Register argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     * 
     * @param controller
     *            controller to register
     */
    void registerObserver(NNCalcController controller);

    /**
     * Updates top operand display based on NaturalNumber provided as argument.
     * 
     * @param n
     *            new value of top operand display
     */
    void updateTopDisplay(NaturalNumber n);

    /**
     * Updates bottom operand display based on NaturalNumber provided as
     * argument.
     * 
     * @param n
     *            new value of bottom operand display
     */
    void updateBottomDisplay(NaturalNumber n);

    /**
     * Updates display of whether subtract operation is allowed.
     * 
     * @param allowed
     *            true iff subtract is allowed
     */
    void updateSubtractAllowed(boolean allowed);

    /**
     * Updates display of whether divide operation is allowed.
     * 
     * @param allowed
     *            true iff divide is allowed
     */
    void updateDivideAllowed(boolean allowed);

    /**
     * Updates display of whether power operation is allowed.
     * 
     * @param allowed
     *            true iff power is allowed
     */
    void updatePowerAllowed(boolean allowed);

    /**
     * Updates display of whether root operation is allowed.
     * 
     * @param allowed
     *            true iff root is allowed
     */
    void updateRootAllowed(boolean allowed);

}
