package tpcs.test.util;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.util.ImportClazzPOI;
import com.tz.tpcs.util.ImportStudentPOI;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2015/2/11.
 */
public class TestImportStudentPOI {

    @Test
    public void testReadFromExcel(){
//        String filename = "excel/student.xlsx";
        String filename = "excel/student2.xls";
        List<Student> stuList = ImportStudentPOI.readFromExcel(filename);
        if(stuList != null){
            System.out.println(stuList.size());
            for(Student s : stuList){
                System.out.println(s);
                System.out.println("所在班级："+s.getClazz().getName());
            }
        }
    }
}
