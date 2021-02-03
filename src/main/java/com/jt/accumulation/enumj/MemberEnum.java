package com.jt.accumulation.enumj;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/2/3 13:43
 */
@Getter
public enum MemberEnum {
    GOLD_MEMBER(1,"黄金会员"){
            @Override
            public BigDecimal caculateDiscountPrice(BigDecimal originPrice) {
                return originPrice.multiply(BigDecimal.valueOf(0.6));
            }
    },SILVER_MEMBER(2,"白银会员"){
        @Override
        public BigDecimal caculateDiscountPrice(BigDecimal originPrice) {
            return originPrice.multiply(BigDecimal.valueOf(0.7));
        }
    },BRONZE_MEMBER(3,"青铜会员"){
        @Override
        public BigDecimal caculateDiscountPrice(BigDecimal originPrice) {
            return originPrice.multiply(BigDecimal.valueOf(0.8));
        }
    },IRON_MEMBER(4,"黑铁会员"){
        @Override
        public BigDecimal caculateDiscountPrice(BigDecimal originPrice) {
            return originPrice.multiply(BigDecimal.valueOf(0.9));
        }
    };
    MemberEnum(int memberType,String desc){
        this.desc = desc;
        this.memberType = memberType;
    }
    int memberType;
    String desc;
    private static Map<Integer,MemberEnum> memberEnumMap;
    static {
        memberEnumMap= Arrays.asList(MemberEnum.values()).stream().collect(Collectors.toMap(MemberEnum::getMemberType, v -> v, (v2, v1) -> v1));
    }

    public abstract BigDecimal caculateDiscountPrice(BigDecimal originPrice);

    public static MemberEnum getMemberEnumByType(int memberType){
        return memberEnumMap.get(memberType);
    }
}
