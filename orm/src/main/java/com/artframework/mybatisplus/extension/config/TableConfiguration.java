package com.artframework.mybatisplus.extension.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li.pengcheng
 * @version V1.0
 * @date 2022/11/21
 **/
@XmlRootElement(name = "tables")
public class TableConfiguration {

    private List<Table> tables;

    public List<Table> getTables() {
        return tables;
    }
    @XmlElement(name = "table")
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @XmlRootElement(name = "table")
    public static class Table {

        private String type;


        private List<TableColumn> columns;


        public String getType() {
            return type;
        }
        @XmlAttribute
        public void setType(String type) {
            this.type = type;
        }


        public List<TableColumn> getColumns() {
            return columns;
        }
        @XmlElement(name = "column")
        public void setColumns(List<TableColumn> columns) {
            this.columns = columns;
        }

        @XmlRootElement(name = "column")
        public static class TableColumn {
            private String name;

            private String type;


            public String getName() {
                return name;
            }
            @XmlAttribute(name = "name")
            public void setName(String name) {
                this.name = name;
            }


            public String getType() {
                return type;
            }
            @XmlAttribute(name = "type")
            public void setType(String type) {
                this.type = type;
            }
        }
    }


    public static TableConfiguration DEFAULT() {
        TableConfiguration configuration = new TableConfiguration();
        configuration.setTables(new ArrayList<>());
        return configuration;
    }
//
//    public static void main(String[] args) throws Exception {
//
//        JAXBContext context = JAXBContext.newInstance(TableConfiguration.class,TableConfiguration.Table.class,TableConfiguration.Table.TableColumn.class);    // ?????????????????????
//        Marshaller marshaller = context.createMarshaller(); // ?????????????????????marshaller??????
//
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // ?????????????????????
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // ?????????XML???????????????????????????
//
//        marshaller.marshal(getSimpleDepartment(),System.out);   // ??????????????????
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        marshaller.marshal(getSimpleDepartment(), baos);
//        String xmlObj = new String(baos.toByteArray());         // ??????XML?????????
//        System.out.println(xmlObj);
//    }
//
//    /**
//     *   propertyMap.put("name", Class.forName("java.lang.String"));
//     *             propertyMap.put("address", Class.forName("java.lang.String"));
//     * @return
//     */
//    private static TableConfiguration getSimpleDepartment() {
//        TableConfiguration tableConfiguration = new TableConfiguration();
//        List<TableConfiguration.Table> tables = new ArrayList<>();
//
//        TableConfiguration.Table stf = new TableConfiguration.Table();
//        stf.setType("com.example.demo.dataobject.UserInfoDo");
//
//        List<TableConfiguration.Table.TableColumn> columns = new ArrayList<>();
//        TableConfiguration.Table.TableColumn column1 = new Table.TableColumn();
//        column1.setName("name");
//        column1.setType("java.lang.String");
//        columns.add(column1);
//
//        TableConfiguration.Table.TableColumn column2 = new Table.TableColumn();
//        column2.setName("address");
//        column2.setType("java.lang.String");
//        columns.add(column2);
//        stf.setColumns(columns);
//        tables.add(stf);
//
//        tableConfiguration.setTables(tables);
//
//        return tableConfiguration;
//    }

}
