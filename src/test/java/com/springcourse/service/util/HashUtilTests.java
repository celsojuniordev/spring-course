package com.springcourse.service.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class HashUtilTests {

    @Test
    public void getSecureHashTest() {
        String hash = HashUtil.getSecureHash("Teste");
        System.out.println(hash);

        assertThat(hash.length()).isEqualTo(64);
    }
}
