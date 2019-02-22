package entities.cards;

import exceptions.InvalidTurnoverException;
import interfaces.DiscountCard;

public abstract class BaseCard implements DiscountCard {

    private static final String INCORRECT_TURNOVER_VALUE_EX_MSG = "Turnover value cannot be negative.";
    private static final double MIN_TURNOVER_VALUE = 0D;

    private double previousMonthTurnover;

    protected BaseCard(double turnover) {
        this.setTurnover(turnover);
    }

    private void setTurnover(double turnover) {
        if (turnover < MIN_TURNOVER_VALUE) {
            throw new InvalidTurnoverException(INCORRECT_TURNOVER_VALUE_EX_MSG);
        }
        this.previousMonthTurnover = turnover;
    }

    @Override
    public double getPreviousMonthTurnover() {
        return this.previousMonthTurnover;
    }
}
