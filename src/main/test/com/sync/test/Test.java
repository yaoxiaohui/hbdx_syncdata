package com.sync.test;
import com.sync.control.ResourceDataSyncController;
import com.sync.dao.impl.TableInfoDaoImpl;
import com.sync.pojo.WorkOrderBean;
import com.sync.pojo.WorkOrderContentBean;
import com.zhxg.doc_classify.runstart.RunClass;
import com.zhxg.doc_classify.runstart.StartClass;
import com.zhxg.gdjl.CalModelCluster;
import com.zhxg.gdjl.InfoPair;
import com.zhxg.gdjl.ModelCluster;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ljw
 * @Description :
 * @Date Created in 15:46 2017/9/8.
 * @Modified By :
 */
public class Test {

    public static void main(String[] sad) {
        String modelString = "[{'keys':'手机加宽带 手机和电视绑定 宽带绑定 带宽带和电视 业务加起来 宽带和那个平板电视 手机宽带电视捆绑 捆绑的融合的业务 手机宽带绑定 电信宽带这个和手机那个绑定 赠这个宽带给这个电视 买手机赠红包 套餐比较合适 手机卡电视还有宽带','name':'融合新装','nice':'XZ-RHXZ'},{'keys':'装宽带 安宽带 现装宽带 4G宽带 宽带怎么装 装一下宽带 手机宽带和电视宽带捆绑 宽带网是电信的 装个宽带 办宽带 宽带是半年是多少钱 电信的那个宽带 新装一条宽带 宽带咋办 捆绑宽带 申请这个宽带 光纤的宽带 家里宽带 电视跟宽带套餐 一百兆带宽','name':'单宽新装','nice':'XZ-DKXZ'},{'keys':'机顶盒 猫和机顶盒 光猫 电信电视 加一个电视 办理电视和宽带 电信这个网络电视 宽带电视 加装一个IPTV','name':'单TV新装','nice':'XZ-DTVXZ'},{'keys':'退网 销卡 能不能销 退订 不想用 不想交钱 销这个号 退费 退了它 接受不了 销号 退机 号码销掉 自动放弃 不要了 销户 要退了 办亲情号','name':'中高危客户挽留','nice':'CFLH-ZGWHKWL'},{'keys':'流量包 天翼视讯 亲情号 腾讯的流量包 流量加油包 开通一下流量','name':'附属产品加包','nice':'JZ-FSCPJB'},{'keys':'到期 续约 返费 没有协议的','name':'租机续约','nice':'XFXY-ZJXY'},{'keys':'缴话费 交话费 交话费送话费 存多少送多少 缴费赠费 缴费赠流量 缴费赠易支付红包 参与活动缴费 缴200赠1G流量 缴200送1G流量 缴赠 缴200话费赠1G流量 预交话费赠流量 缴费就能赠流量 缴费赠送 交60块钱增60块钱话费 交费赠流量 免费获赠1G 存30赠30 存60增60 交50赠50 存30增的易支付红包 存30赠的易支付红包 直接赠 50话费赠50话费 交费赠易支付红包 缴话费 为什么没赠 交了100','name':'缴赠','nice':'XFXY-JF'},{'keys':'工信部 打官司 通管局 集团热线 315热线 网络曝光 起诉你们 监督电信服务 单位上边的部门 打120报案 投诉你们经理 省青年报反映 上传到网上 向上级 投诉 法律来维权','name':'越级倾向','nice':'WTWJ-YJQX'}]";
        //D:\\zhxg\\doc  表示日志输出位置  可以为空
        StartClass sc = new StartClass(modelString);
//        String ds = RunClass.init(modelString, "D:\\zhxg\\doc");
//        System.out.println(">>>>>>>>"+ds);
//        String title = "去市场买菜时，如果老板蹲下去，一定要留心！赶紧看看吧！！";
        String content = "流量包 原标题：去市场买菜时，流量包 如果老板蹲下去，一定要留心！赶紧看看吧！！" +
                "看了这个视频  真的不能愉快的买菜了！  女商贩一分钟内  竟迅雷不及掩耳盗铃之势  调包了两袋鱼。  " +
                "必是日积月累的实操  方能获此手法之娴熟！流量包 流量包    视频没点开的不要紧  请看动图↓  " +
                "第一次，小贩装菜的时候，拿到低处，拿另一袋掉了包    第二次，小贩索性扔掉鱼和袋子，" +
                "直接拎起提前准备好的次品上台    止不住看了好几遍  被这“鬼手”彻底折服  卖菜卖成这样，" +
                "也是成精了。  只是功夫用错了地方，欺骗消费者的事情可不能干！  注意了，买菜时留点心，" +
                "不良商贩太可恶！  多留个心眼，别让无良商贩有机可乘  看文章返回搜狐，查看更多 " +
                "beginimghttp://5b0988e595225.cdn.sohucs.com/images/20170905/23" +
                "312f73208e4c4185d082e4f932ccd7.gifendimg beginimghttp://5b0988e595225.cdn.sohucs.c" +
                "om/images/20170905/54511cf2df3645389d34e5961c915074.gifendimg ";
//        String result = RunClass.run(title, content);
        String result = sc.run("title", content);
        System.out.println("result>>>>>>"+result);

        /*String modelString = "[{'keys':'手机加宽带 手机和电视绑定 宽带绑定 带宽带和电视 业务加起来 宽带和那个平板电视 手机宽带电视捆绑 捆绑的融合的业务 手机宽带绑定 电信宽带这个和手机那个绑定 赠这个宽带给这个电视 买手机赠红包 套餐比较合适 手机卡电视还有宽带','name':'融合新装','nice':'XZ-RHXZ'},{'keys':'装宽带 安宽带 现装宽带 4G宽带 宽带怎么装 装一下宽带 手机宽带和电视宽带捆绑 宽带网是电信的 装个宽带 办宽带 宽带是半年是多少钱 电信的那个宽带 新装一条宽带 宽带咋办 捆绑宽带 申请这个宽带 光纤的宽带 家里宽带 电视跟宽带套餐 一百兆带宽','name':'单宽新装','nice':'XZ-DKXZ'},{'keys':'机顶盒 猫和机顶盒 光猫 电信电视 加一个电视 办理电视和宽带 电信这个网络电视 宽带电视 加装一个IPTV','name':'单TV新装','nice':'XZ-DTVXZ'},{'keys':'退网 销卡 能不能销 退订 不想用 不想交钱 销这个号 退费 退了它 接受不了 销号 退机 号码销掉 自动放弃 不要了 销户 要退了 办亲情号','name':'中高危客户挽留','nice':'CFLH-ZGWHKWL'},{'keys':'流量包 天翼视讯 亲情号 腾讯的流量包 流量加油包 开通一下流量','name':'附属产品加包','nice':'JZ-FSCPJB'},{'keys':'到期 续约 返费 没有协议的','name':'租机续约','nice':'XFXY-ZJXY'},{'keys':'缴话费 交话费 交话费送话费 存多少送多少 缴费赠费 缴费赠流量 缴费赠易支付红包 参与活动缴费 缴200赠1G流量 缴200送1G流量 缴赠 缴200话费赠1G流量 预交话费赠流量 缴费就能赠流量 缴费赠送 交60块钱增60块钱话费 交费赠流量 免费获赠1G 存30赠30 存60增60 交50赠50 存30增的易支付红包 存30赠的易支付红包 直接赠 50话费赠50话费 交费赠易支付红包 缴话费 为什么没赠 交了100','name':'缴赠','nice':'XFXY-JF'},{'keys':'工信部 打官司 通管局 集团热线 315热线 网络曝光 起诉你们 监督电信服务 单位上边的部门 打120报案 投诉你们经理 省青年报反映 上传到网上 向上级 投诉 法律来维权','name':'越级倾向','nice':'WTWJ-YJQX'}]";
        //D:\\zhxg\\doc  表示日志输出位置  可以为空
        String ds = RunClass.init(modelString, "");
        System.out.println(">>>>>>>>"+ds);
//        String title = "去市场买菜时，如果老板蹲下去，一定要留心！赶紧看看吧！！";
        String content = "a：您好，4G宽带有什么事您说 q：诶，您好，我想问宽带怎么装啊？ a：您是想装一下宽带是吗？ q：对 a：嗯，您这样，我帮您登记一下，会有专门的装维部门尽快跟您联系的，您看可以吗？ q：诶，好的好的好的 a：您是哪个市的。。。 q：多少时间跟我联系啊 a：嗯，尽快跟您联系，您是哪个市的 q：藁城藁城，藁城区 a：嗯，石家庄市是吗 q：对，石家庄市藁城区，对 a：藁城区，藁城区是开发区吗 q：原来是藁城市，后来划成为到藁城区了，你就写藁城区吧，知道，一说石家庄藁城区都知道，只要石家庄人都知道 a：嗯，稍等一下 q:嗯 a：您说一下详细地址，哪个村哪个街道 q：哪个村，嗯，这叫顺利路吧，顺利东路，县医院家属宿舍 a：稍等一下。嗯，那栋楼呀 q：哎呀，这是那栋楼呀，我还真不知道 a：嗯，先这么跟您登记吧。您贵姓先生 q：我姓刘 a：文刀刘是吗 q:对，文刀刘，对 a：您联系方式，留您这个本机您看方便吗 q：哎，对，就留这个电话就方便就行，留这个电话就可以 a:您证件号码方便提供吗 q：啊 a：身份证，身份证件方便提供一下吗 q：你们来了我再登记吧，行吧 a：可以 q：啊 a：嗯，这个已经帮您登记好了，提前预留好端口了，会有工作人员尽快跟您联系的 q：一般是上班以后是吗，会联系是吗 a：嗯，他们核实好之后会尽快跟您联系的 q：哎，我问一下，当天能装上吗 a：嗯，这个的话，由那个装维，装维，装维师傅进行联系的 q：诶，好嘞好嘞。行，我问一下，那个，我们现在装那个那个宽带电视，那个电视呀，你像那个电视应该是网络电视那种电视吧，能合并吗跟那个，我们的时间是，因为原来他们装的那个是 a：嗯，原来装的，是电信电视吗 q：具体我不清楚，应该是电信吧，好像是 a：哦，这个的话，到时候有装维师傅联系你的时候，你仔细，有什么问题咨询他吧，好吗 q：啊，好嘞好嘞 a：到时候给您装，有其他可以帮忙吗 q：啊，没，不需要了，谢谢啊 a：不客气，感谢来电。\n" +
                "a：您好，请讲q：诶，您好，电信是吧a：对q：我咨询一个事，就是那个个人用户装宽带现在资费是啥情况呀a：你是在邢台的市区还是农村装呀q：邢台市区的a：如果你装宽带的话呢，这边就是那个，市区的话，现在有一个买手机的活动，手机宽带和电视宽带捆绑的这个，您需要了解一下吗q：嗯，那是个什么情况呢，你再说说a：就是你到营业厅的话，购买4G的终端，买多少钱的手机呢，就送多少钱的红包，比如说你购买，那个购买了4G手机了以后呢，办理了129元的套餐，每月给你返52块钱的预支付的红包，这个会限制11期，红包返完为止，套餐呢，是包含500分钟的主叫，和1个G的全国流量，宽带和电视都是免费的q：就这一种吗a：嗯，除了这个的话，还有一个那个单宽带，只安装宽带的话是两年100M的光宽带是1440q：一下两年呗，没一年一年装那个a：一年的话是720，然后带宽的话是50M的q：哦，你说两年那个是100Ma：对q：哦，这个这个，50M，这个这个，有其他活动呗，就交这么多钱，还有别的活动啊a：对，咱们这边登记的就这样的一个活动，别的没有登记q：没有说其他，跟铁通网、移动啊、铁通那些，那个装的话有别的小赠品呀，或者有别的优惠呀a：我们这边没有登记先生，我们这边可以帮您联系一下，到时候咱们联系您，24小时之内给您回电话，到时候详细情况的话，您再咨询一下，好吧q：哦，好嘞好嘞a：哦，邢台哪个区呀q：桥西a：桥西区，具体在哪个小区q：桥西区，在建设大街吧，建设大街上哩a：建设大街的具体位置，您说一下q：建设大街那个工业学校那块哩a：工业学校q：对a：哦，这就是那个，在工业学校里边装是吗q：就在这一片嘛，说这都知道，说别的地也不知道那地方，一说工业学校都知道，那一片a：嗯，那那个，联系方式的话留哪个呀q：嗯，15631913138a：15631913138，是吗q：对，我这咨询一下看看有没有，现在装的话，有没有活动啥，有时候不是赶上什么电信日啊，十一啊什么的，都有活动啊其他公司我看a：哦，身份证号码方便提供吗q：身份证，还用身份证吗，有电话联系再说吧，留身份证干什么a：那就不给您留身份证号码了，那帮您登记了，然后那个联系方式留的156的，然后的话是给您电话好吗q：行，回电话是那块，是工程上啊，还是营业厅这啊a：应该是您那个当地的那个安装，上门安装宽带的这个人员q：哦，行行行，好嘞好嘞a：还有什么问题吗q：谢谢呀\n" +
                "a：你好，很高兴为您服务q：唉，喂，您好，我问一下你们那个电信现在有什么就是宽带有什么活动么？a：您是要现装宽带还是进行续费呢？女士q：嗯，就是现装a：是在哪里安装，石家庄市区么？q：对a：市区的话有这种融合业务，电视加宽带加手机捆绑在一起的一个融合业务到营业厅购买一个399元的礼包，里面包含200块钱话费，一部光猫，一部机顶盒然后每月只要79块钱享受100兆的免费宽带，免费电视电信服务，手机是一个G的全国流量500分钟全国通话q：什么意思呀？就是我掏399享受一年么a：不是一年，首次交399这399块钱里面包含200块钱话费，这200块钱话费可以以后抵扣这个月付，然后呃这个剩下199是一部光猫，一部机顶盒，然后以后每个月月租是79，宽带加手机的电视就都包括了q：一个月79，那一年下来是多少钱啊？a：按80算的话，一年就是960吧，960左右q：那我不要那样的呢？a：这个比较合算，不要这样的有这种办宽带q：我只安装宽带a：只要办宽带的话是720一年，50兆的带宽q：50兆的，还有高一点的么？a：嗯，我们这边登记的都是一个标准资费，其他的优惠活动你可以到当地营业厅咨询，或者是我帮您登登记一下，让当地公司来跟您联系q：呃，那如果说我要是那个什么的话那个要安装的话什么时候就安装上了？a：申请次日起7个工作日之内给你安装完毕的，一般都会尽快给您安装的q：那要是在营业厅呢？a：都是这样的，一般都会尽快给您安装的，或者是先给一个营业厅电话q：那您帮我申请一下吧a：那我帮您登记一下吧，您在哪个区安装呀？q：我在那个嗯石家庄然后跃进路和谈固东街交叉口这儿阜新苑a：跃进路与谈固东街交口q：对，阜新苑a：阜新苑q：对对对a：这属于长安区是么？q：应该属于长安区a：嗯，好的，联系方式留来电号码可以吗？q：呃，我给你留一个电话吧a：嗯，好的，您请讲q：135a：嗯q：133a：嗯q：11156a：135….q：或者打不通你打我这个电话a：嗯，好的，13513311156，是吧？q：嗯，对，如果联系不上他就联系我a：嗯，好的，您注意保持电话畅通q：嗯，好，谢谢啊a：嗯，不客气，感谢来电，稍后为我的服务作出评价。\n" +
                "a：您好，很高兴为您服务q：哎，您好，我想问一下那个，电信宽带怎么，那个电信宽带有没有按月的那个业务呀a：没有这种的q：哦，那最短期的是多长时间呢a：半年q：半年a：嗯q：半年多少钱a：你在哪装宽带呀q：嗯，石家庄那个华富城a：石家庄市区是吗q：对a：那您安装的话一个月60，半年360的，但是只能用50M的一个带宽，您得去网上营业厅申请的q：哦，就是半年是360，是吧a：对q：得去网上营业厅申请a：对q：哦，那个，哦，行行，半年360，可以a：对q：好勒a：嗯，还有别的问题吗q：谢谢a：不客气q：没有了a：那感谢来电，稍后对服务做出评价，再见。\n" +
                "a：很高兴为您服务q：诶，您好a：您好q：那个我想，宽带网是电信的a：哦，是想装个宽带是吗q：哦，就是我现在，就是刚住这个小区来，就是之前有一根电信的网线，还用重新装吗a：这个需要重新装的。您是在哪个小区的，石家庄的吗q：哦对，石家庄康庄这边a：康庄，是属于哪个区呢属于q：属于哪个区，我也说，我也不知，具体我也，我得看一下我得a：哦，到时候这样，到时候你找到了或者联系附近营业厅可以带证件去营业厅可以咨询安装的，或者是不方便去的话，到时候联系我们也行，需要核实一下这个具体的地址q：哦，我到时候看好我这地址给你说一下，你们过来装吗a：对，到时候我们会这边给你安排下去，就是期效以后，到时候我们这边会联系您的q：哦，这个，一连连多久呀这个a：一般都一年的q：没有短期的呀a：短期的话需要您咨询一下营业厅了，我这登记的一般都是一年的，要不这样，到时候您核实地址以后，如果您附近那有营业厅的话，到时候去营业厅指接办就行，看能否办半年的或几个月的q：哦，你这一年的费用大概多少呀a：一年都是720q：哦，一年都720a：对q：行行行，那麻烦了啊a：不客气，那感谢来电，稍后做出评价，再见。\n" +
                "a：您好，请讲q:喂a:您好a:你好,我，我是，我是那个扶莱县东石圈的q:嗯a:我想问，就是，就是村里，然后电信的那个宽带q:能不能宽带就是、a:啊，对，是宽带，就是上网哪个呗q:怀来县什么村里装？a:怀来县沙城镇冻水泉q:东西南北的东吗a:对对对q:市是哪个市呢a:啊？q:冻水什么a:冻水泉q:冻水泉？a:泉水的泉，对对对，q:怀来县沙城镇冻水泉村？a:对，沙城镇q:嗯，目前没有这个信息登记，无法确定，您是要新装一条宽带吗？a:嗯，对，是想新装一条，看看能不能装，然后，有多少宽的？q:目前这里的信息没有登记，能不能装无法确定，您是家里用的吗需要a:对，家里就是平时上网用的电脑，手机，上网用。q:那我帮您登记联系一下当地的安装部门吧，就是看一下能不能装，到时候您在决定办不办理a:你说什么啊，在说一遍q:我说给您登记一下您的情况到咱们的安装部门，给您查一下看能不能装，之后会联系您的a:嗯，看看q:联系电话留您本号码可以吗？您好？a:我给你个移动号吧？q；您说一下a:183q;嗯a:33683394q：18333983394a：3368 3368,对，3394q:18333683394a:对q；那您保持电话畅通，等待一下电话a:还有就是，这个宽带在村里装的话，就有多少钱的q:具体的资费优惠的话，跟您的地址有关，到时候咱们的安装部门会跟您详细介绍的，您到时候在选择适合您的办理就行a；是不是到时候他给我打电话告诉我能不能办，能办的话都有多少钱的是不是q:嗯，是的a：哦，哦，行q:咱们装宽带有个加电视的活动，我把介绍发给您，您可以了解一下a:哦q:还有其他问题吗a:没有了q:请您稍后给我评价好吗？\n" +
                "q:您好，很高兴为您服务a:您好，我想问一下，办宽带要怎么弄q:办宽带的话在河北省哪个地方装呀a:在，在那个桥东区q:桥东，这属于石家庄吗a:哦，我人是在在河北嘛然后我这个卡是北京的卡q:你不是想装宽带吗，你是想在什么地方装呀a:在高新区这边q:高新，哪个区啊这是，在石家庄哪个区啊a:属于张家口市桥东区q:张家口桥东区？a:噢q:什么地方呀a:就是那个高新区q:嗯什么小区或者什么村呀a:金地家园小区然后q:经经济家园是吗经济a:哦q:好那我们让我帮你查询一下a:刚才我在网上申请了一下然后就是没有给我任何回复嘛q:啊这你已经联系过我们了是吧a:就早点只是要完善完善完善那个营业厅那个申请了q:您去的营业厅，不是问的我们人工客服是吧a:哦，那我在这边申请的话，那网上可以取消掉吗？q:这您交过费了吗，去营业厅办的话a:我已经申请了，但是没交q:没交费是吧a:没交q:没交费的话应该没关系，这个我也不确定，得问营业厅我这边让工作帮你查询再联系号码，你还没交费呢应该没事a:反正我已经登记了然后就是没有交费q:好的帮你查询一下吧身份证号你知道吗知道也可以说一下a:你算可以再查吗 q:现在帮你查不了那么就先查询好之后再联系您吧好吧a:你要打电话的话就打我这个电话q:你给我联系电话吧好吧a:18606900636q:好再联系你还有什么其他的问题吗a:噢没有你再帮我查一下然后是没有的话我这边那个宽带那个快点等一下然后我问一下那个宽带是半年是多少钱q:我们没有查到半年的最低是一年720块钱一年a:多少钱一年？q:720块钱一年a:噢那行那那那到时候你查一下看那个我申请的那边有没有进展，没有进展的话你到时候在联系q:好的还有其他问题吗a:噢没有了，谢谢q:好麻烦你为我的服务做评价感谢来电\n" +
                "q:你好a:我想问一下电视跟宽带套餐咱有什么合适的吗现在q:那请问您这边的话是属于哪个城市什么小区的呢我帮您查一下这边的资费有什么样的a:嗯很合适q:嗯就幸福里小区幸福福里小区是吧a:对q:稍等我帮您查一下a:嗯q:这要帮你看看现在显示办理就是单宽带按照每个月60块钱是一百兆带宽可以免费看电视直播点播另外扣费的这个在首次办理的时候最少要交半年费用360再加240够买光猫机顶盒总共是600块钱这是一个然后还有一种的话是手机宽带加电视绑定在一起的这个就需要您购买手机号码了a:嗯q:你要是刚才手机号码这个的话他这个最低是每月69,手机是有一百分钟国内主叫500兆国内流量宽带也是一百兆带宽免费看电视直播的点播另外扣费他这个在办的时候需要最少交399其中200块2百块钱是作为预存话费199购买的光猫机顶盒a:噢一共我们这一次安装就是够399块钱q:对的a:交的那个扣的那个您说那个69块钱？嗯行我想现在可以安吗q:如果说要需要的话我们倒可以帮您登记上然后会有您这边负责安装的装备人员和您联系安装，会让他您先查一下有没有线路a:行你帮我查一下刚才我打过电话了说那个给我们回电话我等了两个小时了快都没回电话这个q:回复的话它会在24小时之内给您回复的,你也别太着急它会核实后跟你联系的a:噢是吗我想尽快装上我们现在已经搬过来了我想这个还挺合适的我想装上这个还得等24小时q:对这个一般的话都是在24小时之内会和您这边进行联系我帮您加急催一下,您这个再多关注一下吧a:然后你尽量加急因为他这个电话是外地的号下午就回去了就,明天下午,明天下午q:嗯好的我找也会帮您加急上的a:嗯行好的谢谢您啊q:不客气那还有其他问题吗a:嗯q:还有其他需要查询的吗a:那个没有别的了q:行好的谢谢祝您生活愉快再见\n" +
                "q:您好，很高兴为您服务a:我想问一下那个查下那个上网那个河北邯郸魏县q:家里宽带上不了网了吗现在a:不是我想申请这个宽带q:嗯刚才您说的就是家里要加装一个是吗？在农村还是在城市装呀？a:县城q:县城里边好的，我看一下这个目前的资费情况a:啊q:邯郸县城嘛是吧a:那魏县q:好的，嗯是这样的我们这边目前有两个部分一个是到营业厅买手机送红包的一个活动，就是买多少钱手机soon个多少钱的红包然后是办理一个四季办理一个59的套餐，每个月是返20的红包然后是红包返还完为止，嗯套餐里面包含一百分钟话费500兆全国流量a:嗯是有线还是无线q:无线这个是a:有线是吗无限的，我是办有线q:那这样吧我这给您登记一下吧在邯郸魏县是吧a:对q:我这给您备注一下到时候让我们这地市的工作人员跟您电话联系一下看一下有没有这个a:那我想咨询一下也就是说那个嗯再往家里面进宽带这个资费是什么之类的q:嗯,什么样的您是说a:往家里进宽带，光纤的宽带q:他这种就是光纤的宽带a:这个交多少钱吗一年q:您是说交多少钱吗？a:一年多少钱，这个进户的光纤？q:这种的话有一种单宽带是一年720呢a:噢宽带一年720是吧，两年呢q:两年1440a:这个也就是时间长也没有打折是吧q:目前没有a:那个多场时间会来给我装啊？q:24小时之内会有工作人员给你电话联系a；嗯，好了q：到时候保持话机畅通好吧a:嗯嗯q:其他问题还有咨询的吗？a:没有了q：好的，请您对我的服务评价再见\n" +
                "q:您好请讲，喂你好？a:你好我问一下宽带咋办呢q:宽装的话你是想在哪块安装呢a：在哪安装？在哪个地方吧q:对，您是在哪个地方安装啊？a:我就是先咨询一下q:因为我这块登记的比较少，现在活动比较多，所以你要需要的话我给你联系然后咱们我们那一块的给你个电话介绍一下a:我就是说现在有什么套餐呢q:这边需要买手机吗,这边都是买手机的,然后的话捆绑宽带的a:你不需要拿手机我就不要手机q:不要手机的话，那我让咱当地的给你联系一下，你是在唐山在哪一块啊a:唐山我就想问你们这儿吧q:我们这边都是买手机的，我为您做个登记不是不是说给你登记后你必须得去安装，到时候你可以不安装，先介绍一下a:嗯，那不用了那我我就向我们营业厅问吧q:可以a:好的，谢谢你啊q:不用客气。";
        String result = RunClass.run("", content);
        System.out.println("result>>>>>>"+result);
        JSONObject jsonObjectResult = new JSONObject(result);
        JSONObject jsonObjectClassify = (JSONObject) jsonObjectResult.get("result");
        String classify = (String) jsonObjectClassify.get("classify");
        System.out.println("classify>>>>>>"+classify);

        if(classify != null && !("").equals(classify)){
            String fatherClassify = classify.split("-")[0];//父分类
            System.out.println("fatherClassify>>>>>>"+fatherClassify);

            Map<String, Object> fatherClassifyMap = new HashMap<>();
            Map<String, Object> fatherClassifyMapTemp = new HashMap<>();
            Map<String, String> sunClassifyMap = new HashMap<>();
            String[] classifyArray = classify.split(" ");
            int count = 0;
            for (int i = 0; i < classifyArray.length; i++) {
                String sonClassifyString = classifyArray[i];
                String[] sonClassifyStringArray = sonClassifyString.split("\\(");
                String sonClassify = sonClassifyStringArray[0];//子分类标签
                String sonClassifyCount = sonClassifyStringArray[1].substring(0,sonClassifyStringArray[1].length()-1);//数值

                System.out.println("sonClassify>>>>>>"+sonClassify);
                System.out.println("sonClassifyCount>>>>>>"+sonClassifyCount);

                count = count + Integer.parseInt(sonClassifyCount);

                sunClassifyMap.put(sonClassify, sonClassifyCount);
            }
            fatherClassifyMapTemp.put("SON",sunClassifyMap);
            fatherClassifyMapTemp.put("COUNT",count);
            fatherClassifyMap.put(fatherClassify, fatherClassifyMapTemp);
        }
        String loopfn = "TRECORDINFO-201710100230.txt6";
        System.out.println(loopfn.replace("TRECORDINFO-", "").split("\\.")[0]);*/


    }

