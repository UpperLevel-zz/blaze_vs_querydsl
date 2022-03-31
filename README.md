## Blaze Persistence as an alternative for Query DSL?

The initial question was:

Is there any necessity to switch from "Query DSL" to "Blaze Persistence"? Which benefits do we expect? What
disadvantages will it come with? What would it cost to migrate existing code?

The answer is:
No! There is no need for query-dsl to be replaced with Blaze Persistence. Instead: Blaze Persistence can be used in
combination with query-dsl applications.
see: https://persistence.blazebit.com/documentation/1.6/core/manual/en_US/index.html#querydsl-integration

### Comparision

Data collected in 2022-04

|                  | Blaze                | QueryDsl  |
|------------------|----------------------|-----------|
| Last Release     | 2022-01              | 2021-07   |
| Roadmap          | Yes                  | No        |
| Usage            | < 100                | > 200     |
| Java Version     | 7 - 17               | 7 - 17    |
| Support          | Community/Commercial | Community |
| Window Functions | Yes                  | Yes       |
| Functions        | Yes                  | Yes       |
| Entity View      | Yes                  | No        |
| CTE              | Yes                  | No        |
| SET operations   | Yes                  | No        |
| DataNucleus      | Yes                  | ?         |

### Bullet Points

- build on jpa criteria-api --> code against standard api possible
- framework support for things like spring, quarkus, hibernate, querydsl, graphql, etc...
- query dsl team itself recommends the use
  of https://persistence.blazebit.com/documentation/1.6/core/manual/en_US/#querydsl-integration if features like
  fetchCount will be used.
- some query dsl developers are also contributors in blaze
- no migration needed for existing querydsl applications

### Disadvantages

Blaze persistence api is build as an advanced criteria api so from scratch it will be string based. Autocompletion and
compiler issues will not be available.

#### Blaze Persistence

```
List<Foo> results = cbf.create(entityManager, Foo.class, "Q")  
    .where("Q.age").betweenExpression("5").andExpression("10") 
    .orderByAsc("Q.id")
    .getResultList();
```

#### Query DSL

```
QFoo Q = QFoo.foo
List<Foo> results = = new JPAQuery<Foo>(entityManager)
    .from(Q)
    .where(Q.age.between(5, 10))
    .orderBy(Q.id.asc())
    .fetch();
```