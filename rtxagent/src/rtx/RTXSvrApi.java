package rtx;

/**
 * ��װ��RTX�Ĳ���
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: Tencent C0. Ltd</p>
 * @author herolin
 * @version 1.0
 */
public class RTXSvrApi {

    //�ͻ�����server���ݵ�Э��
    static int PRO_ADDUSER = 0x0001; //����û���Ϣ
    static int PRO_DELUSER = 0x0002; //ɾ���û���Ϣ
    static int PRO_GETUSERDETAILINFO = 0x0004; //��ȡ�û���ϸ��Ϣ
    static int PRO_SETUSERDETAILINFO = 0x0005; //�����û���ϸ��Ϣ
    static int PRO_GETUSERSMPLINFO = 0x0006; //��ȡ�û���Ҫ��Ϣ
    static int PRO_SETUSERSMPLINFO = 0x0003; //�����û���Ҫ��Ϣ
    static int PRO_SETUSERPRIVILEGE = 0x0007; //�����û�Ȩ��
    static int PRO_IFEXIST = 0x0008; //�ж��û��Ƿ����
    static int PRO_TRANUSER = 0X0009; //�û�����UIN�Ļ�ת

    static int PRO_ADDDEPT = 0x0101; //���Ӳ���
    static int PRO_DELDEPT = 0x0102; //ɾ������
    static int PRO_SETDEPT = 0x0103; //���²�����Ϣ
    static int PRO_GETCHILDDEPT = 0x0104; //��ȡ�Ӳ���
    static int PRO_GETDEPTALLUSER = 0x0105; //��ȡ�����û�
    static int PRO_SETDEPTPRIVILEGE = 0x0106; //���ò���Ȩ��
    static int PRO_GETDEPTSMPLINFO = 0x0107; //��ȡ������Ϣ

    static int PRO_SMS_LOGON = 0x1000;
    static int PRO_SMS_SEND = 0x1001; //���Ͷ��� ����ֻ�����(���ŷָ�)��Ⱥ��
    static int PRO_SMS_NICKLIST = 0x1002; //���û���ȡ����
    static int PRO_SMS_FUNCLIST = 0x1003; //�����ܺŶ�ȡ����
    static int PRO_SMS_CHECK = 0x1004; //��ȡδ����������

    static int PRO_SYS_GETSESSIONKEY = 0x2000; //������֤��ȡsessionKey
    static int PRO_SYS_GETUSERSTATUS = 0x2001; //��ѯ�û�����״̬
    static int PRO_SYS_SENDIM = 0x2002; //����IM��Ϣ
    static int PRO_SYS_SESSIONKEYVERIFY = 0x2003; //��֤sessionKey

    static int PRO_EXT_NOTIFY = 0x2100; //��ʱ��Ϣ����

	static int PRO_IMPORTUSER = 0x0001; //����xml�û�����
	static int PRO_EXMPORTUSER = 0x0002; //����xml�û�����
	
    //��������
    static String OBJNAME_RTXEXT = "EXTTOOLS"; //��չ����
    static String OBJNAME_RTXSYS = "SYSTOOLS"; //ϵͳ����
    static String OBJNAME_DEPTMANAGER = "DEPTMANAGER"; //���Ŷ���
    static String OBJNAME_USERMANAGER = "USERMANAGER"; //�û�����
    static String OBJNAME_SMSMANAGER = "SMSOBJECT"; //���Ŷ���
    static String OBJNAME_USERSYNC = "USERSYNC"; //�û����ݵ��뵼��


    static String KEY_TYPE = "TYPE";

    static String KEY_USERID = "USERID";
    static String KEY_USERNAME = "USERNAME";
    static String KEY_UINTYPE = "UINTYPE";
    static String KEY_UIN = "UIN"; //RTX���
    static String KEY_NICK = "NICK"; //��½��
    static String KEY_MOBILE = "MOBILE"; //�ֻ�
    static String KEY_OUTERUIN = "OUTERUIN";
    static String KEY_LASTMODIFYTIME = "LASTMODIFYTIME";
    static String KEY_FACE = "FACE";	//ͷ��
    static String KEY_PASSWORD = "PWD";	//����

