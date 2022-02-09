package com.example;

import com.blazebit.persistence.view.EntityView;
import com.blazebit.persistence.view.IdMapping;

@EntityView(MyEntity.class)
public interface MyEntityView {
    @IdMapping
    Long getId();
}
