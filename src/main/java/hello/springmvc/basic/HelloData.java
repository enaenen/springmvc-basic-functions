package hello.springmvc.basic;

import lombok.Data;

@Data //Getter, Setter, toString, EqualsAndHashCode, Required Arg Constructor 자동적용
public class HelloData {
    private String username;
    private int age;

}
