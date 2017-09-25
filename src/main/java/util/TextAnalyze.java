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
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextAnalyze {

    /**
     * 拼接模型json
     */
    public static String getModelStr(List<CategoryBean> sonCategoryList){

        List<Map> modelStrList = new ArrayList<>();
        for(CategoryBean category:sonCategoryList){
            Map<String,String> modelMap = new HashMap<>();
            modelMap.put("name",category.getName());
            modelMap.put("nice",category.getAlias());
            modelMap.put("keys",category.getRoleWord());
            modelStrList.add(modelMap);
            modelMap = null;
        }
        return JsonUtil.toJSONString(modelStrList);
    }

    /**
     * 标签分析
     */
    public static Map<String, Object> categoryAnalyze(String modelString, List<WorkOrderBean> workOrderBeans, List<CategoryBean> categoryBeans) {

        for (int i = 0; i < workOrderBeans.size() ; i++) {

            WorkOrderBean workOrderBean = workOrderBeans.get(i);

            String content = workOrderBean.getTextContent();
            RunClass.init(modelString, "");
            String result = RunClass.run("", content);
            System.out.println("result>>>>>>"+result);
            JSONObject jsonObjectResult = new JSONObject(result);
            JSONObject jsonObjectClassify = (JSONObject) jsonObjectResult.get("result");
            String classify = (String) jsonObjectClassify.get("classify");
            System.out.println("classify>>>>>>"+classify);

            if(classify != null && !("").equals(classify)){
                String fatherClassify = classify.split("-")[0];//父分类
                System.out.println("fatherClassify>>>>>>"+fatherClassify);

                String[] classifyArray = classify.split(" ");
                int count = 0;
                for (int j = 0; j < classifyArray.length; j++) {
                    String sonClassifyString = classifyArray[i];
                    String[] sonClassifyStringArray = sonClassifyString.split("\\(");
                    String sonClassify = sonClassifyStringArray[0];//子分类标签
                    String sonClassifyCount = sonClassifyStringArray[1].substring(0,sonClassifyStringArray[1].length()-1);//数值

                    System.out.println("sonClassify>>>>>>"+sonClassify);
                    System.out.println("sonClassifyCount>>>>>>"+sonClassifyCount);


                    count = count + Integer.parseInt(sonClassifyCount);

                }
            }
        }

        return null;
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
