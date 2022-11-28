import ProducerBuyer.ProducerBuyer;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * CSE 460 Software Analysis and Design Project - Sample Test Cases
 * This class contains some test cases that will be used in automated grading of your project.
 * Note that these test cases are not exhaustive - other cases and abnormal inputs will be used as well.
 *
 * These tests concern only with the correctness and robustness of your implementation. You are still required to
 * adopt Publisher-Subscriber pattern in your design and make use of good programming practises. Otherwise you can
 * suffer heavy penalty for a 100% correct implementation!
 *
 * The undisclosed part of the test will differ from this test somewhat:
 * - Test cases are automatically generated with random characters (other than commas and leading/trailing spaces).
 * - The generated test cases can be very long and contains unusual sequences.
 * - Instead of testing the output of your program against a "standard answer", your output would be compared against
 *   the output of a "Reference Implementation" (RI), which is seen as the authoritative answer.
 *
 * @author Sheetal Mohite <smohite3@asu.edu>
 * @version 1.0
 */
public class PublicTestCases {
    private static ProducerBuyer pb;

    // Create ProducerBuyer object to test with
    @BeforeClass
    public static void setupProducerBuyer() {
        pb = new ProducerBuyer();
    }

    // Reset the pb object every time a test finishes so that it can accept a new batch of commands
    @After
    public void resetProducerBuyer() {
        pb.reset();
    }

    @Test
    public void testSubPub() {
        // Expected output
        List<String> expected = new ArrayList<>(Arrays.asList(
                "avis notified car: honda civic, hybrid fuel",
                "avis notified car: audi q5, hybrid fuel",
                "avis notified car: chevrolet general motors, hybrid fuel"));

        // Feed the pb object with some commands

        pb.processInput("subscribe, Avis, Car");
        //publish [producer] [product category] [model type] [fuel name]
        pb.processInput("publish, Honda, Car, Civic, hybrid");
        pb.processInput("publish, Audi, Car, Q5, hybrid");
        pb.processInput("publish, Chevrolet, Car, general motors, hybrid");

        // Obtain the actual result from your pb object and compare it with the expected output
        // Note that each entry is stripped and converted to lowercase before comparison
        List<String> actual = pb.getAggregatedOutput().stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    public void testIllegalParamLens() {
        // Expected output (nothing)
        List<String> expected = new ArrayList<>();

        pb.processInput("subscribe, Avis, Car, abccccc");
        pb.processInput("subscribe, Budget");
        pb.processInput("publish, Honda, Car, Civic, hybrid, $15000.00");

        // Obtain the actual result from your pb object and compare it with the expected output
        List<String> actual = pb.getAggregatedOutput();

        assertEquals(expected, actual);
    }

}
