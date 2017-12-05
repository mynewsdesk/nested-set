package no.mnd.nestedset;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NestedSet {

    private DataSource ds;
    private TableMeta tableMeta;

    public NestedSet(DataSource ds, TableMeta tableMeta) {
        this.ds = ds;
        this.tableMeta = tableMeta;
    }

    public List<String> getTreeNodesFromTop(String topNode) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put("topNode", topNode);
        return namedParameterJdbcTemplate.queryForList(new SqlTreeFromTopNodes(tableMeta).sql(),
                namedParameters, String.class);
    }

    public List<String> getTreeNodes() {
        JdbcTemplate template = new JdbcTemplate(ds);
        return template.queryForList(new SqlTreeNodes(tableMeta).sql(), String.class);
    }

    public List<String> getLeafNodes() {
        JdbcTemplate template = new JdbcTemplate(ds);
         return template.queryForList(new SqlLeafNodes(tableMeta).sql(), String.class);
    }

    public List<ContentDepth> getDepthNodes() {
        JdbcTemplate template = new JdbcTemplate(ds);
        SqlDepthNodes sqlDepthNodes = new SqlDepthNodes(tableMeta);
        return template.query(sqlDepthNodes.sql(), sqlDepthNodes.rowMapper);
    }
}
