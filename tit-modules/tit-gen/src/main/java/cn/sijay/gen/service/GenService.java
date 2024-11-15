package cn.sijay.gen.service;

import cn.sijay.common.core.constant.Constants;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.enums.GenFileType;
import cn.sijay.common.core.exception.BaseException;
import cn.sijay.common.core.utils.FileUtil;
import cn.sijay.common.core.utils.StringUtil;
import cn.sijay.common.mybatis.dictionary.*;
import cn.sijay.common.template.utils.VelocityUtil;
import cn.sijay.gen.constants.GenConstants;
import cn.sijay.gen.entity.GenTable;
import cn.sijay.gen.entity.GenTableColumn;
import cn.sijay.gen.properties.GenProperties;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <strong>GenService</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenService {
    private final IGenTableService genTableService;
    private final IGenTableColumnService genTableColumnService;
    private final GenProperties genProperties;

    public HashMap<String, String> preview(Long id) {
        GenTable genTable = genTableService.getById(id);
        setExtAttr(genTable);
        VelocityUtil.initVelocity();
        VelocityContext context = prepareContext(genTable);
        HashMap<String, String> result = new HashMap<>();
        for (String template : genFileTypeMap().keySet()) {
            result.put(StringUtil.toLowerCamelCase(template.replace(".vm", "")), VelocityUtil.render(template, context));
        }
        return result;
    }

    public void generate(Long id) {
        GenTable genTable = genTableService.getById(id);
        setExtAttr(genTable);
        VelocityUtil.initVelocity();
        VelocityContext context = prepareContext(genTable);
        genFileTypeMap().forEach((template, genFileType) -> {
            String fileName = template.split("\\.")[0];
            final String rootPath = "C:\\Users\\sijay\\code\\sijay\\tit-cloud";
            String path = switch (genFileType) {
                case JAVA -> FileUtil.concatPath(rootPath + "\\tit-modules\\tit-system", genFileType.getGenPath(),
                        genTable.getPackageName().replace(".", File.separator),
                        genTable.getModuleName(), fileName.replace("_", File.separator),
                        getFileName(genTable, fileName, genFileType));
                case XML ->
                        FileUtil.concatPath(rootPath + "\\tit-modules\\tit-system", genFileType.getGenPath(), fileName, genTable.getModuleName(), getFileName(genTable, fileName, genFileType));
                case VUE, JS ->
                        FileUtil.concatPath(rootPath + "\\ui", genFileType.getGenPath(), genTable.getModuleName(), getFileName(genTable, fileName, genFileType));
                case SQL -> "";
            };
            FileUtil.mkDirOrTouch(path);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                fos.write(VelocityUtil.render(template, context).getBytes(StandardCharsets.UTF_8));
            } catch (FileNotFoundException e) {
                throw new BaseException(ExceptionEnum.FILE_NOT_FOUND, path);
            } catch (IOException e) {
                throw new BaseException("渲染模板失败，表名：" + context.get("tableName"));
            }
        });
    }

    public void download(Long id, HttpServletResponse response) {

    }

    private void setExtAttr(GenTable genTable) {
        List<GenTableColumn> fields = genTableColumnService.listByTableId(genTable.getId()).stream().peek(item -> {
            item.setGetterName("get" + StringUtil.toUpperCamelCase(item.getColumnName()));
        }).toList();
        Set<String> imports = fields.stream()
                                    .filter(item -> !item.isSuperColumn())
                                    .map(GenTableColumn::getJavaType)
                                    .map(JavaType::getFullName)
                                    .filter(StringUtil::isNotBlank)
                                    .collect(Collectors.toSet());
        genTable.setImports(imports);
        genTable.setFields(fields);
    }

    private VelocityContext prepareContext(GenTable genTable) {
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("packageName", genTable.getPackageName());
        velocityContext.put("module", genTable.getModuleName());
        velocityContext.put("className", genTable.getClassName());
        velocityContext.put("lowerName", genTable.getBusinessName());
        velocityContext.put("comment", genTable.getFunctionName());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("author", genTable.getAuthor());
        velocityContext.put("createTime", LocalDate.now().toString());
        velocityContext.put("imports", genTable.getImports());
        velocityContext.put("fields", genTable.getFields());
        SuperClassType superClass = genTable.getSuperClass();
        velocityContext.put("superClass", superClass.getFullName());
        velocityContext.put("superClassName", superClass.getClassName());
//        setMenuVelocityContext(velocityContext, genTable);
//        if (GenConstants.TPL_TREE.equals(tplCategory)) {
//            setTreeVelocityContext(velocityContext, genTable);
//        }
        return velocityContext;
    }

    private Map<String, GenFileType> genFileTypeMap() {
        Map<String, GenFileType> map = new HashMap<>();
        map.put("entity.java.vm", GenFileType.JAVA);
        map.put("dto.java.vm", GenFileType.JAVA);
        map.put("controller.java.vm", GenFileType.JAVA);
        map.put("service.java.vm", GenFileType.JAVA);
        map.put("service_impl.java.vm", GenFileType.JAVA);
        map.put("mapper.java.vm", GenFileType.JAVA);
        map.put("mapper.xml.vm", GenFileType.XML);
        map.put("vue.vm", GenFileType.VUE);
        map.put("js.vm", GenFileType.JS);
        return map;
    }

    private String getGenPath(GenTable genTable, String template, GenFileType genFileType, String path) {
        return switch (genFileType) {
            case JAVA -> FileUtil.concatPath(path, genFileType.getGenPath(),
                    genTable.getPackageName().replace(".", File.separator),
                    genTable.getModuleName(), template.replace("_", File.separator),
                    getFileName(genTable, template, genFileType));
            case XML ->
                    FileUtil.concatPath(path, genFileType.getGenPath(), template, genTable.getModuleName(), getFileName(genTable, template, genFileType));
            case VUE, JS ->
                    FileUtil.concatPath(path, genFileType.getGenPath(), genTable.getModuleName(), getFileName(genTable, template, genFileType));
            case SQL -> "";
        };
    }

    private String getFileName(GenTable genTable, String template, GenFileType genFileType) {
        if (Objects.requireNonNull(genFileType) == GenFileType.JAVA) {
            if ("entity".equals(template)) {
                return genTable.getClassName() + Constants.DOT + genFileType.getType();
            } else if ("service".equals(template)) {
                return "I" + genTable.getClassName() + StringUtil.toUpperCamelCase(template) + Constants.DOT + genFileType.getType();
            } else {
                return genTable.getClassName() + StringUtil.toUpperCamelCase(template) + Constants.DOT + genFileType.getType();
            }
        } else if (genFileType == GenFileType.XML) {
            return genTable.getClassName() + "Mapper" + Constants.DOT + genFileType.getType();
        } else if (genFileType == GenFileType.VUE || genFileType == GenFileType.JS || genFileType == GenFileType.SQL) {
            return genTable.getBusinessName() + Constants.DOT + genFileType.getType();
        }
        throw new IllegalArgumentException();
    }

    public HashMap<String, String> update(GenTable genTable) {
        if (genTableService.updateById(genTable)) {
            genTableColumnService.updateBatchById(genTable.getFields());
        }
        return preview(genTable.getId());
    }

    public List<GenTableColumn> listTableColumns(String tableName) {
        return initFields(genTableColumnService.listByTableName(tableName));
    }

    public List<GenTableColumn> listColumnByTableId(Long tableId) {
        return genTableColumnService.listByTableId(tableId).stream().peek(
                item -> {
                    item.setUnmodified(false);
                    if (item.isPrimaryKey() || item.isSuperColumn() || "deleted".equals(item.getColumnName())) {
                        item.setUnmodified(true);
                    }
                }
        ).collect(Collectors.toList());
    }

    public void autoImport() {
        List<GenTable> tables = genTableService.listTable().stream().peek(genTable -> {
            genTable.setTemplateType(TemplateType.LIST);
            genTable.setPackageName(genProperties.getPackageName());
            genTable.setAuthor(genProperties.getAuthor());
            genTable.setGenType(GenType.of(genProperties.getGenType()));
            genTable.setGenPath("/");
            genTable.setFunctionName(genTable.getTableComment().replaceAll("表$", ""));
            List<GenTableColumn> fields = initFields(genTableColumnService.listByTableName(genTable.getTableName()));
            genTable.setImports(fields.stream().map(GenTableColumn::getJavaType).
                                      map(JavaType::getFullName).
                                      filter(StringUtil::isNotBlank)
                                      .collect(Collectors.toSet()));
            Set<String> list = fields.stream().map(GenTableColumn::getColumnName).collect(Collectors.toSet());
            if (list.containsAll(Arrays.asList(GenConstants.BASE_ENTITY_WITH_VERSION_FIELDS))) {
                genTable.setSuperClass(SuperClassType.BASE_ENTITY_WITH_VERSION);
            } else if (list.containsAll(Arrays.asList(GenConstants.BASE_ENTITY_FIELDS))) {
                genTable.setSuperClass(SuperClassType.BASE_ENTITY);
            }
            genTable.setFields(fields);
        }).toList();
        for (GenTable table : tables) {
            boolean save = genTableService.save(table);
            if (save) {
                for (GenTableColumn field : table.getFields()) {
                    field.setTableId(table.getId());
                    genTableColumnService.save(field);
                }
            }

        }

    }

    private List<GenTableColumn> initFields(List<GenTableColumn> columns) {
        List<String> baseEntityFields = Arrays.asList(GenConstants.BASE_ENTITY_WITH_VERSION_FIELDS);
        return columns.stream().peek(item -> {
            item.setGetterName("get" + StringUtil.toUpperCamelCase(item.getColumnName()));
            item.setFieldName(StringUtil.toLowerCamelCase(item.getColumnName()));
            item.setJavaType(switch (item.getDataType()) {
                case "bigint" -> JavaType.LONG;
                case "datetime" -> JavaType.DATE_TIME;
                case "date" -> JavaType.DATE;
                case "time" -> JavaType.TIME;
                case "int" -> JavaType.INTEGER;
                case "decimal" -> JavaType.BIG_DECIMAL;
                case "double" -> JavaType.DOUBLE;
                case "boolean" -> JavaType.BOOLEAN;
                case "json" -> JavaType.OBJECT;
                default -> JavaType.STRING;
            });
            item.setEditable(false);
            item.setQueryable(false);
            item.setAddable(false);
            item.setVisible(false);
            item.setExcelColumn(false);
            item.setUnmodified(true);
            if (!item.isPrimaryKey()) {
                if (baseEntityFields.contains(item.getFieldName())) {
                    item.setSuperColumn(true);
                } else {
                    item.setEditable(true);
                    item.setQueryable(true);
                    item.setAddable(true);
                    item.setSuperColumn(false);
                    item.setVisible(true);
                    item.setExcelColumn(true);
                    item.setUnmodified(false);
                }
            }
            if ("deleted".equals(item.getColumnName())) {
                item.setAddable(false);
                item.setEditable(false);
                item.setExcelColumn(false);
                item.setVisible(false);
                item.setQueryable(false);
                item.setUnmodified(false);
            }
            if (StringUtil.anyMatchIgnoreCase(item.getColumnName(), "sort", "order")) {
                item.setQueryable(false);
            }
            item.setInputType(switch (item.getJavaType()) {
                case LONG, INTEGER, BIG_DECIMAL, DOUBLE -> InputType.NUMBER_INPUT;
                case DATE_TIME -> InputType.DATETIME_PICK;
                case DATE -> InputType.DATE_PICK;
                case TIME -> InputType.TIME_PICK;
                case BOOLEAN -> InputType.SWITCH;
                default -> InputType.TEXT_INPUT;
            });

            item.setQueryType(switch (item.getInputType()) {
                case DATETIME_PICK, DATE_PICK, TIME_PICK -> QueryType.BETWEEN;
                case NUMBER_INPUT, SWITCH -> QueryType.EQUAL;
                default -> QueryType.LIKE;
            });
        }).toList();
    }

    public GenTable getTableById(Long tableId) {
        return genTableService.getById(tableId);
    }
}
