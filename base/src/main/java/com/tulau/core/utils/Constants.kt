package com.tulau.core.utils

import android.net.Uri

object Constants {
    var versionName = "1.0"
    var versionCode = 1

    const val COLOR_BAR_HEADER = "#0dba46"

    const val SHAREDPREF_KEY = "app.sign"

    var LINK_SERVICE_PIEPME_CA = "https://google.com/"// fake

    var LINK_SERVICE_V2 = "https://google.com/"// fake
    var LINK_SERVICE_LOGIN = "https://google.com/"// fake
    var LINK_SERVICE_LOGIN_VN = "https://google.com/"// fake
    var LINK_SOCKET = "https://node.google.com/"// fake
    var DOMAIN_LINK_SOCKET = "google.com"// fake

    var DOMAIN_ATTACH_POST_DATA = "google.com"// fake
    const val ATTACH_FILE_UPLOAD = "akaUpload"
    const val ATTACH_FILE_UPLOAD_V2 = "akaUploadV2"

    var DOMAIN_GOOGLE = "google.com"// fake
    var AES_KEY: String? = null

    var AES_IV: String? = null


    const val LINK_TTJOBS_SHARE = "https://tuoitretimviec.com"
    const val avatar_user_defaul = "https://cdn.piepme.com/piep-avatar.png"

    const val KEYSHARE_NICKNAME = "nickname"
    const val KEYSHARE_SECURITYNUMBER = "securitynumber"
    const val KEYSHARE_UUID = "uuid"
    const val KEYSHARE_TOKEN = "token"
    const val KEYSHARE_COUNTRY = "country"
    const val KEYSHARE_FO100 = "fo100"
    const val KEYSHARE_TIMEUPDATE = "timeupdate"
    const val PROVIDER_NAME = "com.piepme.android.app_version_3.other.MyProvider"
    const val URL = "content://$PROVIDER_NAME/skholinguacp"
    val CONTENT_URI = Uri.parse(URL)

    val SOURCE_DEVICE = "Android"

    val KEY_TOKEN = "keyToken"
    val KEY_TOKEN_VALUE = "PiepJobs2019"
    val KEY_TOKEN_VERSION = "v"
    val KEY_TOKEN_VERSION_VALUE = "v1"

    const val FCM_SHARED_PREF = "fcm_ah_firebase"
    const val TOKEN_DEVICE_FCM = "regId"

    const val REGISTRATION_COMPLETE = "registrationComplete"
    const val PUSH_NOTIFICATION = "pushNotification"

    const val NOTIFICATION_FROM_AVATAR_KEY = "NV112"
    const val NOTIFICATION_FROM_NICKNAME_KEY = "NV104"

    const val NOTIFICATION_FROM_ACTION_KEY = "from_action"

    const val NOTIFICATION_FO100_KEY = "FO100"
    const val NOTIFICATION_FO100_SEND_KEY = "FO100S"
    const val NOTIFICATION_FO100_RECEIVE_KEY = "FO100R"

    const val NOTIFICATION_FO100_FROM_KEY = "FO100F"  //fo100 của ng gửi tin nhắn
    const val NOTIFICATION_FO100_TO_KEY = "FO100T"      //fo100 của ng nhận tin nhắn


    const val NOTIFICATION_FP800_KEY = "FP800"
    const val NOTIFICATION_PJ100_KEY = "PJ100"

    const val NOTIFICATION_FK100_KEY = "FK100"
    const val NOTIFICATION_FP600_KEY = "FP600"
    const val NOTIFICATION_PV603_KEY = "PV603"
    const val NOTIFICATION_PC100_KEY = "PC100"

    const val NOTIFICATION_TO_FM200_KEY = "FM200"
    const val NOTIFICATION_INFO_USER_KEY = "INFO"
    const val NOTIFICATION_TO_PM100 = "PM100"
    const val NOTIFICATION_TO_PM200 = "PM200"
    const val NOTIFICATION_TO_MESSAGE = "to_message"
    const val NOTIFICATION_TO_FM400_KEY = "to_fm400"
    const val NOTIFICATION_TYPE_KEY = "TYPE"
    const val NOTIFICATION_INFO_KEY = "INFO"


    //"Vừa mới gởi cho bạn 1 tin nhắn"
    const val NOTIFICATIONS_EVENT_MESSAGE = "SEND_MESSAGE_TO_USER"