    static String KEY_AGE = "AGE";	//����
    static String KEY_GENDER = "GENDER";	//�Ա�
    static String KEY_BIRTHDAY = "BIRTHDAY";	//����
    static String KEY_BLOODTYPE = "BLOODTYPE";	//Ѫ��
    static String KEY_CONSTELLATION = "CONSTELLATION";	//����
    static String KEY_COLLAGE = "COLLAGE";	//��ѧ
    static String KEY_HOMEPAGE = "HOMEPAGE";	//��ҳ
    static String KEY_EMAIL = "EMAIL";	//����
    static String KEY_PHONE = "PHONE";	//�绰
    static String KEY_FAX = "FAX";	//�绰�ֻ�����
    static String KEY_ADDRESS = "ADDRESS";	//��ַ
    static String KEY_POSTCODE = "POSTCODE";	//�ʱ����
    static String KEY_COUNTRY = "COUNTRY";	//����
    static String KEY_PROVINCE = "PROVINCE";	//ʡ��
    static String KEY_CITY = "CITY";	//����
    static String KEY_MEMO = "MEMO";	//����˵��
    static String KEY_STREET = "STREET";	//�ֵ�
    static String KEY_MOBILETYPE = "MOBILETYPE";	
    static String KEY_AUTHTYPE = "AUTHTYPE";
    static String KEY_POSITION = "POSITION";
    static String KEY_OPENGSMINFO = "OPENGSMINFO";
    static String KEY_OPENCONTACTINFO = "OPENCONTACTINFO";
    static String KEY_PUBOUTUIN = "PUBOUTUIN";
    static String KEY_PUBOUTNICK = "PUBOUTNICK";
    static String KEY_PUBOUTNAME = "PUBOUTNAME";
    static String KEY_PUBOUTDEPT = "PUBOUTDEPT";
    static String KEY_PUBOUTPOSITION = "PUBOUTPOSITION";
    static String KEY_PUBOUTINFO = "PUBOUTINFO";
    static String KEY_OUTERPUBLISH = "OUTERPUBLISH";

    static String KEY_LDAPID = "LDAPID";
    static String KEY_DEPTID = "DEPTID";
    static String KEY_PDEPTID = "PDEPTID";
    static String KEY_SORTID = "SORTID";
    static String KEY_NAME = "NAME";
    static String KEY_INFO = "INFO";
    static String KEY_COMPLETEDELBS = "COMPLETEDELBS";


    //Ȩ�����
    static String KEY_DENY = "DENY";
    static String KEY_ALLOW = "ALLOW";

    static String KEY_SESSIONKEY = "SESSIONKEY";


	//���뵼��xml�������
	static String KEY_MODIFYMODE = "MODIFYMODE";
	static String KEY_DATA = "DATA";
	
    //�������
    static String KEY_SENDER = "SENDER";
    static String KEY_FUNNO = "FUNCNO";
    static String KEY_RECEIVER = "RECEIVER";
    static String KEY_RECEIVERUIN = "RECEIVERUIN";
    static String KEY_SMS = "SMS";
    static String KEY_CUT = "CUT";
    static String KEY_NOTITLE = "NOTITLE";
    static String KEY_DELFLAG = "DELFLAG";


    //RTXServerҵ���߼�
    static String KEY_RECVUSERS = "RECVUSERS";
    static String KEY_IMMSG = "IMMSG";


    //��Ϣ����
    static String KEY_MSGID = "MSGID";
    static String KEY_MSGINFO = "MSGINFO";
    static String KEY_ASSISTANTTYPE = "ASSTYPE";
    static String KEY_TITLE = "TITLE";
	static String KEY_DELAYTIME = "DELAYTIME";

    //������ϵ�����
    static String KEY_RESULT_INCODE = "INNERCODE"; //�ڲ�����
    static String KEY_RESULT_ERR_INFO = "ERR_INFO";
    static String KEY_RESULT_CODE = "CODE"; //���ش���
    static String KEY_RESULT_TYPE = "TYPE"; //��������
    static String KEY_RESULT_NAME = "NAME";
    static String KEY_RESULT_VALUE = "VALUE";
    static String KEY_RESULT_VARIANT = "VARIANT";


    //˽������
    private int iObj;
    private int iProp;
    private int innerCode;
    private int iResult;

