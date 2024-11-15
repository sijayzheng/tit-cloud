package cn.sijay.system.controller;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.entity.Res;
import cn.sijay.common.core.entity.SelectOption;
import cn.sijay.common.mybatis.dictionary.OperateType;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.web.annotation.OprLog;
import cn.sijay.common.web.base.BaseController;
import cn.sijay.common.web.entity.PageResult;
import cn.sijay.common.web.utils.ExcelUtil;
import cn.sijay.system.dto.SysDictTypeDto;
import cn.sijay.system.entity.SysDictType;
import cn.sijay.system.service.ISysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <strong>SysDictTypeController</strong>
 * <p>
 * 字典类型控制层
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Tag(name = "字典类型", description = "/sysDictType")
@RequiredArgsConstructor
@RestController
@RequestMapping("sysDictType")
public class SysDictTypeController extends BaseController {
    private final ISysDictTypeService sysDictTypeService;

    /**
     * 根据id查询
     *
     * @param id id
     * @return 字典类型详细信息
     */
    @OprLog(value = "根据id查询", operateType = OperateType.SELECT)
    @Operation(summary = "根据id查询")
    @Parameter(name = "id", description = "id", required = true)
    @ApiResponse(responseCode = "200", description = "字典类型详细信息")
    @GetMapping("getById")
    public Res<SysDictTypeDto> getById(@RequestParam("id") Long id) {
        return success(sysDictTypeService.getById(id).toSysDictTypeDto());
    }

    /**
     * 查询所有记录
     *
     * @return 字典类型信息列表，如果没有记录，则返回空列表。
     */
    @OprLog(value = "查询所有记录", operateType = OperateType.SELECT)
    @Operation(summary = "查询所有记录")
    @ApiResponse(responseCode = "200", description = "字典类型信息列表，如果没有记录，则返回空列表。")
    @GetMapping("listAll")
    public Res<List<SysDictTypeDto>> listAll() {
        return success(sysDictTypeService.list().stream().map(SysDictType::toSysDictTypeDto).toList());
    }

    /**
     * 分页查询
     *
     * @param dto       查询条件，可以为空。如果为空，则查询所有记录。
     * @param pageQuery 分页参数，可以为空。如果为空，则查询所有记录。
     * @return 字典类型信息列表，如果没有记录，则返回空列表。
     */
    @OprLog(value = "分页查询", operateType = OperateType.SELECT)
    @Operation(summary = "分页查询")
    @Parameter(name = "pageQuery", description = "分页查询条件，可以为空。如果为空，则查询所有记录。")
    @ApiResponse(responseCode = "200", description = "字典类型信息列表，如果没有记录，则返回空列表。")
    @GetMapping("page")
    public PageResult<SysDictTypeDto> page(SysDictTypeDto dto, PageQuery pageQuery) {
        return toPageResult(sysDictTypeService.page(dto, pageQuery));
    }

    /**
     * 添加
     *
     * @param dto 添加的字典类型信息，不能为空。如果为空，则添加失败。
     * @return 是否添加成功
     */
    @OprLog(value = "添加", operateType = OperateType.CREATE)
    @Operation(summary = "添加")
    @Parameter(name = "sysDictType", description = "添加的字典类型信息，不能为空。如果为空，则添加失败。")
    @ApiResponse(responseCode = "200", description = "是否添加成功")
    @PostMapping("add")
    public Res<Boolean> add(@RequestBody SysDictTypeDto dto) {
        return toBoolean(sysDictTypeService.add(dto), OperateType.CREATE);
    }

    /**
     * 修改
     *
     * @param dto 修改的字典类型信息，不能为空。如果为空，则添加失败。
     * @return 是否修改成功
     */
    @OprLog(value = "修改", operateType = OperateType.MODIFY)
    @Operation(summary = "修改")
    @Parameter(name = "sysDictType", description = "修改的字典类型信息，不能为空。如果为空，则添加失败。")
    @ApiResponse(responseCode = "200", description = "是否修改成功")
    @PostMapping("update")
    public Res<Boolean> update(@RequestBody SysDictTypeDto dto) {
        return toBoolean(sysDictTypeService.update(dto), OperateType.MODIFY);
    }

    /**
     * 删除
     *
     * @param id 要删除的字典类型id，不能为空。如果为空，则删除失败。
     * @return 是否删除成功
     */
    @OprLog(value = "删除", operateType = OperateType.REMOVE)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "要删除的字典类型id，不能为空。如果为空，则删除失败。")
    @ApiResponse(responseCode = "200", description = "是否删除成功")
    @PostMapping("remove")
    public Res<Boolean> remove(@RequestBody Id<Long> id) {
        return toBoolean(sysDictTypeService.remove(id), OperateType.REMOVE);
    }

    /**
     * 导入
     *
     * @param file 要导入的excel文件。
     * @return 是否导入成功
     */
    @OprLog(value = "导入", operateType = OperateType.IMPORT)
    @Operation(summary = "导入")
    @Parameter(name = "file", description = "要导入的excel文件")
    @ApiResponse(responseCode = "200", description = "是否导入成功")
    @PostMapping("import")
    public Res<Void> importData(@RequestPart("file") MultipartFile file) {
        return success(sysDictTypeService.importData(file));
    }

    /**
     * 导出
     */
    @OprLog(value = "导出", operateType = OperateType.EXPORT)
    @Operation(summary = "导出")
    @ApiResponse(responseCode = "200", description = "是否导出成功")
    @PostMapping("export")
    public void export(HttpServletResponse response) {
        sysDictTypeService.export(response);

    }

    /**
     * 下载字典类型_模板
     */
    @OprLog(value = "下载字典类型_模板", operateType = OperateType.EXPORT)
    @Operation(summary = "下载字典类型_模板")
    @ApiResponse(responseCode = "200", description = "是否下载成功")
    @PostMapping("downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("字典类型_模板", SysDictTypeDto.class, response);
    }

    /**
     * 同步枚举字典
     */
    @OprLog(value = "同步枚举字典", operateType = OperateType.OTHER)
    @Operation(summary = "同步枚举字典")
    @ApiResponse(responseCode = "200", description = "同步枚举字典")
    @GetMapping("syncDict")
    public Res<Void> syncDict() {
        sysDictTypeService.syncDict();
        return success();
    }

    /**
     * 字典类型下拉列表
     */
    @OprLog(value = "字典类型下拉列表", operateType = OperateType.SELECT)
    @Operation(summary = "字典类型下拉列表")
    @ApiResponse(responseCode = "200", description = "字典类型下拉列表")
    @GetMapping("toOptions")
    public Res<List<SelectOption<String>>> toOptions() {
        return success(sysDictTypeService.toOptions());
    }
}
