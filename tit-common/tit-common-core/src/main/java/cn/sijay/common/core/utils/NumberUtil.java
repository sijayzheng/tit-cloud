package cn.sijay.common.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * <strong>NumberUtil</strong>
 * <p>
 * NumberUtil
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NumberUtil extends NumberUtils {
    public static int firstGreatThanZero(int... nums) {
        for (int num : nums) {
            if (num > 0) {
                return num;
            }
        }
        return 0;
    }
}
