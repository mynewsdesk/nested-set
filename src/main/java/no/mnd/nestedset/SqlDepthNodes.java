package no.mnd.nestedset;

import org.springframework.jdbc.core.RowMapper;

public class SqlDepthNodes {

    private String sql;
    private TableMeta tableMeta;

    public SqlDepthNodes(TableMeta tableMeta) {
        String depthSql = "SELECT node.%content%, (COUNT(parent.%content%) - 1) AS depth " +
                "FROM %table% AS node, %table% AS parent " +
                "WHERE node.%lft% BETWEEN parent.%lft% AND parent.%rgt% " +
                "GROUP BY node.%content% " +
                "ORDER BY node.%lft%";

        this.tableMeta = tableMeta;

        this.sql = depthSql.replace("%content%", tableMeta.content)
                .replace("%table%", tableMeta.table)
                .replace("%rgt%", tableMeta.right)
                .replace("%lft%", tableMeta.left);
    }

    public String sql() {
        return sql;
    }

    public RowMapper<ContentDepth> rowMapper = (rs, rowNum) -> {
        ContentDepth cd = new ContentDepth();
        cd.content = rs.getString(tableMeta.content);
        cd.depth = rs.getInt("depth");
        return cd;
    };
}
