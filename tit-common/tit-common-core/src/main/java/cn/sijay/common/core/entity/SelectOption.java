package cn.sijay.common.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <strong>SelectOption<T></strong>
 * <p>
 * SelectOption<T>
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SelectOption<T> {
  private String label;
  private T value;
  private List<SelectOption<T>> children;

  public SelectOption(String label, T value) {
    this.label = label;
    this.value = value;
  }

  public SelectOption append(SelectOption<T> option) {
    this.children.add(option);
    return this;
  }
}
