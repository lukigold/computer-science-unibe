import java.util.*;


public class Benchmark
{
    long time_insertionSort(int[] arr)
    {
        Timer timer = new Timer();
        Sorting.insertionSort(arr);
        return timer.timeElapsed();
    }

    long time_mergeSort(int[] arr)
    {
        Timer timer = new Timer();
        Sorting.mergeSort(arr, 0, arr.length-1);
        return timer.timeElapsed();
    }

    long time_builtinSort(int[] arr)
    {
        Timer timer = new Timer();
        Arrays.sort(arr);
        return timer.timeElapsed();
    }

    void run() {
        Random random = new Random();
        System.out.println("n_elements;ins_ms;merge_ms;builtin_ms");
        long duration_merge_ms = 0;
        long duration_insert_ms = 0;
        long duration_builtin_ms = 0;
        List<Integer> lens = new ArrayList<Integer>();
        for (int len=20000; len <= 300000; len += 20000) {
            lens.add(len);
        }
        for (int len=300000; len <= 3000000; len += 100000) {
            lens.add(len);
        }
        for (int len=3000000; len <= 10000000; len += 500000) {
            lens.add(len);
        }
        for (int len: lens)
        {
            int[] random_arr = new int[len];
            int repetitions = 5;
            for (int i=0; i < len; ++i) {
                random_arr[i] = random.nextInt();
            }
            duration_builtin_ms = 0;
            duration_merge_ms = 0;
            duration_insert_ms = 0;
            for (int i = 0; i < repetitions; ++i) {
                duration_builtin_ms += time_builtinSort(random_arr.clone());
                duration_merge_ms += time_mergeSort(random_arr.clone());
            }
            duration_builtin_ms /= repetitions;
            duration_merge_ms /= repetitions;

            if (len > 300000) {
                duration_insert_ms = -1;
            } else {
                if (len >= 100000) {
                    repetitions = 3;
                } else if (len >= 200000) {
                    repetitions = 1;
                }
                for (int i = 0; i < repetitions; ++i) {
                    duration_insert_ms += time_insertionSort(random_arr.clone());
                }
                duration_insert_ms /= repetitions;
            }



            System.out.println(String.valueOf(len)
                    + ";" + duration_insert_ms
                    + ";" + duration_merge_ms
                    + ";" + duration_builtin_ms);

        }
    }
    public static void main(String[] args)
    {
        Benchmark b = new Benchmark();
        b.run();
    }
}
