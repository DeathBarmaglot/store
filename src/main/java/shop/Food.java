package shop;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Food {
    private String name;
    private LocalDateTime date;
    private int price;
    private int id;

}