    //NV nhận được Yêu cầu Báo giá từ 1 chủ nhà nào đó  "Bạn được chọn báo giá"
    const val NOTIFICATIONS_EVENT_WAITING = "EVENT_WAIT_NOTIFICATIONS"

    //nhan vien gui bao gia cho chu nha "Nhân viên gửi báo giá cho chủ nhà"
    const val NOTIFICATIONS_EVENT_PENDING = "EVENT_PEN_NOTIFICATIONS"

    //chu nha dong y bao gia cua nhan vien  "Chủ nhà đồng ý báo giá của nhân viên"
    const val NOTIFICATIONS_EVENT_APPROVED = "EVENT_APP_NOTIFICATIONS"

    //"%@ đã nộp CV vào %@"
    const val NOTIFICATIONS_EVENT_SEL_APPLY_JOB = "SEND_NOTI_SEL"

    //"%@ đã mời bạn đi phỏng vấn %@"
    const val NOTIFICATIONS_EVENT_LIH_APPLY_JOB = "SEND_NOTI_LIH"

    //"%@ đã từ chối đi phỏng vấn %@"
    const val NOTIFICATIONS_EVENT_REJ_APPLY_JOB = "SEND_NOTI_REJ"

    //"%@ đã đồng ý đi phỏng vấn %@"
    const val NOTIFICATIONS_EVENT_AGR_APPLY_JOB = "SEND_NOTI_AGR"

    //"Bạn đã trúng tuyển"
    const val NOTIFICATIONS_EVENT_APP_APPLY_JOB = "SEND_NOTI_APP"

    //Tài khoản doanh nghiệp của bạn đã được phê duyệt
    const val NOTIFICATIONS_ADMIN_DN_ACCEPT = "NOTI-FROM-ADMIN-FOR-DN-ACC"
    //Tài khoản doanh nghiệp của bạn đã bị từ chối
    const val NOTIFICATIONS_ADMIN_DN_REJECT = "NOTI-FROM-ADMIN-FOR-DN-REJ"


    val PARTTIME_NOTIFICATION_CHANNELID = "ttJOBS PARTTIME Notification"
    val DEFAULT_NOTIFICATION_CHANNELID = "ttJOBS Notification"
    val MESSENGER_NOTIFICATION_CHANNELID = "ttJOBS Messenger"
    val CALL_NOTIFICATION_CHANNELID = "ttJOBS Call"

    //key msg dung de dong fragment
    const val ITEM_CLICK = "ITEM_CLICK"
    const val KEY_BEFORE_BACK = "beforeback"
    const val KEY_IN_BACK = "back"

    //key này gọi khi callback bao gồm cả 2 thao tác KEY_BEFORE_BACK + KEY_IN_BACK
    const val CALLBACK_CLOSE = "CLOSE"
    const val KEY_ADD_TAG = "addTag"
    const val RETURN_RESPONSE = "RETURN_RESPONSE"
    const val RESPONSE_FAIL = "RESPONSE_FAIL" // có trả về mà sai thông tin
    const val RESPONSE_ERROR = "RESPONSE_ERROR" //lỗi connect tới server

    const val SHAREDPREF_TOKEN_AUTHEN_FIREBASE = "token.authen.firebase"
    const val SHAREDPREF_PHONE_AUTHEN_FIREBASE = "token.phone.firebase"

    const val SHAREDPREF_FORTGET_USER_KEY = "login.forget.user"
    const val SHAREDPREF_FORTGET_USER_INFO_KEY = "login.forget.user.info"
    const val SHAREDPREF_FORTGET_USER_OTP_KEY = "login.forget.user.otp"
    const val SHAREDPREF_FORTGET_USER_NEWDEVICE_KEY = "login.forget.user.newdevice"
    const val SHAREDPREF_FORTGET_TYPEVERIFY_KEY = "login.forget.user.typeverify"
    const val SHAREDPREF_FORTGET_NUMPHONE_KEY = "login.forget.user.numphone"
    const val SHAREDPREF_FORTGET_LISTSECURE_KEY = "login.forget.user.lstsecure"

    const val SHAREDPREF_LST_LANGUAGE_KEY = "LIST_S500"
    const val SHAREDPREF_SCREEN_WIDTH_KEY = "screen_width"
    const val SHAREDPREF_SCREEN_HEIGHT_KEY = "screen_height"

