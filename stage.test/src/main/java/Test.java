import java.io.IOException;

/**
 * @author ZMS
 * @Date 2018/10/13 5:53 PM
 */
public class Test {

    public static void main(String[] args) throws IOException {

        //File file = new File("/Users/zms/Downloads/22wid.csv");
        //
        //List<String> list = Files.readAllLines(file.toPath());
        //
        //FileWriter fileWriter = new FileWriter("/Users/zms/Downloads/test_user_600line.data");
        //
        //StringBuilder sb = new StringBuilder();
        //int i = 0;
        //for (String s : list) {
        //    if (sb.length() > 0) {
        //        sb.append(";");
        //    }
        //    sb.append(s);
        //    if (++i % 600 == 0) {
        //        fileWriter.write(sb.toString());
        //        fileWriter.write('\n');
        //        sb.setLength(0);
        //    }
        //}
        //if (sb.length() > 0) {
        //    fileWriter.write(sb.toString());
        //}
        //fileWriter.flush();
        //fileWriter.close();

        //System.out.println(new Date(1539424800000L));

        Double d = new Double(123.456);
        System.out.println(d.longValue());

    }

}
