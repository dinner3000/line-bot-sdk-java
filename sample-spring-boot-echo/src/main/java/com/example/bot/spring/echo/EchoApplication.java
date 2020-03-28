/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import com.linecorp.bot.model.action.Action;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ButtonsTemplate;
import com.linecorp.bot.model.message.template.Template;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    private final Logger log = LoggerFactory.getLogger(EchoApplication.class);

    private final SnowFlakeUIDGenerator snowFlakeUIDGenerator;

    private final NewFollowerTemporaryStorage newFollowerTemporaryStorage;

    public EchoApplication(SnowFlakeUIDGenerator snowFlakeUIDGenerator, NewFollowerTemporaryStorage newFollowerTemporaryStorage) {
        this.snowFlakeUIDGenerator = snowFlakeUIDGenerator;
        this.newFollowerTemporaryStorage = newFollowerTemporaryStorage;
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws URISyntaxException {
        log.info("event: " + event);
//        final String originalMessageText = event.getMessage().getText();
//        return new TextMessage(originalMessageText);
        String branchCode = (String) RequestContextHolder.currentRequestAttributes().getAttribute("branchCode", WebRequest.SCOPE_REQUEST);

        long nonce = snowFlakeUIDGenerator.get();
        NewFollowerEntity newFollowerEntity = new NewFollowerEntity(nonce, branchCode, event.getSource().getUserId());
        newFollowerTemporaryStorage.put(nonce, newFollowerEntity);

        List<Action> actionList = new ArrayList<Action>();
        actionList.add(new URIAction("Sign Up", new URI(String.format("https://login-starter.herokuapp.com/signup?nonce=%d", nonce)), null));
        Template template = new ButtonsTemplate(null, "Sign Up", "Please click below button to connect service notifications", actionList);

        return new TemplateMessage("Sign Up", template);
    }

    @EventMapping
    public Message handleFollowEvent(FollowEvent event) throws URISyntaxException {
        log.info("event: " + event);

        String branchCode = (String) RequestContextHolder.currentRequestAttributes().getAttribute("branchCode", WebRequest.SCOPE_REQUEST);

        long nonce = snowFlakeUIDGenerator.get();
        NewFollowerEntity newFollowerEntity = new NewFollowerEntity(nonce, branchCode, event.getSource().getUserId());
        newFollowerTemporaryStorage.put(nonce, newFollowerEntity);

        List<Action> actionList = new ArrayList<Action>();
        actionList.add(new URIAction("Sign Up", new URI(String.format("https://login-starter.herokuapp.com/signup?nonce=%d", nonce)), null));
        Template template = new ButtonsTemplate(null, "Sign Up", "Please click below button to connect service notifications", actionList);

        return new TemplateMessage("Sign Up", template);
    }

    @EventMapping
    public Message handleUnfollowEvent(UnfollowEvent event) {
        log.info("event: " + event);
        return new TextMessage("Got unfollowed event");
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
