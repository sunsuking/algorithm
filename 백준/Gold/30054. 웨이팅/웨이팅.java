import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Reservation {
    int reservationTime;
    int arriveTime;

    public Reservation(int r, int a) {
        this.reservationTime = r;
        this.arriveTime = a;
    }

    public boolean isOnTime() {
        return reservationTime >= arriveTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationTime=" + reservationTime +
                ", arriveTime=" + arriveTime +
                '}';
    }
}

class N30054Comparator implements Comparator<Reservation> {
    @Override
    public int compare(Reservation o1, Reservation o2) {
        int compare = Integer.compare(o1.arriveTime, o2.arriveTime);
        if (compare == 0) {
            return Integer.compare(o1.reservationTime, o2.reservationTime);
        }
        return compare;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Queue<Reservation> queue = new PriorityQueue<>(new N30054Comparator());
        Map<Integer, Reservation> map = new HashMap<>();
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int reservationTime = Integer.parseInt(st.nextToken());
            Reservation r = new Reservation(reservationTime, Integer.parseInt(st.nextToken()));
            map.put(reservationTime, r);
            queue.offer(r);
        }

//        System.out.println(map);

        int time = 0;
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()) {
            time++;
//            System.out.println(time);

            if (map.containsKey(time) && map.get(time).isOnTime()) {
                Reservation reservation = map.get(time);
//                System.out.println(reservation);
//                System.out.println("map");
                queue.remove(reservation);
                max = Math.max(max, time - reservation.arriveTime);
                continue;
            }

            if (queue.peek().arriveTime > time) continue;
            Reservation pop = queue.poll();
            max = Math.max(max, time - pop.arriveTime);
            map.remove(pop.reservationTime);
        }
//        System.out.println(time);
        System.out.println(max);
    }
}