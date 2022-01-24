package hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        this.price = price; //여기서 문제발생!
        return price; // 위 price필드를 없애는 대신 매소드에 return price로직을 만든다.
    }

    public int getPrice() {
//        return price;
    }
}
