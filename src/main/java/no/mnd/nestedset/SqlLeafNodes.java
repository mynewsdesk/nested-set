package no.mnd.nestedset;

public class SqlLeafNodes {

    private String sql;

    public SqlLeafNodes(TableMeta tableMeta) {
        String leafNodesSql = "SELECT %content% FROM %table% WHERE %rgt% = %lft% + 1;";

        this.sql = leafNodesSql.replace("%content%", tableMeta.content)
        .replace("%table%", tableMeta.table)
        .replace("%rgt%", tableMeta.right)
        .replace("%lft%", tableMeta.left);
    }

    public String sql() {
        return sql;
    }
}
