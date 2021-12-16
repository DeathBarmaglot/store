package shop.web.utils;

import lombok.Builder;
import shop.web.entity.Food;

import javax.servlet.http.HttpServletRequest;

@Builder
public class WebUtil {
    public static Food getFood(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }
}
