package com.jt.projects.validation.lists;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.*;

public class ValidList<E> {
    @Valid
    @NotEmpty(message = "传入数据不能为空")
    private List<E> list = new ArrayList<>();
}
