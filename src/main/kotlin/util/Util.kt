package util

fun assert(stmt: Boolean, message: String?) {
    if (!stmt)
        throw AssertionError(message);
}