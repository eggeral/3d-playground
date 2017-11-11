package test

import test.matrix.*
import test.shapes.*

fun runTestClass(testClass: Any, verbose: Boolean = false) {
    js("""
        var testClassName = testClass.constructor.name;
        var hasSetUp = false;
        var hasTearDown = false;
        var tests = [];

        console.log("Running " + testClassName + "...");

        for (var field in testClass) {
            if (typeof(testClass[field]) !== "function" || field === "constructor")
                continue;

            if (field === "setUp") {
                hasSetUp = true;
            } else if (field === "tearDown") {
                hasTearDown = true;
            } else {
                tests.push({ name: field, fn: testClass[field] });
            }
        }

        if ((hasSetUp && !hasTearDown) || (!hasSetUp && hasTearDown)) {
            console.log("Consider adding both setUp() and tearDown() to " + testClassName);
        }

        var failures = 0;

        for (var i = 0; i < tests.length; ++i) {
            try {
                if (hasSetUp)
                    testClass.setUp();

                tests[i]["fn"].call(testClass);

                if (hasTearDown)
                    testClass.tearDown();

                if (verbose)
                    console.log("    " + tests[i]["fn"] + "... OK");
            }
            catch (e) {
                console.log("    "  + tests[i]["name"] + "... FAILED: " + e);
                ++failures;
            }
        }

        console.log("    " + tests.length + " run, " + failures + " failed");
        console.log();
    """);
}

fun run() {
    val tests = arrayOf(
            Vec2Test(),
            Mat2Test(),
            Mat2dTest(),
            Mat3Test(),
            Mat4Test(),
            TestRectangle(),
            TestTriangle()
    );

    for (testClass: Any in tests)
        runTestClass(testClass);
}