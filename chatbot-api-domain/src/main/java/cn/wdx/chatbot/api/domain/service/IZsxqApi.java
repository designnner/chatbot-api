package cn.wdx.chatbot.api.domain.service;

import cn.wdx.chatbot.api.domain.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnansweredQuestionsTopicId(String groupId,String cookie) throws IOException;

    boolean answer(String groupId,String cookie,String topicId,String text,boolean silenced) throws IOException;

}
