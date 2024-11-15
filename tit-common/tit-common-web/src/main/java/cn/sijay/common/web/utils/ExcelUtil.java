package cn.sijay.common.web.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.IdUtil;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.exception.UtilException;
import cn.sijay.common.json.utils.JsonUtil;
import cn.sijay.common.office.convert.ExcelBigNumberConvert;
import cn.sijay.common.office.util.BaseExcelUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.fill.FillWrapper;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * <strong>ExcelUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-15
 */
@Slf4j
public class ExcelUtil extends BaseExcelUtil {
    public static <T> void export(List<T> list, String name, Class<T> clazz, HttpServletResponse response) {
        exportExcel(list, "项目分包合同", clazz, response);

    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param response  响应体
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, false, os, null);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param merge     是否合并单元格
     * @param response  响应体
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, merge, os, null);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param response  响应体
     * @param options   级联下拉选
     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, HttpServletResponse response, List<DropDownOptions> options) {
//        try {
//            resetResponse(sheetName, response);
//            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, false, os, options);
//        } catch (IOException e) {
//            throw new RuntimeException("导出Excel异常");
//        }
//    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param os        输出流
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, OutputStream os) {
//        exportExcel(list, sheetName, clazz, false, os, null);
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param merge     是否合并单元格
     * @param response  响应体
     * @param options   级联下拉选
     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, HttpServletResponse response, List<DropDownOptions> options) {
//        try {
//            resetResponse(sheetName, response);
//            ServletOutputStream os = response.getOutputStream();
//            exportExcel(list, sheetName, clazz, merge, os, options);
//        } catch (IOException e) {
//            throw new RuntimeException("导出Excel异常");
//        }
//    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param merge     是否合并单元格
     * @param os        输出流
     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, OutputStream os, List<DropDownOptions> options) {
//        ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
//                                                   .autoCloseStream(false)
//                                                   // 自动适配
//                                                   .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
//                                                   // 大数值自动转换 防止失真
//                                                   .registerConverter(new ExcelBigNumberConvert())
//                                                   .sheet(sheetName);
//        if (merge) {
//            // 合并处理器
//            builder.registerWriteHandler(new CellMergeStrategy(list, true));
//        }
//        // 添加下拉框操作
//        builder.registerWriteHandler(new ExcelDownHandler(options));
//        builder.doWrite(list);
//    }
    public static <T> void exportTemplate(String name, Class<T> clazz, HttpServletResponse response) {
        try {
            EasyExcel.write(response.getOutputStream(), clazz)
                     .autoCloseStream(false)
                     // 自动适配
                     .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                     .sheet(name).doWrite(new ArrayList<>());
        } catch (IOException e) {
            throw new UtilException(ExceptionEnum.EXCEL_TEMPLATE_EXPORT_ERROR);
        }
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param os        输出流
     * @param options   级联下拉选内容
     */
//    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, OutputStream os, List<DropDownOptions> options) {
//        exportExcel(list, sheetName, clazz, false, os, options);
//    }
    public static <T> List<T> read(MultipartFile file, Class<T> clazz) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            return convertList(readSheet(workbook.getSheetAt(0)), clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.EXCEL_READ_ERROR);
        }
    }

    /**
     * 同步导入(适用于小数据量)
     *
     * @param is 输入流
     * @return 转换后集合
     */
    public static <T> List<T> importExcel(InputStream is, Class<T> clazz) {
        return EasyExcel.read(is).head(clazz).autoCloseStream(false).sheet().doReadSync();
    }

    /**
     * 单表多数据模板导出 模板格式为 {.属性}
     *
     * @param filename     文件名
     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
     *                     例如: excel/temp.xlsx
     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
     * @param data         模板需要的数据
     * @param response     响应体
     */
    public static void exportTemplate(List<Object> data, String filename, String templatePath, HttpServletResponse response) {
        try {
            resetResponse(filename, response);
            ServletOutputStream os = response.getOutputStream();
            exportTemplate(data, templatePath, os);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 使用校验监听器 异步导入 同步返回
     *
     * @param is         输入流
     * @param clazz      对象类型
     * @param isValidate 是否 Validator 检验 默认为是
     * @return 转换后集合
     */
//    public static <T> ExcelResult<T> importExcel(InputStream is, Class<T> clazz, boolean isValidate) {
//        DefaultExcelListener<T> listener = new DefaultExcelListener<>(isValidate);
//        EasyExcel.read(is, clazz, listener).sheet().doRead();
//        return listener.getExcelResult();
//    }

    /**
     * 使用自定义监听器 异步导入 自定义返回
     *
     * @param is       输入流
     * @param clazz    对象类型
     * @param listener 自定义监听器
     * @return 转换后集合
     */
//    public static <T> ExcelResult<T> importExcel(InputStream is, Class<T> clazz, ExcelListener<T> listener) {
//        EasyExcel.read(is, clazz, listener).sheet().doRead();
//        return listener.getExcelResult();
//    }

    /**
     * 单表多数据模板导出 模板格式为 {.属性}
     *
     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
     *                     例如: excel/temp.xlsx
     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
     * @param data         模板需要的数据
     * @param os           输出流
     */
    public static void exportTemplate(List<Object> data, String templatePath, OutputStream os) {
        ClassPathResource templateResource = new ClassPathResource(templatePath);
        ExcelWriter excelWriter = EasyExcel.write(os)
                                           .withTemplate(templateResource.getStream())
                                           .autoCloseStream(false)
                                           // 大数值自动转换 防止失真
                                           .registerConverter(new ExcelBigNumberConvert())
                                           .build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        if (CollUtil.isEmpty(data)) {
            throw new IllegalArgumentException("数据为空");
        }
        // 单表多数据导出 模板格式为 {.属性}
        for (Object d : data) {
            excelWriter.fill(d, writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 多表多数据模板导出 模板格式为 {key.属性}
     *
     * @param filename     文件名
     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
     *                     例如: excel/temp.xlsx
     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
     * @param data         模板需要的数据
     * @param response     响应体
     */
    public static void exportTemplateMultiList(Map<String, Object> data, String filename, String templatePath, HttpServletResponse response) {
        try {
            resetResponse(filename, response);
            ServletOutputStream os = response.getOutputStream();
            exportTemplateMultiList(data, templatePath, os);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 多表多数据模板导出 模板格式为 {key.属性}
     *
     * @param templatePath 模板路径 resource 目录下的路径包括模板文件名
     *                     例如: excel/temp.xlsx
     *                     重点: 模板文件必须放置到启动类对应的 resource 目录下
     * @param data         模板需要的数据
     * @param os           输出流
     */
    public static void exportTemplateMultiList(Map<String, Object> data, String templatePath, OutputStream os) {
        ClassPathResource templateResource = new ClassPathResource(templatePath);
        ExcelWriter excelWriter = EasyExcel.write(os)
                                           .withTemplate(templateResource.getStream())
                                           .autoCloseStream(false)
                                           // 大数值自动转换 防止失真
//                                           .registerConverter(new ExcelBigNumberConvert())
                                           .build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        if (CollUtil.isEmpty(data)) {
            throw new IllegalArgumentException("数据为空");
        }
        for (Map.Entry<String, Object> map : data.entrySet()) {
            // 设置列表后续还有数据
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            if (map.getValue() instanceof Collection) {
                // 多表导出必须使用 FillWrapper
                excelWriter.fill(new FillWrapper(map.getKey(), (Collection<?>) map.getValue()), fillConfig, writeSheet);
            } else {
                excelWriter.fill(map.getValue(), writeSheet);
            }
        }
        excelWriter.finish();
    }

    /**
     * 重置响应体
     */
    private static void resetResponse(String sheetName, HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = encodingFilename(sheetName);
        FileNameUtil.setAttachmentResponseHeader(response, filename);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
    }

    /**
     * 编码文件名
     */
    public static String encodingFilename(String filename) {
        return IdUtil.fastSimpleUUID() + "_" + filename + ".xlsx";
    }

    /**
     * 导出excel
     *
     * @param list      导出数据集合
     * @param sheetName 工作表的名称
     * @param clazz     实体类
     * @param merge     是否合并单元格
     * @param response  响应体
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, boolean merge, int rowIndex, HttpServletResponse response) {
        try {
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
            ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
                                                       .autoCloseStream(false)
                                                       // 自动适配
                                                       .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                                                       // 大数值自动转换 防止失真
//                                                       .registerConverter(new ExcelBigNumberConvert())
                                                       .sheet(sheetName);
            if (merge) {
                // 合并处理器
//                builder.registerWriteHandler(new CellMergeStrategy(list, true, rowIndex));
            }
            builder.doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }

    /**
     * 导出excel
     */
    public static <T> void exportExcel(List<T> list, String sheetName, Class<T> clazz, int rowIndex, Set<String> excludeColumnFieldNames, HttpServletResponse response) {
        try {
            log.info(JsonUtil.toJsonString(excludeColumnFieldNames));
            resetResponse(sheetName, response);
            ServletOutputStream os = response.getOutputStream();
            ExcelWriterSheetBuilder builder = EasyExcel.write(os, clazz)
                                                       .autoCloseStream(false)
                                                       // 自动适配
                                                       .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                                                       // 大数值自动转换 防止失真
//                                                       .registerConverter(new ExcelBigNumberConvert())
                                                       .excludeColumnFieldNames(excludeColumnFieldNames)
                                                       .sheet(sheetName);
//            builder.registerWriteHandler(new CellMergeStrategy(list, true, rowIndex));
            builder.doWrite(list);
        } catch (IOException e) {
            throw new RuntimeException("导出Excel异常");
        }
    }
}
