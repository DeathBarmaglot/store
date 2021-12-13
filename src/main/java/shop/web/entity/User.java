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
public class User {
    private String name;
    private String email;
    private String pwd;
    private String token;
    private LocalDateTime date;
}
