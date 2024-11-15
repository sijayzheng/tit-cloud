package cn.sijay.gen.test;

import cn.sijay.common.json.utils.JsonUtil;
import cn.sijay.gen.TitGen;
import cn.sijay.gen.entity.GenTable;
import cn.sijay.gen.service.GenService;
import cn.sijay.gen.service.IGenTableColumnService;
import cn.sijay.gen.service.IGenTableService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <strong>GenTest</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-16
 */
@SpringBootTest(classes = TitGen.class)
public class GenTest {
    @Autowired
    private GenService genService;
    @Autowired
    private IGenTableService genTableService;
    @Autowired
    private IGenTableColumnService genTableColumnService;

    @Test
    public void autoImport() {
        genService.autoImport();
    }

    @Test
    public void generate() {
        long[] longs = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 13L, 14L, 16L};
        for (long l : longs) {
            genService.generate(l);
        }
    }

    @Test
    public void test() {
        Page<GenTable> page = new Page<>(1, 10);
        Page<GenTable> page1 = genTableService.page(page);
        System.out.println(JsonUtil.toPrettyJsonString(page1));
    }
}
