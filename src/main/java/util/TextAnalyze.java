package util;

import com.sync.pojo.CategoryBean;
import com.sync.pojo.WorkOrderBean;
import com.sync.pojo.WorkOrderContentBean;
import com.sync.service.TableInfoService;
import com.sync.service.impl.TableInfoServiceImpl;
import com.zhxg.doc_classify.runstart.RunClass;
import com.zhxg.gdjl.CalModelCluster;
import com.zhxg.gdjl.InfoPair;
import com.zhxg.gdjl.ModelCluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextAnalyze {

    /**
     * 拼接模型json
     */
    public static String getModelStr(){

        TableInfoService tableInfoService = new TableInfoServiceImpl();

        List<CategoryBean> sonCategoryList = tableInfoService.queryTableInfoCategory();
        List<Map> modelStrList = new ArrayList<>();
        for(CategoryBean category:sonCategoryList){
            Map<String,String> modelMap = new HashMap<>();
            modelMap.put("name",category.getName());
            modelMap.put("nice",category.getAlias());
            modelMap.put("keys",category.getRoleWord());
            modelStrList.add(modelMap);
            modelMap = null;
        }
        return JSONUtils.toJSONString(modelStrList);
    }

    /**
     * 标签分析
     */
    public static String categoryAnalyze(String modelString, String content) {
//        String modelString = "[{'name':'负面-政府负面-环保问题-水体污染','nice':'环保问题','keys':'变差,变黄,浑浊,异常,水域,河流,河水,湖水,海洋,地下水,自来水,污水,废水,脏水,臭水,红水,黄水,黑水,海域'},{'name':'负面-政府负面-腐败问题-霸占问题','nice':'腐败问题','keys':'路霸侵吞霸占霸市菜霸街霸警霸冒领退税款无端被强征暴力毁占疯狂抢占强占鱼池被无故因开挖提水站占用土地'},{'name':'负面-政府负面-民生问题-土地问题','nice':'民生问题','keys':'强制征地非法占用占用农田倒卖集体土地非法征地数量大\n'}]";
        //D:\\zhxg\\doc  表示日志输出位置  可以为空
        RunClass.init(modelString, "");
        String result = RunClass.run("", content);
        return result;
    }

    /**
     * 观点聚类（暂时没用到，后期用）
     */
    public List<ModelCluster> clusterCal(List<WorkOrderContentBean> listinfo)throws Exception{
        List<ModelCluster> list = new ArrayList<ModelCluster>();

        if(listinfo==null||listinfo.isEmpty())return null;
        List<InfoPair> liststr=new ArrayList<InfoPair>();

        for(WorkOrderContentBean workOrder:listinfo){
            InfoPair infp=new InfoPair();
            infp.id=workOrder.getWorkOrderId();
            infp.content=workOrder.getContent();
            liststr.add(infp);
        }
        System.out.println("get the cal datas:>>>>"+liststr.size());
        long s=System.currentTimeMillis();
        //聚类
        if(liststr!=null&&!liststr.isEmpty()){
            list= CalModelCluster.cal(liststr, 2);
        }
        //按权重将聚类结果进行返回。
        if(list!=null&&!list.isEmpty()){
            int max = list.get(0).getCount();
            int i=0;
            for(;i<list.size()&&i<100;i++){
                if(list.get(i).getCount()<max/20)
                    break;
            }
            if(i<list.size()){
                list = list.subList(0, i);
            }
        }
        long e=System.currentTimeMillis();
        System.out.println("get the cluster time:>>>>"+(e-s));
        return list;
    }
}
