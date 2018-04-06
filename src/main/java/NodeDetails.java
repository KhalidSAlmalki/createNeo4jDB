//import org.apache.commons.lang.StringEscapeUtils;
//
//public class NodeDetails {
//
//    public String getName() {
//        return name;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public String getHref() {
//        return href;
//    }
//
//    private String name;
//    private String returnValue;
//    private String description;
//    private String desc;
//        private  String href;
//
//        public NodeDetails(String name, String desc, String href) {
//            this.name = name;
//            this.desc = desc;
//            this.href = href;
//        }
//
//    public NodeDetails(String name, String returnValue, String description,String href) {
//        this.name = name;
//        this.returnValue = returnValue;
//        this.description = description;
//        this.href = href;
//    }
//
//    public String getMethodDetails(){
//
//        return  String.format("{name:'%s', desc:\"%s\", href:'%s', returnValue:'%s'}",name,StringEscapeUtils.escapeJava(description),href,returnValue);
//
//    }
//    public String getExtendDetails(){
//
//        return  String.format("{name:'%s', package:\"%s\", href:'%s'}",name,StringEscapeUtils.escapeJava(desc),href);
//
//    }
//
//        @Override
//        public String toString() {
//
//            return  String.format("{name:'%s', desc:\"%s\", href:'%s'}",name,StringEscapeUtils.escapeJava(desc),href);
//        }
//
//    }
//
