package edu.school21.numbers;

import edu.school21.exceptions.IllegalNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
class NumberWorkerTest {

    NumberWorker nw = new NumberWorker();

    @ParameterizedTest(name = "{index} - {0} is a prime")
    @ValueSource(ints = {7,11,13})
    void isPrimeForPrimes(int number) throws IllegalNumberException {
        assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest(name = "{index} - {0} is not a prime")
    @ValueSource(ints = {6,10,1002})
    void isPrimeForNotPrimes(int number) throws IllegalNumberException {
        assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest(name = "{index} - {0} is illegal number")
    @ValueSource(ints = {0,1})
    void isPrimeForIncorrectNumbers() throws IllegalNumberException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            nw.isPrime(1);
        });
    }

    @ParameterizedTest(name = "{index} - {0} is correct number")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 0)
    void checkDigitsSum(int number, int result) {
        assertEquals(nw.digitsSum(number), result);
    }

}