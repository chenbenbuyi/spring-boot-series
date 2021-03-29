package cnblogs.chenbenbuyi;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.StopWatch;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * @author chen
 * @date 2021/3/28 20:17
 * 对于 Excel 表的操作，可以将实际的 Excel简单的理解为四大对象
 * 工作簿：Workbook
 * 工作表: Sheet
 * 行: Row
 * 单元格：Cell
 * 面向对象的 Java 对Excel的操作，实际就是对几个抽象对象的操作
 */

public class ExcelTest {


    /**
     * poi {@link Workbook} 为顶级接口，下面有针对 03和 07两个版本的实现类{@link HSSFWorkbook} 和 {@link XSSFWorkbook}以及升级版本(super)的XSSF {@link SXSSFWorkbook}等
     */
    @Test
    public void simpleWrite() throws Exception {
        URL url = ExcelTest.class.getClassLoader().getResource("");

        Workbook workbook = new HSSFWorkbook();
//        Workbook workbook = new XSSFWorkbook(); 07 版本 ，后缀不同哟
        Sheet sheet = workbook.createSheet("陈本布衣");
        Row row = sheet.createRow(0);
        Row row1 = sheet.createRow(1);
        Cell cell = row.createCell(0);
        // 以上对象的创建就对应的excel的首行的第一格，其它类推
        cell.setCellValue("电影名称");

        Cell cell1 = row.createCell(1);
        // 以上对象的创建就对应的excel的首行的第一格，其它类推
        cell1.setCellValue("上映时间");

        row1.createCell(0).setCellValue("变形金刚10");
        row1.createCell(1).setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));


        // 通过流进行写出
        FileOutputStream fos = new FileOutputStream(url.getPath() + "陈本布衣.xls");
        workbook.write(fos);
        fos.close();
    }


    /**
     * 测试大数据量的耗时——HSSFWorkbook
     * 特别注意：对于 03版的excel（HSSFWorkbook对象操控）最大行数限制为 65535，超出最大行数将会抛出异常——
     * java.lang.IllegalArgumentException: Invalid row number (65536) outside allowable range (0..65535)
     */
    @Test
    public void HSSFWorkbookWrite() throws IOException {
        URL url = ExcelTest.class.getClassLoader().getResource("");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("HSSFWorkbook");
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream(url.getPath() + "陈本布衣.xls");
        workbook.write(fos);
        fos.close();
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println("累计耗时：" + totalTimeSeconds + " 秒");
    }

    /**
     * 测试大数据量的耗时——XSSFWorkbook
     * 在该测试项中，65535行数据100列的情况下，测试竟然报出了oom——
     * java.lang.OutOfMemoryError: Java heap space
     * 这是 poi 内存消耗的老问题，因为 poi会先将所有数据加载到内存，再写入磁盘，这也是后面 EasyExcel针对改进的地方
     * 经测试对比： HSSFWorkbook 和 XSSFWorkbook 在写入同样数据量——65535行 10列的时间消耗分别为 3秒和 12秒，可以看出，XSSFWorkbook的效率着实不怎么样，但其有点就是不限制行数——只要内存够
     */
    @Test
    public void XSSFWorkbookWrite() throws IOException {
        URL url = ExcelTest.class.getClassLoader().getResource("");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("HSSFWorkbook");
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream(url.getPath() + "陈本布衣.xlsx");
        workbook.write(fos);
        fos.close();
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println("累计耗时：" + totalTimeSeconds + " 秒");
    }

    /**
     * 测试大数据量的耗时——SXSSFWorkbook
     * Streaming version of XSSFWorkbook implementing the "BigGridDemo" strategy.  This allows to write very large files without running out of memory as only a configurable portion of the rows are kept in memory at any one time.
     * SXSSFWorkbook 是大数据量的流式版本的XSSFWorkbook,占用更少内存(在初始化SXSSFWorkbook实例时，需要填写一个缓冲行数参数，默认100行。经测试，在 相同数据量 65535行 100列，XSSFWorkbook会导致 oom,SXSSFWorkbook不会)，速度也更快
     * 针对 XSSFWorkbook 操作慢的问题，可以使用 SXSSFWorkbook 对象代替
     */
    @Test
    public void SXSSFWorkbookWrite() throws IOException {
        URL url = ExcelTest.class.getClassLoader().getResource("");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("SXSSFWorkbook");
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 100; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream(url.getPath() + "陈本布衣s.xlsx");
        workbook.write(fos);
        fos.close();
        // 清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();
        System.out.println("累计耗时：" + totalTimeSeconds + " 秒");
    }


    @Test
    public void HXSSFWorkbookRead() throws IOException {
        URL url = ExcelTest.class.getClassLoader().getResource("");
        FileInputStream fis = new FileInputStream(new File(url.getPath() + "test.xlsx"));
        // 从输入流获取工作簿对象，具体操作对象针对不同版本而定，这里以07版为演示
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        //先获取表头
        Row rowHead = sheet.getRow(0);
        int cellCount = 0;
        if (rowHead != null) {
            cellCount = rowHead.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                Cell cell = rowHead.getCell(cellNum);
                if (cell != null) {
                    String cellVal = cell.getStringCellValue();
                    System.out.print(cellVal + " | ");
                }
            }
        }
        System.out.println();
        // 获取内容 注意数据类型的转换
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {
                for (int cellNum = 0; cellNum < cellCount; cellNum++) {
                    Cell cellData = rowData.getCell(cellNum);
                    if (cellData != null) {
                        CellType cellType = cellData.getCellTypeEnum();
                        String cellVal = "";
                        switch (cellType) {
                            case STRING:
                                cellVal = cellData.getStringCellValue();
                                break;
                            case BOOLEAN:
                                cellVal = String.valueOf(cellData.getBooleanCellValue());
                                break;
                            case BLANK:
                                System.out.print("第" + rowNum + "行第" + cellNum + "列没有值");
                                break;
                            case NUMERIC:
                                // 判断日期类型
                                if (HSSFDateUtil.isCellDateFormatted(cellData)) {
                                    cellVal = DateUtil.format(cellData.getDateCellValue(), "yyyy-MM-dd");
                                } else {
                                    cellData.setCellType(CellType.STRING);
                                    cellVal = cellData.toString();
                                }
                                break;
                            case ERROR:
                                System.out.println("第" + rowNum + "行第" + cellNum + "列类型转换出错");
                                break;
                            default:
                                break;
                        }
                        System.out.print(cellVal+" | ");
                    }
                }
                System.out.println();
            }
        }
        fis.close();
    }
}
