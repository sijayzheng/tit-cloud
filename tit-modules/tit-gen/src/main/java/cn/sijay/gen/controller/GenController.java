package cn.sijay.gen.controller;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.entity.Res;
import cn.sijay.common.mybatis.dictionary.OperateType;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.web.base.BaseController;
import cn.sijay.common.web.entity.PageResult;
import cn.sijay.gen.entity.GenTable;
import cn.sijay.gen.entity.GenTableColumn;
import cn.sijay.gen.properties.GenProperties;
import cn.sijay.gen.service.GenService;
import cn.sijay.gen.service.IGenTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <strong>GenController</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Tag(name = "代码生成", description = "/gen")
@RequiredArgsConstructor
@RestController
@RequestMapping
public class GenController extends BaseController {
    private final GenService genService;
    private final GenProperties genProperties;
    private final IGenTableService genTableService;

    /**
     * 预览代码
     */
    @Operation(summary = "预览代码")
    @ApiResponse(responseCode = "200", description = "预览代码")
    @GetMapping("preview")
    public Res<HashMap<String, String>> preview(@RequestParam("id") @Nullable Long id) {
        return success(genService.preview(id));
    }

    /**
     * 生成代码
     */
    @Operation(summary = "生成代码")
    @ApiResponse(responseCode = "200", description = "生成代码")
    @GetMapping("generate")
    public Res<Void> generate(@RequestParam("id") @Nullable Long id) {
        genService.generate(id);
        return success();
    }

    /**
     * 下载代码
     */
    @Operation(summary = "下载代码")
    @ApiResponse(responseCode = "200", description = "下载代码")
    @PostMapping("download")
    public void download(@RequestBody Id<Long> id, HttpServletResponse response) {
        genService.download(id.getId(), response);
    }

    /**
     * 获取代码生成的参数信息
     */
    @Operation(summary = "获取代码生成的参数信息")
    @ApiResponse(responseCode = "200", description = "获取代码生成的参数信息")
    @GetMapping("getGenProperties")
    public Res<GenProperties> getGenProperties() {
        return success(genProperties);
    }

    /**
     * 更新
     */
    @Operation(summary = "更新")
    @ApiResponse(responseCode = "200", description = "更新")
    @PostMapping("update")
    public Res<HashMap<String, String>> update(@RequestBody GenTable genTable) {
        return success(genService.update(genTable));
    }

    /**
     * 获取表的列信息
     */
    @Operation(summary = "获取表的列信息")
    @ApiResponse(responseCode = "200", description = "表的列信息")
    @GetMapping("listTableColumns")
    public Res<List<GenTableColumn>> listTableColumns(@RequestParam("tableName") String tableName) {
        return success(genService.listTableColumns(tableName));
    }

    /**
     * 根据tableId查询
     *
     * @param tableId tableId
     * @return 列信息详细信息
     */
    @Operation(summary = "根据tableId查询")
    @Parameter(name = "tableId", description = "tableId", required = true)
    @ApiResponse(responseCode = "200", description = "列信息详细信息")
    @GetMapping("listColumnByTableId")
    public Res<List<GenTableColumn>> listColumnByTableId(@RequestParam("tableId") Long tableId) {
        return success(genService.listColumnByTableId(tableId));
    }

    /**
     * 分页查询
     *
     * @param genTable  查询条件，可以为空。如果为空，则查询所有记录。
     * @param pageQuery 分页参数，可以为空。如果为空，则查询所有记录。
     * @return 表信息信息列表，如果没有记录，则返回空列表。
     */
    @Operation(summary = "分页查询")
    @Parameter(name = "pageQuery", description = "分页查询条件，可以为空。如果为空，则查询所有记录。")
    @ApiResponse(responseCode = "200", description = "表信息信息列表，如果没有记录，则返回空列表。")
    @GetMapping("page")
    public PageResult<GenTable> page(GenTable genTable, PageQuery pageQuery) {
        return toPageResult(genTableService.page(genTable, pageQuery));
    }

    /**
     * 删除
     *
     * @param ids 要删除的表信息id，不能为空。如果为空，则删除失败。
     * @return 是否删除成功
     */
    @Operation(summary = "删除")
    @Parameter(name = "idDto", description = "要删除的表信息id，不能为空。如果为空，则删除失败。")
    @ApiResponse(responseCode = "200", description = "是否删除成功")
    @PostMapping("remove")
    public Res<Boolean> remove(@RequestBody Id<Long> ids) {
        return toBoolean(genTableService.remove(ids), OperateType.REMOVE);
    }

    /**
     * 获取所有要添加的表信息
     *
     * @return
     */
    @Operation(summary = "获取所有要添加的表信息")
    @ApiResponse(responseCode = "200", description = "所有要添加的表信息")
    @GetMapping("listTable")
    public Res<List<GenTable>> listTable() {
        return success(genTableService.listTable());
    }

    @GetMapping("autoImport")
    public void autoImport() {
        genService.autoImport();
    }

    /**
     * 查询表信息
     */
    @Operation(summary = "查询表信息")
    @ApiResponse(responseCode = "200", description = "查询表信息")
    @GetMapping("getTableById")
    public Res<GenTable> getTableById(@RequestParam("id") @Nullable Long tableId) {
        return success(genService.getTableById(tableId));
    }
}
