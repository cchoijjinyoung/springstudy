package hello.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// lombok 은 getter / setter 를 자동으로 생성.
// 실무에서 많이 씀.
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");

        String name = helloLombok.getName();

    }
}