    static {
        System.loadLibrary("SDKAPIJava");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                         ϵͳ�Դ��ĺ���                                                           //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//��ʼ����
    public native boolean Init();	

	//������
    public native void UnInit();	

	//ͨ��int���͵Ĵ�������ȡ������Ϣ
    public native String GetError(int innerCode);	

	//��ȡ�汾��
    public native String GetVersion();	

	//ͨ���������ƴ���һ������,����������OBJNAME_RTXEXT������int���͵ľ�����ڱ��ĳ�ΪiObjectHandle
    public native int GetNewObject(String szObjectName);	

	//ͨ�������ȡ��������(�÷���һ���ò���)
    public native String GetObjectName(int iObjectHandle);

	//���þ���Ķ�������(�÷���һ���ò���)
    public native int SetObjectName(int iObjectHandle, String szObjectName);

	//��ȡ���Լ��Ͼ�� 
    public native int GetNewPropertys();

	//��int���͵����Ƿ��Ǿ�� (�÷���һ���ò���)
    public native int IsHandle(int iHandle);

	//���ü���(�÷���һ���ò���)
    public native int AddRefHandle(int iHandle);

	//�ͷž��
    public native int ReleaseHandle(int iHandle);

	//�����Լ����������,iHandle��ʾ���Լ��ϵľ��
    public native int AddProperty(int iPropertyHandle, String szName, String szValue);

	//�����Լ�����ɾ��ĳ�����ԣ�ͨ������ɾ��
    public native int ClearProperty(int iPropertyHandle, int iIndex);

	//�����Լ�����ɾ��ĳ������,ͨ��������ɾ��
    public native int RemoveProperty(int iPropertyHandle, String szName);

	//��ȡ���Լ�����ĳ�����Ե�ֵ
    public native String GetProperty(int iPropertyHandle, String szName);

	//���÷�����IP��ַ
    public native int SetServerIP(int iObjectHandle, String szServerIP);

	//��ȡ������IP��ַ
    public native String GetServerIP(int iObjectHandle);

	//��ȡ������IP��ַ
    public native int GetServerPort(int iObjectHandle);

	//���÷������˿�
    public native int SetServerPort(int iObjectHandle, int iPort);

	//��ȡ���Լ��������Ե�����
    public native int GetPropertysCount(int iHandle);

	//ͨ��������ȡ���Լ����е���Ŀ
    public native int GetPropertysItem(int iHandle, int iIndex);

	//����SDK����������������Ծ��������ID
    public native int Call(int iObjectHandle, int iPropertyHandle, int iCmdID);

	//��ȡ���ؽ���ļ��Լ��ϣ�����һ�����ؽ���ľ��
    public native int GetResultPropertys(int iResultHandle);

	//ͨ������һ�����ؽ���ľ������ȡ�þ���Ľ������int���ͱ�ʾ
    public native int GetResultInt(int iResultHandle);

	//ͨ������һ�����ؽ���ľ������ȡ�þ���Ľ������String���ͱ�ʾ
    public native String GetResultString(int iResultHandle);

	//��ȡһ�����Ե�����
    public String GetPropertyItemName(int iHandle) {
        return GetProperty(iHandle, KEY_RESULT_NAME);
    }

	//��ȡһ�����Ե�ֵ
    public String GetPropertyItemValue(int iHandle) {
        return GetProperty(iHandle, KEY_RESULT_VALUE);
    }

	//��ȡ�ڲ��������
    public int GetResultInnerCode(int iHandle) {
        String sz = GetProperty(iHandle, KEY_RESULT_INCODE);
        return Integer.parseInt(sz);
    }
	//��ȡ�ڲ�������Ϣ
    public String GetResultErrString(int iHandle) {
        String sz = GetProperty(iHandle, KEY_RESULT_ERR_INFO);
        return sz;
    }
	//��ȡSDK���÷��ؽ����Code��0��ʾ�ɹ���������ʾʧ��
    public int GetResultCode(int iHandle) {
        String sz = GetProperty(iHandle, KEY_RESULT_CODE);
        return Integer.parseInt(sz);
    }

	//��ȡĳ�����Ե�����(һ���ò���)
    public int GetResultType(int iHandle) {
        String sz = GetProperty(iHandle, KEY_RESULT_TYPE);
        return Integer.parseInt(sz);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                           ��չ�ĺ���                                                           //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * ��ʼ������
     * @param objName String
     */
    private void svrInit(String objName) {
        iObj = GetNewObject(objName);
        iProp = GetNewPropertys();
    }

    /**
     * �ͷ���Դ
     */
    private void release() {
        ReleaseHandle(iObj);
        ReleaseHandle(iProp);
        ReleaseHandle(iResult);
    }

   /**
    * ���÷�����IP
    * @param strIP String
    */
   public void setServerIP(String strIP){
       svrInit(OBJNAME_RTXSYS);
       int iResult=SetServerIP(iObj,strIP);
       release();

   }

   /**
    * ���÷������˿�
    * @param iPort int �˿ڣ�Ĭ����6000
    */
   public void setServerPort(int iPort){
       svrInit(OBJNAME_RTXSYS);
       int iResult=SetServerPort(iObj,iPort);
       release();
   }

   /**
    * ȡ������IP
    *@param iPort int
    */
   public String getServerIP(){
       svrInit(OBJNAME_RTXSYS);
       String ip=GetServerIP(iObj);
       release();
       return ip;
   }

   /**
    * ȡ�������˿�
    * @return String ��������ַ
    */
   public int getServerPort(){
       svrInit(OBJNAME_RTXSYS);
       int port=GetServerPort(iObj);
       release();
       return port;
   }

    /**
     * ��ѯ�û�����״̬
     * @param UserName String �û��ʺ�
     * @return int 0:���� 1:���� 2:�뿪 11:�����ڸ��û� ����:δ֪״̬
     */
    public int QueryUserState(String UserName) {
    	
        svrInit(OBJNAME_RTXSYS);
        
        AddProperty(iProp, KEY_USERNAME, UserName);
         //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        int iResult = Call(iObj, iProp, PRO_SYS_GETUSERSTATUS);
        int innerCode = GetResultInnerCode(iResult);
        int iRTXState = GetResultInt(iResult);
        if (innerCode != 0) {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
            release();
            return innerCode;
        } 
        	
    	release();
        return iRTXState;
      
    }


    /**
     * ɾ���û���Ϣ
     * @param String UserName�û��ʺ�
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
    public int deleteUser(String UserName) {
        if(UserName==null || "".equals(UserName)){
            return -1;
        }

        svrInit(OBJNAME_USERMANAGER);
        
        AddProperty(iProp,KEY_USERNAME,UserName);
       //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        iResult=Call(iObj,iProp,PRO_DELUSER);
        innerCode = GetResultInnerCode(iResult);
        if(innerCode!=0){
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
		}
		
        release();
        return innerCode;
    }


    /**
     * �����û�
     * @param UserName string �û��ʺ�
     * @param DeptID String ����ID	
     * @paramint ChsName String �û�����
     * @paramint Pwd String ����
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
    public int addUser(String UserName, String DeptID, String ChsName, String Pwd ) {
    	
        svrInit(OBJNAME_USERMANAGER);
        
        AddProperty(iProp,KEY_NICK,UserName); //�û���
        AddProperty(iProp, KEY_DEPTID, DeptID); //��֯ID
        AddProperty(iProp, KEY_NAME, ChsName); //��ʵ��
        AddProperty(iProp, KEY_PASSWORD, Pwd);//����

        iResult=Call(iObj,iProp,PRO_ADDUSER);
        innerCode = GetResultInnerCode(iResult);
        
        if (innerCode != 0)
        {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        }
        
        release();
        return innerCode;
    }

    /**
     * �鿴�û�������
     * @param UserName String �û��ʺ�
     * @return String[][]  �����ɹ�����һ����ά���� ʧ��Ϊnull
      */
	public String[][] GetUserSimpleInfo(String UserName)
	{
		
		String[][] info=null;
		
		svrInit(OBJNAME_USERMANAGER);
		
        AddProperty(iProp, KEY_USERNAME, UserName);
        //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        int iResult = Call(iObj, iProp, PRO_GETUSERSMPLINFO);
        int innerCode = GetResultInnerCode(iResult);
        if (innerCode != 0) {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        } else {
            int iProps = GetResultPropertys(iResult);
            int iCount = GetPropertysCount(iProps);
            info = new String[iCount][2];
            for (int i = 0; i < iCount; i++) {
                int iHandle = GetPropertysItem(iProps, i);
                info[i][0] = GetPropertyItemName(iHandle);
                info[i][1] = GetPropertyItemValue(iHandle);             
                ReleaseHandle(iHandle);
            }
        }
        
        release();
        return info;
	}
	
	/**
     * �����û�������
     * @param UserName String �û��ʺ�
     * @param ChsName String �û�����
     * @param email String �����ַ
     * @param gender String �Ա���Ϊ0��ŮΪ1
     * @param mobile String �ֻ�����
     * @param phone String  �绰
     * @param pwd String ����
     * @return int  0 �����ɹ� ��0Ϊʧ��
      */
	public int SetUserSimpleInfo(String UserName,String ChsName,String email,String gender,String mobile,String phone,String pwd)
	{

		svrInit(OBJNAME_USERMANAGER);
		
		//�����϶�������д��ȥ
        AddProperty(iProp, KEY_USERNAME, UserName);
         //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        iResult = Call(iObj, iProp, PRO_GETUSERSMPLINFO);
        innerCode = GetResultInnerCode(iResult);
        if ( innerCode!= 0) {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
            release();
        	return innerCode;
        } else {
            int iProps = GetResultPropertys(iResult);
            int iCount = GetPropertysCount(iProps);
            
           for (int i=0 ;i  < iCount ; i++)
           {
           		if (i==9)
           		{
           			if (pwd != null && !"".equals(pwd) && !"null".equals(pwd)) {
        				AddProperty(iProp, KEY_PASSWORD, pwd); //����
        			}
           		}
           		else 
           		{
           			int iHandle = GetPropertysItem(iProps, i);
            		AddProperty(iProp, GetPropertyItemName(iHandle), GetPropertyItemValue(iHandle));
            		ReleaseHandle(iHandle);
           		}

           }

        }	
        
        //�����û���Ϣ
        if (ChsName != null && !"".equals(ChsName) && !"null".equals(ChsName)) {
        	AddProperty(iProp, KEY_NAME, ChsName); //������
        }
        if (gender != null && !"".equals(gender) && !"null".equals(gender)) {
        	AddProperty(iProp, KEY_GENDER, gender); //�Ա�
        }
        if (mobile != null && !"".equals(mobile) && !"null".equals(mobile)) {
        	AddProperty(iProp, KEY_MOBILE, mobile); //�ֻ�
        }
        if (phone != null && !"".equals(phone) && !"null".equals(phone)) {
        	AddProperty(iProp, KEY_PHONE, phone); //�绰
        }
        if (email != null && !"".equals(email) && !"null".equals(email)) {
        	AddProperty(iProp, KEY_EMAIL, email); //����
        }

       //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

		//���ð��û�������д��ȥ
        int iResult = Call(iObj, iProp, PRO_SETUSERSMPLINFO);
        int innerCode = GetResultInnerCode(iResult);
        if (innerCode != 0) {
        	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
    	} 
        
        release();
        return innerCode;
	}

	/**
     * �����û������ϣ�֧�����ò���ID
     * @param UserName String �û��ʺ�
     * @param DeptID String ����ID
     * @param ChsName String �û�����
     * @param email String �����ַ
     * @param gender String �Ա���Ϊ0��ŮΪ1
     * @param mobile String �ֻ�����
     * @param phone String  �绰
     * @param pwd String ����
     * @return int  0 �����ɹ� ��0Ϊʧ��
      */
	public int SetUserSimpleInfoEx(String UserName,String DeptID, String ChsName,String email,String gender,String mobile,String phone,String pwd)
	{

		svrInit(OBJNAME_USERMANAGER);
		
		//�����϶�������д��ȥ
        AddProperty(iProp, KEY_USERNAME, UserName);
        
        iResult = Call(iObj, iProp, PRO_GETUSERSMPLINFO);
        innerCode = GetResultInnerCode(iResult);
        if ( innerCode!= 0) {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
            release();
        	return innerCode;
        } else {
            int iProps = GetResultPropertys(iResult);
            int iCount = GetPropertysCount(iProps);
            
           for (int i=0 ;i  < iCount ; i++)
           {
           		if (i==9)
           		{
           			if (pwd != null && !"".equals(pwd) && !"null".equals(pwd)) {
        				AddProperty(iProp, KEY_PASSWORD, pwd); //����
        			}
           		}
           		else 
           		{
           			int iHandle = GetPropertysItem(iProps, i);
            		AddProperty(iProp, GetPropertyItemName(iHandle), GetPropertyItemValue(iHandle));
            		ReleaseHandle(iHandle);
           		}

           }

        }	
        
        //�����û���Ϣ
        if (DeptID != null && !"".equals(DeptID) && !"null".equals(DeptID)) {
        	AddProperty(iProp, KEY_DEPTID, DeptID); //����ID
        }
        if (ChsName != null && !"".equals(ChsName) && !"null".equals(ChsName)) {
        	AddProperty(iProp, KEY_NAME, ChsName); //������
        }
        if (gender != null && !"".equals(gender) && !"null".equals(gender)) {
        	AddProperty(iProp, KEY_GENDER, gender); //�Ա�
        }
        if (mobile != null && !"".equals(mobile) && !"null".equals(mobile)) {
        	AddProperty(iProp, KEY_MOBILE, mobile); //�ֻ�
        }
        if (phone != null && !"".equals(phone) && !"null".equals(phone)) {
        	AddProperty(iProp, KEY_PHONE, phone); //�绰
        }
        if (email != null && !"".equals(email) && !"null".equals(email)) {
        	AddProperty(iProp, KEY_EMAIL, email); //����
        }

        
		//���ð��û�������д��ȥ
        int iResult = Call(iObj, iProp, PRO_SETUSERSMPLINFO);
        int innerCode = GetResultInnerCode(iResult);
        if (innerCode != 0) {
        	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
    	} 
        
        release();
        return innerCode;
	}
	
    /**
     * �鿴�û���ϸ����
     * @param UserName String �û��ʺ�
     * @return String[][] �����ɹ�����һ����ά���� ʧ�ܷ���null
     */
    public String[][] GetUserDetailInfo(String UserName) {
		
		String[][] info=null;
    	svrInit(OBJNAME_USERMANAGER);
    	
        AddProperty(iProp, KEY_USERNAME, UserName);
         //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        int iResult = Call(iObj, iProp, PRO_GETUSERDETAILINFO);
        int innerCode = GetResultInnerCode(iResult);
        if (innerCode != 0) {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        } else {
            int iProps = GetResultPropertys(iResult);
            int iCount = GetPropertysCount(iProps);
            info = new String[iCount][2];
            for (int i = 0; i < iCount; i++) {
                int iHandle = GetPropertysItem(iProps, i);
                info[i][0] = GetPropertyItemName(iHandle);
                info[i][1] = GetPropertyItemValue(iHandle);  
                ReleaseHandle(iHandle);
            }
        }
        
        release();
        return info;
    }
    
    /**
     * �����û���Ϣ
     * @param UserName String
     * @param ....... String
     * @param MOBILE String
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
    public int setUserDetailInfo(String UserName,String ADDRESS,String AGE,String BIRTHDAY,
    String BLOODTYPE,String CITY,String COLLAGE,String CONSTELLATION,String COUNTRY,String FAX,
    String HOMEPAGE,String MEMO,String POSITION,String POSTCODE,String PROVINCE,String STREET,
    String PHONE,String MOBILE) {
        
		//��Ҫ�ǵõ�DetpID
		svrInit(OBJNAME_USERMANAGER);
		
        AddProperty(iProp, KEY_USERNAME, UserName);
        //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        iResult = Call(iObj, iProp, PRO_GETUSERSMPLINFO);
        innerCode = GetResultInnerCode(iResult);
        if ( innerCode!= 0) {
            System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
            release();
        	return innerCode;
        } else {
            int iProps = GetResultPropertys(iResult);
            int iHandle = GetPropertysItem(iProps, 0);
            AddProperty(iProp, KEY_DEPTID, GetPropertyItemValue(iHandle));
            ReleaseHandle(iHandle);
        }	
		
		
        if (ADDRESS != null && !"".equals(ADDRESS) && !"null".equals(ADDRESS)) {
            AddProperty(iProp, KEY_ADDRESS, ADDRESS); //סַ
        }
        if (BLOODTYPE != null && !"".equals(BLOODTYPE) && !"null".equals(BLOODTYPE)) {
            AddProperty(iProp, KEY_BLOODTYPE, BLOODTYPE); //Ѫ��
        }
        if (COUNTRY != null && !"".equals(COUNTRY) && !"null".equals(COUNTRY)) {
            AddProperty(iProp, KEY_COUNTRY, COUNTRY); //����
        }
        if (PROVINCE != null && !"".equals(PROVINCE) && !"null".equals(PROVINCE)) {
            AddProperty(iProp, KEY_PROVINCE, PROVINCE); //ʡ
        }
        if (CITY != null && !"".equals(CITY) && !"null".equals(CITY)) {
            AddProperty(iProp, KEY_CITY, CITY); //����
        }
        if (POSTCODE != null && !"".equals(POSTCODE) && !"null".equals(POSTCODE)) {
            AddProperty(iProp, KEY_POSTCODE, POSTCODE); //��������
        }
        if (HOMEPAGE != null && !"".equals(HOMEPAGE) && !"null".equals(HOMEPAGE)) {
            AddProperty(iProp, KEY_HOMEPAGE, HOMEPAGE); //������ҳ
        }
        if (PHONE != null && !"".equals(PHONE) && !"null".equals(PHONE)) {
            AddProperty(iProp, KEY_PHONE, PHONE); //�绰
        }
        if (MOBILE != null && !"".equals(MOBILE) && !"null".equals(MOBILE)) {
            AddProperty(iProp, KEY_MOBILE, MOBILE); //�ƶ��绰
        }
        if (MEMO != null && !"".equals(MEMO) && !"null".equals(MEMO)) {
            AddProperty(iProp, KEY_MEMO, MEMO); //���˼��
        }
        if (POSITION != null && !"".equals(POSITION) && !"null".equals(POSITION)) {
            AddProperty(iProp, KEY_POSITION, POSITION); //ְ��
        }
        if (FAX != null && !"".equals(FAX) && !"null".equals(FAX)) {
            AddProperty(iProp, KEY_FAX, FAX); //����
        }
        if (AGE != null && !"".equals(AGE) && !"null".equals(AGE)) {
            AddProperty(iProp, KEY_AGE, AGE); //����
        }
        if (BIRTHDAY != null && !"".equals(BIRTHDAY) && !"null".equals(BIRTHDAY)) {
            AddProperty(iProp, KEY_BIRTHDAY, BIRTHDAY); //����
        }
        if (COLLAGE != null && !"".equals(COLLAGE) && !"null".equals(COLLAGE)) {
            AddProperty(iProp, KEY_COLLAGE, COLLAGE); //��ѧ
        }
        if (STREET != null && !"".equals(STREET) && !"null".equals(STREET)) {
            AddProperty(iProp, KEY_STREET, STREET); //�ֵ�
        }

        //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        iResult = Call(iObj, iProp, PRO_SETUSERDETAILINFO);
        innerCode = GetResultInnerCode(iResult);
        if (innerCode != 0) {
        	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
    	} 
        release();
        return innerCode;
    }

    /**
     * ��RTX����ת��Ϊ�س�
     * @param  UIN String RTX����
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
	public String UinToUserName ( String UIN)
	{
		String UserName = null;
		svrInit(OBJNAME_USERMANAGER);
		
        AddProperty(iProp,KEY_USERNAME,UIN); //RTX����
        
        iResult=Call(iObj,iProp,PRO_GETUSERSMPLINFO);
        innerCode = GetResultInnerCode(iResult);
        if ( innerCode != 0){
        	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        	release();
        	return null;
        }

    	int iProps = GetResultPropertys(iResult);
        int iHandle = GetPropertysItem(iProps, 7);
        UserName = GetPropertyItemValue(iHandle);
        ReleaseHandle(iHandle);
        release();
        return UserName;
                    
	}
	
    /**
     * �����֯��Ϣ
     * @param deptId String		����ID
     * @param DetpInfo String	������Ϣ
     * @param DeptName String	��������
     * @param ParentDeptId String 	������ID
     * @param type String 	0:ֻɾ����ǰ��֯ 1:ɾ���¼���֯���û�
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
    public int addDept(String deptId,String DetpInfo,String DeptName,String ParentDeptId ) {
   
        svrInit(OBJNAME_DEPTMANAGER);
        
        AddProperty(iProp,KEY_DEPTID,deptId);
        AddProperty(iProp,KEY_NAME,DeptName);
        AddProperty(iProp,KEY_INFO,DetpInfo);
        AddProperty(iProp,KEY_PDEPTID,ParentDeptId);

        iResult = Call(iObj, iProp, PRO_ADDDEPT);
        innerCode = GetResultInnerCode(iResult);
        if(innerCode!=0){
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        }
        
        release();
        return innerCode;
    }

    /**
     * �޸���֯��Ϣ
     * @param deptId String 	����ID
     * @param DetpInfo string 	������Ϣ
     * @param DeptName string 	��������
     * @param ParentDeptId string	������ID
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
    public int setDept(String deptId,String DetpInfo,String DeptName,String ParentDeptId ) {
   
        svrInit(OBJNAME_DEPTMANAGER);
        
        AddProperty(iProp,KEY_DEPTID,deptId);
        AddProperty(iProp,KEY_NAME,DeptName);
        AddProperty(iProp,KEY_INFO,DetpInfo);
        AddProperty(iProp,KEY_PDEPTID,ParentDeptId);

        iResult = Call(iObj, iProp, PRO_SETDEPT);
        innerCode = GetResultInnerCode(iResult);
        if(innerCode!=0){
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        }
        
        release();
        return innerCode;
    }

    /**
     * ɾ����֯��Ϣ
     * @param deptId String	����
     * @param type String 0:ֻɾ����ǰ��֯ 1:ɾ���¼���֯���û�
     * @return int  0 �����ɹ� ��0Ϊʧ��
     */
    public int deleteDept(String deptId,String type) {
   
        svrInit(OBJNAME_DEPTMANAGER);
        
        AddProperty(iProp, KEY_DEPTID, deptId);
        AddProperty(iProp,KEY_COMPLETEDELBS,type);

        iResult = Call(iObj, iProp, PRO_DELDEPT);
        innerCode = GetResultInnerCode(iResult);
        if(innerCode!=0){
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        }
        
        release();
        return innerCode;
    }

    /**
     * �ж�ĳ����֯�Ƿ����
     * @param deptId String ����ID
     * @return int 0:���� ��0:������
     */
    public int deptIsExist(String deptId) {
        if(deptId==null || "".equals(deptId)){
            return -100;
        }

	svrInit(OBJNAME_DEPTMANAGER);
	String info=null;
		
	AddProperty(iProp,KEY_DEPTID,deptId);
		
	int iResult =Call(iObj, iProp, PRO_GETDEPTSMPLINFO);
	innerCode = GetResultInnerCode(iResult);

        	release();

        
       	 return innerCode;
    }

    /**
     * ȡ��ǰ��֯�µ��û�
     * @param DeptID String ����ID
     * @return String[] �ɹ����ز������û��ʺ����飬ʧ�ܷ���null
     */
    public String[] getDeptUsers(String DeptID){
    	
        String[] user=null;
        
        svrInit(OBJNAME_DEPTMANAGER);
        
        AddProperty(iProp,KEY_DEPTID,DeptID);
        
        iResult =Call(iObj, iProp, PRO_GETDEPTALLUSER);
        innerCode = GetResultInnerCode(iResult);
        if (innerCode != 0)
        {
        	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        }        
        int iProps=GetResultPropertys(iResult);
        int iCount=GetPropertysCount(iProps);
        if (iCount > 0) {
            user = new String[iCount];
            for (int i = 0; i < iCount; i++) {
                int iHandler = GetPropertysItem(iProps, i);
                user[i]=UinToUserName(GetPropertyItemValue(iHandler)) ; 	//iHandler��ȡ��������RTX����
                ReleaseHandle(iHandler);                
            }
        }
        
        release();       
        return user;
    }


    /**
     * ȡ��ǰ��֯�µ��Ӳ���ID
     * @param DeptID String ����ID
     * @return String[] �ɹ������Ӳ������飬ʧ�ܷ���null
     */
    public String[] getChildDepts(String DeptID){
    	
        String[] detps=null;
        
        svrInit(OBJNAME_DEPTMANAGER);
        
        AddProperty(iProp,KEY_PDEPTID,DeptID);
        
        iResult =Call(iObj, iProp, PRO_GETCHILDDEPT);
        innerCode = GetResultInnerCode(iResult);
        if(innerCode!=0){
          	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
          	release();
          	return null;
        }
        
        int iProps=GetResultPropertys(iResult);
        int iCount=GetPropertysCount(iProps);
        if (iCount > 0) {
            detps = new String[iCount];
            for (int i = 0; i < iCount; i++) {
                int iHandler = GetPropertysItem(iProps, i);
                detps[i]=GetPropertyItemValue(iHandler);
                ReleaseHandle(iHandler);                
            }
        }

        release();       
        return detps;
    }

    /**
     * �ж�ĳ���û��Ƿ����
     * @param UserName String �û��ʺ�
     * @return int 0:���� ��0:������
     */
    public int userIsExist(String UserName) {
    	
        svrInit(OBJNAME_USERMANAGER);
        
        AddProperty(iProp,KEY_USERNAME,UserName);
        //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        iResult = Call(iObj, iProp, PRO_IFEXIST);
        innerCode = GetResultInnerCode(iResult);
        int iExist=GetResultInt(iResult);
        release();
        if(innerCode!=0){
          	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
        }
        
         return iExist;
    }

   /**
    * ��ȡsessionKey
    * @param UserName String �û��ʺ�
    * @return String �ɹ�����SessionKey��ʧ�ܷ���null
    */
   public String getSessionKey(String UserName){
   	
       svrInit(OBJNAME_RTXSYS);
       
       AddProperty(iProp,KEY_USERNAME,UserName);
       iResult=Call(iObj,iProp,PRO_SYS_GETSESSIONKEY);
       
       innerCode = GetResultInnerCode(iResult);
       String szKey = new String ("");
       if(innerCode!=0){
           	System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
       }else{
           szKey=GetResultString(iResult);
       }
       
       release();
       return szKey;

   }

   
   /**
     * ������Ϣ����
     * @param receivers String ������(����������Զ��ŷָ�)
     * @param title String ��Ϣ����
     * @param msg String ��Ϣ����
     * @param type String 0:��ͨ��Ϣ 1:������Ϣ
     * @param delayTime String ��ʾͣ��ʱ��(����) 0:Ϊ����ͣ��(�û��ر�ʱ�Źر�)
     * @return int 0:�����ɹ� ��0:�������ɹ�
     */
    public int sendNotify(String receivers,String title,String msg, String type,String delayTime) {
        
        svrInit(OBJNAME_RTXEXT);
        
        AddProperty(iProp, KEY_USERNAME, receivers);
        AddProperty(iProp, KEY_TITLE, title);
        AddProperty(iProp, KEY_MSGINFO, msg);
        AddProperty(iProp, KEY_TYPE, type);
        AddProperty(iProp, KEY_MSGID, "0");
        AddProperty(iProp, KEY_ASSISTANTTYPE, "0");
       //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

        if(!"0".equals(delayTime))
            AddProperty(iProp, KEY_DELAYTIME, delayTime);

        iResult = Call(iObj, iProp, PRO_EXT_NOTIFY);
        innerCode = GetResultInnerCode(iResult);
        
        release();
        return innerCode;
    }

    /**
    * ���Ͷ���
    * @param sender String ������
    * @param receiver String ������(RTX�û����ֻ��������,���128��)
    * @param smsInfo String ��������
    * @param autoCut int �Ƿ��Զ��������� 0:�� 1:��
    * @param noTitle int �Ƿ��Զ���д���� 0:�Զ� 1:�ƶ�
    * @return int �ɹ�����0��ʧ�ܷ�������
    */
   public int sendSms(String sender, String receiver, String smsInfo,int autoCut, int noTitle) {
       
       svrInit(OBJNAME_SMSMANAGER);
       
       AddProperty(iProp, KEY_SENDER, sender);
       AddProperty(iProp, KEY_RECEIVER, receiver);
       AddProperty(iProp, KEY_SMS, smsInfo);
       AddProperty(iProp, KEY_CUT, String.valueOf(autoCut));
       AddProperty(iProp, KEY_NOTITLE, String.valueOf(noTitle));
       //AddProperty(iProp,KEY_UINTYPE,"Account"); �������KEY_UINTYPE ΪAccount�����۴���ȥ�Ǵ����ֻ����ַ���������Ϊ���ʺš�

       iResult = Call(iObj, iProp, PRO_SMS_SEND);
       innerCode = GetResultInnerCode(iResult);
       if ( innerCode != 0)
       {
       		System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
       }
       release();
       return innerCode;
   }
   
    /**
    * ����rtx���û�����Ϊxml
    * @return string  �ɹ�����RTX�û����ݵ�xml,ʧ�ܷ���null
    */
	public String exportXmldata()
	{
		String strResult = null;
		svrInit(OBJNAME_USERSYNC);
		
		AddProperty(iProp,"MODIFYMODE","1");
		AddProperty(iProp,"XMLENCODE","<?xml version=\"1.0\" encoding=\"gb2312\" ?>");
		
		int iResult =Call(iObj, iProp, PRO_EXMPORTUSER);
		int iProps=GetResultPropertys(iResult);
		innerCode = GetResultInnerCode(iResult);
		if ( innerCode != 0)
		{
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
		}
	    strResult = GetResultString(iResult);
		
		release();
		return strResult;
	}

	/**
    * ����xml���û�����
    * @param xmldata String xml�û�����
    * @return int :�ɹ����ز������ƣ�ʧ�ܷ���null
    */
	public int  importXmldata(String xmldata)
	{
		svrInit(OBJNAME_USERSYNC);
		
		AddProperty(iProp,KEY_MODIFYMODE,"1");
		AddProperty(iProp,KEY_DATA,xmldata);
		
		int iResult =Call(iObj, iProp, PRO_IMPORTUSER);
		innerCode = GetResultInnerCode(iResult);
		if ( innerCode != 0)
		{
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
		}
		
		release();
		return innerCode;
	}

	/**
    * ��ȡ��������
    * @param deptID String ����ID
    * @return String 0:�����ɹ� ��0:�������ɹ�
    */	
	public String  GetDeptName(String deptID)
	{
		svrInit(OBJNAME_DEPTMANAGER);
		String info=null;
		
		AddProperty(iProp,KEY_DEPTID,deptID);
		
		int iResult =Call(iObj, iProp, PRO_GETDEPTSMPLINFO);
		innerCode = GetResultInnerCode(iResult);
		if ( innerCode != 0)
		{
			System.out.println("�������:" + innerCode + "\t" + "������Ϣ��"+ GetResultErrString(iResult) );
		}
		else
		{
        		int iProps=GetResultPropertys(iResult);
        		int iCount=GetPropertysCount(iProps);
        		System.out.println("GetResultPropertys:"+iProps+ "\t" + "GetPropertysCount:"+iCount);
        	
            		int iHandle = GetPropertysItem(iProps, 2);
                	info = GetPropertyItemValue(iHandle);             
                	ReleaseHandle(iHandle);
                	System.out.println(info);
		}

		release();
		return info;
	}


}
