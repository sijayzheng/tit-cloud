package cn.sijay.system.controller;

import cn.sijay.common.core.entity.Id;
import cn.sijay.common.core.entity.Res;
import cn.sijay.common.mybatis.dictionary.OperateType;
import cn.sijay.common.mybatis.entity.PageQuery;
import cn.sijay.common.web.annotation.OprLog;
import cn.sijay.common.web.base.BaseController;
import cn.sijay.common.web.entity.PageResult;
import cn.sijay.common.web.utils.ExcelUtil;
import cn.sijay.system.dto.SysDeptDto;
import cn.sijay.system.entity.SysDept;
import cn.sijay.system.service.ISysDeptService;
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
 * <strong>SysDeptController</strong>
 * <p>
 * 部门信息控制层
 * </p>
 *
 * @author sijay
 * @since 2024-04-25
 */
@Tag(name = "部门信息", description = "/sysDept")
@RequiredArgsConstructor
@RestController
@RequestMapping("sysDept")
public class SysDeptController extends BaseController {
    private final ISysDeptService sysDeptService;

    /**
     * 根据id查询
     *
     * @param id id
     * @return 部门信息详细信息
     */
    @OprLog(value = "根据id查询", operateType = OperateType.SELECT)
    @Operation(summary = "根据id查询")
    @Parameter(name = "id", description = "id", required = true)
    @ApiResponse(responseCode = "200", description = "部门信息详细信息")
    @GetMapping("getById")
    public Res<SysDeptDto> getById(@RequestParam("id") Long id) {
        return success(sysDeptService.getById(id).toSysDeptDto());
    }

    /**
     * 查询所有记录
     *
     * @return 部门信息信息列表，如果没有记录，则返回空列表。
     */
    @OprLog(value = "查询所有记录", operateType = OperateType.SELECT)
    @Operation(summary = "查询所有记录")
    @ApiResponse(responseCode = "200", description = "部门信息信息列表，如果没有记录，则返回空列表。")
    @GetMapping("listAll")
    public Res<List<SysDeptDto>> listAll() {
        return success(sysDeptService.list().stream().map(SysDept::toSysDeptDto).toList());
    }

    /**
     * 分页查询
     *
     * @param dto       查询条件，可以为空。如果为空，则查询所有记录。
     * @param pageQuery 分页参数，可以为空。如果为空，则查询所有记录。
     * @return 部门信息信息列表，如果没有记录，则返回空列表。
     */
    @OprLog(value = "分页查询", operateType = OperateType.SELECT)
    @Operation(summary = "分页查询")
    @Parameter(name = "pageQuery", description = "分页查询条件，可以为空。如果为空，则查询所有记录。")
    @ApiResponse(responseCode = "200", description = "部门信息信息列表，如果没有记录，则返回空列表。")
    @GetMapping("page")
    public PageResult<SysDeptDto> page(SysDeptDto dto, PageQuery pageQuery) {
        return toPageResult(sysDeptService.page(dto, pageQuery));
    }

    /**
     * 添加
     *
     * @param dto 添加的部门信息信息，不能为空。如果为空，则添加失败。
     * @return 是否添加成功
     */
    @OprLog(value = "添加", operateType = OperateType.CREATE)
    @Operation(summary = "添加")
    @Parameter(name = "sysDept", description = "添加的部门信息信息，不能为空。如果为空，则添加失败。")
    @ApiResponse(responseCode = "200", description = "是否添加成功")
    @PostMapping("add")
    public Res<Boolean> add(@RequestBody SysDeptDto dto) {
        return toBoolean(sysDeptService.add(dto), OperateType.CREATE);
    }

    /**
     * 修改
     *
     * @param dto 修改的部门信息信息，不能为空。如果为空，则添加失败。
     * @return 是否修改成功
     */
    @OprLog(value = "修改", operateType = OperateType.MODIFY)
    @Operation(summary = "修改")
    @Parameter(name = "sysDept", description = "修改的部门信息信息，不能为空。如果为空，则添加失败。")
    @ApiResponse(responseCode = "200", description = "是否修改成功")
    @PostMapping("update")
    public Res<Boolean> update(@RequestBody SysDeptDto dto) {
        return toBoolean(sysDeptService.update(dto), OperateType.MODIFY);
    }

    /**
     * 删除
     *
     * @param id 要删除的部门信息id，不能为空。如果为空，则删除失败。
     * @return 是否删除成功
     */
    @OprLog(value = "删除", operateType = OperateType.REMOVE)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "要删除的部门信息id，不能为空。如果为空，则删除失败。")
    @ApiResponse(responseCode = "200", description = "是否删除成功")
    @PostMapping("remove")
    public Res<Boolean> remove(@RequestBody Id<Long> id) {
        return toBoolean(sysDeptService.remove(id), OperateType.REMOVE);
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
        return success(sysDeptService.importData(file));
    }

    /**
     * 导出
     */
    @OprLog(value = "导出", operateType = OperateType.EXPORT)
    @Operation(summary = "导出")
    @ApiResponse(responseCode = "200", description = "是否导出成功")
    @PostMapping("export")
    public void export(HttpServletResponse response) {
        sysDeptService.export(response);

    }

    /**
     * 下载部门信息_模板
     */
    @OprLog(value = "下载部门信息_模板", operateType = OperateType.EXPORT)
    @Operation(summary = "下载部门信息_模板")
    @ApiResponse(responseCode = "200", description = "是否下载成功")
    @PostMapping("downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) {
        ExcelUtil.exportTemplate("部门信息_模板", SysDeptDto.class, response);
    }

}
