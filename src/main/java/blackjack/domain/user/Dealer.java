package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Dealer extends User {
    public static final int TURN_OVER_COUNT = 16;
    private static final Name name = new Name("딜러");
    protected final List<Card> cards = new ArrayList<>();

    @Override
    public String getName() {
        return name.getName();
    }

    @Override
    protected List<Card> getCards() {
        return cards;
    }

    @Override
    public boolean isGameOver(int gameOverScore) {
        return calculateScore(gameOverScore) > TURN_OVER_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(cards, dealer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}