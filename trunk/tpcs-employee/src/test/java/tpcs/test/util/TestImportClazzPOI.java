package tpcs.test.util;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.util.ImportClazzPOI;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2015/2/11.
 */
public class TestImportClazzPOI {

    @Test
    public void testReadFromExcel(){
        String filename = "excel/class.xlsx";
//        String filename = "excel/class2.xls";
        List<Clazz> cList = ImportClazzPOI.readFromExcel(filename);
        if(cList != null){
            System.out.println(cList.size());
            for(Clazz c : cList){
                System.out.println(c);
            }
        }
    }
}
