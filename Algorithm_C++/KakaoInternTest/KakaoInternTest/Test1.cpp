#include <iostream>
#include <vector>

using namespace std;
const int MAXSIZE = 30;         // 상수는 define 보다는 const로 처리해주는 것이 더 좋음.(안정성 문제)

struct MyStack {
	int basket[MAXSIZE];
	int top = -1;

	void push(int num) {
		if (top >= MAXSIZE)    top = 0;

		basket[++top] = num;
	}

	int pop() {
		return top < 0 ? 0 : basket[top--];
	}

	int peek() {
		return top < 0 ? 0 : basket[top];
	}
};

int findIDX(vector<vector<int>> board, int spot) {
	int idx = -1;

	for (int i = 0; i < board.size(); i++) {
		if (board[i].at(spot) != 0) return i;
	}

	return idx;
}

int solution(vector< vector<int> > board, vector <int> moves) {
	int answer = 0;
	MyStack stack;

	int len = moves.size();
	for (int i = 0; i < len; i++) {
		int spot = moves.at(i) - 1;
		int idx = findIDX(board, spot);

		if (idx != -1) {
			if (stack.peek() == board[idx][spot]) {
				stack.pop();
				answer += 2;
			}
			else {
				stack.push(board[idx][spot]);
			}
			board[idx][spot] = 0;
		}
	}
	
	return answer;
}

int main() {
	vector<vector<int>> board { {0,0,0,0,0}, {0,0,1,0,3}, {0,2,5,0,1}, {4,2,4,4,2}, {3,5,1,3,1} };
	vector<int> moves = { 1,5,3,5,1,2,1,4 };
	
	printf("%d", solution(board, moves));

	return 0;
}