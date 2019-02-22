package entities.cards;

public final class SilverCard extends BaseCard {

    private static final double INITIAL_DISCOUNT = 2D;
    private static final double THREE_AND_HALF_PERCENT = 3.5D;
    private static final double THREE_HUNDRED_DOLLARS = 300D;

    public SilverCard(double turnover) {
        super(turnover);
    }

    @Override
    public double getDiscountRate() {
        return super.getPreviousMonthTurnover() <= THREE_HUNDRED_DOLLARS ? INITIAL_DISCOUNT
                : THREE_AND_HALF_PERCENT;
    }
}
