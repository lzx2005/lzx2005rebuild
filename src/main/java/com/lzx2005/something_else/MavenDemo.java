package com.lzx2005.something_else;

/**
 * Created by JohnPC on 2016/9/9.
 */
public class MavenDemo {
    public static void main(String[] args) {
        Decpendency d1 = new Decpendency("org.springframework","spring-webmvc","4.1.7.RELEASE",1,null);
        Decpendency d5 = new Decpendency("org.springframework","spring-test","4.1.7.RELEASE",1,null);
        Decpendency[] decs1 = new Decpendency[2];
        decs1[0]=d1;
        decs1[1]=d5;
        Decpendency d2 = new Decpendency("org.springframework","spring-test","4.1.7.RELEASE",0,decs1);
        Decpendency d4 = new Decpendency("org.springframework","spring-test","4.1.8.RELEASE",0,decs1);
        Decpendency d3 = new Decpendency("log4j","log4j","1.2.16",0,null);

        Decpendency[] decs2 = new Decpendency[3];

        //4.1.7在前
        decs2[0]=d2;
        decs2[1]=d3;
        decs2[2]=d4;

        /*改变顺序4.1.8在前
        decs2[2]=d2;
        decs2[1]=d3;
        decs2[0]=d4;*/

        DecpendencyFactory df = new DecpendencyFactory();
        df.getDependency(decs2);
        Decpendency[] allDecpendencies = df.getDecpendencies();
        for(Decpendency d : allDecpendencies){
            if(d!=null){
                System.out.println(d);
            }
        }
    }
}


class DecpendencyFactory{
    private Decpendency[] decpendencies = new Decpendency[10];
    private int count = 0;
    public void getDependency(Decpendency[] decpendencies){
        for(int i =0;i<decpendencies.length;i++){
            Decpendency dec = decpendencies[i];
            if(dec!=null){
                Decpendency inDecpendency = dependencyExist(dec);
                if(inDecpendency!=null){
                    //存在依赖，进行比较
                    if(inDecpendency.getVersion().equals(dec.getVersion())){
                        //版本相同，不做改变
                    }else{
                        //版本不同
                        if(inDecpendency.getDeep()>dec.getDeep()){
                            //数组内版本路径较长，替换为当前版本
                            this.decpendencies[i] = dec;
                        }else if(inDecpendency.getDeep()==dec.getDeep()){
                            //深度一样,但是根据第一声明原则,不替换依赖
                        }
                    }
                }else{
                    //不存在依赖，添加进依赖
                    addDependency(dec);
                }

                Decpendency[] nextDec = dec.getDecpendencies();
                if(nextDec!=null&&nextDec.length>0){
                    //有依赖，递归
                    getDependency(nextDec);
                }
            }
        }
    }

    //找到相同的依赖
    public Decpendency dependencyExist(Decpendency decpendency){
        if(decpendency==null){
            return null;
        }
        for(Decpendency dec : this.decpendencies){
            if(dec==null){
                return null;
            }
            if(dec.getArtifactId().equals(decpendency.getArtifactId())){
                //有相同的ArtifactId，说明存在这个依赖
                return dec;
            }
        }
        return null;
    }

    public void addDependency(Decpendency decpendency){
        try {
            this.decpendencies[count] = decpendency;
            count++;
        }catch (ArrayIndexOutOfBoundsException e){
            expandDecpendency();
            addDependency(decpendency);
        }
    }

    private void expandDecpendency(){
        Decpendency[] newDecpendencies = new Decpendency[this.decpendencies.length*2];
        for (int i=0;i<this.decpendencies.length;i++){
            newDecpendencies[i] = this.decpendencies[i];
        }
        this.decpendencies = newDecpendencies;
    }

    public Decpendency[] getDecpendencies() {
        return decpendencies;
    }
}

class Decpendency{
    private String groupId;
    private String artifactId;
    private String version;
    private int deep;
    private Decpendency[] decpendencies;

    public Decpendency(String groupId, String artifactId, String version, int deep, Decpendency[] decpendencies) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.deep = deep;
        this.decpendencies = decpendencies;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Decpendency[] getDecpendencies() {
        return decpendencies;
    }

    public void setDecpendencies(Decpendency[] decpendencies) {
        this.decpendencies = decpendencies;
    }

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    @Override
    public String toString() {
        return "Decpendency{" +
                "groupId='" + groupId + '\'' +
                ", artifactId='" + artifactId + '\'' +
                ", version='" + version + '\'' +
                ", deep=" + deep +
                '}';
    }
}