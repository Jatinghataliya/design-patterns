package creational.builder.query;

import java.util.ArrayList;
import java.util.List;

/**
 * A practical Real-World Example: SQL Query Builder.
 * Demonstrates step-by-step construction of complex, safe SQL queries.
 */
public class SqlQuery {
    private final String table;
    private final List<String> columns;
    private final List<String> whereClauses;
    private final List<String> orderBy;
    private final Integer limit;
    private final Integer offset;

    private SqlQuery(Builder builder) {
        this.table = builder.table;
        this.columns = new ArrayList<>(builder.columns);
        this.whereClauses = new ArrayList<>(builder.whereClauses);
        this.orderBy = new ArrayList<>(builder.orderBy);
        this.limit = builder.limit;
        this.offset = builder.offset;
    }

    public String toSql() {
        StringBuilder sql = new StringBuilder("SELECT ");

        // Columns
        if (columns.isEmpty()) {
            sql.append("*");
        } else {
            sql.append(String.join(", ", columns));
        }

        // Table
        sql.append(" FROM ").append(table);

        // WHERE clauses
        if (!whereClauses.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", whereClauses));
        }

        // ORDER BY
        if (!orderBy.isEmpty()) {
            sql.append(" ORDER BY ").append(String.join(", ", orderBy));
        }

        // LIMIT
        if (limit != null) {
            sql.append(" LIMIT ").append(limit);
        }

        // OFFSET
        if (offset != null) {
            sql.append(" OFFSET ").append(offset);
        }

        sql.append(";");
        return sql.toString();
    }

    @Override
    public String toString() {
        return toSql();
    }

    /**
     * Fluent Builder for SqlQuery.
     */
    public static class Builder {
        private final String table;
        private final List<String> columns = new ArrayList<>();
        private final List<String> whereClauses = new ArrayList<>();
        private final List<String> orderBy = new ArrayList<>();
        private Integer limit;
        private Integer offset;

        public Builder(String table) {
            if (table == null || table.trim().isEmpty()) {
                throw new IllegalArgumentException("Table name cannot be null or empty");
            }
            this.table = table.trim();
        }

        public Builder select(String... cols) {
            for (String col : cols) {
                if (col != null && !col.trim().isEmpty()) {
                    this.columns.add(col.trim());
                }
            }
            return this;
        }

        public Builder where(String condition) {
            if (condition != null && !condition.trim().isEmpty()) {
                this.whereClauses.add(condition.trim());
            }
            return this;
        }

        public Builder orderBy(String column, String direction) {
            this.orderBy.add(column + " " + (direction != null ? direction.toUpperCase() : "ASC"));
            return this;
        }

        public Builder limit(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit must be positive");
            }
            this.limit = limit;
            return this;
        }

        public Builder offset(int offset) {
            if (offset < 0) {
                throw new IllegalArgumentException("Offset cannot be negative");
            }
            this.offset = offset;
            return this;
        }

        public SqlQuery build() {
            return new SqlQuery(this);
        }
    }
}
