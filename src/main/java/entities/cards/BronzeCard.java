package entities.cards;

public final class BronzeCard extends BaseCard {

    private static final double INITIAL_DISCOUNT = 0D;
    private static final double ONE_PERCENT = 1D;
    private static final double TWO_AND_HALF_PERCENT = 2.5D;
    private static final double ONE_HUNDRED_DOLLARS = 100D;
    private static final double THREE_HUNDRED_DOLLARS = 300D;

    public BronzeCard(double turnover) {
        super(turnover);
    }

    @Override
    public double getDiscountRate() {
        return super.getPreviousMonthTurnover() < ONE_HUNDRED_DOLLARS ? INITIAL_DISCOUNT
                : super.getPreviousMonthTurnover() <= THREE_HUNDRED_DOLLARS ? ONE_PERCENT
                : TWO_AND_HALF_PERCENT;
    }
}
