package aud7;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class InvalidPickerArgumentsException extends Exception {

    public InvalidPickerArgumentsException(String msg) {
        super(msg);
    }
}

class FinalistPicker {
    int finalists;
    static Random RANDOM = new Random();

    public FinalistPicker(int finalists) {
        this.finalists = finalists;
    }

    public List<Integer> pick(int n) throws InvalidPickerArgumentsException {
        if (n > finalists)
            throw new InvalidPickerArgumentsException("The number n cannot exceed the number of finalists");
        if (n <= 0) throw new InvalidPickerArgumentsException("The number n must be a positive number");

        List<Integer> pickedFinalists = new ArrayList<>();

     return  RANDOM.ints(2*n, 1, finalists +1).
             boxed().
             distinct().
             limit(n).
             collect(Collectors.toList());

//        while (pickedFinalists.size() != n) {
//            int pick = RANDOM.nextInt(finalists) + 1;
//            if (!pickedFinalists.contains(pick)){
//                pickedFinalists.add(pick);
//            }
//        }
//
//        return pickedFinalists;
    }
}

public class FinalistTest {
    public static void main(String[] args) {
        FinalistPicker picker = new FinalistPicker(5);
        try {
            System.out.println(picker.pick(3));
        } catch (InvalidPickerArgumentsException e) {
            System.out.println(e.getMessage());
        }

    }
}
