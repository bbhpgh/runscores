/*
 Navicat Premium Data Transfer

 Source Server         : mysql_local
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : runscore

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 17/06/2019 22:17:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account_change_log
-- ----------------------------
DROP TABLE IF EXISTS `account_change_log`;
CREATE TABLE `account_change_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_change_amount` double DEFAULT NULL,
  `account_change_time` datetime(0) DEFAULT NULL,
  `account_change_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `game_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `issue_num` bigint(20) DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cash_deposit` double DEFAULT NULL,
  `commission` double DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account_change_log
-- ----------------------------
INSERT INTO `account_change_log` VALUES ('1119688874247847936', -400, '2019-04-21 03:46:45', '3', NULL, NULL, NULL, '1119688829557538816', '1115264086179840000', 0, NULL, 2414.4, NULL);
INSERT INTO `account_change_log` VALUES ('1119688937636364288', 4.8, '2019-04-21 03:47:00', '4', NULL, NULL, NULL, '1119688937636364288', '1115264086179840000', 0, '接单返水率:1.2%', 2419.2, NULL);
INSERT INTO `account_change_log` VALUES ('1119688991545753600', -400, '2019-04-21 03:47:13', '3', NULL, NULL, NULL, '1119688976685334528', '1115264086179840000', 0, NULL, 2019.2, NULL);
INSERT INTO `account_change_log` VALUES ('1119689117840441344', 400, '2019-04-21 03:47:43', '6', NULL, NULL, NULL, '1119688976685334528', '1115264086179840000', 0, NULL, 2419.2, NULL);
INSERT INTO `account_change_log` VALUES ('1119818767572926464', -300, '2019-04-21 12:22:54', '3', NULL, NULL, NULL, '1119818705870520320', '1115264086179840000', 0, NULL, 2119.2, NULL);
INSERT INTO `account_change_log` VALUES ('1119818823923400704', 3.6, '2019-04-21 12:23:08', '4', NULL, NULL, NULL, '1119818823923400704', '1115264086179840000', 0, '接单返水率:1.2%', 2122.8, NULL);
INSERT INTO `account_change_log` VALUES ('1119820187353219072', -300, '2019-04-21 12:28:33', '3', NULL, NULL, NULL, '1119819151049752576', '1115264086179840000', 0, NULL, 1822.8, NULL);
INSERT INTO `account_change_log` VALUES ('1119866776973213696', -100, '2019-04-21 15:33:40', '3', NULL, NULL, NULL, '1119838679796285440', '1115264086179840000', 0, NULL, 1722.8, NULL);
INSERT INTO `account_change_log` VALUES ('1120015474696912896', 1.2, '2019-04-22 01:24:33', '4', NULL, NULL, NULL, '1120015474696912896', '1115264086179840000', 0, '接单返水率:1.2%', 1724, NULL);
INSERT INTO `account_change_log` VALUES ('1120363663417409536', 200, '2019-04-23 00:28:07', '7', NULL, NULL, NULL, NULL, '1117282076584837120', 0, NULL, 633, NULL);
INSERT INTO `account_change_log` VALUES ('1120364130318942208', 200, '2019-04-23 00:29:59', '8', NULL, NULL, NULL, NULL, '1117282076584837120', 0, NULL, 833, NULL);
INSERT INTO `account_change_log` VALUES ('1120364324024483840', -200, '2019-04-23 00:30:45', '9', NULL, NULL, NULL, NULL, '1117282076584837120', 0, NULL, 633, NULL);
INSERT INTO `account_change_log` VALUES ('1120364344698208256', -200, '2019-04-23 00:30:50', '9', NULL, NULL, NULL, NULL, '1117282076584837120', 0, NULL, 433, NULL);
INSERT INTO `account_change_log` VALUES ('1120364594871664640', 1000, '2019-04-23 00:31:49', '8', NULL, NULL, NULL, NULL, '1117282076584837120', 0, NULL, 1433, NULL);
INSERT INTO `account_change_log` VALUES ('1120885655707058176', -100, '2019-04-24 11:02:20', '3', NULL, NULL, NULL, '1119838679796285441', '1115264086179840000', 0, NULL, 1624, NULL);
INSERT INTO `account_change_log` VALUES ('1120886396232400896', -100, '2019-04-24 11:05:17', '3', NULL, NULL, NULL, '1119838679796285442', '1115264086179840000', 0, NULL, 1524, NULL);
INSERT INTO `account_change_log` VALUES ('1120887917233831936', -100, '2019-04-24 11:11:19', '3', NULL, NULL, NULL, '1119838679800479744', '1115264086179840000', 0, NULL, 1424, NULL);
INSERT INTO `account_change_log` VALUES ('1120911815551746048', 1.2, '2019-04-24 12:46:17', '4', NULL, NULL, NULL, '1120911815551746048', '1115264086179840000', 0, '接单返水率:1.2%', 1425.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087167846678528', -100, '2019-04-25 00:23:04', '3', NULL, NULL, NULL, '1119838679842422784', '1115264086179840000', 0, NULL, 1325.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087174108774400', -100, '2019-04-25 00:23:06', '3', NULL, NULL, NULL, '1119838679842422785', '1115264086179840000', 0, NULL, 1225.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087175643889664', -100, '2019-04-25 00:23:06', '3', NULL, NULL, NULL, '1119838679846617088', '1115264086179840000', 0, NULL, 1125.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087180148572160', -100, '2019-04-25 00:23:07', '3', NULL, NULL, NULL, '1119838679821451264', '1115264086179840000', 0, NULL, 1025.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087180643500032', -100, '2019-04-25 00:23:07', '3', NULL, NULL, NULL, '1119838679863394304', '1115264086179840000', 0, NULL, 925.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087186159009792', -100, '2019-04-25 00:23:09', '3', NULL, NULL, NULL, '1119838679875977217', '1115264086179840000', 0, NULL, 825.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087197647208448', -100, '2019-04-25 00:23:11', '3', NULL, NULL, NULL, '1119838679880171520', '1115264086179840000', 0, NULL, 725.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087203246604288', -100, '2019-04-25 00:23:13', '3', NULL, NULL, NULL, '1119838679880171521', '1115264086179840000', 0, NULL, 625.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087209110241280', -100, '2019-04-25 00:23:14', '3', NULL, NULL, NULL, '1119838679875977216', '1115264086179840000', 0, NULL, 525.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087214453784576', -100, '2019-04-25 00:23:15', '3', NULL, NULL, NULL, '1119838679875977219', '1115264086179840000', 0, NULL, 425.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087220237729792', -100, '2019-04-25 00:23:17', '3', NULL, NULL, NULL, '1119838679875977218', '1115264086179840000', 0, NULL, 325.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087228932521984', -100, '2019-04-25 00:23:19', '3', NULL, NULL, NULL, '1119838679871782912', '1115264086179840000', 0, NULL, 225.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087234544500736', -100, '2019-04-25 00:23:20', '3', NULL, NULL, NULL, '1119838679834034176', '1115264086179840000', 0, NULL, 125.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121087243042160640', -100, '2019-04-25 00:23:22', '3', NULL, NULL, NULL, '1119838679938891776', '1115264086179840000', 0, NULL, 25.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121452371277250560', -300, '2019-04-26 00:34:16', '3', NULL, NULL, NULL, '1121452339853524992', '1115264086179840000', 0, NULL, 1700, NULL);
INSERT INTO `account_change_log` VALUES ('1121456508865871872', -200, '2019-04-26 00:50:42', '3', NULL, NULL, NULL, '1121456487311343616', '1115264086179840000', 0, NULL, 1500, NULL);
INSERT INTO `account_change_log` VALUES ('1121458414220738560', 300, '2019-04-26 00:58:16', '11', NULL, NULL, NULL, '1121452339853524992', '1115264086179840000', 0, NULL, 1800, NULL);
INSERT INTO `account_change_log` VALUES ('1121460467751976960', 100, '2019-04-26 01:06:26', '10', NULL, NULL, NULL, '1121456487311343616', '1115264086179840000', 0, NULL, 1900, NULL);
INSERT INTO `account_change_log` VALUES ('1121460467772948480', 1.2, '2019-04-26 01:06:26', '4', NULL, NULL, NULL, '1121460467772948480', '1115264086179840000', 0, '接单返水率:1.2%', 1901.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121465223845249024', -100, '2019-04-26 01:25:20', '3', NULL, NULL, NULL, '1121465208045305856', '1115264086179840000', 0, NULL, 1801.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121467398143082496', 100, '2019-04-26 01:33:58', '11', NULL, NULL, NULL, '1121465208045305856', '1115264086179840000', 0, NULL, 1901.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121468162953445376', -100, '2019-04-26 01:37:01', '3', NULL, NULL, NULL, '1121468145953931264', '1115264086179840000', 0, NULL, 1801.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121611524176084992', -100, '2019-04-26 11:06:41', '3', NULL, NULL, NULL, '1121611509810593792', '1115264086179840000', 0, NULL, 1701.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121871455303237632', -100, '2019-04-27 04:19:33', '3', NULL, NULL, NULL, '1121871440556064768', '1115264086179840000', 0, NULL, 1601.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121979213012795392', 100, '2019-04-27 11:27:44', '11', NULL, NULL, NULL, '1121871440556064768', '1115264086179840000', 0, NULL, 1701.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121979695621996544', 70, '2019-04-27 11:29:39', '10', NULL, NULL, NULL, '1121611509810593792', '1115264086179840000', 0, NULL, 1771.2, NULL);
INSERT INTO `account_change_log` VALUES ('1121979695659745280', 0.36, '2019-04-27 11:29:39', '4', NULL, NULL, NULL, '1121979695659745280', '1115264086179840000', 0, '接单返水率:1.2%', 1771.56, NULL);
INSERT INTO `account_change_log` VALUES ('1121981030379552768', -100, '2019-04-27 11:34:58', '3', NULL, NULL, NULL, '1121871435774558208', '1115264086179840000', 0, NULL, 1671.56, NULL);
INSERT INTO `account_change_log` VALUES ('1122000472127832064', -100, '2019-04-27 12:52:13', '3', NULL, NULL, NULL, '1122000451554770944', '1115264086179840000', 0, NULL, 1571.56, NULL);
INSERT INTO `account_change_log` VALUES ('1122025059876077568', -100, '2019-04-27 14:29:55', '3', NULL, NULL, NULL, '1122024813628489728', '1115264086179840000', 0, NULL, 1471.56, NULL);
INSERT INTO `account_change_log` VALUES ('1122045806006763520', -200, '2019-04-27 15:52:21', '3', NULL, NULL, NULL, '1122045791460917248', '1115264086179840000', 0, NULL, 1271.56, NULL);

-- ----------------------------
-- Table structure for appeal
-- ----------------------------
DROP TABLE IF EXISTS `appeal`;
CREATE TABLE `appeal`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appeal_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `initiation_time` datetime(0) DEFAULT NULL,
  `merchant_order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `actual_pay_amount` double DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_sreenshot_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `process_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `merchant_sreenshot_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `initiator_obj` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of appeal
-- ----------------------------
INSERT INTO `appeal` VALUES ('1122041203257245696', '3', '2019-04-27 15:34:04', '1122024813628489728', NULL, '2', NULL, '5', '1122041202959450112', 'merchant');
INSERT INTO `appeal` VALUES ('1121999780252221440', '3', '2019-04-27 12:49:28', '1121871435774558208', NULL, '1', NULL, NULL, '1121999780176723968', 'merchant');
INSERT INTO `appeal` VALUES ('1122000518927876096', '2', '2019-04-27 12:52:24', '1122000451554770944', 33, '1', '1122000615971487744', NULL, '1122000518864961536', 'merchant');

-- ----------------------------
-- Table structure for config_item
-- ----------------------------
DROP TABLE IF EXISTS `config_item`;
CREATE TABLE `config_item`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `config_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `config_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `config_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_item
-- ----------------------------
INSERT INTO `config_item` VALUES ('1087199613623992320', 'recharge.effectiveDuration', '充值-订单有效时长', '300', 0);
INSERT INTO `config_item` VALUES ('1087368191623036928', 'muspay.fxid', 'muspay聚合支付-商户ID', '你的商户id', 0);
INSERT INTO `config_item` VALUES ('1087368191631425536', 'muspay.secret', 'muspay聚合支付-密钥', '你的密钥', 0);
INSERT INTO `config_item` VALUES ('1087397461661581312', 'muspay.payUrl', 'muspay聚合支付-支付接口地址', 'https://api.xxv.cn/Pay', 0);
INSERT INTO `config_item` VALUES ('1102214508572573696', 'muspay.asynNoticeUrl', 'muspay聚合支付-异步通知地址', 'http://mi2cig.natappfree.cc/recharge/muspayCallback', 3);
INSERT INTO `config_item` VALUES ('1102214508685819904', 'muspay.ssynNoticeUrl', 'muspay聚合支付-同步通知地址', 'http://ts2tcq.natappfree.cc/pay-success', 2);
INSERT INTO `config_item` VALUES ('1102214508685819111', 'storageUrl', '存储对象下载地址', 'http://localhost:8080/storage/fetch/', 2);
INSERT INTO `config_item` VALUES ('1116910378048028672', 'abcyzf.pid', 'abcyzf商户id', '你的商户id', 1);
INSERT INTO `config_item` VALUES ('1116910522596327424', 'abcyzf.notifyUrl', 'abcyzf异步通知地址', 'http://mi2cig.natappfree.cc/recharge/callback/abcyzfCallback', 2);
INSERT INTO `config_item` VALUES ('1116910633418227712', 'abcyzf.returnUrl', 'abcyzf同步跳转地址', 'http://baidu.com', 1);
INSERT INTO `config_item` VALUES ('1116910727974617088', 'abcyzf.secretKey', 'abcyzf secretKey', '你的密钥', 1);
INSERT INTO `config_item` VALUES ('1117141929813868544', 'abcyzf.name', 'abcyzf商品名', '日用品', 0);
INSERT INTO `config_item` VALUES ('1117142118343639040', 'abcyzf.payUrl', 'abcyzf支付地址', 'http://pays.sddyun.cn/submit.php', 0);

-- ----------------------------
-- Table structure for dict_item
-- ----------------------------
DROP TABLE IF EXISTS `dict_item`;
CREATE TABLE `dict_item`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_item_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dict_item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dict_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` double DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKjld3d2k4ap5lmg137axsc9r8m`(`dict_type_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_item
-- ----------------------------
INSERT INTO `dict_item` VALUES ('1105870811111096321', '5', '人工取消', '1105870811018821632', 5, 0);
INSERT INTO `dict_item` VALUES ('1105870811111096320', '4', '超时取消', '1105870811018821632', 4, 0);
INSERT INTO `dict_item` VALUES ('1105870811106902016', '3', '已结算', '1105870811018821632', 3, 0);
INSERT INTO `dict_item` VALUES ('1105870811102707712', '2', '已支付', '1105870811018821632', 2, 0);
INSERT INTO `dict_item` VALUES ('1121782127990407168', '2', '改为实际支付金额', '1121452882311249920', 2, 0);
INSERT INTO `dict_item` VALUES ('1117358763645337600', 'wechat', '微信', '1099166491925807104', 1, 0);
INSERT INTO `dict_item` VALUES ('1117358763687280640', 'alipay', '支付宝', '1099166491925807104', 2, 0);
INSERT INTO `dict_item` VALUES ('1087722503586971648', '1', '充值', '1087722503477919744', 1, 0);
INSERT INTO `dict_item` VALUES ('1087722503586971649', '2', '提现', '1087722503477919744', 1, 0);
INSERT INTO `dict_item` VALUES ('1098859553321123840', '1', '启用', '1098859552956219392', 1, 0);
INSERT INTO `dict_item` VALUES ('1098859553329512448', '0', '禁用', '1098859552956219392', 2, 0);
INSERT INTO `dict_item` VALUES ('1105870811094319104', '1', '待支付', '1105870811018821632', 1, 0);
INSERT INTO `dict_item` VALUES ('1099267380988608512', '1', '发起提现', '1099267380833419264', 1, 0);
INSERT INTO `dict_item` VALUES ('1099267380992802816', '2', '审核通过', '1099267380833419264', 2, 0);
INSERT INTO `dict_item` VALUES ('1099267381009580032', '3', '审核不通过', '1099267380833419264', 2, 0);
INSERT INTO `dict_item` VALUES ('1099267381009580033', '4', '已到帐', '1099267380833419264', 2, 0);
INSERT INTO `dict_item` VALUES ('1121452767181799424', '2', '已完结', '1121066481761648640', 2, 0);
INSERT INTO `dict_item` VALUES ('1115665130961502209', '2', '异常', '1115665030281428992', 2, 0);
INSERT INTO `dict_item` VALUES ('1121783158149218304', 'user', '用户', '1121782956281561088', 1, 0);
INSERT INTO `dict_item` VALUES ('1121783158149218305', 'merchant', '商户', '1121782956281561088', 2, 0);
INSERT INTO `dict_item` VALUES ('1121610831608414208', '1', '等待接单', '1098626328485167104', 1, 0);
INSERT INTO `dict_item` VALUES ('1121610831641968640', '2', '已接单', '1098626328485167104', 2, 0);
INSERT INTO `dict_item` VALUES ('1121610831641968641', '3', '平台已确认支付', '1098626328485167104', 3, 0);
INSERT INTO `dict_item` VALUES ('1121610831641968642', '4', '已支付', '1098626328485167104', 4, 0);
INSERT INTO `dict_item` VALUES ('1121610831641968643', '5', '超时取消', '1098626328485167104', 5, 0);
INSERT INTO `dict_item` VALUES ('1121610831646162944', '6', '人工取消', '1098626328485167104', 6, 0);
INSERT INTO `dict_item` VALUES ('1121610831646162945', '7', '商户取消订单', '1098626328485167104', 7, 0);
INSERT INTO `dict_item` VALUES ('1121610831646162946', '9', '客服取消订单退款', '1098626328485167104', 8, 0);
INSERT INTO `dict_item` VALUES ('1121777333028519936', '1', '未支付申请取消订单', '1120978122905223168', 1, 0);
INSERT INTO `dict_item` VALUES ('1121777333062074368', '2', '实际支付金额小于收款金额', '1120978122905223168', 2, 0);
INSERT INTO `dict_item` VALUES ('1121777333066268672', '3', '已支付用户未进行确认', '1120978122905223168', 3, 0);
INSERT INTO `dict_item` VALUES ('1121782127994601472', '3', '取消订单', '1121452882311249920', 3, 0);
INSERT INTO `dict_item` VALUES ('1121782127994601473', '4', '不做处理', '1121452882311249920', 4, 0);
INSERT INTO `dict_item` VALUES ('1121782127994601474', '5', '商户撤销申诉', '1121452882311249920', 5, 0);
INSERT INTO `dict_item` VALUES ('1120889480388018176', 'admin', '管理员', '1103178577832050688', 1, 0);
INSERT INTO `dict_item` VALUES ('1120889480429961216', 'agent', '代理', '1103178577832050688', 2, 0);
INSERT INTO `dict_item` VALUES ('1118142200362827776', '2', '停止接单', '1118141051320664064', 2, 0);
INSERT INTO `dict_item` VALUES ('1118142200316690432', '1', '正在接单', '1118141051320664064', 1, 0);
INSERT INTO `dict_item` VALUES ('1120889480434155520', 'member', '会员', '1103178577832050688', 3, 0);
INSERT INTO `dict_item` VALUES ('1120889480434155521', 'merchant', '商户', '1103178577832050688', 4, 0);
INSERT INTO `dict_item` VALUES ('1121782127944269824', '1', '用户撤销申诉', '1121452882311249920', 1, 0);
INSERT INTO `dict_item` VALUES ('1121452767152439296', '1', '待处理', '1121066481761648640', 1, 0);
INSERT INTO `dict_item` VALUES ('1121459252213317632', '1', '账号充值', '1105159258481098752', 1, 0);
INSERT INTO `dict_item` VALUES ('1121459252217511936', '2', '充值优惠', '1105159258481098752', 2, 0);
INSERT INTO `dict_item` VALUES ('1121459252217511937', '3', '接单扣款', '1105159258481098752', 3, 0);
INSERT INTO `dict_item` VALUES ('1121459252217511938', '4', '接单奖励金', '1105159258481098752', 4, 0);
INSERT INTO `dict_item` VALUES ('1121459252217511939', '5', '账号提现', '1105159258481098752', 5, 0);
INSERT INTO `dict_item` VALUES ('1121459252217511940', '6', '退还保证金', '1105159258481098752', 6, 0);
INSERT INTO `dict_item` VALUES ('1121459252221706240', '7', '提现不符退款', '1105159258481098752', 7, 0);
INSERT INTO `dict_item` VALUES ('1121459252225900544', '8', '手工增保证金', '1105159258481098752', 8, 0);
INSERT INTO `dict_item` VALUES ('1121459252225900545', '9', '手工减保证金', '1105159258481098752', 9, 0);
INSERT INTO `dict_item` VALUES ('1121459252225900546', '10', '改单为实际支付金额并退款', '1105159258481098752', 10, 0);
INSERT INTO `dict_item` VALUES ('1121459252225900547', '11', '客服取消订单退款', '1105159258481098752', 11, 0);
INSERT INTO `dict_item` VALUES ('1115665130961502208', '1', '正常', '1115665030281428992', 1, 0);
INSERT INTO `dict_item` VALUES ('1115641794437054464', 'alipay', '支付宝', '1115641671787216896', 2, 0);
INSERT INTO `dict_item` VALUES ('1115641794432860160', 'wechat', '微信', '1115641671787216896', 1, 0);

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `dict_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES ('1105870811018821632', 'rechargeOrderState', '充值订单状态', 0, NULL);
INSERT INTO `dict_type` VALUES ('1103178577832050688', 'accountType', '账号类型', 0, NULL);
INSERT INTO `dict_type` VALUES ('1118141051320664064', 'receiveOrderState', '接单状态', 0, '');
INSERT INTO `dict_type` VALUES ('1087722503477919744', 'rechargeWithdrawLogOrderType', '充提日志订单类型', 1, NULL);
INSERT INTO `dict_type` VALUES ('1098626328485167104', 'platformOrderState', '商户订单状态', 2, NULL);
INSERT INTO `dict_type` VALUES ('1098859552956219392', 'accountState', '账号状态', 0, NULL);
INSERT INTO `dict_type` VALUES ('1099166491925807104', 'rechargeWay', '充值方式', 0, NULL);
INSERT INTO `dict_type` VALUES ('1099267380833419264', 'withdrawRecordState', '提现记录状态', 4, '');
INSERT INTO `dict_type` VALUES ('1120978122905223168', 'appealType', '申诉类型', 0, '');
INSERT INTO `dict_type` VALUES ('1105159258481098752', 'accountChangeType', '账变类型', 0, NULL);
INSERT INTO `dict_type` VALUES ('1115641671787216896', 'gatheringChannel', '收款渠道', 0, '');
INSERT INTO `dict_type` VALUES ('1115665030281428992', 'gatheringCodeState', '收款码状态', 0, '');
INSERT INTO `dict_type` VALUES ('1121066481761648640', 'appealState', '申诉状态', 0, '');
INSERT INTO `dict_type` VALUES ('1121452882311249920', 'appealProcessWay', '申诉处理方式', 0, '');
INSERT INTO `dict_type` VALUES ('1121782956281561088', 'appealInitiatorObj', '申诉发起方', 1, '');

-- ----------------------------
-- Table structure for gathering_code
-- ----------------------------
DROP TABLE IF EXISTS `gathering_code`;
CREATE TABLE `gathering_code`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `gathering_amount` double DEFAULT NULL,
  `gathering_channel_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `payee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `storage_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fixed_gathering_amount` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gathering_code
-- ----------------------------
INSERT INTO `gathering_code` VALUES ('1122025015693279232', '2019-04-27 14:29:45', 100, 'wechat', '黄振华', '1', '1115264086179840000', '1122025014669869056', b'0');

-- ----------------------------
-- Table structure for invite_code
-- ----------------------------
DROP TABLE IF EXISTS `invite_code`;
CREATE TABLE `invite_code`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `period_of_validity` datetime(0) DEFAULT NULL,
  `invitee_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `inviter_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invite_code
-- ----------------------------
INSERT INTO `invite_code` VALUES ('1105688465493721088', '1d86ca', '2019-03-13 12:34:08', '2019-04-25 12:34:08', '1120380067894001664', '1100805062633979904');

-- ----------------------------
-- Table structure for invite_register_setting
-- ----------------------------
DROP TABLE IF EXISTS `invite_register_setting`;
CREATE TABLE `invite_register_setting`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `lately_update_time` datetime(0) DEFAULT NULL,
  `effective_duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of invite_register_setting
-- ----------------------------
INSERT INTO `invite_register_setting` VALUES ('1105679714787262464', b'1', '2019-04-23 01:29:11', 30);

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `secret_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `relevance_account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `merchant_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('2', '2019-04-15 01:03:30', '腾讯', '456', 5, '1120900361515696128', '1001');
INSERT INTO `merchant` VALUES ('1120900453878464512', '2019-04-24 12:01:08', 'alipay', '4343', 1, '1120889791697649664', '1000');

-- ----------------------------
-- Table structure for merchant_order
-- ----------------------------
DROP TABLE IF EXISTS `merchant_order`;
CREATE TABLE `merchant_order`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gathering_amount` double DEFAULT NULL,
  `gathering_channel_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `submit_time` datetime(0) DEFAULT NULL,
  `order_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `platform_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `received_account_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `received_time` datetime(0) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `confirm_time` datetime(0) DEFAULT NULL,
  `platform_confirm_time` datetime(0) DEFAULT NULL,
  `bounty` double DEFAULT NULL,
  `bounty_settlement_time` datetime(0) DEFAULT NULL,
  `deal_time` datetime(0) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `useful_time` datetime(0) DEFAULT NULL,
  `merchant_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gathering_code_storage_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant_order
-- ----------------------------
INSERT INTO `merchant_order` VALUES ('1122045791460917248', 200, 'wechat', '1122045791460917248', '2019-04-27 15:52:18', '2', NULL, '1115264086179840000', '2019-04-27 15:52:21', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-27 16:02:18', '2', '1122025014669869056');
INSERT INTO `merchant_order` VALUES ('1122044776686813184', 444, 'wechat', '1122044776686813184', '2019-04-27 15:48:16', '7', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2019-04-27 15:48:36', NULL, '2019-04-27 15:58:16', '2', NULL);
INSERT INTO `merchant_order` VALUES ('1122044836824743936', 333, 'alipay', '1122044836824743936', '2019-04-27 15:48:30', '7', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2019-04-27 15:48:33', NULL, '2019-04-27 15:58:30', '2', NULL);
INSERT INTO `merchant_order` VALUES ('1122044574072569856', 190, 'wechat', '1122044574072569856', '2019-04-27 15:47:28', '7', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2019-04-27 15:48:38', NULL, '2019-04-27 15:57:28', '2', NULL);
INSERT INTO `merchant_order` VALUES ('1122031623898923008', 100, 'wechat', '1122031623898923008', '2019-04-27 14:56:00', '7', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, '2019-04-27 15:48:41', NULL, '2019-04-27 15:06:00', '2', NULL);
INSERT INTO `merchant_order` VALUES ('1122024813628489728', 100, 'wechat', '1122024813628489728', '2019-04-27 14:28:56', '2', NULL, '1115264086179840000', '2019-04-27 14:29:55', 1, NULL, NULL, NULL, NULL, NULL, NULL, '2019-04-27 14:38:56', '2', '1122025014669869056');

-- ----------------------------
-- Table structure for platform_order_setting
-- ----------------------------
DROP TABLE IF EXISTS `platform_order_setting`;
CREATE TABLE `platform_order_setting`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lately_update_time` datetime(0) DEFAULT NULL,
  `order_effective_duration` int(11) DEFAULT NULL,
  `return_water_rate` double DEFAULT NULL,
  `return_water_rate_enabled` bit(1) DEFAULT NULL,
  `unfixed_gathering_code_receive_order` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_order_setting
-- ----------------------------
INSERT INTO `platform_order_setting` VALUES ('1117370137402408960', '2019-04-21 12:32:54', 10, 1.2, b'1', b'1');

-- ----------------------------
-- Table structure for recharge_order
-- ----------------------------
DROP TABLE IF EXISTS `recharge_order`;
CREATE TABLE `recharge_order`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pay_time` datetime(0) DEFAULT NULL,
  `recharge_amount` double DEFAULT NULL,
  `recharge_way_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `submit_time` datetime(0) DEFAULT NULL,
  `user_account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deal_time` datetime(0) DEFAULT NULL,
  `useful_time` datetime(0) DEFAULT NULL,
  `pay_url` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `settlement_time` datetime(0) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `actual_pay_amount` double DEFAULT NULL,
  `recharge_preferential_settlement_time` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKsv4xqo59a5iiedsq372mgpu50`(`user_account_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge_order
-- ----------------------------
INSERT INTO `recharge_order` VALUES ('1117152171574427648', NULL, '1117152171574427648', '3', '2019-04-14 03:47:07', 0.01, 'alipay', '2019-04-14 03:46:48', '1115264086179840000', '2019-04-14 03:47:07', '2019-04-14 04:06:48', 'http://pays.sddyun.cn/api/Pay/submit?out_trade_no=1117152171574427648&money=0.01&name=%E6%97%A5%E7%94%A8%E5%93%81&sign=948c6d7e6075c94754341f2b3b7e825c&return_url=http%3A%2F%2Fbaidu.com&pid=230199&type=alipay&notify_url=http%3A%2F%2Fmi2cig.natappfree.cc%2Frecharge%2Fcallback%2FabcyzfCallback&sign_type=MD5', '2019-04-14 03:47:08', 2, 0.01, NULL);

-- ----------------------------
-- Table structure for recharge_setting
-- ----------------------------
DROP TABLE IF EXISTS `recharge_setting`;
CREATE TABLE `recharge_setting`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lately_update_time` datetime(0) DEFAULT NULL,
  `order_effective_duration` int(11) DEFAULT NULL,
  `return_water_rate` double DEFAULT NULL,
  `return_water_rate_enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of recharge_setting
-- ----------------------------
INSERT INTO `recharge_setting` VALUES ('1117370098537988096', '2019-04-14 18:12:46', 20, 0.5, b'1');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `associate_biz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `associate_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file_size` bigint(20) DEFAULT NULL,
  `file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `upload_time` datetime(0) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES ('1121115867522793472', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:17:07', NULL, 0);
INSERT INTO `storage` VALUES ('1121115799507959808', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:16:51', NULL, 0);
INSERT INTO `storage` VALUES ('1121115867510210560', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:17:07', NULL, 0);
INSERT INTO `storage` VALUES ('1121115799445045248', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:16:51', NULL, 0);
INSERT INTO `storage` VALUES ('1121115659044913152', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:16:17', NULL, 0);
INSERT INTO `storage` VALUES ('1121115539574358016', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:15:49', NULL, 0);
INSERT INTO `storage` VALUES ('1121115658847780864', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:16:17', NULL, 0);
INSERT INTO `storage` VALUES ('1121115539561775104', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:15:49', NULL, 0);
INSERT INTO `storage` VALUES ('1121115318547120128', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:14:56', NULL, 0);
INSERT INTO `storage` VALUES ('1121115318534537216', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:14:56', NULL, 0);
INSERT INTO `storage` VALUES ('1121115207335149568', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:14:29', NULL, 0);
INSERT INTO `storage` VALUES ('1121115207347732480', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:14:29', NULL, 0);
INSERT INTO `storage` VALUES ('1121107682137210880', 'appealSreenshot', '1121107682762162176', '4.jpg', 39711, 'image/jpeg', '2019-04-25 01:44:35', NULL, 1);
INSERT INTO `storage` VALUES ('1121107682128822272', 'appealSreenshot', '1121107682783133696', '2.jpg', 19889, 'image/jpeg', '2019-04-25 01:44:35', NULL, 1);
INSERT INTO `storage` VALUES ('1121112334236909568', 'appealSreenshot', '1121112334765391872', '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:03:04', NULL, 1);
INSERT INTO `storage` VALUES ('1121112334291435520', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:03:04', NULL, 0);
INSERT INTO `storage` VALUES ('1121113044810727424', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:05:54', NULL, 0);
INSERT INTO `storage` VALUES ('1121113044823310336', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:05:54', NULL, 0);
INSERT INTO `storage` VALUES ('1121113155569713153', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:06:20', NULL, 0);
INSERT INTO `storage` VALUES ('1121113155569713152', NULL, NULL, '5.jpg', 31031, 'image/jpeg', '2019-04-25 02:06:20', NULL, 0);
INSERT INTO `storage` VALUES ('1121113239300603904', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:06:40', NULL, 0);
INSERT INTO `storage` VALUES ('1121113239308992512', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:06:40', NULL, 0);
INSERT INTO `storage` VALUES ('1121113914889732096', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:09:21', NULL, 0);
INSERT INTO `storage` VALUES ('1121113914940063744', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:09:21', NULL, 0);
INSERT INTO `storage` VALUES ('1121114426565459968', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:11:23', NULL, 0);
INSERT INTO `storage` VALUES ('1121114426582237184', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:11:23', NULL, 0);
INSERT INTO `storage` VALUES ('1121114942125113344', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:13:26', NULL, 0);
INSERT INTO `storage` VALUES ('1121114942137696256', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:13:26', NULL, 0);
INSERT INTO `storage` VALUES ('1121115079937359872', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:13:59', NULL, 0);
INSERT INTO `storage` VALUES ('1121115080046411776', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:13:59', NULL, 0);
INSERT INTO `storage` VALUES ('1121116087400792064', NULL, NULL, '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:17:59', NULL, 0);
INSERT INTO `storage` VALUES ('1121116087413374976', NULL, NULL, '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:17:59', NULL, 0);
INSERT INTO `storage` VALUES ('1121116223405293568', 'appealSreenshot', '1121116223489179648', '2.jpg', 19889, 'image/jpeg', '2019-04-25 02:18:32', NULL, 1);
INSERT INTO `storage` VALUES ('1121116223417876480', 'appealSreenshot', '1121116223489179648', '4.jpg', 39711, 'image/jpeg', '2019-04-25 02:18:32', NULL, 1);
INSERT INTO `storage` VALUES ('1121420254551998464', 'appealSreenshot', '1121420255218892800', '6hd-bg_1_.jpg', 21262, 'image/jpeg', '2019-04-25 22:26:38', NULL, 1);
INSERT INTO `storage` VALUES ('1121420254753325056', 'appealSreenshot', '1121420255218892800', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-25 22:26:38', NULL, 1);
INSERT INTO `storage` VALUES ('1121421288418574336', 'appealSreenshot', '1121421288846393344', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-25 22:30:45', NULL, 1);
INSERT INTO `storage` VALUES ('1121421288494071808', 'appealSreenshot', '1121421288846393344', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-25 22:30:45', NULL, 1);
INSERT INTO `storage` VALUES ('1121456591166504960', 'appealSreenshot', '1121456591267168256', '6hd-bg.jpg', 21262, 'image/jpeg', '2019-04-26 00:51:02', NULL, 1);
INSERT INTO `storage` VALUES ('1121456591200059392', 'appealSreenshot', '1121456591267168256', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-26 00:51:02', NULL, 1);
INSERT INTO `storage` VALUES ('1121460165070028800', 'appealSreenshot', '1121460165158109184', '6hd-bg.jpg', 21262, 'image/jpeg', '2019-04-26 01:05:14', NULL, 1);
INSERT INTO `storage` VALUES ('1121460165086806016', 'appealSreenshot', '1121460165158109184', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-26 01:05:14', NULL, 1);
INSERT INTO `storage` VALUES ('1121466955178442752', 'appealSreenshot', '1121466955253940224', '6hd-bg.jpg', 21262, 'image/jpeg', '2019-04-26 01:32:13', NULL, 1);
INSERT INTO `storage` VALUES ('1121770844909993984', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:39:46', NULL, 0);
INSERT INTO `storage` VALUES ('1121771099890122752', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:40:46', NULL, 0);
INSERT INTO `storage` VALUES ('1121771330295824384', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:41:41', NULL, 0);
INSERT INTO `storage` VALUES ('1121771881863577600', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:43:53', NULL, 0);
INSERT INTO `storage` VALUES ('1121771987094470656', 'appealSreenshot', '1121771987224494080', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:44:18', NULL, 1);
INSERT INTO `storage` VALUES ('1121772364862849024', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:45:48', NULL, 0);
INSERT INTO `storage` VALUES ('1121772454809698304', NULL, NULL, '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:46:09', NULL, 0);
INSERT INTO `storage` VALUES ('1121772541451436032', NULL, NULL, '6hd-bg_1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:46:30', NULL, 0);
INSERT INTO `storage` VALUES ('1121772640571228160', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:46:54', NULL, 0);
INSERT INTO `storage` VALUES ('1121772688533094400', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:47:05', NULL, 0);
INSERT INTO `storage` VALUES ('1121772762403176448', 'appealSreenshot', '1121772762600308736', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 21:47:23', NULL, 1);
INSERT INTO `storage` VALUES ('1121777000671870976', 'appealSreenshot', '1121777001158410240', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 22:04:13', NULL, 1);
INSERT INTO `storage` VALUES ('1121782392965562368', 'appealSreenshot', '1121782393410158592', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 22:25:39', NULL, 1);
INSERT INTO `storage` VALUES ('1121782609706221568', 'appealSreenshot', '1121782609983045632', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-26 22:26:31', NULL, 1);
INSERT INTO `storage` VALUES ('1121871651567304704', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 04:20:20', NULL, 0);
INSERT INTO `storage` VALUES ('1121876340572684288', 'appealSreenshot', '1121876340706902016', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 04:38:58', NULL, 1);
INSERT INTO `storage` VALUES ('1121876340610433024', 'appealSreenshot', '1121876340706902016', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-27 04:38:58', NULL, 1);
INSERT INTO `storage` VALUES ('1121974392461459456', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:08:35', NULL, 0);
INSERT INTO `storage` VALUES ('1121974528629538816', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:09:08', NULL, 0);
INSERT INTO `storage` VALUES ('1121974647194124288', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:09:36', NULL, 0);
INSERT INTO `storage` VALUES ('1121974709022359552', 'appealSreenshot', '1121876340706902016', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:09:51', NULL, 1);
INSERT INTO `storage` VALUES ('1121974897715707904', 'appealSreenshot', '1121876340706902016', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:10:36', NULL, 1);
INSERT INTO `storage` VALUES ('1121975632561963008', 'appealSreenshot', '1121876340706902016', '6hd-bg_1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:13:31', NULL, 1);
INSERT INTO `storage` VALUES ('1121978216400027648', 'appealSreenshot', '1121876340706902016', '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:23:47', NULL, 1);
INSERT INTO `storage` VALUES ('1121978545992630272', 'appealSreenshot', '1121978481912053760', '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:25:05', NULL, 1);
INSERT INTO `storage` VALUES ('1121978615827791872', 'appealSreenshot', '1121978615957815296', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:25:22', NULL, 1);
INSERT INTO `storage` VALUES ('1121979009178009600', 'appealSreenshot', '1121978615957815296', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-27 11:26:56', NULL, 1);
INSERT INTO `storage` VALUES ('1121982474021240832', 'appealSreenshot', '1121982474499391488', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 11:40:42', NULL, 1);
INSERT INTO `storage` VALUES ('1121990866500583424', NULL, NULL, '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:14:03', NULL, 0);
INSERT INTO `storage` VALUES ('1121991604735836160', NULL, NULL, '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-27 12:16:59', NULL, 0);
INSERT INTO `storage` VALUES ('1121991834088767488', 'appealSreenshot', '1121982474499391488', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-27 12:17:54', NULL, 1);
INSERT INTO `storage` VALUES ('1121994244840488960', 'appealSreenshot', '1121994245079564288', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:27:28', NULL, 1);
INSERT INTO `storage` VALUES ('1121996315450933248', 'appealSreenshot', '1121996315534819328', '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:35:42', NULL, 1);
INSERT INTO `storage` VALUES ('1121996974371897344', 'appealSreenshot', '1121996974447394816', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:38:19', NULL, 1);
INSERT INTO `storage` VALUES ('1121997482226614272', 'appealSreenshot', '1121997441101463552', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:40:20', NULL, 1);
INSERT INTO `storage` VALUES ('1121998747711045632', 'appealSreenshot', '1121998714869645312', '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:45:22', NULL, 1);
INSERT INTO `storage` VALUES ('1121999206433685504', 'appealSreenshot', '1121999206500794368', '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:47:11', NULL, 1);
INSERT INTO `storage` VALUES ('1121999320367759360', 'appealSreenshot', '1121999206500794368', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-27 12:47:38', NULL, 1);
INSERT INTO `storage` VALUES ('1121999780176723968', 'appealSreenshot', '1121999780252221440', '6hd-bg_1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:49:28', NULL, 1);
INSERT INTO `storage` VALUES ('1122000518864961536', 'appealSreenshot', '1122000518927876096', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 12:52:24', NULL, 1);
INSERT INTO `storage` VALUES ('1122000615971487744', 'appealSreenshot', '1122000518927876096', '65091eebc48899298171c2eb6696fe27.jpg', 179318, 'image/jpeg', '2019-04-27 12:52:47', NULL, 1);
INSERT INTO `storage` VALUES ('1122025014669869056', 'gatheringCode', '1122025015693279232', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 14:29:44', NULL, 1);
INSERT INTO `storage` VALUES ('1122040905956589568', NULL, NULL, '6hd-bg_1_ _1_.jpg', 21262, 'image/jpeg', '2019-04-27 15:32:53', NULL, 0);
INSERT INTO `storage` VALUES ('1122041202959450112', 'appealSreenshot', '1122041203257245696', '6hd-bg _1_.jpg', 21262, 'image/jpeg', '2019-04-27 15:34:04', NULL, 1);

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lately_login_time` datetime(0) DEFAULT NULL,
  `login_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `money_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `registered_time` datetime(0) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `account_holder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank_card_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank_info_lately_modify_time` datetime(0) DEFAULT NULL,
  `open_account_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `inviter_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `cash_deposit` double DEFAULT NULL,
  `receive_order_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES ('1100805062633979904', '2019-04-14 16:37:07', '$2a$10$Mq0ErivWBfHhg/8WvpBAbusAEmoi23T3OnHmIDMikEOSkThwDps5u', '$2a$10$Mq0ErivWBfHhg/8WvpBAbusAEmoi23T3OnHmIDMikEOSkThwDps5u', '黄振华', '2019-02-28 01:09:14', 'zohar001', 48, NULL, NULL, NULL, NULL, '1', '1086574195480985600', 'admin', 5100, '1');
INSERT INTO `user_account` VALUES ('1115264086179840000', '2019-04-27 15:51:50', '$2a$10$MLmNGluUsDfzYMkOagXfG.jpTwN9clDQkC/S1ZFW6Pp3991JYTCl6', '$2a$10$l4Fj8oHEQaCx5ZK9rOpUZu5RVXFyqpegPrd/Y8VzDgBACsCtDmkZq', '黄振华', '2019-04-08 22:44:13', 'test', 302, '22', '33', '2019-04-09 00:21:01', '11', '1', NULL, 'member', 1271.56, '1');
INSERT INTO `user_account` VALUES ('1120900361515696128', NULL, '$2a$10$T4AQqmwwx75vmsc8WcfaseOLz25g.tMAAGeDMCsJNbUAHi8y6tpqO', '$2a$10$T4AQqmwwx75vmsc8WcfaseOLz25g.tMAAGeDMCsJNbUAHi8y6tpqO', '腾讯', '2019-04-24 12:00:46', 'tencent', 0, NULL, NULL, NULL, NULL, '1', NULL, 'merchant', 0, '2');

-- ----------------------------
-- Table structure for v_recharge_withdraw_log
-- ----------------------------
DROP TABLE IF EXISTS `v_recharge_withdraw_log`;
CREATE TABLE `v_recharge_withdraw_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `submit_time` datetime(0) DEFAULT NULL,
  `user_account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for withdraw_record
-- ----------------------------
DROP TABLE IF EXISTS `withdraw_record`;
CREATE TABLE `withdraw_record`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account_holder` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank_card_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `approval_time` datetime(0) DEFAULT NULL,
  `open_account_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `submit_time` datetime(0) DEFAULT NULL,
  `user_account_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `withdraw_amount` double DEFAULT NULL,
  `confirm_credited_time` datetime(0) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of withdraw_record
-- ----------------------------
INSERT INTO `withdraw_record` VALUES ('1099237103696347136', '李嘉诚', '62218884343983933355', '2019-02-23 22:12:00', '工商银行', '1099237103696347136', '3', '2019-02-23 17:18:43', '1086574195480985600', 50, '2019-02-23 21:55:00', '777');
INSERT INTO `withdraw_record` VALUES ('1099293849051725824', '李嘉诚', '62218884343983933355', '2019-02-23 21:55:16', '工商银行', '1099293849051725824', '4', '2019-02-23 21:04:12', '1086574195480985600', 1, '2019-02-23 22:12:06', '555');
INSERT INTO `withdraw_record` VALUES ('1115314582861119488', '22', '33', '2019-04-09 11:57:18', '11', '1115314582861119488', '4', '2019-04-09 02:04:53', '1115264086179840000', 4, '2019-04-09 11:57:33', '');
INSERT INTO `withdraw_record` VALUES ('1115314670954086400', '22', '33', NULL, '11', '1115314670954086400', '1', '2019-04-09 02:05:14', '1115264086179840000', 1, NULL, NULL);
INSERT INTO `withdraw_record` VALUES ('1115463787109941248', '22', '33', NULL, '11', '1115463787109941248', '1', '2019-04-09 11:57:46', '1115264086179840000', 3, NULL, NULL);
INSERT INTO `withdraw_record` VALUES ('1117447923211173888', '22', '33', NULL, '11', '1117447923211173888', '1', '2019-04-14 23:22:01', '1115264086179840000', 16000, NULL, NULL);

-- ----------------------------
-- View structure for v_month_statistical
-- ----------------------------
DROP VIEW IF EXISTS `v_month_statistical`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_month_statistical` AS select ifnull(sum((case when (`po`.`order_state` = '4') then `po`.`gathering_amount` else 0 end)),0) AS `trade_amount`,ifnull(sum((case when (`po`.`order_state` = '4') then 1 else 0 end)),0) AS `paid_order_num`,count(1) AS `order_num` from `merchant_order` `po` where ((`po`.`submit_time` >= str_to_date(date_format(curdate(),'%Y-%m-01 00:00:00'),'%Y-%m-%d %H:%i:%s')) and (`po`.`submit_time` < (str_to_date(date_format(curdate(),'%Y-%m-01 00:00:00'),'%Y-%m-%d %H:%i:%s') + interval 1 month)));

-- ----------------------------
-- View structure for v_today_account_receive_order_situation
-- ----------------------------
DROP VIEW IF EXISTS `v_today_account_receive_order_situation`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_today_account_receive_order_situation` AS select `po`.`received_account_id` AS `received_account_id`,`ua`.`user_name` AS `user_name`,round(sum(`po`.`gathering_amount`),4) AS `gathering_amount`,count(1) AS `order_num`,round(sum((case when (`po`.`order_state` = '4') then `po`.`gathering_amount` else 0 end)),4) AS `paid_amount`,round(sum((case when (`po`.`order_state` = '4') then `po`.`bounty` else 0 end)),4) AS `bounty`,sum((case when (`po`.`order_state` = '4') then 1 else 0 end)) AS `paid_order_num` from (`merchant_order` `po` left join `user_account` `ua` on((`po`.`received_account_id` = `ua`.`id`))) where ((`po`.`received_account_id` is not null) and (`po`.`received_time` >= str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d %H:%i:%s')) and (`po`.`received_time` < (str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d %H:%i:%s') + interval 1 day))) group by `po`.`received_account_id`;

-- ----------------------------
-- View structure for v_today_statistical
-- ----------------------------
DROP VIEW IF EXISTS `v_today_statistical`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_today_statistical` AS select ifnull(sum((case when (`po`.`order_state` = '4') then `po`.`gathering_amount` else 0 end)),0) AS `trade_amount`,ifnull(sum((case when (`po`.`order_state` = '4') then 1 else 0 end)),0) AS `paid_order_num`,count(1) AS `order_num` from `merchant_order` `po` where ((`po`.`submit_time` >= str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d %H:%i:%s')) and (`po`.`submit_time` < (str_to_date(date_format(now(),'%Y-%m-%d'),'%Y-%m-%d %H:%i:%s') + interval 1 day)));

-- ----------------------------
-- View structure for v_total_account_receive_order_situation
-- ----------------------------
DROP VIEW IF EXISTS `v_total_account_receive_order_situation`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_total_account_receive_order_situation` AS select `po`.`received_account_id` AS `received_account_id`,`ua`.`user_name` AS `user_name`,round(sum(`po`.`gathering_amount`),4) AS `gathering_amount`,count(1) AS `order_num`,round(sum((case when (`po`.`order_state` = '4') then `po`.`gathering_amount` else 0 end)),4) AS `paid_amount`,round(sum((case when (`po`.`order_state` = '4') then `po`.`bounty` else 0 end)),4) AS `bounty`,sum((case when (`po`.`order_state` = '4') then 1 else 0 end)) AS `paid_order_num` from (`merchant_order` `po` left join `user_account` `ua` on((`po`.`received_account_id` = `ua`.`id`))) where (`po`.`received_account_id` is not null) group by `po`.`received_account_id`;

-- ----------------------------
-- View structure for v_total_cash_deposit
-- ----------------------------
DROP VIEW IF EXISTS `v_total_cash_deposit`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_total_cash_deposit` AS select sum(`user_account`.`cash_deposit`) AS `total_cash_deposit` from `user_account`;

-- ----------------------------
-- View structure for v_total_statistical
-- ----------------------------
DROP VIEW IF EXISTS `v_total_statistical`;
CREATE ALGORITHM = UNDEFINED DEFINER = `root`@`localhost` SQL SECURITY DEFINER VIEW `v_total_statistical` AS select ifnull(sum((case when (`po`.`order_state` = '4') then `po`.`gathering_amount` else 0 end)),0) AS `trade_amount`,ifnull(sum((case when (`po`.`order_state` = '4') then 1 else 0 end)),0) AS `paid_order_num`,count(1) AS `order_num` from `merchant_order` `po`;

SET FOREIGN_KEY_CHECKS = 1;