        /*public static void main(String[] args){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:oracle:thin:@172.16.254.206:1521:orcl";
        String userName = "HBTELE";
        String password = "HBTELE";

        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery("select * from TEL_RECORD_INFO_1");
            while (res.next()){
                System.out.println(res.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
      }*/
    /**
     * 观点聚类（暂时没用到，后期用）
     */
   /* public static List<ModelCluster> clusterCal(List<WorkOrderBean> listinfo) throws Exception {
        List<ModelCluster> list = new ArrayList<ModelCluster>();

        if (listinfo == null || listinfo.isEmpty()) return null;
        List<InfoPair> liststr = new ArrayList<InfoPair>();

        for (WorkOrderBean workOrder : listinfo) {
            InfoPair infp = new InfoPair();
            infp.id = workOrder.getId();
            infp.content = workOrder.getTextContent();
            liststr.add(infp);
        }
        System.out.println("get the cal datas:>>>>" + liststr.size());
        long s = System.currentTimeMillis();
        //聚类
        if (liststr != null && !liststr.isEmpty()) {
            list = CalModelCluster.cal(liststr, 2);
        }
        //按权重将聚类结果进行返回。
        if (list != null && !list.isEmpty()) {
            int max = list.get(0).getCount();
            int i = 0;
            for (; i < list.size() && i < 100; i++) {
                if (list.get(i).getCount() < max / 20)
                    break;
            }
            if (i < list.size()) {
                list = list.subList(0, i);
            }
        }
        long e = System.currentTimeMillis();
        System.out.println("get the cluster time:>>>>" + (e - s));
        return list;
    }

    public static void main(String[] args) {
        List<WorkOrderBean> workOrderBeans = new ArrayList();
        WorkOrderBean workOrderBean1 = new WorkOrderBean();
        workOrderBean1.setId("1");
        workOrderBean1.setTextContent(
                "3175_A.wav 70_19200_她很高兴说有好有好麻烦您看下下一班的高三的农村都觉得是县城记下去再发现成这是指一般的那种但也有具体的有的也有可能有具体的范围涉及三十一套单位这一辈子他也没了钱去杀了九一八九九的正面不限量套餐？\n" +
                "3175_Q.wav\n 70_22190_你好我想问一下咱们那个电信不是有的套餐只针对于农村就是那个通话时间的那个当时我想呃我想问一下他这个农村的范围知道听你说我没有好我就是想扮好呢就是像过去一样他这个范围吧现场也算农村如果那个就是你能帮我看一下嘛就是我就是有一个一千分钟的阿呆？");
        WorkOrderBean workOrderBean2 = new WorkOrderBean();
        workOrderBean2.setId("2");
        workOrderBean2.setTextContent(
                "3176_A.wav 70_10220_你好幸福嘻哈有一个五十块钱一个月的流量版绅士这种就不是小三的也可能是成七八月份的如果你不推电话本是我把你推进门今天会下雨？"+
                        "3176_Q.wav 70_5740_是吗李娜加油吧？");
        workOrderBeans.add(workOrderBean1);
        workOrderBeans.add(workOrderBean2);
        try {
            clusterCal(workOrderBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}

