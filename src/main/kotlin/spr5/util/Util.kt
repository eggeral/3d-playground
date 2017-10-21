package spr5.util;

import kotlin.js.Math;

const val EPS = 0.00001;

fun assert(stmt: Boolean, message: String = "Assertion failed") {
    if (!stmt)
        throw AssertionError(message);
}

fun assertEquals(expected: Double, actual: Double, message: String = "Values are not equal") {
    assert(Math.abs(expected - actual) <= EPS, message);
}

fun<T> assertEquals(expected: T, actual: T, message: String = "Values are not equal") {
    assert(expected == actual, message);
}

fun<T> assertNull(v: T, message: String = "Value is not null") {
    assert(v == null, message);
}

fun<T> assertNotNull(v: T, message: String = "Value is null") {
    assert(v != null, message);
}

fun assertTrue(stmt: Boolean, message: String = "Value is not true") {
    assert(stmt, message);
}

fun assertFalse(stmt: Boolean, message: String = "Value is not false") {
    assert(!stmt, message);
}