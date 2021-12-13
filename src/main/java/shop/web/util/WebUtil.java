package shop.web.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import shop.web.entity.Food;

@Builder
public class WebUtil {
    public static Food getFood(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }
}
