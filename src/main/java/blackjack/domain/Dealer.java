package blackjack.domain;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_REDRAW_STANDARD = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isDealerDrawScore() {
        return this.getScore() < DEALER_REDRAW_STANDARD &&
                !this.isBust();
    }
}