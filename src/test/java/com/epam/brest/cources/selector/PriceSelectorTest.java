package com.epam.brest.cources.selector;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class PriceSelectorTest {

    private PriceSelector selector = new PriceSelector();

    private static Map<Integer, BigDecimal> valuesMap;

    @BeforeAll
    static void setup() {
        valuesMap = Mockito.mock(Map.class);
    }

    @Test
    public void selectPriceValue() {

        SortedSet<Integer> sortedKeys = new TreeSet<>();
        sortedKeys.add(5);

        Mockito.when(valuesMap.keySet()).thenReturn(sortedKeys);
        Mockito.when(valuesMap.get(Mockito.any())).thenReturn(new BigDecimal(7));

        BigDecimal result = selector.selectPriceValue(valuesMap, new BigDecimal(2));

        Mockito.verify(valuesMap, Mockito.times(1)).keySet();
        Mockito.verify(valuesMap, Mockito.times(1)).get(5);
        Mockito.verifyNoMoreInteractions(valuesMap);

        Assert.assertNotNull(result);
        Assert.assertEquals(result, new BigDecimal(7));
    }
}
