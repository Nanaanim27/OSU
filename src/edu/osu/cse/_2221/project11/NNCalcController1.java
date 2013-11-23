package edu.osu.cse._2221.project11;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 * 
 * @author Put your name here
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     * 
     * @ensures <pre>
     * {@code [this.view has been updated to be consistent with this.model]}
     * </pre>
     */
    private void updateViewToMatchModel() {

        // TODO: fill in body

    }

    /**
     * Constructor.
     * 
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        this.updateViewToMatchModel();
    }

    @Override
    public void processEnterEvent() {

        // TODO: fill in body

    }

    @Override
    public void processAddEvent() {

        // TODO: fill in body

    }

    @Override
    public void processSubtractEvent() {

        // TODO: fill in body

    }

    @Override
    public void processMultiplyEvent() {

        // TODO: fill in body

    }

    @Override
    public void processDivideEvent() {

        // TODO: fill in body

    }

    @Override
    public void processPowerEvent() {

        // TODO: fill in body

    }

    @Override
    public void processRootEvent() {

        // TODO: fill in body

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        // TODO: fill in body

    }

}
