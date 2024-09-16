package edu.school21.numbers;


import edu.school21.exceptions.IllegalNumberException;

public class NumberWorker
{
    public boolean isPrime(int number) throws IllegalNumberException {
        if (number < 2) {
            throw new IllegalNumberException();
        }
        for (int i = 2; i*i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

}
