#include <iostream>
#include <vector>

using namespace std;
const int MAXSIZE = 30;         // ����� define ���ٴ� const�� ó�����ִ� ���� �� ����.(������ ����)

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
	for (int i = 0; i < len; i++) {	// ũ���� �Է� ��� �� ��ŭ
		int spot = moves.at(i) - 1;	// ũ���� ��ġ �޾ƿ���
		//cout << spot << "�� ũ���� ��ġ" << endl;

		for (int j = 0; j < board.size(); j++) {	// ũ���� ��ġ���� depth��ŭ ��
			if (board[j].at(spot) != 0) {			// ���� �� ����ִ� ���� �ƴϸ�
				if (stack.empty()) {				// ���� ��������� 
				//	cout << board[j].at(spot) << "�� ���ÿ� ����� !" << endl;
					stack.push_back(board[j].at(spot));		// ���ÿ� �ֱ�
				}
				else {
					if (stack.back() == board[j].at(spot)) {	// ���ÿ� �ִ� ������ ũ���ο� �ִ� �����̶� ������
					//	cout << stack.back() << "���� ���� �ƾ��!" << endl;
						stack.pop_back();	// ����
						answer += 2;		// ī��Ʈ ����
					}
					else {		// ��������� �ʰ�, ������ �Ȱ��� ��
				//		cout << board[j].at(spot) << "�� ���ÿ� ����� !" << endl;
						stack.push_back(board[j].at(spot));		// ���ÿ� �ֱ�
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