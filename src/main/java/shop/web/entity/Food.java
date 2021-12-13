package shop.web.entity;

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
    private int price;
    private int id;
    private LocalDateTime date;
}
