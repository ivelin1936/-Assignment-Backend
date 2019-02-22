package entities.cards;

public final class GoldCard extends BaseCard {

    private static final double INITIAL_DISCOUNT = 2D;
    private static final double MAX_DISCOUNT = 10D;
    private static final double DISCOUNT_RATE_INCREMENT = 1D;
    private static final double ONE_HUNDRED_DOLLARS = 100D;

    public GoldCard(double turnover) {
        super(turnover);
    }

    @Override
    public double getDiscountRate() {
        double discount = INITIAL_DISCOUNT;
        discount += (super.getPreviousMonthTurnover() / ONE_HUNDRED_DOLLARS) * DISCOUNT_RATE_INCREMENT;

        return discount > MAX_DISCOUNT ? MAX_DISCOUNT
                : discount;
    }
}
