package com.zdk.utils;

/**
 * @Description
 * @Author zdk
 * @Date 2021/6/2 9:31
 * 权限常量
 */
@SuppressWarnings("all")
public class Permission {
    /**
     * 获取管理员列表
     */
    public static final String ADMINLIST="adminList";
    /**
     * 获取普通用户列表
     */
    public static final String PRIMARYLIST="primaryList";
    /**
     * 获取企业用户列表
     */
    public static final String ENTERPRISELIST="enterpriseList";
    /**
     * 获取角色列表
     */
    public static final String ROLELIST="roleList";
    /**
     * 获取权限列表
     */
    public static final String RIGHTLIST="rightList";
    /**
     * 删除管理员用户
     */
    public static final String REMOVEADMIN="removeAdmin";
    /**
     * 删除企业用户
     */
    public static final String REMOVEENTERPRISE="removeEnterprise";
    /**
     * 添加企业用户
     */
    public static final String ADDENTERPRISE="addEnterprise";
    /**
     * 展示企业用户编辑信息
     */
    public static final String SHOWENTERPRISEUSERS="showEnterpriseUsers";
    /**
     * 修改企业用户信息
     */
    public static final String EDITENTERPRISEUSERS="editEnterpriseUsers";
    /**
     * 删除普通用户
     */
    public static final String REMOVEPRIMARYUSERS="removePrimaryUsers";
    /**
     * 添加普通用户
     */
    public static final String ADDUSER="addUser";
    /**
     * 展示普通用户编辑信息
     */
    public static final String SHOWEDITPRIMARYUSERS="showEditPrimaryUsers";
    /**
     * 修改普通用户
     */
    public static final String EDITPRIMARYUSERS="editPrimaryUsers";
    /**
     * 添加角色
     */
    public static final String ADDROLES="addRoles";
    /**
     * 展示角色编辑信息
     */
    public static final String SHOWROLES="showRoles";
    /**
     * 修改角色
     */
    public static final String MODIFYROLES="modifyRoles";
    /**
     * 删除角色
     */
    public static final String DELETEROLES="deleteRoles";
    /**
     * 展示树形权限
     */
    public static final String SHOWSETRIGHTDIALOG="showSetRightDialog";
    /**
     * 获取食品分类列表
     */
    public static final String GETFOODCATEGORY="getFoodCategory";
    /**
     * 添加食品
     */
    public static final String ADDFOOD="addFood";
    /**
     * 删除食品
     */
    public static final String DELETEFOOD="deleteFood";
    /**
     * 修改食品信息
     */
    public static final String MODIFYFOOD="modifyFood";
    /**
     * 获取食品信息列表
     */
    public static final String GETFOOD="getFood";
    /**
     * 获取货单信息列表
     */
    public static final String GETMANIFEST="getManifest";
    /**
     * 添加货单信息
     */
    public static final String ADDMANIFEST="addManifest";
    /**
     * 添加食品分类
     */
    public static final String ADDFOODCATEGORY="addFoodCategory";
    /**
     * 删除食品分类信息
     */
    public static final String DELETEFOODCATEGORY="deleteFoodCategory";
    /**
     * 修改食品分类信息
     */
    public static final String MODIFYFOODCATEGORY="modifyFoodCategory";
    /**
     * 编辑查看食品类别信息
     */
    public static final String CATEGORIES="categories";
    /**
     * 分配权限弹出框
     */
    public static final String RIGHTLISTROLE="rightListRole";
    /**
     * 获取想编辑的食品信息
     */
    public static final String GETFOODBYID="getFoodById";
    /**
     * 获取要编辑的货单信息
     */
    public static final String GETMANIFESTBYID="getManifestById";
    /**
     * 修改货单信息
     */
    public static final String MODIFYMANIFEST="modifyManifest";
    /**
     * 删除货单信息
     */
    public static final String DELETEMANIFEST="deleteManifest";
    /**
     * 分配权限
     */
    public static final String ASSIGNRIGHT="assignRight";
    /**
     * 获取所有储存站点名称
     */
    public static final String GETALLSTORAGENAME="getAllStorageName";
    /**
     * 获取当前用户信息
     */
    public static final String USERINFO="userInfo";
    /**
     * 获取食品检测列表
     */
    public static final String GETRISKLIST="getRiskList";
    /**
     * 获取站点信息
     */
    public static final String GETSTORAGE="getStorage";
    /**
     * 修改站点信息
     */
    public static final String MODIFYSTORAGE="modifyStorage";
    /**
     * 删除站点
     */
    public static final String DELETESTORAGE="deleteStorage";
    /**
     * 显示要修改的站点的信息
     */
    public static final String SHOWSTORAGE="showStorage";

    /**
     * OPTIONS请求
     */
    public static final String OPTIONS="OPTIONS";

    /**
     * 添加食品检测数据
     */
    public static final String ADDRISK="addRisk";

    /**
     * 删除食品检测数据
     */
    public static final String DELETERISK="deleteRisk";

    /**
     * 显示要编辑的食品检测数据
     */
    public static final String SHOWRISKINFO="showRiskInfo";

    /**
     * 修改食品检测数据
     */
    public static final String MODIFYRISKS="modifyRisks";

    /**
     * 显示企业用户发货的货单列表
     */
    public static final String GETSENDMANIFEST="getSendManifest";

    /**
     * 显示企业用户收货的货单列表
     */
    public static final String GETRECEIVEMANIFEST="getReceiveManifest";
}
