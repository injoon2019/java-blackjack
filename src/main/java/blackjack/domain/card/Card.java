package blackjack.domain.card;

import java.util.Objects;

public class Card {

    public static final int BEST_SCORE = 21;
    private static final int ACE_ADDITIONAL_NUMBER = 10;

    private final Number number;
    private final Kind kind;

    private Card(Number number, Kind kind) {
        this.number = number;
        this.kind = kind;
    }

    public static Card from(Number number, Kind kind) {
        return new Card(number, kind);
    }

    public boolean isAce() {
        return number == Number.ACE;
    }

    public int getBest(int sum) {
        if (isAce() && sum + ACE_ADDITIONAL_NUMBER <= BEST_SCORE) {
            sum += ACE_ADDITIONAL_NUMBER;
        }
        return sum;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number.getName() + kind.getSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return number == card.number && kind == card.kind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, kind);
    }
}
