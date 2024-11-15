package cn.sijay.common.core.utils;

import cn.sijay.common.core.entity.TreeNode;

/**
 * <strong>ParseToTreeNode</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/25 15:32
 */
public interface TreeNodeConvert<T, K> {
    /**
     * @param entity   实体类
     * @param treeNode 树节点
     */
    void convert(T entity, TreeNode<K> treeNode);

}