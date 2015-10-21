/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.Message;
import java.util.List;

/**
 *
 * @author Serguei
 */
public interface MessagesServiceIntf {
    void addMessage(Message message);
    void setReadedMessage(Message message);
    void removeMessageById(int id);
    
    Message readMessagebyId(int id);
    List<Message> findAllTitlesMessages();
}
