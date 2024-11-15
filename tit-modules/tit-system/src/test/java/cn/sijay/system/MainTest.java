package cn.sijay.system;

import cn.sijay.common.json.utils.JsonUtil;
import cn.sijay.system.service.ISysDictTypeService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <strong>MainTest</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-26
 */
@SpringBootTest(classes = TitSystem.class)
public class MainTest {
    @Autowired
    private ISysDictTypeService sysDictTypeService;

    @Test
    public void test() {
        System.out.println(JsonUtil.toJsonString(sysDictTypeService.page(new Page<>(0, 10))));
    }
}
