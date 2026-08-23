package corejava;

import java.util.ArrayList;
import java.util.List;

final class Query {

    private final String sql;

    Query(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return sql;
    }
}


class QueryBuilder {

    private final List<String> selectColumns = new ArrayList<>();
    private String fromTable;

    private final List<String> joins = new ArrayList<>();
    private final List<String> conditions = new ArrayList<>();
    private final List<String> groupByColumns = new ArrayList<>();
    private final List<String> orderByColumns = new ArrayList<>();

    private Integer limit;
    private Integer offset;

    private QueryBuilder() {
    }

    // Entry point
    public static QueryBuilder select(String... columns) {

        QueryBuilder builder = new QueryBuilder();

        if (columns == null || columns.length == 0) {
            throw new IllegalArgumentException(
                    "At least one SELECT column is required"
            );
        }

        for (String column : columns) {
            builder.selectColumns.add(column);
        }

        return builder;
    }

    public QueryBuilder from(String table) {

        if (table == null || table.isBlank()) {
            throw new IllegalArgumentException(
                    "FROM table cannot be empty"
            );
        }

        this.fromTable = table;

        return this;
    }

    public QueryBuilder join(String table, String condition) {

        joins.add(
                "JOIN " + table + " ON " + condition
        );

        return this;
    }

    public QueryBuilder leftJoin(String table, String condition) {

        joins.add(
                "LEFT JOIN " + table + " ON " + condition
        );

        return this;
    }

    public QueryBuilder where(String condition) {

        conditions.add(condition);

        return this;
    }

    public QueryBuilder and(String condition) {

        if (conditions.isEmpty()) {
            throw new IllegalStateException(
                    "AND cannot be used before WHERE"
            );
        }

        conditions.add("AND " + condition);

        return this;
    }

    public QueryBuilder or(String condition) {

        if (conditions.isEmpty()) {
            throw new IllegalStateException(
                    "OR cannot be used before WHERE"
            );
        }

        conditions.add("OR " + condition);

        return this;
    }

    public QueryBuilder groupBy(String... columns) {

        for (String column : columns) {
            groupByColumns.add(column);
        }

        return this;
    }

    public QueryBuilder orderBy(String... columns) {

        for (String column : columns) {
            orderByColumns.add(column);
        }

        return this;
    }

    public QueryBuilder limit(int limit) {

        if (limit <= 0) {
            throw new IllegalArgumentException(
                    "LIMIT must be greater than 0"
            );
        }

        this.limit = limit;

        return this;
    }

    public QueryBuilder offset(int offset) {

        if (offset < 0) {
            throw new IllegalArgumentException(
                    "OFFSET cannot be negative"
            );
        }

        this.offset = offset;

        return this;
    }

    public Query build() {

        if (fromTable == null) {
            throw new IllegalStateException(
                    "FROM clause is required"
            );
        }

        StringBuilder sql = new StringBuilder();

        // SELECT
        sql.append("SELECT ")
           .append(String.join(", ", selectColumns));

        // FROM
        sql.append("\nFROM ")
           .append(fromTable);

        // JOIN
        for (String join : joins) {
            sql.append("\n")
               .append(join);
        }

        // WHERE
        if (!conditions.isEmpty()) {

            sql.append("\nWHERE ");

            sql.append(
                    String.join(" ", conditions)
            );
        }

        // GROUP BY
        if (!groupByColumns.isEmpty()) {

            sql.append("\nGROUP BY ")
               .append(
                    String.join(", ", groupByColumns)
               );
        }

        // ORDER BY
        if (!orderByColumns.isEmpty()) {

            sql.append("\nORDER BY ")
               .append(
                    String.join(", ", orderByColumns)
               );
        }

        // LIMIT
        if (limit != null) {
            sql.append("\nLIMIT ")
               .append(limit);
        }

        // OFFSET
        if (offset != null) {
            sql.append("\nOFFSET ")
               .append(offset);
        }

        return new Query(sql.toString());
    }
}

public class FluentBuilderDemo {

    public static void main(String[] args) {

        Query query = QueryBuilder
                .select(
                        "u.id",
                        "u.name",
                        "u.email"
                )
                .from("users u")
                .join(
                        "orders o",
                        "u.id = o.user_id"
                )
                .where("u.status = 'ACTIVE'")
                .and("o.amount > 1000")
                .groupBy(
                        "u.id",
                        "u.name",
                        "u.email"
                )
                .orderBy("o.amount DESC")
                .limit(10)
                .offset(20)
                .build();

        System.out.println(query);
    }
}
