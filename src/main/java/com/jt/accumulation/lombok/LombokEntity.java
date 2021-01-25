package com.jt.accumulation.lombok;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author jiatai.hu
 * @version 1.0
 * @date 2021/1/25 15:37
 */
@Data
// 链式调用
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "newInstance")
@AllArgsConstructor
@NoArgsConstructor/*(access = AccessLevel.PRIVATE)*/
public class LombokEntity {
    @NonNull
    private String name;
    private int type;
}
