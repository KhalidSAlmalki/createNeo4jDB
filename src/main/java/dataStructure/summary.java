package dataStructure;

public abstract class summary {

    public String name;
    public String descrption;
    public String href;
    public String packageName;

    public summary(String name, String description, String href) {
        this.name = name;
        this.descrption = description;
        this.href = href;
    }
    public void addPackageName(String packageName) {
        this.packageName = packageName;
    }


}
