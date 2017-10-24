package com.sync.test;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @Author: ljavaw
 * @Description: ansj测试类
 * @Date:17:24 2017/10/18
 */
public class AnsjTest {
    



    static Map<String, Integer> map = new HashMap<String, Integer>();
    static List<String> list = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

        test();
        InputWord();
        /*FileReader fr = new FileReader("resultbig.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String outline[] = new String[2000];
        int count;
        int progress = 0;

        while (line != null) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            //System.out.println(line);
            progress++;
            System.out.println("now is processing the " + progress + "'s news");
            outline = line.split(" ", -1);
            for (int i = 0; i < outline.length; i++) {
                if (map.containsKey(outline[i]) != false) {
                    count = map.get(outline[i]) + 1;
                    map.put(outline[i], count);
                }
            }

        }
        fr.close();
        br.close();*/
        String outline[] = new String[2000];
        int count;
        int progress = 0;
        for (String temp : list) {
            progress++;
            System.out.println("now is processing the " + progress + "'s news");
            outline = temp.split(" ", -1);
            for (int i = 0; i < outline.length; i++) {
                if (map.containsKey(outline[i]) != false) {
                    count = map.get(outline[i]) + 1;
                    map.put(outline[i], count);
                }
            }
        }
        WriteWord();

    }

    /**
     * @Author: ljavaw
     * @Description: 分词
     * @Date:17:24 2017/10/18
     */
    public static void test() {
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(str); //分词结果的一个封装，主要是一个List<Term>的terms
        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms(); //拿到terms
        System.out.println(terms.size());

        for(int i=0; i<terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            list.add(word);
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if(expectedNature.contains(natureStr)) {
                System.out.println(word + ":" + natureStr + ":" +terms.get(i).toValue());
            }
        }
    }

    /**
     * @Author: ljavaw
     * @Description:put the word into the map
     * @Date:16:42 2017/10/19
     */
    public static void InputWord() throws IOException {
        System.out.println("begin get the word");
        FileReader fr = new FileReader("D:\\2017-09-20\\baike.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = new String();
        while (line != null) {
            line = br.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
            map.put(line, 0);
        }
        br.close();
        fr.close();
        System.out.println("get the word is ok");
    }

    /**
     * @Author: ljavaw
     * @Description: write word and frequency into the file
     * @Date:16:42 2017/10/19
     */
    public static void WriteWord() throws IOException {
        System.out.println("begin write the word");
        File file = new File("D:\\2017-09-20\\wordfreq.txt");
        file.createNewFile();
        FileWriter fw = new FileWriter("wordfreq.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            int value = entry.getValue();
            bw.write(key + " " + value);
            bw.newLine();
            bw.flush();
        }
        fw.close();
        bw.close();
        System.out.println("write the word is ok");
    }
}
