package pr.iceworld.fernando.spring6.junit5.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Fruit {

    private Long id;
    private String name;
}
