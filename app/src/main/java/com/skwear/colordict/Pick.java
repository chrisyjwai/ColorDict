package com.skwear.colordict;

/**
 * Created by skweartop on 11/19/2016.
 */
public class Pick {
    private static Pick ourInstance = new Pick();

    public static Pick getInstance() {
        return ourInstance;
    }

    private Pick() {
    }

    private Slice slicePicked;

    public void setSlicePicked(Slice s) {
        slicePicked = s;
    }

    public Slice getSlicePicked() {
        return slicePicked;
    }
}
