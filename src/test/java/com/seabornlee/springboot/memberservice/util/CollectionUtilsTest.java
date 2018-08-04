package com.seabornlee.springboot.memberservice.util;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CollectionUtilsTest {
    @Test
    public void should_return_null_given_null() {
        assertThat(CollectionUtils.split(null, 2)).isNull();
    }

    @Test
    public void should_return_null_given_empty_list() {
        assertThat(CollectionUtils.split(emptyList(), 2)).isNull();
    }

    @Test
    public void should_return_null_given_length_less_than_one() {
        assertThat(CollectionUtils.split(Lists.list("1"), 0)).isNull();
    }

    /*@Test
    public void 集合元素个数刚好和length一样() {
        List<List<Object>> split = CollectionUtils.split(Lists.list("1", "2"), 2);
        assertThat(split).isEqualTo(Lists.list(Lists.list("1", "2")));
    }

    @Test
    public void 集合元素不能整除length() {
        List<List<Object>> split = CollectionUtils.split(Lists.list("1", "2", "3"), 2);
        assertThat(split).isEqualTo(Lists.list(Lists.list("1", "2"), Lists.list("3")));
    }*/
}
