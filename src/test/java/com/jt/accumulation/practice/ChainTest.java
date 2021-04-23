package com.jt.accumulation.practice;

import com.jt.design_patterns.chains.handlers.verify.VerifyChain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/4/23 14:07
 */
@SpringBootTest
public class ChainTest {

    @Autowired
    private VerifyChain verifyChain;

    @Test
    public void testChain() {
        List<Object> verify = verifyChain.verify(new ArrayList<>());
        System.out.println(verify);
    }
}
