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
    private long id;
    private String name;
    private String comment;
    private int price;
    private String email;
    private LocalDateTime date;
}
