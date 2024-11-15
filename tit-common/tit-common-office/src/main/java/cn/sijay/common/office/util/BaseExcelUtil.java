package cn.sijay.common.office.util;

import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.StringUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <strong>ExcelUtil</strong>
 * <p>
 * ExcelUtil
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
public class BaseExcelUtil {

    public static <T> List<T> read(String path, Class<T> clazz) {
        return read(new File(path), clazz);
    }

    public static <T> List<T> read(File file, Class<T> clazz) {
        try (Workbook workbook = WorkbookFactory.create(file)) {
            return convertList(readSheet(workbook.getSheetAt(0)), clazz);
        } catch (IOException e) {
            throw new BaseException(ExceptionEnum.EXCEL_READ_ERROR);
        }
    }

    protected static <T> List<T> convertList(List<HashMap<String, String>> list, Class<T> clazz) {
        return list.stream().map(map -> {
            try {
                T t = clazz.getDeclaredConstructor().newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
                    if (annotation != null) {
                        field.setAccessible(true);
                        field.set(t, map.get(annotation.value()));
                    }
                }
                return t;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("{} 实例化失败", clazz.getName());
                return null;
            }
        }).filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
    }

    protected static List<HashMap<String, String>> readSheet(Sheet sheet) {
        HashMap<Integer, String> titleMap = getTitle(sheet);
        return getData(sheet, titleMap);
    }

    protected static HashMap<Integer, String> getTitle(Sheet sheet) {
        HashMap<Integer, String> titleMap = new HashMap<>();
        for (Cell cell : sheet.getRow(0)) {
            titleMap.put(cell.getColumnIndex(), getCellValue(cell));
        }
        return titleMap;
    }

    protected static List<HashMap<String, String>> getData(Sheet sheet, HashMap<Integer, String> titleMap) {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (int j = 1; j <= sheet.getLastRowNum(); j++) {
            HashMap<String, String> map = new HashMap<>();
            for (Cell cell : sheet.getRow(j)) {
                map.put(titleMap.get(cell.getColumnIndex()), getCellValue(cell));
            }
            list.add(map);
        }
        return list;
    }

    protected static String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case NUMERIC -> DateUtil.isCellDateFormatted(cell) ?
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue()) :
                    BigDecimal.valueOf(cell.getNumericCellValue()).toString();
            case STRING -> cell.getRichStringCellValue().getString();
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            case ERROR -> String.valueOf(cell.getErrorCellValue());
            default -> cell.getRichStringCellValue().toString();
        };
    }

    /**
     * 反向解析值 男=0,女=1,未知=2
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String reverseByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(StringUtil.SEPARATOR);
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtil.containsAny(propertyValue, separator)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[1].equals(value)) {
                        propertyString.append(itemArray[0]).append(separator);
                        break;
                    }
                }
            } else {
                if (itemArray[1].equals(propertyValue)) {
                    return itemArray[0];
                }
            }
        }
        return StringUtil.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 解析导出值 0=男,1=女,2=未知
     *
     * @param propertyValue 参数值
     * @param converterExp  翻译注解
     * @param separator     分隔符
     * @return 解析后值
     */
    public static String convertByExp(String propertyValue, String converterExp, String separator) {
        StringBuilder propertyString = new StringBuilder();
        String[] convertSource = converterExp.split(StringUtil.SEPARATOR);
        for (String item : convertSource) {
            String[] itemArray = item.split("=");
            if (StringUtil.containsAny(propertyValue, separator)) {
                for (String value : propertyValue.split(separator)) {
                    if (itemArray[0].equals(value)) {
                        propertyString.append(itemArray[1]).append(separator);
                        break;
                    }
                }
            } else {
                if (itemArray[0].equals(propertyValue)) {
                    return itemArray[1];
                }
            }
        }
        return StringUtil.stripEnd(propertyString.toString(), separator);
    }

}