    const val SHAREDPREF_LOGIN_NICKNAME_KEY = "login.NICKNAME"
    const val SHAREDPREF_LOGIN_SECURITYNUMBER_KEY = "login.SECURITYNUMBER"
    const val SHAREDPREF_LOGIN_UUID_KEY = "login.UUID"
    const val SHAREDPREF_LOGIN_FO100_KEY = "login.FO100"
    const val SHAREDPREF_LOGIN_TOKEN_KEY = "login.TOKEN"
    const val SHAREDPREF_LOGIN_COUNTRY_KEY = "login.COUNTRY"
    const val SHAREDPREF_LOGIN_DATE = "login.DATE"
    const val SHAREDPREF_LOGIN_LANGUAGE_KEY = "login.LANGUAGE"

    const val SHAREDPREF_LOGIN_NICKNAME_KEY_CA = "login.NICKNAME.CA"
    const val SHAREDPREF_LOGIN_SECURITYNUMBER_KEY_CA = "login.SECURITYNUMBER.CA"
    const val SHAREDPREF_LOGIN_UUID_KEY_CA = "login.UUID.CA"
    const val SHAREDPREF_LOGIN_FO100_KEY_CA = "login.FO100.CA"
    const val SHAREDPREF_LOGIN_TOKEN_KEY_CA = "login.TOKEN.CA"
    const val SHAREDPREF_LOGIN_COUNTRY_KEY_CA = "login.COUNTRY.CA"
    const val SHAREDPREF_LOGIN_DATE_CA = "login.DATE.CA"


    const val SHAREDPREF_DARK_MODE_STATE = "system.DARK_MODE_STATE"


    const val SHAREDPREF_COMPLETE_CV_FILTER = "complete_cv_filter"
    const val SHAREDPREF_INFO_EMAIL = "info_email"

    const val APPCLIENTJOIN = "appClientJoin"
    const val APPCLIENTCONFIRM = "appClientConfirm"

    const val avatar_test_url =
            "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/G-Dragon_Infinite_Challenge_2015.jpg/250px-G-Dragon_Infinite_Challenge_2015.jpg"

    val lstDefaultLanguage = arrayOf("de", "en", "vi")

    const val CONNECTTOSERVER = "connectToServer"
    const val SENDSECURITYPIEPER = "sendSecurityPieper"

    const val ACTION_REQUEST_START_SIGNUP_ACTIVITY = 1000

    const val LIMIT = 15

    const val DEFAULT_LAT = 10.7796402834456
    const val DEFAULT_LNG = 106.679642563785

    const val TYPING_TIMEOUT = 1000L // 1 seconds timeout


    const val ACTION_REQUEST_PERMISSION_STORAGE_CAMERA = 1017
    const val ACTION_REQUEST_PERMISSION_AUDIO = 1015
    const val ACTION_REQUEST_PERMISSION_AUDIO_CALL = 9015
    const val ACTION_CHOOSE_TAG = 1016
    const val ACTION_REQUEST_PERMISSION_LOCATION = 1018
    const val ACTION_REQUEST_PERMISSION_APP = 1019
    const val ACTION_REQUEST_OVERLAY = 1020
    const val ACTION_REQUEST_PERMISSION_CAMERA = 1021
    const val ACTION_REQUEST_PERMISSION_CAMERA_CALL = 9021
    const val ACTION_REQUEST_PERMISSION_AUDIO_CAMERA_CALL = 9022
    const val ACTION_REQUEST_QRCODE = 1022
    const val ACTION_CHOOSECAMERA = 1023
    const val ACTION_REQUEST_QRCODE_LOGIN_APP = 1024
    const val ACTION_REQUEST_PERMISSION_CAMERA_LOGIN_WED = 1025

    const val ACTION_DETAIL_CV = 2001
    const val ACTION_CV_JOB_NEAR_YOUR = 2002
    const val ACTION_DETAIL_COMPANY = 2003
    const val ACTION_CREATE_POST = 2004
    const val ACTION_DETAIL_EMPLOYEE = 2005
    const val ACTION_DETAIL_JOB_BUSINESS = 2006
    const val ACTION_REGISTER_BUSINESS = 2007
    const val ACTION_FULL_SCREEN_VIDEO = 2008
    const val CONNECTION_REQUEST_CALL = 2405
    const val ACTION_FULL_SCREEN_VIDEO_SIGN_UP = 2009
    const val ACTION_POST_JOB = 2076
    const val SHARE_PIEPER_RESULT = 1978

