import java.math.BigInteger;
import java.util.Scanner;

public class test_2168 {
	public static long gcd(long a,long b){
		BigInteger b1 = BigInteger.valueOf(a);
		BigInteger b2 = BigInteger.valueOf(b);
	    BigInteger gcd = b1.gcd(b2);

	    return gcd.intValue();
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
		long n = scan.nextLong(); 
		long m = scan.nextLong(); 
		System.out.println(n+m-gcd(n,m));
	}
	
	
	// 참고 사이트 : https://m.blog.naver.com/PostView.nhn?blogId=programmer18&logNo=221107256038&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F
	/*
	 * nxm 그림을 그려보면 gcd(n,m) >1  ~>  타일들의 교차꼭지점을 지난다. 
	 * gcd = 1  ~>  꼭지점을 아예 지나지 않는다. 
	 * 따라서 경우 두가지
	 *  
	 * 1) gcd(n,m) = 1
	 * 대각선이 가로와 세로줄을 한번씩 지날때마다 대각선이 그려진 타일의 개수가 증가 
	 * (n-1)의 가로줄, (m-1)의 세로줄, 처음 시작한 타일(1)을 포함 ~> (n-1)+(m-1)+1 = n+m-1 개의 타일이 색칠.
	 * 
	 * 2)g =gcd(n,m) > 1 
	 * 총 g 개의 (n/g) x (m/g) 인 gcd(n/g,m/g) = 1 인 직사각형을 만들 수 있으므로 g(n/g + m/g -1) = n+m-g. 
	 */
}
