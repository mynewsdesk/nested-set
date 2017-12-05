package no.mnd.nestedset;

public class SqlTreeFromTopNodes {

    private String sql;

    public SqlTreeFromTopNodes(TableMeta tableMeta) {
        String treeNodesSql =
                "SELECT node.%content% FROM %table% AS node, %table% AS parent " +
                    " WHERE node.%lft% BETWEEN parent.%lft% AND parent.%rgt% AND parent.%content% = :topNode " +
                        " ORDER BY node.%lft%";

        this.sql = treeNodesSql.replace("%table%", tableMeta.table)
                .replace("%content%", tableMeta.content)
                .replace("%rgt%", tableMeta.right)
                .replace("%lft%", tableMeta.left);
    }

    public String sql() {
        return sql;
    }

}
