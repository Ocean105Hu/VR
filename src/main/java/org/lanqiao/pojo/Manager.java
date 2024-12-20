package org.lanqiao.pojo;


public class Manager {
    private String managerId;
    private String managerName;
    private String managerAccount;
    private String managerPassword;

    public Manager() {
    }

    public Manager(String managerId, String managerName, String managerAccount, String managerPassword) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerAccount = managerAccount;
        this.managerPassword = managerPassword;
    }

    /**
     * 获取
     * @return managerId
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 设置
     * @param managerId
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取
     * @return managerName
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * 设置
     * @param managerName
     */
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    /**
     * 获取
     * @return managerAccout
     */
    public String getManagerAccount() {
        return managerAccount;
    }

    /**
     * 设置
     */
    public void setManagerAccount(String managerAccount) {
        this.managerAccount = managerAccount;
    }

    /**
     * 获取
     * @return managerPassword
     */
    public String getManagerPassword() {
        return managerPassword;
    }

    /**
     * 设置
     * @param managerPassword
     */
    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String toString() {
        return "Manger{managerId = " + managerId + ", managerName = " + managerName + ", managerAccout = " + managerAccount + ", managerPassword = " + managerPassword + "}";
    }
}
