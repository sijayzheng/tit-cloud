package cn.sijay.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <strong>TreeNode<K></strong>
 * <p>
 * TreeNode<K>
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TreeNode<K> {
    private K id;
    private K pid;
    private String name;
    private int sort;
    private List<TreeNode<K>> children;

    public TreeNode(K id, K pid, String name) {
        this.id = id;
        this.pid = pid;
        this.name = name;
    }

    public TreeNode(K id, K pid, String name, int sort) {
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.sort = sort;
    }

}

