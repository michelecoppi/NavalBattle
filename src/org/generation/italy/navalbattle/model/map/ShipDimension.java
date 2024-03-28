package org.generation.italy.navalbattle.model.map;

public enum ShipDimension {
    SUBMARINE(1), CRUISER(2), DESTROYER(3), AIRCRAFT_CARRIER(4);

    private int dimension;

    ShipDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension;
    }
}