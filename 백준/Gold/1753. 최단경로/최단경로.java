/** 작성자 : 황성민
 * 작성 일자 : 24.02.27
 * 문제 해결 : 다익스트라 알고리즘을 사용한다.  --- >   한 노드에서 다른 모든 노드까지의 최단거리를 구하는 알고리즘 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 최대 노드 20,000 간선의 최대 가중치 10을 고려하여 최댓값 설정
		final int INF = 200_001;

		// 노드 수, 엣지 수, 시작지점 
		int Node = Integer.parseInt(st.nextToken());
		int Edge = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());

		
		// 방문 배열 
		boolean[] visited = new boolean[Node + 1];
		// 거리 배열 
		int[] distance = new int[Node + 1];

		// 그래프 인접리스트로 표현 
		LinkedList<Edge>[] graph = new LinkedList[Node + 1];
		for (int i = 0; i < Node + 1; i++) {
			graph[i] = new LinkedList();
		}

		
		// 그래프 정보 입력 
		for (int i = 0; i < Edge; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[from].offer(new Edge(to, weight));
		}

		
		// 초기값 거리값 무한으로 
		Arrays.fill(distance, INF);

		// 시작점 거리 0으로 
		distance[start] = 0;

		// 우선 순위 큐 선언  거리가 짧을 수록 우선순위 
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		// 큐에 시작 지점과 거리 넣어주기 
		pq.offer(new Edge(start, 0));
		
		// 시작 지점은 방문 처리 
		visited[start] = true;

		// 큐가 빌때까지 반복 
		while (!pq.isEmpty()) {
			
			// 현재 노드와 거리를 가지고 있는 객체 
			Edge cur = pq.poll();
			
			// 현재 노드 
			int tmp = cur.next;
			
			// 현재 노드와 연걸된 노드들 확인 
			for (Edge e : graph[tmp]) {
				
				// 다음 노드의 거리값이      현재의 거리값 + 간선의 가중치 보다 크다면 
				if (distance[e.next] > distance[tmp] + e.weight) {
					// 값 갱신 
					distance[e.next] = distance[tmp] + e.weight;
					
					// 우선순위 큐에 넣어준다. 
					pq.offer(new Edge(e.next, distance[e.next]));
				}
			}
		}

		
		// 값 출력 
		for (int i = 1; i < Node + 1; i++) {

			if (distance[i] == INF)
				System.out.println("INF");
			else
				System.out.println((int) distance[i]);
		}

	}

	static class Edge implements Comparable<Edge> {
		int next;
		int weight;

		public Edge(int to, int weight) {
			this.next = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
}