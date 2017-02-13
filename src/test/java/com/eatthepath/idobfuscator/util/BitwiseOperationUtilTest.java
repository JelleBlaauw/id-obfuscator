package com.eatthepath.idobfuscator.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.eatthepath.idobfuscator.util.BitwiseOperationUtil;

public class BitwiseOperationUtilTest {

    @Test
    public void testGetLowestBits() {
        assertEquals(0x00L, BitwiseOperationUtil.getLowestBits(Long.MAX_VALUE, 0));
        assertEquals(0xffL, BitwiseOperationUtil.getLowestBits(Long.MAX_VALUE, Byte.SIZE));
        assertEquals(0xffffffffffffffffL, BitwiseOperationUtil.getLowestBits(-1L, Long.SIZE));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLowestBitsWidthTooLong() {
        BitwiseOperationUtil.getLowestBits(Long.MAX_VALUE, Long.SIZE + 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLowestBitsWidthNegative() {
        BitwiseOperationUtil.getLowestBits(Long.MAX_VALUE, -1);
    }

    @Test
    public void testSignExtendLowestBitsToLong() {
        assertEquals(-1L, BitwiseOperationUtil.signExtendLowestBitsToLong(-1L, Long.SIZE));
        assertEquals(0L, BitwiseOperationUtil.signExtendLowestBitsToLong(0L, Long.SIZE));
        assertEquals(1L, BitwiseOperationUtil.signExtendLowestBitsToLong(1L, Long.SIZE));
        assertEquals(Long.MAX_VALUE, BitwiseOperationUtil.signExtendLowestBitsToLong(Long.MAX_VALUE, Long.SIZE));
        assertEquals(Long.MIN_VALUE, BitwiseOperationUtil.signExtendLowestBitsToLong(Long.MIN_VALUE, Long.SIZE));

        assertEquals(-1L, BitwiseOperationUtil.signExtendLowestBitsToLong(0x01ffff, 17));
        assertEquals(0x00ffffL, BitwiseOperationUtil.signExtendLowestBitsToLong(0x00ffffL, 17));
        assertEquals(0L, BitwiseOperationUtil.signExtendLowestBitsToLong(0xffffffff00000000L, Integer.SIZE));
        assertEquals(-1L, BitwiseOperationUtil.signExtendLowestBitsToLong(0x00000000ffffffffL, Integer.SIZE));
    }

    @Test
    public void testGetMinValueForSize() {
        assertEquals(Byte.MIN_VALUE, BitwiseOperationUtil.getMinValueForSize(Byte.SIZE));
        assertEquals(Short.MIN_VALUE, BitwiseOperationUtil.getMinValueForSize(Short.SIZE));
        assertEquals(Integer.MIN_VALUE, BitwiseOperationUtil.getMinValueForSize(Integer.SIZE));
        assertEquals(Long.MIN_VALUE, BitwiseOperationUtil.getMinValueForSize(Long.SIZE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinValueForSizeZeroBits() {
        BitwiseOperationUtil.getMinValueForSize(Long.SIZE + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMinValueForSizeTooManyBits() {
        BitwiseOperationUtil.getMinValueForSize(Long.SIZE + 1);
    }

    @Test
    public void testGetMaxValueForSize() {
        assertEquals(Byte.MAX_VALUE, BitwiseOperationUtil.getMaxValueForSize(Byte.SIZE));
        assertEquals(Short.MAX_VALUE, BitwiseOperationUtil.getMaxValueForSize(Short.SIZE));
        assertEquals(Integer.MAX_VALUE, BitwiseOperationUtil.getMaxValueForSize(Integer.SIZE));
        assertEquals(Long.MAX_VALUE, BitwiseOperationUtil.getMaxValueForSize(Long.SIZE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMaxValueForSizeZeroBits() {
        BitwiseOperationUtil.getMaxValueForSize(Long.SIZE + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMaxValueForSizeTooManyBits() {
        BitwiseOperationUtil.getMaxValueForSize(Long.SIZE + 1);
    }

    @Test
    public void testAssertValueFitsWithinSize() {
        // We're happy here as long as nothing explodes
        BitwiseOperationUtil.assertValueFitsWithinSize(Byte.MIN_VALUE, Byte.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Byte.MAX_VALUE, Byte.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Short.MIN_VALUE, Short.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Short.MAX_VALUE, Short.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Integer.MIN_VALUE, Integer.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Integer.MAX_VALUE, Integer.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Long.MIN_VALUE, Long.SIZE);
        BitwiseOperationUtil.assertValueFitsWithinSize(Long.MAX_VALUE, Long.SIZE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertValueFitsWithinSizeZeroBits() {
        BitwiseOperationUtil.assertValueFitsWithinSize(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAssertValueFitsWithinSizeTooManyBits() {
        BitwiseOperationUtil.assertValueFitsWithinSize(0, Long.SIZE + 1);
    }
}