    const val VIDEO_FROM_DETAILCVEMPLOYEE = 1
    const val VIDEO_FROM_CREATEPOSTRECRUITMENTACTIVITY = 2
    const val VIDEO_FROM_DNLIVEFRAGMENT = 3
    const val VIDEO_FROM_PROFILEBUSINESSFRAGMENT = 4
    const val VIDEO_FROM_PROFILEFRAGMENT = 5
    const val VIDEO_FROM_DETAILCOMPANYACTIVITY = 6
    const val VIDEO_FROM_DETAILJOBACTIVITY = 7
    const val VIDEO_FROM_DETAILRECRUITMENTACTIVITY = 8
    const val VIDEO_FROM_PROFILESIGNUPSERVICEFRAGMENT = 9
    const val VIDEO_INTRO_MEDIA = 10

    //key dung cho gallery
    const val NOT_CHOOSE_IMAGE = 0
    const val NOT_CHOOSE_VIDEO = 1
    const val CHOOSE_SINGLE_IMAGE = 2
    const val CHOOSE_SINGLE_VIDEO = 3
    const val CHOOSE_MULTIPLE_IMAGE = 4
    const val CHOOSE_MULTIPLE_VIDEO = 5
    const val CHOOSE_SINGLE_VIDEO_CONFIRM = 6
    const val IMAGE_VIDEO_SELECTED = "LIST_ALLFILE_SELECTED"
    const val IMAGES_SELECTED = "LIST_IMAGE_SELECTED"
    const val VIDEOS_SELECTED = "LIST_VIDEO_SELECTED"

    const val DOWNLOAD_FOLDER_NAME = "PiepJob"


    const val UPLOAD_START = "UPLOAD_START"
    const val UPLOAD_PERCENTS = "UPLOAD_PERCENTS"
    const val UPLOAD_FINISHED = "UPLOAD_FINISHED"
    const val UPLOAD_SUCCESS = "UPLOAD_SUCCESS"
    const val UPLOAD_ERROR = "UPLOAD_ERROR"

