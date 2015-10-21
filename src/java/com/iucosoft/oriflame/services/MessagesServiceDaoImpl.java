/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serguei
 */
public class MessagesServiceDaoImpl implements MessagesServiceIntf {

    private static MessagesServiceDaoImpl instance;
    private MyDataSource myDataSource;

    private Connection conn;

    public static MessagesServiceDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new MessagesServiceDaoImpl(myDataSource);
        }
        return instance;
    }

    private MessagesServiceDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override
    public void addMessage(Message message) {
        String sql = "INSERT INTO messages VALUES (null,?,?,?,?,?,?)";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
            pstat.setString(2, message.getName());
            pstat.setString(3, message.getEmail());
            pstat.setString(4, message.getPhone());
            pstat.setByte(5, (byte) 0);
            pstat.setString(6, message.getMessage());
            pstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void setReadedMessage(Message message) {
        String sql = "UPDATE messages SET isreaded=1 WHERE id=?";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, message.getId());
            pstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    @Override
    public void removeMessageById(int id) {
        String sql = "DELETE FROM messages WHERE id=?";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, id);
            pstat.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Message readMessagebyId(int id) {
        String sql = "SELECT * FROM messages WHERE id=?";
        PreparedStatement pstat = null;
        ResultSet rs = null;
        Message message = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, id);
            rs = pstat.executeQuery();
            if (rs.next()) {
                message = new Message();
                message.setId(id);
                message.setDate(rs.getTimestamp(2));
                message.setName(rs.getString(3));
                message.setEmail(rs.getString(4));
                message.setPhone(rs.getString(5));
                message.setIsReaded(rs.getBoolean(6));
                message.setMessage(rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return message;
    }

    @Override
    public List<Message> findAllTitlesMessages() {
        String sql = "SELECT * FROM messages ORDER BY id DESC";
        Statement stat = null;
        ResultSet rs = null;
        Message message = null;
        List<Message> messagesList = new ArrayList<>();
        try {
            conn = myDataSource.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                message = new Message();
                message.setId(rs.getInt(1));
                message.setDate(rs.getTimestamp(2));
                message.setName(rs.getString(3));
                message.setEmail(rs.getString(4));
                message.setPhone(rs.getString(5));
                message.setIsReaded(rs.getBoolean(6));
                messagesList.add(message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stat != null) {
                    stat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return messagesList;
    }

}
