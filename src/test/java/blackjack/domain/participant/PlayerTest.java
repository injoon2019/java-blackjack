package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Number;
import blackjack.domain.result.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 생성자 테스트")
    @Test
    void create() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE))));

        assertThat(player).isNotNull();
    }

    @DisplayName("Ace를 1로 판단했을 시 베스트 점수 계산 테스트")
    @Test
    void calculateBestScore_ConsideringAceIsOne_IsBest() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.KING, Kind.SPADE))));

        assertThat(player.calculateBestScore()).isEqualTo(21);
    }

    @DisplayName("Ace를 11로 판단했을 시 베스트 점수 계산 테스트")
    @Test
    void calculateBestScore_ConsideringAceIsEleven_IsBest() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.FIVE, Kind.SPADE),
                Card.from(Number.SEVEN, Kind.SPADE),
                Card.from(Number.EIGHT, Kind.SPADE))));

        assertThat(player.calculateBestScore()).isEqualTo(21);
    }

    @DisplayName("플레이어의 점수가 더 높은 경우 승리 테스트")
    @Test
    void isWinner_Player20_isWin() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));

        assertThat(player.judgeResult(19)).isEqualTo(Result.WIN);
    }

    @DisplayName("딜러의 점수가 더 높은 경우 패배 테스트")
    @Test
    void isWinner_Player20_isLose() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));

        assertThat(player.judgeResult(21)).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어만 버스트된 경우 패배 테스트")
    @Test
    void isWinner_PlayerBusted_isLose() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.SPADE),
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART))));

        assertThat(player.judgeResult(19)).isEqualTo(Result.LOSE);
    }

    @DisplayName("딜러만 버스트된 경우 승리 테스트")
    @Test
    void isWinner_DealerBusted_isWin() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.NINE, Kind.SPADE))));

        assertThat(player.judgeResult(22)).isEqualTo(Result.WIN);
    }

    @DisplayName("둘 다 버스트된 경우 딜러 승 테스트")
    @Test
    void isWinner_BothBusted_isLose() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.NINE, Kind.SPADE),
                Card.from(Number.NINE, Kind.CLOVER),
                Card.from(Number.NINE, Kind.HEART))));

        assertThat(player.judgeResult(22)).isEqualTo(Result.LOSE);
    }

    @DisplayName("Ace만 있을 시 베스트 점수 계산 테스트")
    @Test
    void calculateBestScore_FourAces_IsBest() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.ACE, Kind.DIAMOND),
                Card.from(Number.ACE, Kind.CLOVER),
                Card.from(Number.ACE, Kind.HEART))));

        assertThat(player.calculateBestScore()).isEqualTo(14);
    }

    @DisplayName("플레이어 카드 추가 수령 가능 여부 테스트")
    @Test
    void isReceivable_BestScore21_IsTrue() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.ACE, Kind.SPADE),
                Card.from(Number.KING, Kind.SPADE))));

        assertThat(player.isReceivable()).isTrue();
    }

    @DisplayName("플레이어 카드 추가 수령 실패 여부 테스트")
    @Test
    void isReceivable_BestScore22_IsFalse() {
        Player player = Player.of("Pobi");
        player.receive(new Cards(List.of(
                Card.from(Number.TEN, Kind.SPADE),
                Card.from(Number.TWO, Kind.SPADE),
                Card.from(Number.TEN, Kind.HEART))));

        assertThat(player.isReceivable()).isFalse();
    }
}
