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

int solution(vector< vector<int> > board, vector <int> moves) {
	int answer = 0;
	vector<int> stack;

	int len = moves.size();
	for (int i = 0; i < len; i++) {	// 크레인 입력 명령 수 만큼
		int spot = moves.at(i) - 1;	// 크레인 위치 받아오기
		//cout << spot << "에 크레인 위치" << endl;

		for (int j = 0; j < board.size(); j++) {	// 크레인 위치에서 depth만큼 들어감
			if (board[j].at(spot) != 0) {			// 들어갔을 때 비어있는 곳이 아니면
				if (stack.empty()) {				// 스택 비어있으면 
				//	cout << board[j].at(spot) << "가 스택에 들어갔어요 !" << endl;
					stack.push_back(board[j].at(spot));		// 스택에 넣기
				}
				else {
					if (stack.back() == board[j].at(spot)) {	// 스택에 있는 인형이 크레인에 있는 인형이랑 같으면
					//	cout << stack.back() << "에서 제거 됐어요!" << endl;
						stack.pop_back();	// 제거
						answer += 2;		// 카운트 증가
					}
					else {		// 비어있지도 않고, 인형이 안같을 때
				//		cout << board[j].at(spot) << "가 스택에 들어갔어요 !" << endl;
						stack.push_back(board[j].at(spot));		// 스택에 넣기
					}
				}
				board[j][spot] = 0;
				break;
			}
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