    val ALLOWED_IMAGE_EXT = arrayOf("jpg", "jpeg", "png", "gif")
    val ALLOWED_SOUND_EXT = arrayOf("mp3", "wav", "acc", "m4a", "flac")
    val ALLOWED_VIDEO_EXT = arrayOf("mp4", "mov", "3gpp", "mpeg", "mkv")
    val ALLOWED_LIVESTREAM_EXT = arrayOf("m3u8", "ts")
    val ALLOWED_DOCUMENT_EXT = arrayOf("pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "zip")

    const val ALLOWED_NAME_UPLOAD = "QBRANDOMNAME"


    /**
     * ghi chú mới vèe 3 trường hợp login từ anh Gia=)) 14/12/2020 11:48
     * 1: thiết bị mới chưa từng được đăg ký  ==> hiện form đăng ký mới
     * 2: full data login (tên, fo100) ==> vẫn phải check xem có sđt,email hay chưa để hiện view bổ sung sđt,email ở tab messenge/profile
     * 3: acc lấy từ CA qua để gọi hàm check (acc này chưa được dky ttJOBS) --> dùng data có sẳn này để điền vào form đăng ký tài khoảnn ttJOBS như trường hợp == 1
     */

    //1, // new Device   // 2 (FO100) Detect = FO100 từ APP Piepme   // 3(FO100) Detect != FO100 từ APP Piepme
    const val NEW_DEVICE = 1 //k có info từ CA qua, haoực thiết bị mới
    const val FULL_LOGIN = 2 //gọi login luôn, info user y như bên CA có đầy đủ sđt
    const val MID_LOGIN = 3 //hiện view có tên hiển thị sẳn, (có hoặc k) số điện thoại, đợi user nhấn OK => gọi hàm login


    const val TYPE_PIEP_PUBLIC = "PUB" //public
    const val TYPE_PIEP_DRAFT = "DRA" //nháp
    const val TYPE_PIEP_EXPIRY = "EXP" //hết hạn
    const val TYPE_PIEP_HIDDEN = "HID" //ẩn tin
    const val TYPE_PIEP_PENDING = "PEN"//Pending (chờ duyệt)
    const val TYPE_PIEP_REJECTED = "REJ"//reject (đã bị từ chối)


    const val TYPE_FILTER_PUBLIC = "PUBLIC"
    const val TYPE_FILTER_MATCHING = "MATCHING"
    const val TYPE_FILTER_SAVED = "SAVED"
    const val TYPE_FILTER_HIGH_SALARY = "PTOP" // trước là "H_SALARY" đổi lại thành việc nổi bật chỉ cần đổi filter
    const val TYPE_FILTER_NEAR = "NEAR"
    const val TYPE_FILTER_JOBS = "JOBS"
    const val TYPE_FILTER_PART_TIME_JOBS = "PART_TIME"
    const val TYPE_FILTER_ENJOY_JOBS = "YWE"

    const val CV102_IVT = "IVT" //DN mời bạn,
    const val CV102_SEL = "SEL" //người dùng tự nộp CV

    const val PROFILE_TYPE = 0
    const val CHAT_TYPE = 1

    const val RESULT_FAIL_SERVER = -100 //lỗi 502 hoặc server
    const val RESULT_SUCCES_BACK = 50000 //điều kiện để ko show status thành công
    const val ACTION_REQUEST_PERMISSION_GALLERY = 1011
    const val ACTION_FILE_MANAGER = 3030


    const val DETAIL_FORMAT_DATE_SERVER = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DETAIL_FORMAT_DATE_DMY = "dd/MM/yyy"
    const val DETAIL_FORMAT_DATE_DMY_HM = "dd/MM/yyy h:mm"
    const val DETAIL_FORMAT_TIME = "h:mm"
    const val DETAIL_FORMAT_TIME_24 = "HH:mm"
    const val DETAIL_FORMAT_TIME_A = "h:mm a"
    const val DETAIL_FORMAT_DATE_APPOINTMENT = "h:mm a - dd/MM/yyy"
    const val DETAIL_FORMAT_DATE_APPOINTMENT_24 = "HH:mm - dd/MM/yyy"

    /// ADDRESS: Hiện đang sống ở
    const val OTAG_ADDRESS = "ADDRESS"

    /// JOB_POS: Vị trí công việc (công việc)
    const val OTAG_JOB_POS = "JOB_POS"

    /// JOB_TIME: Thời gian làm việc (công việc)
    const val OTAG_JOB_TIME = "JOB_TIME"

    /// JOB_SALARY: Mức lương (công việc)
    const val OTAG_JOB_SALARY = "JOB_SALARY"

    /// SKILL_CE: Bằng cấp (Kỹ năng chuyên môn)
    const val OTAG_SKILL_CE = "SKILL_CE"

    /// SKILL_JOB: Nghề nghiệp (Kỹ năng chuyên môn)
    const val OTAG_SKILL_JOB = "SKILL_JOB"

    ///  SKILL_Y_EXP: Số năm kinh nghiệm (Kỹ năng chuyên môn)
    const val OTAG_SKILL_Y_EXP = "SKILL_Y_EXP"

    /// SKILL_ADV: Kỹ năng chuyên môn (Kỹ năng chuyên môn)
    const val OTAG_SKILL_ADV = "SKILL_ADV"

    /// danh sách lĩnh vực
    const val OTAG_FOA = "FOA"

    /// danh sách lĩnh vực
    const val OTAG_D100 = "D100"

    /// danh sách việc nhà
    const val VIECNHA_TYPE = "VIECNHA_TYPE"

    /// danh sách Độ tuổi
    const val RANGE_AGE_TYPE = "RANGE_AGE"

    const val TARGET_CHAT_NORMAL = "OFF"
    const val TARGET_CHAT_FRIEND = "FRI"
    const val TARGET_CHAT_SYSTEM = "PIE"
    const val TARGET_CHAT_BUSINESS = "BUS"
    const val TARGET_CHAT_GROUP = "GRO"

    //key dùng cho chat group
    const val KEY_GROUP_INTRO = "KEY_INTRO"
    const val KEY_GROUP_ADD = "KEY_ADD_MEM"
    const val KEY_GROUP_LEAVE = "KEY_LEA_MEM"
    const val KEY_GROUP_DELETE = "KEY_DEL_MEM"
    const val KEY_GROUP_CHANGE = "KEY_CHA_MEM"

    //MV102 chat
    const val TEXT_STYLE = "TEX"
    const val LINK_STYLE = "LINK"
    const val LIXI_STYLE = "LIXI"
    const val WITHDRAW_STYLE = "WDRA"
    const val IMAGE_STYLE = "IMG"
    const val IMAGES_STYLE = "IMGS"
    const val PIEPER_STYLE = "PIE"
    const val PIEPER_SHARE = "PIE2"
    const val CHAT_LIVE = "LIVE"
    const val NO_PIEPER_STYLE = "NOPIE"
    const val VOUCHER_STYLE = "VOU"
    const val CALL_STYLE = "CAL"
    const val FILE_STYLE = "FILE"
    const val VIDEO_STYLE = "VIDEO"
    const val VIDEOS_STYLE = "VIDEOS"
    const val UNDO_STYLE = "UNDO"
    const val BUY_STYLE = "BUY3"
    const val BUY_OTP_STYLE = "OTPBUY"
    const val BUY_REJECTED_STYLE = "DENYBUY"
    const val CONTACTS_STYLE = "CON"
    const val LOCATION_STYLE = "LOC"
    const val QUOTE_STYLE = "QUO"
    const val VOICE_STYLE = "REC"
    const val INVITE_STYLE = "IVT"
    const val LICHHEN_STYLE = "LIH"
    const val AGREE_STYLE = "AGR"
    const val REJECT_STYLE = "REJ"
    const val AGREE_NOPCV_STYLE = "SEL"
    const val REJECT_NOPCV_STYLE = "TCN"
    const val DA_PV_STYLE = "ITV"
    const val MISS_STYLE = "MIS"
    const val APPLIED_STYLE = "APP"


    const val MESSAGE_SENDING = 0
    const val MESSAGE_SENT = 1
    const val MESSAGE_RECEIVED = 2
    const val MESSAGE_SEEN = 3

    const val MESSAGE_CHAT = "CHAT"
    const val MESSAGE_CHAT_GROUP = "CHAT_GROUP"
    const val MESSAGE_UPDATE_DELIVERY = "UPDATEDELIVERY"
    const val MESSAGE_START_TYPING = "STARTTYPING"
    const val MESSAGE_END_TYPING = "ENDTYPING"


    //action for C100
    const val PENDING_PV = "PEN"     //đang chờ
    const val AGREE_PV = "AGR"      //đồng ý
    const val REJECT_PV = "REJ"    // từ chối PV  dùng key này nhớ truyền thêm lý do nha!!!!! CV202 nha!!!!
    const val INVITE_NOPCV = "IVT"     //mời nộp cv
    const val AGREE_NOPCV = "SEL"    // đồng ý nộp CV
    const val REJECT_NOPCV = "TCN"    // từ chối nộp CV
    const val DN_ACC = "NOTI-FROM-ADMIN-FOR-DN-ACC"    // Tài khoản doanh nghiệp của bạn đã được phê duyệt
    const val DN_REJ = "NOTI-FROM-ADMIN-FOR-DN-REJ"    // Tài khoản doanh nghiệp của bạn đã bị từ chố



    //dành cho view doanh nghiệp
    const val DN_PENDING_PV = "PEN"     //đang chờ
    const val DN_DESTROY_PV = "DES"     //từ chối tuyển dụng
    const val DN_MISSING_PV = "MIS"     //Lỡ hẹn
    const val DN_INTERVIEW_PV = "ITV"   //đã PV
    const val DN_APPROVE_PV = "APP"     //đồng ý tuyển dụng

    const val HOME_BANNER = "HOME_BANNER"  //get list quảng cáo

    const val GET_INTRO_T100 = "INTRO"

    const val UPDATE_NOTIFY = "UPDATE_NOTIFY"
    const val UPDATE_M200 = "UPDATE_M200"
    const val REFRESH_M200 = "ISREFRESH_M200"
    const val REMOVE_M200 = "ISREMOVEONE_M200"


    //for c2019_listOfTabC100_Noti func
    //0 is all, 1 read, 2 do not read
    const val GET_ALL_NOTI = 0
    const val GET_READ_NOTI = 1
    const val GET_NOT_READ_NOTI = 2


    const val GET_BUSINESS_NOTI = "DN"
    const val GET_CHUNHA_NOTI = "CN"
    const val GET_USER_NOTI = "US"


    /**
     * 2 biến truyền cho PV801//required trong tạo việc nhà
     */
    const val PUBLISH_JOBS = "PUB"// Publish
    const val DRAFT_JOBS = "DRA"// Draft

    /**
     * 2 biến truyền cho PV808//required trong tạo việc nhà
     * PV808 // Tùy chọn tìm nhân viên
     */
    const val MANUAL_FIND = "MANUAL"// Bạn tự tìm
    const val AUTO_FIND = "AUTO"// Hệ thống tìm


    // Đăng ký dịch vụ việc postListMedia
    const val SIGN_UP_MEDIA = "SIGNUPMEDIA"// Hệ thống tìm

    /**
     * NV130: {type: String}, // Tình trạng đăng ký "Dịch vụ việc nhà"
     * CON: Đã duyệt
     * WAI: Đã đăng ký chờ xác nhận
     * REJ: Đã từ chối
     * BAN: Vi phạm điều khoản
     */
    const val CONFIRMED_STATUS = "CON"
    const val WAITING_STATUS = "WAI"
    const val REJECTED_STATUS = "REJ"
    const val BANNED_STATUS = "BAN"


    //JV101 = WAI -> bao gia, APP -> da bao gia, LIH - lich hen, HIS - lich su
    const val WAITTING_JOBS_LIST_NV = "WAI"
    const val APPLY_JOBS_LIST_NV = "APP"
    const val SCHEDULE_JOBS_LIST_NV = "LIH"
    const val HISTORY_JOBS_LIST_NV = "HIS"

    const val HISTORY_JOBS_LIST_CN = "HIS"
    //JV101 = WAI
    /**
     * JV101: {type: String}, // Tình trạng báo giá "Dịch vụ việc nhà"
     *  WAI: Đã báo giá chờ xác nhận
     *
     * WAITING chờ NV báo giá, CN da yeu cau bao gia
     * PENDING chờ CN xác nhận, NV da bao gia theo yeu cau
     *
     */
    const val WAITTING_JOBS_OFFER = "WAI"
    const val PENDING_JOBS_OFFER = "PEN"
    const val APPROVE_JOBS_OFFER = "APP"    //lịch hẹn
    const val HISTORY_JOBS_OFFER = "HIS"    //lịch sử


    //ACTION for JV101 at  j2020_actionOfTabJ100ForCN
    const val CANCEL_ACTION = "CAN" //cancel boi chu nha
    const val START_ACTION = "STA" //da bat dau lam viec
    const val END_ACTION = "END" //da xong lam viec dành cho NHÂN VIÊN
    const val DONE_ACTION = "DON" //da xong lam viec dành cho CHỦ NHÀ


    // FILTER // Chuỗi dữ liệu muốn lọc ra
    const val NEAR_JOBS = "NEAR"   // Gần đây (CONDITION = [LAT,LONG])
    const val LATEST_JOBS = "LATEST"   // Mới nhất


//    STATUS_VIECNHA: {
//        WAIT: 'WAI', //yeu cau bao gia
//        LIH: 'LIH', // Lich h
//        MIS: 'MIS',
//        CAN: 'CAN', //cancel boi chu nha
//        STA: 'STA', //da bat dau lam viec
//        DON: 'DON', //da xong lam viec
//        HIS: 'HIS', // lish su giao dich
//        APP: 'APP', //Cn dong y bao gia - di qua lich hen
//        PEN: 'PEN' //da bao gia theo yeu cau
//    },


    //livestream
    const val SHAREDPREF_FIRST_LIVE = "first.live"

    //trạng thái cho biến NV135 trong K100  NV135
    const val BUSSINESS_CONFIRM = "CON"      //gọi detailK100_CON
    const val BUSSINESS_BANDED = "BAN"       //gọi detailK100_CON
    const val BUSSINESS_WAITING = "WAI"      //gọi detailK100Wai
    const val BUSSINESS_REJETCED = "REJ"     //gọi detailK100Wai


    const val VIECNHA_NOTI_MARK = "VN"
    const val TIMVIEC_NOTI_MARK = "TV"


    //LV301 cho noti
    const val VIECNHA_NOTI = "VN"
    const val TIMVIEC_NOTI = "TV"
    const val TATCA_NOTI = "ALL"

    //LV302 cho not tìm việc
    const val USER_NOTI = "US"
    const val BUSINESS_NOTI = "BS"

    //LV302 cho not  việc nha
    const val USER_PARTTIME_NOTI = "CN"
    const val BUSINESS_PARTTIME_NOTI = "DN"
}
