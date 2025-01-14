# 녹색 옷 입은애가 젤다지?

from collections import deque
import heapq

di = [1, -1, 0,  0]
dj = [0, 0, -1, 1]

def bfs(lst, N):
  pq = []
  heapq.heappush(pq, (lst[0][0], 0, 0))  # (비용, i좌표, j좌표)

  visited = [[1e9] * N for _ in range(N)]
  visited[0][0] = lst[0][0]

  while pq:
    cost, i, j = heapq.heappop(pq)

    if i == N - 1 and j == N - 1:
      break

    for d in range(4):
      ni = i + di[d]
      nj = j + dj[d]

      # 인덱스 검사
      if 0 <= ni < N and 0 <= nj < N:
        new_cost = cost + lst[ni][nj]
        if visited[ni][nj] > new_cost:
          visited[ni][nj] = new_cost
          heapq.heappush(pq, (visited[ni][nj], ni, nj))
  
  return visited[N - 1][N - 1]

T = int(input())
tc = 1

while T != 0:
  arr = [list(map(int, input().split())) for _ in range(T)]
  result = bfs(arr, T)
  print(f'Problem {tc}: {result}')
  T = int(input())
  tc += 1
