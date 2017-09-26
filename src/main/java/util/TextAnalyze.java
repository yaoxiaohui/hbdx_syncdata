package util;

import com.sync.pojo.*;
import com.zhxg.doc_classify.runstart.StartClass;
import com.zhxg.gdjl.CalModelCluster;
import com.zhxg.gdjl.InfoPair;
import com.zhxg.gdjl.ModelCluster;
import lombok.extern.java.Log;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log
public class TextAnalyze {

    /**
     * 拼接模型json
     */
    public static String getModelStr(List<CategoryBean> sonCategoryList) {

        List<Map> modelStrList = new ArrayList<>();
        for (CategoryBean category : sonCategoryList) {
            Map<String, String> modelMap = new HashMap<>();
            modelMap.put("name", category.getName());
            modelMap.put("nice", category.getAlias());
            modelMap.put("keys", category.getRoleWord());
            modelStrList.add(modelMap);
            modelMap = null;
        }
        return JsonUtil.toJSONString(modelStrList);
    }

    /**
     * 标签分析
     */
    public static int[][] categoryAnalyze(String modelString, List<WorkOrderBean> workOrderBeans, List<CategoryBean> categoryBeans) {

        int[][] tagAnalysisArr = new int[CountyMapping.getNumOfCounty() + 1][CategoryMapping.getNumOfCategory() + 1];
        StartClass sc = new StartClass(modelString);
        Integer countyIndex;
        Integer categoryIndex;
        String categoryAlias;
        StringBuilder keyword_sb = new StringBuilder();
        JSONObject eachWorkOrderMatchCategroy;
        WorkOrderBean workOrderBean;
        for (int i = 0; i < workOrderBeans.size(); i++) {

            workOrderBean = workOrderBeans.get(i);
//            传入标题和内容的String类型，返回json类型
//            {"result":[{"negative":"1","tagclassly":"快递速度快","words":"就收到","weight":"388"},{"negative":"1","tagclassly":
//                "快递正面评价", "words":"快递.给力","weight":"240"},{"negative":"-1","tagclassly":"评价","words":"是一般","weight":"28"}],"code":"true"}
            String content = workOrderBean.getTextContent();
            // 获取地区index
            countyIndex = CountyMapping.getIndexByGivenCode(workOrderBean.getCid());
            String result = sc.run("title", content);
            log.info("result>>>>>>" + result);
            keyword_sb.delete(0, keyword_sb.length());
            JSONObject resp_jo = new JSONObject(result);
            if(StringUtils.isEmpty(resp_jo)){
                continue;
            }
            Object resultTemp = resp_jo.get("result");
            JSONArray result_ja = resp_jo.getJSONArray("result");
            if(StringUtils.isEmpty(resultTemp) || result_ja.isEmpty()){
                continue;
            }
            JSONObject eachresult_jo;
            eachWorkOrderMatchCategroy = new JSONObject();
            for (int j = 0; j < result_ja.length(); j++) {
                eachresult_jo = (JSONObject) result_ja.get(j);
                // 取业务所属类目index
                categoryIndex = CategoryMapping.getIndexByGivenAlias(eachresult_jo.get("tagclassly").toString());
                categoryAlias = eachresult_jo.get("tagclassly").toString();
                // 对应地区和业务的变量增1
                ++tagAnalysisArr[countyIndex][categoryIndex];

                keyword_sb.append(eachresult_jo.get("words").toString());
                if (j != result_ja.length() - 1) {
                    keyword_sb.append(",");
                }
                eachWorkOrderMatchCategroy.put(categoryAlias, eachresult_jo.get("weight").toString());
            }
            workOrderBean.setKeyword(keyword_sb.toString());
            workOrderBean.setMatchCategory(eachWorkOrderMatchCategroy.toString());
        }
        return tagAnalysisArr;
    }

    /**
     * 观点聚类（暂时没用到，后期用）
     */
    public List<ModelCluster> clusterCal(List<WorkOrderContentBean> listinfo) throws Exception {
        List<ModelCluster> list = new ArrayList<ModelCluster>();

        if (listinfo == null || listinfo.isEmpty()) return null;
        List<InfoPair> liststr = new ArrayList<InfoPair>();

        for (WorkOrderContentBean workOrder : listinfo) {
            InfoPair infp = new InfoPair();
            infp.id = workOrder.getWorkOrderId();
            infp.content = workOrder.getContent();
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
}
