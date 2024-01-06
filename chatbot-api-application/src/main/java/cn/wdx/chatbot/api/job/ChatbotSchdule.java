package cn.wdx.chatbot.api.job;

import cn.wdx.chatbot.api.domain.qfchat.IOpenAi;
import cn.wdx.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wdx.chatbot.api.domain.zsxq.model.res.RespData;
import cn.wdx.chatbot.api.domain.zsxq.model.vo.Topics;
import cn.wdx.chatbot.api.domain.zsxq.service.IZsxqApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author wudanxin
 * @version 1.0
 * @description: TODO
 * @date 2024-01-04 20:23
 */

@Configuration
@EnableScheduling
public class ChatbotSchdule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchdule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenAi openAi;
    /**
     * @description: 定时任务每小时跑一次，扫描问题列表，使用chatgpt回答问题
     * @param:
     * @return: void
     * @author wudanxin
     * @date: 2024-01-04 20:41
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void run() throws Exception {
        try {

            if(new Random().nextBoolean()){
                logger.info("任务打烊中，不执行。");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if(hour > 21 || hour < 6){
                logger.info("访问时间不对劲，不做请求。");
                return ;
            }


            UnAnsweredQuestionsAggregates unansweredQues = zsxqApi.queryUnansweredQuestionsTopicId(groupId, cookie);

            //里面包含topics，即问题列表
            RespData resp_data = unansweredQues.getResp_data();

            List<Topics> topics = resp_data.getTopics();

            if(null == topics || topics.isEmpty()){
                logger.warn("本次未检索到");
                return ;
            }

            Topics topic = topics.get(0);

            String topic_id = topic.getTopic_id();
            String question = topic.getQuestion().getText();

            logger.info("topic_id：{},question：{}",topic_id,question);
            boolean status = false;
            if(!topic.getAnswered()){
                String answer = openAi.answerByAi(question);
                status = zsxqApi.answer(groupId, cookie, topic_id, answer, false);
            }

            logger.info("回答状态：{}",status);

        }catch (Exception e){
            logger.info("Scheduled error：",e.getMessage());
            throw new Exception();
        }
    }


}
