package tpcs.test.json;

import com.alibaba.fastjson.JSON;
import com.tz.tpcs.entity.Resources;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/24 12:28
 */
public class TestJson {

    @Test
    public void test01ObjectToJson(){
        List<Resources> list = new ArrayList<>();
        //客户管理
        Resources r1 = new Resources("班级", "menu_clazz", Resources.Type.FOLDER, "", null, null,1,true);
        Resources r11 = new Resources("班级列表", "menu_clazz_list", Resources.Type.URL, "/clazz/list", r1, null,11,true);
        Resources r12 = new Resources("添加班级", "menu_clazz_initAdd", Resources.Type.URL, "/clazz/initAdd", r1, null,12,false);
        Resources r13 = new Resources("导入班级", "menu_clazz_import", Resources.Type.URL, "/clazz/initImport", r1, null,13,true);
        Resources r14 = new Resources("班级活动", "menu_clazz_campaign", Resources.Type.URL, "/clazzCampaign/list", r1, null,14,true);
        list.add(r1);
        list.add(r11);
        list.add(r12);
        list.add(r13);
        list.add(r14);

        Resources r2 = new Resources("学员", "menu_student", Resources.Type.FOLDER, "", null, null,2,true);
        Resources r21 = new Resources("学员列表", "menu_student_list", Resources.Type.URL, "/students/list", r2, null,21,true);
        Resources r22 = new Resources("导入学员", "menu_student_import", Resources.Type.URL, "/students/initImport", r2, null,22,true);
        list.add(r2);
        list.add(r21);
        list.add(r22);

        Resources r3 = new Resources("知识库", "menu_knowledge", Resources.Type.FOLDER, "", null, null,3,true);
        Resources r31 = new Resources("课程列表", "menu_course_list", Resources.Type.URL, "/knowledge/list", r3, null,31,true);
        Resources r32 = new Resources("查看课程", "menu_view_course", Resources.Type.URL, "/knowledge/initView", r3, null,32,true);
        Resources r33 = new Resources("知识中心", "menu_knowledge_center", Resources.Type.URL, "/knowledge/initCenter", r3, null,33,true);
        list.add(r3);
        list.add(r31);
        list.add(r32);
        list.add(r33);

        Resources r4 = new Resources("项目案例", "menu_project", Resources.Type.FOLDER, "", null, null,4,true);
        Resources r41 = new Resources("项目列表", "menu_project_list", Resources.Type.URL, "/projects/list", r4, null,41,true);
        Resources r42 = new Resources("分配项目", "menu_assign_project", Resources.Type.URL, "/projects/initAssign", r4, null,42,true);
        Resources r43 = new Resources("进行中的项目", "menu_project_pending", Resources.Type.URL, "/projects/initPending", r4, null,43,true);
        Resources r44 = new Resources("项目追踪", "menu_track", Resources.Type.URL, "/knowledge/initTrack", r4, null,44,true);
        list.add(r4);
        list.add(r41);
        list.add(r42);
        list.add(r43);
        list.add(r44);

        Resources r5 = new Resources("员工", "menu_employee", Resources.Type.FOLDER, "", null, null,5,true);
        Resources r51 = new Resources("员工列表", "menu_employee_list", Resources.Type.URL, "/employees/list", r5, null,51,true);
        Resources r52 = new Resources("新增员工", "menu_add_employee", Resources.Type.URL, "/employees/initAdd", r5, null,52,true);
        Resources r53 = new Resources("通讯录", "menu_employee_addressBook", Resources.Type.URL, "/employees/addressBook", r5, null,53,true);
        list.add(r5);
        list.add(r51);
        list.add(r52);
        list.add(r53);

        String str = JSON.toJSONString(list);
        System.out.println(str);
        try {
            FileOutputStream fos= new FileOutputStream(new File("d:/resources.json"));
            PrintWriter out = new PrintWriter(fos);
            out.println(str);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02JsonToObject() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("init/resources.json");
        String jsonStr = JsonUtil.getJsonFromResource(in);
//        System.out.println(jsonStr);
        List<Resources> list = JSON.parseArray(jsonStr, Resources.class);
        System.out.println(list);
    }

    @Test
    public void test03String() throws IOException {
//        String s = "《终结者》（The Terminator）是一部1984年上映的美国科幻动作片，由詹姆斯·卡梅隆执导，阿诺德·施瓦辛格、迈克尔·比恩和琳达";
        String s = "tjqevznxclmndaawethajnahzxgxzzdurifeubrhhqajaiohnjurnrlpcjxfarpbqzqaph";
        System.out.println(s.length());
    }

}
