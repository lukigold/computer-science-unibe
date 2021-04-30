package covidgame1;

import java.awt.Color;

/**
 * Represents Field in Environment.
 * Has certain color depending on Area type.
 * 
 * @author Kevin Stöckli
 * @author Lukas Ingold
 *
 */
public enum Area {
    /** Represents a covid area :-) */
    COVID,
    /** Represents a safe area. */
    SAFE,
    /** Represents the trail left by a player */
    TRAIL,
    /** Represents the current position of player */
    CURRENT;
	

    public Color toColor() {
        switch (this) {
            case COVID: return Color.RED;
            case SAFE: return Color.DARK_GRAY;
            case TRAIL: return Color.LIGHT_GRAY;
            case CURRENT: return Color.GREEN;
            default: throw new IllegalStateException();
        }
    }
}
