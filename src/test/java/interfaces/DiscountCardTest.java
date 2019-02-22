package interfaces;

import entities.cards.BronzeCard;
import entities.cards.GoldCard;
import entities.cards.SilverCard;
import org.junit.Assert;
import org.junit.Test;

public class DiscountCardTest {

    private static final double ASSERT_EQUALS_DELTA = 0.001D;
    private static final double EXPECTED_DISCOUNT_ZERO_PERCENTS = 0D;
    private static final double EXPECTED_DISCOUNT_ONE_PERCENTS = 1D;
    private static final double EXPECTED_DISCOUNT_TWO_PERCENTS = 2D;
    private static final double EXPECTED_DISCOUNT_TWO_AND_HALF_PERCENTS = 2.5D;
    private static final double EXPECTED_DISCOUNT_THREE_PERCENTS = 3D;
    private static final double EXPECTED_DISCOUNT_THREE_AND_HALF_PERCENTS = 3.5D;
    private static final double EXPECTED_DISCOUNT_SIX_PERCENTS = 6D;
    private static final double EXPECTED_DISCOUNT_MAX_PERCENTS = 10D;

    private DiscountCard card;

    @Test
    public void bronzeCardDiscRateBelow100$Turnover() {
        this.card = new BronzeCard(0D);

        double expected = EXPECTED_DISCOUNT_ZERO_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void bronzeCardDiscRateBetween100and300$Turnover() {
        this.card = new BronzeCard(250D);

        double expected = EXPECTED_DISCOUNT_ONE_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void bronzeCardDiscRateAbove301$Turnover() {
        this.card = new BronzeCard(301D);

        double expected = EXPECTED_DISCOUNT_TWO_AND_HALF_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void silverCardDiscRateBelow250$Turnover() {
        this.card = new SilverCard(250D);

        double expected = EXPECTED_DISCOUNT_TWO_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void silverCardDiscRateEqual300$Turnover() {
        this.card = new SilverCard(300D);

        double expected = EXPECTED_DISCOUNT_TWO_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void silverCardDiscRateOver300$Turnover() {
        this.card = new SilverCard(301D);

        double expected = EXPECTED_DISCOUNT_THREE_AND_HALF_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void goldCardInitialDiscRateWith0$Turnover() {
        this.card = new GoldCard(0D);

        double expected = EXPECTED_DISCOUNT_TWO_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void goldCardDiscRateFor100$Turnover() {
        this.card = new GoldCard(100D);

        double expected = EXPECTED_DISCOUNT_THREE_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void goldCardDiscRateFor400$Turnover() {
        this.card = new GoldCard(400D);

        double expected = EXPECTED_DISCOUNT_SIX_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void goldCardDiscountRateMax() {
        this.card = new GoldCard(800D);

        double expected = EXPECTED_DISCOUNT_MAX_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }

    @Test
    public void goldCardMaxDiscountRate() {
        this.card = new GoldCard(999999D);

        double expected = EXPECTED_DISCOUNT_MAX_PERCENTS;
        double actual = this.card.getDiscountRate();

        Assert.assertEquals(expected, actual, ASSERT_EQUALS_DELTA);
    }
}