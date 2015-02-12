package com.tz.tpcs.util;

import com.tz.tpcs.entity.Clazz;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 叶加飞
 * @version 1.0
 * @since 2015/2/11 13:14
 *
 * 此类用来导入班级模板excel中的数据,日志输出的内容中会记录哪些行或列的值存在问题。
 */
public final class ImportClazzPOI {

    /** log4j的日志器 */
    private static final Logger LOGGER = Logger.getLogger(ImportClazzPOI.class);

    /*** 工具类应该私有化构造 */
    private ImportClazzPOI(){}

    /*****
     * 根据给定的模板文件名来读取数据
     * @param fileName
     * @return 读取的班级数据集
     */
    public static List<Clazz> readFromExcel(String fileName){
        //1.获取文件的输入流: 以当前的classpath路径为参考路径
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if(null == in){
            LOGGER.error("文件不存在："+fileName);
            return null;
        }
        //对象变量申明
        List<Clazz> clazzList = new ArrayList<>();
        //用来保存工作表
        Workbook wb = null;
        //用来保存当前列, 从第2列开始[第1列的下标是0]
        int idx = 1;
        try{
            //2.根据文件流创建WorkBook
            wb = WorkbookFactory.create(in);
            //3.从工作表中获取 sheet
            Sheet sheet = wb.getSheetAt(0);
            //查看此sheet的起始行及最大行
            int startRow = sheet.getFirstRowNum();
            int endRow = sheet.getLastRowNum();
            LOGGER.info("此sheet的起始行："+startRow+",最大行："+endRow);
            int rowNum;
            Clazz instance = null;
            Cell cell = null;
            //4. 迭代这个 sheet中的所有行
            for(Row row : sheet){
                //处理行
                rowNum = row.getRowNum();
                //第0行跳过,因为它是表头，不是我们需要的数据
                if(rowNum == 0){
                    continue;
                }
                LOGGER.debug(">>正在处理第"+rowNum+"行.");
                //创建 Clazz实例， 每行一个实例
                instance = new Clazz();
                //处理我们需要的单元格
                idx = 1; //每行开始，此变量都要重置为1
                //班级名,1
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String cName = cell.getStringCellValue();
                        instance.setName(cName);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //教室名称,2
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String room = cell.getStringCellValue();
                        instance.setRoom(room);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //开班日期,3
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        //进一步判断是否是日期
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date open = cell.getDateCellValue();
                            instance.setOpen(open);
                        }else{
                            LOGGER.error("第" + rowNum + "行3列 单元格内的格式不是合法的日期格式，此值被忽略.");
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //开班人数,4
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        int count = (int) cell.getNumericCellValue();
                        instance.setCount(count);
                    }else{
                        LOGGER.error("第"+rowNum+"行4列 单元格内的数据不是合法的数字，此值被忽略.");
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //获取班主任,5
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String advisor = cell.getStringCellValue();
                        instance.setAdvisor(advisor);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //训练营日期,6
                idx++;
                cell = row.getCell(idx, Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        //进一步判断是否是合法日期
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Date trainingDate = cell.getDateCellValue();
                            instance.setTrainingDate(trainingDate);
                        }else{
                            LOGGER.error("第"+rowNum+"行6列 单元格内的格式不是合法的日期格式，此值被忽略.");
                        }
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                //训练营讲师,7
                idx++;
                cell = row.getCell(idx,Row.RETURN_BLANK_AS_NULL);
                if(cell != null) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String lecturer = cell.getStringCellValue();
                        instance.setLecturer(lecturer);
                    }
                }else{
                    LOGGER.warn("第"+rowNum+"行"+idx+"列 单元格的值为NULL");
                }
                LOGGER.debug("<<第"+rowNum+"行结束");
                //把实例添加到集合中, 注：把没有填写班级的学员过滤掉
                if(null != instance.getName() && instance.getName().trim().length() > 0) {
                    clazzList.add(instance);
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
        return clazzList;
    }
}
