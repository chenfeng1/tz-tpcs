package com.tz.tpcs.util;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Gender;
import com.tz.tpcs.entity.Student;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 叶加飞
 * @version 1.0
 * @since 2015/2/11 14:30
 *
 * 此类用来导入学员模板excel中的数据,日志输出的内容中会记录哪些行或列的值存在问题。
 */
public final class ImportStudentPOI {

    /** log4j的日志器 */
    private static Logger LOGGER = Logger.getLogger(ImportStudentPOI.class);

    /*****
     * 根据给定的模板文件名来读取数据
     * @param fileName
     * @return 读取的学员数据集
     */
    public static List<Student> readFromExcel(String fileName){
        //1.获取文件的输入流: 以当前的classpath路径为参考路径
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if(null == in){
            LOGGER.error("文件不存在："+fileName);
            return null;
        }
        //对象变量申明
        List<Student> stuList = new ArrayList<>();
        //用来格式化科学计数法的数字对象
        DecimalFormat df = new DecimalFormat("#");
        Workbook wb = null;
        try{
            //2.根据文件流创建WorkBook
            wb = WorkbookFactory.create(in);
            //3.从工作表中获取 sheet
            Sheet sheet = wb.getSheetAt(0);
            //查看此sheet的起始行及最大行
            int startRow = sheet.getFirstRowNum();
            int endRow = sheet.getLastRowNum();
            LOGGER.info("此sheet的起始行："+startRow+",最大行："+endRow);
            //定义列下标索引变量
            int idx = 1; //第0列我们不考虑
            int type; //用来保存当前单元格的类型
            //4. 迭代这个 sheet中的所有行
            for(Row row : sheet){
                //处理行
                int rowNum = row.getRowNum();
                //第0行跳过,因为它是表头，不是我们需要的数据
                if(rowNum == 0){
                    continue;
                }
                LOGGER.debug(">>正在处理第"+rowNum+"行.");
                //创建 Clazz实例， 每行一个实例
                Student instance = new Student();
                //处理我们需要的单元格
                idx = 1; //每行开始，此变量都要重置为1
                //班级名,1
                Cell cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cName = cell.getStringCellValue();
                        //先创建一个 Clazz对象，再把name属性设置进去
                        Clazz c = new Clazz();
                        c.setName(cName);
                        //把此班级绑定到学员上
                        instance.setClazz(c);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //学员名,2
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String realName = cell.getStringCellValue();
                        instance.setRealname(realName);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //生日,3
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        //进一步判断是否是日期
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date birth = cell.getDateCellValue();
                            instance.setBirthDate(birth);
                        }else{
                            LOGGER.error("第" + rowNum + "行"+idx+"列 单元格内的格式不是合法的日期格式，此值被忽略.");
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //手机号,4
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    type = cell.getCellType();
                    //有可能是数字
                    if (Cell.CELL_TYPE_NUMERIC == type) {
                        double phone = cell.getNumericCellValue();
                        //读出来是科学计数法的格式，利用 NumberFormat格式化成字符串
                        instance.setPhone(df.format(phone));
                    }else if(Cell.CELL_TYPE_STRING == type) {
                        //有可能是字符
                        instance.setPhone(cell.getStringCellValue());
                    }

                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //备用号,5
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    type = cell.getCellType();
                    //有可能是数字
                    if (Cell.CELL_TYPE_NUMERIC == type) {
                        double bakPhone = cell.getNumericCellValue();
                        //格式化为非科学计数的字符串形式
                        instance.setBakPhone(df.format(bakPhone));
                    }else if(Cell.CELL_TYPE_STRING == type) {
                        //有可能是字符
                        instance.setBakPhone(cell.getStringCellValue());
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //邮箱,6
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String email = cell.getStringCellValue();
                        instance.setEmail(email);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //院校,7
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String school = cell.getStringCellValue();
                        instance.setSchool(school);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //专业，8
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String major = cell.getStringCellValue();
                        instance.setMajor(major);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //学历，9
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String degree = cell.getStringCellValue();
                        //把此字符串转换成枚举常量
                        Degree d = Degree.convert(degree);
                        instance.setDegree(d);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //贷款状态，10
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String loadStatus = cell.getStringCellValue();
                        //把此字符串转换成枚举常量
                        instance.setLoanStatus(Student.LoanStatus.convert(loadStatus));
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //合同签约日期,11
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        //进一步判断是否是合法日期
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date loanDate = cell.getDateCellValue();
                            instance.setLoanDate(loanDate);
                        }else{
                            LOGGER.error("第"+rowNum+"行"+idx+"列 单元格内的格式不是合法的日期格式，此值被忽略.");
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //渠道来源，12
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String source = cell.getStringCellValue();
                        //转换成枚举后再设置
                        instance.setSource(Student.Source.convert(source));
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //qq号,13
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    type = cell.getCellType();
                    //有可能有是数字
                    if (Cell.CELL_TYPE_NUMERIC == type) {
                        double qq = cell.getNumericCellValue();
                        //读出来是科学计数法的格式，利用 NumberFormat格式化成字符串
                        instance.setQq(df.format(qq));
                    }else {
                        if (Cell.CELL_TYPE_STRING == type) {
                            //有可能是字符
                            instance.setQq(cell.getStringCellValue());
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //身份证,14
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    type = cell.getCellType();
                    //如果是数字型
                    if (Cell.CELL_TYPE_NUMERIC == type) {
                        double identityCard = cell.getNumericCellValue();
                        //读出来是科学计数法的格式，利用 NumberFormat格式化成字符串
                        instance.setIdentityCard(df.format(identityCard));
                    }else if(Cell.CELL_TYPE_STRING == type){
                        instance.setIdentityCard(cell.getStringCellValue());
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //省份,15
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String province = cell.getStringCellValue();
                        instance.setProvince(province);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //城市,16
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String city = cell.getStringCellValue();
                        instance.setCity(city);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //当前所在地,17
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String currentLoc = cell.getStringCellValue();
                        instance.setCurrentLoc(currentLoc);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //工作地,18
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String workLoc = cell.getStringCellValue();
                        instance.setWorkLoc(workLoc);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //工作年限，19
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                        double workingYears = cell.getNumericCellValue();
                        instance.setWorkingYears((int)workingYears);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }

                //毕业时间,20
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                        //进一步判断是否为日期
                        if(DateUtil.isCellDateFormatted(cell)){
                            Date graduationDate = cell.getDateCellValue();
                            instance.setGraduationDate(graduationDate);
                        }else{
                            LOGGER.error("第"+rowNum+"行"+idx+"列 单元格内的格式不是合法的日期格式，此值被忽略.");
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }

                //是否需要毕设，21
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){
                        boolean needDesign = cell.getBooleanCellValue();
                        instance.setNeedDesign(needDesign);
                    }else{
                        LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值类型不是布尔值，将被忽略.");
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }

                //毕设时间,22
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                        //进一步判断是否为日期
                        if(DateUtil.isCellDateFormatted(cell)){
                            Date designDate = cell.getDateCellValue();
                            instance.setDesignDate(designDate);
                        }else{
                            LOGGER.error("第"+rowNum+"行"+idx+"列 单元格内的格式不是合法的日期格式，此值被忽略.");
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //紧急联系人,23
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String emergencyContact = cell.getStringCellValue();
                        instance.setEmergencyContact(emergencyContact);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //紧急联系人电话，24
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    type = cell.getCellType();
                    //有可能是数字
                    if (Cell.CELL_TYPE_NUMERIC == type) {
                        double emergencyPhone = cell.getNumericCellValue();
                        //读出来是科学计数法的格式，利用 NumberFormat格式化成字符串
                        instance.setEmergencyPhone(df.format(emergencyPhone));
                    }else if(Cell.CELL_TYPE_STRING == type) {
                        //有可能是字符
                        instance.setEmergencyPhone(cell.getStringCellValue());
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //性别，25
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null){
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String gender = cell.getStringCellValue();
                        //把 gender 转换成枚举常量
                        instance.setGender(Gender.convert(gender));
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //备注，26
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String remark = cell.getStringCellValue();
                        instance.setRemark(remark);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //
                LOGGER.debug("<<第"+rowNum+"行结束");
                //把实例添加到集合中, 注：把没有填写班级的学员过滤掉
                if(null != instance.getRealname() && instance.getRealname().trim().length() > 0) {
                    stuList.add(instance);
                }else{
                    LOGGER.warn("存在无效行，有可能是Excel本身引起的，也有可能是你的写的数据不全,please check it again!");
                }
            }
        }catch(Exception e){
            LOGGER.error("处理Excel文件失败",e);
        } finally {
            if(wb != null){
                try{
                    wb.close();
                    LOGGER.debug("释放 Workbook资源成功.");
                }catch(IOException ee){
                    LOGGER.error("释放 Workbook资源失败.",ee);
                }
            }
        }
        //返回结果
        return stuList;
    }
}
