package cn.sijay.common.core.entity;

import lombok.Data;

import java.util.List;

/**
 * <strong>Ids<T></strong>
 * <p>
 * Ids<T>
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
public class Id<T> {
    private T id;
    private List<T> ids;

    public Id<T> of(T id) {
        this.id = id;
        return this;
    }

    public Id<T> of(List<T> ids) {
        this.ids = ids;
        return this;
    }

}
