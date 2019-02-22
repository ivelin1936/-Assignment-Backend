import interfaces.OutputWriter;
import io.ConsoleOutputWriter;
import entities.cards.BronzeCard;
import entities.cards.GoldCard;
import entities.cards.SilverCard;
import interfaces.DiscountCard;
import interfaces.Cart;
import interfaces.Item;
import entities.items.ItemImpl;
import entities.carts.StoreCart;
import entities.payDesk.PayDesk;

public class MarketStore {
    public static void main(String[] args) {
        OutputWriter writer = new ConsoleOutputWriter();

        /** Shopping with Bronze Discount Card */
        Cart firstShopCart = new StoreCart();
        Item clockItem = new ItemImpl("Casio", 150D);
        firstShopCart.addItem(clockItem);

        DiscountCard bronzeCard = new BronzeCard(0D);

        String invoice = PayDesk.pay(firstShopCart, bronzeCard);
        writer.writeLine(invoice + System.lineSeparator());

        /** Shopping with Silver Discount Card */
        Cart secondShopCart = new StoreCart();
        Item computerItem = new ItemImpl("Pentium", 850D);
        secondShopCart.addItem(computerItem);

        DiscountCard silverCard = new SilverCard(600D);

        String secondInvoice = PayDesk.pay(secondShopCart, silverCard);
        writer.writeLine(secondInvoice + System.lineSeparator());

        /** Shopping with Gold Discount Card */
        Cart thirdShopCart = new StoreCart();
        Item tvItem = new ItemImpl("TV", 1300D);
        thirdShopCart.addItem(tvItem);

        DiscountCard goldCard = new GoldCard(1300D);

        String thirdInvoice = PayDesk.pay(thirdShopCart, goldCard);
        writer.writeLine(thirdInvoice + System.lineSeparator());

        /** Shopping more items with Gold Discount Card */
        Cart shopCart = new StoreCart();

        shopCart.addItem(new ItemImpl("Video", 350D));
        shopCart.addItem(new ItemImpl("Fridge", 700D));
        shopCart.addItem(new ItemImpl("Picture", 250D));

        DiscountCard goldenCard = new GoldCard(1300);

        String invoiceResult = PayDesk.pay(shopCart, goldenCard);
        writer.writeLine(invoiceResult);

    }
}
