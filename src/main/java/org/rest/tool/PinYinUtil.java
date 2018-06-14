package org.rest.tool;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XiuYin.Cui on 2018/6/14.
 */
/**
 * 拼音工具类
 *
 * @author lsf
 */
public class PinYinUtil {
    public static Map<String,String> dictionary = new HashMap<String,String>();

    //加载多音字词典
    static{

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(PinYinUtil.class.getResourceAsStream("/duoyinzi_pinyin.txt"),"UTF-8"));

            String line = null;
            while((line=br.readLine())!=null){

                String[] arr = line.split("#");

                if(StringUtils.isNotEmpty(arr[1])){
                    String[] sems = arr[1].split(" ");
                    for (String sem : sems) {

                        if(StringUtils.isNotEmpty(sem)){
                            dictionary.put(sem , arr[0]);
                        }
                    }
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String[] chineseToPinYin(char chineseCharacter) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        if(chineseCharacter>=32 && chineseCharacter<=125){    //ASCII >=33 ASCII<=125的直接返回 ,ASCII码表：http://www.asciitable.com/
            return new String[]{String.valueOf(chineseCharacter)};
        }

        return PinyinHelper.toHanyuPinyinStringArray(chineseCharacter, outputFormat);
    }

    /**
     * 获取汉字拼音的全拼
     * @param chineseCharacter
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String chineseToPinYin(String chineseCharacter) throws BadHanyuPinyinOutputFormatCombination{
        if(StringUtils.isEmpty(chineseCharacter)){
            return null;
        }

        char[] chs = chineseCharacter.toCharArray();

        StringBuilder result = new StringBuilder();

        for(int i=0;i<chs.length;i++){
            String[] arr = chineseToPinYin(chs[i]);
            if(arr==null){
                result.append("");
            }else if(arr.length==1){
                result.append(arr[0]);
            }else if(arr[0].equals(arr[1])){
                result.append(arr[0]);
            }else{

                String prim = chineseCharacter.substring(i, i+1);

                String lst = null,rst = null;

                if(i<=chineseCharacter.length()-2){
                    rst = chineseCharacter.substring(i,i+2);
                }
                if(i>=1 && i+1<=chineseCharacter.length()){
                    lst = chineseCharacter.substring(i-1,i+1);
                }

                String answer = null;
                for (String py : arr) {

                    if(StringUtils.isEmpty(py)){
                        continue;
                    }

                    if((lst!=null && py.equals(dictionary.get(lst))) ||
                            (rst!=null && py.equals(dictionary.get(rst)))){
                        answer = py;
                        break;
                    }

                    if(py.equals(dictionary.get(prim))){
                        answer = py;
                    }
                }
                if(answer!=null){
                    result.append(answer);
                }else{
                }
            }
        }

        return result.toString().toLowerCase();
    }

    public static String chineseToPinYinSToUpperCase(String chineseCharacter) throws BadHanyuPinyinOutputFormatCombination{
        return chineseToPinYinS(chineseCharacter).toUpperCase();
    }

    public static String chineseToPinYinSToLowerCase(String chineseCharacter) throws BadHanyuPinyinOutputFormatCombination{
        return chineseToPinYinS(chineseCharacter).toLowerCase();
    }

    public static String chineseToPinYinS(String chineseCharacter) throws BadHanyuPinyinOutputFormatCombination{
        if(StringUtils.isEmpty(chineseCharacter)){
            return null;
        }

        char[] chs = chineseCharacter.toCharArray();

        StringBuilder result = new StringBuilder();

        for(int i=0;i<chs.length;i++){
            String[] arr = chineseToPinYin(chs[i]);
            if(arr==null){
                result.append("");
            }else if(arr.length==1){
                result.append(arr[0].charAt(0));
            }else if(arr[0].equals(arr[1])){
                result.append(arr[0].charAt(0));
            }else{

                String prim = chineseCharacter.substring(i, i+1);

                String lst = null,rst = null;

                if(i<=chineseCharacter.length()-2){
                    rst = chineseCharacter.substring(i,i+2);
                }
                if(i>=1 && i+1<=chineseCharacter.length()){
                    lst = chineseCharacter.substring(i-1,i+1);
                }

                String answer = null;
                for (String py : arr) {

                    if(StringUtils.isEmpty(py)){
                        continue;
                    }

                    if((lst!=null && py.equals(dictionary.get(lst))) ||
                            (rst!=null && py.equals(dictionary.get(rst)))){
                        answer = py;
                        break;
                    }

                    if(py.equals(dictionary.get(prim))){
                        answer = py;
                    }
                }
                if(answer!=null){
                    result.append(answer.charAt(0));
                }else{
                }
            }
        }
        return result.toString().toLowerCase();
    }

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        System.out.println(PinYinUtil.chineseToPinYin("厦门市"));
        System.out.println(PinYinUtil.chineseToPinYinSToLowerCase("厦门市"));
        System.out.println(PinYinUtil.chineseToPinYinSToUpperCase("厦门市"));
    }
}
