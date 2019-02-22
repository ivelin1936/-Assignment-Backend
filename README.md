# Assignment Backend Guide

This project is a small object-oriented model of market store system - console application. A market store offers to their clients three different types of discount cards: bronze, silver and gold. Each card stores information about it's owner, the turnover for the previous month and the initial discount rate. With each card you can calculate the discount of the current purchase.

## Getting Started
### Application is supposed to be run using an IDE

* Clone repository: using client for the Git version control system. [TortoiseGit](https://tortoisegit.org/)
* Import project
* Give full path to the project directory
* Import project from external model 
   - Maven
* Use [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

#### Repository URL
```
https://github.com/ivelin1936/Assignment-Backend.git
```

Check below, for the technologies and frameworks used versions.

## Describe Functionality

The functionality of the software involves shopping in store system. The main logic cicling around the discount cards. As you see the cards are the main entities of the program. Every card has and initial discount rate, and keep information for it's owner's turnover for the previous month. On base of it's previous month turnover, the card discount rate change. With this information, pay-desk can calculate the discount of the current purchase and the total purchase value.
How it's work:
MarketStore class have main() method, with which we can run the console application. 
For every shopping tour, need to create a cart instance, in which we can collect all our wished stuffs for buying. 
Every stuff is an instance of the item's class. Item's class is just a date class, which keep the information about the items like a price and name.
To finish the purchase we need to have and discount card, for the demo, discount card accept from it's constructor a turnover value for the previous month.
In the end with pay desk we can finalize our purchase and get in back an invoice of the purchase when invoke pay() method. 
PayDesk has a private constructor (we can't instance it), because it work with a static method pay(), can be called without creating an object of class because it's associated to the class in which is reside not to the objects of that class.

### Demo
#### Shopping items with Gold Discount Card with Mock data: turnover $1500, purchase value $1300;
* Getting our card and start the shopping, so need to create an instace of card with turnover = 1300$
```
DiscountCard goldenCard = new GoldCard(1300);
```
   Input turnover will be validate, and if is incorrect will throw and exeption
   ```
   private void setTurnover(double turnover) {
       if (turnover < 0) {
           throw new IllegalArgumentException(INCORRECT_TURNOVER_VALUE_EX_MSG);
       }
       this.turnover = turnover;
   }
   ```
* To can start shopping will need to create an instace of cart too
```
Cart shopCart = new StoreCart();
```
* Let's choice what a stuffs to buying. For the demo createing instances with sample data, will buy three stuffs for 350$, 700$ and 250$
```
Item video = new ItemImpl("Video", 350D);
Item fridge = new ItemImpl("Fridge", 700D);
Item picture = new ItemImpl("Picture", 250D);
```
Items validate the passed name and price, before to assigne them to his properties.
* We need to adding every choosen stuff to our shopping cart
```
shopCart.addItem(video);
shopCart.addItem(fridge);
shopCart.addItem(picture);
```
* Now we are done with the shopping and need goint to the paydesk to finalize our purchase
```
String invoiceResult = PayDesk.pay(shopCart, goldenCard);
```
Getting card discount rate from our card. The card will calculate it on base on the turnover from the previous month.
```
INITIAL_DISCOUNT = 2D; ONE_HUNDRED_DOLLARS = 100D; DISCOUNT_RATE_INCREMENT = 1D; MAX_DISCOUNT = 10D;

@Override
public double getDiscountRate() {
  double discount = INITIAL_DISCOUNT; 
  discount += (super.getPreviousMonthTurnover() / ONE_HUNDRED_DOLLARS) * DISCOUNT_RATE_INCREMENT;
  
  return discount > MAX_DISCOUNT ? MAX_DISCOUNT : discount;
}
```
Getting purchase total value from our cart. The cart will calculate it and will return the totalAmount
```
@Override
public double getPurchaseValue() {
    return this.items.stream()
            .mapToDouble(Item::getPrice)
            .sum();
}
```
On base on the cardDiscountRate and totalPurchaseValue, will be calculate purchaseDiscount and totalAmount
```
private static double calculatePurchaseDiscount(double purchaseValue, double cardDiscountRate) {
    return purchaseValue * cardDiscountRate / ONE_HUNDRED;
}

private static double calculateTotalAmount(double purchaseValue, double discount) {
    return purchaseValue - discount;
}
```
Now the paydesk can build our invoice and to return it. So let's print on the console our invoice and to see result.
```
writer.writeLine(invoiceResult);
```
RESULT:
```
Purchase value: $1300,00
Discount rate: 10,0%
Discount: $130,00
Total: $1170,00
```

## Structure
#### The structure of the software is concentrate over the shopping with discount cards

### [BaseCards](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/cards/BaseCard.java)
The cards are initialized with:
   * `- turnover: double`
   * `- setTurnover(double turnover)`
   * `+ double getPreviousMonthTurnover()`

There are generally 3 types of Cards.
#### [Bronze](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/cards/BronzeCard.java)
   * `+ getDiscountRate()`
* ```The bronze card comes with no discount rate if the turnover for the previous month is below $100. If it is between $100 and $300, the discount rate is 1%. And if it is above $300, the rate is 2.5%.```

#### [Silver](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/cards/SilverCard.java)
* `+ getDiscountRate()`
* ```The silver card comes with an initial discount rate of 2%. In case, the turnover is over $300, the rate is 3.5%.```

#### [Gold](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/cards/GoldCard.java)
* `+ getDiscountRate()`
* ```The gold card comes with an initial discount rate of 2%. The discount rate grows 1% for each $100 from the turnover, capping at 10%.```

### [Item](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/items/ItemImpl.java)
The items are initialized with:
   * `- name: String`
   * `- price: floating-point number`
   * `- setName(String name)`
   * `- setPrice(double price)`
   * `+ getName()`
   * `+ getPrice`
#### Constrains / Validation
   * Name cannot be empty or null - will throw ```InvalidNameException```
   * Price cannot be negative - will throw ```InvalidPriceException``` 
   
### [StoreCart](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/carts/StoreCart.java)
The cart is initialized with:
   * `- items: List<Item>`
   * `- setItems(List<Item> items)`
   * `+ addItem(Item item)`
   * `+ getPurchaseValue()`
#### Constrains / Validation
   * items cannot be null or empty - will throw ```InvalidItemsCollectionException```
   
### [PayDesk](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/entities/payDesk/PayDesk.java)
The payDesk is initialized with static method(s)
   * `+ pay(Cart shoppingCart, DiscountCard card)`
   * `- calculatePurchaseDiscount(double purchaseValue, double cardDiscountRate)`
   * `- calculateTotalAmount(double purchaseValue, double discount)`
   
### [ConsoleOutputWriter](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/main/java/io/ConsoleOutputWriter.java)
The ConsoleOutputWriter is initialized with:
   * `+ writeLine(String output)`
   * `+ writeLine(String format, Object... params)`
   
### Interfaces
#### [DiscountCard](https://github.com/ivelin1936/Intership-Solutions/blob/master/src/main/java/interfaces/DiscountCard.java)
    + double getPreviousMonthTurnover()
    + double getDiscountRate()
#### [Item](https://github.com/ivelin1936/Intership-Solutions/blob/master/src/main/java/interfaces/Item.java)
    + String getName()
    + double getPrice()
#### [Cart](https://github.com/ivelin1936/Intership-Solutions/blob/master/src/main/java/interfaces/Cart.java)
    + double getPurchaseValue()
    + void addItem(Item item)
#### [OutputWriter](https://github.com/ivelin1936/Intership-Solutions/blob/master/src/main/java/interfaces/OutputWriter.java)
    + void writeLine(String output)
    + void writeLine(String format, Object... params)

## Unit Tests

For reduces defects in the newly developed features or reduces bugs when changing the existing functionality, and testing on the existing alredy functionality i am used Unit testing. Unit tests detect changes that may break a design contract. They helps with maintaining and changing the code. With this Unit testing verifies the accuracy of the each unit.

Used Java Reflection, JUnit and Mokito. Check below, for the frameworks used versions.

### Tests
#### [CartTest](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/test/java/interfaces/CartTest.java)
#### [ItemTest](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/test/java/interfaces/ItemTest.java)
#### [DiscountCardTest](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/test/java/interfaces/DiscountCardTest.java)
#### [PayDeskTest](https://github.com/ivelin1936/Assignment-Backend/blob/master/src/test/java/entities/payDesk/PayDeskTest.java)

## Technologies

* Java - [JDK11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Frameworks

* Mockito - [org.mockito](https://site.mockito.org/), version 1.10.19 - [dependency](https://mvnrepository.com/artifact/org.mockito/mockito-all/1.10.19)

* Junit - [ReleaseNotes4.12.md](https://github.com/junit-team/junit4/blob/master/doc/ReleaseNotes4.12.md), version 4.12 - [dependency](https://mvnrepository.com/artifact/junit/junit/4.12)

## IDE 

* IntelliJ Idea Ultimate - [JetBrains](https://www.jetbrains.com/idea/)

## Version

1.0-SNAPSHOT

## Author

**Ivelin DImitrov** - [ivelin1936](https://github.com/ivelin1936)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
