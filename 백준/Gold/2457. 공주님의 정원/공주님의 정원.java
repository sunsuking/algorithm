import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	static class Work {
		int startM;
		int startD;
		int endM;
		int endD;
		int day;
		
		public Work(int startM, int startD, int endM, int endD) {
			super();
			this.startM = startM;
			this.startD = startD;
			this.endM = endM;
			this.endD = endD;
			
			if (startM == endM) {
				this.day = endD - startD; 
			} else {
				int startDay = days[startM] - startD;
				for (int m = startDay + 1; m < endM; m++) {
					startDay += days[m];
				}
				startDay += endD;
				this.day = startDay;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		StringTokenizer st;
		List<Work> remains = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			remains.add(new Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int month = 3;
		int day = 1;
		int count = 0;
		
		while(true) {
			if (month > 11) break;
			List<Work> remainWorks = new ArrayList<>();
			for (Work w : remains) {
				if (month > w.startM || (month == w.startM && day >= w.startD)) {
					remainWorks.add(w);
				}
			}
			if (remainWorks.size() == 0) {
				System.out.println(0);
				System.exit(0);
			}
			
			Collections.sort(remainWorks, (w1, w2) -> {
				int compare = Integer.compare(w2.endM, w1.endM);
				if (compare == 0) {
					return Integer.compare(w2.endD, w1.endD);
				}
				return compare;
			});
			
			Work pick = remainWorks.get(0);
			month = pick.endM;
			day = pick.endD;
			remains.remove(pick);
			count++;
		}
		System.out.println(count);
	}

}