import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Test_14406 {

    static HashMap<String,String> saveSaying() {
        HashMap<String, String> saying = new HashMap<>();

        saying.put("사.우.나","사랑과 우정을 나누자");
        saying.put("오.징.어","오래도록 징그럽게 어울리자");
        saying.put("사.이.다","사랑하자 이 세상 다 바쳐");
        saying.put("나.가.자","나라, 가정, 자신의 발전을 위하여");
        saying.put("재.개.발","재미있고 개성있게 발전적으로 살자");
        saying.put("우.아.미","우아하고 아름다운 미래를 위하여");
        saying.put("이.기.자","이런 기회를 자주 만들자");
        saying.put("청.바.지","청춘은 바로 지금부터");
        saying.put("걸.걸.걸", "더 사랑할걸, 더 참을걸, 더 즐길걸.");
        saying.put("지.화.자", "지금부터 화합하자");
        saying.put("재.건.축", "재미있고 건강하게, 축복하며 살자");
        saying.put("해.당.화", "해가 갈수록 당당하고 화려하게");
        saying.put("주.전.자", "주인의식을 갖고 전문성을 갖추고 자신있게 살자");


        return saying;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String,String> saying = saveSaying();

        String key = br.readLine();

        System.out.println(saying.get(key));

        br.close();

    }
}
