package com.example;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestTransaction
class MyEntityDbServiceTest {

    @Inject
    MyEntityDbService sut;

    @Test
    void name() {
        sut.add(create());
        assertThat(sut.findAll()).isNotEmpty();
    }

    @Test
    void name1() {
        MyEntity myEntity = create();
        sut.add(myEntity);
        assertThat(sut.find(myEntity.getId())).isNotNull();
    }

    private MyEntity create() {
        return new MyEntity();
    }

}