package _1_basic._3_thread_api.Fight;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zy
 * @date 2023/1/6 18:39
 */
public class FightQueryExample {
    private static List<String> fightCompanies = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> serach = serach("chengdu", "shenzhen");
        System.out.println("===查询结束===");
        serach.forEach(t -> System.out.println(t));
    }

    public static List<String> serach(String src, String des) {
        final List<String> result = new ArrayList<>();
        List<FightQueryTask> tasks = fightCompanies.stream().map(f -> createSearchTask(f, src, des)).collect(Collectors.toList());
        //tasks.forEach(task -> task.start());
        tasks.forEach(Thread::start);
        tasks.forEach(task -> {
            try {
                task.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tasks.stream().map(t ->  t.get()).forEach(result::addAll);
        return result;


    }


    private static FightQueryTask createSearchTask(String fight, String src, String des) {
        return new FightQueryTask(fight, src, des);
    }

}
