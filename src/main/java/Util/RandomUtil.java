package Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by nick on 2017/12/10.
 */
public class RandomUtil {

    public static String randomQuestion(int question_num,long question_count){
        String result="";
        List<Integer> list =new ArrayList<>();
        while (list.size()<question_num){
            int ran = (int) (Math.random()*question_count);
            if (!list.contains(ran)){
                list.add(ran);
                result+=ran+"-";
            }
        }
        result = result.substring(0,result.length()-1);
        return result;
    }

    public static String randomPossibleAnswer(int question_num){
        String result="";

        for (int i=0;i<question_num;i++){
            int[] sequence = {1,2,3,4};
            for(int j = 0; j < 4; j++){
                int p = (int) (Math.random()*4);
                int tmp = sequence[j];
                sequence[j] = sequence[p];
                sequence[p] = tmp;
            }
            for (int num:sequence){
                result+=num;
            }
            result+="-";
        }

        result = result.substring(0,result.length()-1);
        return result;
    }

    public static String randomCode(String studentid,String examid){
        return studentid+examid+getRandomString(6);
    }


    private static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
