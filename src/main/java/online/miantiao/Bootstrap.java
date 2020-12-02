package online.miantiao;

import online.miantiao.utils.ParseAndGetGPX;

/**
 * @author: miantiao
 * @date: 2020/12/1
 */
public class Bootstrap {
    public static void main(String[] args) {
        if(args.length == 0 || args[0] == null){
            System.out.println("Please run the JAR with a TXT file path!");
            return;
        }
        if(!args[0].endsWith(".txt")){
            System.out.println("Please run the JAR with a TXT file path!");
            return;
        }
        ParseAndGetGPX.readTXT(args[0]);
    }


}
