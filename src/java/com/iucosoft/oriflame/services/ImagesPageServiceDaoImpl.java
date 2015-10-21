/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.ImagesPage;
import com.iucosoft.oriflame.domain.PageText;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Turkov S
 */
public class ImagesPageServiceDaoImpl implements ImagesPageServiceIntf {

    private static final Logger LOG = Logger.getLogger(ImagesPageServiceDaoImpl.class.getName());

    private static ImagesPageServiceDaoImpl instance;
    private MyDataSource myDataSource;

    private Connection conn;

    public static ImagesPageServiceDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new ImagesPageServiceDaoImpl(myDataSource);
        }
        return instance;
    }

    private ImagesPageServiceDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override
    public void addImage(ImagesPage img) {
        String sql = "INSERT INTO imagespages VALUES (null,?,?,?,?)";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setBytes(1, img.getImageData());
            pstat.setInt(2, img.getIdPageText());
            pstat.setString(3, img.getFilename());
            pstat.setString(4, img.getDirectory());
            pstat.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void removeImage(ImagesPage img) {
        String sql = "DELETE FROM imagespages WHERE id=?";
        PreparedStatement pstat = null;
        try {
            conn = myDataSource.getConnection();
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, img.getId());
            pstat.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pstat != null) {
                    pstat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ImagesPage findByIdWithoutData(int idImg) {
        ImagesPage image = null;
        String sql = "SELECT * FROM imagespages WHERE id=" + idImg;
        try {
            conn = myDataSource.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                image = new ImagesPage();
                image.setId(rs.getInt(1));
                image.setDirectory(rs.getString(5));
                image.setFilename(rs.getString(4));
                image.setIdPageText(rs.getInt(3));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return image;
    }

    @Override
    public List<ImagesPage> findImagesByPageWithoutData(PageText page) {
        List<ImagesPage> imagesList = new ArrayList<>();
        ImagesPage image = null;
        String sql = "SELECT * FROM imagespages WHERE idpagetext=" + page.getId();
        try {
            conn = myDataSource.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                image = new ImagesPage();
                image.setId(rs.getInt(1));
                image.setDirectory(rs.getString(5));
                image.setFilename(rs.getString(4));
                image.setIdPageText(rs.getInt(3));
                imagesList.add(image);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return imagesList;
    }

    @Override
    public List<ImagesPage> findAllWithoutData() {
        List<ImagesPage> imagesList = new ArrayList<>();
        ImagesPage image = null;
        String sql = "SELECT * FROM imagespages";
        Statement stat=null;
        ResultSet rs=null;
        try {
            conn = myDataSource.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                image = new ImagesPage();
                image.setId(rs.getInt(1));
                image.setDirectory(rs.getString(5));
                image.setFilename(rs.getString(4));
                image.setIdPageText(rs.getInt(3));
                imagesList.add(image);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return imagesList;
    }

    @Override
    public ImagesPage finByIdWithData(int idImg) {
        ImagesPage image = null;
        String sql = "SELECT * FROM imagespages WHERE id=" + idImg;
        Statement stat=null;
        ResultSet rs=null;
        try {
            conn = myDataSource.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                image = new ImagesPage();
                image.setId(rs.getInt(1));
                image.setDirectory(rs.getString(5));
                image.setFilename(rs.getString(4));
                image.setIdPageText(rs.getInt(3));
                image.setImageData(rs.getBytes(2));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                stat.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return image;    }

    @Override
    public int countImagesByPage(PageText page) {
        String sql = "SELECT COUNT(id) FROM imagespages WHERE idpagetext=" + page.getId();
        Statement stat = null;
        int count = 0;
        try {
            conn = myDataSource.getConnection();
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                myDataSource.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(MessagesServiceDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

}
