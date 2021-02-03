package com.jt.accumulation.enumj;

import java.math.BigDecimal;
/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/3 14:06
 */
public class EnumTest {
    public static void main(String[] args) {
        User user = new User(1,"jiatai",MemberEnum.GOLD_MEMBER);
        User cs = new User(2,"测试",MemberEnum.IRON_MEMBER);
        BigDecimal discountPrice = MemberEnum.getMemberEnumByType(user.getMemberEnum().getMemberType()).caculateDiscountPrice(BigDecimal.valueOf(1000));
        BigDecimal discountPriceCs = MemberEnum.getMemberEnumByType(cs.getMemberEnum().getMemberType()).caculateDiscountPrice(BigDecimal.valueOf(1000));
        System.out.println(discountPrice);
        System.out.println(discountPriceCs);
    }
}
