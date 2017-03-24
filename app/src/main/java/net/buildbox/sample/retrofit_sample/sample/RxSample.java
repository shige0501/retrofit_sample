package net.buildbox.sample.retrofit_sample.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class RxSample {
    private static final List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    public static List<Integer> getEvenListBefore() {
        List<Integer> evenLists = new ArrayList<>();
        for (Integer num : numList) {
            if (num % 2 == 0) {
                evenLists.add(num);
            }
        }
        return evenLists;
    }

    public static Single<List<Integer>> getEvenListAfter() {
        return Flowable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .filter(i -> i % 2 == 0)
            .toList();
    }
}
